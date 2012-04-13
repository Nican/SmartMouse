import graph.Graph;
import graph.MazeGenerator;

import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.JFrame;

public class Simulator {

	public Graph graph = new Graph();
	public MouseMaze maze;
	public Mouse mouse = new Mouse(this);
	public MazeDraw mazeDraw;

	public Simulator() {
		MazeGenerator generator = new MazeGenerator(graph);
		generator.generate();

		maze = new MouseMaze(graph);
		mazeDraw = new MazeDraw(maze, mouse);

		final JFrame frame = new JFrame("Maze");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.add(mazeDraw);
		frame.pack();

		frame.setSize(new Dimension(512, 512));

		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				try {
					System.out.println("Closing mouse, since window closed");
					mouse.close();
					System.exit(0);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});

		while (frame.isShowing()) {
			mouse.readFromProcess();
		}

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new Simulator();
	}

}
