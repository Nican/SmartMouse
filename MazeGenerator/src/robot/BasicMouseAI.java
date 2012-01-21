package robot;

import java.util.List;

import smartmouse.Direction;
import smartmouse.Vertex;

public class BasicMouseAI extends MouseBaseAI {

	public BasicMouseAI(Mouse m) {
		super(m);
	}

	@Override
	public Direction getNextMove() {
		List<Direction> directions = possibleMoves();
		
		for( Direction direction : directions ){
			
			Vertex relative = mouse.current.getRelative(direction);
			
			if( !mouse.inHistory(relative)){
				return direction;
			}
			
		}
		
		return null;
	}

}
