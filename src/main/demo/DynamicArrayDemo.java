package demo;

import complexity.DynamicArray;

/** A demo of DynamicArray: fill it past its starting capacity, then remove from the end. */
public final class DynamicArrayDemo {

  private DynamicArrayDemo() {
    // This class should not be instantiated!
  }

  public static void main(String[] args) {
    DynamicArray numbers = new DynamicArray();

    // The starting capacity is 8, so the 9th add grows the array to 16 slots.
    for (int i = 1; i <= 9; i++) {
      numbers.add(i * 10);
    }
    System.out.println("size after 9 adds: " + numbers.size());

    // Each removeLast returns the last value: 90, 80, 70, 60, 50.
    // The 5th remove leaves 4 elements, a quarter of 16, so it shrinks the array to 8 slots.
    for (int i = 0; i < 5; i++) {
      System.out.println("removeLast: " + numbers.removeLast());
    }
    System.out.println("size: " + numbers.size());

    // The remaining values are still in place: 10 20 30 40.
    for (int i = 0; i < numbers.size(); i++) {
      System.out.print(numbers.get(i) + " ");
    }
    System.out.println();
  }
}
