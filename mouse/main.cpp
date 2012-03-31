//
//  main.cpp
//  
//
//  Created by Alexander Ryan on 3/21/12.
//  SmartMouse Project
//

void init();

void mapMaze();

bool solveMaze();


int main(){
    init(); //setups up robot variables and peripherals
    
    mapMaze(); //Commences mapping process
    
    while(solveMaze()) ; //Solves maze over and over again until an error occurs
    
    return 1;
}

#include <iostream>
