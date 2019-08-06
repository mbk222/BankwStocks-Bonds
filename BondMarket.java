import java.util.ArrayList;
import java.util.List;


public class BondMarket {
	private static ArrayList<Bond> market = new ArrayList<Bond>();
	private static Bond[] bonds = {
			new Bond(), // Government Bond
			new Bond("UK", 250, 0.06, 16),
			new Bond("BRZ", 150, 0.03, 4),
			new Bond("TWTR", 100, 0.1, 1),
			new Bond("EBAY", 75, 0.03, 4)
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
	
	 private static int getIndex(ArrayList<Bond> bondList, Bond bond) {
			for (int i=0; i < bondList.size();i++) {
				if (bond.equals(bondList.get(i))) {
					return i;
				}
			}
			return -1;
		}
	 
	
}
