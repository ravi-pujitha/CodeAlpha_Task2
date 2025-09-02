package stockTrading;

import java.util.*;

public class Market {
    private Map<String, Stock> stocks;

    public Market() {
        stocks = new HashMap<>();
        stocks.put("AAPL", new Stock("AAPL", 150));
        stocks.put("GOOG", new Stock("GOOG", 2800));
        stocks.put("TSLA", new Stock("TSLA", 700));
        stocks.put("AMZN", new Stock("AMZN", 3300));
    }

    public Map<String, Stock> getStocks() {
        return stocks;
    }

    public void updateMarket() {
        for (Stock stock : stocks.values()) {
            stock.updatePrice();
        }
    }

    public void displayMarket() {
        System.out.println("\n📈 Market Prices:");
        for (Stock stock : stocks.values()) {
            System.out.println(stock);
        }
    }
}
