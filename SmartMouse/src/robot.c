/*
 * robot.c
 *
 * This file will read the values from serial
 * This is purely for debugging
 *
 *  Created on: Mar 2, 2012
 *      Author: nican
 */

#include "robot.h"

#include <sys/time.h>
#include <time.h>
#include <assert.h>
#include <stdlib.h>
#include <stdio.h>
#include <string.h>

struct timeval rightEncoder;
struct timeval leftEncoder;

char rightWheelPower = 50;
char leftWheelPower = 50;

unsigned int frontIR;
unsigned int frontLeftIR;
unsigned int backLeftIR;
unsigned int backRightIR;
unsigned int frontRightIR;

void InitializeSensors(void) {
	gettimeofday(&rightEncoder, NULL);
	gettimeofday(&leftEncoder, NULL);
}

void ReadSensors(void) {

	char string[256];

	gets(string);

	sscanf(string, "v:%u,%u,%u,%u,%u", &frontIR, &frontLeftIR,
			&backLeftIR, &backRightIR, &frontRightIR);

}

void SetRightWheel(char power) {
	assert( power >= -100 && power <= 100);
	rightWheelPower = power;
}

void SetLeftWheel(char power) {
	assert( power >= -100 && power <= 100);
	leftWheelPower = power;
}

/*
 * TODO: Make more accurate preditions
 * At power = 100, make wheel spin at 1 rotation per second
 */
int getEndoderTicks(struct timeval* time, char power) {
	struct timeval now;
	struct timeval diff;
	gettimeofday(&now, NULL);

	//timersub(&now, time, &diff);
	//Make a comparison of the two times
	diff.tv_sec = now.tv_sec - time->tv_sec;
	diff.tv_usec = now.tv_usec - time->tv_usec;

	if( diff.tv_usec < 0 ){
		diff.tv_sec--;
		diff.tv_usec += 1000000;
	}

	int usecs = diff.tv_sec * 1000000 + diff.tv_usec;
	int ticks = ENCODER_PULSES_PER_REVOLUTION * power * usecs / (1000000 * 100);

	if( ticks != 0 )
		gettimeofday(time, NULL);

	return ticks;
}

int GetRightEncoder() {
	return getEndoderTicks(&rightEncoder, rightWheelPower);
}

int GetLeftEncoder() {
	return getEndoderTicks(&leftEncoder, leftWheelPower);
}

unsigned int GetFrontRightSonar() {
	return 0;
}

unsigned int GetFrontLeftSonar() {
	return 0;
}

unsigned int GetFrontIR() {
	return frontIR;
}

unsigned int GetRightFrontIR() {
	return frontRightIR;
}

unsigned int GetRightBackIR() {
	return backRightIR;
}

unsigned int GetLeftFrontIR() {
	return backLeftIR;
}

unsigned int GetLeftBackIR() {
	return backRightIR;
}
