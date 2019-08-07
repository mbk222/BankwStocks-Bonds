import java.util.Arrays;


public class Card {
	private String suit, rank;
	private int value;
	
	private String[] highRanks = {"J", "Q", "K", "A"};
	private String[][] suits = new String[][] {{"Heart"," <3"}, {"Diamond", " <>"}, {"Spade", "-|>"}, {"Club", "-8o"}}; 
	
	Card(String suit, String rank) {
		this.suit = suit;
		this.rank = rank;
		
		// determine the card value based on rank
		if (this.rank == "J" || this.rank == "Q" || this.rank == "K" || this.rank == "A") {
			if (this.rank == "A")
				this.value = 11;
			else 
				this.value = 10;
			}
		else
			this.value = Integer.valueOf(this.rank);
		}
	
	public boolean equals(Card other) {
		return (this.value == other.value);
	}

	// accessor methods - suit
	public String getSuit() {
		String rep = "";
		for (int i = 0; i < suits.length; i++) {
			if (this.suits[i][0] == this.suit) {
				rep = this.suits[i][1];
			}	
		}
		return rep;
	}
		
	// accessor methods - rank
	public String getRank() {
		return this.rank;
	}
		
	// accessor method - value
	public int getValue() {
		return this.value;
	}

	public String toString() {
		String ssym = new String();
		String rep = new String();
		for (int i = 0; i < suits.length; i++) {
			if (this.suits[i][0] == this.suit) {
				ssym += this.suits[i][1];
			}	
		}
		if (this.rank.length() > 1) {
			rep = " ____\n" + 
				  "|" + this.rank + "  |\n" + 
				  "|" + ssym + " |\n" + 
				  "|__" + this.rank + "|\n";
		}
		else {
			rep = " ____\n" + 
				 "|" + this.rank + "   |\n" + 
			     "|" + ssym + " |\n" + 
			     "|___" + this.rank + "|\n";
		}
		return rep;		
	}
	
	public String flip() {
		String rep = " ____\n" +
					"|    |\n" + 
					"|    |\n" +
					"|____|";
		return rep;
	}

	public static void main(String[] args) {
		
//		Card h = new Card("Heart", "2");
//		Card s = new Card("Spade","A");
//		Card d =  new Card("Diamond", "J");
//		Card c = new Card("Club", "10");
//		
//		System.out.println(s);
//		System.out.println(h);
//		System.out.println(d);
//		System.out.println(c);
//		
//
//		String t = "\u2665";
//		System.out.println(t);
		
		Card h = new Card("Heart", "2");
		System.out.println(h.getValue());
		
		String a = new String(new char[2]).replace("\0", "____ ");
		System.out.println(a);
		
	}

}
