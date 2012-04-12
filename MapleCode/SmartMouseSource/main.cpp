//
//  main.cpp
//  
//
//  Created by Alexander Ryan on 3/21/12.
//  SmartMouse Project
//

#include "IO.h"
int main(){
    setupIOPins();
    while(solveMaze()) ; //Solves maze over and over again until an error occurs
    
    return 1;
}

