package complexity;

/**
 * A growable array of ints that also shrinks, using the naive rule: halve the
 * capacity as soon as the array is half empty. This rule lets alternating add
 * and removeLast calls resize on every call, so both are O(n) amortized.
 */
public class DynamicArrayShrinkAtHalf {

  private static final int MIN_CAPACITY = 10;

  private int[] arr;
  private int size;

  public DynamicArrayShrinkAtHalf() {
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
  // Shrinks as soon as the array is half empty.
  public int removeLast() {
    if (size == 0) {
      throw new IllegalStateException();
    }
    size--;
    int value = arr[size];
    arr[size] = 0;           // clear the now-unused slot
    if (size == arr.length / 2 && arr.length > MIN_CAPACITY) {
      shrink();
    }
    return value;
  }

  // Doubles the capacity.
  private void grow() {
    int[] bigger = new int[arr.length * 2];
    for (int i = 0; i < size; i++) {
      bigger[i] = arr[i];
    }
    arr = bigger;
  }

  // Halves the capacity.
  private void shrink() {
    int[] smaller = new int[arr.length / 2];
    for (int i = 0; i < size; i++) {
      smaller[i] = arr[i];
    }
    arr = smaller;
  }
}
