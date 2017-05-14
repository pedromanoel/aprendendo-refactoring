package refactoring.capitulo1;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.Test;

public class PriceTest {

    @Test
    public void regularAndChildrensPricesAlwaysReturnOneFrequentRenterPoint() {
        Stream.of(new ChildrensPrice(), new RegularPrice())
                .flatMapToInt(price -> IntStream.range(0, 10).map(price::getFrequentRenterPoints))
                .forEach(points -> assertThat(points, is(1)));
    }

    @Test
    public void newReleaseReturnsOnePointBeforeTwoDaysRent() {
        IntStream.range(0, 2)
                .map(daysRented -> new NewReleasePrice().getFrequentRenterPoints(daysRented))
                .forEach(points -> assertThat(points, is(1)));
    }

    @Test
    public void newReleaseReturnsTwoPointAfterTwoDaysRent() {
        IntStream.range(2, 10)
                .map(daysRented -> new NewReleasePrice().getFrequentRenterPoints(daysRented))
                .forEach(points -> assertThat(points, is(2)));
    }
}