import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

public class MazeDraw extends Component {

	private static final long serialVersionUID = -3106129751076323176L;
	private MouseMaze maze;
	private Mouse mouse;

	public MazeDraw(MouseMaze maze, Mouse mouse) {

		this.maze = maze;
		this.mouse = mouse;

		this.setMaximumSize(new Dimension(35 * 16, 35 * 16));
	}

	@Override
	public void paint(Graphics g) {

		Graphics2D g2 = (Graphics2D) g;

		double sx = ((double) this.getWidth()) / MouseMaze.MAZE_SIZE_D;
		double sy = ((double) this.getHeight()) / MouseMaze.MAZE_SIZE_D;

		g2.setTransform(AffineTransform.getScaleInstance(sx, sy));

		for (Line2D line : this.maze.lines) {
			g2.draw(line);
		}
		paintMouse(g2, AffineTransform.getTranslateInstance(
				(double) mouse.position.x, (double) mouse.position.y));

	}

	public void paintMouse(Graphics2D g2, AffineTransform transform) {
		
		g2.draw(mouse.frontIR.getTracedLine(maze));
		g2.draw(mouse.backLeftIR.getTracedLine(maze));
		g2.draw(mouse.backRightIR.getTracedLine(maze));
		g2.draw(mouse.frontLeftIR.getTracedLine(maze));
		g2.draw(mouse.frontRightIR.getTracedLine(maze));
		
		Rectangle2D mouseRect = new Rectangle2D.Double(-mouse.size / 2,
				-mouse.size / 2, mouse.size, mouse.size);

		Shape mouseShape = AffineTransform.getRotateInstance(-mouse.rotation)
				.createTransformedShape(mouseRect);

		mouseShape = transform.createTransformedShape(mouseShape);
		

		g2.draw(mouseShape);

	}

}
