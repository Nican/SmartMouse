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
void setupIOPins();
int solveMaze();
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
        
    } //Solves maze over and over again until an error occurs
    
    return 1;
}

int solveMaze(){ return 1; }