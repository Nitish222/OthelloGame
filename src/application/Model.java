package application;

import java.io.BufferedReader;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

import application.constants.Entity;
import application.constants.Messages;
public class Model {

		private int[][] board;
		
	    //Some class constants for your use:
	    public static final int NORMAL=0;
	    public static final int CORNER_TEST=1;
	    public static final int OUTER_TEST=2;
	    public static final int TEST_CAPTURE=3;
	    public static final int TEST_CAPTURE2=4;
	    public static final int UNWINNABLE=5;
	    public static final int INNER_TEST=6;
	    public static final int ARROW=7;

	    public static final int EMPTY=0;
	    public static final int BLACK=1;
	    public static final int WHITE=2;
	    public static final int LEGAL=3;
	   
	    public int captures=0;
	    
	    //Default constructor prepares a normal game.
	    public Model()
	    {
	        prepareBoard(NORMAL);
	    }
	    
	    
	    public int[][] returnBoard(){
	    	return this.board;
	    }
		public void prepareBoard(int mode)
		{
			switch (mode)
			{
			case CORNER_TEST: 
				this.board=new int[][]{
					{2, 0, 0, 0, 0, 0, 0, 1},
					{0, 1, 0, 0, 0, 0, 2, 0},
					{0, 0, 0, 0, 0, 0, 0, 0},
					{0, 0, 0, 0, 0, 0, 0, 0},
					{0, 0, 0, 0, 0, 0, 0, 0},
					{0, 0, 0, 0, 0, 0, 0, 0},
					{0, 1, 0, 0, 0, 0, 1, 0},
	                {2, 0, 0, 0, 0, 0, 0, 2}};
	            break;
	                
			case OUTER_TEST:
				this.board = new int[][] {
					{0, 0, 0, 0, 0, 0, 0, 0},
					{0, 2, 2, 2, 2, 2, 2, 0},
					{0, 2, 1, 1, 1, 1, 2, 0},
					{0, 2, 1, 0, 0, 1, 2, 0},
					{0, 2, 1, 0, 0, 1, 2, 0},
					{0, 2, 1, 1, 1, 1, 2, 0},
					{0, 2, 2, 2, 2, 2, 2, 0},
					{0, 0, 0, 0, 0, 0, 0, 0}};
				break;
	                
			case INNER_TEST:
				this.board = new int[][] {
					{2, 2, 2, 2, 2, 2, 2, 2},
					{2, 0, 0, 0, 0, 0, 0, 2},
					{2, 0, 2, 2, 2, 2, 0, 2},
					{2, 0, 2, 1, 1, 2, 0, 2},
					{2, 0, 2, 1, 1, 2, 0, 2},
					{2, 0, 2, 2, 2, 2, 0, 2},
					{2, 0, 0, 0, 0, 0, 0, 2},
					{2, 2, 2, 2, 2, 2, 2, 2}};
				break;
	                
			case UNWINNABLE:
				this.board = new int[][] {
					{0, 0, 0, 0, 0, 0, 0, 0},
					{0, 0, 0, 0, 0, 0, 0, 0},
					{0, 0, 0, 0, 0, 0, 0, 0},
					{0, 0, 0, 0, 0, 0, 0, 0},
					{0, 0, 0, 0, 0, 0, 0, 0},
					{0, 0, 0, 0, 0, 0, 0, 0},
					{0, 0, 0, 0, 0, 0, 0, 0},
					{0, 0, 0, 0, 0, 0, 0, 0}};
				break;
	                
			case TEST_CAPTURE:
				this.board=new int[][]{
					{0, 0, 0, 0, 0, 0, 0, 0},
					{0, 1, 1, 1, 1, 1, 1, 0},
					{0, 1, 1, 1, 1, 1, 1, 0},
					{0, 1, 2, 2, 2, 1, 1, 0},
					{0, 1, 2, 0, 2, 1, 1, 0},
					{0, 1, 2, 2, 2, 1, 1, 0},
					{0, 1, 1, 1, 1, 1, 1, 0},
					{0, 0, 0, 0, 0, 0, 0, 0}};
					break;
					
			case TEST_CAPTURE2:
				this.board=new int[][]{
					{1, 1, 1, 1, 1, 1, 1, 1},
					{1, 1, 1, 1, 1, 1, 1, 1},
					{1, 2, 2, 2, 1, 2, 1, 1},
					{1, 2, 2, 2, 2, 2, 1, 1},
					{1, 2, 2, 0, 2, 2, 1, 1},
					{1, 2, 2, 2, 2, 1, 1, 1},
					{1, 2, 1, 2, 2, 2, 1, 1},
					{1, 1, 1, 1, 1, 1, 1, 1}};
					break;
	                
	            case ARROW:
	             this.board=new int[][]{
	                {0, 0, 0, 1, 1, 0, 0, 0},
	                {0, 0, 1, 1, 1, 1, 0, 0},
	                {0, 1, 0, 1, 1, 0, 1, 0},
	                {1, 0, 0, 1, 1, 0, 0, 1},
	                {0, 0, 0, 1, 1, 0, 0, 0},
	                {0, 0, 0, 1, 1, 0, 0, 0},
	                {0, 0, 0, 1, 1, 0, 0, 0},
	                {0, 0, 0, 1, 1, 0, 0, 0}};
	                break;
	                
			default:
				this.board = new int[][]{
					{0, 0, 0, 0, 0, 0, 0, 0},
					{0, 0, 0, 0, 0, 0, 0, 0},
					{0, 0, 0, 0, 0, 0, 0, 0},
					{0, 0, 0, 2, 1, 0, 0, 0},
					{0, 0, 0, 1, 2, 0, 0, 0},
					{0, 0, 0, 0, 0, 0, 0, 0},
					{0, 0, 0, 0, 0, 0, 0, 0},
					{0, 0, 0, 0, 0, 0, 0, 0}};
			}	
		}
	    
