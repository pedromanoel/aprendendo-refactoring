package refactoring.capitulo1;

class Rental {

    private Movie movie;
    private int daysRented;

    Rental(Movie movie, int daysRented) {
        this.movie = movie;
        this.daysRented = daysRented;
    }

    Movie getMovie() {
        return movie;
    }

    int getDaysRented() {
        return daysRented;
    }

    double getCharge() {
        return getMovie().getCharge(getDaysRented());
    }

    int getFrequentRenterPoints() {
        return getMovie().getFrequentRenterPoints(getDaysRented());
    }
}
