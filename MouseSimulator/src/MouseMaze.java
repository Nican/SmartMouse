import graph.Direction;
import graph.Graph;
import graph.Vertex;

import java.awt.geom.Line2D;
import java.awt.geom.Line2D.Double;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class MouseMaze {

	public static final int MAZE_SIZE = 1 << 24;
	public static final double MAZE_SIZE_D = (double) MAZE_SIZE;
	public static final int MAZE_BLOCK = MAZE_SIZE / 16;

	public static final int MAZE_BLOCK_MM = 180;
	public static final int UNITS_PER_MM = MAZE_BLOCK / MAZE_BLOCK_MM;

	public final List<Line2D> lines = new ArrayList<Line2D>();

	public MouseMaze(Graph g) {

		lines.add(new Double(0, 0, MAZE_SIZE_D, 0));
		lines.add(new Double(0, 0, 0, MAZE_SIZE_D));
		lines.add(new Double(MAZE_SIZE_D, 0, MAZE_SIZE_D, MAZE_SIZE_D));
		lines.add(new Double(0, MAZE_SIZE_D, MAZE_SIZE_D, MAZE_SIZE_D));

		double blockSize = MAZE_SIZE_D / (double) Graph.SIZE;

		for (int x = 0; x < Graph.SIZE; x++) {
			for (int y = 0; y < Graph.SIZE; y++) {

				Vertex vertex = g.get(x, y);
				double startY = y * blockSize;
				double startX = x * blockSize;
				double endY = (y + 1) * blockSize;
				double endX = (x + 1) * blockSize;

				if (!vertex.hasNeighbor(Direction.SOUTH)) {
					lines.add(new Double(startX, endY, endX, endY));
				}

				if (!vertex.hasNeighbor(Direction.EAST)) {
					lines.add(new Double(endX, startY, endX, endY));
				}

			}
		}

	}

	static boolean getLineLineIntersection(Line2D l1, Line2D l2,
			Point2D.Double intersection) {
		if (!l1.intersectsLine(l2))
			return false;

		double x1 = l1.getX1(), y1 = l1.getY1(), x2 = l1.getX2(), y2 = l1
				.getY2(), x3 = l2.getX1(), y3 = l2.getY1(), x4 = l2.getX2(), y4 = l2
				.getY2();

		intersection.x = det(det(x1, y1, x2, y2), x1 - x2, det(x3, y3, x4, y4),
				x3 - x4) / det(x1 - x2, y1 - y2, x3 - x4, y3 - y4);
		intersection.y = det(det(x1, y1, x2, y2), y1 - y2, det(x3, y3, x4, y4),
				y3 - y4) / det(x1 - x2, y1 - y2, x3 - x4, y3 - y4);

		return true;
	}

	static double det(double a, double b, double c, double d) {
		return a * d - b * c;
	}

}
