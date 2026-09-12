package complexity;

/** A growable array of ints that also shrinks. */
public class DynamicArray {

  private static final int MIN_CAPACITY = 10;
  private static final int GROWTH_FACTOR = 2;  // grow multiplies capacity by this
  private static final int SHRINK_FACTOR = 4;  // shrink when size is capacity / this

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

  // Removes and returns the last element. Throws if the array is empty.
  public int removeLast() {
    if (size == 0) {
      throw new IllegalStateException();
    }
    size--;
    int value = arr[size];
    arr[size] = 0;           // clear the now-unused slot
    if (size == arr.length / SHRINK_FACTOR && arr.length > MIN_CAPACITY) {
      shrink();
    }
    return value;
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
    int[] smaller = new int[arr.length / GROWTH_FACTOR];
    for (int i = 0; i < size; i++) {
      smaller[i] = arr[i];
    }
    arr = smaller;
  }
}
