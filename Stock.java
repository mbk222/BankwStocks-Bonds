import java.util.Random;

public class Stock {
	private String name, ticker;
	private double price; // per share
	private int numOfShares = 0;
	private Date dateBought;
	private double boughtp;

	// PRICE BOUGHT AT 
	
	private Random random = new Random();
	
	public Stock() {
		name = "Apple Inc.";
		ticker = "AAPL";
		price = 200;
		boughtp = price;
	}
	
	public Stock(String name, String ticker, double price) {
		this.name = name;
		this.ticker = ticker;
		this.price = price;
		boughtp = price;
	}
	
	public Stock(Stock stock, int numShares) { // GETS PUT INTO CLIENT ACCOUNT
        this.name = stock.getName();
        this.ticker = stock.getTicker();
        this.price = stock.getPrice();
        this.numOfShares = numShares;
        boughtp = price;
        dateBought = Date.getCurrentDate();
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
	
	public int getNumOfShares() {
		return numOfShares;
	}
	
	public double getBoughtP() {
		return boughtp;
	}
	
	// Setter Methods:

	public void setName(String name) { 
		this.name = name; 
	}

    public void setTicker(String ticker) { 
    	this.ticker = ticker; 
    }

    public void setPrice(double price) { 
    	this.price = price; 
    }

    public void setShares(int numOfShares) { 
    	this.numOfShares = numOfShares; 
    }

    public void setDateBought(Date dateBought) {
    	this.dateBought = dateBought;
    }
	
	public double randomPrice() {
		int direction = random.nextInt(2); // stock goes - or + 
		double percentage = random.nextInt(3); // by (x - 1) % 
		percentage /= 100;
		if (direction > 0) {
			price += price * percentage;
			return Math.round(price * 100.00) / 100.00;
		}
		price -= price * percentage;
		return Math.round(price * 100.00) / 100.00;
	}
	
	 public boolean equals(Stock stock) {
	        if (this.ticker == stock.getTicker() && this.name == stock.getName()) {
	            return true;
	        }
	        return false;
	    }
	 
	public String toString() {
        return name + " | " + ticker + " | $" + String.format("%.2f", price) + " per share.";
    }
	
//	public void buyStock(Securities acc) { MINE
//		numOfShares++;
//		if(!acc.getStockList().contains(this)) {
//			acc.getStockList().add(this);
//		}
//	}
//	
	
	public static void main(String[] args) {
//		Securities s = new Securities();
//		Stock a = new Stock();
//		Stock b = new Stock("Skylar", "SKYG", 500);
//		System.out.println(a.price);
//		System.out.println(a.randomPrice())
//		;
//		System.out.println(a.price);
//		
//		a.buyStock(s);
//		b.buyStock(s);
//		a.buyStock(s);
//		
//		
//		System.out.println(s.displayStockL());
//		
	}
	
}
