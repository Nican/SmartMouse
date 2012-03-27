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

//Holds the most recent readings of the IR sensors
short infraredOneBuffer[INFRARED_BUFFER_SIZE];
short infraredTwoBuffer[INFRARED_BUFFER_SIZE];
short infraredThreeBuffer[INFRARED_BUFFER_SIZE];
short infraredFourBuffer[INFRARED_BUFFER_SIZE];
short infraredFiveBuffer[INFRARED_BUFFER_SIZE];
short infraredSixBuffer[INFRARED_BUFFER_SIZE];

char OverSampleBits = 0;



void setIROverSampleRate(int bitsOverSample){
    OverSampleBits = bitsOverSample;
}