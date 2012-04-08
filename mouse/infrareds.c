//
//  Infrareds.c
//  
//
//  Created by Alexander Ryan on 3/21/12.
//  Copyright 2012 Worcester Polytechnic Institute. All rights reserved.
//


#include "infrareds.h"
#include "adc.h"
#include "wirish.h"

//maintain a buffer of 100 values sampled at 1 KHz, or 100 ms of data
#define INFRARED_BUFFER_SIZE 100
#define INFRARED_TIMER 3
#define INFRARED_TIMER_PRESCALER 72
#define INFRARED_TIMER_OVERFLOW 1000
#define INFRARED_TIMER_FREQUENCY 1000

//Holds the most recent readings of the IR sensors
short infraredOneBuffer[INFRARED_BUFFER_SIZE];
short* IROneBufferFront = &infraredOneBuffer[0];
short infraredTwoBuffer[INFRARED_BUFFER_SIZE];
short* IROneBufferFront = &infraredOneBuffer[0];
short infraredThreeBuffer[INFRARED_BUFFER_SIZE];
short* IROneBufferFront = &infraredOneBuffer[0];
short infraredFourBuffer[INFRARED_BUFFER_SIZE];
short* IROneBufferFront = &infraredOneBuffer[0];
short infraredFiveBuffer[INFRARED_BUFFER_SIZE];
short* IRFiveBufferFront = &infraredFiveBuffer[0];
short infraredSixBuffer[INFRARED_BUFFER_SIZE];
short* IRSixBufferFront = &infraredSixBuffer[0];



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
   //Setups up IR timer to go off at 1 ms intervals to sample all ADC's
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
    //Low level call to adc_read
    short IROne = adc_read(ADC1, IRONE_ADC_CHANNEL);
    short IRTwo = adc_read(ADC1, IRTWO_ADC_CHANNEL);
    short IRThree = adc_read(ADC1, IRTHREE_ADC_CHANNEL);
    short IRFour = adc_read(ADC1, IRFOUR_ADC_CHANNEL);
    short IRFive = adc_read(ADC1, IRFIVE_ADC_CHANNEL);
    short IRSix = adc_read(ADC1, IRSIX_ADC_CHANNEL);
    *IROneBufferFront = IROne;
    IROneBufferFront++;
}