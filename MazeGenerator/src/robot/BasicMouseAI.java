package robot;

import java.util.List;

import smartmouse.Direction;
import smartmouse.Vertex;

/**
 * Simplests of implementation of the mouse
 * @author Nican
 *
 */
public class BasicMouseAI extends MouseBaseAI {

	public BasicMouseAI(Mouse m) {
		super(m);
	}

	@Override
	public Direction getNextMove() {
		List<Direction> directions = possibleMoves();
		
		//Look for all possible movements we can do (N,S,W,E)
		for( Direction direction : directions ){
			
			Vertex relative = getRelative(direction);
			
			//If we already have not gone there
			if( !mouse.inHistory(relative)){
				return direction;
			}
			
		}
		
		return null;
	}

}
