package cellsociety_team18;

import javafx.application.Application;
import javafx.stage.Stage;
import user_interface.ViewController;

/**
 * @author nikita This class launches the application.
 */
public class Main extends Application {

	/**
	 * Launch the ViewController.
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		new ViewController(primaryStage);
	}

	/**
	 * Start the program.
	 */
	public static void main(String[] args) {
		launch(args);
	}

}
