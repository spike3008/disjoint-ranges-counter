import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

/**
 * The type Range.
 */
@AllArgsConstructor
@Getter
@ToString(includeFieldNames = false)
class Range {
  private int min;
  private int max;

  /**
   * Checks if the object overlaps the other one.
   *
   * @param other the second object to check
   * @return the boolean result
   */
  boolean overlaps(@NonNull Range other) {
    if (other == null) {
      throw new IllegalArgumentException("Other parameter cannot be null");
    }
    int spreadBetweenExtremePoints = Math.max(max, other.getMax()) - Math.min(min, other.getMin());
    return spreadBetweenExtremePoints <= (width() + other.width());
  }

  private int width() {
    return max - min;
  }
}
