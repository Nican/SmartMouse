package smartmouse;


public enum Direction {
	NORTH(0,-1),
	SOUTH(0,1),
	WEST(-1,0),
	EAST(1,0);
	
	public final int x;
	public final int y;
	
	Direction( int x, int y ){
		this.x = x;
		this.y = y;
	}
	
	public static Direction getOpposite( Direction direction ){
		switch( direction ){
		case EAST: return WEST;
		case NORTH: return SOUTH;
		case SOUTH: return NORTH;
		case WEST: return EAST;
		}
		throw new IllegalStateException("What direction are you going?");
	}
	
	public static Direction[] getPerpendicular( Direction direction ){
		switch( direction ){
		case EAST:
		case WEST:
			return new Direction[]{NORTH,SOUTH};
		case NORTH:
		case SOUTH:
			return new Direction[]{EAST,WEST};
		}
		throw new IllegalStateException("What direction are you going?");
	}
}
