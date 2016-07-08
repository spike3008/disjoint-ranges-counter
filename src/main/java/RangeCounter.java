import lombok.NonNull;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

class RangeCounter {

  /**
   * Method counts the number of disjoint ranges in given list
   *
   * @param ranges List of ranges, it doesn't have to be sorted
   * @return the int - number of disjoint ranges
   */
  public static int getNumberOfDisjointRanges(@NonNull List<Range> ranges) {
    if (ranges == null) {
      throw new IllegalStateException("List of ranges cannot be null");
    }

    Comparator<Range> maxValueComparator = (o1, o2) -> o1.getMax() - o2.getMax();
    List<Range> list = ranges.stream()
            .sorted(maxValueComparator)
            .collect(Collectors.toList());

    Optional<Range> previous = Optional.empty();
    int counter = 0;
    for (Range current : list) {
      if (previous.isPresent()) {
        if (!current.overlaps(previous.get())) {
          counter++;
        }
      } else {
        counter++;
      }
      previous = Optional.ofNullable(current);
    }

    return counter;
  }
}
