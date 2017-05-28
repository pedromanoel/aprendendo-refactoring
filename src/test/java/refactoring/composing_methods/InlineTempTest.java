package refactoring.composing_methods;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class InlineTempTest {

    @Test
    public void returnFalseWhenLessThan1000() {
        Order order = new Order(10.0, 999);
        assertThat(new InlineTemp(order).basePriceLargerThanThousand(), is(false));
    }

    @Test
    public void returnFalseWhenEqualTo1000() {
        Order order = new Order(10.0, 1000);
        assertThat(new InlineTemp(order).basePriceLargerThanThousand(), is(false));
    }

    @Test
    public void returnTrueWhenLargerThan1000() {
        Order order = new Order(10.0, 1001);
        assertThat(new InlineTemp(order).basePriceLargerThanThousand(), is(true));
    }
}