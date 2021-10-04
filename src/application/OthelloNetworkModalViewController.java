package application;


import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/// Main Controller class deals with menu bar Actions

public class OthelloNetworkModalViewController implements Initializable {	
	
	//creating instance of Model class
	private Model model = new Model();
	
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	private int[][] currentBoard;
	private int activePlayer=1;
	
	private int[][] validMoves;
	private String message;
	WhitePieceLoader white = new WhitePieceLoader();
    BlackPieceLoader black = new BlackPieceLoader();
    
    
    //Referring the id's and making the members in 
	@FXML
	BorderPane GameBoard = new BorderPane();
	
	@FXML
	GridPane MyGrid = new GridPane();
	
	@FXML 
	Pane cursor = new Pane();
	
	@FXML
	TextFlow Console = new TextFlow();
	
	@FXML 
	Text BlackScore= new Text();
	@FXML 
	Text WhiteScore= new Text();
	
	@FXML
	TextField messageField = new TextField();
	
	

	

	public void CloseApp(ActionEvent action) {
		Platform.exit();
		System.exit(0);
	}
		
	public void ScoreUpdate(int player) {
		if(player==1) {
		String score = String.valueOf(model.countSquares(1));
		BlackScore.setText(score);
		}
		String score = String.valueOf(model.countSquares(2));
		WhiteScore.setText(score);
	}
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		currentBoard = model.returnBoard();
		new Board(MyGrid, currentBoard, cursor);
	}
	

	//get 2d array with active cursor position
	//Pane is moving on the grid, the node which it related is our position
	
	@FXML
	public void MoveUp(MouseEvent event) {
		ObservableList<Node> nodes= MyGrid.getChildren();
		
		int r=0;
		int c=0;
		//accessing each node in the gridpane
		for(Node node:nodes) {
			Pane currentPane = (Pane) node;
			if(currentPane.getChildren().contains(cursor)) {
				Integer colIndex = GridPane.getColumnIndex(node);
			    Integer rowIndex = GridPane.getRowIndex(node);	
			    //This is because gridpane first index that is (0,0) return null,null
			    //so we are just initializing it if it return null
			    //to prevent it from throwing non null exception
			    r = rowIndex == null ? 0:rowIndex;
			    c = colIndex == null ? 0:colIndex;
			    
			    //once the node where movement is identified 
			    //remove the cursor from there and get the next node
			    //That is if we are moving up remove one index from the row 
			    currentPane.getChildren().remove(cursor);
			    Node nextNode = getNode(r-1, c);
			   
			    Pane nextPane = (Pane) nextNode;
			    //finally move the cursor to the new position
			    nextPane.getChildren().add(cursor);
			    break;
			}
		}
	}
	
	@FXML 
	public void ShowValidMoves(ActionEvent action) {
		//first initialize the 2d arrya in the modal instance with the vali moves cosntants that is 3
		//then get the array and store it in the validmoves array, modal.returnBoard() does it
		model.findLegalSquaresInEveryDirection(1);
		validMoves = model.returnBoard();
        //ObservableList<E>
		//then parse through the array
		//and place a green circle where there is valid move identifier 
			    for(int i = 0; i<8; i++) {
					for(int j=0; j<8; j++) {
						if(validMoves[i][j]==3) {
							Circle circle = new Circle();
							circle.setFill(Color.GREEN);
							circle.setRadius(10);
							//this is to place the circle in the center of the cell
							circle.centerXProperty().bind(MyGrid.widthProperty().divide(2));
					        circle.centerYProperty().bind(MyGrid.heightProperty().divide(2));
					        MyGrid.widthProperty();
					        MyGrid.heightProperty();
							MyGrid.add(circle, j, i);
						}
					}
			    }
	}
	
	private void removeCursor() {
		//get all the children nodes in the gridpane
		ObservableList<Node> nodes = MyGrid.getChildren();
		//run for each loop to iterate over each node
		for(Node node:nodes) {
			//get the current pane
			Pane currentPane = (Pane) node;
			//check if current pane has the cursor 
			//remove the cursor from there
			if(currentPane.getChildren().contains(cursor)) {
			    currentPane.getChildren().remove(cursor);
			    break;
			}
		}
	}
	
	//This is when move button is pressed
	// and board is needed to be updated
	@FXML
	public void MoveOnBoard(MouseEvent action) {
		//remove the cursor from the previous position 
		removeCursor();
		Node node = (Node)action.getTarget();
		
		//getting the index of the current node
		Integer colIndex = GridPane.getColumnIndex(node);
	    Integer rowIndex = GridPane.getRowIndex(node);	
	    int r = rowIndex == null ? 0:rowIndex;
	    int c = colIndex == null ? 0:colIndex;
	    
	    //this is to store the string message that is returned from the trymove
	    //these messages could be invalid move, player turn 
	    message = model.tryMove(r, c, activePlayer);
	    
	    //add the message to the console
	    Text text = new Text(message);
	    Console.getChildren().add(text);
	    Console.getChildren().add(new Text(System.lineSeparator()));
	    //initilize the board 2d array again after making the move
	    this.currentBoard = model.returnBoard();
	    this.activePlayer = model.switchPlayer(activePlayer);
	    //update the grid 
	    //place white piece where there is 2
	    //place black piece where there is 1 
		for(int i = 0; i<8; i++) {
			for(int j=0; j<8; j++) {
						if(currentBoard[i][j]==2) {
							MyGrid.add(white.getWhite(), j, i);
						}
						else if(currentBoard[i][j]==1) {
							MyGrid.add(black.getBlack(), j, i);
						}
		
					}
    	}
	}
	
	//similar to move up
	//only difference is we are doing row+1 to go down on the grid
	@FXML
	public void MoveDown(MouseEvent event) {
		ObservableList<Node> nodes= MyGrid.getChildren();
		int r=0;
		int c=0;
		
		for(Node node:nodes) {
			Pane currentPane = (Pane) node;
			if(currentPane.getChildren().contains(cursor)) {
				Integer colIndex = GridPane.getColumnIndex(node);
			    Integer rowIndex = GridPane.getRowIndex(node);	
			    r = rowIndex == null ? 0:rowIndex;
			    c = colIndex == null ? 0:colIndex;
			    //for removing cursor and making sure it doesnt go outside the board 
			    currentPane.getChildren().remove(cursor);
			    if(r<8||c<8||r>-1||c>-1) {
			    	Node nextNode = getNode(r+1, c);
				    Pane nextPane = (Pane) nextNode;
				    nextPane.getChildren().add(cursor);
			    }  
			    break;
			}
		}
	}
	
	//geting each node in the grid 
	public Node getNode(int row,int col) {
		Node result = null;
	    ObservableList<Node> childrens = MyGrid.getChildren();		
	    for (Node node : childrens) {
	    	Integer colIndex = GridPane.getColumnIndex(node);
		    Integer rowIndex = GridPane.getRowIndex(node);	
		    int r = rowIndex == null ? 0:rowIndex;
		    int c = colIndex == null ? 0:colIndex;
	        if(r == row && c == col) {
	            result = node;
	            break;
	        }
	    }
	    return result;
	}
	
	//similar to move up
	//only difference is we do coloumn index + 1 to move to right
	@FXML
	public void MoveRight(MouseEvent event) {
		ObservableList<Node> nodes= MyGrid.getChildren();
		int r=0;
		int c=0;
		for(Node node:nodes) {
			Pane currentPane = (Pane) node;
			if(currentPane.getChildren().contains(cursor)) {
				Integer colIndex = GridPane.getColumnIndex(node);
			    Integer rowIndex = GridPane.getRowIndex(node);	
			    r = rowIndex == null ? 0:rowIndex;
			    c = colIndex == null ? 0:colIndex;
			    currentPane.getChildren().remove(cursor);
			    Node nextNode = getNode(r, c+1);
			    Pane nextPane = (Pane) nextNode;
			    nextPane.getChildren().add(cursor);
			    break;
			}
		}
	}
	
	//similar to move up
	//only difference is we do coloumn index - 1 to move to right
	@FXML
	public void MoveLeft(MouseEvent event) {
		ObservableList<Node> nodes= MyGrid.getChildren();
		int r=0;
		int c=0;
		for(Node node:nodes) {
			Pane currentPane = (Pane) node;
			if(currentPane.getChildren().contains(cursor)) {
				Integer colIndex = GridPane.getColumnIndex(node);
			    Integer rowIndex = GridPane.getRowIndex(node);	
			    r = rowIndex == null ? 0:rowIndex;
			    c = colIndex == null ? 0:colIndex;
			    currentPane.getChildren().remove(cursor);
			    Node nextNode = getNode(r, c-1);
			    Pane nextPane = (Pane) nextNode;
			    nextPane.getChildren().add(cursor);
			    break;
			}
		}
	}
	
	
	//this when the move button is pressed
	@FXML
	public void Move(ActionEvent event) {
		
		//get cursor 
		Node pane =  cursor.getParent();
		ObservableList<Node> nodes= MyGrid.getChildren();
		int r=0;
		int c=0;
		for(Node node:nodes) {
			Integer colIndex = GridPane.getColumnIndex(node);
		    Integer rowIndex = GridPane.getRowIndex(node);	
		    r = rowIndex == null ? 0:rowIndex;
		    c = colIndex == null ? 0:colIndex;
		    if(node.equals(pane)) {
		    	message = model.tryMove(r, c, activePlayer);
		    	Text text = new Text(message);
		    	Console.getChildren().add(text);
		    	Console.getChildren().add(new Text(System.lineSeparator()));
		    	ScoreUpdate(activePlayer);
		    	this.currentBoard = model.returnBoard();
		    }
		}
		//doesnt switch player on invalid move, just compare the string returned from trymove method in model
		if(message != "invalid move") {
			this.activePlayer = model.switchPlayer(activePlayer);
		}
		for(int i = 0; i<8; i++) {
			for(int j=0; j<8; j++) {
						if(currentBoard[i][j]==2) {
							MyGrid.add(white.getWhite(), j, i);
						}
						else if(currentBoard[i][j]==1) {
							MyGrid.add(black.getBlack(), j, i);
						}
		
					}
    	}
		
	}
	
	@FXML
	public void NewGame(ActionEvent event) {
		currentBoard = model.returnBoard();
		try {
		//set new board	
		new Board(MyGrid, currentBoard,cursor);
		}
		catch(Exception e) {
		}
	}
	
	
	
	
	
	@FXML
	public void SaveGame(ActionEvent action) {
		if(currentBoard==null) {
			Console.getChildren().add(new Text("Please Start New Game"));
			Console.getChildren().add(new Text(System.lineSeparator()));
		}
		else {
		//pass the 2d array to model class
		//where it is formatted and stored in the text file
		model.saveGame(currentBoard);
		Console.getChildren().add(new Text("Game Saved"));
		Console.getChildren().add(new Text(System.lineSeparator()));
		}
	}
	
	@FXML
	public void LoadGame(ActionEvent action) {
		//initialize the array again to loaded array from the text file 
		model.loadSaved();
		//print the required messsage on the cosole 
		if(model.returnBoard()==null) {
			Console.getChildren().add(new Text("No Saved Games To Load"));
			Console.getChildren().add(new Text(System.lineSeparator()));
		}
		Console.getChildren().add(new Text("Game Loaded, Start new Game now"));
		Console.getChildren().add(new Text(System.lineSeparator()));
	}
	
	
	
	
	@FXML
	public void OpenHelp(ActionEvent action) {
		 final Stage dialog = new Stage();
         dialog.initModality(Modality.APPLICATION_MODAL);
         dialog.initOwner(stage);
         VBox dialogVbox = new VBox(20);
         dialogVbox.getChildren().add(new Text("OtHello Game"));
         dialogVbox.getChildren().add(new Text("By Arshdeep Kaur"));
         dialogVbox.getChildren().add(new Text("July 2021"));
         Button btn = new Button();
         btn.setText("Ok");
         dialogVbox.setAlignment(Pos.CENTER);
         dialogVbox.setStyle("-fx-background-color: #FFB6C1;");
         btn.setOnAction(
        	        new EventHandler<ActionEvent>() {
        	            @Override
        	            public void handle(ActionEvent event) {
        	                dialog.close();
        	            }
        	         });
         dialogVbox.getChildren().add(btn);
         Scene dialogScene = new Scene(dialogVbox, 300, 200);
         dialog.setScene(dialogScene);
         dialog.initStyle(StageStyle.UNDECORATED);
         dialog.show();
	}
