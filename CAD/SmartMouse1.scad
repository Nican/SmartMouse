include <components-lib.scad>;




module chassis(){

};

module layout(){
	union(){
		#cube([120,120,0.1]);
		translate([19,25,0])2slipo();
		translate([60-6,3.5+9.75,13])micro_motor();
		translate([60+6,120-3.5-9.27,13])rotate([0,0,180])micro_motor();
		translate([60,0,18])rotate([-90,0,0])small_tamiya_wheel();
		translate([60,120-3.5,18])rotate([-90,0,0])small_tamiya_wheel();
		translate([30,50,25])maple(0);
		translate([103,107,0])rotate([0,-90,180])ultrasonic(0);
		translate([103,13,22.5])rotate([0,90,0])ultrasonic(0);
		translate([104,60-18.5,10])rotate([0,90,0])ir_sensor(0);//front
		translate([16,60+18.5,30])rotate([0,90,180])ir_sensor(0);//back
		translate([10,103,5])rotate([90,0,180])ir_sensor(0);
		translate([10,17,42])rotate([-90,0,180])ir_sensor(0);
		translate([90,17,5])rotate([90,0,0])ir_sensor(0);
		translate([90,103,42])rotate([-90,0,0])ir_sensor(0);
		//translate([0,0,0])rotate([0,180,90])ball_caster();
	}
};

module bom(){
	#cube([100,100,0.1]);
	translate([0,68,0])micro_motor();
	translate([15,68,0])micro_motor();
	//translate([1,1,0])2slipo();
	translate([28,38,0])maple(0);
	translate([13,55,0])ball_caster(0);
	translate([13,43,0])ball_caster(0);
	translate([75,0,10])ultrasonic();
	translate([75,21,10])ultrasonic();
}

layout();
//bom();