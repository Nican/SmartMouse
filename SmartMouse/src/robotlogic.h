/*
 * robotlogic.h
 *
 *  Created on: Mar 1, 2012
 *      Author: nican
 */

#ifndef ROBOTLOGIC_H_
#define ROBOTLOGIC_H_

#include "maze.h"

void UpdateAI(void);

int CanReadSensors(void);

void ThinkEncoders(void);
void PrintPosition(void);

Point getPosition(void);
double getRotation(void);

#endif /* ROBOTLOGIC_H_ */
