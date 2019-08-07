
import java.util.Arrays;

public class Dealer extends Player {
	private boolean Computer = false;
	private Deck deck;
	public int value = 0; 
	public int gameNum = 0;
	
	// constructor method
	public Dealer(boolean comp, Deck d, String name, int pot) {
		super(name, pot);
		this.deck = d;
		Computer = comp;
		Card c1 = d.deal();
		Card c2 = d.deal();
		this.hands[0] = new Hand(c1, c2);
		this.hands[0].initVal();
	//	this.gameNum += 1 ;
	}
	
	
	public String getName() {
		return this.name;
	}
	
	public int getPot() {
		return this.pot;
	}
	
	public Hand getHand() {
		return this.hands[0];
	}
	
	private int getHandLen() {
		return this.getHand().length();
	}
	
	private int[] getHandVals() {
		int[] potVals = getHand().potVals;
		return potVals;
	}
	
	private void addCard(Card c) {
		this.getHand().getnCard(c);
	}
	
	
	private int maxHandVal() {
		int[] temp = this.getHandVals();
		Arrays.parallelSort(temp);
		return temp[getHandLen() - 1];
	}
	
	private int minHandVal() {
		int[] temp = this.getHandVals();
		Arrays.parallelSort(temp);
		return temp[0];
	}
	
	public void hit() {
		//this.addCard(this.deck.deal());
		int max = this.maxHandVal();
		int min = this.minHandVal();
		
		while (true) {
			
			if ( (max >= 17) && (max < 22) )
				break;
			
			else if (max > 21) {
				if (min == max) 
					break;
				else 
					max = min;
			}
			else {
				this.addCard(this.deck.deal());
				max = this.maxHandVal();
				min = this.minHandVal();
			}
		}
		this.value = max;
		gameNum++;
	}
	
	public void addToPot(int PotMoney) {
		this.pot += PotMoney;
	}
	
	public String toString() {
		String rep = "Dealer " + this.name + "'s hand: \n"; 
		if (gameNum == 0) {
			rep+= this.hands[0].getHand()[0] + this.hands[0].getHand()[1].flip() + "\n";
			
		}
		else {
			rep += this.hands[0].sidebyside();
		}
		return rep;
	}
	

}