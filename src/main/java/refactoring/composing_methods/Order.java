package refactoring.composing_methods;

class Order {

    private double amount;
    private double basePrice;

    public Order(double amount) {
        this(amount, 0);
    }

    Order(double amount, double basePrice) {
        this.amount = amount;
        this.basePrice = basePrice;
    }

    public double getAmount() {
        return amount;
    }

    public double basePrice() {
        return basePrice;
    }
}
