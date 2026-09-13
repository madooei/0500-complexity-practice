package complexity;

/** A growable array of ints that also shrinks. */
public class DynamicArray {

  private static final int MIN_CAPACITY = 8;
  // grow multiplies the capacity by this, and shrink divides it by this
  private static final int RESIZE_FACTOR = 2;
  // shrink fires when size falls to capacity / SHRINK_THRESHOLD
  private static final int SHRINK_THRESHOLD = 4;

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

  // Removes and returns the last element.
  // Throws IllegalStateException if the array is empty.
  public int removeLast() {
    // TODO: Implement me
    throw new UnsupportedOperationException("TODO: Implement me");
  }

  // Multiplies the capacity by RESIZE_FACTOR.
  private void grow() {
    int[] bigger = new int[arr.length * RESIZE_FACTOR];
    for (int i = 0; i < size; i++) {
      bigger[i] = arr[i];
    }
    arr = bigger;
  }

  // Divides the capacity by RESIZE_FACTOR.
  private void shrink() {
    // TODO: Implement me
    throw new UnsupportedOperationException("TODO: Implement me");
  }
}
