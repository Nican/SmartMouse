include <components-lib.scad>;

//vars
//chassis
chassisX = 100;
chassisY = 100;
chassisThick = 4;
chassisHeight = 2.4;
opticSpacing = 1;

//maze
maze = 180;
mazeWall = 150; 
mazeThick = 12;


module chassis(){

};

module layout(){
	union(){
		#cube([chassisX,chassisY,0.1]);
		//Static objects
		translate([chassisX/2-6,3.5+9.27,13])micro_motor();
		translate([chassisX/2+6,chassisY-3.5-9.27,13])rotate([0,0,180])micro_motor();
		translate([chassisX/2,3.5,18])rotate([-90,0,0])pololu_hub(0);
		translate([chassisX/2,chassisY-3.5,18])rotate([90,0,0])pololu_hub(0);
		translate([chassisX/2,0,18])rotate([-90,0,0])small_tamiya_wheel();
		translate([chassisX/2,chassisY-3.5,18])rotate([-90,0,0])small_tamiya_wheel();
		translate([0,chassisY/2,0])rotate([0,0,90])ball_caster(0);
		
		translate([0,0,chassisThick+chassisHeight]){
			translate([7,chassisY/2-35,0])2slipo();
			//translate([34,40,25])maple(0);
			translate([chassisX-16,chassisY,0])rotate([0,-90,180])ultrasonic(0);
			translate([chassisX-16,0,22.5])rotate([0,90,0])ultrasonic(0);
			translate([chassisX-13.6,chassisY/2-29.5/2,13.1/2])rotate([0,90,0])ir_sensor(1);//front
			translate([13.1/2,chassisY/2,20])rotate([90,0,180])ir_sensor(0);//back left
			translate([13.1/2,chassisY/2,29.5+20])rotate([-90,0,180])ir_sensor();//back right
			translate([90,chassisY/2,29.5+20])rotate([-90,0,180])ir_sensor(1);//front right
			translate([90,chassisY/2,20])rotate([90,0,180])ir_sensor(1);//front left
			rotate([0,-90,90])encoder();
			//rotate([0,-90,90])encoder();
			translate([chassisX/2+7,chassisY/2+opticSpacing/2,0])optic_lens();
			translate([chassisX/2+7,chassisY/2-30-opticSpacing/2,0])optic_lens();
		
		}
	}
};

module maze(transparency){
	color("brown",transparency)translate([0,-mazeThick,-mazeThick])cube([maze,maze+mazeThick*2,mazeThick]);
	color("white",transparency)translate([0,-mazeThick,0])cube([maze,mazeThick,mazeWall]);
	color("white",transparency)translate([0,maze,0])cube([maze,mazeThick,mazeWall]);

}

translate([(maze-chassisX)/2,(maze-chassisY)/2,0])layout();
maze(0.75);


