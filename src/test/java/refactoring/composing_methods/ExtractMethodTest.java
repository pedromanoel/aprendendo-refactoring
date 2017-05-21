package refactoring.composing_methods;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.io.ByteArrayOutputStream;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class ExtractMethodTest {

    private ByteArrayOutputStream outputStream;
    private ExtractMethod subject;

    @Before
    public void setUp() {
        List<Order> orders = Arrays.asList(new Order(10.0), new Order(22.0));
        subject = new ExtractMethod(orders, "Fulano");

        outputStream = new ByteArrayOutputStream();
        subject.setOutputStream(outputStream);
    }

    @Test
    public void testPrintOwing() {
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

    @Test
    public void testPrintOwingWithPreviousAmount() {
        subject.printOwing(10.0);

        assertThat(
            outputStream.toString(),
            is(String.join("\n", Arrays.asList(
                "*************************",
                "***** Customer Owes *****",
                "*************************",
                "name: Fulano",
                "amount: 44.0",
                ""))
            ));

    }
}