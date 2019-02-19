package controller;

import java.Dice;
import java.Player;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import javafx.application.Application;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javafx.util.converter.IntegerStringConverter;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TextFormatter.Change;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

public class Welcome extends Application implements Initializable {

	private static Label player1, player2, dice, maxscore;

	@FXML
	private ResourceBundle resources;
	@FXML
	private URL location;
	@FXML
	private TextField player1_name, player2_name, maxpoint;
	@FXML
	private Button playbutton, rulebutton, helpbutton;
	@FXML
	private Label label1, label2, label3;
	@FXML
	private ImageView logo1, logo2;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/view/Welcome_Scene.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setTitle("Dice Game");
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	public void p1enter(Event event) {
		String name_1 = player1_name.getText();
		if (!(name_1.isEmpty()) && name_1.length() <= 12) {
			label1.setText("ok");
			label1.setStyle("-fx-text-fill: black;");
		}
	}

	@FXML
	public void p1exit(Event event) {
		String name_1 = player1_name.getText();
		if (name_1.isEmpty()) {
			System.out.println("player 1 is empty");
			label1.setText("Player 1 Name cannot be empty!!");
			label1.setStyle("-fx-text-fill: red;");
		} else if (name_1.length() > 12) {
			System.out.println("player 1 Name over limit");
			label1.setText("Player 1 Name can only be " + "\n" + "a maximum of 12 Characters!!");
			label1.setStyle("-fx-text-fill: red;");
		}
	}

	@FXML
	public void p2enter(Event event) {
		String name_2 = player2_name.getText();
		if (!(name_2.isEmpty()) && name_2.length() <= 12) {
			label2.setText("ok");
			label2.setStyle("-fx-text-fill: black;");
		}
	}

	@FXML
	public void p2exit(Event event) {
		String name_2 = player2_name.getText();
		if (name_2.isEmpty()) {
			System.out.println("player 2 is empty");
			label2.setText("Player 2 Name cannot be empty!!");
			label2.setStyle("-fx-text-fill: red;");
		} else if (name_2.length() > 12) {
			System.out.println("player 2 Name over limit");
			label2.setText("Player 2 Name can only be " + "\n" + "a maximum of 12 Characters!!");
			label2.setStyle("-fx-text-fill: red;");
		}
	}

	@FXML
	public void maxenter(Event event) {
		String score = maxpoint.getText();

		if (score.isEmpty()) {
			System.out.println("max score is empty or less than 1");
			maxpoint.setText("");
			label3.setText("Max Score value cannot " + "\n" + "be empty or Zero!!");
			label3.setStyle("-fx-text-fill: red;");
		} else if (!score.matches("[0-9]+")) {
			System.out.println("max score is not number");
			maxpoint.setText("");
			label3.setText("Max score value has to be " + "\n" + "Numbers");
			label3.setStyle("-fx-text-fill: red;");
		} else if (!(Integer.parseInt(score) > 0)) {
			System.out.println("max score is empty or less than 1");
			maxpoint.setText("");
			label3.setText("Max Score value cannot " + "\n" + "be empty or Zero!!");
			label3.setStyle("-fx-text-fill: red;");
		}
	}

	@FXML
	public void maxexit(Event event) {
		String score = maxpoint.getText();

		if (!(score.isEmpty()) && score.matches("[0-9]+") && Integer.parseInt(score) > 0) {

			label3.setText("ok");
			label3.setStyle("-fx-text-fill: black;");
		}

	}

	@FXML
	public void playgame(Event event) throws IOException {

		String name_1 = player1_name.getText();
		String name_2 = player2_name.getText();
		String score = maxpoint.getText();

		if (name_1.length() <= 12 && name_2.length() <= 12 && score.matches("[0-9]+") && Integer.parseInt(score) > 0) {

			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/Game_Scene.fxml"));
			Parent parent = fxmlLoader.load();
			Game gpc = fxmlLoader.getController();
			gpc.setText(name_1, name_2, score);
			Scene scene = new Scene(parent);
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.setTitle("Game in Progress...");
			stage.setScene(scene);
			stage.show();
		} else {
			System.out.println("errors");

		}

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
	public void helpbuttonpressed(Event event) throws IOException {

		Parent root = FXMLLoader.load(getClass().getResource("/view/Help_Scene.fxml"));
		Scene scene = new Scene(root);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.setTitle("Help");
		stage.show();

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

}
