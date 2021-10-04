package application;

import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

public class WhitePieceLoader {
	
	private Pane view;
	
	public Pane getWhite() {
		try {
			URL file = Main.class.getResource("/application/WhitePawn.fxml");
			if(file==null) {
				throw new java.io.FileNotFoundException("FXML fiel doesnt exist");
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
