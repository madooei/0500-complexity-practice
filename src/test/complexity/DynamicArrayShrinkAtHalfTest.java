package complexity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

/**
 * Tests for the shrink-at-half version. The last test is the bad sequence
 * from the notes: alternating add and removeLast at the boundary resizes the
 * array on every single call.
 */
public class DynamicArrayShrinkAtHalfTest {

  private static DynamicArrayShrinkAtHalf filled(int count) {
    DynamicArrayShrinkAtHalf array = new DynamicArrayShrinkAtHalf();
    for (int i = 0; i < count; i++) {
      array.add(i);
    }
    return array;
  }

  @Test
  public void removeLastReturnsLastAndShrinksSize() {
    DynamicArrayShrinkAtHalf array = filled(3);
    assertEquals(2, array.removeLast());
    assertEquals(2, array.size());
    assertEquals(1, array.get(1));
  }

  @Test
  public void removeLastOnEmptyThrows() {
    DynamicArrayShrinkAtHalf array = new DynamicArrayShrinkAtHalf();
    try {
      array.removeLast();
      fail("Failed to throw IllegalStateException");
    } catch (IllegalStateException e) {
      return;
    }
  }

  @Test
  public void neverShrinksBelowStartingCapacity() {
    DynamicArrayShrinkAtHalf array = filled(10);
    while (array.size() > 0) {
      array.removeLast();
    }
    assertEquals(10, array.capacity());
  }

  @Test
  public void alternatingCallsResizeEveryTime() {
    DynamicArrayShrinkAtHalf array = filled(10);  // full at capacity 10
    array.add(10);                                // grows to 20, half full
    assertEquals(20, array.capacity());
    array.removeLast();                           // half empty: shrinks to 10
    assertEquals(10, array.capacity());
    array.add(10);                                // full again: grows to 20
    assertEquals(20, array.capacity());
    array.removeLast();                           // and shrinks again
    assertEquals(10, array.capacity());
  }
}
