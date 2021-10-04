package application;

public class AIModel {
	Model model = new Model();
	
	int[][] grid = model.returnBoard();
	
	//to store the test move to predict the valid move
	int[][] testGrid = grid;
	public void MakeAiMove(int rowStep, int colStep) {
		int rowX;
		int colX;
		
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				/* Find current players discs, move in a direction */
				if (testGrid[i][j] == 2) {
					rowX = i + rowStep;
					colX = j + colStep;

					/* Stay within board boundaries */
					if (rowX < 0 || rowX > 7 || colX < 0 || colX > 7) {
						continue;
					}
					/* If the next square is opposite, keep moving */
					while (testGrid[rowX][colX] == 1) {
						rowX = rowX + rowStep;
						colX = colX + colStep;
						/* Stay in bounds */
						if (rowX < 0 || rowX > 7 || colX < 0 || colX > 7) {
							break;
						}
						/* Then if the next square is open, it is legal */
						if (testGrid[rowX][colX] == 0) {
							testGrid[rowX][colX]=3;
							model.tryMove(rowX, colX, 2);
						}
					}
				}
			}
		}
	}
}
