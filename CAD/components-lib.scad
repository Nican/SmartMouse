$fn =61;

module motor(holes){
	difference(){
		union(){	
			cylinder(r=16/2, h=2);//bump
			translate([0,0,2])cylinder(r=35.75/2, h= 57);//motor
			translate([0,0,59])cylinder(r=32/2, h=28);//planetary
			translate([0,0,87])cylinder(r=20/2, h=3.5);//bump
			translate([0,0,90.5])cylinder(r=6/2, h=16);//shaft
			if(holes ==1){
				translate([0,-25/2,87])cylinder(r=3/2, h=19.5);//screw hole
				translate([-25/2,0,87])cylinder(r=3/2, h=19.5);//screw hole
				translate([25/2,0,87])cylinder(r=3/2, h=19.5);//screw hole
				translate([0,25/2,87])cylinder(r=3/2, h=19.5);//screw hole			
			}
		}
	translate([2.5,-3,94])cube([0.5,6,12.5]);//flat
	}
}
module wheel(){
	difference(){
		cylinder(r=72/2, h = 18.5);
		translate([0,0,-1])cylinder(r=4/2, h= 18.5+2);
	}

}
module hub(holes){
	difference(){
		union(){	
				cylinder(r=22/2, h=1.6);
				cylinder(r=12.75/2, h=19);
				if(holes==1)for(i = [0:360/5:360]){
					rotate([0,0,i])translate([8.5,0,-5])cylinder(r=2/2, h=10);
				}

		}
	translate([0,0,-1])cylinder(r=8.5/2, h=2);
	translate([0,0,-1])cylinder(r=3/2, h=19+2);
	translate([0,0,6])cylinder(r=6.25/2, h=13+1);
	translate([0,0,12.5])rotate([0,90,0])cylinder(r=5/2, h=12.75/2);
	if(holes==0)for(i = [0:360/5:360]){
			rotate([0,0,i])translate([8.5,0,-1])cylinder(r=2/2, h=1.6+2);
	}
	}

}

//Parts layout
translate([60,0,0])color("grey",1.0)motor(1);
color("black", 1.0)wheel();
translate([42,25,0])color("grey",1.0)hub(0);