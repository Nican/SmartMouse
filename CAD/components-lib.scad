//I feel like this is going to blow up over time. 
/*
TODO:
-Finish Maple
-Add 


*/

$fn =61;

module paulo_motor(holes){
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
module paulo_wheel(){
	difference(){
		cylinder(r=72/2, h = 18.5);
		translate([0,0,-1])cylinder(r=4/2, h= 18.5+2);
	}

}
module paulo_hub(holes){
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

module pololu_wheel(dia, width, holes){
	treadThick = 3;
	spokes = 5;
	spokesThick = 4;
	spokesWidth = 8;
	difference(){
		union(){
			color("black")difference(){	//Tread
				cylinder(r=dia/2, h=width);
				translate([0,0,-1])cylinder(r=dia/2-treadThick, h=width+2);
			}
			color("blue")difference(){	//Rim
				cylinder(r=dia/2-treadThick, h=width);
				translate([0,0,-1])cylinder(r=dia/2-treadThick-3, h=width+2);
			}
			color("blue")for( i = [0: 360/spokes :360]){	//Spokes
				rotate([0,0,i])translate([0,(dia/2-treadThick-3)/2,width/2])cube([spokesWidth,dia/2-treadThick-3,spokesThick],center=true);
			}
			color("blue")translate([0,0,width/2])cylinder(r=10, h=spokesThick, center = true);	//Center
		}
		color("blue"){
			translate([1/4*25.4,0,0])cylinder(r=1.5, h=width);
			translate([-1/4*25.4,0,0])cylinder(r=1.5, h=width);
			cylinder(r=1.5, h=width);
			translate([-0.5,-11,0])cube([1,11,width]);
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
	color("blue")cube([35,70,13]);
}

module pololu_hub(holes){

}

module ball_caster(holes){
	difference(){
		union(){
			color("silver")translate([0,0,-3/8*25.4/2+0.4*25.4])sphere(r=3/8*25.4/2);
			color("black")hull(){
				translate([0.53*25.4/2,0,0])cylinder(r=0.22*25.4/2, h=2);
				translate([-0.53*25.4/2,0,0])cylinder(r=0.22*25.4/2, h=2);
			}
			if(holes==1){
				translate([0.53*25.4/2,0,-10])cylinder(r=0.09*25.4/2, h=20);
				translate([-0.53*25.4/2,0,-10])cylinder(r=0.09*25.4/2, h=20);
			}
		}
		if(holes==0){
			translate([0.53*25.4/2,0,-1])cylinder(r=0.09*25.4/2, h=4);
			translate([-0.53*25.4/2,0,-1])cylinder(r=0.09*25.4/2, h=4);
		}
	}
}

module encoder(){
	difference(){
		union(){
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
module ir_sensor(holes){
	color("grey"){
		difference(){
			union(){
				translate([-9.65,3.75,0])cube([18.9,29.5,9.2]);
				translate([-3.75,3.75,0])cube([8.4,29.5,9.2+4.3]);
				translate([-3.75+0.6,3.75,0])cube([7.2,29.5,9.2+6.3]);
				translate([-10-9.65,3.75+9.7,3.3])cube([10,10.1,6]);
				hull(){
					cylinder(r=3.75, h=2);
					translate([0,37,0])cylinder(r=3.75, h=2);
				}
				if(holes==1){
					cylinder(r=3.2/2, h=20, center=true);
					translate([0,37,0])cylinder(r=3.2/2, h=20, center=true);
				}
			}
			if(holes==0){
				translate([0,0,-1])cylinder(r=3.2/2, h=2+2);
				translate([0,37,-1])cylinder(r=3.2/2, h=2+2);
			}
		}
	}
}

//ir_sensor(0);
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
