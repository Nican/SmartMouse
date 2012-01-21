package robot;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import smartmouse.Direction;
import smartmouse.Vertex;

public class Mouse {

	public Vertex current;
	public List<Vertex> history = new ArrayList<>();
	public Set<Vertex> discovered = new HashSet<>();
	
	public Mouse( Vertex start ){
		current = start;
		
		history.add(current);
		discover(start);
	}
	
	public void move( Direction direction ){
		if( !current.hasNeighbor(direction))
			throw new IllegalStateException("Mouse can not move in that direction");
		
		current = current.getRelative(direction);
		
		history.add(current);
		
		discover(current);
	}
	
	public boolean inHistory( Vertex vertex ){
		return history.contains(vertex);
	}
	
	public boolean inDiscovered( Vertex vertex ){
		return discovered.contains(vertex);
	}
	
	public void discover( Vertex vertex ){
		discovered.add(vertex);
		
		for( Direction direction : Direction.values() ){			
			if( vertex.hasNeighbor(direction))
				discovered.add(vertex.getRelative(direction));
		}
	}	
	
}
