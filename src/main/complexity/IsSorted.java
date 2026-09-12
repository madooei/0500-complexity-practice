package complexity;

/** Solution to the is-it-sorted practice problem. */
public final class IsSorted {

  private IsSorted() {
    // This class should not be instantiated!
  }

  // Assumes arr is not null. Returns true if arr is in non-decreasing order,
  // so equal neighbors are fine.
  public static boolean isSorted(int[] arr) {
    for (int i = 1; i < arr.length; i++) {
      if (arr[i - 1] > arr[i]) {
        return false;
      }
    }
    return true;
  }
}
