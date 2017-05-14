package refactoring.capitulo1;

abstract class Price {
    abstract int getPriceCode();

    abstract double getCharge(int daysRented);

    int getFrequentRenterPoints(double daysRented) {
        return 1;
    }
}
