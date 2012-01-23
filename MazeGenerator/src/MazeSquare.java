import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import robot.Mouse;
import smartmouse.Direction;
import smartmouse.Vertex;

/**
 * An UI element representing a given vertex
 * @author Nican
 *
 */
public class MazeSquare extends Component {
	
	private static final Font font = new Font("Serif", Font.PLAIN, 12);
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1504491135251095028L;
	public static final int borderSize = 1;
	public static final int mouseSize = 16;
	
	public final int x;
	public final int y;
	public final Maze maze;

	public int weightValue;
	
	public MazeSquare( Maze maze, int x, int y ){
		this.maze = maze;
		this.x = x;
		this.y = y;
		
		this.setPreferredSize(new Dimension(50,50));
	}
	
	public Vertex getVertex(){
		return  maze.graph.get(x, y);
	}
	
	public Color getBackgroundColor(){
		
		if( getMouse().isDiscovered(getVertex()))
			return Color.green;
		
		return Color.white;
		
	}
	
	public Mouse getMouse(){
		return maze.mouse;
	}

	@Override
	public void paint( Graphics g ){
		Vertex vertex = getVertex();

		if( vertex.emptyEdges() ){
			g.setColor(Color.black);
			g.fillRect(0, 0, getWidth(), getHeight());
			
		} else {
		
			g.setColor(getBackgroundColor());
			g.fillRect(0, 0, getWidth(), getHeight());
			
			g.setColor(Color.black);
			
			if( !vertex.hasNeighbor(Direction.NORTH)){
				g.fillRect(0, 0, getWidth(), borderSize);
			}
			
			if( !vertex.hasNeighbor(Direction.SOUTH)){
				g.fillRect(0, this.getHeight() - borderSize, getWidth(), borderSize);
			}
			
			if( !vertex.hasNeighbor(Direction.WEST)){
				g.fillRect(0, 0, borderSize, getHeight());
			}
			
			if( !vertex.hasNeighbor(Direction.EAST)){
				g.fillRect(getWidth() - borderSize, 0, borderSize, getHeight());
			}
			
		}
		
		if( getMouse().current.equals(vertex) ){
			g.setColor(Color.pink);
			g.fillOval(getWidth() / 2 - mouseSize / 2, getHeight() / 2 - mouseSize / 2, mouseSize, mouseSize);
		}
		
		g.setFont(font);
		g.setColor(Color.red);
		g.drawString(x + ", " + y, 15, 15);
		g.drawString(Integer.toString(weightValue), 15, 28);
		
	}

}
