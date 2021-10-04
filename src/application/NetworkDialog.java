package application;


import com.sun.javafx.scene.paint.GradientUtils.Point;
import com.sun.javafx.tk.FontMetrics;
import com.sun.javafx.tk.Toolkit;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.scene.transform.Scale;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import server.Server;


public class NetworkDialog {
	
	public NetworkDialog(Stage stage,TextFlow console) {
		final Stage dialog = new Stage();
		TextField addressField =  new TextField();
		
		String[] ports = {"32300","42300","52300"};
		ComboBox<String> portMenu = new ComboBox<String>();
		portMenu.getItems().addAll(ports);
		TextField nameField =  new TextField();
		
		//IP Address area
		VBox addressArea = new VBox();
		HBox address = new HBox();
		TextField portAddress =  new TextField();
		address.getChildren().add(new Label("Address:"));
		address.getChildren().add(portAddress);
		addressArea.getChildren().add(address);
	
		portMenu.setEditable(true);
		
		
		//Name Area
		VBox nameArea = new VBox();
		HBox name = new HBox();
		name.getChildren().add(new Label("Name:"));
		name.getChildren().add(nameField);
		nameArea.getChildren().add(name);
		
		//Port Area
		VBox portArea = new VBox();
		HBox port = new HBox();
		port.getChildren().add(new Label("Port:"));
		port.getChildren().add(portMenu);
		dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(stage);
        VBox dialogVbox = new VBox(5);
        portArea.getChildren().add(port);
        
        
        
        //horizontal pane
        HBox buttons = new HBox();
        
        //connect button
        Button connect = new Button();
        connect.setText("Connect");
        dialogVbox.setAlignment(Pos.CENTER);
        dialogVbox.setStyle("-fx-background-color: #FFB6C1;");
        connect.setOnAction(
       	        new EventHandler<ActionEvent>() {
       	            @Override
       	            public void handle(ActionEvent event) {
       	            	if(portAddress.getText().isEmpty()) {
       	            		addressArea.getChildren().add(getAddressLabel());
       	            	}
       	            	if(nameField.getText().isEmpty()) {
       	            		nameArea.getChildren().add(getNameLabel());
       	            	}
       	            	if(portMenu.getSelectionModel().isEmpty()) {
       	            		portArea.getChildren().add(getPortLabel());
       	            	}
       	            	else {
       	            		new Server(Integer.parseInt(portMenu.getSelectionModel().toString()));
       	            		console.getChildren().add(new Text("Connected"));
       	            		console.getChildren().add(new Text(System.lineSeparator()));
       	            	}
       	            }
       	         }
       	);
        buttons.getChildren().add(connect);
        
        //cancel button
        Button cancel = new Button();
        cancel.setText("Cancel");
        dialogVbox.setAlignment(Pos.CENTER);
        dialogVbox.setStyle("-fx-background-color: #FFB6C1;");
        cancel.setOnAction(
       	        new EventHandler<ActionEvent>() {
       	            @Override
       	            public void handle(ActionEvent event) {
       	            	console.getChildren().add(new Text("Cancelled"));
	            		console.getChildren().add(new Text(System.lineSeparator()));
       	                dialog.close();
       	            }
       	         });
        buttons.getChildren().add(cancel);
        
        dialogVbox.getChildren().addAll(addressArea,portArea,nameArea,buttons);
        dialogVbox.setStyle("-fx-border-width: 2px; -fx-padding: 10; -fx-spacing: 8; -fx-background-color: #A9A9A9");
       
        Scene dialogScene = new Scene(dialogVbox,300,200);
        dialog.setScene(dialogScene);
        dialog.initStyle(StageStyle.UNDECORATED);
        dialog.show();
	}
	
	
	private Label getAddressLabel() {
		Label labelresponse= new Label("Address cannot be left empty");
		labelresponse.setStyle("-fx-font-size:10px;-fx-font-weight: bold;-fx-text-fill:red;");
		labelresponse.setTextAlignment(TextAlignment.LEFT);
		return labelresponse;
	}
	private Label getPortLabel() {
		Label labelresponse= new Label("Port cannot be left empty");
		labelresponse.setStyle("-fx-font-size:10px;-fx-font-weight: bold;-fx-text-fill:red;");
		labelresponse.setTextAlignment(TextAlignment.LEFT);
		return labelresponse;
	}
	private Label getNameLabel() {
		Label labelresponse= new Label("Name cannot be left empty");
		labelresponse.setStyle("-fx-font-size:10px;-fx-font-weight: bold;-fx-text-fill:red;");
		labelresponse.setTextAlignment(TextAlignment.LEFT);
		return labelresponse;
	}
	
	
}
