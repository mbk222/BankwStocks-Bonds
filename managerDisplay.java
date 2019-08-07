import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Component;

public class managerDisplay extends JPanel {

	private static JPanel panel = new JPanel();
	
	private static JFrame testframe = new JFrame();
	private static Manager testManager = new Manager("a","b","c");
	private static JTextField stockTickerTF;
	private static JTextField stockPriceTF;
	private static JTextField stockNameTF;
	private static JTextField bondNameTF;
	private static JTextField bondInterestTF;
	private static JTextField bondLengthTF;
	
	public managerDisplay() {
		testframe.setBounds(100, 100, 480, 330);
		testframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		init(testframe, testManager);
	}
	
	public static JPanel init(JFrame frame, Manager manager) {
		panel.setLayout(null);
		
		JLabel lblPleaseChooseWhat = new JLabel("Please choose what you would like to do:");
		lblPleaseChooseWhat.setHorizontalAlignment(SwingConstants.CENTER);
		lblPleaseChooseWhat.setBounds(95, 44, 248, 20);
		panel.add(lblPleaseChooseWhat);
		
		JButton checkClient = new JButton("Check Client");
		checkClient.setBounds(323, 141, 117, 29);
		
		
		JButton getReport = new JButton("Daily Report");
		getReport.setBounds(323, 220, 117, 29);
		
		JButton back = new JButton("Back");
		back.setBounds(323, 260, 117, 29);
		
		
		
		class reportListener implements ActionListener {
			public void actionPerformed(ActionEvent e ) {
				System.out.println("Getting Daily Report");
				
				JLabel phrase = new JLabel("DAILY REPORT FOR " + Date.getCurrentDate().toString());
				JLabel dailyReport = new JLabel( "<html>"+ manager.getDailyReport(Date.getCurrentDate()) + "</html>");
				JPanel reportPanel = new JPanel();
				
				reportPanel.add(phrase);
				reportPanel.add(dailyReport);
				JButton back = new JButton("Back");
				
				class backListener implements ActionListener {
					public void actionPerformed(ActionEvent e ) {
						JPanel panel_New = managerDisplay.init(frame, manager);
						
						frame.setContentPane(panel_New);
						frame.revalidate();
						frame.repaint();
					}
				}
				
				backListener backL = new backListener();
				back.addActionListener(backL);
				
				reportPanel.add(back);
				frame.setContentPane(reportPanel);
				frame.revalidate();
				frame.repaint();

			}
		}
		
		
		class backListener implements ActionListener {
			public void actionPerformed(ActionEvent e ) {
				System.out.println("BACK");
				JPanel panelNew = GUI.initialize(frame);
				frame.setContentPane(panelNew);
				frame.revalidate();
				frame.repaint();
			}
		}
		
		
		class checkListener implements ActionListener {
			public void actionPerformed(ActionEvent e ) {
				System.out.println("CHECK");
				JPanel panelNew = managerCheckClient.init(frame, manager);
				frame.setContentPane(panelNew);
				frame.revalidate();
				frame.repaint();
			}
		}
		
		
		reportListener reportL = new reportListener();
		getReport.addActionListener(reportL);
		
		backListener backL = new backListener();
		back.addActionListener(backL);
		
		checkListener checkL = new checkListener();
		checkClient.addActionListener(checkL);
		
		
		panel.add(getReport);
		panel.add(checkClient);
		panel.add(back);
		
		JButton viewDebt = new JButton("View Debtors");
		class debtListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Checking debtors");
				JFrame debt = new JFrame();
				JPanel debtPanel = new JPanel();
				JLabel debtLabel = new JLabel("<html>" + Database.getInDebt() + "</html>");
				
				debtPanel.add(debtLabel);
				debt.getContentPane().add(debtPanel);
				
				debt.setTitle("DEBTORS");
				debt.setSize( 250, 75 );
				debt.setLocation( 250, 200 );
				debt.setVisible( true );
			}
		}
		
		debtListener debtL = new debtListener();
		viewDebt.addActionListener(debtL);
		
		viewDebt.setBounds(323, 181, 117, 29);
		panel.add(viewDebt);
		
		JButton btnRemoveStock = new JButton("Remove Stock");
		btnRemoveStock.setBounds(10, 260, 127, 29);
		panel.add(btnRemoveStock);
		
		stockTickerTF = new JTextField();
		stockTickerTF.setBounds(54, 167, 83, 20);
		panel.add(stockTickerTF);
		stockTickerTF.setColumns(10);
		
		stockPriceTF = new JTextField();
		stockPriceTF.setColumns(10);
		stockPriceTF.setBounds(54, 198, 83, 20);
		panel.add(stockPriceTF);
		
		stockNameTF = new JTextField();
		stockNameTF.setColumns(10);
		stockNameTF.setBounds(54, 136, 83, 20);
		panel.add(stockNameTF);
		
		JButton btnAddStock = new JButton("Add Stock");
		btnAddStock.setBounds(10, 225, 127, 29);
		panel.add(btnAddStock);
		
		bondNameTF = new JTextField();
		bondNameTF.setColumns(10);
		bondNameTF.setBounds(215, 136, 73, 20);
		panel.add(bondNameTF);
		
		bondInterestTF = new JTextField();
		bondInterestTF.setColumns(10);
		bondInterestTF.setBounds(215, 167, 73, 20);
		panel.add(bondInterestTF);
		
		bondLengthTF = new JTextField();
		bondLengthTF.setColumns(10);
		bondLengthTF.setBounds(215, 198, 73, 20);
		panel.add(bondLengthTF);
		
		JButton addBond = new JButton("Add Bond");
