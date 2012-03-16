

#include "maze.h"

MazePoint GetBlock( Point point ){
	MazePoint pos;
	pos.x = point.x / MAZE_BLOCK;
	pos.y = point.y / MAZE_BLOCK;
	return pos;
};

Point GetPoint( MazePoint point ){
	Point pos;
	pos.x = point.x * MAZE_BLOCK;
	pos.y = point.y * MAZE_BLOCK;
	return pos;
}

Point GetPointCenter( MazePoint point ){
	Point pos;
	pos.x = point.x * MAZE_BLOCK + MAZE_BLOCK / 2;
	pos.y = point.y * MAZE_BLOCK + MAZE_BLOCK / 2;
	return pos;
}
