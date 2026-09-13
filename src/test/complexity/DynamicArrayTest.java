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

  // The three constants the class is built on, read through reflection so
  // the expected numbers below follow the class if a constant changes.
  private static final int MIN_CAPACITY = readStaticInt("MIN_CAPACITY");
  private static final int RESIZE_FACTOR = readStaticInt("RESIZE_FACTOR");
  private static final int SHRINK_THRESHOLD = readStaticInt("SHRINK_THRESHOLD");

  // The capacity right after the first grow.
  private static final int GROWN = MIN_CAPACITY * RESIZE_FACTOR;

  private static DynamicArray filled(int count) {
    DynamicArray array = new DynamicArray();
    for (int i = 0; i < count; i++) {
      array.add(i);
    }
    return array;
  }

  private static int readStaticInt(String name) {
    try {
      Field field = DynamicArray.class.getDeclaredField(name);
      field.setAccessible(true);
      return field.getInt(null);
    } catch (ReflectiveOperationException e) {
      throw new IllegalStateException("DynamicArray has no constant " + name);
    }
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
    DynamicArray array = filled(MIN_CAPACITY + 1);  // one add past full: grew
    assertEquals(GROWN, capacity(array));
    for (int i = 0; i < 5; i++) {
      array.removeLast();
      assertEquals(GROWN, capacity(array));
      array.add(99);
      assertEquals(GROWN, capacity(array));
    }
  }

  @Test
  public void shrinksAtQuarterFullAndLandsHalfFull() {
    DynamicArray array = filled(MIN_CAPACITY + 1);
    int threshold = GROWN / SHRINK_THRESHOLD;
    while (array.size() > threshold + 1) {
      array.removeLast();
    }
    assertEquals(GROWN, capacity(array));  // one above the threshold: no shrink
    array.removeLast();                    // now exactly at the threshold
    assertEquals(GROWN / RESIZE_FACTOR, capacity(array));
    assertEquals(threshold, array.size());
    for (int i = 0; i < threshold; i++) {
      assertEquals(i, array.get(i));       // elements survived the copy
    }
  }

  @Test
  public void neverShrinksBelowStartingCapacity() {
    DynamicArray array = filled(MIN_CAPACITY + 1);
    while (array.size() > 0) {
      array.removeLast();
    }
    assertEquals(MIN_CAPACITY, capacity(array));
  }
}
