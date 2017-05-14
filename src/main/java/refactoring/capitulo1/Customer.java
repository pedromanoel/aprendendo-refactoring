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
        String result = "refactoring.capitulo1.Rental record for " + getName() + "\n";
        for (Rental each : rentals) {
            // show figures for this each
            result += "\t" + each.getMovie().getTitle() + "\t" + String.valueOf(each.getCharge()) + "\n";
        }
        // add footer lines
        result += "Amount owed is " + String.valueOf(getTotalCharge()) + "\n";
        result += "You earned " + String.valueOf(getFrequentRenterPoints()) + " frequent renter points";
        return result;
    }

    double getTotalCharge() {
        double totalAmount = 0;

        for (Rental each : rentals) {
            totalAmount += each.getCharge();
        }

        return totalAmount;
    }

    int getFrequentRenterPoints() {
        int frequentRenterPoints = 0;

        // add frequent renter points
        for (Rental each : rentals) {
            frequentRenterPoints += each.getFrequentRenterPoints();
        }

        return frequentRenterPoints;
    }
}
