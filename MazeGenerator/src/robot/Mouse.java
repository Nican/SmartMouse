package robot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import robot.graph.Direction;
import robot.graph.Graph;
import robot.graph.Vertex;

/**
 * The logical representation of the robot
 * 
 * @author Nican
 * 
 */
public class Mouse {

	public Vertex current;

	public List<Vertex> history = new ArrayList<>();
	public Set<Vertex> discovered = new HashSet<>();
	public HashMap<Vertex, Integer> weights = new HashMap<>();

	public final Graph graph;

	public WeightMap weightMap;
	public MinPath minPath;

	public boolean visitedCenter = false;

	public Mouse(Graph g, Vertex start) {
		current = start;
		graph = g;

		weightMap = new WeightMap(graph, this);
		minPath = new MinPath(graph, this);

		history.add(current);
		discover(start);

		update();
	}

	public Vertex first() {
		return history.get(0);
	}
	
	public boolean inPath(){
		return minPath.path.contains(current);
	}

	/**
	 * Makes the mouse move in the direction, throws an error if it can not do
	 * the move
	 * 
	 * @param direction
	 */
	public void move(Direction direction) {
		if (!current.hasNeighbor(direction))
			throw new IllegalStateException(
					"Mouse can not move in that direction");

		current = current.getRelative(direction);

		history.add(current);

		discover(current);

		if (visitedCenter == false && graph.isCenter(current))
			visitedCenter = true;

		update();
	}
	
	public void update(){
		System.out.println("Generating weight map");
		weightMap.generate();
		System.out.println("Generating min path");
		minPath.generate();
		System.out.println("\t Min path of size: " + minPath.path.size());
	}

	/**
	 * If the mouse has visited either of the vertexes, the mouse knows about
	 * the edges around it. Now consider:
	 * 
	 * <code>
	 * | 1 |
	 * |   |___ ___
	 * | 2   B
	 * |    ___ ___
	 * | 3   A 
	 * |    ___ ___
	 * | 4 |
	 * |   |
	 * </code> Since the mouse travels down from 1 to 4, he knows that must be a
	 * wall between A and B.
	 * 
	 * How about: If the robot has walked 1 2 3 7 8 9, we do not know if there
	 * is a wall between A and B <code>
	 *      ___ ___
	 * | 1 |   |   | 9 |
	 * |   |   |   |   |
	 * | 2   A ?  B  8 |   
	 * |       ?       |
	 * | 3 |   |   | 7 |
	 * |   |___|___|   |
	 * </code> (There is the expection of the center)
	 * 
	 * @param v1
	 * @param v2
	 * @return
	 */
	public boolean knowsEdge(Vertex v1, Vertex v2) {

		Direction direction = v1.getDirection(v2);

		if (direction == null)
			throw new IllegalStateException("Vertexes are not near each other!");

		if (inHistory(v1) || inHistory(v2))
			return !v1.hasNeighbor(v2);
		
		boolean center1 = graph.isCenter(v1);
		boolean center2 = graph.isCenter(v2);
		
		if (visitedCenter && (center1 || center2))
			return center1 != center2 && !v1.hasNeighbor(v2); //They can not both be true

		if (isDiscovered(v1) && isDiscovered(v2)) {

			for (Direction perpendicular : direction.getPerpendicular()) {

				Vertex relative1 = v1.getRelative(perpendicular);
				Vertex relative2 = v2.getRelative(perpendicular);

				if (!v1.hasNeighbor(relative1) || !v2.hasNeighbor(relative2))
					continue;

				if (history.contains(relative1) && history.contains(relative2)
						&& relative1.hasNeighbor(direction))
					return true;
			}

		}

		return false;
	}

	public WeightMap getWeightMap() {
		return weightMap;
	}

	/**
	 * Checks if the mouse has already traveled that vertex
	 * 
	 * @param vertex
	 * @return
	 */
	public boolean inHistory(Vertex vertex) {
		return history.contains(vertex);
	}

	/**
	 * Checks if the mouse has already traveled, or walked near by the vertex
	 * 
	 * @param vertex
	 * @return
	 */
	public boolean isDiscovered(Vertex vertex) {
		return discovered.contains(vertex);
	}

	/**
	 * Marks the vertex, and all visible vertex around it as discovered
	 * 
	 * @param vertex
	 */
	public void discover(Vertex vertex) {
		discovered.add(vertex);

		for (Direction direction : Direction.values()) {
			if (vertex.hasNeighbor(direction))
				discovered.add(vertex.getRelative(direction));
		}
	}

}
