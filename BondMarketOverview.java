
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import javax.swing.*;
import java.util.List;
import java.util.Random;

public class BondMarketOverview extends JPanel {
	
	public static JFrame testframe = new JFrame();
	public static Securities testaccount = new Securities();
	public static Client testclient = new Client("a","b","c");
	
	public BondMarketOverview() {
		testframe.setBounds(100, 100, 480, 330);
		testframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		init(testframe, testclient, testaccount);
	}
	
	public static Currency USD = new Currency("USD", "Dollars");
	public static Currency RUB = new Currency("RUB", "Rubles");
	public static Currency CNY = new Currency("CNY", "Yen");
	public static Currency GBP = new Currency("GBP", "Pounds");
	public static Currency[] currencies = {USD,RUB,CNY,GBP};
	private static JTextField tickerChart;
	private static JTextField buyTicker;
	private static JTextField txtOfShares;
	private static JTextField textField;
	
	

	/**
	 * Create the panel.
	 */
	public static JPanel init(JFrame frame, Client client, Securities account) {
		//BondMarket.init(); // initiliazing bond market
		BondMarket.setMarket(SqlFunc.getAllBond());

		double unrealizedBond = 0.0;

		List<boughtBond> lst = SqlFunc.getBondBoughtByCustomerId(client.getId());
		for (boughtBond b : lst) {
			Bond bond = SqlFunc.getBondByName(b.getBondName());
			if (LocalDate.now().compareTo(b.getDate()) >= 0) {
				unrealizedBond += (b.getBoughtAmount()*bond.getInterest());
			}
		}

		account.setUnrealizedBond(unrealizedBond);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(230, 230, 250));
		panel.setLayout(null);
		
		JLabel overview = new JLabel("Account: ");
		overview.setBounds(6, 6, 56, 20);
		panel.add(overview);
		
		JLabel acc = new JLabel(account.type() + " " + account.getID());
		acc.setBounds(59, 6, 102, 20);
		panel.add(acc);
		
		JLabel balance = new JLabel("Balance: ");
		balance.setBounds(168, 6, 56, 20);
		panel.add(balance);
		
		JLabel accBalance = new JLabel(account.getBalance() + " " + account.getCurrency());
		accBalance.setBounds(221, 6, 102, 20);
		panel.add(accBalance);
		
		JLabel transaction = new JLabel("Bond Market:"); // CHANGE
		transaction.setBounds(6, 37, 102, 20);
		panel.add(transaction);
		
		JPanel panelN = new JPanel();
		panelN.setBackground(new Color(255, 255, 255));
		panelN.setBounds(6, 55, 218, 90);
		panel.add(panelN);
		panelN.setLayout(null);
		
	//	JLabel label = new JLabel(account.displayTransactions());

		JLabel label = new JLabel(BondMarket.displayMarket()); 
		label.setBounds(6, 0, 212, 90);
		panelN.add(label);
		
