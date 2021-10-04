package application;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class Board {
	private GridPane updatedBoard= new GridPane();
	private WhitePieceLoader white = new WhitePieceLoader();
	private BlackPieceLoader black = new BlackPieceLoader();
	
	Board(GridPane board,int[][] data,Pane cursor){
		//initialize the GridPane
		ObservableList<Node> nodes= board.getChildren();
		//read through the passed new board array
		for(int i = 0; i<8; i++) {
			for(int j=0; j<8; j++) {
						
				//initialize the board
						if(data[i][j]==2) {
							for(Node node:nodes) {
								Integer colIndex = GridPane.getColumnIndex(node);
							    Integer rowIndex = GridPane.getRowIndex(node);	
							    int r = rowIndex == null ? 0:rowIndex;
							    int c = colIndex == null ? 0:colIndex;
							    Pane currentPane = (Pane) node;
								if(r==i&&c==j) {
									currentPane.getChildren().add(white.getWhite());
								    break;
								}
							}
							//board.add(white.getWhite(), j, i);
						}
						else if(data[i][j]==1) {
							for(Node node:nodes) {
								Integer colIndex = GridPane.getColumnIndex(node);
							    Integer rowIndex = GridPane.getRowIndex(node);	
							    int r = rowIndex == null ? 0:rowIndex;
							    int c = colIndex == null ? 0:colIndex;
							    Pane currentPane = (Pane) node;
								if(r==i&&c==j) {
									currentPane.getChildren().add(black.getBlack());
								    break;
								}
							}
							//board.add(black.getBlack(), j, i);
						}
						else{
							for(Node node:nodes) {
								Integer colIndex = GridPane.getColumnIndex(node);
							    Integer rowIndex = GridPane.getRowIndex(node);	
							    int r = rowIndex == null ? 0:rowIndex;
							    int c = colIndex == null ? 0:colIndex;
							    Pane currentPane = (Pane) node;
								if(r==i&&c==j) {
									//if cursor is present at that position on grid
									if(currentPane.getChildren().contains(cursor)) {
								    	
								    	//clear the children from the board that is our pawn and cursor
								    	currentPane.getChildren().clear();
								    	//then add the cursor at the same position
								    	currentPane.getChildren().add(cursor);
								    	continue;
								    }
								    else {
								    	//if cursor is not present just clear the pane
								    	currentPane.getChildren().clear();
								    }
									
									break;
								}
							}
						}
			}
    	}
	}
	
	public GridPane returnBoard() {
		return updatedBoard;
	}
}
