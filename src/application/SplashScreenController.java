package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class SplashScreenController implements Initializable {
	
	//this is used to initialize the loading label and change the loading bar rectangle width
	@FXML
	private Text LoadingText;
	public static Text label;
	@FXML
	private Rectangle LoadingRec;
	public static Rectangle progressbar;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		label = LoadingText;
		progressbar = LoadingRec;
		
	}
}
