package refactoring.composing_methods;

import java.util.List;

public class ExtractMethod {

    private List<Order> orders;
    private String name;

    public ExtractMethod(List<Order> orders, String name) {
        this.orders = orders;
        this.name = name;
    }

    void printOwing() {
        double outstanding = 0;

        // print banner
        System.out.println("*************************");
        System.out.println("***** Customer Owes *****");
        System.out.println("*************************");

        // calculate outstanding
        for (Order order : orders) {
            outstanding += order.getAmmount();
        }

        // print details
        System.out.println("name: " + name);
        System.out.println("amount: " + outstanding);
    }

}
