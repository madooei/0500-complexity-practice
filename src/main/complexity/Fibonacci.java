package complexity;

/** Solution to the memoized Fibonacci practice problem. */
public final class Fibonacci {

  private Fibonacci() {
    // This class should not be instantiated!
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

  // Assumes n >= 0.
  public static int fib(int n) {
    int[] memo = new int[n + 1];
    return fib(n, memo);
  }
}
