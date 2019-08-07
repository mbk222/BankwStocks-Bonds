import java.util.ArrayList;
import java.util.List;

public class Securities extends Account {
	//private Currency USD = new Currency("USD", "Dollars");
	private ArrayList<Stock> stocks = new ArrayList<Stock>();
	private ArrayList<Bond> bonds = new ArrayList<Bond>();
	private double unrealizedTotal;
	private double unrealizedBond;
	
	
	public Securities() {
		super();
		type = "Securities";
	}

	public Securities(String type, double balance, Currency curr, String accountID) {
		this.type = type;
		this.balance = balance;
		this.currency = curr;
		this.accountID = accountID;
	}
	
	public ArrayList<Stock> getStockList() {
		return stocks;
	}
	
	public ArrayList<Bond> getBondList() {
		return bonds;
	}
	
	public double getUnrealizedTotal() {
		return unrealizedTotal;
	}
	
	public double getUnrealizedBond() {
		return unrealizedBond;
	}
	
	public void setUnrealizedTotal(double total) {
		unrealizedTotal = total;
	}
	
	public void setUnrealizedBond(double total) {
		unrealizedBond = total;
	}

	
	public int displayContains(ArrayList<Stock> list ,Stock stock) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getTicker().equals(stock.getTicker()))
				return i;
		}
		return -1;
	}
	
	public String displayStockL() {
		
		ArrayList<Stock> heldStocks = new ArrayList<Stock>();
		for (int i = 0; i < stocks.size(); i++) {
			if (displayContains(heldStocks, stocks.get(i)) != -1) {
				//System.out.println("Again " + stocks.get(i));
				int index = displayContains(heldStocks, stocks.get(i));
			//	System.out.println("Num of shares: " + stocks.get(i).getNumOfShares());
				heldStocks.get(index).setShares(heldStocks.get(index).getNumOfShares()+stocks.get(i).getNumOfShares());
			//	System.out.println("Num of shares: " + stocks.get(i).getNumOfShares());
			}
			else {
			//	System.out.println("First " + stocks.get(i));
				heldStocks.add(new Stock(stocks.get(i), stocks.get(i).getNumOfShares()));
			}
		}
		
		String res = "<html>";
		for (int i = 0; i < heldStocks.size(); i++) {
			res += heldStocks.get(i).getNumOfShares() + " share(s) of " + heldStocks.get(i).getTicker() + "<br/>"; 
		}
		return res + "</html>";
	}
	
	public String displayBondL() {
		String res = "<html>";
		for (int i = 0; i< bonds.size();i++) {
			res+= bonds.get(i).displayBond() + "<br/>";
		}
		return res + "</html>";
	}

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
