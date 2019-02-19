package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class HelpandDocumentation implements Initializable{
	
	@FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label help1;

    @FXML
    private Label help2;

    @FXML
    private Label help3;
    
    @FXML
    private Label help4;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		help1.setText("Navigation \nClick on 'Play!' button will take you to our game page." );
		
		help2.setText("\n" +"'Rules' button is provided on the Welcome page and Game Page which takes you"
				+ "\n" + "to a seperate window. Back button will take you back to the previous page.");
		
		help3.setText("Rules \nIn the Rules window, you will be given all the necessary information on how the game "
				+ "\n" + "works.");
		
		help4.setText("Restart \nRestart button will allow you to reset both players' score to 0, but do not reset players' "
				+ "\n" +"names.");
		
	}
	
	

}
