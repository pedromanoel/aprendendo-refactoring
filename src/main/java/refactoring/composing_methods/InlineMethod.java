package refactoring.composing_methods;

public class InlineMethod {

    private int numberOfLateDeliveries;

    public InlineMethod(int numberOfLateDeliveries) {
        this.numberOfLateDeliveries = numberOfLateDeliveries;
    }

    int getRating() {
        return (numberOfLateDeliveries > 5)? 2 : 1;
    }
}
