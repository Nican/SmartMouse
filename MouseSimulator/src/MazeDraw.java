import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

public class MazeDraw extends Component {

	private static final long serialVersionUID = -3106129751076323176L;
	private MouseMaze maze;
	private Mouse mouse;

	public int wallValues[][][] = new int[16][16][2];

	public MazeDraw(MouseMaze maze, Mouse mouse) {

		this.maze = maze;
		this.mouse = mouse;

		this.setMaximumSize(new Dimension(35 * 16, 35 * 16));
		this.setMinimumSize(this.getMaximumSize());
	}

	private Shape getShape(int x, int y, int direction) {

		// East wall
		if (direction == 1) {
			return new Rectangle((x + 1) * MouseMaze.MAZE_BLOCK
					- MouseMaze.MAZE_BLOCK / 16, y * MouseMaze.MAZE_BLOCK,
					MouseMaze.MAZE_BLOCK / 8, MouseMaze.MAZE_BLOCK);
		}

		return new Rectangle(x * MouseMaze.MAZE_BLOCK, (y + 1)
				* MouseMaze.MAZE_BLOCK - MouseMaze.MAZE_BLOCK / 16,
				MouseMaze.MAZE_BLOCK, MouseMaze.MAZE_BLOCK / 8);

	}

	private Color getColor(int value) {

		if (value > 0) {
			return new Color(1.0f, 0.0f, 0.0f,
					((float) Math.min(value, 100)) / 100.0f * 0.25f);
		} else if (value < 0) {
			return new Color(0.0f, 1.0f, 0.0f,
					((float) Math.min(-value, 100)) / 100.0f * 0.25f);
		}

		return new Color(0.0f, 0.0f, 0.0f, 0.0f);
	}

	@Override
	public void paint(Graphics g) {

		Graphics2D g2 = (Graphics2D) g;

		double sx = ((double) this.getWidth()) / MouseMaze.MAZE_SIZE_D;
		double sy = ((double) this.getHeight()) / MouseMaze.MAZE_SIZE_D;

		g2.setTransform(AffineTransform.getScaleInstance(sx, sy));

		for (int x = 0; x < 16; x++) {
			for (int y = 0; y < 16; y++) {

				int southwall = wallValues[x][y][0];
				int eastwall = wallValues[x][y][1];

				if (southwall != 0) {
					g.setColor(getColor(southwall));
					g2.fill(getShape(x, y, 0));

				}

				if (eastwall != 0) {
					g.setColor(getColor(eastwall));
					g2.fill(getShape(x, y, 1));
				}

			}
		}

		g.setColor(Color.black);

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
