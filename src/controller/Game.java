package controller;

import java.Dice;
import java.Player;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class Game implements Initializable {

	@FXML
	private Label player1_name, player2_name, maxscore, score1, score2, yourturn, ahead, win1, win2;
	@FXML
	private Button back_button, rulebutton, rollbutton, restartbutton;
	@FXML
	private TextArea textarea;
	@FXML
	private ImageView diceimg1, diceimg2, diceimg3;

	// Used in Rolling final dice
	boolean alreadyExecuted = false;

	// counts players turn
	int counter;

	// dice rolled number
	int roll1;
	int roll2;
	int roll3;

	// max point play up to
	int max_score;

	// players current points
	int point1 = 0;
	int point2 = 0;

	// win count
	int w1 = 0;
	int w2 = 0;
	boolean p1win = false;
	boolean p2win = false;

	// controlls dice image;
	boolean show = false;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
		textarea.setEditable(false);
	}

	/*
	 * implementing just leave it commented
	 */

	public void setText(String player1, String player2, String Maxscore) {
		player1_name.setText(player1);
		player2_name.setText(player2);
		maxscore.setText(Maxscore);
		score1.setText("0");
		score2.setText("0");
		win1.setText("0");
		win2.setText("0");

		diceimg1.setImage(new Image("/resource/dieRed_border1.png"));
		diceimg2.setImage(new Image("/resource/dieRed_border1.png"));
		diceimg3.setImage(new Image("/resource/dieRed_border1.png"));

		int num = 2;
		int random = 1 + (int) (Math.random() * num);

		if (random == 1) {
			yourturn.setText(player1_name.getText() + ", it is your turn! -->");
			textarea.setText("Playing Dice Game up to : " + Maxscore + " points." + "\n" + player1 + " roll first");
			counter = 1;
		}

		if (random == 2) {
			yourturn.setText(player2_name.getText() + ", it is your turn! -->");
			textarea.setText("Playing Dice Game up to : " + Maxscore + " points." + "\n" + player2 + " roll first");
			counter = 0;
		}

	}

	@FXML
	public void restartpressed(Event event) throws IOException {

		if (p1win == true) {
			w1++;
			String a = Integer.toString(w1);
			win1.setText(a);
		}

		if (p2win == true) {
			w2++;
			String b = Integer.toString(w2);
			win2.setText(b);
		}

		score1.setText("0");
		score2.setText("0");
		point1 = 0;
		point2 = 0;

		int num = 2;
		int random = 1 + (int) (Math.random() * num);

		ahead.setText("");
		alreadyExecuted = false;

		if (random == 1) {
			yourturn.setText(player1_name.getText() + ", it is your turn! -->");
			textarea.setText("Restarted Dice Game" + "\n" + "Playing Dice Game up to : " + maxscore.getText()
					+ " points." + "\n" + player1_name.getText() + " roll first");
			counter = 1;
		}

		if (random == 2) {
			yourturn.setText(player2_name.getText() + ", it is your turn! -->");
			textarea.setText("Restarted Dice Game" + "\n" + "Playing Dice Game up to : " + maxscore.getText()
					+ " points." + "\n" + player2_name.getText() + " roll first");
			counter = 0;
		}

		p1win = false;
		p2win = false;

	}

	@FXML
	public void backpressed(Event event) throws IOException {

		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/Welcome_Scene.fxml"));
		Parent parent = fxmlLoader.load();
		Scene scene = new Scene(parent);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setTitle("Dice Game");
		stage.setScene(scene);
		stage.show();
	}

	@FXML
	public void rulebuttonpressed(Event event) throws IOException {

		Parent root = FXMLLoader.load(getClass().getResource("/view/Rules_Scene.fxml"));
		Scene scene = new Scene(root);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.setTitle("Rules");
		stage.show();

	}

	@FXML
	public void rollpressed(Event event) throws IOException, InterruptedException {

		// set up values
		int sides = 6;
		boolean roll = false;
		roll1 = 1 + (int) (Math.random() * sides);
		roll2 = 1 + (int) (Math.random() * sides);
		roll3 = 1 + (int) (Math.random() * sides);
		max_score = Integer.parseInt(maxscore.getText());

		if (!(point1 >= max_score) && !(point2 >= max_score)) {
			roll = true;
		}

		// while in initial loop rolls,
		// after a roll, if counter is 0 , tell player1 is their turn
		while (roll == true) {
			if (counter == 0) {
				yourturn.setText("*" + player1_name.getText() + ", it is your turn! -->");

			}
			// after roll, if counter is 1 , tell player2 is their turn
			if (counter == 1) {
				yourturn.setText("*" + player2_name.getText() + ", it is your turn! -->");

			}
			break;
		}

		/*
		 * initial loop rolls stops when player1 or player2 score >= set Score.
		 */
		while (roll) {
			show = true;

			if (counter == 1) { // Player1's turn

				if (roll1 == roll2 && !(roll3 == roll1)) {
					int sum = roll1 + roll2;
					String s = textarea.getText();
					textarea.setText(s + "\n" + player1_name.getText() + " Rolled Dice, result:  " + roll1 + "  "
							+ roll2 + "  " + roll3 + ", a 2 pair Combo! ( " + roll1 + " and " + roll2 + " ) " + "  +"
							+ sum + " points");
					textarea.selectEnd();
					textarea.deselect();
					point1 += roll1 + roll2;

					String total = Integer.toString(point1);
					score1.setText(total);
				} else if (roll2 == roll3 && !(roll1 == roll2)) {
					int sum = roll2 + roll3;
					String s = textarea.getText();
					textarea.setText(s + "\n" + player1_name.getText() + " Rolled Dice, result:  " + roll1 + "  "
							+ roll2 + "  " + roll3 + ", a 2 pair Combo! ( " + roll2 + " and " + roll3 + " ) " + "  +"
							+ sum + " points");
					textarea.selectEnd();
					textarea.deselect();
					point1 += roll2 + roll3;

					String total = Integer.toString(point1);
					score1.setText(total);
				} else if (roll3 == roll1 && !(roll2 == roll3)) {
					int sum = roll3 + roll1;
					String s = textarea.getText();
					textarea.setText(s + "\n" + player1_name.getText() + " Rolled Dice, result:  " + roll1 + "  "
							+ roll2 + "  " + roll3 + ", a 2 pair Combo! ( " + roll3 + " and " + roll1 + " ) " + "  +"
							+ sum + " points");
					textarea.selectEnd();
					textarea.deselect();
					point1 += roll3 + roll1;
					String total = Integer.toString(point1);
					score1.setText(total);
				} else if (roll1 == roll2 && roll2 == roll3) {
					String s = textarea.getText();
					textarea.setText(s + "\n" + player1_name.getText() + " Rolled Dice, result:  " + roll1 + "  "
							+ roll2 + "  " + roll3 + ", 3 the same Combo! +18 points");
					textarea.selectEnd();
					textarea.deselect();
					point1 += 18;
					String total = Integer.toString(point1);
					score1.setText(total);
				} else {
					String s = textarea.getText();
					textarea.setText(s + "\n" + player1_name.getText() + " Rolled Dice, result:  " + roll1 + "  "
							+ roll2 + "  " + roll3 + "  +1 point");
					textarea.selectEnd();
					textarea.deselect();
					point1++;

					String total = Integer.toString(point1);
					score1.setText(total);
				}

			} else if (counter == 0) {

				if (roll1 == roll2 && !(roll3 == roll1)) {
					int sum = roll1 + roll2;
					String s = textarea.getText();
					textarea.setText(s + "\n" + player2_name.getText() + " Rolled Dice, result:  " + roll1 + "  "
							+ roll2 + "  " + roll3 + ", a 2 pair Combo! ( " + roll1 + " and " + roll2 + " ) " + "  +"
							+ sum + " points");
					textarea.selectEnd();
					textarea.deselect();
					point2 += roll1 + roll2;

					String total = Integer.toString(point2);
					score2.setText(total);
				} else if (roll2 == roll3 && !(roll1 == roll2)) {
					int sum = roll2 + roll3;
					String s = textarea.getText();
					textarea.setText(s + "\n" + player2_name.getText() + " Rolled Dice, result:  " + roll1 + "  "
							+ roll2 + "  " + roll3 + ", a 2 pair Combo! ( " + roll2 + " and " + roll3 + " ) " + "  +"
							+ sum + " points");
					textarea.selectEnd();
					textarea.deselect();
					point2 += roll2 + roll3;

					String total = Integer.toString(point2);
					score2.setText(total);
				} else if (roll3 == roll1 && !(roll2 == roll3)) {
					int sum = roll3 + roll1;
					String s = textarea.getText();
					textarea.setText(s + "\n" + player2_name.getText() + " Rolled Dice, result:  " + roll1 + "  "
							+ roll2 + "  " + roll3 + ", a 2 pair Combo! ( " + roll3 + " and " + roll1 + " ) " + "  +"
							+ sum + " points");
					textarea.selectEnd();
					textarea.deselect();
					point2 += roll3 + roll1;

					String total = Integer.toString(point2);
					score2.setText(total);
				} else if (roll1 == roll2 && roll2 == roll3) {
					String s = textarea.getText();
					textarea.setText(s + "\n" + player2_name.getText() + " Rolled Dice, result:  " + roll1 + "  "
							+ roll2 + "  " + roll3 + ", 3 the same Combo! +18 points");
					textarea.selectEnd();
					textarea.deselect();
					point2 += 18;

					String total = Integer.toString(point2);
					score2.setText(total);
				} else {
					String s = textarea.getText();
					textarea.setText(s + "\n" + player2_name.getText() + " Rolled Dice, result:  " + roll1 + "  "
							+ roll2 + "  " + roll3 + "  +1 point");
					textarea.selectEnd();
					textarea.deselect();
					point2++;

					String total = Integer.toString(point2);
					score2.setText(total);
				}

			}

			if (point1 >= max_score && point2 + 18 >= max_score) {
				yourturn.setText("*" + player2_name.getText() + ", Last Roll! -->");
			}

			if (point2 >= max_score && point1 + 18 >= max_score) {
				yourturn.setText("*" + player1_name.getText() + ", Last Roll! -->");
			}

			break;
		}

//__________________________________________________________________________________________________________________
//check for win conditions and last roll
//__________________________________________________________________________________________________________________		
		while (roll == false) {

			if (point2 >= max_score && point1 + 18 < max_score) {
				int sum = point2 - point1;
				

				yourturn.setText("*Game Over. Please restart the game to continue.");
				p2win = true;

				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Game finished");
				alert.setHeaderText("Result:");
				alert.setContentText(
						player2_name.getText() + " won by " + sum + "points!" + "\n" + "Please restart the game to continue.");
				alert.showAndWait();
				String s = textarea.getText();
				textarea.setText(s + "\n" + "Congratulations " + player2_name.getText() + "! You've won!" + "\n"
						+ "Please restart the game to continue.");
				textarea.selectEnd();
				textarea.deselect();
				break;
			} else if (point1 >= max_score && point2 + 18 < max_score) {
				int sum = point1 - point2;
				

				yourturn.setText("* Game Over. Please restart the game to continue.");

				p1win = true;

				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Game finished");
				alert.setHeaderText("Result:");
				alert.setContentText(
						player1_name.getText() + " won by " + sum + "points!" + "\n" + "Please restart the game to continue.");
				alert.showAndWait();
				String s = textarea.getText();
				textarea.setText(s + "\n" + "Congratulations " + player1_name.getText() + "! You've won!" + "\n"
						+ "Please restart the game to continue.");
				textarea.selectEnd();
				textarea.deselect();
				break;

			} else if (!alreadyExecuted) {
				show = true;
				if (counter == 1) { // Player1's turn
					if (roll1 == roll2 && !(roll3 == roll1)) {
						int sum = roll1 + roll2;
						String s = textarea.getText();
						textarea.setText(s + "\n" + player1_name.getText() + " Rolled Dice, result:  " + roll1 + "  "
								+ roll2 + "  " + roll3 + ", a 2 pair Combo! ( " + roll1 + " and " + roll2 + " ) "
								+ "  +" + sum + " points");
						textarea.selectEnd();
						textarea.deselect();
						point1 += roll1 + roll2;

						String total = Integer.toString(point1);
						score1.setText(total);
						alreadyExecuted = true;
					} else if (roll2 == roll3 && !(roll1 == roll2)) {
						int sum = roll2 + roll3;
						String s = textarea.getText();
						textarea.setText(s + "\n" + player1_name.getText() + " Rolled Dice, result:  " + roll1 + "  "
								+ roll2 + "  " + roll3 + ", a 2 pair Combo! ( " + roll2 + " and " + roll3 + " ) "
								+ "  +" + sum + " points");
						textarea.selectEnd();
						textarea.deselect();
						point1 += roll2 + roll3;

						String total = Integer.toString(point1);
						score1.setText(total);
						alreadyExecuted = true;
					} else if (roll3 == roll1 && !(roll2 == roll3)) {
						int sum = roll3 + roll1;
						String s = textarea.getText();
						textarea.setText(s + "\n" + player1_name.getText() + " Rolled Dice, result:  " + roll1 + "  "
								+ roll2 + "  " + roll3 + ", a 2 pair Combo! ( " + roll3 + " and " + roll1 + " ) "
								+ "  +" + sum + " points");
						textarea.selectEnd();
						textarea.deselect();
						point1 += roll3 + roll1;
						String total = Integer.toString(point1);
						score1.setText(total);
						alreadyExecuted = true;
					} else if (roll1 == roll2 && roll2 == roll3) {
						String s = textarea.getText();
						textarea.setText(s + "\n" + player1_name.getText() + " Rolled Dice, result:  " + roll1 + "  "
								+ roll2 + "  " + roll3 + ", 3 the same Combo! +18 points");
						textarea.selectEnd();
						textarea.deselect();
						point1 += 18;
						String total = Integer.toString(point1);
						score1.setText(total);
						alreadyExecuted = true;
					} else {
						String s = textarea.getText();
						textarea.setText(s + "\n" + player1_name.getText() + " Rolled Dice, result:  " + roll1 + "  "
								+ roll2 + "  " + roll3 + "  +1 point");
						textarea.selectEnd();
						textarea.deselect();
						point1++;

						String total = Integer.toString(point1);
						score1.setText(total);
						alreadyExecuted = true;
					}

				}
				if (counter == 0) {

					if (roll1 == roll2 && !(roll3 == roll1)) {
						int sum = roll1 + roll2;
						String s = textarea.getText();
						textarea.setText(s + "\n" + player2_name.getText() + " Rolled Dice, result:  " + roll1 + "  "
								+ roll2 + "  " + roll3 + ", a 2 pair Combo! ( " + roll1 + " and " + roll2 + " ) "
								+ "  +" + sum + " points");
						textarea.selectEnd();
						textarea.deselect();
						point2 += roll1 + roll2;

						String total = Integer.toString(point2);
						score2.setText(total);
						alreadyExecuted = true;
					} else if (roll2 == roll3 && !(roll1 == roll2)) {
						int sum = roll2 + roll3;
						String s = textarea.getText();
						textarea.setText(s + "\n" + player2_name.getText() + " Rolled Dice, result:  " + roll1 + "  "
								+ roll2 + "  " + roll3 + ", a 2 pair Combo! ( " + roll2 + " and " + roll3 + " ) "
								+ "  +" + sum + " points");
						textarea.selectEnd();
						textarea.deselect();
						point2 += roll2 + roll3;

						String total = Integer.toString(point2);
						score2.setText(total);
						alreadyExecuted = true;
					} else if (roll3 == roll1 && !(roll2 == roll3)) {
						int sum = roll3 + roll1;
						String s = textarea.getText();
						textarea.setText(s + "\n" + player2_name.getText() + " Rolled Dice, result:  " + roll1 + "  "
								+ roll2 + "  " + roll3 + ", a 2 pair Combo! ( " + roll3 + " and " + roll1 + " ) "
								+ "  +" + sum + " points");
						textarea.selectEnd();
						textarea.deselect();
						point2 += roll3 + roll1;

						String total = Integer.toString(point2);
						score2.setText(total);
						alreadyExecuted = true;
					} else if (roll1 == roll2 && roll2 == roll3) {
						String s = textarea.getText();
						textarea.setText(s + "\n" + player2_name.getText() + " Rolled Dice, result:  " + roll1 + "  "
								+ roll2 + "  " + roll3 + ", 3 the same Combo! +18 points");
						textarea.selectEnd();
						textarea.deselect();
						point2 += 18;

						String total = Integer.toString(point2);
						score2.setText(total);
						alreadyExecuted = true;
					} else {
						String s = textarea.getText();
						textarea.setText(s + "\n" + player2_name.getText() + " Rolled Dice, result:  " + roll1 + "  "
								+ roll2 + "  " + roll3 + "  +1 point");
						textarea.selectEnd();
						textarea.deselect();
						point2++;

						String total = Integer.toString(point2);
						score2.setText(total);
						alreadyExecuted = true;
					}
				}
			}

			if (point1 >= max_score && point2 < max_score) {
				int sum = point1 - point2;
				

				yourturn.setText("* Game Over. Please restart the game to continue.");

				p1win = true;

				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Game finished");
				alert.setHeaderText("Result:");
				alert.setContentText(
						player1_name.getText() + " won by " + sum + "points!" + "\n" + "Please restart the game to continue.");
				alert.showAndWait();
				String s = textarea.getText();
				textarea.setText(s + "\n" + "Congratulations " + player1_name.getText() + "! You've won!" + "\n"
						+ "Please restart the game to continue.");
				textarea.selectEnd();
				textarea.deselect();
				break;

			} else if (point2 >= max_score && point1 < max_score) {
				int sum = point2 - point1;
				

				p2win = true;

				yourturn.setText("* Game Over. Please restart the game to continue.");

				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Game finished");
				alert.setHeaderText("Result:");
				alert.setContentText(
						player2_name.getText() + " won by " + sum + "points!" + "\n" + "Please restart the game to continue.");
				alert.showAndWait();
				String s = textarea.getText();
				textarea.setText(s + "\n" + "Congratulations " + player2_name.getText() + "! You've won!" + "\n"
						+ "Please restart the game to continue.");
				textarea.selectEnd();
				textarea.deselect();
				break;

			} else if (point2 >= max_score && point1 >= max_score || point1 >= max_score && point2 >= max_score) {

				p1win = false;
				p2win = false;
				

				yourturn.setText("*Game Over. Please restart the game to continue.");

				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Game finished");
				alert.setHeaderText("Result:");
				alert.setContentText("Draw!" + "\n" + "Please restart the game to continue.");
				alert.showAndWait();
				String s = textarea.getText();
				textarea.setText(s + "\n" + "Draw" + "\n" + "Please restart the game to continue.");
				textarea.selectEnd();
				textarea.deselect();
				break;

			}
		}

//____________________________________________________________________________________________________________________

//____________________________________________________________________________________________________________________

		// counter sets between 0 and 1 each round
		if (counter == 0) {
			counter++;
		} else if (counter == 1) {
			counter--;
		}

		// set ahead to null
		if (point1 == point2) {
			ahead.setText("* equal in points!");
		}
		// set player1 as ahead
		if (point1 > point2) {
			ahead.setText("*" + player1_name.getText() + " is " + "\n" + "currently ahead!");
		}
		// set player2 as ahead
		if (point1 < point2) {
			ahead.setText("*" + player2_name.getText() + " is " + "\n" + "currently ahead!");
		}

		if (show == true) {
			switch (roll1) {
			case 1:
				diceimg1.setImage(new Image("/resource/dieRed_border1.png"));
				break;
			case 2:
				diceimg1.setImage(new Image("/resource/dieWhite_border2.png"));
				break;
			case 3:
				diceimg1.setImage(new Image("/resource/dieRed_border3.png"));
				break;
			case 4:
				diceimg1.setImage(new Image("/resource/dieWhite_border4.png"));
				break;
			case 5:
				diceimg1.setImage(new Image("/resource/dieRed_border5.png"));
				break;
			case 6:
				diceimg1.setImage(new Image("/resource/dieWhite_border6.png"));
				break;
			}

			switch (roll2) {
			case 1:
				diceimg2.setImage(new Image("/resource/dieRed_border1.png"));
				break;
			case 2:
				diceimg2.setImage(new Image("/resource/dieWhite_border2.png"));
				break;
			case 3:
				diceimg2.setImage(new Image("/resource/dieRed_border3.png"));
				break;
			case 4:
				diceimg2.setImage(new Image("/resource/dieWhite_border4.png"));
				break;
			case 5:
				diceimg2.setImage(new Image("/resource/dieRed_border5.png"));
				break;
			case 6:
				diceimg2.setImage(new Image("/resource/dieWhite_border6.png"));
				break;
			}

			switch (roll3) {
			case 1:
				diceimg3.setImage(new Image("/resource/dieRed_border1.png"));
				break;
			case 2:
				diceimg3.setImage(new Image("/resource/dieWhite_border2.png"));
				break;
			case 3:
				diceimg3.setImage(new Image("/resource/dieRed_border3.png"));
				break;
			case 4:
				diceimg3.setImage(new Image("/resource/dieWhite_border4.png"));
				break;
			case 5:
				diceimg3.setImage(new Image("/resource/dieRed_border5.png"));
				break;
			case 6:
				diceimg3.setImage(new Image("/resource/dieWhite_border6.png"));
				break;
			}

		}
		
		show = false;
	}
}
