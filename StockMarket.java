import java.util.ArrayList;

/** Represents the market where stocks are sold from. Also holds the methods to buy and sell stocks.
 */
public class StockMarket {
	private static ArrayList<Stock> market = new ArrayList<Stock>();
	
	/** No-arg constructor for StockMarket
	 */
	public StockMarket() {
	}
	
	/** Initializes the market.
	 */
	public static void init() {
		addStock(new Stock()); //AAPL
		addStock(new Stock ("Microsoft Corp.", "MSFT", 140));
		addStock(new Stock ("Facebook Inc.", "FB", 190));
		addStock(new Stock ("eBay Inc.", "EBAY", 40));
		addStock(new Stock ("Twitter Inc.", "TWTR", 42));
	}
	
	/** Used to help display the market, and randomizes the prices.
	 * @return Returns the string format of the market
	 */
	public static String displayMarket() {
		String fin = "<html>";
		for (int i = 0; i < market.size(); i++) {
			market.get(i).setPrice(market.get(i).randomPrice());
			fin+= market.get(i) + "<br/>";
		}
		return fin + "</html>";
	}

	/** Adds a new stock to the market if the market doesn't already contain a stock from that company.
	 * @param stock The stock to be added
	 */
	public static void addStock(Stock stock) {
		int i = contains(market,stock);
		if (i < 0) {
			market.add(stock);
		}
	}

	/** Removes a stock from the market if the market contains a stock from that company.
	 * @param stock The stock to be removed
	 */
	public static void removeStock(Stock stock) {
		int i = contains(market,stock);
		if (i >= 0) {
			market.remove(i);
		}
	}

	public static Stock getStock(String ticker) {
		for (int i = 0; i <stocks.length; i++) {
			if (stocks[i].getTicker().equals(ticker))
				return stocks[i];
		}
		return null;
	}
	
	/** If the buyer's account contains enough money to buy the desired number of stock shares, it will take out
	 *  the money from the account and put a copy of the stock with the number of shares into the account.
	 * @param stock The stock to be bought
	 * @param numShares The number of shares to be bought
	 * @param account The account to place the bought stocks in
	 * @param tdate The date of the transaction
	 */
	public static void buyStock(Stock stock, int numShares, Securities account, Date tdate) {
		Double price = stock.getPrice()*numShares;
		if (account.getBalance()>=price) {
			account.getStockList().add( new Stock(stock, numShares) );
			account.makeTransaction("payment", price, "BUY STOCK", tdate);
		}
		else {
			// COULDN'T BUY STOCK
		}
    }

    /** If the buyer's account contains the stock and enough shares of that stock, it will sell those shares.
     * @param stock The stock to be bought
     * @param numShares The number of shares to be sold
	 * @param account The account to remove the stocks from
	 * @param tdate The date of the transaction
     */
    public static void sellStock(Stock stock, int numShares, Securities account, Date tdate) {
    	if (sellable(stock, numShares, account)) {
    		int i = contains(account.getStockList(), stock);
    		Stock accountStock = account.getStockList().get(i);
    		if (accountStock.numOfShares()>numShares) {
    			account.makeTransaction("receipt", stock.getPrice()*numShares, "SELL STOCKS", tdate);
    			accountStock.setShares( accountStock.numOfShares()-numShares );
    		}
    		else {
    			account.makeTransaction("receipt", stock.getPrice()*numShares, "SELL STOCKS", tdate);
    			account.getStockList().remove(accountStock);
    		}
    	}
    }

    /** Calculates and returns the unrealized profits between the current stock price, and the price when the shares
     *  were bought, multiplied by the number of shares to be sold.
     * @param stock The stock to be sold
     * @param numShares The number of shares to be sold
     * @param account The account that holds the stock to compare prices with
     * @return Returns the amount of money that would be made or lost or 0 if the stock and numShares are unsellable
     */
    public static double unrealizedProfit(Stock stock, int numShares, Securities account) {
    	if (sellable(stock, numShares, account)) {
    		int i = contains(account.getStockList(), stock);    // Gets the index of the stock in the account stock list
    		Stock accountStock = account.getStockList().get(i); // Gets the stock from the account stock list
    		return ((stock.getPrice()-accountStock.getPrice()) * numShares);
    	}
    	else {
    		return 0;
    	}
    }

    /** Determines if the account actually holds the stock to be sold and has enough shares to sell the amount desired.
     * @param stock The stock to be sold
     * @param numShares The number of shares to be sold
     * @param account The account to check if it has the stock and enough shares of that stock
     * @return Returns true if it can sell numShares of stock, and false if it either doesn't have the stock or doesn't
     * 		   have enough of that stock.
     */
    private static boolean sellable(Stock stock, int numShares, Securities account) {
    	int i = contains(account.getStockList(), stock);	
    	if (i >=0 ) {
    		Stock accountStock = account.getStockList().get(i);
    		if (accountStock.numOfShares()>=numShares) {
				return true;		
    		}
    		else {
    			// YOU DON'T HOLD THAT MANY SHARES
    			return false;
    		}
    	}
    	else {
    		// YOU DON'T HAVE ANY OF THOSE STOCKS
    		return false;
    	}
    }

    /** Checks if the given list of stocks contains the stock in question (the given stock).
     * @param getStockList The list of stocks to check
     * @param stock The desired stock to find
     * @return Returns the index of the desired stock, or -1 if it doesn't exist in the list
     */
	private static int contains(ArrayList<Stock> getStockList, Stock stock) {
		for (int i=0; i<getStockList.size();i++) {
			if (stock.equals(getStockList.get(i))) {
				return i;
			}
		}
		return -1;
	}
	
	// public static void main(String[] args) {
	// 	StockMarket.init();
	// 	System.out.println(StockMarket.displayMarket());
	// }
	
}
