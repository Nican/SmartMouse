package graph;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Graph implements Serializable{

	private static final long serialVersionUID = 8368671287672227182L;
	public final static int SIZE = 16;

	final Vertex verticies[][] = new Vertex[SIZE][SIZE];
	
	public final List<Vertex> centerVerticies = new ArrayList<Vertex>();
	public final List<Vertex> allVerticies = new ArrayList<Vertex>(SIZE * SIZE);

	public Graph() {

		int x, y;

		for (x = 0; x < SIZE; x++) {
			for (y = 0; y < SIZE; y++) {
				Vertex vertex = new Vertex(x, y, this);
				
				verticies[x][y] = vertex;
				allVerticies.add(vertex);
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
	
	public void reset(){
		for( Vertex vertex : allVerticies ){
			vertex.neighbors.clear();
		}
	}

}
