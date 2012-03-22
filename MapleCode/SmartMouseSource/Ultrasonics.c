//
//  Ultrasonics.c
//  
//
//  Created by Alexander Ryan on 3/21/12.
//  SmartMouse Project
//
//Contains all Ultrasonic sensor code for controlling Maxbotix sensors
//
//
//



#include "adc.h"
#include "dma.h"
#include "Ultrasonics.h"
#include "wirish.h"

//How many reads to hold with the envelope
#define ULTRASONIC_ENVELOPE_BUFFER_SIZE 

int mostRecentUltrasonicVoltageOne = 0; //Used for holding the most recent analog reading from sensor one
int mostRecentUltrasonicVoltageTwo = 0; //Used for holding the most recent analog reading from sensor two

int mostRecentOversampleReadingOne = 0; //Holds accumulated values for oversampling. Contains same value as analog reading if oversampling disabled

int mostRecentOversampleReadingTwo = 0; //Holds accumulated values for oversampling.

char overSampleBits = 0;

#ifdef USE_ANALOG_ENVELOPE

short[ULTRASONIC_ENVELOPE_BUFFER_SIZE] ultrasonicOneBuffer;

short[ULTRASONIC_ENVELOPE_BUFFER_SIZE] ultrasonicOneBuffer;

