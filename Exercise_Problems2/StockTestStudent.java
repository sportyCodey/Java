//creates the Stock class and tests it in the main

public class StockTestStudent {
  public static void main(String[] args) {
	  //stock1 has name "AAPL", purchase price of 357.98, quantity 45
      Stock stock1 = new Stock("AAPL", 357.98, 45);

	  //stock2 has name "BCBB", purchase price is 2.35, quantity 100
      Stock stock2 = new Stock("BCBB", 2.35, 100);

	  //For stock1, Print out information nicely formatted output, use the
	  //toString method
	  System.out.println(stock1.toString());

      //For stock2, print out information on the stock.use the
	  //toString method
	  System.out.println(stock2.toString());

      //market day closes, update current marketPrice to 402.30 for stock1
      // and 1.75 for stock2.
	  stock1.setCurrentMarketPrice(402.30);
	  stock2.setCurrentMarketPrice(1.75);

	  //Print out the current amount earned since purchase of all shares of stock1
	  System.out.println("AAPL is worth $" + (stock1.getCurrentMarketPrice() - stock1.getPurchasePrice()) * stock1.getQty_shares());


  }  //end main
}  //end StockTest1

class Stock {
	private String name;
	private double purchasePrice;
	private int qtyShares;
	private double currentMarketPrice;

	public Stock(String name, double purchasePrice, int qty) {
		this.name = name;
		this.purchasePrice = purchasePrice;
		this.qtyShares = qty;
	}

	public String getName(){
		return name;
	}

	public double getPurchasePrice(){
		return purchasePrice;
	}

	public int getQty_shares(){
		return qtyShares;
	}

	public double getCurrentMarketPrice() {
		return currentMarketPrice;
	}

	public void setCurrentMarketPrice(double newPrice){
		currentMarketPrice = newPrice;
	}

	public void sellShares(double amount){
		if((qtyShares - amount) > 0)
			qtyShares -= amount;
	}

	public String toString(){
		return name + " purchase price " + purchasePrice +
		" quantity " + qtyShares;
	}

}

