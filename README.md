# Complexity Analysis — Practice: Memoized Fibonacci and a Shrinking DynamicArray

The two practice problems: a recursive Fibonacci that caches its results, and
a `DynamicArray` that shrinks on `removeLast`. Each comes with a JUnit suite
that checks it on the inputs the practice pages discuss.

## Prerequisites

- JDK 17+ (JUnit 6 requires it).
- The JUnit jar is already vendored in `lib/`; there is nothing to download.

## Repository layout

```plaintext
code/
  README.md
  .gitignore
  lib/
    junit-platform-console-standalone-6.1.0.jar
  src/
    main/
      complexity/
        Fibonacci.java        # recursive fib with an array cache
        DynamicArray.java     # grows on add, shrinks on removeLast
    test/
      complexity/
        FibonacciTest.java
        DynamicArrayTest.java
  scripts/
    test.sh                   # compile everything and run the JUnit tests
```

## How to compile and run

- `scripts/test.sh` — compiles everything and runs the full JUnit suite.
- `scripts/test.sh complexity.FibonacciTest` — compiles everything and runs one
  test class only. Use this while you are working on one problem and the
  other is still empty. The class names are listed in the layout above.

## What's here

- `complexity.Fibonacci` — `fib`, top-down memoized with an `int[]` cache and a
  private helper.
- `complexity.DynamicArray` — the `DynamicArray` from the first chapter with a
  `removeLast` that gives capacity back. `grow` and `shrink` both use
  `RESIZE_FACTOR`; `shrink` fires only when the array is one `SHRINK_THRESHOLD`-th
  full, so every resize leaves it half full.
