package robot;

import java.util.ArrayList;
import java.util.List;

import smartmouse.Direction;

public abstract class MouseBaseAI {
	
	final Mouse mouse;
	
	public MouseBaseAI( Mouse m ){
		this.mouse = m;
	}
	
	public List<Direction> possibleMoves(){
		List<Direction> directions = new ArrayList<>();
		
		for( Direction direction : Direction.values() ){
			
			if( mouse.current.hasNeighbor(direction) )
				directions.add(direction);
			
		}
		
		return directions;
	}
	
	public void think(){
		
		Direction direction = getNextMove();		
		
		if( direction != null )
			mouse.move(direction);
		
	}
	
	public abstract Direction getNextMove();
}
