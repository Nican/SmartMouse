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
#include <stdlib.h>
#include <stdio.h>
#include <math.h>
#include <string.h>

#define WALL_UNKOWN 0
#define WALL_EXISTS 1
#define WALL_MISSING 2

#define SOUTHWALL 0
#define EASTWALL 1

int wallInfo[16][16][2];

int validPosition(MazePoint point) {
	return point.x >= 0 && point.y >= 0 && point.x < MAZE_SIZE
			&& point.y < MAZE_SIZE;
}

void SensorRead(MazePoint position, MazePoint delta, int distance) {

	//If the sensor is reading too much distance,
	//It might not be reading the exact distance correctly.
	int tooFar = 0;

	int direction = delta.x != 0 ? 0 : 1;

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

		wallInfo[position.x][position.y][direction] -= 1;

		//printf("Decreasing one: %d %d %d\n", position.x, position.y, direction );

		position.x += delta.x;
		position.y += delta.y;

		distance -= MAZE_BLOCK;
	}

	if (tooFar == 0 && validPosition(position)) {
		//printf("Increasing one: (%d,%d) \t (%d,%d) \t %d\n", position.x,
		//		position.y, start.x, start.y, direction);
		wallInfo[position.x][position.y][direction] += 1;
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

int isRotating = 0;
double rotateTo = 0;

void CalculateRotation(Point robotPosition, MazePoint delta) {

	MazePoint mazePosition = GetBlock(robotPosition);

	if (delta.x < 0)
		mazePosition.x -= 1;
	if (delta.y < 0)
		mazePosition.y -= 1;

	int direction = delta.x != 0 ? 0 : 1;
	unsigned int maxDistance = (MAZE_BLOCK - ROBOT_SIZE) / 2;

	if (wallInfo[mazePosition.x][mazePosition.y][direction] > 50
			&& GetFrontIR() < maxDistance) {

		isRotating = 1;
		rotateTo = fmod(rotateTo - M_PI_2, 2.0 * M_PI);
		//rotateTo %= 2.0 * M_PI;
		SetRightWheel(-10);
		SetLeftWheel(10);
	}

}

void UpdateMaze() {

	//First we are going to find in which direction we are looking
	double rotation = getRotation();

	if (isRotating == 1) {

		if (fabs(rotation - rotateTo) < 0.05) {
			isRotating = 0;
			SetRightWheel(50);
			SetLeftWheel(50);
		}

		return;
	}

	Point robotPosition = getPosition();
	MazePoint delta = { .x = round(cos(rotation)), .y = -round(sin(rotation)) };

	if (delta.x != 0 && delta.y != 0) {
		//ERRRR, we can not be moving in both directions!
		printf("We are moving in both directions!\n");
	}

	readFront(robotPosition, delta);

	CalculateRotation(robotPosition, delta);

}

