/*
 * robot.h
 *
 *  Created on: Mar 1, 2012
 *      Author: nican
 */

#include "maze.h"
#include <math.h>

#ifndef ROBOT_H_
#define ROBOT_H_

#define ROBOT_START_X (MAZE_TOTAL_SIZE-MAZE_BLOCK/2)
#define ROBOT_START_Y (MAZE_TOTAL_SIZE-5*MAZE_BLOCK/2)

#define WHEEL_DIAMETER_MM 55
#define WHEEL_CIRCUFERENCE_MM (WHEEL_DIAMETER_MM * M_PI)

#define WHEEL_CIRCUFERENCE (UNITS_PER_MM * WHEEL_CIRCUFERENCE_MM)

#define ROBOT_SIZE_MM 100
#define ROBOT_SIZE (ROBOT_SIZE_MM * UNITS_PER_MM)

//Number of pulses the encoder emits per full turn
#define ENCODER_PULSES_PER_REVOLUTION 64

//Every 2 turns on the encoder, is one turn on the wheel
#define ENCODER_TO_WHEEL_RADIO 2

void InitializeSensors(void);
void ReadSensors();

//Set the power of the wheels, hopefully in a linear manner
void SetRightWheel( char power ); //Set a value 0-100
void SetLeftWheel( char power ); //Set a value 0-100

//Gets the number of ticks the encoder has fired, and resets it to 0
int GetRightEncoder();
int GetLeftEncoder();

unsigned int GetFrontRightSonar();
unsigned int GetFrontLeftSonar();
unsigned int GetFrontIR();

unsigned int GetRightFrontIR();
unsigned int GetRightBackIR();

unsigned int GetLeftFrontIR();
unsigned int GetLeftBackIR();

#endif /* ROBOT_H_ */
