//
//  main.cpp
//  
//
//  Created by Alexander Ryan on 3/21/12.
//  SmartMouse Project
//

#include "IO.h"
#include "wirish.h"
void setupIOPins();
int solveMaze();
int doshit = 1;
int main(){
    
    setupIOPins();
    
    while(doshit){
        SerialUSB.println("RUNNING!!!!!");   
    } //Solves maze over and over again until an error occurs
    
    return 1;
}

int solveMaze(){ return 1; }