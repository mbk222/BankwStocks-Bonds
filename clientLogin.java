import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class clientLogin extends JPanel {
	private static JTextField loginField;
	private static JPasswordField passwordField;
	private static JButton loginB;
	private static JLabel please;
	
	public static JFrame testframe = new JFrame();
	private static JButton backButton;
	
	public clientLogin() {
		testframe.setBounds(100, 100, 480, 330);
		//testframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		testframe.addWindowListener(new WindowAdapter() {
//			@Override
//			public void windowClosing(WindowEvent e) {
//				System.out.println("WindowClosingDemo.windowClosing");
//				System.exit(0);
//			}
//		});
		init(testframe);
	}
	
	
	public static JPanel init(JFrame frame) {
		JPanel panel = new JPanel();
		panel.setLayout(null);
		
		JLabel login = new JLabel("Username");
		login.setBounds(120, 99, 120, 20);
		panel.add(login);
		
		loginField = new JTextField();
		loginField.setBounds(182, 99, 130, 20);
		panel.add(loginField);
		loginField.setColumns(10);
		
		JLabel password = new JLabel("Password");
		password.setBounds(120, 137, 120, 20);
		panel.add(password);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(182, 137, 130, 20);
		panel.add(passwordField);
		
		loginB = new JButton("LOGIN");
		loginB.setBounds(157, 179, 117, 29);
		
		
		class loginListener implements ActionListener {
			public void actionPerformed(ActionEvent e ) {
				String user = loginField.getText();
				String pass1 = passwordField.getText();

				//SqlFunc.init();
				Client client = SqlFunc.getCustomerByUsername(user);
				if(client == null){
					JOptionPane.showMessageDialog(null, "No such user!", "ERROR",JOptionPane.ERROR_MESSAGE);

				}else{
					if(!client.checkPassword(pass1)){
						JOptionPane.showMessageDialog(null, "Wrong password!", "ERROR",JOptionPane.ERROR_MESSAGE);
					}else{
						JPanel panelNew = clientDisplay.init(frame, client);

						frame.setContentPane(panelNew);
						frame.revalidate();
						frame.repaint();
					}
				}
				//SqlFunc.close();


				// INVALID LOGIN ERROR
//				if (Database.findClient(user, pass1) == null) {
//					JFrame error = new JFrame();
//					JPanel errorPanel = new JPanel();
//
//					JLabel label = new JLabel("Invalid Username and Password");
//
//					errorPanel.add(label);
//					error.add(errorPanel);
//
//					error.setTitle("ERROR");
//					error.setSize( 250, 75 );
//					error.setLocation( 250, 200 );
//				//	error.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
//					error.setVisible( true );
//
////					JPanel panelNew = clientDisplay.init(frame, client);
////					frame.setContentPane(panelNew);
////					frame.revalidate();
////					frame.repaint();
//				}
//				else {
//					Client client = Database.findClient(user, pass1);
//					System.out.println(Database.checkUserPass(user,pass1));
//					JPanel panelNew = clientDisplay.init(frame, client);
//
//					frame.setContentPane(panelNew);
//					frame.revalidate();
//					frame.repaint();
//				}
				
			}
		}
		
		loginListener loginL = new loginListener();
		loginB.addActionListener(loginL);
		
		
		panel.add(loginB);
		
		please = new JLabel("Please enter your login information");
		please.setHorizontalAlignment(SwingConstants.CENTER);
		please.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		please.setBounds(82, 49, 289, 33);
		panel.add(please);
		
		backButton = new JButton("BACK");
		backButton.setBounds(157, 219, 117, 29);
		
		class backListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Back");
				JPanel panelNew = ClientFirstChoices.init(frame);

				frame.setContentPane(panelNew);
				frame.revalidate();
				frame.repaint();
			}
		}
		backListener backL = new backListener();
		backButton.addActionListener(backL);

		panel.add(backButton);
		
		return panel;
	}
}
