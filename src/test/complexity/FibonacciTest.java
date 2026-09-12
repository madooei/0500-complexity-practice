package complexity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * Tests for the memoized Fibonacci. The large inputs would take far too long
 * with the chapter's plain recursive version, so passing them quickly is the
 * evidence that the cache is doing its job.
 */
public class FibonacciTest {

  @Test
  public void baseCases() {
    assertEquals(0, Fibonacci.fib(0));
    assertEquals(1, Fibonacci.fib(1));
  }

  @Test
  public void smallValues() {
    assertEquals(1, Fibonacci.fib(2));
    assertEquals(2, Fibonacci.fib(3));
    assertEquals(5, Fibonacci.fib(5));
    assertEquals(55, Fibonacci.fib(10));
  }

  @Test
  public void largeValues() {
    // fib(46) is the largest that fits in an int; fib(47) overflows
    assertEquals(832040, Fibonacci.fib(30));
    assertEquals(1836311903, Fibonacci.fib(46));
  }
}
