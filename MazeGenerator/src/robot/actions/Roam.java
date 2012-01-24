package robot.actions;

import java.util.List;

import robot.BasicMouseAI;
import robot.WeightMap;
import robot.graph.Direction;
import robot.graph.Vertex;

public class Roam extends BaseState {

	public Roam(BasicMouseAI ai) {
		super(ai);
	}

	@Override
	public Direction getNextMove() {

		List<Direction> newDirections = ai.possibleNewMoves();
		WeightMap map = ai.mouse.getWeightMap();
		Vertex current = ai.mouse.current;

		if (newDirections.size() == 1)
			return newDirections.get(0);

		if (newDirections.size() > 1) {

			Direction minDirection = null;
			int minweight = 0;

			for (Direction direction : newDirections) {

				Vertex relative = current.getRelative(direction);
				int weight = map.get(relative);

				if (minDirection == null || minweight > weight) {
					minDirection = direction;
					minweight = weight;
				}

			}

			return minDirection;
		}

		// We have explored in all directions already, lets go back to a place
		// we have not looked yet.
		//Do a BFS to find the first unexplored node

		//List<Direction> directions = ai.possibleMoves();
		
		//TODO!
		return null;

	}

}
