package application;


import java.io.IOException;

import java.net.URL;

import com.sun.javafx.application.LauncherImpl;

import javafx.application.Preloader;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import application.SplashScreenController;

public class SplashScreenLoader extends Preloader {

	private Stage splashScreen;
	Pane view;
	private Scene scene;
	public SplashScreenLoader() {
		
	}
	
	@Override 
	public void init() throws Exception{
		Parent root = FXMLLoader.load(getClass().getResource("SplashScreen.fxml"));
		scene = new Scene(root);
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		
			//initialize the stage and scene 
			//this is not primary stage primary stage is set after this have been displayed
			//and hide preloader class event happen
			this.splashScreen = stage;
		    splashScreen.setScene(scene);
		    splashScreen.initStyle(StageStyle.UNDECORATED);
		    splashScreen.show();
	}
	
	

	
	
	//Handle application Notification identifies each event being notified from the preloader
	//Refering back to Main, the preloader.notify
	@Override
	public void handleApplicationNotification(Preloader.PreloaderNotification info) {
		if(info instanceof ProgressNotification) {
			//parsing the progress notification info to integer and then displaying unique message for each index in switch case
			switch((int)((ProgressNotification) info).getProgress()) {
				case 1: 
					//updating label in Controller 
					SplashScreenController.label.setText("Initiating...");
					//Increasing the width of the rectangle named progress bar 
					SplashScreenController.progressbar.setWidth(70);
					break;
				case 2: 
					SplashScreenController.label.setText("Building Board...");
					SplashScreenController.progressbar.setWidth(140);
					break;
				case 3: 
					SplashScreenController.label.setText("Building Pawns...");
					SplashScreenController.progressbar.setWidth(210);
					break;
				case 4: 
					SplashScreenController.label.setText("Building Menu...");
					SplashScreenController.progressbar.setWidth(280);
					break;
				case 5: 
					SplashScreenController.label.setText("Starting...");
					SplashScreenController.progressbar.setWidth(350);
					break;
			}
		}
	}
	
	@Override
    public void handleStateChangeNotification(Preloader.StateChangeNotification info) {
		
		//geting the type of event happening right now 
		StateChangeNotification.Type type = info.getType();
		
		switch (type) {
        case BEFORE_START:
            // Called after MyApplication#init and before MyApplication#start is called.
            System.out.println("BEFORE_START");
            splashScreen.hide();
            break;
		}
       }
    }
