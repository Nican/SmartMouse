package smartmouse;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Stack;

/**
 * Implementation of:
 * http://weblog.jamisbuck.org/2011/1/24/maze-generation-hunt-and-kill-algorithm
 * 
 * @author Nican
 * 
 */
public class MazeGenerator {

	public final Graph graph;
	final Random random = new Random();
	List<Vertex> center = new ArrayList<>();

	public MazeGenerator(Graph g) {
		this.graph = g;
	}

	public void generate() {

		// Start at the top half of the maze
		int startX = random.nextInt(Graph.SIZE / 2);
		int startY = random.nextInt(Graph.SIZE / 2);

		// First generate the center square
		Vertex center1 = this.graph.get(Graph.SIZE / 2 - 1, Graph.SIZE / 2 - 1);
		Vertex center2 = this.graph.get(Graph.SIZE / 2, Graph.SIZE / 2 - 1);
		Vertex center3 = this.graph.get(Graph.SIZE / 2, Graph.SIZE / 2);
		Vertex center4 = this.graph.get(Graph.SIZE / 2 - 1, Graph.SIZE / 2);

		center.add(center1);
		center.add(center2);
		center.add(center3);
		center.add(center4);

		center1.addNeighbor(center2);
		center2.addNeighbor(center3);
		center3.addNeighbor(center4);
		center4.addNeighbor(center1);

		Vertex start = this.graph.get(startX, startY);

		// Create new paths, while vertex without connections exist
		do {
			walk(start);
		} while ((start = hunt()) != null);

		int successLeft = 6;
		int failCount = 0;

		while (successLeft > 0 && failCount < 100 ) {

			int pointX = random.nextInt(Graph.SIZE);
			int pointY = random.nextInt(Graph.SIZE);
			Vertex vertex = graph.get(pointX, pointY);

			if (newPath(vertex, Graph.SIZE * 3))
				successLeft--;
			else
				failCount++;

		}

		// Check if the center squares still has no entrance, and add a random
		// one
		checkCenter();

	}

	private void checkCenter() {

		Vertex toConnect = center.get(random.nextInt(center.size()));

		for (Direction direction : Direction.values()) {
			Vertex relative = toConnect.getRelative(direction);

			if (!center.contains(relative)) {
				toConnect.addNeighbor(relative);
				return;
			}
		}

	}

	private boolean newPath(Vertex start, int size) {

		Stack<Vertex> history = new Stack<>();
		history.push(start);

		boolean success = searchPath(history, size);

		if (!success)
			System.out.println("Begin search on " + start
					+ " but never found a path to cut.");

		return success;

	}

	/**
	 * Adds a edge between two verticies that are far apart!
	 * @param stack
	 * @param size
	 * @return
	 */
	private boolean searchPath(Stack<Vertex> stack, int size) {

		Vertex current = stack.peek();

		int stackSize = stack.size();

		for (Direction direction : Direction.values()) {

			Vertex neighbor = current.getRelative(direction);

			//Avoid vertixes outside of the map
			if (neighbor == null)
				continue;

			//If it does not have an edge between them
			if (!current.hasNeighbor(direction)) {
				
				//If we are far away enough, and it is a good cut
				if (stackSize >= size && goodCut(current, direction)) {

					int search = stack.indexOf(neighbor);

					if (search > 0 && search <= stackSize / 2) {
						Vertex begin = stack.get(0);

						System.out.println("Removing at: " + neighbor + "/"
								+ current + "  from: " + begin);
						current.addNeighbor(neighbor);
						return true;
					}

				}

				continue;
			}

			//Do not go back where we already visited
			if (stack.contains(neighbor))
				continue;

			stack.push(neighbor);

			boolean found = searchPath(stack, size);

			stack.pop();

			if (found)
				return true;

		}

		return false;
	}

