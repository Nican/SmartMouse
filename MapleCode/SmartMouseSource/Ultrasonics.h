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
#define USE_ANALOG_ENVELOPE

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

//Todo- Fill in what this struct is. Represents return from envelope sampling code
struct DETECTEDOBJECTS {
    int num_objects_detected;
};

typedef struct DETECTEDOBJECTS  EnvelopeObjects;



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
 Returns the most recent value sampled on the analog voltage
 */
int inline readVoltageUltrasonicOne();

/**
 
 */
int inline readVoltageUltrasonicTwo();

/**
 
 */
int inline getRangeUltrasonicOne();

/**
 */
int inline getRangeUltrasonicTwo();

/**
 
 */
int readEnvelopeUltrasonicOne();

/**
 
 */
int readEnvelopeUltrasonicTwo();

/**
 Returns a pointer to a struct containing info to returns from the most recent envelope analysis
 */
#ifdef USE_ANALOG_ENVELOPE
EnvelopeObjects* getEnvelopeRange();
#endif

/**
 Sets the number of bits to oversample with the ultrasonics. Only oversamples on the voltage output, not the envelope (might be possible with envelope if sample rate is increased???).
 Oversamples 2^bitsOverSample times. Set to 0 to disable oversampling.
 @param bitsOverSample the number of extra bits of information to obtain
 */
void setUltrasonicOversampleRate(int bitsOverSample);



#endif
