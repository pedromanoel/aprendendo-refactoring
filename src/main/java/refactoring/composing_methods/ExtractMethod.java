package refactoring.composing_methods;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Iterator;
import java.util.List;

public class ExtractMethod {

    private List<Order> orders;
    private String name;
    private OutputStream outputStream;

    public ExtractMethod(List<Order> orders, String name) {
        this.orders = orders;
        this.name = name;
    }

    void printOwing() {
        printBanner();

        double outstanding = getOutstanding();

        printDetails(outstanding);
    }

    void printOwing(double previousAmount) {
        double outstanding = previousAmount * 1.2;

        printBanner();

        outstanding = calculateOutstanding(outstanding);

        printDetails(outstanding);
    }

    private double calculateOutstanding(double outstanding) {
        Iterator<Order> i = orders.iterator();
        // calculate outstanding
        while(i.hasNext()) {
            Order order = i.next();
            outstanding += order.getAmmount();
        }

        return outstanding;
    }

    private void printBanner() {
        println("*************************");
        println("***** Customer Owes *****");
        println("*************************");
    }

    private void printDetails(double outstanding) {
        // print details
        println("name: " + name);
        println("amount: " + outstanding);
    }

    private double getOutstanding() {
        Iterator<Order> i = orders.iterator();
        double result = 0;

        while(i.hasNext()) {
            Order order = i.next();
            result += order.getAmmount();
        }

        return result;
    }

    void setOutputStream(ByteArrayOutputStream outputStream) {
        this.outputStream = outputStream;
    }

    private void println(String text) {
        new PrintStream(outputStream).println(text);
    }
}
