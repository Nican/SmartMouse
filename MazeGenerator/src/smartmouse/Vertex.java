package smartmouse;

import java.util.ArrayList;
import java.util.List;

public class Vertex {
	
	List<Vertex> neighbors = new ArrayList<>();
	
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
		
		int deltaX = Math.abs(x - vertex.x);
		int deltaY = Math.abs(y - vertex.y);
		
		//Make sure it is not too far away, or diagonal from each other
		if( deltaX > 1 || deltaY > 1 || deltaX == deltaY ){
			throw new IllegalStateException("Connecting vertexes that are too far away!");
		}
		
		this.neighbors.add(vertex);
		vertex.neighbors.add(this);
		
	}
	
	/**
	 * Checks if the vertex has no connected edges
	 * @return
	 */
	public boolean emptyEdges(){
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
	
	@Override
	public String toString(){
		return String.format("Vertex(%d,%d)", x, y);
	}
	
}
