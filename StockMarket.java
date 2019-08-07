import java.util.ArrayList;

public class StockMarket {
	private static ArrayList<Stock> market = new ArrayList<Stock>();
	private static Stock[] stocks = {
			new Stock(), //AAPL
			new Stock ("Microsoft Corp.", "MSFT", 140),
			new Stock ("Facebook Inc.", "FB", 190),
			new Stock ("eBay Inc.", "EBAY", 40),
			new Stock("Twitter Inc.", "TWTR", 42)
		};
	private static int marketSize = stocks.length;
	
	
	public static void init() { // DIFFERENT INITIALIZATIONS DUE TO stockMarketOverview CALLING THIS MULTIPLE TIMES
		if (market.size() == 0) {
			for (int i = 0; i < stocks.length; i++) {
				market.add(stocks[i]);
			}
		}
		else {
			for (int i = 0; i < marketSize; i++) {
				if (!market.get(i).getTicker().equals(stocks[i].getTicker()))
					market.add(stocks[i]);
			}
		}
		for (int i=0; i < marketSize; i++) {
			market.get(i).randomPrice();
		}
	}
	
	public static Stock getStock(String ticker) {
		for (int i = 0; i < stocks.length; i++) {
			if (stocks[i].getTicker().equals(ticker))
				return stocks[i];
		}
		System.out.println("Stock not found");
		return null;
	}
	
	public static String displayMarket() {
		String fin = "<html>";
		for (int i = 0; i < market.size(); i++) {
			fin+= market.get(i).getName() + " | " + market.get(i).getTicker() + " | $" + market.get(i).getPrice() + " per share.<br/>";
		}
		return fin + "</html>";
	}
	
	
	public static void addStock(Stock stock) {
		int i = getIndex(market,stock);
		if (i < 0) {
			market.add(stock);
		}
		else{
			System.out.println("Stock not added");
		}
	}

	public static void removeStock(Stock stock) {
		int i = getIndex(market,stock);
		if (i >= 0) {
			market.remove(i);
		}
		else{
			System.out.println("Stock not removed");
		}
	}
	
	// MAYBE CHANGE THIS TO BOOLEAN 
	public static void buyStock(Stock stock, int numShares, Securities account, Date tdate) {
		Double price = stock.getPrice()*numShares;
		if (account.getBalance()>=price) {
//			boolean seen = false;
//			for (int i = 0; i < account.getStockList().size(); i++) {
//				if (account.getStockList().get(i).getTicker().equals(stock.getTicker())) {
//					seen = true;
//					int shares = account.getStockList().
//					account.getStockList().get(i).setShares();
//				}
//			}

			account.getStockList().add( new Stock(stock, numShares) );
			account.makeTransaction("payment", price, "BUY STOCK", tdate);
		}
		else {
			System.out.println("Couldn't buy stock");
			// COULDN'T BUY STOCK
		}
    }
	
	//  MAYBE CHANGE THIS TO BOOLEAN 
	 public static void sellStock(Stock stock, int numShares, Securities account, Date tdate) {
	    	if (sellable(stock, numShares, account)) {
	    		int i = getIndex(account.getStockList(), stock);
	    		Stock accountStock = account.getStockList().get(i);
	    		if (accountStock.getNumOfShares()>numShares) {
	    			account.makeTransaction("receipt", stock.getPrice()*numShares, "SELL STOCKS", tdate);
	    			accountStock.setShares( accountStock.getNumOfShares()-numShares );
	    		}
	    		else {
	    			account.makeTransaction("receipt", stock.getPrice()*numShares, "SELL STOCKS", tdate);
	    			account.getStockList().remove(accountStock);
	    		}
	    	}
	    }
	 
