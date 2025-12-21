import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

// Practice Set 4: Composable Validation
public class Solution {
    public record ValidationResult(boolean valid, List<String> messages) {
        public static ValidationResult ok() { return new ValidationResult(true, List.of()); }
        public static ValidationResult fail(String msg) { return new ValidationResult(false, List.of(msg)); }
        public static ValidationResult failAll(List<String> msgs) { return new ValidationResult(false, List.copyOf(msgs)); }
    }

    @FunctionalInterface
    public interface Validator<T> {
        ValidationResult validate(T value);

        default Validator<T> and(Validator<T> other) {
            return v -> {
                ValidationResult r1 = this.validate(v);
                if (!r1.valid) return r1; // short-circuit
                ValidationResult r2 = other.validate(v);
                if (!r2.valid) return r2;
                return ValidationResult.ok();
            };
        }

        default Validator<T> or(Validator<T> other) {
            return v -> {
                ValidationResult r1 = this.validate(v);
                if (r1.valid) return ValidationResult.ok();
                ValidationResult r2 = other.validate(v);
                if (r2.valid) return ValidationResult.ok();
                return ValidationResult.failAll(StreamUtils.concat(r1.messages, r2.messages));
            };
        }

        default <U> Validator<U> compose(java.util.function.Function<U, T> mapper) {
            return u -> this.validate(mapper.apply(u));
        }

        static <T> Validator<T> lift(Predicate<T> predicate, String message) {
            return v -> predicate.test(v) ? ValidationResult.ok() : ValidationResult.fail(message);
        }
    }

    public record User(String name, String email, int age, String password) {}

    public static void main(String[] args) {
        Validator<String> nonEmpty = Validator.lift(s -> s != null && !s.isBlank(), "must not be empty");
        Validator<String> email = Validator.lift(s -> s != null && s.matches("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$"), "invalid email");
        Validator<Integer> age = Validator.lift(a -> a >= 18 && a <= 120, "age must be 18-120");
        Validator<String> strongPwd = Validator.lift(p -> p != null && p.length() >= 8 && p.matches(".*[A-Z].*") && p.matches(".*[0-9].*"), "weak password");

        Validator<User> userValidator = nonEmpty.compose(User::name)
                .and(email.compose(User::email))
                .and(age.compose(User::age))
                .and(strongPwd.compose(User::password));

        User good = new User("Alice", "alice@example.com", 30, "Str0ngPass");
        User bad = new User("", "nope", 15, "weak");

        System.out.println("Good user -> " + userValidator.validate(good));
        System.out.println("Bad user  -> " + userValidator.validate(bad));

        // Demonstrate OR (either corporate email or personal email)
        Validator<String> corporateEmail = Validator.lift(s -> s.endsWith("@corp.com"), "not corporate email");
        Validator<String> personalEmail = Validator.lift(s -> s.endsWith("@gmail.com"), "not gmail");
        Validator<User> emailRule = corporateEmail.or(personalEmail).compose(User::email);

        User corp = new User("Bob", "bob@corp.com", 28, "Str0ngPass1");
        User personal = new User("Cara", "cara@gmail.com", 28, "Str0ngPass1");
        User other = new User("Dan", "dan@yahoo.com", 28, "Str0ngPass1");

        System.out.println("Corp email -> " + emailRule.validate(corp));
        System.out.println("Personal   -> " + emailRule.validate(personal));
        System.out.println("Other      -> " + emailRule.validate(other));
    }
}

// Utility to concat lists immutably
class StreamUtils {
    public static List<String> concat(List<String> a, List<String> b) {
        return List.copyOf(new java.util.ArrayList<>() {{ addAll(a); addAll(b); }});
    }
}

