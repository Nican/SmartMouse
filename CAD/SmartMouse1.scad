include <components-lib.scad>;

//vars
//chassis
chassisX = 100;
chassisY = 100;
chassisThick = 4;
chassisHeight = 2.4;
opticSpacing = 30;
chassisRadius = 2;

//hardware
nutApothem =5;

//maze
maze = 180;
mazeWall = 150; 
mazeThick = 12;

module layout(){
	union(){
		#cube([chassisX,chassisY,0.1]);
		//Static constructions
		translate([chassisX/2-6,3.5+9.27,13])micro_motor();
		translate([chassisX/2+6,chassisY-3.5-9.27,13])rotate([0,0,180])micro_motor();
		translate([chassisX/2,3.5,18])rotate([-90,0,0])pololu_hub(0);
		translate([chassisX/2,chassisY-3.5,18])rotate([90,0,0])pololu_hub(0);
		translate([chassisX/2,0,18])rotate([-90,0,0])small_tamiya_wheel();
		translate([chassisX/2,chassisY-3.5,18])rotate([-90,0,0])small_tamiya_wheel();
		translate([0,chassisY/2,0])rotate([0,0,90])ball_caster(0);
		
		translate([0,0,chassisThick+chassisHeight]){
			translate([30,chassisY/2-35,0])rotate([0,-15,0])2slipo();
			//translate([34,40,25])maple(0);
			translate([chassisX-16-2,chassisY-2,0])rotate([0,-90,180])ultrasonic(0);
			translate([chassisX-16-2,2,22.5])rotate([0,90,0])ultrasonic(0);
			translate([chassisX-13.6,chassisY/2-29.5/2,13.1/2])rotate([0,90,0])ir_sensor();//front
			translate([13.1/2+7,chassisY/2,15])rotate([90,0,0])ir_sensor(0);//back left
			translate([13.1/2+7,chassisY/2,29.5+15])rotate([-90,0,0])ir_sensor();//back right
			translate([75,chassisY/2,29.5+23])rotate([-90,0,0])ir_sensor();//front right
			translate([75,chassisY/2,23])rotate([90,0,0])ir_sensor();//front left
			//rotate([0,-90,90])encoder();
			//rotate([0,-90,90])encoder();
			translate([3,chassisY/2+opticSpacing/2,0])optic_lens();
			translate([3,chassisY/2-30-opticSpacing/2,0])optic_lens();
		
		}
	}
}

module chassis(){
	translate([0,0,chassisHeight]){
		difference(){
			union(){
				translate([chassisX-17,chassisY/2-20,0])cube([17,40,10]);//front ultra mount
				translate([0,chassisY/2-13.1,0])cube([25,26.1,10]); //back ultra mounts

				hull(){
					translate([chassisRadius,chassisRadius,0])cylinder(r=chassisRadius, h=chassisThick);
					translate([chassisX-chassisRadius,chassisRadius,0])cylinder(r=chassisRadius, h=chassisThick);
					translate([chassisRadius,chassisY-chassisRadius,0])cylinder(r=chassisRadius, h=chassisThick);
					translate([chassisX-chassisRadius,chassisY-chassisRadius,0])cylinder(r=chassisRadius, h=chassisThick);
				}
				
			}
			translate([0,0,-chassisHeight])layout();	
			translate([chassisX/2-18,-1,-1])cube([36,5+1,chassisThick+2]); //right wheel slot
			translate([chassisX/2-18,chassisY-5,-1])cube([36,5+1,chassisThick+2]); //left wheel slot
		}
	}
}

module maze(transparency){
	color("brown",transparency)translate([0,-mazeThick,-mazeThick])cube([maze,maze+mazeThick*2,mazeThick]);
	color("white",transparency)translate([0,-mazeThick,0])cube([maze,mazeThick,mazeWall]);
	color("white",transparency)translate([0,maze,0])cube([maze,mazeThick,mazeWall]);

}

translate([(maze-chassisX)/2,(maze-chassisY)/2,0]){
	layout();
	chassis();
	}
maze(0.75);

