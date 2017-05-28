package refactoring.composing_methods;

public class InlineTemp {
    private Order anOrder;

    public InlineTemp(Order order) {
        anOrder = order;
    }

    boolean basePriceLargerThanThousand() {
        return anOrder.basePrice() > 1000;
    }
}
