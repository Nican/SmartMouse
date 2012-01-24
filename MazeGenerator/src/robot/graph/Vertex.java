package robot.graph;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Vertex implements Serializable {

	private static final long serialVersionUID = 6437384775241005915L;
	public List<Vertex> neighbors = new ArrayList<>();

	public final int x;
	public final int y;
	final Graph graph;

	public Vertex(int x, int y, Graph g) {
		this.x = x;
		this.y = y;
		this.graph = g;
	}

	/**
	 * Adds a neighbor to this vertex Do not forget to add a neighor from that
	 * vertex to this one
	 * 
	 * @param vertex
	 */
	void addNeighbor(Vertex vertex) {

		if (vertex.equals(this)) {
			throw new IllegalStateException("This is not a loop edge!");
		}

		int deltaX = Math.abs(x - vertex.x);
		int deltaY = Math.abs(y - vertex.y);

		// Make sure it is not too far away, or diagonal from each other
		if (deltaX > 1 || deltaY > 1 || deltaX == deltaY) {
			throw new IllegalStateException(
					"Connecting vertexes that are too far away!");
		}

		this.neighbors.add(vertex);
		vertex.neighbors.add(this);

	}

	/**
	 * Checks if the vertex has no connected edges
	 * 
	 * @return
	 */
	public boolean emptyEdges() {
		return neighbors.isEmpty();
	}

	/**
	 * Gets the vertex relative to this one, null if such vertex does not exist
	 * (Outside of the map)
	 * 
	 * @param direction
	 * @return
	 */
	public Vertex getRelative(Direction direction) {
		int x2 = this.x + direction.x;
		int y2 = this.y + direction.y;

		if (x2 < 0 || x2 >= Graph.SIZE)
			return null;

		if (y2 < 0 || y2 >= Graph.SIZE)
			return null;

		return graph.get(x2, y2);
	}

	/**
	 * Checks if the vertex has a edge to the given direction
	 * 
	 * @param direction
	 * @return
	 */
	public boolean hasNeighbor(Direction direction) {
		Vertex relative = getRelative(direction);

		return relative != null && neighbors.contains(relative);
	}
	
	public boolean hasNeighbor(Vertex v2) {
		return this.neighbors.contains(v2);
	}

	@Override
	public String toString() {
		return String.format("Vertex(%d,%d)", x, y);
	}

	public Direction getDirection(Vertex v2) {

		if (this.x == v2.x) {
			if (this.y - 1 == v2.y)
				return Direction.NORTH;
			if (this.y + 1 == v2.y)
				return Direction.SOUTH;
		}

		if (this.y == v2.y) {
			if (this.x - 1 == v2.x)
				return Direction.WEST;
			if (this.x + 1 == v2.x)
				return Direction.EAST;
		}

		return null;

	}

}
