import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.DefaultComboBoxModel;
import java.awt.Color;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class clientDisplay extends JPanel {
	
	public static Currency USD = new Currency("USD", "Dollars");
	public static Currency RUB = new Currency("RUB", "Rubles");
	public static Currency CNY = new Currency("CNY", "Yen");
	public static Currency GBP = new Currency("GBP", "Pounds");
	public static Currency[] currencies = {USD,RUB,CNY,GBP};
	
	private static JTextField accNum;
	
	private static JFrame testframe = new JFrame();
	private static Client testclient = new Client("a","b","c");
	
	public clientDisplay() {
		testframe.setBounds(100, 100, 480, 330);
		testframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		init(testframe, testclient);
	}

	/**
	 * Create the panel.
	 */
	public static JPanel init(JFrame frame, Client client) {
		JPanel panel = new JPanel();
		panel.setLayout(null);
		
//		JLabel overview = new JLabel("Client Overview:");
//		overview.setBounds(17, 18, 107, 20);
//		panel.add(overview);
		
		JLabel summary = new JLabel(client.viewAccounts());
		summary.setBounds(17, 16, 395, 60);
		panel.add(summary);
		
		JButton openAccount = new JButton("OPEN ACCOUNT");
		
		
		openAccount.setBounds(253, 130, 159, 29);
		panel.add(openAccount);
		
		JLabel please = new JLabel("Please fill in the information below to open an account.");
		please.setBounds(17, 80, 395, 20);
		panel.add(please);
		
		JLabel accType = new JLabel("Account Type");
		accType.setBounds(17, 111, 92, 20);
		panel.add(accType);
		
		JComboBox accTypes = new JComboBox();
		accTypes.setForeground(new Color(0, 0, 0));
		accTypes.setModel(new DefaultComboBoxModel(new String[] {"Checking", "Savings", "Securities"}));
		accTypes.setBounds(17, 134, 117, 20);
		panel.add(accTypes);
		
		JLabel currency = new JLabel("Currency");
		currency.setBounds(158, 111, 92, 20);
		panel.add(currency);
		
		JComboBox curr = new JComboBox();
		curr.setModel(new DefaultComboBoxModel(new String[] {"USD", "RUB", "CNY", "GBP"}));
		curr.setForeground(Color.BLACK);
		curr.setBounds(158, 134, 83, 20);
		panel.add(curr);
		
		
		openAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// create account
				String type = accTypes.getSelectedItem().toString();
				String currency = curr.getSelectedItem().toString();
				Currency c = null;
				
				for (int i = 0; i < currencies.length; i++) {
					if (currency.equals(currencies[i].getAcronym())) {
						c = currencies[i];
					}
				}
				if (type.equals("Securities")) {
					client.openTypeAccount(type, 0, USD);
				}
				else {
				client.openTypeAccount(type, 0, c);
				}
				// call clientOverview again
				JPanel panelNew = clientDisplay.init(frame, client);
				frame.setContentPane(panelNew);
				frame.revalidate();
				frame.repaint();
			}
		});
		
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.BLACK);
		separator.setBounds(17, 177, 411, 12);
		panel.add(separator);
		
		JLabel view = new JLabel("Please enter the ID of the account you would like to access.");
		view.setBounds(17, 201, 395, 20);
		panel.add(view);
		
		
		accNum = new JTextField();
		accNum.setBounds(17, 233, 100, 20);
		panel.add(accNum);
		accNum.setColumns(10);
		
		JButton viewAcc = new JButton("VIEW ACCOUNT");
		
		viewAcc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("account overview of");
				String ID = accNum.getText();
				Account account = client.getAccount(ID);
				
				// use this when error checking:
//				JFrame error = new JFrame();
//				error.setTitle("ERROR");
//				error.setSize( 250, 250 );
//				error.setLocation( 300, 100 );
//				error.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
//				error.setVisible( true );
				JPanel panelNew = null;
				if (account.type().equals("Securities")) {
					panelNew = securitiesOverview.init(frame, client, (Securities) account);
				}
				else 
					panelNew = accountOverview.init(frame, client, account);
				frame.setContentPane(panelNew);
				frame.revalidate();
				frame.repaint();
			}
		});
		
		
		viewAcc.setBounds(124, 230, 159, 29);
		panel.add(viewAcc);
		
		JButton btnLogOut = new JButton("LOG OUT");
		
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Log out");
				System.out.println("BACK");
				JPanel panelNew = GUI.initialize(frame);
				frame.setContentPane(panelNew);
				frame.revalidate();
				frame.repaint();
			}
		});
		
		
		btnLogOut.setBounds(327, 265, 117, 29);
		panel.add(btnLogOut);
		
		
		panel.add(accNum);
		accNum.setColumns(10);

		return panel;
	}
}
