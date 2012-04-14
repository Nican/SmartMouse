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

#include "robot.h"
#include "robotlogic.h"
#include "mazelogic.h"
#include <stdlib.h>
#include <stdio.h>
#include <math.h>
#include <string.h>

int wallInfo[16][16][2];

int validPosition(MazePoint point) {
	return point.x >= 0 && point.y >= 0 && point.x < MAZE_SIZE
			&& point.y < MAZE_SIZE;
}

void ModulateWall(MazePoint position, int direction, int modulation) {
	wallInfo[position.x][position.y][direction] += modulation;

	printf("wv:%d,%d,%d,%d\n", position.x, position.y, direction,
			wallInfo[position.x][position.y][direction]);
}

void SensorRead(MazePoint position, MazePoint delta, int distance) {

	//If the sensor is reading too much distance,
	//It might not be reading the exact distance correctly.
	int tooFar = 0;

	int direction = delta.x != 0 ? EASTWALL : SOUTHWALL;

	//printf("Reading %d\t%d\t%d\n", delta.x, delta.y, distance );

	//If we are looking backwards, we have to start by moving a block back
	//Since the memory of the walls is always below and to the right
	if (delta.x < 0)
		position.x--;
	if (delta.y < 0)
		position.y--;

	if (distance > MAZE_BLOCK * 3 + 1) {
		distance = MAZE_BLOCK * 3;
		tooFar = 1;
	}

	//MazePoint start = position;

	while (distance > MAZE_BLOCK && validPosition(position)) {

		ModulateWall( position, direction, -1 );

		position.x += delta.x;
		position.y += delta.y;

		distance -= MAZE_BLOCK;
	}

	if (tooFar == 0 && validPosition(position)) {
		ModulateWall( position, direction, 1 );
	}

}

void GetFrontSensorPosition(Point* robotPosition, MazePoint delta) {
	robotPosition->x += ROBOT_SIZE * delta.x / 2;
	robotPosition->y += ROBOT_SIZE * delta.y / 2;
}

void readFront(Point robotPosition, MazePoint delta) {
	//Translate the position to the location of the front sensor
	GetFrontSensorPosition(&robotPosition, delta);

	MazePoint mazePosition = GetBlock(robotPosition);

	SensorRead(mazePosition, delta, GetFrontIR());
}

void readRight(Point robotPosition, MazePoint delta) {
	MazePoint newDelta;
	newDelta.x = -delta.y;
	newDelta.y = delta.x;

	//Translate the position to the location of the front sensor
	//GetFrontSensorPosition(&robotPosition, delta);
	Point centerRight = robotPosition;
	centerRight.x += newDelta.x * ROBOT_SIZE * 4 / 10 / 2;
	centerRight.y += newDelta.y * ROBOT_SIZE * 4 / 10 / 2;

	Point front = centerRight;
	front.x += delta.x * ROBOT_SIZE * 8 / 10 / 2;
	front.y += delta.y * ROBOT_SIZE * 8 / 10 / 2;

	Point back = centerRight;
	back.x += -delta.x * ROBOT_SIZE * 8 / 10 / 2;
	back.y += -delta.y * ROBOT_SIZE * 8 / 10 / 2;

	SensorRead(GetBlock(front), newDelta, GetRightFrontIR());
	SensorRead(GetBlock(back), newDelta, GetRightBackIR());
}

void readLeft(Point robotPosition, MazePoint delta) {
	MazePoint newDelta;
	newDelta.x = delta.y;
	newDelta.y = -delta.x;

	//Translate the position to the location of the front sensor
	//GetFrontSensorPosition(&robotPosition, delta);
	Point centerLeft = robotPosition;
	centerLeft.x += newDelta.x * ROBOT_SIZE * 4 / 10 / 2;
	centerLeft.y += newDelta.y * ROBOT_SIZE * 4 / 10 / 2;

	Point front = centerLeft;
	front.x += delta.x * ROBOT_SIZE * 8 / 10 / 2;
	front.y += delta.y * ROBOT_SIZE * 8 / 10 / 2;

	Point back = centerLeft;
	back.x += -delta.x * ROBOT_SIZE * 8 / 10 / 2;
	back.y += -delta.y * ROBOT_SIZE * 8 / 10 / 2;

	SensorRead(GetBlock(front), newDelta, GetLeftFrontIR());
	SensorRead(GetBlock(back), newDelta, GetLeftBackIR());
}

void UpdateMaze() {

	//First we are going to find in which direction we are looking
	double rotation = getRotation();

	Point robotPosition = getPosition();
	MazePoint delta = { .x = round(cos(rotation)), .y = -round(sin(rotation)) };

	if (delta.x != 0 && delta.y != 0) {
		//ERRRR, we can not be moving in both directions!
		printf("We are moving in both directions!\n");
	}

	readFront(robotPosition, delta);
	readRight(robotPosition, delta);
	readLeft(robotPosition, delta);

	//CalculateRotation(robotPosition, delta);

}

