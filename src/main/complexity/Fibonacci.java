package complexity;

/** Solution to the memoized Fibonacci practice problem. */
public final class Fibonacci {

  private Fibonacci() {
    // This class should not be instantiated!
  }

  // Assumes n >= 0. Keeps the recursion but caches each result in an array,
  // so every Fibonacci number is computed at most once: O(n) time, O(n)
  // auxiliary space (the cache plus the call stack).
  public static int fib(int n) {
    int[] memo = new int[n + 1];
    return fib(n, memo);
  }

  // A slot holding 0 means "not computed yet". That is safe here because
  // every cached value is F(n) for n >= 2, and those are all at least 1.
  private static int fib(int n, int[] memo) {
    if (n <= 1) {
      return n;
    }
    if (memo[n] != 0) {
      return memo[n];
    }
    memo[n] = fib(n - 1, memo) + fib(n - 2, memo);
    return memo[n];
  }
}
