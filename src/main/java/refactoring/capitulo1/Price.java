package refactoring.capitulo1;

abstract class Price {
    abstract int getPriceCode();

    abstract double getCharge(int daysRented);

    public int getFrequentRenterPoints(double daysRented) {
        int frequentRenterPoints = 0;
        // add frequent renter points
        frequentRenterPoints++;
        // add bonus for a two day new release each
        if (getPriceCode() == Movie.NEW_RELEASE && daysRented > 1)
            frequentRenterPoints++;

        return frequentRenterPoints;
    }
}
