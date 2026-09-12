package complexity;

/** Solution to the is-it-sorted practice problem. */
public final class IsSorted {

  private IsSorted() {
    // This class should not be instantiated!
  }

  // Returns true if arr is sorted in non-decreasing order, so equal neighbors
  // are fine. Stops at the first out-of-order pair: best case O(1), worst
  // case O(n). Empty and single-element arrays count as sorted.
  public static boolean isSorted(int[] arr) {
    for (int i = 1; i < arr.length; i++) {
      if (arr[i - 1] > arr[i]) {
        return false;
      }
    }
    return true;
  }
}
