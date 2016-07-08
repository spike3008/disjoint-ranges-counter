import Range
import spock.lang.Specification
import spock.lang.Unroll

class RangeTest extends Specification {
    def "should throw when overlaps called with null parameter"() {
        when:
        new Range(1, 3).overlaps(null)
        then:
        thrown IllegalArgumentException
    }

    def "should overlap itself"() {
        given:
        def range = new Range(10, 20)
        expect:
        range.overlaps(range)
    }

    @Unroll
    def "should overlap [#range1; #range2]"() {
        expect:
        range1.overlaps(range2)
        range2.overlaps(range1)
        where:
        range1              | range2
        new Range(1, 10)    | new Range(2, 11)
        new Range(100, 200) | new Range(150, 200)
        new Range(10, 20)   | new Range(20, 21)
        new Range(1, 10)    | new Range(6, 7)
    }

    @Unroll
    def "should not overlap [#range1; #range2]"() {
        expect:
        !range1.overlaps(range2)
        !range2.overlaps(range1)
        where:
        range1              | range2
        new Range(1, 10)    | new Range(20, 30)
        new Range(100, 200) | new Range(201, 300)
    }
}
