import java.util.Arrays;
import java.util.Scanner;

public class Player {
	protected String name;
	protected int pot;
	protected Hand hand;
	protected Hand[] hands = new Hand[4];
	public int splits = 0;
	

	public Player(String name, int pot, Card c1, Card c2) {
		this.name = name;
		this.pot = pot;
		this.hands[0] = new Hand(c1,c2);
		this.hand = hands[0];
		this.hand.initVal();
	}
	
	public int length() {
		int acc = 0;
		for (int i =0; i < hands.length; i++) {
			if (hands[i] != null)
				acc++;
		}
		return acc;
	}
	
	public String toString() {
		String rep = "Player " + this.name + "'s hands:\n";
		for (int i = 0; i < this.length(); i++) {
				rep += "\nHand " + i + ":\n";
				rep += hands[i].sidebyside() + "\n";
		}
		return rep + "\n";
	}
	
	public Player(String name, int pot) {
		this.name = name;
		this.pot = pot;
	}
	
	public boolean check21(Hand h) {
		return (h.checkWin());
	}
	
	public void setPot(int money) {
		System.out.println("Enter desired pot");
		Scanner scan = new Scanner(System.in);
		int res = scan.nextInt();
		this.pot = res;
	}
	
	public void resetPot() {
		this.pot = 0;
	}
	
	
	private int getHandLen(int index) {
		return this.getHand(index).length();
	}
	
	public Hand getHand(int index) {
		return this.hands[index];
	}
	
	private int[] getHandVals(int index) {
		int[] potVals = getHand(index).potVals;
		return potVals;
	}
	
	private int maxHandVal(int index) {
		int[] temp = this.getHandVals(index);
		Arrays.parallelSort(temp);
		return temp[getHandLen(index) - 1];
	}
	
	private int minHandVal(int index) {
		int[] temp = this.getHandVals(index);
		Arrays.parallelSort(temp);
		return temp[0];
	}
	
	public int getVal(int index) {
		
		int max = maxHandVal(index);
		int min = minHandVal(index);
		if ( max > 21 )
			return min;
		else {
			return min;
		}
	}
	
	
	// hit
	
	public void hit(Hand hand, Deck d) {
		hand.getnCard(d.deal());
	}
	
	// double
	
	public void doubl(Hand hand, Deck d) {
		hit(hand, d);
		stand(hand);
	}
	
	//stand 
	
	public void stand(Hand hand) {
		hand.stand = true;
	}
	
	// split
	
	public void Split(Hand given,int amountbet) {
		if (this.splits > 3 || pot < amountbet) {
			System.out.println("Can't split");
		}
		else {
			int end = given.countCards(given.getHand()) - 1;
			if (given.getHand()[end].equals(given.getHand()[end - 1])) {	
				splits++;
				this.pot -= amountbet;
				// maybe add a way to let dealer know how much he wins
				
				this.hands[splits] = given.split();
				this.hands[splits].initVal();
				this.hands[splits].stand = false;
				
			}
			else
				System.out.println("Can't split");
		}
	
	}
	
	public int getPot() {
		return pot;
	}
	
	
}