	    //This method will return the value of a given square.
		public int getSquare(int row, int col)
		{
			int value = this.board[row][col];
			return value;
		}
			
		
		
		
		
		public int switchPlayer(int player) {
			   if (player == 1) {
			        return 2;
			    }
			   	else {
			        return 1;
			    }
		}
	    //Can the player make a valid move at the square specified?
		public boolean canMove(int row, int col, int player)
		{
			 //Makes sure adjacent and passed in position is not same color
	        if(board[row][col + 1] == WHITE
	                && player == BLACK){
	            //Start from adjacent + 1 and go to end
	            for (int newCol = col + 2; newCol < 8; newCol ++){
	                //If elements is empty return false
	                if (board[row][newCol] == 0) return false;
	                //if it hits black return true
	                if (board[row][newCol] == BLACK) return true;
	            }

	        }

	        else if (board[row][col+1] == BLACK
	                && player == WHITE){
	            for (int newCol = col + 2; newCol < 8; newCol ++){
	                if (board[row][newCol] == 0) return false;
	                if (board[row][newCol] == WHITE) return true;
	            }
	        }
	        else {
	            return false;
	        }

	        return false;
	    }
		
	    //The player is trying to move at the specified square.
	    //Return the number of chips captured, 0 for an invalid move.
		public String tryMove(int row, int col, int player)
		{
			findLegalSquaresInEveryDirection(player);
			if (board[row][col] == 3) {
				removeLegalMarkers();
				board[row][col] = player;
				flipDiscs(row,col,player);
//				findLegalSquaresInEveryDirection(oppositeDisc(player));
				endOfTurn(player);
				if (checkWin(player)) {
					return setLabelsWinner();
				}
				if(player==1) {
					return Messages.PLAYER_TWO_ACTIVE;
				}
				return Messages.PLAYER_ONE_ACTIVE;
			} else {
				return invalidSquare();
			}
	    }
		
		private String invalidSquare() {
			return "invalid move";
		}
		public int endOfTurn(int player) {
			if (changeTurn(countSquares(3) > 0, player)) {
				return player;
			} else {
				return oppositeDisc(player);
			}
		}
		
		public void findLegalSquaresInEveryDirection(int discColor) {
			for (int i = -1; i <= 1; i++) {
				for (int j = -1; j <= 1; j++) {
					findLegalSquares(discColor, i, j);
				}
			}
			
		}
		
		private String setLabelsWinner() {
			return Messages.GAME_COMPELTE_WON;
		}
		
		public boolean checkWin(int currentDiscColor) {
			if (countSquares(0) == 0 && countSquares(3) == 0) {
				return true;
			} else {
				findLegalSquaresInEveryDirection(currentDiscColor);
				if (countSquares(3) == 0) {
					findLegalSquaresInEveryDirection(oppositeDisc(currentDiscColor));
					if (countSquares(3) == 0)
						return true;
				}
			}
			return false;
		}
		
	
		
		public void flipDiscs(int row,int col,int player) {
			for (int i = -1; i <= 1; i++) {
				for (int j = -1; j <= 1; j++) {
					othelloFlip(row, col, player, i, j);
					
				}
			}
		}
		
