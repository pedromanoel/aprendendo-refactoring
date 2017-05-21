package refactoring.movie_rental;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

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
                "Rental record for Pedro\n" +
                        "Amount owed is 0.0\n" +
                        "You earned 0 frequent renter points"));
    }

    @Test
    public void testHtmlStatement() {
        customer.addRental(new Rental(regular, 2));
        assertThat(customer.htmlStatement(), is(
                "<h1>Rentals for <em>Pedro</em></h1><p>\n" +
                        "Robocop 1: 2.0<br>\n" +
                        "You owe <em>2.0</em><p>\n" +
                        "On this rental you earned <em>1</em> frequent renter points<p>"));
    }

    @Test
    public void testStatementWhenThreeAreRented() {
        customer.addRental(new Rental(regular, 2));
        customer.addRental(new Rental(children, 2));
        customer.addRental(new Rental(newRelease, 2));

        assertThat(customer.statement(), is(
                "Rental record for Pedro\n" +
                        "\tRobocop 1\t2.0\n" +
                        "\tMatilda\t1.5\n" +
                        "\tMoana\t6.0\n" +
                        "Amount owed is 9.5\n" +
                        "You earned 4 frequent renter points"));
    }
}