import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;

import robot.BasicMouseAI;
import robot.Mouse;
import robot.MouseBaseAI;
import robot.graph.Graph;
import robot.graph.MazeGenerator;

public class Main {

	public static Maze maze;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Graph g = new Graph();
		MazeGenerator generator = new MazeGenerator(g);
		
		generator.generate();
		
		Mouse m = new Mouse(g, g.get(Graph.SIZE-1, Graph.SIZE-1));
		maze = new Maze(g, m);
		
		final MouseBaseAI ai = new BasicMouseAI(m);
		
		final JFrame frame = new JFrame("Maze");

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

		frame.add(maze);
		
		frame.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				ai.think();
				maze.updateValues();
				frame.repaint();
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});

		frame.pack();

	}

}
