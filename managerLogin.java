import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class managerLogin extends JPanel {
	public managerLogin() {
		testframe.setBounds(100, 100, 480, 330);
		testframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		init(testframe);
	}
	
	private static Manager manager = new Manager("John", "Doe", "money"); // SET MANAGER USER INFO HERE
	
	private static JTextField loginField;
	private static JPasswordField passwordField;
	private static JButton loginB;
	private static JLabel please;

	public static JFrame testframe = new JFrame();
	
	public static JPanel init(JFrame frame) {
		
		
		manager.setPassword("1234");
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		
		JLabel login = new JLabel("Login");
		login.setBounds(120, 99, 120, 20);
		panel.add(login);
		
		loginField = new JTextField();
		loginField.setBounds(199, 99, 130, 26);
		panel.add(loginField);
		loginField.setColumns(10);
		
		JLabel password = new JLabel("Password");
		password.setBounds(120, 137, 120, 26);
		panel.add(password);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(199, 137, 130, 26);
		panel.add(passwordField);
		
		loginB = new JButton("LOGIN");
		loginB.setBounds(161, 182, 117, 29);
		
		
		class loginListener implements ActionListener {
			public void actionPerformed(ActionEvent e ) {
				
				String user = loginField.getText();
				String pass = new String(passwordField.getPassword());
				
				
				if (manager.checkPassword( pass ) && manager.checkLogin(user)) {
					System.out.println("Manager Logged In");
					JPanel panelNew = managerDisplay.init(frame, manager);
					frame.setContentPane(panelNew);
					frame.revalidate();
					frame.repaint();
				}
				else {
					System.out.println("Manager wrong password");
					
					JFrame error = new JFrame();
					JPanel errorPanel = new JPanel();
					
					JLabel label = new JLabel("Invalid Login and Password");
					
					errorPanel.add(label);
					error.add(errorPanel);
					error.setTitle("ERROR");
					error.setSize( 250, 75 );
					error.setLocation( 250, 200 );
					error.setVisible( true );
					
					JPanel panelNew = managerLogin.init(frame);
					frame.setContentPane(panelNew);
					frame.revalidate();
					frame.repaint();
				}
			}
		}
		
		loginListener loginL = new loginListener();
		loginB.addActionListener(loginL);
		
		
		panel.add(loginB);
		
		please = new JLabel("Please input your login information");
		please.setHorizontalAlignment(SwingConstants.CENTER);
		please.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		please.setBounds(83, 55, 289, 29);
		panel.add(please);
		
		return panel;
	}
	
}