//		class buyBondListener implements ActionListener {
//			public void ActionPerformed(ActionEvent e) {
//				System.out.println("Adding Bond");
//			}
//		}
	
		addBond.setBounds(170, 225, 117, 29);
		panel.add(addBond);
		
		JButton btnRemoveBond = new JButton("Remove Bond");
		btnRemoveBond.setBounds(170, 260, 117, 29);
		panel.add(btnRemoveBond);
		
		JLabel stockName = new JLabel("name");
		stockName.setHorizontalAlignment(SwingConstants.CENTER);
		stockName.setBounds(0, 136, 52, 20);
		panel.add(stockName);
		
		JLabel stockTicker = new JLabel("ticker");
		stockTicker.setHorizontalAlignment(SwingConstants.CENTER);
		stockTicker.setBounds(0, 167, 52, 20);
		panel.add(stockTicker);
		
		JLabel stockPrice = new JLabel("price");
		stockPrice.setHorizontalAlignment(SwingConstants.CENTER);
		stockPrice.setBounds(0, 198, 52, 20);
		panel.add(stockPrice);
		
		JLabel bondName = new JLabel("name");
		bondName.setHorizontalAlignment(SwingConstants.CENTER);
		bondName.setBounds(153, 136, 52, 20);
		panel.add(bondName);
		
		JLabel bondInterest = new JLabel("interest");
		bondInterest.setHorizontalAlignment(SwingConstants.CENTER);
		bondInterest.setBounds(153, 167, 52, 20);
		panel.add(bondInterest);
		
		JLabel length = new JLabel("length");
		length.setHorizontalAlignment(SwingConstants.CENTER);
		length.setBounds(153, 198, 52, 20);
		panel.add(length);
		
		JLabel lblStockOptions = new JLabel("Stock Options");
		lblStockOptions.setHorizontalAlignment(SwingConstants.CENTER);
		lblStockOptions.setBounds(10, 105, 117, 20);
		panel.add(lblStockOptions);
		
		JLabel lblBondOptions = new JLabel("Bond Options");
		lblBondOptions.setHorizontalAlignment(SwingConstants.CENTER);
		lblBondOptions.setBounds(161, 105, 117, 20);
		panel.add(lblBondOptions);
		
		return panel;
	}
}
