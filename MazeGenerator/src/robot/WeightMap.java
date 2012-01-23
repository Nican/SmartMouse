package robot;

import java.util.ArrayList;
import java.util.List;

import smartmouse.Direction;
import smartmouse.Graph;
import smartmouse.Vertex;

public class WeightMap {

	int[][] weights = new int[Graph.SIZE][Graph.SIZE];
	Graph graph;

	public final Mouse mouse;

	public final static Direction[] validSearch = new Direction[] {
			Direction.SOUTH, Direction.EAST };
	public final static Direction[] secondOptionSearch = new Direction[] {
			Direction.NORTH, Direction.WEST };

	public WeightMap(Graph g, Mouse mouse) {
		this.mouse = mouse;
		this.graph = g;

	}

	public void generate() {

		for (int x = 0; x < Graph.SIZE; x++)
			for (int y = 0; y < Graph.SIZE; y++)
				weights[x][y] = -1;

		// First we generate the weights from the known graph, using a DFS
		Vertex start = mouse.first();
		weights[start.x][start.y] = 0;
		
		List<Vertex> nextSearch = new ArrayList<>();
		int weight = 1;
		nextSearch.add(start);

		do{ 
			nextSearch = searchBFS(nextSearch, weight++);
		} while( !nextSearch.isEmpty() );

	}
	
	private List<Vertex> searchBFS(List<Vertex> search, int weight) {
		
		List<Vertex> nextSearch = new ArrayList<>();
		
		for( Vertex current : search ){
			
			for (Direction direction : Direction.values()) {
				Vertex relative = current.getRelative(direction);

				if (relative == null)
					continue;
				
				if (get(relative) != -1)
					continue;
				
				if (mouse.knowsEdge(current, relative))
					continue;
				
				weights[relative.x][relative.y] = weight;
				
				nextSearch.add(relative);			
				
			}
			
		}
		
		return nextSearch;
		
		
	}

	public int get(Vertex v) {
		return get(v.x, v.y);
	}

	public int get(int x, int y) {
		return weights[x][y];
	}
}
