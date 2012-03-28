//
//  Infrareds.c
//  
//
//  Created by Alexander Ryan on 3/21/12.
//  Copyright 2012 Worcester Polytechnic Institute. All rights reserved.
//


#include "Infrareds.h"
#include "adc.h"
#include "wirish.h"

#define INFRARED_BUFFER_SIZE 50
#define INFRARED_TIMER 3
#define INFRARED_TIMER_PRESCALER 72
#define INFRARED_TIMER_OVERFLOW 1000

//Holds the most recent readings of the IR sensors
short infraredOneBuffer[INFRARED_BUFFER_SIZE];
short infraredTwoBuffer[INFRARED_BUFFER_SIZE];
short infraredThreeBuffer[INFRARED_BUFFER_SIZE];
short infraredFourBuffer[INFRARED_BUFFER_SIZE];
short infraredFiveBuffer[INFRARED_BUFFER_SIZE];
short infraredSixBuffer[INFRARED_BUFFER_SIZE];



char OverSampleBits = 0;

HardwareTimer IRTimer(INFRARED_TIMER);

void sampleIR();


void setIROverSampleRate(int bitsOverSample){
    OverSampleBits = bitsOverSample;
}

void setupIRPins(){
    //Set all proper pins to analog read type
    pinMode(LEFTFRONTIR, INPUT_ANALOG);
    pinMode(RIGHTFRONTIR, INPUT_ANALOG);
    pinMode(FRONTLEFTIR, INPUT_ANALOG);
    pinMode(FRONTRIGHTIR, INPUT_ANALOG);
    pinMode(LEFTREARIR, INPUT_ANALOG);
    pinMode(RIGHTREARIR, INPUT_ANALOG);
}

void setupIRTimer(){
    IRTimer.pause();
    //Use channel 1 because I can >:D
    IRTimer.setCount(0);
    IRTimer.setChannel1Mode(TIMER_OUTPUT_COMPARE);
    IRTimer.setPrescaleFactor(INFRARED_TIMER_PRESCALER);
    IRTimer.setOverflow(INFRARED_TIMER_OVERFLOW);
   
    IRTimer.setCompare(TIMER_CH1, 1);
    timer.attachCompare1Interrupt(sampleIR());
}

void setupIRSensors(){
    setupIRPins();
    setupIRTimer();
}
/**
 To be used to sample all
 */
void sampleIR(){
    
}