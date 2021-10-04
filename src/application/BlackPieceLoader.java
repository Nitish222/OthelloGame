package application;

import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

public class BlackPieceLoader {	
		private double x;
		private double y;
		
		private Pane view;
		
		public Pane getBlack() {
			try {
				URL file = Main.class.getResource("/application/BlackPawn.fxml");
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
		
		public void setX(double x) {
			this.x = x;
		}
		
		public void setY(double y) {
			this.y = y;
		}
		
		public double getX() {
			return x;
		}
		public double getY() {
			return y;
		}
		
}
