/*
 * maze.h
 *
 *	We are going to define the maze as top right block starting at 0,0
 *
 *	(0,0)
 *	+---------------+
 *	|				|
 *	|				|
 *	|				|
 *	|				|
 *	|				|
 *	+---------------+
 *                   (65536, 65536)
 *
 *  The smartmouse maze is composed, of equally sized 16x16 blocks.
 *  Therefore, each square is of the size 4096x4096
 *
 *  The mouse should start at about the coordinate (2048,2048).
 *  Turned towards the wall, so it can adjust its sensors before starting running.
 *
 *	Since each block has 180mm, we know that each mm has ~22.75555 "units"
 *
 *
 *  Created on: Mar 1, 2012
 *      Author: nican
 */

#ifndef MAZE_H_
#define MAZE_H_

typedef struct  {
	int x; //Absolute position of the robot, between [0, 1<<24]
	int y;
} Point;

typedef struct {
	int x; //Between [0,16]
	int y; //Between [0,15]
} MazePoint;

#define MAZE_SIZE 16
#define MAZE_TOTAL_SIZE (1<<24)
#define MAZE_BLOCK (MAZE_TOTAL_SIZE / MAZE_SIZE)

#define MAZE_BLOCK_MM 180 //Size of a maze block, in millimeters

#define UNITS_PER_MM (MAZE_BLOCK/MAZE_BLOCK_MM)


/**
 * Get the logical position of the block; Good for calculating shortest path, etc..
 */
MazePoint GetBlock( Point point );
/**
 * Get the top-left corner of the block map position.
 * This is going to be a big number to represent the robot position accurately
 */
Point GetPoint( MazePoint point );
/**
 * Same as GetPoint, but returns the center of the block instead
 */
Point GetPointCenter( MazePoint point );

#endif /* MAZE_H_ */
