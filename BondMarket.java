import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;



public class BondMarket {
	private static ArrayList<Bond> market = new ArrayList<Bond>();
	private static Bond[] bonds = {
			new Bond(), // Government Bond
			new Bond("UK", 0.06, 16),
			new Bond("BRZ", 0.03, 4),
			new Bond("TWTR", 0.1, 1),
			new Bond("EBAY", 0.03, 4)
			};
	
	
	private static int marketSize = bonds.length;
	
	public static void init() { // DIFFERENT INITIALIZATIONS DUE TO bondMarketOverview CALLING THIS MULTIPLE TIMES
		if (market.size() == 0) {
			for (int i = 0; i < bonds.length; i++) {
				market.add(bonds[i]);
			}
		}
		else {
			for (int i = 0; i < marketSize; i++) {
				if (!market.get(i).getName().equals(bonds[i].getName())) 
					market.add(bonds[i]);
			}
		}
	}
	
	public static Bond getBond(String name) {
		for (int i = 0; i < bonds.length; i++) {
			if (bonds[i].getName().equals(name))
				return bonds[i];
		}
		System.out.println("Bond not found");
		return null;
	}
	
	public static void addBond(Bond bond) {
		int i = getIndex(market,bond);
		if (i < 0) {
			market.add(bond);
		}
	}

	public static void removeBond(Bond bond) {
		int i = getIndex(market,bond);
		if (i >= 0) {
			market.remove(i);
		}
	}

	public static void buyBond(Bond bond, double amount, Securities account, Date tdate) {
		if (account.getBalance()>=amount) {
			account.getBondList().add( new Bond(bond, amount) );
			account.makeTransaction("payment", amount, "BUY BOND", tdate);
		}
		else {
			// COULDN'T BUY BOND
		}
    }

    public static void sellBond(Securities account, String bondID, Date tdate) {
    	for (int i=0; i < account.getBondList().size(); i++) {
    		Bond accountBond = account.getBondList().get(i);
    		if (accountBond.getBondId().equals(bondID)) {
    			if (LocalDate.now().compareTo(accountBond.getSellBy()) >= 0) {
    				System.out.println("Interest recieved");
					account.makeTransaction("receipt", ((accountBond.getAmount() + accountBond.getAmount()) * accountBond.getInterest()), "SELL BOND", tdate);
					account.getBondList().remove(i);
				}
				else {
					System.out.println("No interest");
					account.makeTransaction("receipt", accountBond.getAmount(), "SELL BOND", tdate);
					account.getBondList().remove(i);
				}
    		}
    	}
    }
	
	private static int getIndex(ArrayList<Bond> bondList, Bond bond) {
			for (int i=0; i < bondList.size();i++) {
				if (bond.equals(bondList.get(i))) {
					return i;
				}
			}
			return -1;
		}
	
	public static String displayMarket() {
		String fin = "<html>";
		for (int i = 0; i < market.size(); i++) {
			fin+= market.get(i) + "<br/>";
		}
		return fin + "</html>";
	}
	
	public static void main(String[] args) {
		Securities s = new Securities();
		Bond b = new Bond();
		BondMarket.buyBond(b, 0, s, Date.getCurrentDate());
		System.out.println(s.displayBondL());
	}
	 
	
}