	/**
	 * Hard to explain. So, here is a picture. Consider the following part of the maze: <code>
	 * ___ ___ ___ 
	 *            |
	 * ___ ___    | 
	 *            |
	 * ___ ___ ___|
	 * </code>
	 * 
	 * And we want to remove the middle line, we are going to have a 2x2 block
	 * with connections. Removing it, would result in, which is undesired:
	 * 
	 * <code>
	 * ___ ___ ___ 
	 *            |
	 * ___        | 
	 *            |
	 * ___ ___ ___|
	 * </code>
	 * 
	 * This code just checks if adding a connection would result in such
	 * undesired result, by checking if both perpendilar directions at least has
	 * one good side
	 * 
	 * @param vertex
	 * @param direction
	 * @return
	 */
	private boolean goodCut(Vertex vertex, Direction direction) {

		Vertex other = vertex.getRelative(direction);
		boolean[] goodCuts = new boolean[2];
		int i = 0;

		for (Direction perpendicular : Direction.getPerpendicular(direction)) {

			Vertex perpendiularVertex = vertex.getRelative(perpendicular);

			if (perpendiularVertex == null)
				continue;

			boolean side1 = vertex.hasNeighbor(perpendicular);
			boolean side2 = other.hasNeighbor(perpendicular);
			boolean side3 = perpendiularVertex.hasNeighbor(direction);

			goodCuts[i++] = !side1 || !side2 || !side3;

		}

		return goodCuts[0] && goodCuts[1];

	}

	/**
	 * Finds vertecies around the start, that either are empty, or already have
	 * connections
	 * 
	 * @param start
	 *            The vertex to look around
	 * @param empty
	 *            Graph vertex that are empty, or already have edges
	 * @return
	 */
	public ArrayList<Vertex> findNeighbors(Vertex start, boolean empty) {
		ArrayList<Vertex> possibilies = new ArrayList<>();

		for (Direction direction : Direction.values()) {
			Vertex relative = start.getRelative(direction);

			if (relative != null && relative.emptyEdges() == empty) {
				possibilies.add(relative);
			}
		}

		return possibilies;
	}
	
	public boolean isCorner( Vertex vertex ){
		if( vertex.x == 0 && vertex.y == 0 )
			return true;
		
		if( vertex.x == 0 && vertex.y == Graph.SIZE - 1 )
			return true;
		
		if( vertex.x == Graph.SIZE - 1 && vertex.y == 0 )
			return true;
		
		if( vertex.x == Graph.SIZE - 1 && vertex.y == Graph.SIZE - 1 )
			return true;
		
		return false;		
	}

	public void walk(Vertex start) {

		while (true) {

			// First choose a direction in which we are going to go
			ArrayList<Vertex> possibilies = findNeighbors(start, true);

			// Check if it is empty, if it is, we are done!
			if (possibilies.isEmpty())
				return;

			// Graph a random one to walk to.
			Vertex next = possibilies.get(random.nextInt(possibilies.size()));

			next.addNeighbor(start);
			
			//According the rules, we can not have "L" on the corner, just a dead end
			if( isCorner(next))
				return;

			start = next;
		}

	}

	public Vertex hunt() {

		int x, y;

		for (y = 0; y < Graph.SIZE; y++) {
			for (x = 0; x < Graph.SIZE; x++) {

				Vertex vertex = graph.get(x, y);

				if (!vertex.emptyEdges())
					continue;

				ArrayList<Vertex> possibilies = findNeighbors(vertex, false);

				if (possibilies.isEmpty())
					continue;
				
				// Graph a random one to start from to.
				Collections.shuffle(possibilies);
				
				for( Vertex next : possibilies ){
					
					//Do not touch the middle, that is reserved for the trophy
					if (center.contains(vertex) || center.contains(next))
						continue;
					
					if( isCorner(next) && possibilies.size() > 1 )
						continue;
	
					next.addNeighbor(vertex);
	
					return vertex;
				}
				
			}
		}

		return null;

	}

}
