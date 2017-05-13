package refactoring.capitulo1;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.util.HashMap;
import java.util.stream.Stream;

import org.hamcrest.BaseMatcher;
import org.hamcrest.CustomMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Test;

public class CustomerTest {

    private Customer customer;
    private Movie regular;
    private Movie newRelease;
    private Movie children;

    @Before
    public void setUp() throws Exception {
        customer = new Customer("Pedro");
        regular = new Movie("Robocop 1", Movie.REGULAR);
        newRelease = new Movie("Moana", Movie.NEW_RELEASE);
        children = new Movie("Matilda", Movie.CHILDREN);
    }

    @Test
    public void testStatementWhenRentalsIsEmpty() {
        assertThat(customer.statement(), is(
                "refactoring.capitulo1.Rental record for Pedro\n" +
                        "Amount owed is 0.0\n" +
                        "You earned 0 frequent renter points"));
    }

    @Test
    public void testRegularRentalCostsTwoBeforeTwoDays() {
        Stream.of(0, 1, 2).forEach(daysOfRent -> {
            Customer customer = new Customer("Pedro");
            customer.addRental(new Rental(regular, daysOfRent));

            assertThat(customer.statement(), hasRentalCostsOf(2.0));
            assertThat(customer.statement(), hasMovieWithTitle(regular.getTitle()));
        });
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
        }.forEach((daysOfRent, expectedRentalCost) -> {
            Customer customer = new Customer("Pedro");
            customer.addRental(new Rental(regular, daysOfRent));

            assertThat(customer.statement(), hasMovieWithTitle(regular.getTitle()));
            assertThat(customer.statement(), hasRentalCostsOf(expectedRentalCost));
        });
    }

    @Test
    public void testNewReleaseRentalCostsThreeForEachDayRented() {
        Stream.of(0, 1, 2, 3).forEach(daysOfRent -> {
            Customer customer = new Customer("Pedro");
            customer.addRental(new Rental(newRelease, daysOfRent));

            assertThat(customer.statement(), hasRentalCostsOf(daysOfRent * 3));
        });
    }

    @Test
    public void testChildrenRentalCostsOneFiveBeforeThreeDays() {
        Stream.of(0, 1, 2, 3).forEach(daysOfRent -> {
            Customer customer = new Customer("Pedro");
            customer.addRental(new Rental(children, daysOfRent));

            assertThat(customer.statement(), hasRentalCostsOf(1.5));
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
            Customer customer = new Customer("Pedro");
            customer.addRental(new Rental(children, daysOfRent));

            assertThat(customer.statement(), hasRentalCostsOf(expectedRentalCost));
        });
    }

    @Test
    public void testRegularAndChildrenAccumulateOneFrequentRenterPoint() {
        Stream.of(regular, children).forEach(movie -> {
            Customer customer = new Customer("Pedro");
            customer.addRental(new Rental(movie, 10));

            assertThat(customer.statement(), hasFrequentRenterPointsOf(1));
        });
    }

    @Test
    public void testNewReleaseAccumulateTwoFrequentRenterPoint() {
        Customer customer = new Customer("Pedro");
        customer.addRental(new Rental(newRelease, 10));

        assertThat(customer.statement(), hasFrequentRenterPointsOf(2));
    }

    @Test
    public void testStatementWhenTwoMoviesAreRented() {
        customer.addRental(new Rental(regular, 2));
        customer.addRental(new Rental(children, 2));

        assertThat(customer.statement(), is(
                "refactoring.capitulo1.Rental record for Pedro\n" +
                        "\tRobocop 1\t2.0\n" +
                        "\tMatilda\t1.5\n" +
                        "Amount owed is 3.5\n" +
                        "You earned 2 frequent renter points"));
    }

    private Matcher<String> hasRentalCostsOf(double value) {
        return new BaseMatcher<String>() {
            @Override
            public boolean matches(Object item) {
                final String statement = (String) item;
                return statement.matches(String.format("(?s).*Amount owed is %.1f.*", value));
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("Amount owed should be ").appendValue(value);
            }
        };
    }

    private Matcher<String> hasFrequentRenterPointsOf(int value) {
        return new BaseMatcher<String>() {
            @Override
            public boolean matches(Object item) {
                final String statement = (String) item;

                String regex = String.format("(?s).*You earned %d frequent renter points", value);
                return statement.matches(regex);
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("Frequent renter points should be ").appendValue(value);
            }
        };
    }

    private Matcher<String> hasMovieWithTitle(String title) {

        return new CustomMatcher<String>(String.format("Title should be %s", title)) {
            @Override
            public boolean matches(Object item) {
                String statement = (String) item;
                return statement.contains(title);
            }
        };
    }
}