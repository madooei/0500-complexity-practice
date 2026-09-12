package complexity;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Tests for the is-it-sorted check. Each test is one scenario from the
 * notes, including the descent positions that exercise the early return:
 * at the first pair, in the middle, and at the very last pair.
 */
public class IsSortedTest {

  @Test
  public void emptyArray() {
    // no adjacent pairs, so it is sorted
    assertTrue(IsSorted.isSorted(new int[] {}));
  }

  @Test
  public void singleElement() {
    // one element has no pair to be out of order
    assertTrue(IsSorted.isSorted(new int[] {5}));
  }

  @Test
  public void equalNeighborsAllowed() {
    // non-decreasing permits equal adjacent values
    assertTrue(IsSorted.isSorted(new int[] {1, 2, 2, 5}));
  }

  @Test
  public void descentAtFirstPair() {
    // early return fires on the very first comparison
    assertFalse(IsSorted.isSorted(new int[] {5, 1, 3}));
  }

  @Test
  public void midArrayDescent() {
    // out-of-order pair sits in the middle
    assertFalse(IsSorted.isSorted(new int[] {1, 3, 2}));
  }

  @Test
  public void descentAtLastPair() {
    // only out-of-order pair is the final one
    assertFalse(IsSorted.isSorted(new int[] {1, 2, 3, 5, 4}));
  }

  @Test
  public void strictlyDecreasing() {
    assertFalse(IsSorted.isSorted(new int[] {3, 2, 1}));
  }

  @Test
  public void allEqual() {
    // every pair is equal, which is non-decreasing
    assertTrue(IsSorted.isSorted(new int[] {2, 2, 2}));
  }
}
