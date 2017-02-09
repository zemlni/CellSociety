package user_interface;

import java.util.ArrayList;
import java.util.Map;

import javafx.geometry.Side;
import javafx.scene.Group;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

public class PopulationGraph extends Group {
	
	private final LineChart<Number, Number> lineChart;

	public PopulationGraph(int width) {
		final NumberAxis xAxis = new NumberAxis();
		final NumberAxis yAxis = new NumberAxis();
		yAxis.setLabel("%");
		lineChart = new LineChart<Number, Number>(xAxis, yAxis);
		lineChart.setAnimated(false);
		lineChart.setCreateSymbols(false);
		lineChart.setPrefWidth(width);
		lineChart.setPrefHeight(150);
		lineChart.setLegendSide(Side.RIGHT);
		getChildren().add(lineChart);
	}
	
	public void update(ArrayList<Map<String, Double>> data) {
		clear();
		for (String stateName : data.get(0).keySet()) {
			lineChart.getData().add(createSeries(stateName, data));
		}
	}
	
	public void clear() {
		lineChart.getData().removeAll(lineChart.getData());
	}

	private XYChart.Series<Number, Number> createSeries(String title, ArrayList<Map<String, Double>> data) {
		XYChart.Series<Number, Number> series = new XYChart.Series<Number, Number>();
		series.setName(title);
		int i = 0;
		for (Map<String, Double> step : data) {
			series.getData().add(new XYChart.Data<Number, Number>(i, step.get(title)));
			i++;
		}
		return series;
	}

}
