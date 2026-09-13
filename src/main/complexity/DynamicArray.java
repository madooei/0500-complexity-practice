package complexity;

/** A growable array of ints that also shrinks. */
public class DynamicArray {

  private static final int MIN_CAPACITY = 10;
  // grow multiplies the capacity by this, and shrink divides it by this
  private static final int GROWTH_FACTOR = 2;
  // shrink fires when size falls to capacity / SHRINK_FACTOR
  private static final int SHRINK_FACTOR = 4;

  private int[] arr;
  private int size;

  public DynamicArray() {
    arr = new int[MIN_CAPACITY];
    size = 0;
  }

  public void add(int value) {
    if (size == arr.length) {
      grow();
    }
    arr[size] = value;
    size++;
  }

  public int get(int index) {
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException();
    }
    return arr[index];
  }

  public int size() {
    return size;
  }

  // The length of the backing array. Exposed so we can watch grow and shrink.
  public int capacity() {
    return arr.length;
  }

  // Removes and returns the last element.
  // Throws IllegalStateException if the array is empty.
  public int removeLast() {
    // TODO: Implement me
    throw new UnsupportedOperationException("TODO: Implement me");
  }

  // Multiplies the capacity by GROWTH_FACTOR.
  private void grow() {
    int[] bigger = new int[arr.length * GROWTH_FACTOR];
    for (int i = 0; i < size; i++) {
      bigger[i] = arr[i];
    }
    arr = bigger;
  }

  // Divides the capacity by GROWTH_FACTOR.
  private void shrink() {
    // TODO: Implement me
    throw new UnsupportedOperationException("TODO: Implement me");
  }
}
