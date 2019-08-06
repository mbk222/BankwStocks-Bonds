import java.util.Random;

public class Stock {
	private String name, ticker;
	private double price; // per share
	private int numOfShares = 0;
	// DATE BOUGHT AT
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

	public Stock(Stock stock, int numShares) {
        this.name = stock.getName();
        this.ticker = stock.getTicker();
        this.price = stock.getPrice();
        this.numOfShares = numShares;
    }

    // Getter Methods:

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

    // Other Methods:
	
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

	public String toString() {
        return name + " | " + ticker + " | $" + String.format("%.2f", price) + " per share.";
    }

    public boolean equals(Stock stock) {
        if (this.ticker == stock.getTicker() && this.name == stock.getName()) {
            return true;
        }
        return false;
    }
	
	// public void buyStock(Securities acc) {
	// 	numOfShares++;
	// 	if(!acc.getStockList().contains(this)) {
	// 		acc.getStockList().add(this);
	// 	}
	// }


	// public Stock buyStock(int numShares) {
	//     Stock stock = new Stock(this.name,this.ticker,this.price);
 	//     stock.setShares(numShares);
 	//     return stock;
 	// }

    // public Stock buyStock(Stock stock, int numShares) {
    //     Stock newStock = new Stock(stock, numShares);
    //     return newStock;
    // }

    // public double sellStock(double currentPrice) {
    //     return ((currentPrice - price)*numOfShares);
    // }

    // public double sellStock(Stock stock) {
    //     return sellStock(stock.getPrice());
    // }
	
}
