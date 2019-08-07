import javax.swing.*;
import java.awt.Color;
import java.awt.event.*;
import java.util.List;

public class clientDisplay extends JPanel {

    public static Currency USD = new Currency("USD", "Dollars");
    public static Currency RUB = new Currency("RUB", "Rubles");
    public static Currency CNY = new Currency("CNY", "Yen");
    public static Currency GBP = new Currency("GBP", "Pounds");
    public static Currency[] currencies = {USD, RUB, CNY, GBP};

    private static JTextField accNum;

    private static JFrame testframe = new JFrame();
    private static Client testclient = new Client("a", "b", "c");

    public clientDisplay() {
        testframe.setBounds(100, 100, 480, 330);
        //testframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		testframe.addWindowListener(new WindowAdapter() {
//			@Override
//			public void windowClosing(WindowEvent e) {
//				System.out.println("WindowClosingDemo.windowClosing");
//				System.exit(0);
//			}
//		});
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
        //SqlFunc.init();
        List<Account> lst = SqlFunc.getAccountByCustomerId(client.getId());
        //SqlFunc.close();
        String res = "<html>";
        if (lst.size() == 0) {
            res += "You have no accounts open with us.";
        } else {
            for (int i = 0; i < lst.size(); i++) {
                res += lst.get(i).type + " " + lst.get(i).getID() + " | BALANCE: " + lst.get(i).getBalance() + " " + lst.get(i).getCurrency() + "<br/>";
            }
        }
        res += "</html>";
        JLabel summary = new JLabel(res);
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
        accTypes.setModel(new DefaultComboBoxModel(new String[]{"Checking", "Savings", "Securities"}));
        accTypes.setBounds(17, 134, 117, 20);
        panel.add(accTypes);

        JLabel currency = new JLabel("Currency");
        currency.setBounds(158, 111, 92, 20);
        panel.add(currency);

        JComboBox curr = new JComboBox();
        curr.setModel(new DefaultComboBoxModel(new String[]{"USD", "RUB", "CNY", "GBP"}));
        curr.setForeground(Color.BLACK);
        curr.setBounds(158, 134, 83, 20);
        panel.add(curr);


        openAccount.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                // create account
                String type = accTypes.getSelectedItem().toString();
                String currency = curr.getSelectedItem().toString();
                Currency c = null;
                Account a = null;

                for (int i = 0; i < currencies.length; i++) {
                    if (currency.equals(currencies[i].getAcronym())) {
                        c = currencies[i];
                    }
                }
                //SqlFunc.init();
                boolean openSec = true;
                if (type.equals("Securities")) {
                    List<Account> lst = SqlFunc.getAccountByCustomerId(client.getId());
                    openSec = false;
                    for (int i = 0; i < lst.size(); i++) {
                        if (lst.get(i).getCurrency().getAcronym().equals("USD")) {
                            if (lst.get(i).getBalance() >= 5000) // CHANGE THIS BACK TO 5000
                                openSec = true;
                        }
                    }
                    if (!openSec) {
                        JOptionPane.showMessageDialog(null, "One account must have at least 5000 USD", "ERROR", JOptionPane.ERROR_MESSAGE);
                    } else {
                        a = new Securities();
                        client.getAccounts().add(a);
                        SqlFunc.insertAccount(Integer.parseInt(a.getID()), client.getId(), 0, type, c, 0, 0);
                        System.out.println("Inserted");
                    }
                } else if (type.equals("Checking")) {
                    a = new Checking(0, c);
                    client.getAccounts().add(a);
                    SqlFunc.insertAccount(Integer.parseInt(a.getID()), client.getId(), 0, type, c, 0, 0);
                    System.out.println("Inserted");
                } else {
                    a = new Savings(0, c);
                    client.getAccounts().add(a);
                    SqlFunc.insertAccount(Integer.parseInt(a.getID()), client.getId(), 0, type, c, 0, 0);
                    System.out.println("Inserted");
                }
                //SqlFunc.close();

                JPanel panelNew = clientDisplay.init(frame, client);
                frame.setContentPane(panelNew);
                frame.revalidate();
                frame.repaint();
                // CHECKS IF SECURITIES ACCOUNT CAN BE OPENED
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

                Account account = SqlFunc.getAccountByAid(Integer.parseInt(ID));
                JPanel panelNew = null;
                // ACCOUNT NOT FOUND ERROR
                if (account == null) {
                    JOptionPane.showMessageDialog(null, "No such account!", "ERROR", JOptionPane.ERROR_MESSAGE);

                    panelNew = clientDisplay.init(frame, client);

                } else {

                    if (account.type().equals("Securities")) {
                        //panelNew = securitiesOverview.init(frame, client, (Securities) account);
                        Securities sc = new Securities(account.type,account.balance,account.currency,account.accountID);
                        panelNew = securitiesOverview.init(frame, client, sc);
                    } else
                        panelNew = accountOverview.init(frame, client, account);
                }
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
                SqlFunc.close();
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
