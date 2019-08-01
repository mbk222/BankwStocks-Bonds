import java.util.*;

public class Securities extends Account {
  private double unrealizedTotal;
  //this.balance from the superclass is going to be the realizedtotal
  // allows us to use check balance and view balance
  private List<Bond> bonds;
  private List<Stock> stocks;
  
  public Securities() {
    this.name = "Securities";
    this.bonds = new LinkedList<Bond>();
    this.stocks = new LinkedList<Stock>();
    this.unrealizedTotal = 0;
  }
  
  /* setter for unrealized total */
  public void setUnrealized(double amount) {
    this.unrealizedTotal = amount;
  }
  
  /* getter for unrealized total */
  public double getUnrealized() {
    return this.unrealizedTotal;
  }
  
  /* setter for the bonds */
  public void setBonds(List<Bond> newBonds) {
    this.bonds = newBonds;
  }
  
  /* add to bonds */
  public void addBond(Bond newBond) {
    this.bonds.add(newBond);
  }
  
  /* add many bonds to bonds */
  public void addBonds(List<Bond> newBonds) {
    this.bonds.addAll(newBonds);
  }
  
  /* getter for the bonds */
  public List<Bond> getBonds() {
    return this.bonds;
  }
  
  
    /* setter for the stocks */
  public void setStocks(List<Stock> newStocks) {
    this.stocks = newStocks;
  }
  
  /* add to bonds */
  public void addStock(Stock newStock) {
    this.stocks.add(newStock);
  }
  
  /* add many bonds to bonds */
  public void addStocks(List<Stock> newStocks) {
    this.stocks.addAll(newStocks);
  }
  
  /* getter for the bonds */
  public List<Stock> getStocks() {
    return this.stocks;
  }
  
}
