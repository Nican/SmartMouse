package smartmouse;

import java.util.HashSet;
import java.util.Set;

public class Vertex {
	
	Set<Vertex> neighbors = new HashSet<>();
	
	final int x;
	final int y;
	final Graph graph;
	
	public Vertex( int x, int y, Graph g ){
		this.x = x;
		this.y = y;
		this.graph = g;
	}
	
	/**
	 * Adds a neighbor to this vertex
	 * Do not forget to add a neighor from that vertex to this one
	 * @param vertex
	 */
	void addNeighbor( Vertex vertex ){
		
		if(vertex.equals(this) ){
			throw new IllegalStateException("This is not a loop edge!");
		}
		
		if( Math.abs(x - vertex.x) > 1 || Math.abs(y - vertex.y) > 1  ){
			throw new IllegalStateException("Connecting vertexes that are too far away!");
		}
		
		neighbors.add(vertex);
		
	}
	
	/**
	 * Checks if the vertex has no connected edges
	 * @return
	 */
	public boolean emptyNeighbors(){
		return neighbors.isEmpty();
	}
	
	/**
	 * Gets the vertex relative to this one, null if such vertex does not exist (Outside of the map)
	 * @param direction
	 * @return
	 */
	public Vertex getRelative(Direction direction) {
		int x2 = this.x + direction.x;
		int y2 = this.y + direction.y;
		
		if( x2 < 0 || x2 >= Graph.SIZE )
			return null;
		
		if( y2 < 0 || y2 >= Graph.SIZE )
			return null;
		
		return graph.get(x2, y2);
	}

	/**
	 * Checks if the vertex has a edge to the given direction
	 * @param direction
	 * @return
	 */
	public boolean hasNeighbor(Direction direction) {
		Vertex relative = getRelative(direction);
		
		return relative != null && neighbors.contains(relative);		
	}
	
}