		tickerChart = new JTextField();
		tickerChart.setColumns(10);
		tickerChart.setBounds(382, 85, 40, 19);
		panel.add(tickerChart);
		
		
		JButton BACK = new JButton("BACK");
		BACK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Back");
				JPanel panelNew = securitiesOverview.init(frame, client, account);
				frame.setContentPane(panelNew);
				frame.revalidate();
				frame.repaint();
			}
		});
		
		
		BACK.setBounds(365, 7, 79, 26);
		panel.add(BACK);
		
		JButton buyBondButton = new JButton("Buy Bond");
		buyBondButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("buying stock");
				String name = buyTicker.getText();
				double amount = Double.parseDouble(txtOfShares.getText());
				System.out.println(name);
				System.out.println(amount);
				BondMarket.buyBond(BondMarket.getBond(name), amount, account, Date.getCurrentDate());
				Bond tmp = SqlFunc.getBondByName(name);
				Random random = new Random();
				String bondID = String.format("%04d", random.nextInt(10000)); // 5 digit ID;
				SqlFunc.insertBondBought(client.getId(),name,amount,LocalDate.now().plusDays(tmp.getLength() * 7),0,tmp.getLength(),bondID);

			//	StockMarket.buyStock(StockMarket.getStock(ticker), shares, account, Date.getCurrentDate());
				JPanel panelNew = BondMarketOverview.init(frame, client, account);
				frame.setContentPane(panelNew);
				frame.revalidate();
				frame.repaint();

			}
		});
		
		buyBondButton.setBounds(234, 115, 96, 29);
		panel.add(buyBondButton);
		
		buyTicker = new JTextField();
		buyTicker.setColumns(10);
		buyTicker.setBounds(284, 55, 40, 19);
		panel.add(buyTicker);
		
		txtOfShares = new JTextField();
		txtOfShares.setColumns(10);
		txtOfShares.setBounds(283, 85, 40, 19);
		panel.add(txtOfShares);
		
		JLabel buyTitle = new JLabel("Title"); 
		buyTitle.setBounds(245, 55, 40, 20);
		panel.add(buyTitle);
		
		JLabel bondAmount = new JLabel("Amount");
		bondAmount.setBounds(234, 84, 48, 20);
		panel.add(bondAmount);
		
		JButton sellBondButton = new JButton("Sell Bond"); 
		sellBondButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = textField.getText();
				String ID = tickerChart.getText();

				List<boughtBond> lst = SqlFunc.getBondBoughtByCustomerId(client.getId());
				BondMarket.sellBond(account, lst, ID, Date.getCurrentDate());
				//StockMarket.sellStocks(StockMarket.getStock(ticker), shares, account, Date.getCurrentDate());
				
				JPanel panelNew = BondMarketOverview.init(frame, client, account);
				frame.setContentPane(panelNew);
				frame.revalidate();
				frame.repaint();
			}
		});
		sellBondButton.setBounds(348, 115, 96, 29);
		panel.add(sellBondButton);
		

		JLabel ownedStocks = new JLabel("Your Bonds:");
		ownedStocks.setBounds(7, 166, 102, 20);
		panel.add(ownedStocks);
		
		JPanel ownedStocksPanel = new JPanel();
		ownedStocksPanel.setLayout(null);
		ownedStocksPanel.setBackground(Color.WHITE);
		ownedStocksPanel.setBounds(6, 185, 438, 65);
		panel.add(ownedStocksPanel);

		List<boughtBond> lstBond = SqlFunc.getBondBoughtByCustomerId(client.getId());
		System.out.println("------------");
		System.out.println(SqlFunc.displayBond(lstBond));
		System.out.println("------------");
		String str = SqlFunc.displayBond(lstBond);
		JTextArea ownedStocksLabel = new JTextArea(str);
		JScrollPane sourceListScroller = new JScrollPane(ownedStocksLabel);
		sourceListScroller.setPreferredSize(new Dimension(120, 300));
		sourceListScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
	//	ownedStocksLabel = new JLabel("<html>Corporate Bond | Maturity Date: 09/05/19<br/> Government Bond | Maturity Date: 11/05/19</html>");
		sourceListScroller.setBounds(6, 0, 426, 65);
		ownedStocksPanel.add(sourceListScroller);
		
		JLabel sellID = new JLabel("ID");
		sellID.setBounds(348, 85, 30, 20);
		panel.add(sellID);
		
		JLabel unrealized = new JLabel("Unrealized Profit:");
		unrealized.setBounds(6, 260, 102, 20);
		panel.add(unrealized);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(382, 55, 40, 19);
		panel.add(textField);
		
		JLabel sellTitle = new JLabel("Title");
		sellTitle.setBounds(348, 55, 30, 20);
		panel.add(sellTitle);
		
		JLabel curDate = new JLabel(LocalDate.now().toString());
		curDate.setBounds(381, 261, 102, 20);
		panel.add(curDate);
		
		JLabel unrealizedB = new JLabel("" + account.getUnrealizedBond());
		unrealizedB.setBounds(108, 261, 102, 20);
		panel.add(unrealizedB);

		return panel;
	}




	public static void main(String[] args) {
		
		JFrame error = new JFrame();
		error.setTitle("ERROR");
		error.setSize( 250, 250 );
		error.setLocation( 300, 100 );
		error.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		error.setVisible( true );
		// TODO Auto-generated method stub

	}
}
