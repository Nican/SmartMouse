package robot.actions;

import robot.BasicMouseAI;
import robot.graph.Direction;

public abstract class BaseState {
	
	public final BasicMouseAI ai;

	public BaseState( BasicMouseAI ai ){
		this.ai = ai;
	}
	
	public abstract Direction getNextMove();
	
}
