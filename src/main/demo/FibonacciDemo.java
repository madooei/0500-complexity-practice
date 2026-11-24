package demo;

import complexity.Fibonacci;

/** A demo of the memoized Fibonacci on the call the notes trace. */
public final class FibonacciDemo {

  private FibonacciDemo() {
    // This class should not be instantiated!
  }

  public static void main(String[] args) {
    // The sequence starts 0, 1, 1, 2, 3, 5, so F(5) is 5.
    System.out.println("fib(5) = " + Fibonacci.fib(5));

    // F(10) is 55.
    System.out.println("fib(10) = " + Fibonacci.fib(10));
  }
}
