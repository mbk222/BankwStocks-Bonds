//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
//import java.time.LocalDateTime;
//import java.util.Date;
import java.time.LocalDate;

public class Bond {
	private String name;
	private double price, interest; 
	private int length; // in weeks
	private boolean bought; // true once purchased 
	private LocalDate sellby;
	
	public Bond() {
		name = "War Bond";
		price = 25.0;
		length = 16;
		bought = false;
		
	}
	
	public Bond(String name, double price, double interest, int length) {
		this.name = name;
		this.price = price;
		this.interest = interest;
		this.length = length;
		bought = false;
	}
	
	
	public static boolean buyBond(Bond bond) {

		if (true /*do your error checking here*/) {
			bond.bought = true;
			bond.sellby = LocalDate.now().plusDays(bond.length * 7);
			return true; // purchase went through
		}
		return false; // purchase invalid	
		
	}
	
	public static double sellBond(Bond bond) {
		// do your error checking first
		if (!bond.bought) { // other errors?
			if (LocalDate.now().compareTo(bond.sellby) >= 0) {
				System.out.println("here");
				return bond.price + bond.price * bond.interest;
			}
			else 
				// LocalDate.now().compareTo(bond.sellby) < 0) 
				return bond.price;
		}
		else 
			System.out.println("Cannot sell bond");
			return -1;
	}
	
	public String toString() {
		return name + " | $" + price + " | at "+ interest + " interest | " + length + " weeks | bought: " + bought; 
	}
	
	public static void main(String[] args) {
		//DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//		Date date = new Date();
//		//System.out.println(dateFormat.format(date));
//		
//		Date date2 = new Date();
//		System.out.println(date2);
//		System.out.println(date.getMonth());
//		System.out.println(date.getDate());
//		System.out.println(date.getYear());
		
		
		System.out.println(LocalDate.now());
//		
//		LocalDate date3 = LocalDate.now();
//		System.out.println(date3.getDayOfMonth());
//		System.out.println(date3.getMonth());
//		System.out.println(date3.getYear());
		
		Bond b = new Bond("Government", 300, 0.1, 4);
		Bond.buyBond(b);
		
		System.out.println("First sellby " + b.sellby);
		System.out.println("LocalDate " + LocalDate.now());
	
		System.out.println(Bond.sellBond(b));
		
		
		b.sellby = LocalDate.of(2010, 1, 1);
		
		System.out.println(b.sellby);
		System.out.println(Bond.sellBond(b));
		
		
	}
	
	
}