		public void othelloFlip(int rowStart, int colStart, int turnColor, int rowStep, int colStep) {
			int rowX = rowStart + rowStep;
			int colX = colStart + colStep;

			/* Stay in bounds */
			if (rowX < 0 || rowX > 7 || colX < 0 || colX > 7) {
				return;
			}
			/**
			 * We need status of square in the direction we are checking Keep checking until
			 * we hit empty cells
			 */
			while (board[rowX][colX] == 1 || board[rowX][colX] == 2) {
				/**
				 * Return direction to flip chips Run else statement till we hit a cell with the
				 * same color
				 */
				if (board[rowX][colX] == turnColor) {
					while (!(rowStart == rowX && colStart == colX)) {
						board[rowX][colX] = turnColor;
						rowX = rowX - rowStep;
						colX = colX - colStep;
					}
					break;
				}
				/* Moving to next cell in direction to check for chip color change */
				else {
					rowX = rowX + rowStep;
					colX = colX + colStep;
				}

				/* Check to keep us on the board, break when we are off board */
				if (rowX < 0 || rowX > 7 || colX < 0 || colX > 7) {
					break;
				}
			}
		}
		
		public void removeLegalMarkers() {
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					if (board[i][j] == 3) {
						board[i][j] = 0;
					}
				}
			}
		}
		public void findLegalSquares(int currentDiscColor, int rowStep, int colStep) {
			int rowX;
			int colX;
			
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					/* Find current players discs, move in a direction */
					if (board[i][j] == currentDiscColor) {
						rowX = i + rowStep;
						colX = j + colStep;
						
						/* Stay within board boundaries */
						if (rowX < 0 || rowX > 7 || colX < 0 || colX > 7) {
							continue;
						}
						
						/* If the next square is opposite, keep moving */
						while (board[rowX][colX] == oppositeDisc(currentDiscColor)) {
							rowX = rowX + rowStep;
							colX = colX + colStep;
							/* Stay in bounds */
							if (rowX < 0 || rowX > 7 || colX < 0 || colX > 7) {
								break;
							}
							/* Then if the next square is open, it is legal */
							if (board[rowX][colX] == 0) {
								board[rowX][colX]=LEGAL;
							}
						}
					}
				}
			}
		}
		
		
		
		public boolean changeTurn(boolean legalMovesPresent, int whoseTurn) {
			if (legalMovesPresent) {
				whoseTurn = oppositeDisc(whoseTurn);
				return true;
			} else
				return false;
		}
	    
		
		public int oppositeDisc(int disc) {
			if (disc == 1)
				return 2;
			else
				return 1;
		}
		//How many chips does the specified player have in play?
	    public int countSquares(int player)
	    {
	    	int scoreW = 0;
	        int scoreB = 0;
	        
	        if(player == WHITE) {
	        	for (int i = 0; i < 8; i++) {
		            for (int j = 0; j < 8; j++) {
		                if (board[i][j] == WHITE){ scoreW++; }
		            }
		        }
	        	return scoreW;
	        }
	        for (int i = 0; i < 8; i++) {
	            for (int j = 0; j < 8; j++) {
	                if (board[i][j] == BLACK){ scoreB++; }
	            }
	        }
	        return scoreB;
	    }
	    
	    
	    //Saving and Loading
	    public boolean saveGame(int[][] currentBoard){
			StringBuilder builder = new StringBuilder();
			for(int i = 0; i < currentBoard.length; i++)//for each row
			{
			   for(int j = 0; j < currentBoard.length; j++)//for each column
			   {
			      builder.append( currentBoard[i][j]+"");//append to the output string
			      if(j < currentBoard.length - 1)//if this is not the last row element
			         builder.append(",");//then add comma (if you don't like commas you can use spaces)
			   }
			   builder.append("\n");//append new line at the end of the row
			}
			BufferedWriter writer;
			try {
				writer = new BufferedWriter(new FileWriter("SavedGane.txt"));
				writer.write(builder.toString());
				writer.close();
				return true;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return false;
		}
	    
		
		public void loadSaved() {
			String savedGameFile = "SavedGane.txt";
			BufferedReader reader;
			String line = "";
			int row = 0;
			try {
				reader = new BufferedReader(new FileReader(savedGameFile));
				while((line = reader.readLine()) != null)
				{
				   String[] cols = line.split(","); //note that if you have used space as separator you have to split on " "
				   int col = 0;
				   for(String  c : cols)
				   {
				      this.board[row][col] = Integer.parseInt(c);
				      col++;
				   }
				   row++;
				}
				for(int i = 0; i < this.board.length; i++)//for each row
				{
				   for(int j = 0; j < this.board.length; j++)//for each column
				   {
					   	System.out.print(this.board[i][j]);
				   }
				}
				reader.close();
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	    		
	}
		
