//
//  IO.cpp
//  
//
//  Created by Alexander Ryan on 4/8/12.
//  Copyright 2012 Worcester Polytechnic Institute. All rights reserved.
//

#include "IO.h"
#include "wirish.h"

void setupIOPins(){
    setupDriveControlPins();
    setupSensors();
    pinMode(11, INPUT_PULLDOWN);
    pinMode(12, INPUT_PULLDOWN);
    return;
}