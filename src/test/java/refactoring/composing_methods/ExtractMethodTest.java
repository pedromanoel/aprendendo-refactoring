package refactoring.composing_methods;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class ExtractMethodTest {
    
    @Test
    public void testPrintOwing() {
        List<Order> orders = Arrays.asList(new Order(10.0), new Order(22.0));
        ExtractMethod subject = new ExtractMethod(orders, "Fulano");

        subject.printOwing();
    }
}