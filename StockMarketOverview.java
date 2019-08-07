
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

public class StockMarketOverview extends JPanel {
 
 public static JFrame testframe = new JFrame();
 public static Securities testaccount = new Securities();
 public static Client testclient = new Client("a","b","c");
 
 public StockMarketOverview() {
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
 
 

 /**
  * Create the panel.
  */
 public static JPanel init(JFrame frame, Client client, Securities account) {
  StockMarket.init(); // initiliazing stock market
  System.out.println("HERE STOCMKARKETOVERVIEW");
  double unrealizedStock = 0.0;
  double currPrice;
  double boughtPrice;
  
  for (Stock s : account.getStockList()) {
   System.out.println("here loop");
   boughtPrice = s.getBoughtP();
   System.out.println("Bought p: " + boughtPrice);
   currPrice = StockMarket.getStock(s.getTicker()).getPrice();
   System.out.println("Curr price: " + currPrice);
   unrealizedStock += (currPrice - boughtPrice) * s.getNumOfShares();
  }
  account.setUnrealizedTotal(Math.round(unrealizedStock * 100.00) / 100.00);
  
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
  
  JLabel transaction = new JLabel("Stock Market:"); // CHANGE
  transaction.setBounds(6, 37, 102, 20);
  panel.add(transaction);
  
  JPanel panelN = new JPanel();
  panelN.setBackground(new Color(255, 255, 255));
  panelN.setBounds(6, 55, 438, 90);
  panel.add(panelN);
  panelN.setLayout(null);
  
 // JLabel label = new JLabel(account.displayTransactions());
  JLabel label = new JLabel(StockMarket.displayMarket()); 
  label.setBounds(6, 0, 426, 90);
  panelN.add(label);
  
  tickerChart = new JTextField();
  tickerChart.setColumns(10);
  tickerChart.setBounds(211, 263, 40, 26);
  panel.add(tickerChart);
  
  JButton viewChart = new JButton("View Chart of Stock");
  viewChart.addActionListener(new ActionListener() {
   public void actionPerformed(ActionEvent e) {
    System.out.println("Viewing Stock Chart");
    String ID = tickerChart.getText();
    
    // GET CHART IN HERE
     for (Stock s : account.getStockList()) {
       if (s.getTicker().equals(ID)) {
         Chart stockChart = new Chart(s);
         stockChart.begin();
         break;
       }
     }
    
//    double amount = Double.parseDouble(tranAmount.getText());
//    Account send = client.getAccount(ID);
//    account.transfer(send, amount, Date.getCurrentDate());
    
    JPanel panelNew = securitiesOverview.init(frame, client, account);
    frame.setContentPane(panelNew);
    frame.revalidate();
    frame.repaint();
   }
  });
  
  viewChart.setBounds(6, 262, 149, 29);
  panel.add(viewChart);
  
  
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
  
  JButton buyStockButton = new JButton("Buy Stock");
  buyStockButton.addActionListener(new ActionListener() {
   public void actionPerformed(ActionEvent e) {
    System.out.println("BUYING STOCK");
    String ticker = buyTicker.getText();
    int shares = Integer.parseInt(txtOfShares.getText());
    System.out.println(ticker);
    System.out.println(shares);
    StockMarket.buyStock(StockMarket.getStock(ticker), shares, account, Date.getCurrentDate());
    
    JPanel panelNew = StockMarketOverview.init(frame, client, account);
    frame.setContentPane(panelNew);
    frame.revalidate();
    frame.repaint();

   }
  });
  
  buyStockButton.setBounds(211, 148, 112, 29);
  panel.add(buyStockButton);
  
  buyTicker = new JTextField();
  buyTicker.setColumns(10);
  buyTicker.setBounds(43, 149, 40, 26);
  panel.add(buyTicker);
  
  txtOfShares = new JTextField();
  txtOfShares.setColumns(10);
  txtOfShares.setBounds(153, 149, 39, 26);
  panel.add(txtOfShares);
  
  JLabel Ticker = new JLabel("Ticker"); 
  Ticker.setBounds(6, 152, 40, 20);
  panel.add(Ticker);
  
  JLabel lblOfShares = new JLabel("# of stocks");
  lblOfShares.setBounds(90, 153, 72, 20);
  panel.add(lblOfShares);
  
  JButton sellStockButton = new JButton("Sell Stock"); 
  sellStockButton.addActionListener(new ActionListener() {
   public void actionPerformed(ActionEvent e) {
    System.out.println("Selling stock");
    String ticker = buyTicker.getText();
    int shares = Integer.parseInt(txtOfShares.getText());
    StockMarket.sellStocks(StockMarket.getStock(ticker), shares, account, Date.getCurrentDate());
    
    JPanel panelNew = StockMarketOverview.init(frame, client, account);
    frame.setContentPane(panelNew);
    frame.revalidate();
    frame.repaint();
   }
  });
  sellStockButton.setBounds(332, 148, 112, 29);
  panel.add(sellStockButton);
  

  JLabel ownedStocks = new JLabel("Your Stocks:");
  ownedStocks.setBounds(7, 176, 102, 20);
  panel.add(ownedStocks);
  
  JPanel ownedStocksPanel = new JPanel();
  ownedStocksPanel.setLayout(null);
  ownedStocksPanel.setBackground(Color.WHITE);
  ownedStocksPanel.setBounds(6, 195, 438, 65);
  panel.add(ownedStocksPanel);
  
  JLabel ownedStocksLabel = new JLabel("");
  ownedStocksLabel = new JLabel(account.displayStockL());
 // ownedStocksLabel = new JLabel("<html>Corporate Bond | Maturity Date: 09/05/19<br/> Government Bond | Maturity Date: 11/05/19</html>");
  ownedStocksLabel.setBounds(6, 0, 426, 65);
  ownedStocksPanel.add(ownedStocksLabel);
  
  JLabel chartTicker = new JLabel("Ticker");
  chartTicker.setBounds(174, 266, 40, 20);
  panel.add(chartTicker);
  
  JLabel unrealized = new JLabel("Unrealized Profit:");
  unrealized.setBounds(273, 266, 102, 20);
  panel.add(unrealized);
  
  JLabel unrealizedTotal = new JLabel("" + account.getUnrealizedTotal());
  unrealizedTotal.setBounds(376, 266, 53, 20);
  panel.add(unrealizedTotal);

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
