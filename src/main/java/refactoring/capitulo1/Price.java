package refactoring.capitulo1;

abstract class Price {
    abstract double getCharge(int daysRented);

    int getFrequentRenterPoints(double daysRented) {
        return 1;
    }
}
