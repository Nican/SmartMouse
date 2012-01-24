package robot;

import java.util.Stack;

import robot.graph.Direction;
import robot.graph.Vertex;

/**
 * Simplests of implementation of the mouse
 * @author Nican
 *
 */
public class BasicMouseAI extends MouseBaseAI {
	
	int direction = 1;

	public BasicMouseAI(Mouse m) {
		super(m);
	}

	@Override
	public Direction getNextMove() {
		
		Stack<Vertex> minPath = getMinPath();
		
		int index = minPath.indexOf(mouse.current);
		
		if( index != -1 ){
			System.out.println("Found mouse at index: " + index);
			
			if( index == 0 ){
				if( direction == 1 ){
					direction *= -1;
					return null;
				}
			} else if ( index == minPath.size() - 1 ){
				if( direction == -1 ){
					return null;
				}
			}
			
			return mouse.current.getDirection( minPath.get(index-direction) );
		}
		
		//Check if the min path is not infact empty, if it is, what the hell is happening?!
		if( minPath.isEmpty() ){
			throw new IllegalStateException("We can not find our way to the 'non-existant' min path!");
		}
		
		Stack<Vertex> path = pathToMinpath();		
		
		if( path.size() == 1 ){
			return null;
		}
		
		return path.get(0).getDirection(path.get(1));
	}
	
	public Stack<Vertex> getMinPath(){
		return mouse.minPath.path;
	}
	
	public Stack<Vertex> pathToMinpath(){
		
		Stack<Vertex> path = new Stack<>();
		
		//If we are in a dead end, just get out there, do Not do iterative!
		double delta = mouse.getWeightMap().get(mouse.current) == Integer.MAX_VALUE ? 10.0 : 1.0;
		double depth = delta;
		
		path.push(mouse.current);
		
		//Using a Iterative deepening depth-first search
		//http://en.wikipedia.org/wiki/Iterative_deepening_depth-first_search
		//It is inefficient, but it will have to do for now
		
		while( !depthSearch( path, depth ) ){
			depth += delta;
		}
		
		System.out.println("Path to min: " + path );
		
		return path;
		
	}
	
	public boolean depthSearch( Stack<Vertex> path, double depth ){
		
		Stack<Vertex> minPath = getMinPath();
		
		if( depth < 1.0 )
			return false;
		
		Vertex current = path.peek();
		
		for( Direction direction : Direction.vals ){
			
			Vertex relative = current.getRelative(direction);
			
			if( relative == null )
				continue;
			
			if( path.contains(relative))
				continue;
			
			if( mouse.knowsEdge(current, relative) )
				continue;
			
			path.push(relative);
			
			if(minPath.contains(relative)){
				return true;
			}
			
			//Magical variable of 3/4 of a weight not to travel in the same path
			double weight = mouse.isDiscovered(relative) ? 1.0 : 0.5;
			
			if( depthSearch(path, depth - weight) == true )
				return true;
			
			path.pop();
			
		}
		
		return false;
		
	}

}
