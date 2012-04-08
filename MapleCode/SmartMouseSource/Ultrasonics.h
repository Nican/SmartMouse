//
//  Ultrasonics.h
//  
//
//  Created by Alexander Ryan on 3/21/12.
//  SmartMouse Project
//

#ifndef _Ultrasonics_h
#define _Ultrasonics_h

//Which MaxBotix ultrasonics will be used
#define XL_1340AE
#define NUM_OF_ULTRASONICS 2

//Use this define if you want to make use of the analog envelope readings. Will reserve space in memory for the readings
//#define USE_ANALOG_ENVELOPE

#ifdef XL_1340AE
#define HARDWARE_GAIN 250
//Max ultrasonic range in cm for this model on voltage output
#define MAXIMUM_ULTRASONIC_RANGE 765

#elif defined(XL_1320AE)
#define HARDWARE_GAIN 1000
#define MAXIMUM_ULTRASONIC_RANGE 765

#else
#error "No valid Ultrasonic type defined"
#endif
//Pinouts for the Maple
#define ULTRASONIC_ONE_ENABLE_PIN 29
#define ULTRASONIC_TWO_ENABLE_PIN 30

#define ULTRASONIC_ONE_VOLTAGE_PIN 20
#define ULTRASONIC_TWO_VOLTAGE_PIN 28

#define ULTRASONIC_ONE_ENVELOPE_PIN 17
#define ULTRASONIC_TWO_ENVELOPE_PIN 18


#include "adc.h"

enum adc_smp_rate {
    low,
    medium
    high,
    ultra-high
};



/**
 Automatically initializes all pins to 
 */
void setupUltrasonicPins();


/**
 Sets up a timer to automatically turn each ultrasonic on and off with a standard delay between each alternate reading of 100 ms
 This will take the timer off of manual enables and disables and put them under the control of this function. 
 Turn off the automatic enables with the disableChainPulsing function
 
 */
void enableChainPulsingUltrasonics();

/**
 Sets up a timer to automatically turn each ultrasonic on and off with a specified delay IN mS between each reading (do not go below 50 ms, or it the ultrasonics may not be ready to fire so fast)
 This will take the timer off of manual enables and disables and put them under the control of this function.
 Turn off the automatic enables with the disableChainPulsing function
 @param the delay in ms between each ultrasonic reading. Do not go below 50
 */

void enableChainPulsingUltrasonicsFreq(int delay);

/**
 Turns off all ultrasonics from automatically pulsing out of phase.
 */
void disableChainPulsingUltrasonics();

/**
 Returns the most recent value sampled on the analog voltage. 
 @return the sampled voltage in the 12-bit range, 0-4095
 */
int inline readVoltageUltrasonicOne();

/**
 Returns the most recent value sampled on the analog voltage. 
 @return the sampled voltage in the 12-bit range, 0-4095
 */
int inline readVoltageUltrasonicTwo();

/**
 returns range, in mm, for the most recently detected object with the ultrasonic sensor. Each ultrasonic reads at most once every 99 ms
 @return the range, in mm, of the detected object
 */
int inline getRangeUltrasonicOne();

/**
 returns range, in mm, for the most recently detected object with the ultrasonic sensor. Each ultrasonic reads at most once every 99 ms
 
 @return the range, in mm, of the detected object
 
 */
int inline getRangeUltrasonicTwo();

/**
 Returns the time since the last reading of the ultrasonic sensor was performed, and thus how old the reading is.
 @return the time, in us, since the last ultrasonic voltage reading
 */
int inline microsTimeSinceLastADCReadUltrasonicOne();

/**
 Returns the time since the last reading of the ultrasonic sensor was performed, and thus how old the reading is.
 @return the time, in us, since the last ultrasonic voltage reading
 */
int inline microsTimeSinceLastADCReadUltrasonicTwo();



/**
 returns the analog voltage on the envelope line at any time. directly calls adc_read
 @return the value returned by the adc (12-bit, 0-4095)
 
int inline readEnvelopeUltrasonicOne();

/**
 returns the analog voltage on the envelope line at any time. directly calls adc_read
 @return the value returned by the adc (12-bit, 0-4095)
 
int inline readEnvelopeUltrasonicTwo();

/**
 Returns a pointer to a struct containing info to returns from the most recent envelope analysis
 

EnvelopeObjects* getEnvelopeAnalysis();

*/ //Dont use envelope

/**
 Sets the number of bits to oversample with the ultrasonics. Only oversamples on the voltage output, not the envelope (might be possible with envelope if sample rate is increased???).
 Oversamples 2^bitsOverSample times. Set to 0 to disable oversampling.
 @param bitsOverSample the number of extra bits of information to obtain
 */
void setUltrasonicOversampleRate(int bitsOverSample);
//I recommend two bits or three bits oversampling, depending on the noise to the power supplies. -Alex

/**
 Modifies the sampling time for sampling the Ultrasonic voltage. Output is buffered by op-amp so high sample rate is possible
 */
void setUltrasonicSampleRate(adc_smp_rate smp_rate);


#endif
