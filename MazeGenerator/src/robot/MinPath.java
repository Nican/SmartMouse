package robot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;

import robot.graph.Direction;
import robot.graph.Graph;
import robot.graph.Vertex;

public class MinPath {

	public final Graph graph;
	public final Mouse mouse;
	public final Stack<Vertex> path = new Stack<>();
	public final int maxSearch = Graph.SIZE * Graph.SIZE;

	public Comparator<Vertex> compartor = new Comparator<Vertex>() {
		@Override
		public int compare(Vertex v0, Vertex v1) {
			int weight0 = mouse.weightMap.get(v0);
			int weight1 = mouse.weightMap.get(v1);

			if (weight0 < weight1)
				return -1;
			if (weight0 > weight1)
				return 1;

			boolean discovered0 = mouse.isDiscovered(v0);
			boolean discovered1 = mouse.isDiscovered(v1);

			if (discovered0 && !discovered1)
				return -1;
			if (discovered1 && !discovered0)
				return 1;

			return Double.compare(distanceToCenter(v0), distanceToCenter(v1));
		}
	};

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

		while (map.get(current) > 0 && count++ < maxSearch) {
			Vertex min = getMinNeighbor(current);
			path.push(min);
			current = min;
		}

		if (count >= maxSearch) {
			System.out.println("Current path: " + path);
			throw new IllegalStateException(
					"MinPath can not be longer than there are blocks in the maze ("
							+ maxSearch + ")!");
		}

	}

	private Vertex getMinNeighbor(Vertex vertex) {
		List<Vertex> neighbors = new ArrayList<>(4);

		for (Direction direction : Direction.values()) {
			Vertex relative = vertex.getRelative(direction);

			if (relative == null)
				continue;

			if (mouse.knowsEdge(vertex, relative))
				continue;
			
			neighbors.add(relative);
		}
		
		if( neighbors.size() == 0 )
			return null;
		
		Collections.sort(neighbors, compartor);
		
		return neighbors.get(0);
	}

	@Override
	public String toString() {
		return String.format("MinPath<%s>", path.toString());
	}
	
	private double distanceToCenter(Vertex v){
		double dx = (Graph.SIZE / 2.0d) - v.x;
		double dy = (Graph.SIZE / 2.0d) - v.y; 
		
		return dx * dx + dy * dy;
	}

}
