import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;

// Practice Set 5: Type Tokens & Reflection with Generics
public class Solution {
    // 1) TypeToken capturing generic type via subclass
    public static class TypeToken<T> {
        private final Type type;
        protected TypeToken() {
            Type superClass = getClass().getGenericSuperclass();
            if (superClass instanceof ParameterizedType pt) {
                this.type = pt.getActualTypeArguments()[0];
            } else {
                throw new IllegalArgumentException("TypeToken must be created with anonymous subclass");
            }
        }
        public Type getType() { return type; }
        @Override public String toString() { return "TypeToken{" + type + '}'; }
    }

    // 2) Mock JsonMapper using TypeToken to show preserved element type
    public static class JsonMapper {
        public <T> List<T> fromJsonArray(String json, TypeToken<T> token) {
            // Mock deserialization: we just echo an empty list but show the type captured.
            System.out.println("Deserializing JSON array to type: " + token.getType());
            return List.of();
        }
    }

    // 3) Registry keyed by Class<? extends T>
    public static class Registry<T> {
        private final Map<Class<? extends T>, T> map = new HashMap<>();
        public <S extends T> void register(Class<S> type, S instance) { map.put(type, instance); }
        public <S extends T> Optional<S> get(Class<S> type) { return Optional.ofNullable(type.cast(map.get(type))); }
    }

    // 4) newInstance helper
    public static <T> Optional<T> newInstance(Class<T> type) {
        try {
            return Optional.of(type.getDeclaredConstructor().newInstance());
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    // 5) Inspect generic superclass parameters
    public static class Box<T> { /* generic container */ }
    public static class StringBox extends Box<String> { }

    public static Optional<List<Type>> inspectTypeParameters(Class<?> cls) {
        Type superType = cls.getGenericSuperclass();
        if (superType instanceof ParameterizedType pt) {
            return Optional.of(List.of(pt.getActualTypeArguments()));
        }
        return Optional.empty();
    }

    public static void main(String[] args) {
        // TypeToken demo
        TypeToken<List<String>> token = new TypeToken<>() {};
        System.out.println("Captured type: " + token.getType());

        JsonMapper mapper = new JsonMapper();
        mapper.fromJsonArray("[\"a\",\"b\"]", new TypeToken<String>() {});

        // Registry demo
        Registry<Number> reg = new Registry<>();
        reg.register(Integer.class, 42);
        reg.register(Double.class, 3.14);
        System.out.println("Registry get Integer -> " + reg.get(Integer.class));
        System.out.println("Registry get Double  -> " + reg.get(Double.class));

        // newInstance demo
        System.out.println("newInstance ArrayList -> " + newInstance(ArrayList.class));

        // Inspect type parameters
        System.out.println("StringBox params -> " + inspectTypeParameters(StringBox.class));
    }
}

