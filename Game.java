import java.util.Arrays;
import java.util.Scanner;

public class Game {
	 // putarray of hands in player class
	private Player Player1;
	private Dealer Player2;
	private int P1wins;
	private int P2wins;
	private Deck deck;
	private int gameNum;
	private int dPot = 100000;
	private boolean comp;
	
	public Game() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Please enter the number of players: (1-2)");
		int res = scan.nextInt();
		scan.nextLine();
		String dname = "Computer";
		if (res != 1) {
			this.comp = true;
			System.out.println("Please enter dealer name: ");
			dname = scan.nextLine();
		
		}
		System.out.println("Please enter player name: ");
		String pname = scan.nextLine();
		System.out.println("How much pot you want?");
		int pot = scan.nextInt();
		scan.nextLine();
		
		
		Deck d = new Deck();
		deck = d;
		d.shuffle();
		Player1 = new Player(pname, pot, d.deal(), d.deal());
		Player1.hand.initVal();
		Player2 = new Dealer(comp, d, dname, dPot);
	}
	
	public void winner() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Would you like to play again? (1) Yes (2) Cash out");
		int res = scan.nextInt();
		if (res == 1) {
			Player2.gameNum = 0;

			deck = new Deck();
			deck.shuffle();
			Player1 = new Player(Player1.name, Player1.pot, deck.deal(), deck.deal());
			Player1.hand.initVal();
			Player2 = new Dealer(comp, deck, Player2.name, Player2.pot);
			playGame();
		}
		else {
			System.out.println("Player 1 has won " + P1wins + " times");
			System.out.println("Player 1 remaining pot: " + Player1.pot);
			System.out.println("Player 2 has won " + P2wins + " times");
		}
	}
	
	public void playGame() {
		Scanner scan = new Scanner(System.in);
		int bet = askForPot();
		int[] vals = new int[Player1.splits + 1];
		System.out.println(Player2);
		System.out.println(Player1);
		
		if (Player1.hand.getBestValue() == 21 && Player2.value == 21) {
			System.out.println("Push, pot unchanged");
			winner();
			return;
		}
		else if (Player1.hand.getBestValue() == 21) {	
			System.out.println("Player " + Player1.name + " wins");
			Player1.pot += bet;
			P1wins++;
			System.out.println("New pot: " + Player1.pot);
			winner();
			return;
			}
		else if (Player2.value == 21) {
			System.out.println("Dealer " + Player2.name + " wins");
			Player1.pot -= bet;
			P2wins++;
			System.out.println("New pot: " + Player1.pot);
			winner();
			return;
		}
		else {
			makeMove(0, bet);
			if (Player1.hands[0].getBestValue() >= 21) {
				Player1.hands[0].stand = true;
			}
			else {
				System.out.println("1");
				for (int i = 0; i <= Player1.splits; i++) {
					while (!allStand()) {
						
						if (!Player1.hands[i].stand) 
							makeMove(i, bet);
						if (Player1.hands[i].getBestValue() >= 21) {
							Player1.hands[i].stand = true;
						}
							
					}
				}
			}
			Player2.hit();
			for (int j = 0; j <= Player1.splits; j++) {
				System.out.println(Player2);
				System.out.println("\nPlayer1 best value: " + Player1.hands[j].getBestValue());
				System.out.println("Player2 best value: " + Player2.value + "\n");
				
				vals[j] = Player1.hands[j].getBestValue();
			
				if ((Player2.value == 21 && vals[j] == 21) || (Player2.value > 21 && vals[j] > 21)) {
					System.out.println("Push, pot unchanged");
				}
				else if (Player2.value == 21) {
					System.out.println("Dealer " + Player2.name + " wins");
					Player1.pot -= bet;
					P2wins++;
					System.out.println("New pot: " + Player1.pot);
				}
				else if (vals[j] == 21) {
					System.out.println("Player " + Player1.name + " wins");
					Player1.pot += bet;
					P1wins++;
					System.out.println("New pot: " + Player1.pot);		
				}
				else if (Player2.value < 21 && vals[j] < 21) {
					if (Player2.value > vals[j]) {
						System.out.println("Dealer " + Player2.name + " wins");
						Player1.pot -= bet;
						P2wins++;
						System.out.println("New pot: " + Player1.pot);
					}
					else if (Player2.value < vals[j]) {
						System.out.println("Player " + Player1.name + " wins");
						Player1.pot += bet;
						P1wins++;
						System.out.println("New pot: " + Player1.pot);		
					}
					else {
						System.out.println("Push, pot unchanged");
					}
				}
				else if (Player2.value < 21 && vals[j] > 21) {
					System.out.println("Dealer " + Player2.name + " wins");
					Player1.pot -= bet;
					P2wins++;
					System.out.println("New pot: " + Player1.pot);
				}
				else if (Player2.value > 21 && vals[j] < 21) {
					System.out.println("Player " + Player1.name + " wins");
					Player1.pot += bet;
					P1wins++;
					System.out.println("New pot: " + Player1.pot);		
				}
				}
			winner();
 			}
		}
	
		

	
	public boolean allStand() {
		for (int i = 0; i <= Player1.splits; i++) {
			if (Player1.hands[i].stand != true) {
				return false;
			}
		}
		return true;
	}
		
		
		
	
	public void makeMove(int index, int bet) {
		
			Scanner scan = new Scanner(System.in);
			System.out.println("(1) Hit (2) Stand (3) Double (4) Split - Please enter choice: ");
			int opt = scan.nextInt();
			if (opt == 1) {
				Player1.hit(Player1.hands[index], deck);
				System.out.println(Player1);
			}
			else if (opt == 2) {
				Player1.hands[index].stand = true;
			
			}
			else if (opt == 3) {
				Player1.doubl(Player1.hands[index], deck);
				System.out.println(Player1);
			
			}
			else if (opt == 4) {
				Player1.Split(Player1.hands[index], bet);
				Player1.splits++;
				System.out.println(Player1);
			}
			else {
		}
	}
	
	public int askForPot() {
		Scanner scan = new Scanner(System.in);
		int bet;
		do {
			System.out.println(Player1.name + ", how much would you like to bet? 1-5000 (Remaining: $" + Player1.getPot());
			while (!scan.hasNextInt()) {
				String input = scan.next();
				System.out.printf("\"%s\" not a valid response, try again. \n)", input);
			}
			bet = scan.nextInt();
		} while (bet < 1 || bet > 5000 || bet > Player1.getPot());
		return bet;
	}
	
	public static void main(String[] args) {
		Game g = new Game();
		g.playGame();
	}

}
