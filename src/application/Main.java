package application;
 
import com.sun.javafx.application.LauncherImpl;


import javafx.application.Application;
import javafx.application.Preloader;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import application.SplashScreenLoader;
/**
 * Main Class 
 * The main class for a JavaFX application extends the javafx.application.Application class
 * The start() method is the entry point for our application.
 */
public class Main extends Application {
	private static final int COUNT_LIMIT = 5;
	@Override
	public void start(Stage primaryStage) {
		try {	
			//create instance of FXMLLoader
			new FXMLLoader();
			//The FXML loader get the static ojects from the FXMl file 
			Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
			//create scene with root that is our XML
			Scene scene = new Scene(root);
			//we don't really need this coz css is not implemented
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			//Setting the primary stage
			primaryStage.setScene(scene);
			//application is not resizeable
			primaryStage.setResizable(false);
			//make primary stage visible
			//we can make other stages but there is only one primary stage
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}	
	 @Override
	    public void init() throws Exception {       
	        // Sleep the thread for 200ms and display a unique message for each index
	        for (int i = 1; i <= COUNT_LIMIT; i++) {        
	        	//notify the preloader that a event has happened at each index of the loop
	            LauncherImpl.notifyPreloader(this, new Preloader.ProgressNotification(i));
	            Thread.sleep(200);
	        }
	    }
	public static void main(String[] args) {
		/**
		 * Launch the given JavaFX Application with given preloader.
		 *
		 * @param appClass       the JavaFX application class to launch
		 * @param preloaderClass the preloader class used as splash screen with progress
		 * @param args           arguments passed to java command line
		 */
		LauncherImpl.launchApplication(Main.class, SplashScreenLoader.class, args);
		launch(args);
	}
}
