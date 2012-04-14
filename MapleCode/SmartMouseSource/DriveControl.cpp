//
//  DriveControl.cpp
//  
//
//  Created by Alexander Ryan on 4/14/12.
//  Copyright 2012 Worcester Polytechnic Institute. All rights reserved.
//

#include "DriveControl.h"
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
    
    pwmWrite(LEFT_ENABLE_PIN, 0);
    pwmWrite(RIGHT_ENABLE_PIN, 0);
    digitalWrite(LEFT_FORWARD_PIN, HIGH);
    digitalWrite(LEFT_REVERSE_PIN, LOW);
    digitalWrite(RIGHT_FORWARD_PIN, HIGH);
    digitalWrite(RIGHT_REVERSE_PIN, LOW);
}

void setLeftMotor(short value, short forwards){
    leftPWMOut = value;
    leftForward = forwards;
    
    //Update direction
    if(leftForward){
        digitalWrite(LEFT_FORWARD_PIN, HIGH);
        digitalWrite(LEFT_REVERSE_PIN, LOW);
    }
    else{
        digitalWrite(LEFT_REVERSE_PIN, HIGH);
        digitalWrite(LEFT_FORWARD_PIN, LOW);
    }
    pwmWrite(LEFT_ENABLE_PIN, value);
}

void setRightMotor(short value, short forwards){
    rightPWMOut = value;
    rightForward = forwards;
    
    if(rightForward){
        digitalWrite(RIGHT_FORWARD_PIN, HIGH);
        digitalWrite(RIGHT_REVERSE_PIN, LOW);
    }
    else{
        digitalWrite(RIGHT_REVERSE_PIN, HIGH);
        digitalWrite(RIGHT_FORWARD_PIN, LOW);
    }
    pwmWrite(RIGHT_ENABLE_PIN, value);
}

void setLeftRight(short left, short leftForward, short right, short rightForward){
    setLeftMotor(left, leftForward);
    setRightMotor(right, rightForward);
}

void turn_90_degrees_right(){};

void turn_180_degrees(){};

void turn_90_degrees_left(){};

void turn_straight(){};

int isFinishedWithLastCommand(){
    return 1;
};
