package gui;

import org.jfree.chart.ChartFactory; 
import org.jfree.chart.ChartPanel; 
import org.jfree.chart.JFreeChart; 
import org.jfree.chart.plot.PlotOrientation; 
import org.jfree.data.xy.XYSeries; 
import org.jfree.data.xy.XYSeriesCollection; 
import org.jfree.ui.ApplicationFrame; 
import org.jfree.ui.RefineryUtilities;

import hashing.*;

public class OutputGeneration extends ApplicationFrame { 

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public OutputGeneration(String title, String subtitle, XYSeries... series) { 

		super(title);
		

		

		XYSeriesCollection dataset = new XYSeriesCollection(); 

		for(XYSeries s : series) {
			dataset.addSeries(s);
		}
		
		
		JFreeChart chart = ChartFactory.createScatterPlot( 
				"Time analysis over repeated calls to "+subtitle, // title 
				"Insertion #", "Time", // axis labels 
				dataset, // dataset 
				PlotOrientation.VERTICAL, 
				true, // legend? yes 
				true, // tooltips? yes 
				false // URLs? no 
				); 
		ChartPanel chartPanel = new ChartPanel(chart); 
		chartPanel.setPreferredSize(new java.awt.Dimension(500, 270)); 
		setContentPane(chartPanel); 

	} 

	public static void main(String[] args) {
		ExperimentGenerator exp = new ExperimentGenerator();
		
		XYSeries linearinsert = new XYSeries("Linear Probing");
		XYSeries quadraticinsert = new XYSeries("Quadratic Probing");
		XYSeries exponentialinsert = new XYSeries("Exponential Probing");
		
		XYSeries linearsearchS = new XYSeries("Linear Probing");
		XYSeries quadraticsearchS = new XYSeries("Quadratic Probing");
		XYSeries exponentialsearchS = new XYSeries("Exponential Probing");
		
		XYSeries linearsearchF = new XYSeries("Linear Probing");
		XYSeries quadraticsearchF = new XYSeries("Quadratic Probing");
		XYSeries exponentialsearchF = new XYSeries("Exponential Probing");
		
		exp.performExperiment(linearinsert, linearsearchS,linearsearchF, new LinearHashing<Integer>(.5));
		exp.performExperiment(quadraticinsert, quadraticsearchS,quadraticsearchF, new QuadraticHashing<Integer>(.5));
		exp.performExperiment(exponentialinsert, exponentialsearchS, exponentialsearchF, new ExponentialHashing<Integer>(.5));
		
		
		
		
		OutputGeneration insert = new OutputGeneration("Insertion Time Plot", "insert", linearinsert,quadraticinsert,exponentialinsert); 
		insert.pack(); 
		RefineryUtilities.centerFrameOnScreen(insert); 
		insert.setVisible(true); 
		
		OutputGeneration searchS = new OutputGeneration("Search Success Time Plot", "search", linearsearchS, quadraticsearchS, exponentialsearchS); 
		searchS.pack(); 
		RefineryUtilities.centerFrameOnScreen(searchS); 
		searchS.setVisible(true); 
		
		OutputGeneration insert2 = new OutputGeneration("Insertion Time Plot without Exponential", "insert", linearinsert,quadraticinsert); 
		insert2.pack(); 
		RefineryUtilities.centerFrameOnScreen(insert2); 
		insert2.setVisible(true); 
		
		OutputGeneration search2 = new OutputGeneration("Search Success Time Plot without Exponential", "search", linearsearchS,quadraticsearchS); 
		search2.pack(); 
		RefineryUtilities.centerFrameOnScreen(search2); 
		search2.setVisible(true); 
		
		OutputGeneration searchF = new OutputGeneration("Search Failure Time Plot", "search", linearsearchF, quadraticsearchF, exponentialsearchF); 
		searchF.pack(); 
		RefineryUtilities.centerFrameOnScreen(searchF); 
		searchF.setVisible(true); 
		
		OutputGeneration searchF2 = new OutputGeneration("Search Failure Time Plot without Exponential", "search", linearsearchF,quadraticsearchF); 
		searchF2.pack(); 
		RefineryUtilities.centerFrameOnScreen(searchF2); 
		searchF2.setVisible(true);
	}
	
	
	

}