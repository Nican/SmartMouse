include <components-lib.scad>;

thickness = 10;
height = 10; //to bottom of PCB
holeDia = 4;
baseThick = 5;
shieldThick = 2;
shieldExtension = 15;

module ultrasonic_mount(){
	difference(){
		union(){
			cube([22.5+height,20,thickness]);
			translate([0,-holeDia*2,0])cube([baseThick,20+holeDia*4,thickness]);
			//shield
			translate([height+22.5,-shieldThick,0])cube([shieldThick,20+shieldThick*2,thickness+16+shieldExtension]);
			translate([height-shieldThick,-shieldThick,0])cube([shieldThick*2+22.5,shieldThick,thickness+16+shieldExtension]);
			translate([height-shieldThick,20,0])cube([shieldThick*2+22.5,shieldThick,thickness+16+shieldExtension]);
			translate([height-shieldThick,-shieldThick,0])cube([shieldThick,20+shieldThick*2,thickness+16+shieldExtension]);
			
		}
		translate([height,0,thickness])ultrasonic(1);
		translate([-1,-holeDia,thickness/2])rotate([0,90,0])cylinder(r= holeDia/2, h= baseThick+2);
		translate([-1,holeDia+20,thickness/2])rotate([0,90,0])cylinder(r= holeDia/2, h= baseThick+2);
	}
}
union(){
translate([height,0,thickness])ultrasonic(0);
ultrasonic_mount();
}