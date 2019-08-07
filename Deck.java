public class Deck {
	
	private String[] suit = {"Heart", "Spade", "Club", "Diamond"};
	private String[] rank = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
	public int length = suit.length * rank.length;
	private Card[] deck = new Card[length];
	private Card[] usedDeck = new Card[length];
	public int currentCard = 0;
	public int usedCard = 0;
	
	// constructor method
	public Deck() {
		for (int i=0; i < rank.length; i++) {
			for (int j=0; j < suit.length; j++) {
				deck[suit.length*i + j] = new Card(suit[j],rank[i]);
			}
		}
	}
	
	// shuffle the deck of cards
	public void shuffle() {
		for (int i=0; i < length; i++) {
			int num = (int) (Math.random() * (length-i)) + i;
			Card temp = deck[num];
			deck[num] =deck[i];
			deck[i] = temp;
		}
	}
	
	// deal a card from a deck
	public Card deal() {
		Card dealt = new Card("","0");
		dealt = deck[currentCard];
		deck[currentCard] = new Card("","0");
		addToUsed(dealt);
		currentCard++;
		return dealt;
	}
	
	// add the dealt card to a used deck
	public void addToUsed (Card used) {
		usedDeck[usedCard] = used;
		usedCard++;
	}
	
	// reset deck values after the game
	public void reset() {
		currentCard = 0;
		usedCard = 0;
	}
	
	public int getLength() {
		return length;
	}
	
	public Card[] getDeck() {
		return deck;
	}

	public Card[] getUsedDeck() {
		return usedDeck;
	}
	
	public static void main(String[] args) {
		Deck d = new Deck();
		for (int i = 0; i < d.getLength(); i++) {
			System.out.print(i);
			System.out.print(d.deck[i]);
		}
		d.shuffle();
		for (int i = 0; i < 52; i++) {
			System.out.print(i + "s");
			System.out.print(d.deck[i]);
		}
				
	}

}
