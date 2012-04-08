//
//  Infrareds.h
//  
//
//  Created by Alexander Ryan on 3/21/12.
//  SmartMouse Project
//

#ifndef _Infrareds_h
#define _Infrareds_h

//Which Model IR are we using?
#define GP2D120XJ00F


//Def constants specific to this model
#ifdef GP2D120XJ00F
//Minimum IR distance in cm
#define INFRARED_LOW_RANGE 4 
//Maximum IR distance in cm
#define INFRARED_HIGH_RANGE 30

#define POWER_CONSUMPTION_MA 33
#define RESPONSE_TIME_MS 39

#else
#error "No model IR sensor is properly defined"
#endif

#define INFRARED_ONE_PIN 0
#define INFRARED_TWO_PIN 1
#define INFRARED_THREE_PIN 2
#define INFRARED_FOUR_PIN 3
#define INFRARED_FIVE_PIN 15
#define INFRARED_SIX_PIN 16

#define LEFTFRONTIR INFRARED_ONE_PIN
#define LEFTREARPIR INFRARED_TWO_PIN
#define FRONTLEFPIR INFRARED_THREE_PIN
#define FRONTRIGHTPIR INFRARED_FOUR_PIN
#define RIGHTFRONTPIR INFRARED_FIVE_PIN
#define RIGHTREARPIR INFRARED_SIX_PIN

//Data sheet rated constants


/**
 To be called outside to setup sensors
 */
void setupIRSensors();
/**
 Sets all IR pins to the proper type (analog input) and enables them to be used
 */
void setupIRPins();

/**
 Set the IR timer to be used to sample every millisecond
 */
void setupIRTimer();
/**
 
 */

/**
 I forgot why I added this function. Don't call it.
*/
void setIRReadingMode(IR_Reading_Mode readMode);

/**
 Returns the voltage based on an analog read from a given IR sensor
 @param IRNum the pin of the IR sensor being used . Use the #defines for reference
 @return the 12-bit voltage returned by an analog read (0-4095)
 */
int readIRVoltage(int IRNum);

/**
 Returns the range, in mm (for representing fractions of cm), of an analog read on an IR sensor
 @param IRNum the pin of the IR sensor being used
 @return the distance, in mm
 */

int readIRVoltage(int IRNum);


int getLeftFrontIRRange();

int getLeftRearIRRange();

int getFrontLeftIRRange();

int getFrontRightIRRange();

int getRightFrontIRRange();

int getRightRearIRRange();


//Runs an analysis on each of the buffers holding the IR data to determine if the readings are stable. Returns 1 if they are, 0 if not
int isValidIROne();

int isValidIRTwo();

int isValidIRThree();

int isValidIRFour();

int isValidIRFive();

int isValidIRSix();


/**
 Sets the oversample rate for IR reads.
 Disable oversampling by setting the bitsOverSample to 0.
 Oversamples 2^bitsOverSample times
 @param bitsOverSample the number of extra bits to try and obtain via oversampling
 */
void setIROverSampleRate(int bitsOverSample);

#endif






