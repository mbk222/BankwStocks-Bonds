import java.util.Random;

public class Stock {
	private String name, ticker;
	private double price; // per share
	private int numOfShares = 0;
	// DATE BOUGHT 
	// PRICE BOUGHT AT 
	
	private Random random = new Random();
	
	public Stock() {
		name = "Apple Inc.";
		ticker = "AAPL";
		price = 200;
	}
	
	public Stock(String name, String ticker, double price) {
		this.name = name;
		this.ticker = ticker;
		this.price = price;
	}
	
	public String getName() {
		return name;
	}
	
	public String getTicker() {
		return ticker;
	}
	
	public double getPrice() {
		return price;
	}
	
	public int numOfShares() {
		return numOfShares;
	}
	
	public double randomPrice() {
		int direction = random.nextInt(2);
		double percentage = random.nextInt(26);
		percentage /= 100;
		if (direction > 0) {
			price += price * percentage;
			return price;
		}
		price -= price * percentage;
		return price;
	}
	
	public void buyStock(Securities acc) {
		numOfShares++;
		if(!acc.getStockList().contains(this)) {
			acc.getStockList().add(this);
		}
	}
	
	
	public static void main(String[] args) {
		Securities s = new Securities();
		Stock a = new Stock();
		Stock b = new Stock("Skylar", "SKYG", 500);
		System.out.println(a.price);
		System.out.println(a.randomPrice())
		;
		System.out.println(a.price);
		
		a.buyStock(s);
		b.buyStock(s);
		a.buyStock(s);
		
		
		System.out.println(s.displayStockL());
		
	}
	
}
