package refactoring.capitulo1;

class NewReleasePrice extends Price {
    @Override
    double getCharge(int daysRented) {
        return daysRented * 3;
    }

    @Override
    int getFrequentRenterPoints(double daysRented) {
        return daysRented > 1 ? 2 : 1;
    }
}
