import java.util.ArrayList;

public class StockMarket {
	private static ArrayList<Stock> market = new ArrayList<Stock>();
	
	public StockMarket() {
	}
	
	public static void init() {
		market.add(new Stock()); //AAPL
		market.add(new Stock ("Microsoft Corp.", "MSFT", 140));
		market.add(new Stock ("Facebook Inc.", "FB", 190));
		market.add(new Stock ("eBay Inc.", "EBAY", 40));
		market.add(new Stock ("Twitter Inc.", "TWTR", 42));
	}
	
	public static String displayMarket() {
		String fin = "<html>";
		for (int i = 0; i < market.size(); i++) {
			fin+= market.get(i).getName() + " | " + market.get(i).getTicker() + " | $" + market.get(i).randomPrice() + " per share.<br/>";
		}
		return fin + "</html>";
	}
	
	public static void main(String[] args) {
		StockMarket.init();
		System.out.println(StockMarket.displayMarket());
	}
	
}
