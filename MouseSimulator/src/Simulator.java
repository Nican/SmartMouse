import javax.swing.JFrame;

import graph.Graph;
import graph.MazeGenerator;


public class Simulator {
	
	public Graph graph = new Graph();
	public MouseMaze maze;
	public Mouse mouse = new Mouse(this);
	public MazeDraw mazeDraw;
	
	public Simulator(){
		MazeGenerator generator = new MazeGenerator(graph);
		generator.generate();
		
		maze = new MouseMaze(graph);
		mazeDraw = new MazeDraw(maze, mouse);
		
		final JFrame frame = new JFrame("Maze");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.add( mazeDraw );
		frame.pack();
		
		while(true){
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
