package robot;

import java.util.Stack;

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
		
		Stack<Vertex> minPath = getMinPath();
		
		int index = minPath.indexOf(mouse.current);
		
		if( index != -1 ){
			System.out.println("Found mouse at index: " + index);
			
			if( index == 0 )
				return null;
			
			return mouse.current.getDirection( minPath.get(index-1) );
		}
		
		Stack<Vertex> path = pathToMinpath();		
		
		if( path.size() == 1 ){
			return null;
		}
		
		return path.get(0).getDirection(path.get(1));
		
		/*
		Stack<Vertex> minPath = getMinPath();
		
		
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
		*/
	}
	
	public Stack<Vertex> getMinPath(){
		return mouse.minPath.path;
	}
	
	public Stack<Vertex> pathToMinpath(){
		
		Stack<Vertex> path = new Stack<>();
		int depth = 1;
		
		path.push(mouse.current);
		
		//Using a Iterative deepening depth-first search
		//http://en.wikipedia.org/wiki/Iterative_deepening_depth-first_search
		//It is inefficient, but it will have to do for now
		
		while( !DepthSearch( path, depth++ ) );
		
		return path;
		
	}
	
	public boolean DepthSearch( Stack<Vertex> path, int depth ){
		
		Stack<Vertex> minPath = getMinPath();
		
		if( depth <= 0 )
			return false;
		
		Vertex current = path.peek();
		
		for( Direction direction : Direction.values() ){
			
			Vertex relative = current.getRelative(direction);
			
			if( relative == null )
				continue;
			
			if( mouse.knowsEdge(current, relative) )
				continue;
			
			path.push(relative);
			
			if(minPath.contains(relative)){
				return true;
			}
			
			if( DepthSearch(path, depth - 1) == true )
				return true;
			
			path.pop();
			
		}
		
		return false;
		
	}

}
