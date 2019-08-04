import java.util.ArrayList;

public class Securities extends Account {
	//private Currency USD = new Currency("USD", "Dollars");
	private ArrayList<Stock> stocks = new ArrayList<Stock>();
	private ArrayList<Bond> bonds = new ArrayList<Bond>();
	
	
	public Securities() {
		super();
		type = "Securities";
	}
	
	public ArrayList<Stock> getStockList() {
		return stocks;
	}
	
	public ArrayList<Bond> getBondList() {
		return bonds;
	}
	
	public String displayStockL() {
		String res = "<html>";
		for (int i = 0; i < stocks.size(); i++) {
			res += stocks.get(i).numOfShares() + " share(s) of " + stocks.get(i).getTicker() + "<br/>"; 
		}
		return res + "</html>";
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
