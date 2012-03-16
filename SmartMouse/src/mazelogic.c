/*
 * mazelogic.c
 *
 *  Created on: Mar 4, 2012
 *      Author: nican
 */

/* The maze is a grid [0,..,15] by [0,..,15]
 * We know that all outside walls are closed
 * We need only to keep information if the walls [0,..,15]x[0,..,14] is closed to the south
 * We need only to keep information if the walls [0,..,14]x[0,..,15] is closed to the east
 *
 *
 *
 */

#define WALL_UNKOWN 0
#define WALL_EXISTS 1
#define WALL_MISSING 2

#define SOUTHWALL 0
#define EASTWALL 1

char wallInfo[15][15][2];

typedef struct wallLearning {
	char wallX;
	char wallY;
	char wallDir;

	char wallReadings[256];
};


void UpdateMaze(){



}
