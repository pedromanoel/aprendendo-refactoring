package refactoring.composing_methods;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
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
        double outstanding = 0;

        printBanner();

        // calculate outstanding
        for (Order order : orders) {
            outstanding += order.getAmmount();
        }

        printDetails(outstanding);
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

    void setOutputStream(ByteArrayOutputStream outputStream) {
        this.outputStream = outputStream;
    }

    private void println(String text) {
        new PrintStream(outputStream).println(text);
    }
}
