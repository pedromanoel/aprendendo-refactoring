package refactoring.composing_methods;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class InlineMethodTest {

    @Test
    public void getRatingReturnsOneWhenLateDeliveriesBelowFive() {
        assertThat(new InlineMethod(4).getRating(), is(1));
    }

    @Test
    public void getRatingReturnsOneWhenLateDeliveriesIsFive() {
        assertThat(new InlineMethod(5).getRating(), is(1));
    }

    @Test
    public void getRatingReturnsTwoWhenLateDeliveriesIsBiggerThanFive() {
        assertThat(new InlineMethod(6).getRating(), is(2));
    }

}