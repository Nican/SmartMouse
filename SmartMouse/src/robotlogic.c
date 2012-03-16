/*
 * robotlogic.c
 *
 *  Created on: Mar 1, 2012
 *      Author: nican
 */

#define max(A,B) (((A)>(B))?(A):(B))
#define min(A,B) (((A)<(B))?(A):(B))

#include "robotlogic.h"
#include "robot.h"
#include "maze.h"

#include <math.h>
#include <stdio.h>

Point position = { .x = ROBOT_START_X, .y = ROBOT_START_Y };
double rotation = M_PI;

/**
 * Integrating the encoder counts to find the new robot position
 * This function assumes that we call it often, thus not needing
 * to make a sum by parts.
 */

int ticksToDistance(int ticks) {
	return ((WHEEL_CIRCUFERENCE * ticks) / ENCODER_PULSES_PER_REVOLUTION)
			/ ENCODER_TO_WHEEL_RADIO;
}

void rotateRobot(int distance, double wheelRot) {
	//We can not convert these to float, since wirish_math.h uses double anyway
	double angle = atan2(distance, ROBOT_SIZE);

	//Type conversion are expensive
	double x = (double) position.x;
	double y = (double) position.y;

	//Set the origin be relative to the left wheel
	//Since we are moving with the right wheel
	double wheelPosX = x + cos(rotation + wheelRot) * (ROBOT_SIZE * 0.5);
	double wheelPosY = y - sin(rotation + wheelRot) * (ROBOT_SIZE * 0.5);

	double posX = x - wheelPosX;
	double posY = y - wheelPosY;

	//Apply matrix transformation for rotation
	double newPosX = cos(angle) * posX - sin(angle) * posY;
	double newPosY = sin(angle) * posX + cos(angle) * posY;

	position.x = (int) (newPosX + wheelPosX);
	position.y = (int) (newPosY + wheelPosY);

	rotation += angle;
}

void ThinkEncoders(void) {
	//Read the values of the two sensors and
	//this automatically resets them to 0
	int right = ticksToDistance(GetRightEncoder());
	int left = ticksToDistance(GetLeftEncoder());

	//Check if both right and left are the same sign
	if (right * left > 0) {
		//Check if it is positive, get the min
		//Else, if it is negative, get the max
		int minDistance = right > 0 ? min(right, left) : max(right,left);
		right -= minDistance;
		left -= minDistance;

		position.x += (int) (cos(rotation) * ((double) minDistance));
		//Since +y is down, we subtract
		position.y -= (int) (sin(rotation) * ((double) minDistance));
	}

	if (right != 0)
		rotateRobot(right, -M_PI_2);

	if (left != 0)
		rotateRobot(-left, M_PI_2);

}

void PrintPosition(void) {
	printf("po:%d,%d,%f\n", position.x, position.y, rotation);
}
