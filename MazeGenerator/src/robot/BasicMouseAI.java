package robot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;

import robot.graph.Direction;
import robot.graph.Vertex;

/**
 * Simplests of implementation of the mouse
 * 
 * @author Nican
 * 
 */
public class BasicMouseAI extends MouseBaseAI {

	int direction = 1;

	public Stack<Vertex> movementToPath = null;
	public Comparator<Vertex> comparator = new Comparator<Vertex>() {
		@Override
		public int compare(Vertex o1, Vertex o2) {
			return Integer.valueOf(getWeightMap().get(o1)).compareTo(
					Integer.valueOf(getWeightMap().get(o2)));
		}
	};

	public BasicMouseAI(Mouse m) {
		super(m);
	}

	@Override
	public Direction getNextMove() {

		Stack<Vertex> minPath = getMinPath();

		int index = minPath.indexOf(mouse.current);

		if (index != -1) {
			System.out.println("Found mouse at index: " + index);

			movementToPath = null;

			if (index == 0) {
				if (direction == 1) {
					direction *= -1;
					return null;
				}
			} else if (index == minPath.size() - 1) {
				if (direction == -1) {
					return null;
				}
			}

			return mouse.current.getDirection(minPath.get(index - direction));
		}

		// Check if the min path is not infact empty, if it is, what the hell is
		// happening?!
		if (minPath.isEmpty()) {
			throw new IllegalStateException(
					"We can not find our way to the 'non-existant' min path!");
		}

		movementToPath = getPath();

		if (movementToPath.size() == 1) {
			return null;
		}

		return movementToPath.get(0).getDirection(movementToPath.get(1));
	}

	public Stack<Vertex> getPath() {

		if (getWeightMap().get(mouse.current) == Integer.MAX_VALUE) {
			return pathOffDeadEnd();
		}

		return pathToMinpath();

	}

	private Stack<Vertex> pathOffDeadEnd() {

		Stack<Vertex> path = new Stack<>();

		path.push(mouse.current);

		depthPathOffDeadEnd(path);

		return path;
	}

	private boolean depthPathOffDeadEnd(Stack<Vertex> path) {

		Vertex current = path.peek();

		for (Direction direction : Direction.vals) {
			Vertex relative = current.getRelative(direction);

			if (relative == null)
				continue;

			if (path.contains(relative))
				continue;

			if (mouse.knowsEdge(current, relative))
				continue;

			path.push(relative);

			if (getWeightMap().get(relative) != Integer.MAX_VALUE) {
				return true;
			}

			if (depthPathOffDeadEnd(path) == true)
				return true;

			path.pop();
		}

		return false;

	}

	private WeightMap getWeightMap() {
		return mouse.getWeightMap();
	}

	private Stack<Vertex> getMinPath() {
		return mouse.minPath.path;
	}

	private Stack<Vertex> pathToMinpath() {

		Stack<Vertex> path = new Stack<>();

		// If we are in a dead end, just get out there, do Not do iterative!
		double depth = 1.0;

		path.push(mouse.current);

		// Using a Iterative deepening depth-first search
		// http://en.wikipedia.org/wiki/Iterative_deepening_depth-first_search
		// It is inefficient, but it will have to do for now

		while (!depthMinMovementSearch(path, depth)) {
			depth += 1.0;
		}

		System.out.println("Path to min: " + path);

		return path;

	}

	private boolean depthMinMovementSearch(Stack<Vertex> path, double depth) {

		Stack<Vertex> minPath = getMinPath();

		if (depth < 1.0)
			return false;

		Vertex current = path.peek();
		List<Vertex> options = new ArrayList<>(4);

		for (Direction direction : Direction.vals) {

			Vertex relative = current.getRelative(direction);

			if (relative == null)
				continue;

			if (path.contains(relative))
				continue;

			if (mouse.knowsEdge(current, relative))
				continue;

			options.add(relative);

		}

		Collections.sort(options, comparator);

		for (Vertex relative : options) {

			path.push(relative);

			if (minPath.contains(relative)) {
				return true;
			}

			// Magical variable of 3/4 of a weight not to travel in the same
			// path
			double weight = getWeight(relative);

			if (depthMinMovementSearch(path, depth - weight) == true)
				return true;

			path.pop();

		}

		return false;

	}
	
	public double getWeight( Vertex vertex ){
		if( direction == -1 )
			return 1.0;
		
		return mouse.isDiscovered(vertex) ? 1.0 : 0.5;
	}

}
