$fn =61;

module maple(holes){
boardThick=3;
	difference(){
		union(){
			color("red"){
				cube([50,53.3,boardThick]);
				hull(){
					translate([52.5,5,0])rotate([0,0,135])cube([5,5,boardThick]);
					translate([52.5,38,0])rotate([0,0,135])cube([5,5,boardThick]);
				}
			}
			if(holes==1){
				translate([4,4,-1])cylinder(r=1.5, h=boardThick*10,center=true);
				translate([4,53.3-4,-1])cylinder(r=1.5, h=boardThick*10,center=true);
				translate([50,7.6,-1])cylinder(r=1.5, h=boardThick*10,center=true);
				translate([50,7.6+27.9,-1])cylinder(r=1.5, h=boardThick*10,center=true);
			}

		}
		if(holes==0){
			translate([4,4,-1])cylinder(r=1.5, h=boardThick+2);
			translate([4,53.3-4,-1])cylinder(r=1.5, h=boardThick+2);
			translate([50,7.6,-1])cylinder(r=1.5, h=boardThick+2);
			translate([50,7.6+27.9,-1])cylinder(r=1.5, h=boardThick+2);
		}
	}
}

module ultrasonic(holes){
	difference(){
		union(){
			color("green")cube([22.5,20,3.5]);
			color("black")translate([10.5,8.5,3.5])cylinder(r=8, h=12.5);
			color("black")translate([4,17.5,-10])cube([18.5,2.5,10]);
			color("grey")translate([12.5,9,-10])cube([7.75,7.75,10]);
			if(holes==1){
				translate([3.25/2+1,20-3-3.25/2,-15])cylinder(r=3.25/2, h=30);
				translate([22.5-3.25/2-1,3.25/2+1,-15])cylinder(r=3.25/2, h=30);
			}
		}

		if(holes==0){
			translate([3.25/2+1,20-3-3.25/2,-1])cylinder(r=3.25/2, h=5.5);
			translate([22.5-3.25/2-1,3.25/2+1,-1])cylinder(r=3.25/2, h=5.5);
		}
	}
}

module micro_motor(){
	difference(){
		union(){
			color("gold")cube([12,9,10]);
			color("silver")translate([6,-9.27,5])rotate([-90,0,0])cylinder(r=1.5, h=9.27);
			color("silver")translate([6,24,5])rotate([-90,0,0])cylinder(r=2.5, h=2);
			color("silver")translate([6+3.5,24,6])rotate([-90,0,0])cube([0.3,2,2]);
			color("silver")translate([6-3.5,24,6])rotate([-90,0,0])cube([0.3,2,2]);
			color("silver")translate([6,9,5])rotate([-90,0,0])cylinder(r=6, h=15);
			color("silver")translate([6,24,5])rotate([-90,0,0])cylinder(r=0.5, h=6.5);
		}
		color("silver")translate([0,9,10])cube([12,16,3]);
		color("silver")translate([0,9,-3])cube([12,16,3]);
		color("silver")translate([4,-10,6])cube([4,9.99,3]);
	}
}

module 2slipo(){
	color("blue")cube([13,70,35]);
}

module pololu_hub(holes){
	difference(){
		union(){
			color("silver")cylinder(r=17.5/2, h=5.1);
			if(holes==1){
				for(i = [0:90:360])
				rotate([0,0,i])translate([6.4,0,0])cylinder(r=1.25, h=20,center=true);
			}
		}
		translate([0,0,-1])cylinder(r=1.5, h=7.1);
		if(holes==0){
			for(i = [0:90:360]){
				rotate([0,0,i])translate([6.4,0,0])cylinder(r=1.25, h=20,center=true);
				
			}
		}
	}
}

module ball_caster(holes){
	difference(){
		union(){
			color("silver")translate([0,0,1/2*25.4/2])sphere(r=1/2*25.4/2);
			translate([0,0,0.5*25.4])rotate([0,180,0])color("black")hull(){
				translate([0.58*25.4/2,0,0])cylinder(r=0.27*25.4/2, h=2);
				translate([-0.58*25.4/2,0,0])cylinder(r=0.27*25.4/2, h=2);
			}
			translate([0,0,0.5*25.4])rotate([0,180,0])if(holes==1){
				translate([0.58*25.4/2,0,-10])cylinder(r=0.09*25.4/2, h=20);
				translate([-0.58*25.4/2,0,-10])cylinder(r=0.09*25.4/2, h=20);
			}
			color("black")translate([0,0,0.5*25.4])rotate([0,180,0])cylinder(r=8, h=10);
		}
			translate([0,0,0.5*25.4])rotate([0,180,0])if(holes==0){
			translate([0.58*25.4/2,0,-1])cylinder(r=0.09*25.4/2, h=4);
			translate([-0.58*25.4/2,0,-1])cylinder(r=0.09*25.4/2, h=4);
		}
	}
}

module encoder(){
	difference(){
		union(){
			color("black")cube([14.75,14.25,10]);
			color("silver")translate([6.75,14.25/2,0])cylinder(r=1/8*25.4, h=16.35);
			color("silver")translate([6.75,14.25/2,0])cylinder(r=3.17/2, h=32.22);
			color("silver")translate([14.75-1.75,0,-3.18])cube([0.457,14.25,3.18]);
		}
	}
}

module small_tamiya_wheel(holes){
	//difference(){
		union(){
			cylinder(r=18, h=3.5);
		}
	//}
}
module ir_sensor(transparency, holes){
	difference(){
		union(){
			color("grey")translate([-13.1/2,0,0])cube([13.1,29.5,7.2]);
			color("grey")translate([-8.5/2,0,0])cube([8.5,29.5,11.6]);
			color("grey")translate([-7.2/2,0,0])cube([7.2,29.5,13.6]);
			if(transparency==1)%translate([-7.2/2,0,0])cube([7.2,29.5,13.6+40]);
			color("white")translate([-13.1/2-6,9.7,3.3])cube([6,10.1,6.3]);
			color("grey")hull(){
         		translate([0,-3.75,0])cylinder(r=3.75, h=2);
        			translate([0,37-3.75,0])cylinder(r=3.75, h=2);
       		}
        		if(holes==1){
       			translate([0,-3.75,0])cylinder(r=3.2/2, h=20, center=true);
         		translate([0,37-3.75,0])cylinder(r=3.2/2, h=20, center=true);
       		}
		}
		
     	if(holes==0){
       		translate([0,-3.75,-1])cylinder(r=3.2/2, h=2+2);
      		translate([0,37-3.75,-1])cylinder(r=3.2/2, h=2+2);
		}
	}
}

module optic_lens(){
color("grey", 0.5)cube([16,30,5]);
}

//optic_lens();
//pololu_hub(0);
//encoder();
//ir_sensor(1,0);
//small_tamiya_wheel();
//ball_caster(0);
//micro_motor();
//paulo_motor(0);
//paulo_wheel();
//paulo_hub(0);
//pololu_wheel(70, 8, 1);
//ultrasonic(0);
//maple(0);
//2slipo();
