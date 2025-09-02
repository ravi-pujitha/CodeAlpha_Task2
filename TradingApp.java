package stockTrading;

import java.util.Scanner;

public class TradingApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Market market = new Market();
        Portfolio portfolio = new Portfolio("Alice");
        portfolio.loadPortfolio("portfolio.dat");

        while (true) {
            System.out.println("\n===== STOCK TRADING PLATFORM =====");
            System.out.println("1. View Market");
            System.out.println("2. Buy Stock");
            System.out.println("3. Sell Stock");
            System.out.println("4. View Portfolio");
            System.out.println("5. Save & Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    market.updateMarket();
                    market.displayMarket();
                    break;
                case 2:
                    market.displayMarket();
                    System.out.print("Enter stock symbol: ");
                    String buySymbol = sc.next().toUpperCase();
                    System.out.print("Enter quantity: ");
                    int buyQty = sc.nextInt();
                    if (market.getStocks().containsKey(buySymbol)) {
                        portfolio.buyStock(market.getStocks().get(buySymbol), buyQty);
                    }
                    break;
                case 3:
                    System.out.print("Enter stock symbol: ");
                    String sellSymbol = sc.next().toUpperCase();
                    System.out.print("Enter quantity: ");
                    int sellQty = sc.nextInt();
                    if (market.getStocks().containsKey(sellSymbol)) {
                        portfolio.sellStock(market.getStocks().get(sellSymbol), sellQty);
                    }
                    break;
                case 4:
                    portfolio.displayPortfolio(market.getStocks());
                    break;
                case 5:
                    portfolio.savePortfolio("portfolio.dat");
                    System.out.println("👋 Exiting...");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
