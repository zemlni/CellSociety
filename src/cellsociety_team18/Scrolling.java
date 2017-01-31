package cellsociety_team18;



import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.ScrollEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Scrolling extends Application {
	public static final String TITLE = "Scrolling";

	public static final String textName = "DESIGN.md"; 
	private Rectangle bar;
	private Rectangle grabber;
	private Rectangle tableSpace = new Rectangle(10, 10, 300, 300); //use this to set space for table
	
	
	
	
	public static final int SIZE = 400;
	public static final Paint BACKGROUND = Color.WHITE;
	public static final int FRAMES_PER_SECOND = 60;
	public static final int MILLISECOND_DELAY = 1000 / FRAMES_PER_SECOND;
	public static final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
	public static final int KEY_INPUT_SPEED = 5;
	public static final double GROWTH_RATE = 1.1;
	public static final int BOUNCER_SPEED = 30;
	public static final int margin = 10;

	private Scene myScene;

	private ArrayList<Text> textList = new ArrayList<Text>();
	private int topTextIndex = 0;
	private int textSpacing = 12;
	
	private Text selectedGame;

	Group gameList = new Group();





	/**
	 * Initialize what will be displayed and how it will be updated.
	 */
	@Override
	public void start (Stage s) {
		// attach scene to the stage and display it
		Scene scene = setupGame(SIZE, SIZE, BACKGROUND);
		s.setScene(scene);
		s.setTitle(TITLE);
		s.show();
		// attach "game loop" to timeline to play it
		KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY),
				e -> step(SECOND_DELAY));
		Timeline animation = new Timeline();
		animation.setCycleCount(Timeline.INDEFINITE);
		animation.getKeyFrames().add(frame);
		animation.play();
	}

	// Create the game's "scene": what shapes will be in the game and their starting properties
	private Scene setupGame (int width, int height, Paint background) {
		// create one top level collection to organize the things in the scene

		// create a place to see the shapes
		myScene = new Scene(gameList, width, height, background);

		bar = new Rectangle(tableSpace.getX() + tableSpace.getWidth() - margin, tableSpace.getY(), 10, tableSpace.getHeight());
		bar.setFill(Color.GRAY);
		bar.setArcHeight(bar.getWidth());
		bar.setArcWidth(bar.getWidth());

		grabber = new Rectangle(tableSpace.getX() + tableSpace.getWidth() - margin, tableSpace.getY(), 10, bar.getHeight() / 20);
		grabber.setArcHeight(grabber.getWidth());
		grabber.setArcWidth(grabber.getWidth());


		gameList.getChildren().add(bar);
		gameList.getChildren().add(grabber);

		
		myScene.setOnMouseDragged(e -> handleMouseInput(e.getX(), e.getY()));
		myScene.setOnScroll(e -> scroll(e, e.getX(), e.getY()));
		myScene.setOnMouseClicked(e -> clickGameSelection(e.getX(), e.getY()));
		addText();
		return myScene;
	}

	private void handleTextFiles(){
		Scanner sc = null;
		try {
			sc = new Scanner(new File(textName));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayList<String> lines = new ArrayList<String>();
		while (sc.hasNextLine()) {
			lines.add(sc.nextLine());
		}

		String[] arr = lines.toArray(new String[0]);

		//adds text to a list so it can be added to the display
		for (String each : arr){
			textList.add(new Text(20, tableSpace.getY(), each));
		}
	}

	//changes which game name is at the top of the scrolling list based on the position of the grabber
	private void percentageScroll(){
		double grabberPercentage = (100/(bar.getHeight() / (grabber.getY() - bar.getY())));
		int closestIndex = 0;
		double closestDifference = 100;

		//important because we don't want to keep scrolling into white space, stop scrolling when there is not more text
		double realMaxList = textList.size() - ((tableSpace.getHeight() - grabber.getHeight()) / textSpacing);

		if (textList.size() > 0){
			for (int i = 1; i < textList.size(); i++){
				double currentDegree = 100 * ((double) i / realMaxList);
				double currentDifference = Math.abs(grabberPercentage - currentDegree);

				if (currentDifference < closestDifference){
					closestDifference = currentDifference;
					closestIndex = i;
				}
			}
		}

		topTextIndex = closestIndex;

		//fixes a bug that it wouldn't scroll to top 
		if (Math.abs(grabber.getY() - tableSpace.getY()) < 20){
			topTextIndex = 0;
		}

	}

	private void addText(){
		handleTextFiles();
		for (int i = 0; i < textList.size(); i++){
			Text text = textList.get(i);
			
			//adjusts the size of text so it fits in the window provided
			while (text.getBoundsInLocal().getWidth() > tableSpace.getWidth()){
				text.setFont(new Font(text.getFont().getSize() - 1));
			}
		}
		updateText();
	}



	private void updateText(){
		for (int i = 0; i < textList.size(); i++){
			//remove text so it can be updated
			if (gameList.getChildren().contains(textList.get(i))){
				gameList.getChildren().remove(textList.get(i));
			}
			
			//altering the position of the text based on the topTextIndex which is changing (see percentageScroll)
			textList.get(i).setY(tableSpace.getY() + (i - topTextIndex) * textSpacing);
			
			//adding the text back in
			if (i > topTextIndex && (i - topTextIndex) * textSpacing < (tableSpace.getHeight() + tableSpace.getY()) ){
				gameList.getChildren().add(textList.get(i));
			}

		}
	}

	private void step (double elapsedTime) {
		//        // update attributes
		//        myBouncer.setX(myBouncer.getX() + BOUNCER_SPEED * elapsedTime);
		//        myTopBlock.setRotate(myTopBlock.getRotate() - 1);
		//        myBottomBlock.setRotate(myBottomBlock.getRotate() + 1);
	}


	private void handleMouseInput (double x, double y) {
		//moves grabber when clicked and dragged
		if (bar.contains(x, y)) {
			//prevents the grabber from going off the bar 
			if (y <= bar.getHeight() + bar.getY() - grabber.getHeight()){
				grabber.setY(y);
			}
			percentageScroll();
			updateText();
		}
	}
	//alternate way to scroll through game Names
	private void scroll(ScrollEvent event, double x, double y){
		double changeY = grabber.getY() + event.getDeltaY();
		if (tableSpace.contains(x,y)){
			if (changeY <= bar.getHeight() + bar.getY() - grabber.getHeight() && changeY > bar.getY()){
				grabber.setY(changeY);
				percentageScroll();
				updateText();
			}
		}
	}
	
	//allows you to select which game you want to simulate
	private void clickGameSelection(double x, double y){
		for (Text game : textList){
			if (game.contains(x,y)){
				//setting others to normal
				for (Text others : textList){
					others.setFont(Font.font(others.getFont().getFamily(), FontWeight.NORMAL, others.getFont().getSize()));
				}
				selectedGame = game;
				game.setFont(Font.font(game.getFont().getFamily(), FontWeight.BOLD, game.getFont().getSize()));
			}
		}
	}
	
	
	

	/**
	 * Start the program.
	 */
	//calls start
	public static void main (String[] args) {
		launch(args);
	}
}