//	@FXML
//	public void GetMouseClick(MouseEvent event,String movement) {
//		Switch(movement)
//	}
	@FXML
	public void SwitchClassic(ActionEvent event) throws IOException{
		try {
//			CanadianBoardLoader loadCad = new CanadianBoardLoader();
//			GameBoard.setCenter(loadCad.getCanadianPane());
			ObservableList<Node> children = MyGrid.getChildren();
			for(Node node:children) {
				
			    if (node != MyGrid) {
			        Node parent;
			        while ((parent = node.getParent()) != MyGrid) {
			            node = parent;
			        }
			    }
			    Integer colIndex = GridPane.getColumnIndex(node);
			    Integer rowIndex = GridPane.getRowIndex(node);	
			    int r = rowIndex == null ? 0:rowIndex;
			    int c = colIndex == null ? 0:colIndex;
			    for(int i =0; i<8; i++) {
			    	for(int j=0; j<8; j++){
			    		if(r==i&&c==j) {
			    			 if ((r+c) % 2 == 0) {
			    				 node.setStyle("-fx-background-color: #000000");
			                 }
			    		}	
			    	}
			    }
			}
			stage =(Stage) root.getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
		}
		catch(Exception e) {
			
		}
			
	}	
	
	@FXML 
	public void ClearValid() {
		validMoves = null;
		stage =(Stage) root.getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	@FXML
	public void SwitchCanadian(ActionEvent event) throws IOException{
			try {
				
				ObservableList<Node> children = MyGrid.getChildren();
				for(Node node:children) {
					
				    if (node != MyGrid) {
				        Node parent;
				        while ((parent = node.getParent()) != MyGrid) {
				            node = parent;
				        }
				    }
				    Integer colIndex = GridPane.getColumnIndex(node);
				    Integer rowIndex = GridPane.getRowIndex(node);	
				    int r = rowIndex == null ? 0:rowIndex;
				    int c = colIndex == null ? 0:colIndex;
				    for(int i =0; i<8; i++) {
				    	for(int j=0; j<8; j++){
				    		if(r==i&&c==j) {
				    			 if ((r+c) % 2 == 0) {
				    				 node.setStyle("-fx-background-color: #ff0000");
				                 }
				    		}	
				    	}
				    }
				}
				stage =(Stage) root.getScene().getWindow();
				scene = new Scene(root);
				stage.setScene(scene);
				stage.show();
			}
			catch(Exception e) {
				
			}
								
	}	
	
	
	
	//for switching between different modes for the game, this directly refer to the switch case in the modal class
	//and initialize array accordingly
	@FXML
	public void NormalMode(ActionEvent action) {
		model.prepareBoard(0);
		currentBoard = model.returnBoard();
		new Board(MyGrid, currentBoard, cursor);
	}
	@FXML
	public void CornerTest(ActionEvent action) {
		model.prepareBoard(1);
		this.currentBoard = null;
		this.currentBoard = model.returnBoard().clone();
		new Board(MyGrid, currentBoard, cursor);
	}
	@FXML
	public void SideTest(ActionEvent action) {
		model.prepareBoard(2);
		currentBoard = model.returnBoard();
		new Board(MyGrid, currentBoard, cursor);
	}
	@FXML
	public void OneCapture(ActionEvent action) {
		model.prepareBoard(3);
		currentBoard = model.returnBoard();
		new Board(MyGrid, currentBoard, cursor);
	}
	@FXML
	public void TwoCapture(ActionEvent action) {
		model.prepareBoard(4);
		currentBoard = model.returnBoard();
		new Board(MyGrid, currentBoard, cursor);
	}
	@FXML
	public void EmptyBoard(ActionEvent action) {
		model.prepareBoard(5);
		currentBoard = model.returnBoard();
		new Board(MyGrid, currentBoard, cursor);
	}
	@FXML
	public void InnerSquareTest(ActionEvent action) {
		model.prepareBoard(6);
		currentBoard = model.returnBoard();
		new Board(MyGrid, currentBoard, cursor);
	}
	@FXML
	public void UpOrientation(ActionEvent action) {
		model.prepareBoard(7);
		currentBoard = model.returnBoard();
		new Board(MyGrid, currentBoard, cursor);
	}	
	
	@FXML
	public void command(ActionEvent action){
		switch (messageField.getText()) {
		case "/help":
			Console.getChildren().add(new Text("/help: this Message"));
			Console.getChildren().add(new Text(System.lineSeparator()));
			Console.getChildren().add(new Text("/bye: disconnect"));
			Console.getChildren().add(new Text(System.lineSeparator()));
			Console.getChildren().add(new Text("/who: shows the name of connected player"));
			Console.getChildren().add(new Text(System.lineSeparator()));
			break;
		case "/bye":
			
			break;
		case "/who":
			
			break;		
		default:
			Console.getChildren().add(new Text("use /help for commands"));
			Console.getChildren().add(new Text(System.lineSeparator()));
			break;
		}
	}
	@FXML
	public void disconnect(ActionEvent action) {
		Console.getChildren().add(new Text("Disconnected"));
		Console.getChildren().add(new Text(System.lineSeparator()));
	}
	
	//opening the network dialog 
	@FXML
	public void createNewConnection(ActionEvent action) {
		 //opening new network dialog
		 new NetworkDialog(stage,Console);
		 
	}
}
