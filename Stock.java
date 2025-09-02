package stockTrading;

public class Stock {
    private String symbol;
    private double price;

    public Stock(String symbol, double price) {
        this.symbol = symbol;
        this.price = price;
    }

    public String getSymbol() {
        return symbol;
    }

    public double getPrice() {
        return price;
    }

    // Simulate random price update (±5%)
    public void updatePrice() {
        double change = (Math.random() * 0.1) - 0.05; // -0.05 to +0.05
        price = Math.round(price * (1 + change) * 100.0) / 100.0;
    }

    @Override
    public String toString() {
        return symbol + ": $" + price;
    }
}
