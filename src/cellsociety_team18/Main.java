package cellsociety_team18;

import javafx.application.Application;
import javafx.stage.Stage;
import user_interface.ViewController;

public class Main extends Application {

	/**
	 * Launch the ViewController.
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		ViewController controller = new ViewController(primaryStage);
		controller.start();
	}

	/**
	 * Start the program.
	 */
	public static void main(String[] args) {
		launch(args);
	}

}
