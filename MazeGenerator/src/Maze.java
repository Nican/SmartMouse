import java.awt.Container;
import java.awt.GridLayout;

import robot.Mouse;
import smartmouse.Graph;

/**
 * Container that will have all the drawing blocks
 * @author Nican
 *
 */
public class Maze extends Container {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4696400455690047138L;
	
	public final int size = Graph.SIZE;
	public final MazeSquare[][] squares = new MazeSquare[size][size];
	
	public final Graph graph;
	public final Mouse mouse;

	public Maze( Graph g, Mouse m ) {
		GridLayout layout = new GridLayout(size, size);
		
		this.graph = g;
		this.mouse = m;
		
		setLayout(layout);

		for (int y = 0; y < size; y++) {
			for (int x = 0; x < size; x++) {
				MazeSquare square = new MazeSquare(this, x, y);
				squares[x][y] = square;

				add(square);
			}
		}

	}
	
	public MazeSquare get( int x, int y ){
		return squares[x][y];
	}

}
