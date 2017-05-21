package refactoring.movie_rental;

import java.util.ArrayList;
import java.util.List;

class Customer {
    private String name;
    private List<Rental> rentals = new ArrayList<>();

    Customer(String name) {
        this.name = name;
    }

    void addRental(Rental arg) {
        rentals.add(arg);
    }

    String getName() {
        return name;
    }

    String statement() {
        StringBuilder result = new StringBuilder();

        // add header lines
        result.append(String.format("Rental record for %s\n", getName()));

        // show figures for this each
        for (Rental each : rentals) {
            result.append(String.format("\t%s\t%.1f\n", each.getMovie().getTitle(), each.getCharge()));
        }

        // add footer lines
        result.append(String.format("Amount owed is %.1f\n", getTotalCharge()));
        result.append(String.format("You earned %d frequent renter points", getFrequentRenterPoints()));

        return result.toString();
    }

    String htmlStatement() {
        StringBuilder result = new StringBuilder();

        // add header lines
        result.append(String.format("<h1>Rentals for <em>%s</em></h1><p>\n", getName()));

        // show figures for this each
        for (Rental each : rentals) {
            result.append(String.format("%s: %.1f<br>\n", each.getMovie().getTitle(), each.getCharge()));
        }

        // add footer lines
        result.append(String.format("You owe <em>%.1f</em><p>\n", getTotalCharge()));
        result.append(String.format("On this rental you earned <em>%d</em> frequent renter points<p>", getFrequentRenterPoints()));

        return result.toString();
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
