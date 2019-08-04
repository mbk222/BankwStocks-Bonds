import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;

public class clientLogin extends JPanel {
	private static JTextField loginField;
	private static JPasswordField passwordField;
	private static JButton loginB;
	private static JLabel please;
	
	public static JFrame testframe = new JFrame();
	
	public clientLogin() {
		testframe.setBounds(100, 100, 480, 330);
		testframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
				Client client = Database.findClient(user, pass1);
				System.out.println(Database.checkUserPass(user,pass1));
				JPanel panelNew = clientDisplay.init(frame, client);

				frame.setContentPane(panelNew);
				frame.revalidate();
				frame.repaint();
				
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
		
		return panel;
	}
}
