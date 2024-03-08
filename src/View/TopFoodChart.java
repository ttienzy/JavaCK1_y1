package View;

import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import Controller.SummaryManager;

public class TopFoodChart {

	    public static JFreeChart createChart() {
	        JFreeChart barChart = ChartFactory.createBarChart(
	                "TOP-SELLING FOODS",
	                "Food", "Consumption quantity",
	                createDataset(), PlotOrientation.VERTICAL, false, false, false);
	        return barChart;
	    }

	    public static CategoryDataset createDataset() {
	        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
	        SummaryManager sum = new SummaryManager();
	        dataset.addValue(Double.valueOf(sum.TopFood().get(0).get(1)),"Consumption quantity", sum.TopFood().get(0).get(0));
	        dataset.addValue(Double.valueOf(sum.TopFood().get(1).get(1)),"Consumption quantity", sum.TopFood().get(1).get(0));
	        dataset.addValue(Double.valueOf(sum.TopFood().get(2).get(1)),"Consumption quantity", sum.TopFood().get(2).get(0));
	        dataset.addValue(Double.valueOf(sum.TopFood().get(3).get(1)),"Consumption quantity", sum.TopFood().get(3).get(0));
	        dataset.addValue(Double.valueOf(sum.TopFood().get(4).get(1)),"Consumption quantity", sum.TopFood().get(4).get(0));	
	        return dataset;
	    }

	    public static void main(String[] args) {
	        ChartPanel chartPanel = new ChartPanel(createChart());
	        chartPanel.setPreferredSize(new java.awt.Dimension(560, 367));
	        JFrame frame = new JFrame();
	        frame.add(chartPanel);
	        frame.setTitle("");
	        frame.setSize(600, 400);
	        ImageIcon imgTitle =new ImageIcon(Toolkit.getDefaultToolkit().createImage(FrameFastFoodManagement.class.getResource("titlefood.png")));
	    	frame.setIconImage(imgTitle.getImage());
	        frame.setLocationRelativeTo(null);
	        frame.setResizable(false);
	        frame.setVisible(true);
	    }


}
