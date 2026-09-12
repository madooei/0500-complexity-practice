package complexity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * Tests for the shrink-at-half version. The last test is the bad sequence
 * from the notes: alternating add and removeLast at the boundary resizes the
 * array on every single call.
 */
public class DynamicArrayShrinkAtHalfTest {

  private static DynamicArrayShrinkAtHalf filled(int count) {
    DynamicArrayShrinkAtHalf a = new DynamicArrayShrinkAtHalf();
    for (int i = 0; i < count; i++) {
      a.add(i);
    }
    return a;
  }

  @Test
  public void removeLastReturnsLastAndShrinksSize() {
    DynamicArrayShrinkAtHalf a = filled(3);
    assertEquals(2, a.removeLast());
    assertEquals(2, a.size());
    assertEquals(1, a.get(1));
  }

  @Test
  public void removeLastOnEmptyThrows() {
    DynamicArrayShrinkAtHalf a = new DynamicArrayShrinkAtHalf();
    assertThrows(IllegalStateException.class, a::removeLast);
  }

  @Test
  public void neverShrinksBelowStartingCapacity() {
    DynamicArrayShrinkAtHalf a = filled(10);
    while (a.size() > 0) {
      a.removeLast();
    }
    assertEquals(10, a.capacity());
  }

  @Test
  public void alternatingCallsResizeEveryTime() {
    DynamicArrayShrinkAtHalf a = filled(10);   // full at capacity 10
    a.add(10);                                 // grows to 20, half full
    assertEquals(20, a.capacity());
    a.removeLast();                            // half empty: shrinks to 10
    assertEquals(10, a.capacity());
    a.add(10);                                 // full again: grows to 20
    assertEquals(20, a.capacity());
    a.removeLast();                            // and shrinks again
    assertEquals(10, a.capacity());
  }
}
