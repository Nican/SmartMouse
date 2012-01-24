package robot;

import java.util.Stack;

import robot.graph.Direction;
import robot.graph.Graph;
import robot.graph.Vertex;

public class MinPath {

	public final Graph graph;
	public final Mouse mouse;
	public final Stack<Vertex> path = new Stack<>();

	public MinPath(Graph g, Mouse m) {
		this.graph = g;
		this.mouse = m;
	}

	public WeightMap getWeightMap() {
		return mouse.weightMap;
	}

	public void generate() {

		WeightMap map = getWeightMap();
		int count = 0;

		path.clear();

		Vertex current = null;

		for (Vertex vertex : this.graph.centerVerticies) {
			if (current == null || map.get(current) > map.get(vertex)) {
				current = vertex;
			}
		}

		path.push(current);

		while (map.get(current) > 0 && count++ < 1000 ) {
			Vertex min = getMinNeighbor( current );
			path.push(min);
			current = min;
		}
		
		if( count >= 1000 ){
			System.out.println("Such a long min path! " + path );
		}

	}

	private Vertex getMinNeighbor(Vertex vertex) {
		Vertex min = null;
		WeightMap map = getWeightMap();

		for (Direction direction : Direction.values()) {
			Vertex relative = vertex.getRelative(direction);

			if (relative == null)
				continue;

			if (mouse.knowsEdge(vertex, relative))
				continue;

			if (min == null || map.get(min) > map.get(relative))
				min = relative;
		}

		return min;

	}
	
	@Override
	public String toString(){
		return String.format("MinPath<%s>", path.toString() );
	}

}
