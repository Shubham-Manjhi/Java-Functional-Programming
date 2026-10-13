<div align="center">

# ☕ Java SE 21 Developer (1Z0-830)
### Chapter 2 · Operations and Core Data Types

![Java](https://img.shields.io/badge/Java-SE%2021-E76F00?style=for-the-badge&logo=openjdk&logoColor=white)
![Exam](https://img.shields.io/badge/Exam-1Z0-830-C74634?style=for-the-badge&logo=oracle&logoColor=white)

</div>

---

<div align="center">

## 📗 Topic 1 · Module Introduction

*Handling Date, Time, Text, Numeric and Boolean Values*

</div>

> **Module at a glance** — This module covers the three exam objectives grouped under the heading **"Handling date, time, text, numeric, and Boolean values."** Together they span the primitive and wrapper types, expression evaluation, text handling, and the modern date-time API.

---

### 1.1 Exam Objectives Covered

| # | Objective | Focus Area |
|:--:|:--|:--|
| 1 | **Use primitive types and wrapper classes**; evaluate arithmetic and Boolean expressions using the **Math API**, applying **precedence rules, type conversions and casting** | Numeric and Boolean values |
| 2 | **Manipulate text**, including **text blocks**, using the `String` and `StringBuilder` classes | Text |
| 3 | **Manipulate date, time, duration, period, instant and time-zone objects**, including **daylight saving time**, using the Date-Time API | Date and time |

---

### 1.2 Module Map

```text
        Handling Date, Time, Text, Numeric and Boolean Values
        ├── 1. Primitives & Wrappers ─┬─ Math API
        │                             ├─ Operator precedence
        │                             └─ Type conversion & casting
        ├── 2. Text ──────────────────┬─ String
        │                             ├─ StringBuilder
        │                             └─ Text blocks
        └── 3. Date-Time API ─────────┬─ LocalDate / LocalTime / LocalDateTime
                                      ├─ Duration / Period / Instant
                                      └─ Time zones & daylight saving time
```

---

### 1.3 Key Terms Preview

| Term | Meaning |
|:--|:--|
| **Primitive type** | Built-in value type (e.g. `int`, `double`, `boolean`) |
| **Wrapper class** | Object form of a primitive (e.g. `Integer`, `Double`, `Boolean`) |
| **Precedence** | Rules deciding which operator is evaluated first |
| **Casting** | Explicit conversion of a value from one type to another |
| **Text block** | Multi-line string literal delimited by `"""` |
| **Duration** | Time-based amount (hours, minutes, seconds) |
| **Period** | Date-based amount (years, months, days) |
| **Instant** | A point on the UTC timeline |

---

### 1.4 Exam Tip

> 💡 These topics are **small in syntax but rich in traps**: implicit widening, integer overflow, `String` immutability, and daylight-saving transitions are favourite sources of tricky questions. Write code for every rule and predict the output before running it.

<div align="center">

<sub>Java SE 21 Developer (1Z0-830) · Topic 1 of the study guide</sub>

</div>

---

<div style="page-break-after: always;"></div>

<div align="center">

## 📗 Topic 2 · Learning Objectives

*Operators, conversions, wrapper classes, references and equality*

</div>

> **Lesson at a glance** — This lesson builds the foundation for evaluating expressions in Java: how operators combine, how values are promoted and cast, how wrappers behave, how references differ from primitives, and how equality is defined.

---

### 2.1 Learning Path

```text
  Operators ─► Precedence & Order of Evaluation ─► Promotion & Casting
                                                         │
   Equality & equals() ◄─ Primitives vs References ◄─ Wrapper Classes
```

---

### 2.2 Objectives in Detail

| # | Area | What You Will Learn |
|:--:|:--|:--|
| 1 | **Operators** | Java's operator set, **precedence**, **L-values**, **side effects**, and the **order of evaluation of operands** |
| 2 | **Promotion and casting** | Operand promotion rules; special rules for initialization with **constant expressions** |
| 3 | **Conditional expression types** | The type of a conditional expression, including the **`?:` (ternary) operator** and **`switch` expressions** |
| 4 | **Wrapper classes** | Their purpose and features; rules and effects of **autoboxing** and **unboxing** |
| 5 | **Primitive vs reference types** | The distinction between them, and **aliasing** when two references refer to the same object |
| 6 | **Equality** | `==` versus `equals`, and the rules for **implementing an `equals` method** |

---

### 2.3 Key Terms Preview

| Term | Meaning |
|:--|:--|
| **L-value** | An expression that denotes a storage location and can be assigned to (e.g. a variable) |
| **Side effect** | A change of state caused by evaluating an expression (e.g. `i++`) |
| **Order of evaluation** | The sequence in which operands are evaluated; distinct from precedence |
| **Promotion** | Automatic widening of operands to a common type |
| **Constant expression** | A compile-time-computable expression; enables special narrowing on initialization |
| **Autoboxing / Unboxing** | Automatic conversion between a primitive and its wrapper object |
| **Aliasing** | Two references pointing at the same object, so changes are visible through both |

---

### 2.4 Common Pitfalls to Watch For

| ⚠️ Pitfall | Why It Matters |
|:--|:--|
| Confusing precedence with evaluation order | Precedence groups operators; operands are still evaluated left to right |
| Assuming a narrowing assignment always fails | Constant expressions that fit the target type may compile without a cast |
| Unboxing a `null` wrapper | Throws `NullPointerException` |
| Using `==` on objects | Compares references, not content |
| Overriding `equals` carelessly | Must be reflexive, symmetric, transitive, consistent and null-safe |

> 💡 **Exam Tip:** Predict the output of each snippet before running it. Most questions here hinge on a single subtle rule.

<div align="center">

<sub>Java SE 21 Developer (1Z0-830) · Chapter 2 · Topic 2 of the study guide</sub>

</div>

---

<div style="page-break-after: always;"></div>

<div align="center">

## 📙 Topic 3 · Java Operators, Part 1

*Operator families, expressions, L-values, side effects and operand evaluation*

</div>

> **Topic at a glance** — Java has many operators, most of them familiar. The exam rewards a precise understanding of a few: **increment/decrement, bitwise vs logical negation, shifts, `instanceof`, short-circuit operators, `%`** and the difference between an *expression*, an *L-value* and a *side effect*.

---

### 3.1 Operator Families (highest to lowest precedence)

| Level | Family | Operators | Notes |
|:--:|:--|:--|:--|
| 1 | **Postfix** | `i++`  `i--` | Value is taken **before** the change |
| 2 | **Prefix / unary** | `++i`  `--i`  `+`  `-`  `~`  `!` | Prefix changes the value **first** |
| 3 | **Multiplicative** | `*`  `/`  `%` | Evaluated **before** `+` and `-` |
| 4 | **Additive** | `+`  `-` | |
| 5 | **Shift** | `<<`  `>>`  `>>>` | |
| 6 | **Relational** | `<`  `>`  `<=`  `>=`  `instanceof` | |
| 7 | **Equality** | `==`  `!=` | Studied in detail later |
| 8 | **Bitwise / logical** | `&`  then `^`  then `\|`  then `&&`  then `\|\|` | Strict left-to-right order of precedence |
| 9 | **Conditional** | `? :` | Acts like an `if / else` expression |
| 10 | **Assignment** | `=`  `+=`  `-=` … | Lowest; an assignment is itself an expression with a value |

> 📌 Full precedence order is **rarely tested**. Remember mainly that `* / %` run before `+ -`, and that `&&` / `||` are lower than `&` / `^` / `|`.

#### Example — Precedence (`PrecedenceDemo.java`)

```java
public class PrecedenceDemo {
    public static void main(String[] args) {
        System.out.println(2 + 3 * 4);      // 14  (* before +)
        System.out.println((2 + 3) * 4);    // 20
        System.out.println(10 - 6 / 3);     // 8
        System.out.println(true | false & false);  // true  (& binds tighter than |)
        int x = 5;
        x += 3 * 2;                          // assignment is lowest
        System.out.println(x);               // 11
        int y = 3;
        int z = y > 2 ? 10 : 20;             // conditional behaves like if/else
        System.out.println(z);               // 10
    }
}
```

**Output**
```text
14
20
8
true
11
10
```

<div style="page-break-after: always;"></div>

### 3.2 Unary Operators: `+`, `-`, `~`, `!`

| Operator | Meaning | Key Point |
|:--:|:--|:--|
| `+x`  `-x` | Unary plus / minus | Marks a number positive, or **negates** any value |
| `~x` | **Bitwise inversion** | Flips **every binary digit**; integral types only |
| `!x` | **Logical negation** | Works **only on `boolean`**; do not confuse with `~` |

#### Example (`NegationDemo.java`)

```java
public class NegationDemo {
    public static void main(String[] args) {
        int n = 5;
        boolean flag = true;

        System.out.println(+n);     // 5
        System.out.println(-n);     // -5
        System.out.println(~n);     // -6  (all bits inverted)
        System.out.println(!flag);  // false
        // System.out.println(!n);  // compile error: ! needs a boolean
        // System.out.println(~flag); // compile error: ~ needs an integral type
    }
}
```

**Output**
```text
5
-5
-6
false
```

---

### 3.3 Shift Operators: `<<`, `>>`, `>>>`

| Operator | Meaning | Key Point |
|:--:|:--|:--|
| `<<` | Left shift | Each shift **doubles** the value (ignoring overflow) |
| `>>` | Arithmetic right shift | Each shift **halves** the value; integer division loses the fraction; **sign is preserved** |
| `>>>` | **Unsigned** right shift | Sign bit is **not** propagated; a negative becomes a **large positive** with no obvious numeric link (rarely tested) |

#### Example (`ShiftDemo.java`)

```java
public class ShiftDemo {
    public static void main(String[] args) {
        int a = 8;
        System.out.println(a << 1);    // 16
        System.out.println(a >> 1);    // 4
        System.out.println(-8 >> 1);   // -4
        System.out.println(-8 >>> 1);  // 2147483644
        System.out.println(7 >> 1);    // 3  (fraction lost)
    }
}
```

**Output**
```text
16
4
-4
2147483644
3
```

<div style="page-break-after: always;"></div>

### 3.4 `instanceof`

It tests the **actual object type**, not the declared type of the reference. The right operand must be a **type name** the compiler can resolve — never a variable or a `Class` object.

| Valid ✅ | Invalid ❌ |
|:--|:--|
| `obj instanceof String` | `obj instanceof type` *(a variable)* |
| `obj instanceof java.lang.String` | `obj instanceof String.class` |

#### Example (`InstanceofDemo.java`)

```java
public class InstanceofDemo {
    public static void main(String[] args) {
        Object obj = "hello";                       // declared type Object, actual type String

        System.out.println(obj instanceof String);             // true
        System.out.println(obj instanceof java.lang.String);   // true
        System.out.println(obj instanceof Integer);            // false
        System.out.println(obj instanceof Object);             // true

        // String type = "String";
        // System.out.println(obj instanceof type);        // compile error: variable
        // System.out.println(obj instanceof String.class);// compile error: not a type name
    }
}
```

**Output**
```text
true
true
false
true
```

---

### 3.5 Short-Circuit vs Non-Short-Circuit Operators

| Operator | Kind | Behaviour |
|:--:|:--|:--|
| `&&` | Short-circuit AND | Right operand **skipped** if left is `false` |
| `\|\|` | Short-circuit OR | Right operand **skipped** if left is `true` |
| `&`  `\|` | Non-short-circuit | **Both** operands always evaluated |

#### Example (`ShortCircuitDemo.java`)

```java
public class ShortCircuitDemo {
    static boolean check(String name, boolean result) {
        System.out.println("check " + name);
        return result;
    }

    public static void main(String[] args) {
        boolean r1 = check("A", false) && check("B", true);   // B skipped
        System.out.println("r1 = " + r1);

        boolean r2 = check("C", true) || check("D", true);    // D skipped
        System.out.println("r2 = " + r2);

        boolean r3 = check("E", false) & check("F", true);    // both run
        System.out.println("r3 = " + r3);
    }
}
```

**Output**
```text
check A
r1 = false
check C
r2 = true
check E
check F
r3 = false
```

<div style="page-break-after: always;"></div>

### 3.6 Three Key Concepts: Expression, L-value, Side Effect

| Concept | Definition | Value & Type? | Storage? | Assignable? |
|:--|:--|:--:|:--:|:--:|
| **Pure expression** | e.g. `2 + 3` (literals are expressions too) | ✅ (`int`) | ❌ | ❌ |
| **L-value** | Refers to **storage**: a variable or an array element such as `arr[3]` | ✅ | ✅ | ✅ |
| **Side effect** | A change to a stored value made during evaluation, e.g. `x = 3` or `++i` | — | — | — |

- The **L** in *L-value* stands for the **left** side of an assignment.
- A side effect **requires an L-value** — only something with storage can be modified.

#### Example (`ExpressionDemo.java`)

```java
public class ExpressionDemo {
    public static void main(String[] args) {
        int[] arr = new int[5];
        int x;

        int pure = 2 + 3;   // pure expression: value and type, no storage
        // 2 + 3 = 10;      // compile error: not an L-value

        arr[3] = 42;        // array element is an L-value
        x = 3;              // variable is an L-value

        System.out.println(pure);    // 5
        System.out.println(arr[3]);  // 42
        System.out.println(x);       // 3
    }
}
```

**Output**
```text
5
42
3
```

---

### 3.7 Prefix and Postfix Increment

```text
   ++i   ──►  increment, THEN use value         (value = new)
   i++   ──►  use value, THEN increment         (value = old)
```

#### Example (`PrefixPostfixDemo.java`)

```java
public class PrefixPostfixDemo {
    public static void main(String[] args) {
        int i = 99;
        System.out.println(++i);   // 100  (increment, then use)
        System.out.println(i);     // 100

        int j = 99;
        System.out.println(j++);   // 99   (use, then increment)
        System.out.println(j);     // 100
    }
}
```

**Output**
```text
100
100
99
100
```

<div style="page-break-after: always;"></div>

### 3.8 `++i` Is Not an L-value

`++(++i)` does **not compile**: `++i` is an expression, but it has no storage to modify. Writing the increments as separate statements is legal because `i` itself is the L-value each time.

#### Example (`LValueDemo.java`)

```java
public class LValueDemo {
    public static void main(String[] args) {
        int i = 99;
        // System.out.println(++(++i));  // compile error: ++i is not an L-value
        ++i;
        ++i;                              // legal: i is the L-value each time
        System.out.println(i);            // 101
    }
}
```

**Output**
```text
101
```

---

### 3.9 Order of Evaluation of Operands

> **Operands are evaluated left to right — *before* the operation is applied. Side effects occur during operand evaluation.**

| Step (for `100 / --x`) | Action |
|:--:|:--|
| 1 | Evaluate left operand → `100` |
| 2 | Evaluate right operand `--x` → `x` drops from 1 to **0**; the operand's value is **0** |
| 3 | Apply `/` → **division by zero** → `ArithmeticException` |
| 4 | `catch` prints `bang`, then `x`, which is **0** because the decrement already happened |

#### Example (`EvaluationOrderDemo.java`)

```java
public class EvaluationOrderDemo {
    public static void main(String[] args) {
        int x = 1;
        try {
            int r = 100 / --x;             // left: 100, right: --x -> 0
            System.out.println(r);
        } catch (ArithmeticException e) {
            System.out.println("bang");    // bang
            System.out.println(x);         // 0
        }

        int a = 1;
        int b = a + a++ * 2 + a;           // 1 + (1 * 2) + 2
        System.out.println(b);             // 5
    }
}
```

**Output**
```text
bang
0
5
```

---

### 3.10 Key Takeaways

| # | Takeaway |
|:--:|:--|
| ✅ | `* / %` bind tighter than `+ -`; otherwise precedence is seldom tested |
| ✅ | `~` inverts **bits**; `!` negates **booleans** only |
| ✅ | `<<` doubles, `>>` halves (keeps sign), `>>>` **drops the sign** |
| ✅ | `instanceof` checks the **runtime object**, and needs a **type name** on the right |
| ✅ | `&&` and `\|\|` **short-circuit**; `&` and `\|` do not |
| ✅ | A side effect needs an **L-value**; `++i` is **not** an L-value |
| ✅ | Prefix returns the **new** value, postfix the **old** value |
| ✅ | Operands are evaluated **left to right**, with side effects applied as they go |

<div align="center">

<sub>Java SE 21 Developer (1Z0-830) · Chapter 2 · Topic 3 of the study guide</sub>

</div>

---
