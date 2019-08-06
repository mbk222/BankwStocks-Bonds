import java.util.ArrayList;

public class Securities extends Account {
 //private Currency USD = new Currency("USD", "Dollars");
 private ArrayList<Stock> stocks = new ArrayList<Stock>();
 private ArrayList<Bond> bonds = new ArrayList<Bond>();
 private double unrealizedTotal;
 
 public Securities() {
  super();
  type = "Securities";
 }
 
 public ArrayList<Stock> getStockList() {
  return stocks;
 }
 
 public ArrayList<Bond> getBondList() {
  return bonds;
 }
 
 public double getUnrealizedTotal() {
   double unrealizedStockAmnt = 0.0;
   
   for (Stock s : this.stocks) {
     double currPrice = s.getPrice();
     // s. will get the bought price
     // unrealizedStockAmnt += (currPrice - boughtPrice)
   }
   
   double unrealizedBondAmnt = 0.0;
   for (Bond b : this.bonds) {
     // unrealizedBondAmnt += b.getCost() * b.getInterest();
     // it's going to be bond cost * interest; ALWAYS
   }
 // this.unrealized = unrealizedStockAmnt + unrealizedBondAmnt;
 }
 
 public String displayStockL() {
  String res = "<html>";
  for (int i = 0; i < stocks.size(); i++) {
   res += stocks.get(i).numOfShares() + " share(s) of " + stocks.get(i).getTicker() + "<br/>"; 
  }
  return res + "</html>";
 }
 
 public static void main(String[] args) {
  // TODO Auto-generated method stub

 }

}
