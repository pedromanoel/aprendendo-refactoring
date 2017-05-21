package refactoring.composing_methods;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.io.ByteArrayOutputStream;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class ExtractMethodTest {

    @Test
    public void testPrintOwing() {
        List<Order> orders = Arrays.asList(new Order(10.0), new Order(22.0));
        ExtractMethod subject = new ExtractMethod(orders, "Fulano");

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        subject.setOutputStream(outputStream);
        subject.printOwing();

        assertThat(
            outputStream.toString(),
            is(String.join("\n", Arrays.asList(
                "*************************",
                "***** Customer Owes *****",
                "*************************",
                "name: Fulano",
                "amount: 32.0",
                ""))
            ));
    }
}