	 public static void sellStocks(Stock stock, int numShares, Securities securities, Date tdate) {
		 int totalShares = 0;
		 // Gets the total number of shares of a stock
		 for (int i = 0; i < securities.getStockList().size(); i++) {
			 if (securities.getStockList().get(i).equals(stock)) {
				 totalShares += securities.getStockList().get(i).getNumOfShares();
			 }
		 }
		 System.out.println("TS" + totalShares);
		 
		 // Checks if has enough shares of that stock
		 if ( numShares <= totalShares) {
			 double result = 0;
			 // for every stock in the account
			 for (int i = 0; i < securities.getStockList().size(); i++) {
			 	// if it's the correct stock (tickers match)
				 if (securities.getStockList().get(i).equals(stock)) {
					 System.out.println("PRICE: " +securities.getStockList().get(i).getPrice());
					 int has = securities.getStockList().get(i).getNumOfShares(); // num shares your stock has
					 
					 if (has == numShares) {
						 result += stock.getPrice() * securities.getStockList().get(i).getNumOfShares();
						 securities.getStockList().remove(i);
						 break;
					 }
					 else if (has > numShares) {
						 result += stock.getPrice() * numShares;
						 securities.getStockList().get(i).setShares(securities.getStockList().get(i).getNumOfShares() - numShares);
						 break;
					 }
					 else {
						 numShares -= securities.getStockList().get(i).getNumOfShares();
						 result += stock.getPrice() * securities.getStockList().get(i).getNumOfShares();
						 securities.getStockList().remove(i);
					 }
					
				 }
			 }
			
			System.out.println(result);
			System.out.println("Making transaction");
			securities.makeTransaction("receipt", result, "SELL STOCKS", Date.getCurrentDate());
			 
		 }
		 else {
			 System.out.println("Sell failed, not enough shares");
		 }
	 }
	 
	
	 // SEEMINGLY WORKS, NEEDS ROUNDING 
	 public static double unrealizedProfit(Stock stock, int numShares, Securities account) {
	    	if (sellable(stock, numShares, account)) {
	    		int i = getIndex(account.getStockList(), stock);    // Gets the index of the stock in the account stock list
	    		Stock accountStock = account.getStockList().get(i); // Gets the stock from the account stock list
	    		return ((stock.getPrice()-accountStock.getPrice()) * numShares);
	    	}
	    	else {
	    		return 0;
	    	}
	    }
	 
	 
	 private static boolean sellable(Stock stock, int numShares, Securities account) {
	    	int i = getIndex(account.getStockList(), stock);	
	    	if (i >=0 ) {
	    		Stock accountStock = account.getStockList().get(i);
	    		if (accountStock.getNumOfShares()>=numShares) {
					return true;		
	    		}
	    		else {
	    			// YOU DON'T HOLD THAT MANY SHARES
	    			System.out.println("Not sellable, not enough shares");
	    			return false;
	    		}
	    	}
	    	else {
	    		// YOU DON'T HAVE ANY OF THOSE STOCKS
	    		System.out.println("Not sellable, account doesn't have stock");
	    		return false;
	    	}
	    }
	 
	 // 
	 private static int getIndex(ArrayList<Stock> getStockList, Stock stock) {
			for (int i=0; i<getStockList.size();i++) {
				if (stock.equals(getStockList.get(i))) {
					return i;
				}
			}
			return -1;
		}
	 
	public static int getMarketSize() {
		return market.size();
	}
	
	public static void main(String[] args) {
//		StockMarket.init();
//		System.out.println(StockMarket.displayMarket());
//		
//		Securities s = new Securities(); 
//		s.makeTransaction("deposit", 5000, "", Date.getCurrentDate());
//		
//		
//		StockMarket.buyStock(StockMarket.getStock("AAPL"), 2, s, Date.getCurrentDate());
//		StockMarket.buyStock(StockMarket.getStock("EBAY"), 5, s, Date.getCurrentDate());
//		System.out.println(StockMarket.sellable(StockMarket.getStock("AAPL"), 2, s));
//		System.out.println(StockMarket.getIndex(s.getStockList(), StockMarket.getStock("EBAY")));
//		System.out.println(StockMarket.getStock("AAPL").getPrice());
//		StockMarket.getStock("AAPL").randomPrice();
//		System.out.println(StockMarket.getStock("AAPL").getPrice());
//		
//		System.out.println(StockMarket.unrealizedProfit(StockMarket.getStock("AAPL"), 2, s));
//		StockMarket.sellStock(StockMarket.getStock("EBAY"), 3, s, Date.getCurrentDate());
//		StockMarket.buyStock(StockMarket.getStock("AAPL"), 2, s, Date.getCurrentDate());
//		System.out.println("HERE"+ s.displayStockL());
//		StockMarket.sellStocks(StockMarket.getStock("AAPL"), 0, s, Date.getCurrentDate());
//		System.out.println("HERE1"+ s.displayStockL());

		// init();
		// Stock s = new Stock();
		// addStock(s);
		// System.out.println(displayMarket());
		// removeStock(s);
		// System.out.println(displayMarket());
		// removeStock(s);
		// addStock(s);
		// System.out.println(displayMarket());
		// addStock(new Stock("The Company That Sells Both New And Reused Poop", "POOP",200));
		// System.out.println(displayMarket());
		
		
	}
	
}
