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

#define ULTRASONIC_ADC_MODULE 2
#define ULTRASONIC_ONE_CHANNEL 12
#define ULTRASONIC_TWO_CHANNEL 13

//Which timer to handle the automatic switching for the ultrasonics
#define PULSING_TIMER_NUM 2
#define PULSING_TIMER_CHAN 3

//Sets counter to count at 1 khz, and for 80 ms interrupts
#define ma_prescaler 72000
#define ma_overflow 80

int mostRecentUltrasonicVoltageOne = 0; //Used for holding the most recent analog reading from sensor one
int mostRecentUltrasonicVoltageTwo = 0; //Used for holding the most recent analog reading from sensor two

int mostRecentOversampleReadingOne = 0; //Holds accumulated values for oversampling. Contains same value as analog reading if oversampling disabled

int mostRecentOversampleReadingTwo = 0; //Holds accumulated values for oversampling.

int mostRecentUltrasonicRangeOne = 0; //mm

int mostRecentUltrasonicRangeTwo = 0; //mm

unsigned int lastADCTwoReadMicros = 0;

unsigned int lastADCOneReadMicros = 0;

char overSampleBits = 2; //Holds the number of bits to oversample

char sampleUltraOneHuh 1; //

HardwareTimer ultrasonicTimer(PULSING_TIMER_NUM);


#ifdef USE_ANALOG_ENVELOPE

void sampleUltrasonics(void);

short[ULTRASONIC_ENVELOPE_BUFFER_SIZE] ultrasonicOneBuffer;

short[ULTRASONIC_ENVELOPE_BUFFER_SIZE] ultrasonicOneBuffer;

#endif

void setUltrasonicOversampleRate(int bitsOverSample){
    overSampleBits = bitsOverSample;
}

void setupUltrasonicPins(){
    pinMode(ULTRASONIC_ONE_VOLTAGE_PIN, ANALOG_INPUT);
    pinMode(ULTRASONIC_TWO_VOLTAGE_PIN, ANALOG_INPUT);
    pinmode(ULTRASONIC_ONE_ENABLE_PIN, OUTPUT);
    pinMode(ULTRASONIC_TWO_ENABLE_PIN, OUTPUT);
    adc_set_sample_rate(ADC2, ADC_SMPR_13_5); //sets ultrasonic ADC to sample much faster
    digitalWrite(ULTRASONIC_ONE_VOLTAGE_PIN, LOW);
    digitalWrite(ULTRASONIC_TWO_VOLTAGE_PIN, LOW);
}


void setupUltrasonicTimer(){
    ultrasonicTimer.setMode(PULSING_TIMER_CHAN);
    ultrasonicTimer.pause();
    ultrasonicTimer.setCount(0);
    ultrasonicTimer.setPrescaleFactor(ma_prescaler);
    ultrasonicTimer.setOverflow(ma_overflow);
    ultrasonicTimer.setCompare(PULSING_TIMER_CHAN, 1);
    ultrasonicTimer.attachInterrupt(PULSING_TIMER_CHAN, sampleUltrasonics);
    ultrasonicTimer.refresh();
    ultrasonicTimer.resume();
}

void setupUltrasonics(){
    setupUltrasonicPins();
    setupUltrasonicTimer();
}

void sampleUltrasonics(void){
    int a;
    if(sampleUltraOneHuh){
        lastADCOneReadMicros = micros();
        sampleUltraOneHuh = 0;
        for(a = 0; a < (1 << overSampleBits); a++){
            mostRecentOversampleReadingOne += adc_read(ADC2, ULTRASONIC_ONE_CHANNEL);
        }
        mostRecentUltrasonicVoltageOne = mostRecentOversampleReadingOne >> overSampleBits;
    
        mostRecentUltrasonicRangeOne = mostRecentUltrasonicVoltageOne >> 2; //div 4
        
    }
    else{
        lastADCTwoReadMicros = micros();
        sampleUltraOneHuh = 1;
        for(a = 0; a < (1 << overSampleBits); a++){
            mostRecentOversampleReadingTwo += adc_read(ADC2, ULTRASONIC_TWO_CHANNEL);
        }
        
        mostRecentUltrasonicVoltageTwo = mostRecentUltrasonicVoltageTwo >> overSampleBits;
        
        mostRecentUltrasonicRangeTWO = mostRecentUltrasonicVoltageTwo >> 2; //div 4
    }
}


int inline readVoltageUltrasonicOne(){
    return mostRecentUltrasonicVoltageOne;
}

int inline readVoltageUltrasonicTwo(){
    return mostRecentUltrasonicVoltageTwo;
}

int inline getRangeUltrasonicOne(){
    return mostRecentUltrasonicRangeOne;
}

int inline getRangeUltrasonicTwo(){
    return mostRecentUltrasonicRangeTwo;
}

int inline microsTimeSinceLastADCReadUltrasonicOne(){
    return micros() - lastADCOneReadMicros;
}


int inline microsTimeSinceLastADCReadUltrasonicTwo(){
    return micros() - lastADCTwoReadMicros;
}
