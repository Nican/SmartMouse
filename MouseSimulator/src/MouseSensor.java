import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

public class MouseSensor {

	private Mouse mouse;
	private Point2D position;
	private double rotation;

	private int traceSize = MouseMaze.MAZE_BLOCK * 5;

	public MouseSensor(Mouse mouse, Point2D position, double rotation) {
		this.mouse = mouse;
		this.position = position;
		this.rotation = rotation;
	}

	public Line2D.Double getTraceLine() {

		// Create a matrix transformation for the robot rotation
		AffineTransform transform = AffineTransform
				.getRotateInstance(-mouse.rotation);

		Point2D actual = new Point2D.Double();

		transform.transform(position, actual);

		// Now we get a normal vector
		double direction = rotation + mouse.rotation;

		Point2D end = new Point2D.Double(actual.getX() + Math.cos(direction)
				* traceSize, actual.getY() - Math.sin(direction) * traceSize);

		return new Line2D.Double(actual, end);
	}

	public Line2D getTracedLine(MouseMaze maze) {
		Line2D.Double trace = getTraceLine();
		trace.x1 += mouse.position.x;
		trace.y1 += mouse.position.y;
		trace.x2 += mouse.position.x;
		trace.y2 += mouse.position.y;

		double distance = trace.getP1().distanceSq(trace.getP2());

		Point2D.Double intersection = new Point2D.Double();

		for (Line2D line : maze.lines) {

			if (MouseMaze.getLineLineIntersection(line, trace, intersection)) {

				double newDistance = intersection.distanceSq(trace.getP1());

				if (newDistance < distance) {
					trace.setLine(trace.getP1(), intersection);
				}
			}
		}

		return trace;

	}
	
	public int getTracedLineLength(MouseMaze maze) {
		Line2D line = getTracedLine(maze);
		
		return (int) line.getP1().distance(line.getP2());	
	}
}
