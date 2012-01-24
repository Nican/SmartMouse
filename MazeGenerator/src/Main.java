import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

import javax.swing.JFrame;

import robot.BasicMouseAI;
import robot.Mouse;
import robot.MouseBaseAI;
import robot.graph.Graph;

public class Main {

	public static Maze maze;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Graph g;
		/*
		g = new Graph();
		MazeGenerator generator = new MazeGenerator(g);
		
		generator.generate();
		
		FileOutputStream fileStream;
		ObjectOutputStream objectStream;
		
		try {
			fileStream = new FileOutputStream( new File("history/" + new Date().getTime() ) );
			objectStream = new ObjectOutputStream(fileStream);
			
			objectStream.writeObject(g);
			
			fileStream.close();
		} catch( Exception ex ){
			System.out.println(ex);
		}
		
		*/
		FileInputStream fileStream;
		ObjectInputStream objectStream;
		try {
			fileStream = new FileInputStream( new File("testmaze/1327377497920") );
			objectStream = new ObjectInputStream(fileStream);
			
			g = (Graph) objectStream.readObject();
			
		}catch( Exception ex ){
			System.out.println(ex);
			return;
		}
		
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
