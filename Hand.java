import java.util.Arrays;

public class Hand {
	private Player Player;
	private Card[] hand = new Card[11];
	private int currentCard = 0;
	public int value = 0;
	public int[] potVals = {0,0,0,0};
	public boolean stand;

	
	public Hand() {
	}
	
	public Hand(Card c1) {
		hand[currentCard] = c1;
		currentCard++;
		this.stand = false;
	}
	
	public Hand (Card c1, Card c2) {
		hand[currentCard] = c1;
		currentCard++;
		hand[currentCard] = c2;
		currentCard++;
		this.stand = false;
	}
	
	public String toString() {
		String rep = "";
		for (int i = 0; i < hand.length; i++) {
			if (hand[i] != null) 
			rep += hand[i] + " ";
		}
		return rep;
	}
	
	public int length() {
		int acc = 0;
		for (int i = 0; i < hand.length; i++) {
			if (hand[i] != null)
				acc++;
		}
		return acc;
	}
	

	public Card[] getHand() {
		return this.hand;
	}
	
	public String sidebyside() {
		String rep,r1,r2,r3,r4;
		rep = r1 = r2 = r3 = r4 = "";
		int acc = 0;
		int ccount = 0;
		for (int x = 0; x < hand.length; x++) {
			if (hand[x] != null) 
				ccount++;
		}
		
		int iacc = 0;
		int[] indexes = new int[ccount + 1];
		for (int x = 0; x < hand.length; x++) {
			if (hand[x] != null) 
				indexes[iacc] = x;
				iacc++;
		}
		
		String[] ranks = new String[ccount];
		String[] suits = new String[ccount];
		
		for (int i = 0; i < ccount; i++) {
			ranks[i] = hand[indexes[i]].getRank();
			suits[i] = hand[indexes[i]].getSuit();
		}
		
		for (int j = 0; j < ccount; j++) {
			int c = ranks[j].length();
			if (c > 1) {
				r1 += " ____   ";
				r2 += "|" + ranks[j] + "  |  ";
				r3 += "|" + suits[j] + " |  ";
				r4 += "|__" + ranks[j] + "|  ";
			}
			else {
			r1 += " ____   ";
			r2 += "|" + ranks[j] + "   |  ";
			r3 += "|" + suits[j] + " |  ";
			r4 += "|___" + ranks[j] + "|  ";
			}
		}
		
		rep += r1 + "\n" + r2 + "\n" + r3 + "\n" + r4;
		return rep;
	}
	
	public void initVal() {
		int v1 = hand[0].getValue();

		if (countCards(this.hand) < 2) {
			potVals[0] = v1;
			potVals[1] = v1;
			potVals[2] = v1;
			potVals[3] = v1;
		}
		
		else {
			int v2 = hand[1].getValue();
			
			if (hand[0].getRank() == "A" && hand[1].getRank() == "A") {
				potVals[0] = 2;
				potVals[1] = 12;
				potVals[2] = 2;
				potVals[3] = 12;
			}
			else if (hand[0].getRank() == "A" || hand[1].getRank() == "A") {
				if (v1 == 11) {
					potVals[0] = v2 + 1;
					potVals[1] = v2 + 11;
					potVals[2] = v2 + 1;
					potVals[3] = v2 + 11;
				}
				else {
					potVals[0] = v1 + 1;
					potVals[1] = v1 + 11;
					potVals[2] = v1 + 1;
					potVals[3] = v1 + 11;
				}
			}
			else {
				potVals[0] = v1 + v2;
				potVals[1] = v1 + v2;
				potVals[2] = v1 + v2;
				potVals[3] = v1 + v2;
			}
		}
	}
	
	private void updateVal(Card dealt) {
		if (dealt.getRank() == "A") {
			for (int i = 0; i < 4; i++) {
				if (i % 2 == 0) 
					potVals[i] += 1;
				else 
					potVals[i] += 11;
			}
		}
		else {
			for (int i = 0; i < 4; i++) {
				potVals[i] += dealt.getValue();
			}
		}
	}
	
	
	public void getnCard(Card dealt) {
		hand[currentCard] = dealt;
		updateVal(dealt);
		this.currentCard++;
	}
	
	
	public boolean checkWin() {
		if (Arrays.asList(potVals).contains(21))
			return true;
		return false;
	}
	

	
	public int getBestValue() {
		int best = 0;
		for (int i = 0; i < 2; i++) {
			if (potVals[1] > 21)
				best = potVals[0];
			else {
				if (potVals[1] > potVals[0])
					best = potVals[1];
				else 
					best = potVals[0];
			}
		}
		this.value = best;
		return best;
	}

	
	
	public int countCards(Card[] given) {
		int acc = 0;
		for (int i = 0; i < given.length; i++) {
			if (given[i] != null) {
				acc++;
			}
		}
		return acc;
	}
	
	//public split()
	public Hand split() {
				
		int end = countCards(this.hand)-1;

		Hand temp = new Hand();
		int tempInd = 1;
		
		Hand newHand = new Hand();
		int newInd = 1;
		
		newHand.hand[0] = this.hand[end];
		newHand.currentCard = 1;
		temp.hand[0] = this.hand[end-1];
		temp.currentCard = 1;
		
		if (end == 1) {
			this.hand = newHand.hand;	
			return temp;
		}
			
		for (int i = 0; i < end-1; i++) {
			if (i%2 == 1) {
				temp.hand[tempInd] = hand[i];
				tempInd++;
				temp.currentCard++;
			}
			else {
				newHand.hand[newInd] = hand[i];
				newInd++;
				newHand.currentCard++;
			}
		}
		this.hand = newHand.hand;
		//sort();
		
		if (countCards(this.hand) == countCards(temp.hand)) {
			//this.currentCard++;
			return temp;
		}
		else {
			//this.currentCard++;
			return temp;
		}
	}
	
	
	
}