package application.constants;

public enum Action {
	//enum for movement of cursor on the board
	UP(0,-5),
	DOWN(0,5),
	LEFT(-5,0),
	RIGHT(5,0),
	NONE(0,0);
	
	final int dx,dy;
	
	
	Action(int dx,int dy){
		this.dx = dx;
		this.dy = dy;
	}
}
