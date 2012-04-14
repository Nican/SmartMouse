//
//  main.cpp
//  
//
//  Created by Alexander Ryan on 3/21/12.
//  SmartMouse Project
//

#include "IO.h"
#include "wirish.h"
#define tile_size 180

// Force init to be called *first*, i.e. before static object allocation.
// Otherwise, statically allocated objects that need libmaple may fail.
__attribute__((constructor)) void premain() {
    init();
}


int doshit = 1; //lol
int main(){
    
    setupIOPins();
    
    while(doshit){
        SerialUSB.println("RUNNING!!!!!");
        SerialUSB.print("Front left IR Range: ");
        SerialUSB.println(getFrontLeftIRRange());
        SerialUSB.print("Front right IR Range: ");
        SerialUSB.println(getFrontRightIRRange());
        SerialUSB.print("LeftFront IR Range: ");
        SerialUSB.println(getLeftFrontIRRange());
        SerialUSB.print("Right Front IR Range: ");
        SerialUSB.println(getRightFrontIRRange());
        SerialUSB.print("Left Sonar");
        SerialUSB.println(getRangeUltrasonicOne());
        SerialUSB.print("Right Sonar");
        SerialUSB.println(getRangeUltrasonicTwo());

        delay(500);
        
    } //Solves maze over and over again until an error occurs
    
    return 1;
}

int solveMaze(){ return 1; }