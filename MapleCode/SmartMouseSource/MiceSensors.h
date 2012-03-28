//
//  MiceSensors.h
//  
//
//  Created by Alexander Ryan on 3/27/12.
//  Copyright 2012 Worcester Polytechnic Institute. All rights reserved.
//
// The optical sensors work by counting the total distance traveled in the X and Y directions between reads. These can be reference based on physical location on the robot 
// To refer to the change in position and orientation, with small erro
//

#ifndef _MiceSensors_h
#define _MiceSensors_h

/**
 Returns total distance traveled in the X direction, in mm, for the optical sensor #1
 */
int getCurrentXPositionOne();
/**
 Returns total distance traveled in the Y direction, in mm, for the optical sensor #1

 */

int getCurrentYPositionOne();

/**
 Returns total distance traveled in the X direction, in mm, for the optical sensor #2
 */
int getCurrentXPositionTwo();
/**
 Returns total distance traveled in the Y direction, in mm, for the optical sensor #2
 
 */

int getCurrentYPositionTwo();





#endif
