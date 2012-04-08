//
//  DriveControl.h
//  
//
//  Created by Alexander Ryan on 3/22/12.
//  Copyright 2012 Worcester Polytechnic Institute. All rights reserved.
//

#ifndef _DriveControl_h
#define _DriveControl_h

#define LEFT_ENABLE_PIN 6
#define RIGHT_ENABLE_PIN 11

#define LEFT_FORWARD_PIN 10
#define LEFT_REVERSE_PIN 12

#define RIGHT_FORWARD_PIN 5
#define RIGHT_REVERSE_PIN 7

#define LEFT_ENABLE_PIN_TIMER 1
#define LEFT_ENABLE_PIN_CHANNEL 1

#define RIGHT_ENABLE_PIN_TIMER 3
#define RIGHT_ENABLE_PIN_CHANNEL 2


/**
 Sets all drive control pins to be the proper type (digital outputs and PWM)
 */
void setupDriveControlPins();

#endif
