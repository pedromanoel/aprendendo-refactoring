package refactoring.composing_methods;

public class InlineTemp {
    private Order anOrder;

    public InlineTemp(Order order) {
        anOrder = order;
    }

    boolean basePriceLargerThanThousand() {
        final double basePrice = anOrder.basePrice();

        return basePrice > 1000;
    }
}
