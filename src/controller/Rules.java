package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Rules implements Initializable {

	@FXML
	Label des1, des2, des3, rule1, rule2, rule3;

	@FXML
	Button next, back;

	@FXML
	ImageView pic1, pic2, pic3, pic4, pic5, pic6, pic7, pic8, pic9;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		rule1.setVisible(false);
		rule2.setVisible(false);
		rule3.setVisible(false);

		// TODO Auto-generated method stub

		/*
		 * if both players reaches a score which is more than the pre-defined maximum,
		 * the game’s result is a Draw 
		 * 
		 * a player is considered a winner if he/she
		 * accumulates a score which is more than the predefined maximum.
		 * 
		 */
		des1.setText("The system will randomly select a player between player1 and player2." + "\n"
				+ "That player will get a first roll.");
		des2.setText("A player is considered a winner if he/she accumulates a score which is more than"
				+ "\n" + "the predefined maximum.");
		des3.setText("If both players reaches a score which is more than the pre-defined maximum, the"
				+ "\n" + "game’s result is a Draw.");
		
		back.setDisable(true);

	}

	@FXML
	public void nextButtonPressed(Event event) throws IOException, InterruptedException {

		// Set the dice image visible
		pic1.setVisible(true);
		pic2.setVisible(true);
		pic3.setVisible(true);
		pic4.setVisible(true);
		pic5.setVisible(true);
		pic6.setVisible(true);
		pic7.setVisible(true);
		pic8.setVisible(true);
		pic9.setVisible(true);

		// Set the descriptions in first page diabled
		des1.setVisible(false);
		des2.setVisible(false);
		des3.setVisible(false);

		// Set back and next
		back.setDisable(false);
		next.setDisable(true);

		// Set Rules
		rule1.setVisible(true);
		rule2.setVisible(true);
		rule3.setVisible(true);

		// example1: 2 pair
		pic1.setImage(new Image("/resource/dieRed_border3.png"));
		pic2.setImage(new Image("/resource/dieRed_border3.png"));
		pic3.setImage(new Image("/resource/dieWhite_border4.png"));
		rule1.setText("If it generates a pair, then the player score is the sum of that" + "\n"
				+ "pair. For example if it generates 3 & 3 & 4, the player scores" + "\n"
				+ "will be the sum of 3 & 3 which is 6.");

		// example2: 3 pair
		pic4.setImage(new Image("/resource/dieRed_border5.png"));
		pic5.setImage(new Image("/resource/dieRed_border5.png"));
		pic6.setImage(new Image("/resource/dieRed_border5.png"));
		rule2.setText("If all the 3 dice rolls have the same values, then the score will" + "\n" + "be 18.");

		// example2: 3 pair
		pic7.setImage(new Image("/resource/dieRed_border1.png"));
		pic8.setImage(new Image("/resource/dieWhite_border2.png"));
		pic9.setImage(new Image("/resource/dieWhite_border4.png"));
		rule3.setText("Otherwise, for any other combination, they just get 1 score.");
	}

	@FXML
	public void backButtonPressed(Event event) throws IOException, InterruptedException {

		// Set dice pics
		pic1.setVisible(false);
		pic2.setVisible(false);
		pic3.setVisible(false);
		pic4.setVisible(false);
		pic5.setVisible(false);
		pic6.setVisible(false);
		pic7.setVisible(false);
		pic8.setVisible(false);
		pic9.setVisible(false);

		// Set next back
		back.setDisable(true);
		next.setDisable(false);

		// Set Rules
		rule1.setVisible(false);
		rule2.setVisible(false);
		rule3.setVisible(false);

		des1.setVisible(true);
		des2.setVisible(true);
		des3.setVisible(true);
		
		des1.setText("The system will randomly select a player between player1 and player2." + "\n"
				+ "That player will get a first roll.");
		des2.setText("A player is considered a winner if he/she accumulates a score which is more than"
				+ "\n" + "the predefined maximum.");
		des3.setText("If both players reaches a score which is more than the pre-defined maximum, the"
				+ "\n" + "game’s result is a Draw ");
		

	}

}
