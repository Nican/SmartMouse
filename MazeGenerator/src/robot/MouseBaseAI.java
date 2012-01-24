package robot;

import java.util.ArrayList;
import java.util.List;

import robot.graph.Direction;
import robot.graph.Graph;
import robot.graph.Vertex;

public abstract class MouseBaseAI {
	
	public final Mouse mouse;
	
	public MouseBaseAI( Mouse m ){
		this.mouse = m;
	}
	
	public Graph getGraph(){
		return mouse.graph;
	}
	
	/**
	 * Returns a list of moves that the mouse can do
	 * @return
	 */
	public List<Direction> possibleMoves(){
		return possibleMoves(mouse.current);
	}
	
	public List<Direction> possibleMoves( Vertex vertex ){
		List<Direction> directions = new ArrayList<>();
		
		for( Direction direction : Direction.values() ){	
			if( vertex.hasNeighbor(direction) )
				directions.add(direction);
		}
		
		return directions;
	}
	
	public List<Direction> possibleNewMoves(){
		List<Direction> directions = new ArrayList<>();
		
		for( Direction direction : Direction.values() ){	
			if( canTravel(direction) && mouse.inHistory(mouse.current.getRelative(direction)) )
				directions.add(direction);
			
		}
		
		return directions;		
	}
	
	public void think(){
		
		Direction direction = getNextMove();		
		
		if( direction != null )
			mouse.move(direction);
		
	}
	
	/**
	 * Checks if the mouse can travel in the given direction
	 * @param direction the given direction
	 * @return 
	 */
	public boolean canTravel( Direction direction ){
		return mouse.current.hasNeighbor(direction);
	}
	
	/**
	 * Get the relative block to the one the mouse is now
	 * NOTE: It does not check if the mouse can travel there directly!
	 * @param direction relative N,S,W,E
	 * @return null if the block does not exist (Outside of the map), the relative vertex otherwise
	 */
	public Vertex getRelative( Direction direction ){
		return mouse.current.getRelative(direction);
	}
	
	/**
	 * Makes the AI think of the next move
	 * @return
	 */
	public abstract Direction getNextMove();
}
