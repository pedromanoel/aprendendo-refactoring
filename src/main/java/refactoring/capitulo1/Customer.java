package refactoring.capitulo1;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private String name;
    private List<Rental> rentals = new ArrayList<>();

    public Customer(String name) {
        this.name = name;
    }

    public void addRental(Rental arg) {
        rentals.add(arg);
    }

    public String getName() {
        return name;
    }

    public String statement() {
        String result = "Rental record for " + getName() + "\n";
        for (Rental each : rentals) {
            // show figures for this each
            result += "\t" + each.getMovie().getTitle() + "\t" + String.valueOf(each.getCharge()) + "\n";
        }
        // add footer lines
        result += "Amount owed is " + String.valueOf(getTotalCharge()) + "\n";
        result += "You earned " + String.valueOf(getFrequentRenterPoints()) + " frequent renter points";
        return result;
    }

    public String htmlStatement() {
        String result = String.format("<h1>Rentals for <em>%s</em></h1><p>\n", getName());
        for (Rental each : rentals) {
            // show figures for this each
            result += String.format("%s: %.1f<br>\n", each.getMovie().getTitle(), each.getCharge());
        }
        // add footer lines
        result += String.format("You owe <em>%.1f</em><p>\n", getTotalCharge());
        result += String.format("On this rental you earned <em>%d</em> frequent renter points<p>", getFrequentRenterPoints());
        return result;
    }

    double getTotalCharge() {
        return rentals.stream()
                .mapToDouble(Rental::getCharge)
                .sum();
    }

    int getFrequentRenterPoints() {
        return rentals.stream()
                .mapToInt(Rental::getFrequentRenterPoints)
                .sum();
    }
}
