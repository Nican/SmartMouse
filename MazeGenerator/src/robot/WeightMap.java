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
		set(start, 0);

		List<Vertex> nextSearch = new ArrayList<>();
		int weight = 1;
		nextSearch.add(start);

		do {
			nextSearch = searchBFS(nextSearch, weight++);
		} while (!nextSearch.isEmpty());

	}

	private List<Vertex> searchBFS(List<Vertex> search, int weight) {

		List<Vertex> nextSearch = new ArrayList<>();

		for (Vertex current : search) {

			boolean added = false;

			for (Direction direction : Direction.values()) {
				Vertex relative = current.getRelative(direction);

				if (relative == null)
					continue;

				if (get(relative) != -1)
					continue;

				if (mouse.knowsEdge(current, relative))
					continue;

				set(relative, weight);

				nextSearch.add(relative);

				added = true;

			}

			// We have reached a dead end, look back and mark everything as
			// impossible to go to.
			if (added == false && isDeadEnd(current)) {
				foundDeadEnd(current);
			}

		}

		return nextSearch;

	}

	private boolean isDeadEnd(Vertex vertex) {
		int count = 0;

		for (Direction direction : Direction.values()) {
			Vertex relative = vertex.getRelative(direction);

			if (relative == null) {
				count++;
				continue;
			}

			if (get(relative) == Integer.MAX_VALUE) {
				count++;
				continue;
			}

			if (mouse.knowsEdge(vertex, relative)) {
				count++;
				continue;
			}
		}

		return count >= 3;
	}

	private void foundDeadEnd(Vertex current) {

		// System.out.println("Dead end at: " + previus);

		set(current, Integer.MAX_VALUE);

		// It is a dead end, it can only have 1 neighbor
		current = current.neighbors.get(0);

		while (true) {

			List<Vertex> possibleNext = new ArrayList<>();

			for (Direction direction : Direction.values()) {

				Vertex next = current.getRelative(direction);

				if (next == null)
					continue;

				// A dead end with another dead end, is a dead end
				if (get(next) == Integer.MAX_VALUE)
					continue;

				// Has the mouse learned there is a wall between these two?
				if (mouse.knowsEdge(current, next))
					continue;

				// The center is where we want to go!
				if (graph.isCenter(next))
					continue;

				possibleNext.add(next);
			}

			if (possibleNext.size() > 1)
				return;

			if (possibleNext.size() == 0)
				throw new IllegalStateException(
						"How do we reach a deadend with a deadend?");

			set(current, Integer.MAX_VALUE);

			current = possibleNext.get(0);

			// System.out.println("\tAdding to dead end: " + current);
		}

	}

	public void set(Vertex v, int weight) {
		weights[v.x][v.y] = weight;
	}

	public int get(Vertex v) {
		return get(v.x, v.y);
	}

	public int get(int x, int y) {
		return weights[x][y];
	}
}
