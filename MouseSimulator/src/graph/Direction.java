package graph;

public enum Direction {
	NORTH(0, -1), SOUTH(0, 1), WEST(-1, 0), EAST(1, 0);

	public final int x;
	public final int y;

	Direction(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public Direction getOpposite() {
		switch (this) {
		case EAST:
			return WEST;
		case NORTH:
			return SOUTH;
		case SOUTH:
			return NORTH;
		case WEST:
			return EAST;
		}
		throw new IllegalStateException("What direction are you going?");
	}

	public static Direction[] latPerpendiular = new Direction[] { NORTH, SOUTH };
	public static Direction[] horPerpendiular = new Direction[] { EAST, WEST };

	public Direction[] getPerpendicular() {
		switch (this) {
		case EAST:
		case WEST:
			return latPerpendiular;
		case NORTH:
		case SOUTH:
			return horPerpendiular;
		}
		throw new IllegalStateException("What direction are you going?");
	}
		
	public static final Direction[] vals = Direction.values();
}
