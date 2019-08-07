import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class managerCheckClient extends JPanel {
	private static JTextField fnameField;
	private static JTextField mnameField;
	private static JTextField lnameField;
	private static JPanel panel = new JPanel();
	
//	private static JFrame testframe = new JFrame();
//	private static Manager testmanager = new Manager("a","b","c");
//	
//	public managerCheckClient() {
//		testframe.setBounds(100, 100, 480, 330);
//		testframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		init(testframe, testmanager);
//	}

	public static JPanel init(JFrame frame, Manager manager) {
		panel.setLayout(null);
		
		JLabel please = new JLabel("Please input the information of the client you are looking for:");
		please.setBounds(52, 67, 371, 16);
		please.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(please);
		
		JLabel fname = new JLabel("First Name");
		fname.setBounds(130, 105, 100, 20);
		panel.add(fname);
		
		JLabel mname = new JLabel("Middle Name");
		mname.setBounds(130, 136, 100, 20);
		panel.add(mname);
		
		JLabel lname = new JLabel("Last Name");
		lname.setBounds(130, 167, 100, 20);
		panel.add(lname);
		
		fnameField = new JTextField();
		fnameField.setBounds(209, 105, 130, 20);
		panel.add(fnameField);
		fnameField.setColumns(10);
		
		mnameField = new JTextField();
		mnameField.setColumns(10);
		mnameField.setBounds(209, 136, 130, 20);
		panel.add(mnameField);
		
		lnameField = new JTextField();
		lnameField.setColumns(10);
		lnameField.setBounds(209, 167, 130, 20);
		panel.add(lnameField);
		
		JButton check = new JButton("FIND CLIENT");
		check.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Finding client");
				String firstN = fnameField.getText();
				String middleN = mnameField.getText();
				String lastN = lnameField.getText();
			//	String loginN = loginField.getText();
				
				fnameField.setText("");
				mnameField.setText("");
				lnameField.setText("");
				
				Client found = Database.findClient(firstN, middleN, lastN);
				
				JLabel checkClient = new JLabel( "<html>" +  manager.checkClient(found) + "</html>");
				JPanel clientPanel = new JPanel();
				
				clientPanel.add(checkClient);
				
				
				JButton back = new JButton("Back");
				
				class backListener implements ActionListener {
					public void actionPerformed(ActionEvent e ) {
						JPanel panel_New = managerDisplay.init(frame, manager);
						
						clientPanel.removeAll();
						
						frame.setContentPane(panel_New);
						frame.revalidate();
						frame.repaint();
					}
				}
				
				backListener backL = new backListener();
				back.addActionListener(backL);
				
				clientPanel.add(back);
			
				panel.removeAll();
				
				frame.setContentPane(clientPanel);
				frame.revalidate();
				frame.repaint();

			}
		});
		
		
//		JButton back = new JButton("Back");
//		
//		class backListener implements ActionListener {
//			public void actionPerformed(ActionEvent e ) {
//				JPanel panel_New = managerDisplay.init(frame, manager);
//				
//				frame.setContentPane(panel_New);
//				frame.revalidate();
//				frame.repaint();
//			}
//		}
//		
//		backListener backL = new backListener();
//		back.addActionListener(backL);
//	//	clientPanel.add(back);
//		
		
		check.setBounds(156, 208, 117, 29);
		panel.add(check);
		
		JButton backButton = new JButton("BACK");
		class backListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				JPanel panel_New = managerDisplay.init(frame, manager);
				
			}
		}
		backListener backL = new backListener();
		backButton.addActionListener(backL);
		
		backButton.setBounds(156, 242, 117, 29);
		panel.add(backButton);
		return panel;

	}
}
