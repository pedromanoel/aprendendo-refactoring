package refactoring.composing_methods;

public class InlineMethod {

    private int numberOfLateDeliveries;

    public InlineMethod(int numberOfLateDeliveries) {
        this.numberOfLateDeliveries = numberOfLateDeliveries;
    }

    int getRating() {
        return (moreThanFiveLateDeliveries())? 2 : 1;
    }

    private boolean moreThanFiveLateDeliveries() {
        return numberOfLateDeliveries > 5;
    }
}
