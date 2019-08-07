import java.awt.Dimension;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import java.util.HashMap;
import java.util.Map;

public class Chart extends javax.swing.JFrame { // only applicable for market stocks

    public Chart(Stock s) {
      //beg StockPrice curr stock price date difference
        XYSeries Prices = new XYSeries("Value");
        Date current = new Date();
        HashMap<Date, String> history = s.getPriceHistory();
        
        Prices.add(0, s.getPrice()); // purchased 
        double dateFromBegin = 1;
      for (Map.Entry<Date,String> entry : history.entrySet()) {
        Prices.add(dateFromBegin, Double.parseDouble(entry.getValue()));
        dateFromBegin += 1;
      }
      
        XYDataset xyDataset = new XYSeriesCollection(Prices);
        JFreeChart chart = ChartFactory.createXYLineChart(
            s.getName() + " Price Trend", "Days Since Purchase", "Stock Price", //let the title be the stock.name price over time
            xyDataset, PlotOrientation.VERTICAL, true, true, false);
        ChartPanel cp = new ChartPanel(chart) {

            @Override
            public Dimension getPreferredSize() {
                return new Dimension(320, 240);
            }
        };
        cp.setMouseWheelEnabled(true);
        add(cp);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
    }

    public void begin() {
      this.setVisible(true);
//
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//              Stock stock = new Stock();
//                new ChartTest(stock).setVisible(true);
//            }
//        });
    }
}