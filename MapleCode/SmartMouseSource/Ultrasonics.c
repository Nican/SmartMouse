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
#define ULTRASONIC_ENVELOPE_BUFFER_SIZE 2000
#define DMA_ADC_CHANNEL 1
#define DMA_ADC_NUMBER 1

//Which timer to handle the automatic switching for the ultrasonics
#define PULSING_TIMER_NUM 

int mostRecentUltrasonicVoltageOne = 0; //Used for holding the most recent analog reading from sensor one
int mostRecentUltrasonicVoltageTwo = 0; //Used for holding the most recent analog reading from sensor two

int mostRecentOversampleReadingOne = 0; //Holds accumulated values for oversampling. Contains same value as analog reading if oversampling disabled

int mostRecentOversampleReadingTwo = 0; //Holds accumulated values for oversampling.

char overSampleBits = 0; //Holds the number of bits to oversample

#ifdef USE_ANALOG_ENVELOPE

short[ULTRASONIC_ENVELOPE_BUFFER_SIZE] ultrasonicOneBuffer;

short[ULTRASONIC_ENVELOPE_BUFFER_SIZE] ultrasonicOneBuffer;



void setupUltrasonicPins();

void enableChainPulsingUltrasonics();

void enableChainPulsingUltrasonicsFreq(int delay);


void disableChainPulsingUltrasonics();

