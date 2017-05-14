package refactoring.capitulo1;

public class ChildrensPrice extends Price {
    @Override
    int getPriceCode() {
        return Movie.CHILDREN;
    }
}
