# Complexity Analysis — Practice: Is Sorted, Memoized Fibonacci, and a Shrinking DynamicArray

The three practice problems: a check for whether an array is sorted, a
recursive Fibonacci that caches its results, and a `DynamicArray` that shrinks
on `removeLast`, in the naive version and the fixed one. Each comes with a
JUnit suite that checks it on the inputs the practice pages discuss.

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
        IsSorted.java                     # is the array sorted? early return on a descent
        Fibonacci.java                    # recursive fib with an array cache
        DynamicArrayShrinkAtHalf.java     # shrinks when half empty (O(n) amortized)
        DynamicArrayShrinkAtQuarter.java  # shrinks when a quarter full (O(1) amortized)
    test/
      complexity/
        IsSortedTest.java
        FibonacciTest.java
        DynamicArrayShrinkAtHalfTest.java
        DynamicArrayShrinkAtQuarterTest.java
  scripts/
    test.sh                               # compile everything and run the JUnit tests
```

## How to compile and run

- `scripts/test.sh` — compiles everything and runs the full JUnit suite.
- `scripts/test.sh complexity.IsSortedTest` — compiles everything and runs one
  test class only. Use this while you are working on one problem and the
  others are still empty. The class names are listed in the layout above.

## What's here

- `complexity.IsSorted` — `isSorted`, which walks the adjacent pairs and
  returns at the first descent: best case O(1), worst case O(n).
- `complexity.Fibonacci` — `fib`, top-down memoized with an `int[]` cache and a
  private helper: O(n) time, O(n) auxiliary space.
- `complexity.DynamicArrayShrinkAtHalf` — `removeLast` that halves the capacity
  as soon as the array is half empty. Alternating `add` and `removeLast` at the
  boundary resizes on every call, so both are O(n) amortized.
- `complexity.DynamicArrayShrinkAtQuarter` — `removeLast` that halves the
  capacity only when the array is a quarter full. Every resize leaves the array
  half full, so `add` and `removeLast` are O(1) amortized. Both classes expose
  `capacity()` so the tests can watch the resizes.
