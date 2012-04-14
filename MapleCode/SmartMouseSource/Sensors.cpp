//
//  Sensors.cpp
//  
//
//  Created by Alexander Ryan on 4/8/12.
//  Copyright 2012 Worcester Polytechnic Institute. All rights reserved.
//

#include "Sensors.h"

void setupSensors(){
    setupUltrasonics();
    setupIRSensors();
    setupMiceSensors();
    return;
}