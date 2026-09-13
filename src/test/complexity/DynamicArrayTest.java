package complexity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.lang.reflect.Field;
import org.junit.jupiter.api.Test;

/**
 * Tests for the shrinking DynamicArray. The alternating sequence that broke
 * the shrink-at-half rule must not resize, and a shrink only fires after
 * enough removes to bring the array down to a quarter full.
 */
public class DynamicArrayTest {

  private static DynamicArray filled(int count) {
    DynamicArray array = new DynamicArray();
    for (int i = 0; i < count; i++) {
      array.add(i);
    }
    return array;
  }

  // Reads the length of the private backing array through reflection, so the
  // tests can watch grow and shrink without adding a public method for it.
  private static int capacity(DynamicArray array) {
    try {
      Field field = DynamicArray.class.getDeclaredField("arr");
      field.setAccessible(true);
      int[] arr = (int[]) field.get(array);
      return arr.length;
    } catch (ReflectiveOperationException e) {
      throw new IllegalStateException("DynamicArray has no field named arr");
    }
  }

  @Test
  public void removeLastReturnsLastAndShrinksSize() {
    DynamicArray array = filled(3);
    assertEquals(2, array.removeLast());
    assertEquals(2, array.size());
    assertEquals(1, array.get(1));
  }

  @Test
  public void removeLastOnEmptyThrows() {
    DynamicArray array = new DynamicArray();
    try {
      array.removeLast();
      fail("Failed to throw IllegalStateException");
    } catch (IllegalStateException e) {
      return;
    }
  }

  @Test
  public void alternatingCallsDoNotResize() {
    DynamicArray array = filled(11);  // grew to 20 on the 11th add
    assertEquals(20, capacity(array));
    for (int i = 0; i < 5; i++) {
      array.removeLast();
      assertEquals(20, capacity(array));
      array.add(99);
      assertEquals(20, capacity(array));
    }
  }

  @Test
  public void shrinksAtQuarterFullAndLandsHalfFull() {
    DynamicArray array = filled(11);  // size 11, capacity 20
    while (array.size() > 6) {
      array.removeLast();
    }
    assertEquals(20, capacity(array));  // size 6 is above a quarter
    array.removeLast();                  // size 5 == 20 / 4
    assertEquals(10, capacity(array));
    assertEquals(5, array.size());
    for (int i = 0; i < 5; i++) {
      assertEquals(i, array.get(i));     // elements survived the copy
    }
  }

  @Test
  public void neverShrinksBelowStartingCapacity() {
    DynamicArray array = filled(11);
    while (array.size() > 0) {
      array.removeLast();
    }
    assertEquals(10, capacity(array));
  }
}
