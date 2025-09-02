package stockTrading;

import java.io.*;
import java.util.*;

public class Portfolio {
    private String owner;
    private double cash;
    private Map<String, Integer> holdings;
    private List<String> transactions;

    public Portfolio(String owner) {
        this.owner = owner;
        this.cash = 10000.0; // starting cash
        this.holdings = new HashMap<>();
        this.transactions = new ArrayList<>();
    }

    public void buyStock(Stock stock, int quantity) {
        double cost = stock.getPrice() * quantity;
        if (cash >= cost) {
            cash -= cost;
            holdings.put(stock.getSymbol(), holdings.getOrDefault(stock.getSymbol(), 0) + quantity);
            transactions.add("Bought " + quantity + " " + stock.getSymbol() + " @ $" + stock.getPrice());
            System.out.println("✅ Bought " + quantity + " " + stock.getSymbol());
        } else {
            System.out.println("❌ Not enough cash!");
        }
    }

    public void sellStock(Stock stock, int quantity) {
        int owned = holdings.getOrDefault(stock.getSymbol(), 0);
        if (owned >= quantity) {
            holdings.put(stock.getSymbol(), owned - quantity);
            double revenue = stock.getPrice() * quantity;
            cash += revenue;
            transactions.add("Sold " + quantity + " " + stock.getSymbol() + " @ $" + stock.getPrice());
            System.out.println("✅ Sold " + quantity + " " + stock.getSymbol());
        } else {
            System.out.println("❌ Not enough shares to sell!");
        }
    }

    public double getPortfolioValue(Map<String, Stock> market) {
        double value = cash;
        for (String symbol : holdings.keySet()) {
            Stock stock = market.get(symbol);
            if (stock != null) {
                value += stock.getPrice() * holdings.get(symbol);
            }
        }
        return Math.round(value * 100.0) / 100.0;
    }

    public void displayPortfolio(Map<String, Stock> market) {
        System.out.println("\n👤 Owner: " + owner);
        System.out.println("💵 Cash: $" + cash);
        System.out.println("📊 Holdings: " + holdings);
        System.out.println("💰 Portfolio Value: $" + getPortfolioValue(market));
        System.out.println("📝 Transactions: " + transactions);
    }

    // Save portfolio to file
    public void savePortfolio(String filename) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(owner);
            out.writeDouble(cash);
            out.writeObject(holdings);
            out.writeObject(transactions);
            System.out.println("💾 Portfolio saved.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public void loadPortfolio(String filename) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            owner = (String) in.readObject();
            cash = in.readDouble();
            holdings = (Map<String, Integer>) in.readObject();
            transactions = (List<String>) in.readObject();
            System.out.println("📂 Portfolio loaded.");
        } catch (Exception e) {
            System.out.println("⚠ No saved portfolio found.");
        }
    }
}
