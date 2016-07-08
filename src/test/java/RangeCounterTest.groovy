import Range
import spock.lang.Specification
import spock.lang.Unroll

class RangeCounterTest extends Specification {
    def "should throw IllegalArgumentException when null passed"() {
        when:
        RangeCounter.getNumberOfDisjointRanges(null)
        then:
        thrown IllegalStateException
    }

    def "should return 0 for empty list"() {
        expect:
        RangeCounter.getNumberOfDisjointRanges(Collections.emptyList()) == 0
    }

    def "should return 1 for one element list"() {
        given:
        def range = new Range(1, 2)
        expect:
        RangeCounter.getNumberOfDisjointRanges(Arrays.asList(range)) == 1
    }

    @Unroll
    def "should return #expectedResult for ranges [#range1; #range2; #range3]"() {
        given:
        def ranges = Arrays.asList(range1, range2, range3)
        when:
        def disjointRanges = RangeCounter.getNumberOfDisjointRanges(ranges)
        then:
        disjointRanges == expectedResult
        where:
        range1            | range2          | range3           || expectedResult
        new Range(2, 3)   | new Range(5, 7) | new Range(1, 6)  || 1
        new Range(3, 7)   | new Range(7, 8) | new Range(8, 12) || 1
        new Range(1, 3)   | new Range(2, 7) | new Range(8, 9)  || 2
        new Range(10, 12) | new Range(1, 3) | new Range(5, 12) || 2
        new Range(1, 3)   | new Range(5, 7) | new Range(8, 9)  || 3
    }
}
