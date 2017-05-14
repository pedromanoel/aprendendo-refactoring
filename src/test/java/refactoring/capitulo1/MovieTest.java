package refactoring.capitulo1;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.HashMap;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Test;

public class MovieTest {

    private Movie regular;
    private Movie newRelease;
    private Movie children;

    @Before
    public void setUp() throws Exception {
        regular = new Movie("Robocop 1", Movie.REGULAR);
        newRelease = new Movie("Moana", Movie.NEW_RELEASE);
        children = new Movie("Matilda", Movie.CHILDREN);
    }

    @Test
    public void regularAndChildrensMoviesAlwaysReturnOneFrequentRenterPoint() {
        Stream.of(children, regular)
                .flatMapToInt(movie -> IntStream.range(0, 10).map(movie::getFrequentRenterPoints))
                .forEach(points -> assertThat(points, is(1)));
    }

    @Test
    public void newReleaseReturnsOnePointBeforeTwoDaysRent() {
        IntStream.range(0, 2)
                .map(daysRented -> newRelease.getFrequentRenterPoints(daysRented))
                .forEach(points -> assertThat(points, is(1)));
    }

    @Test
    public void newReleaseReturnsTwoPointAfterTwoDaysRent() {
        IntStream.range(2, 10)
                .map(daysRented -> newRelease.getFrequentRenterPoints(daysRented))
                .forEach(points -> assertThat(points, is(2)));
    }


    @Test
    public void testRegularRentalCostsTwoBeforeTwoDays() {
        Stream.of(0, 1, 2).forEach(daysOfRent
                -> assertThat(regular.getCharge(daysOfRent), is(2.0)));
    }

    @Test
    public void testRegularRentalCostsExtraOneFiveForEachDayAfterTwo() {
        new HashMap<Integer, Double>() {
            {
                put(2, 2.0);
                put(3, 3.5);
                put(4, 5.0);
                put(5, 6.5);
            }
        }.forEach((daysOfRent, expectedRentalCost)
                -> assertThat(regular.getCharge(daysOfRent), is(expectedRentalCost)));
    }

    @Test
    public void testNewReleaseRentalCostsThreeForEachDayRented() {
        Stream.of(0, 1, 2, 3).forEach(daysOfRent -> {
            assertThat(newRelease.getCharge(daysOfRent), is(daysOfRent * 3.0));
        });
    }

    @Test
    public void testChildrenRentalCostsOneFiveBeforeThreeDays() {
        Stream.of(0, 1, 2, 3).forEach(daysOfRent -> {
            assertThat(children.getCharge(daysOfRent), is(1.5));
        });
    }

    @Test
    public void testChildrenRentalCostsExtraOneFiveAfterThreeDays() {
        new HashMap<Integer, Double>() {
            {
                put(3, 1.5);
                put(4, 3.0);
                put(5, 4.5);
                put(6, 6.0);
            }
        }.forEach((daysOfRent, expectedRentalCost) -> {
            assertThat(children.getCharge(daysOfRent), is(expectedRentalCost));
        });
    }

}