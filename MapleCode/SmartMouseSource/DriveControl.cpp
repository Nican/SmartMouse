//
//  DriveControl.cpp
//  
//
//  Created by Alexander Ryan on 4/14/12.
//  Copyright 2012 Worcester Polytechnic Institute. All rights reserved.
//

#include "DriveControl.cpp"
#include "wirish.h"

short leftPWMOut, rightPWMOut;
short leftForward, rightForward;

void setupDriveControlPins(){
    pinMode(LEFT_ENABLE_PIN, PWM);
    pinMode(RIGHT_ENABLE_PIN, PWM);
    pinMode(LEFT_FORWARD_PIN, OUTPUT);
    pinMode(LEFT_REVERSE_PIN, OUTPUT);
    pinMode(RIGHT_FORWARD_PIN, OUTPUT);
    pinMode(RIGHT_REVERSE_PIN, OUTPUT);
    
}

void setLeftMotor(short value, short forwards){
    leftPWMOut = value;
    leftForward = forwards;
}

void setRightMotor(short value, short forwards){
    rightPWMOut = value;
    rightForward = forwards;
}

void setLeftRight(short left, short right){
    
}

void turn_90_degrees_right();

void turn_180_degrees();

void turn_90_degrees_left();

void turn_straight();

int isFinishedWithLastCommand();
