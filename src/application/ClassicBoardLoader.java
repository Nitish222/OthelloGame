package application;

import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

public class ClassicBoardLoader {
	private Pane view;
	
	public Pane getClassicPane() {
		try {
			URL file = Main.class.getResource("/application/ClassicTheme.fxml");
			if(file==null) {
				throw new java.io.FileNotFoundException("FXML file doesnt exist");
			}
			new FXMLLoader();
			view = FXMLLoader.load(file);
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return view;
	}
}
