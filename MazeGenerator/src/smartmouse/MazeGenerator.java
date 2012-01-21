package smartmouse;

import java.util.ArrayList;
import java.util.Random;

/**
 * Implementation of: http://weblog.jamisbuck.org/2011/1/24/maze-generation-hunt-and-kill-algorithm 
 * @author Nican
 *
 */
public class MazeGenerator {

	public final Graph graph;
	final Random random = new Random();

	public MazeGenerator(Graph g) {
		this.graph = g;
	}

	public void generate() {

		int startX = random.nextInt(Graph.SIZE);
		int startY = random.nextInt(Graph.SIZE);
		
		Vertex start = this.graph.get(startX, startY);
		
		//Create new paths, while vertex without connections exist
		do{
			walk(start);
		} while( ( start = hunt() ) != null );

	}
	
	/**
	 * Finds vertecies around the start, that either are empty, or already have connections
	 * @param start The vertex to look around
	 * @param empty Graph vertex that are empty, or already have edges
	 * @return
	 */
	public ArrayList<Vertex> findNeighbors( Vertex start, boolean empty ){
		ArrayList<Vertex> possibilies = new ArrayList<>();

		for( Direction direction : Direction.values() ){
			Vertex relative = start.getRelative(direction);
			
			if( relative != null && relative.emptyNeighbors() == empty ){
				possibilies.add(relative);
			}					
		}
		
		return possibilies;
	}

	public void walk(Vertex start) {

		while (true) {

			// First choose a direction in which we are going to go
			ArrayList<Vertex> possibilies = findNeighbors(start, true);
			
			//Check if it is empty, if it is, we are done!
			if( possibilies.isEmpty() )
				return;
			
			//Graph a random one to walk to.
			Vertex next = possibilies.get( random.nextInt( possibilies.size() ) );
			
			next.addNeighbor(start);
			start.addNeighbor(next);
			
			start = next;
		}

	}
	
	public Vertex hunt(){
		
		int x, y;
		
		for( y = 0; y < Graph.SIZE; y++ ){
			for( x = 0; x < Graph.SIZE; x++ ){
				
				Vertex vertex = graph.get(x, y);
				
				if( !vertex.emptyNeighbors() )
					continue;
				
				ArrayList<Vertex> possibilies = findNeighbors(vertex, false);
				
				if( possibilies.isEmpty() )
					continue;
				
				//Graph a random one to start from to.
				Vertex previus = possibilies.get( random.nextInt( possibilies.size() ) );
				
				previus.addNeighbor(vertex);
				vertex.addNeighbor(previus);
				
				return vertex;				
			}
		}
		
		return null;		
		
	}

}
