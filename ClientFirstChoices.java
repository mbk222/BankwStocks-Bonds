import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;

public class ClientFirstChoices extends JPanel {
	public static JFrame testframe = new JFrame();
	
	public ClientFirstChoices() {
		testframe.setBounds(100, 100, 480, 330);
		testframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		init(testframe);
	}
	
	public static JPanel init(JFrame frame) {
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panelNew = new JPanel();
		panel.add(panelNew, BorderLayout.SOUTH);
		
		
		class loginListener implements ActionListener {
			public void actionPerformed(ActionEvent e ) {
				System.out.println("LOGIN");
				JPanel panel_New = clientLogin.init(frame);
				panel.removeAll();
				panel.add(panel_New);
				panel.revalidate();
				panel.repaint();
			}
		}
		
		class signUpListener implements ActionListener {
			public void actionPerformed(ActionEvent e ) {
				System.out.println("SIGN UP");
				JPanel panelNew = clientCreate.init(frame);
				frame.setContentPane(panelNew);
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
		
		loginListener loglis = new loginListener();
		
		signUpListener sulis = new signUpListener();
		
		backListener backlis = new backListener();
		
		
		JButton login = new JButton("LOGIN");
		panelNew.add(login);
		login.addActionListener(loglis);
		JButton signUp = new JButton("SIGN UP");
		panelNew.add(signUp);
		JButton back = new JButton("BACK");
		panelNew.add(back);
		back.addActionListener(backlis);
		signUp.addActionListener(sulis);
		
		JLabel lblNewLabel = new JLabel("Please login into your account or sign up.");
		panel.add(lblNewLabel, BorderLayout.CENTER);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		return panel;
	}
	
	public static void main(String[] args) {
		ClientFirstChoices test = new ClientFirstChoices();
		init(testframe);
	}

}
