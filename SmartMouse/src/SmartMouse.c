/*
 ============================================================================
 Name        : SmartMouse.c
 Author      : 
 Version     :
 Copyright   : Your copyright notice
 Description : Hello World in C, Ansi-style
 ============================================================================
 */

#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

#include "maze.h"
#include "robot.h"
#include "robotlogic.h"
#include "mazelogic.h"

void init(void){
	setvbuf(stdout, NULL, _IONBF, 0);
	setvbuf(stdin, NULL, _IONBF, 0);

	InitializeSensors();
}

void loop(void){
	//Send all location info to java
	PrintPosition();

	//Get info back about the sensors
	ReadSensors();

	//Calculate by the power and time, how much we have moved
	ThinkEncoders();

	//From the sensor readings, find out where the walls are
	UpdateMaze();

	usleep(10000); //10000 = 0.01 seconds

}

int main(void) {
	init();

	while(1){
		loop();
	}

	return EXIT_SUCCESS;
}
