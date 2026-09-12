package complexity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * Tests for the shrink-at-quarter version. The alternating sequence that broke
 * the half rule no longer resizes, and a shrink only fires after enough
 * removes to bring the array down to a quarter full.
 */
public class DynamicArrayShrinkAtQuarterTest {

  private static DynamicArrayShrinkAtQuarter filled(int count) {
    DynamicArrayShrinkAtQuarter a = new DynamicArrayShrinkAtQuarter();
    for (int i = 0; i < count; i++) {
      a.add(i);
    }
    return a;
  }

  @Test
  public void removeLastReturnsLastAndShrinksSize() {
    DynamicArrayShrinkAtQuarter a = filled(3);
    assertEquals(2, a.removeLast());
    assertEquals(2, a.size());
    assertEquals(1, a.get(1));
  }

  @Test
  public void removeLastOnEmptyThrows() {
    DynamicArrayShrinkAtQuarter a = new DynamicArrayShrinkAtQuarter();
    assertThrows(IllegalStateException.class, a::removeLast);
  }

  @Test
  public void alternatingCallsDoNotResize() {
    DynamicArrayShrinkAtQuarter a = filled(11);  // grew to 20 on the 11th add
    assertEquals(20, a.capacity());
    for (int i = 0; i < 5; i++) {
      a.removeLast();
      assertEquals(20, a.capacity());
      a.add(99);
      assertEquals(20, a.capacity());
    }
  }

  @Test
  public void shrinksAtQuarterFullAndLandsHalfFull() {
    DynamicArrayShrinkAtQuarter a = filled(11);  // size 11, capacity 20
    while (a.size() > 6) {
      a.removeLast();
    }
    assertEquals(20, a.capacity());              // size 6 is above a quarter
    a.removeLast();                              // size 5 == 20 / 4
    assertEquals(10, a.capacity());
    assertEquals(5, a.size());
    for (int i = 0; i < 5; i++) {
      assertEquals(i, a.get(i));                 // elements survived the copy
    }
  }

  @Test
  public void neverShrinksBelowStartingCapacity() {
    DynamicArrayShrinkAtQuarter a = filled(11);
    while (a.size() > 0) {
      a.removeLast();
    }
    assertEquals(10, a.capacity());
  }
}
