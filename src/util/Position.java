package util;

public class Position {
	private int x;
	private int y;
	
	public Position(int x, int y) {
		this.x=x;
		this.y=y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	@Override
	public boolean equals(Object obj) {
		Position pos = (Position) obj;
		return pos.getX()==x && pos.getY()==y;
	}
	
	@Override
	public String toString() {
		return String.valueOf(x) + String.valueOf(y);
	}
}
