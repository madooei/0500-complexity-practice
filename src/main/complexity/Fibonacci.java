package complexity;

/** Solution to the memoized Fibonacci practice problem. */
public final class Fibonacci {

  private Fibonacci() {
    // This class should not be instantiated!
  }

  // A slot holding 0 means "not computed yet". That is safe here because
  // every cached value is F(n) for n >= 2, and those are all at least 1.
  private static int fib(int n, int[] memo) {
    // TODO: Implement me
    throw new UnsupportedOperationException("TODO: Implement me");
  }

  // Assumes n >= 0.
  public static int fib(int n) {
    // TODO: Implement me
    throw new UnsupportedOperationException("TODO: Implement me");
  }
}
