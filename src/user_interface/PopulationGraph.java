package user_interface;

import java.util.ArrayList;
import java.util.Map;

import javafx.geometry.Insets;
import javafx.geometry.Side;
import javafx.scene.Group;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;

public class PopulationGraph extends Group {
	
	private final LineChart<Number, Number> lineChart;

	public PopulationGraph(int width, int height) {
		final NumberAxis xAxis = new NumberAxis();
		final NumberAxis yAxis = new NumberAxis();
		yAxis.setLabel("%");
		lineChart = new LineChart<Number, Number>(xAxis, yAxis);
		lineChart.setTitle("Population Evolution");
		lineChart.setAnimated(false);
		lineChart.setCreateSymbols(false);
		lineChart.setPrefWidth(width);
		lineChart.setPrefHeight(height);
		lineChart.setPadding(new Insets(20, 20, 20, 0));
		lineChart.setLegendSide(Side.RIGHT);
		getChildren().add(lineChart);
	}
	
	public void update(ArrayList<ArrayList<Map<String, Double>>> data) {
		clear();
		for (ArrayList<Map<String, Double>> gameData: data) {
			if (gameData.size() > 0) {
				for (String stateName : gameData.get(0).keySet()) {
					lineChart.getData().add(createSeries(data.indexOf(gameData) + 1, stateName, gameData));
				}
			}
		}
	}
	
	public void clear() {
		lineChart.getData().removeAll(lineChart.getData());
	}

	private XYChart.Series<Number, Number> createSeries(int number, String title, ArrayList<Map<String, Double>> data) {
		XYChart.Series<Number, Number> series = new XYChart.Series<Number, Number>();
		series.setName(Integer.toString(number) + " - " + title);
		int i = 0;
		for (Map<String, Double> step : data) {
			series.getData().add(new XYChart.Data<Number, Number>(i, step.get(title)));
			i++;
		}
		return series;
	}

}
