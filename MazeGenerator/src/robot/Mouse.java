package robot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import smartmouse.Direction;
import smartmouse.Vertex;

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

	public Mouse(Vertex start) {
		current = start;

		history.add(current);
		discover(start);
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
	}

	/**
	 * If the mouse has visited either of the vertexes, the mouse knows about
	 * the edges around it. Now consider:
	 * 
	 * <code>
	 * | 1 |___ ___
	 * |  
	 * | 2  ___ ___
	 * |     
	 * | 3  ___ ___
	 * |   |
	 * </code> Since the mouse travels down from 1 to 3, he knows that must be a
	 * wall between besides 2.
	 * 
	 * @param v1
	 * @param v2
	 * @return
	 */
	public boolean knowsEdge(Vertex v1, Vertex v2) {
		return inHistory(v1) || inHistory(v2)
				|| (inDiscovered(v1) && inDiscovered(v2));
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
	public boolean inDiscovered(Vertex vertex) {
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
