import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.DefaultComboBoxModel;

public class accountOverview extends JPanel {
	
	public static JFrame testframe = new JFrame();
	public static Account testaccount = new Account();
	public static Client testclient = new Client("a","b","c");
	
	public accountOverview() {
		testframe.setBounds(100, 100, 480, 330);
		testframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		init(testframe, testclient, testaccount);
	}
	
	public static Currency USD = new Currency("USD", "Dollars");
	public static Currency RUB = new Currency("RUB", "Rubles");
	public static Currency CNY = new Currency("CNY", "Yen");
	public static Currency GBP = new Currency("GBP", "Pounds");
	public static Currency[] currencies = {USD,RUB,CNY,GBP};
	
	
	private static JTextField deposit;
	private static JTextField withdraw;
	private static JTextField accID;
	private static JTextField tranAmount;
	private static JTextField loanAmount;
	
	

	/**
	 * Create the panel.
	 */
	public static JPanel init(JFrame frame, Client client, Account account) {
		JPanel panel = new JPanel();
		panel.setBackground(new Color(230, 230, 250));
		panel.setLayout(null);
		
		JLabel overview = new JLabel("Overview of Account: ");
		overview.setBounds(6, 6, 166, 20);
		panel.add(overview);
		
		JLabel acc = new JLabel(account.type() + " " + account.getID());
		acc.setBounds(144, 6, 239, 20);
		panel.add(acc);
		
		JLabel balance = new JLabel("Balance: ");
		balance.setBounds(6, 28, 56, 20);
		panel.add(balance);
		
		JLabel accBalance = new JLabel(account.getBalance() + " " + account.getCurrency());
		accBalance.setBounds(74, 28, 102, 20);
		panel.add(accBalance);
		
		JLabel transaction = new JLabel("Transactions:");
		transaction.setBounds(6, 59, 102, 20);
		panel.add(transaction);
		
		JPanel panelN = new JPanel();
		panelN.setBackground(new Color(255, 255, 255));
		panelN.setBounds(6, 81, 438, 76);
		panel.add(panelN);
		panelN.setLayout(null);
		
		JLabel label = new JLabel(account.displayTransactions());
		label.setBounds(6, 0, 426, 64);
		panelN.add(label);
		
		deposit = new JTextField();
		deposit.setBounds(128, 163, 79, 26);
		panel.add(deposit);
		deposit.setColumns(10);
		
		JButton depositB = new JButton("Deposit");
		
		depositB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Deposit");
				double amount = Double.parseDouble(deposit.getText());
				account.makeTransaction("deposit", amount, "DEPOSIT", Date.getCurrentDate());
				
				JPanel panelNew = accountOverview.init(frame, client, account);
				frame.setContentPane(panelNew);
				frame.revalidate();
				frame.repaint();
			}
		});
		
		depositB.setBounds(6, 162, 117, 29);
		panel.add(depositB);
		
		withdraw = new JTextField();
		withdraw.setColumns(10);
		withdraw.setBounds(365, 163, 79, 26);
		panel.add(withdraw);
		
		JButton withdrawB = new JButton("Withdraw");
		withdrawB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Withdraw");
				double amount = Double.parseDouble(withdraw.getText());
				account.makeTransaction("payment", amount, "Withdraw", Date.getCurrentDate());
				
				JPanel panelNew = accountOverview.init(frame, client, account);
				frame.setContentPane(panelNew);
				frame.revalidate();
				frame.repaint();
			}
		});
		
		withdrawB.setBounds(243, 163, 117, 29);
		panel.add(withdrawB);
		
		JComboBox curr = new JComboBox();
		curr.setModel(new DefaultComboBoxModel(new String[] {"USD", "RUB", "CNY", "GBP"}));
		curr.setBounds(253, 196, 79, 27);
		panel.add(curr);
		
		JButton changeAcc = new JButton("Change Account Currency");
		changeAcc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Changing account currency");
				String currency = curr.getSelectedItem().toString();
				Currency c = null;
				
				for (int i = 0; i < currencies.length; i++) {
					if (currency.equals(currencies[i].getAcronym())) {
						c = currencies[i];
					}
				}
				account.changeAccountCurrency(c);
				
				JPanel panelNew = accountOverview.init(frame, client, account);
				frame.setContentPane(panelNew);
				frame.revalidate();
				frame.repaint();
			}
		});
		
		changeAcc.setBounds(6, 196, 239, 29);
		panel.add(changeAcc);
		
		accID = new JTextField();
		accID.setText("Account ID");
		accID.setColumns(10);
		accID.setBounds(253, 232, 93, 26);
		panel.add(accID);
		
		tranAmount = new JTextField();
		tranAmount.setText("Amount");
		tranAmount.setColumns(10);
		tranAmount.setBounds(365, 232, 79, 26);
		panel.add(tranAmount);
		
		JButton transfer = new JButton("Transfer Money");
		transfer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Transfering money");
				String ID = accID.getText();
				double amount = Double.parseDouble(tranAmount.getText());
				Account send = client.getAccount(ID);
				account.transfer(send, amount, Date.getCurrentDate());
				
				JPanel panelNew = accountOverview.init(frame, client, account);
				frame.setContentPane(panelNew);
				frame.revalidate();
				frame.repaint();
			}
		});
		
		loanAmount = new JTextField();
		loanAmount.setText("Amount");
		loanAmount.setColumns(10);
		loanAmount.setBounds(253, 265, 93, 26);
		panel.add(loanAmount);
		
		transfer.setBounds(6, 230, 239, 29);
		panel.add(transfer);
		
		JButton loan = new JButton("Request Loan");
		loan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Asking for loan");
				double amount = Double.parseDouble(loanAmount.getText().toString());
				if (account.getBalance() > 5000) {
					account.makeTransaction("receipt", amount, "LOAN", Date.getCurrentDate());
				}
				else 
					System.out.println("LOAN DENIED. COLLATERAL INSUFFICIENT");
				
				JPanel panelNew = accountOverview.init(frame, client, account);
				frame.setContentPane(panelNew);
				frame.revalidate();
				frame.repaint();
			}
		});
		
		loan.setBounds(6, 265, 239, 29);
		panel.add(loan);
		
		
		JButton BACK = new JButton("BACK");
		BACK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Back");
				JPanel panelNew = clientDisplay.init(frame, client);
				frame.setContentPane(panelNew);
				frame.revalidate();
				frame.repaint();
			}
		});
		
		
		BACK.setBounds(365, 7, 79, 26);
		panel.add(BACK);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(new Color(0, 0, 0));
		separator_1.setBounds(6, 261, 436, 12);
		panel.add(separator_1);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setForeground(Color.BLACK);
		separator_2.setBounds(6, 227, 436, 12);
		panel.add(separator_2);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setForeground(Color.BLACK);
		separator_3.setBounds(6, 193, 436, 12);
		panel.add(separator_3);

		return panel;
	}
}
