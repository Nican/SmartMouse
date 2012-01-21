package smartmouse;

public class Graph {
	
	public final static int SIZE = 16;
	
	final Vertex verticies[][] = new  Vertex[SIZE][SIZE];
	
	public Graph(){
		
		int x, y;
		
		for( x = 0; x < SIZE; x++ ){
			for( y = 0; y < SIZE; y++ ){
				verticies[x][y] = new Vertex(x, y, this);
			}
		}
		
	}
	
	public Vertex get( int x, int y ){
		return verticies[x][y];		
	}
	
	
}
