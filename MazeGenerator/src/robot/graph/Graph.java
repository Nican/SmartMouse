package robot.graph;

import java.util.ArrayList;
import java.util.List;

public class Graph {

	public final static int SIZE = 16;

	final Vertex verticies[][] = new Vertex[SIZE][SIZE];

	public final List<Vertex> centerVerticies = new ArrayList<>();

	public Graph() {

		int x, y;

		for (x = 0; x < SIZE; x++) {
			for (y = 0; y < SIZE; y++) {
				verticies[x][y] = new Vertex(x, y, this);
			}
		}

		// First generate the center square
		Vertex center1 = get(Graph.SIZE / 2 - 1, Graph.SIZE / 2 - 1);
		Vertex center2 = get(Graph.SIZE / 2, Graph.SIZE / 2 - 1);
		Vertex center3 = get(Graph.SIZE / 2, Graph.SIZE / 2);
		Vertex center4 = get(Graph.SIZE / 2 - 1, Graph.SIZE / 2);

		centerVerticies.add(center1);
		centerVerticies.add(center2);
		centerVerticies.add(center3);
		centerVerticies.add(center4);

	}

	public Vertex get(int x, int y) {
		return verticies[x][y];
	}

	public boolean isCenter(Vertex v) {
		return centerVerticies.contains(v);
	}

}
