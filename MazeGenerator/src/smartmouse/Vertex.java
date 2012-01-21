package smartmouse;

import java.util.ArrayList;

public class Vertex {
	
	ArrayList<Vertex> neighbors = new ArrayList<>();
	
	final int x;
	final int y;
	final Graph graph;
	
	public Vertex( int x, int y, Graph g ){
		this.x = x;
		this.y = y;
		this.graph = g;
	}
	
	void addNeighbor( Vertex vertex ){
		
		if(vertex.equals(this) ){
			throw new IllegalStateException("This is not a loop edge!");
		}
		
		if( Math.abs(x - vertex.x) > 1 || Math.abs(y - vertex.y) > 1  ){
			throw new IllegalStateException("Connecting vertexes that are too far away!");
		}
		
		neighbors.add(vertex);
		
	}
	
	public boolean emptyNeighbors(){
		return neighbors.isEmpty();
	}
	
	public Vertex getRelative(Direction direction) {
		int x2 = this.x + direction.x;
		int y2 = this.y + direction.y;
		
		if( x2 < 0 || x2 >= Graph.SIZE )
			return null;
		
		if( y2 < 0 || y2 >= Graph.SIZE )
			return null;
		
		return graph.get(x2, y2);
	}

	public boolean hasNeighbor(Direction direction) {
		Vertex relative = getRelative(direction);
		
		return relative != null && neighbors.contains(relative);		
	}
	
}
