include <components-lib.scad>;

//TODO
//-Encoders

/*notes
at 100x100 and 2.12 chassis height, tilt from wheels is 2.43 degrees
*/
//vars
layerHeight = 0.28;

//chassis
chassisX = 100;
chassisY = 100;
chassisThick = 3;
chassisHeight = 2.4-layerHeight;
opticSpacing = 32;
chassisRadius = 5;

trussHeight = 50;
pcbThick = 30;
pcbHeight = 24;

topThick = 3;
topHeight = 37;
topAngle = 0;
postWall = 2;//nut radius derived from apothem plus this
//hardware
nutApothem = 6.1;
nutHeight = 4;

//maze
maze = 180;
mazeWall = 150; 
mazeThick = 12;

module layout(holes){
	union(){
		//#cube([chassisX,chassisY,0.1]);
		//Static constructions
		translate([chassisX/2-6,3.5+9.27,13])micro_motor();
		translate([chassisX/2+6,chassisY-3.5-9.27,13])rotate([0,0,180])micro_motor();
		translate([chassisX/2,3.5,18])rotate([-90,0,0])pololu_hub(0);
		translate([chassisX/2,chassisY-3.5,18])rotate([90,0,0])pololu_hub(0);
		translate([chassisX/2,0,18])rotate([-90,0,0])small_tamiya_wheel();
		translate([chassisX/2,chassisY-3.5,18])rotate([-90,0,0])small_tamiya_wheel();
		translate([8,chassisY/2,0])rotate([0,0,90])ball_caster(holes);
		
		translate([70,chassisY/2,20*sin(2.43)-0.2])rotate([0,0,90])ball_caster(holes); //front ball castor

		translate([0,0,chassisThick+chassisHeight]){
			translate([30,chassisY/2-73/2,-chassisThick/2])rotate([0,-15,0])2slipo();
			//translate([34,40,25])maple(0);
			translate([chassisX-16,chassisY-22.5-10,topHeight+topThick])rotate([90,0,90])ultrasonic(holes);
			translate([chassisX-16,10,topHeight+topThick])rotate([90,0,90])ultrasonic(holes);
			translate([chassisX-13.6,chassisY/2-29.5/2,99])rotate([0,90,0])ir_sensor(0,holes);//front
			translate([13.1/2+2,chassisY/2,104])rotate([90,0,180])ir_sensor(0,holes);//back left
			translate([13.1/2+2,chassisY/2,29.5+104])rotate([-90,0,-180])ir_sensor(0,holes);//back right
			translate([73,chassisY/2,29.5+104])rotate([-90,0,0])ir_sensor(0,holes);//front right
			translate([73,chassisY/2,104])rotate([90,0,0])ir_sensor(0,holes);//front left
			//rotate([0,-90,90])encoder();
			//rotate([0,-90,90])encoder();
			translate([4,chassisY/2+opticSpacing/2,-chassisThick+layerHeight])optic_lens();
			translate([4,chassisY/2-31-opticSpacing/2,-chassisThick+layerHeight])optic_lens();
		}
	}
}

module chassis(){
	translate([0,0,chassisHeight]){
		difference(){
			union(){
				hull(){
					translate([chassisRadius,chassisRadius,0])cylinder(r=chassisRadius, h=chassisThick);
					translate([chassisX-chassisRadius,chassisRadius,0])cylinder(r=chassisRadius, h=chassisThick);
					translate([chassisRadius,chassisY-chassisRadius,0])cylinder(r=chassisRadius, h=chassisThick);
					translate([chassisX-chassisRadius,chassisY-chassisRadius,0])cylinder(r=chassisRadius, h=chassisThick);
				}
				//battery supports
				translate([21,chassisY/2-10,0])cube([15,20,27]); 
				translate([41,chassisY/2-10,0])rotate([0,-20,0])cube([7,20,24]); 
				translate([25,11,0])cube([15,78,10]);
				
				//motor mounts
				translate([39,9.27+3.5,0])cube([21,23,18.5]); 
				translate([39,chassisY-9.27-3.5-23,0])cube([21,23,18.5]); 
				
				//top piece mounts
				translate([chassisX/2,chassisY/2,0])cylinder(r=nutApothem/cos(30)/2+postWall, h=chassisThick+10);
				translate([chassisX-nutApothem/cos(30)/2-postWall,chassisY/2+25,0])cylinder(r=nutApothem/cos(30)/2+postWall, h=chassisThick+10);
				translate([chassisX-nutApothem/cos(30)/2-postWall,chassisY/2-25,0])cylinder(r=nutApothem/cos(30)/2+postWall, h=chassisThick+10);
				
				translate([8,chassisY/2,0])rotate([0,0,90])scale([1.4,1.4,1.7])hull(){
					translate([0.58*25.4/2,0,0])cylinder(r=0.29*25.4/2, h=10);
					translate([-0.58*25.4/2,0,0])cylinder(r=0.29*25.4/2, h=10);
					translate([0,0,0])cylinder(r=8.3, h=10);
				}
				translate([70,chassisY/2,0])rotate([0,0,90])scale([1.4,1.4,1.9])hull(){
					translate([0.58*25.4/2,0,0])cylinder(r=0.29*25.4/2, h=10);
					translate([-0.58*25.4/2,0,0])cylinder(r=0.29*25.4/2, h=10);
					translate([0,0,0])cylinder(r=8.3, h=10);
				}

			}
			//top piece mounts
			translate([chassisX/2,chassisY/2,-1])cylinder(r=1.75, h=chassisThick+12);
			translate([chassisX-nutApothem/cos(30)/2-postWall,chassisY/2+25,-1])cylinder(r=1.75, h=chassisThick+12);
			translate([chassisX-nutApothem/cos(30)/2-postWall,chassisY/2-25,-1])cylinder(r=1.75, h=chassisThick+12);
			//screw recesses
			translate([chassisX/2,chassisY/2,-1])cylinder(r=3.5, h=7);
			translate([chassisX-nutApothem/cos(30)/2-postWall,chassisY/2+25,-1])cylinder(r=3.5, h=7);
			translate([chassisX-nutApothem/cos(30)/2-postWall,chassisY/2-25,-1])cylinder(r=3.5, h=7);
			
			//front center cutout
			translate([chassisX-6-16,chassisY/2-15,-1])minkowski(){
				cylinder(r=2,h=11);
				cube([16,30,11]);
			}
			//corner cutouts
			translate([chassisX-6-12,6,-1])minkowski(){
				cylinder(r=2,h=chassisThick+2);
				cube([12,9,chassisThick+2]);
			}
			translate([chassisX-6-12,chassisY-6-9,-1])minkowski(){
				cylinder(r=2,h=chassisThick+2);
				cube([12,9,chassisThick+2]);
			}

			//center cutout
			translate([30,30,-1])cube([11,40,chassisThick+4]);
			
			translate([chassisX/2-24,-1,-1])cube([48,11+1,chassisThick+2]); //right wheel slot
			translate([chassisX/2-24,chassisY-11,-1])cube([48,11+1,chassisThick+2]); //left wheel slot
			
			//optic holes
			translate([12,chassisY/2+opticSpacing/2+31/2,-1])cylinder(r=8, h=chassisThick+2);
			translate([12,chassisY/2-31-opticSpacing/2+31/2,-1])cylinder(r=8, h=chassisThick+2);
			
			//ziptie routing
			translate([37,23,-1])cube([27,4,chassisThick+4]);
			translate([37,77-4,-1])cube([27,4,chassisThick+4]);
			translate([40,23,-1])rotate([0,-15,0])cube([6,4,20]);
			translate([40,77-4,-1])rotate([0,-15,0])cube([6,4,20]);
			
			//ball caster thingies
			translate([8,chassisY/2,-1])rotate([0,0,90])scale([1.15,1.15,1.0])hull(){
					translate([0.58*25.4/2,0,0])cylinder(r=0.29*25.4/2, h=0.5*25.4-20*sin(2.43)-0.2+1);
					translate([-0.58*25.4/2,0,0])cylinder(r=0.29*25.4/2, h=0.5*25.4-20*sin(2.43)-0.2+1);
					translate([0,0,0])cylinder(r=8.3, h=0.5*25.4-20*sin(2.43)-0.2+1);
					
			}
			translate([70,chassisY/2,-1])rotate([0,0,90])scale([1.15,1.15,1.0])hull(){
					translate([0.58*25.4/2,0,0])cylinder(r=0.29*25.4/2, h=0.5*25.4+1);
					translate([-0.58*25.4/2,0,0])cylinder(r=0.29*25.4/2, h=0.5*25.4+1);
					translate([0,0,0])cylinder(r=8.3, h=0.5*25.4+1);
			}
			translate([-5,35,-1])cube([5,30,20]);//back ball caster cleanup

			//slices
			translate([-1,chassisY/2-0.5,-1])cube([chassisX+2,1,layerHeight*3+1]);
			translate([chassisX/2-0.5,-1,-1])cube([1,chassisY+2,layerHeight*3+1]);
			//layout
			translate([0,0,-chassisHeight])layout(1);	

		}
	}
}

module top_plate(){
	translate([0,0,chassisHeight+chassisThick])difference(){
		union(){
			translate([0,0,topHeight])rotate([0,-topAngle,0])hull(){
					translate([chassisRadius,chassisRadius,0])cylinder(r=chassisRadius, h=topThick);
					translate([chassisX/cos(topAngle)-chassisRadius,chassisRadius,0])cylinder(r=chassisRadius, h=topThick);
					translate([chassisRadius,chassisY-chassisRadius,0])cylinder(r=chassisRadius, h=topThick);
					translate([chassisX/cos(topAngle)-chassisRadius,chassisY-chassisRadius,0])cylinder(r=chassisRadius, h=topThick);
				}
			translate([0,0,topHeight])hull(){
				rotate([0,-topAngle,0])translate([chassisX/2,chassisY/2,0])cylinder(r=10);
				translate([chassisX/2,chassisY/2,-topHeight/2])cylinder(r=nutApothem/cos(30)/2+postWall);
			}
			translate([0,0,topHeight])hull(){
				rotate([0,-topAngle,0])translate([chassisX/cos(topAngle)-10,chassisY/2-25,0])cylinder(r=10);
				translate([chassisX-nutApothem/cos(30)/2-postWall,chassisY/2-25,-topHeight/2])cylinder(r=nutApothem/cos(30)/2+postWall);
			}
			translate([0,0,topHeight])hull(){
				rotate([0,-topAngle,0])translate([chassisX/cos(topAngle)-10,chassisY/2+25,0])cylinder(r=10);
				translate([chassisX-nutApothem/cos(30)/2-postWall,chassisY/2+25,-topHeight/2])cylinder(r=nutApothem/cos(30)/2+postWall);
			}

			translate([chassisX/2,chassisY/2,10])cylinder(r=nutApothem/cos(30)/2+postWall, h=topHeight+chassisX/2*sin(topAngle)+postWall-10);
			translate([chassisX-nutApothem/cos(30)/2-postWall,chassisY/2+25,10])cylinder(r=nutApothem/cos(30)/2+postWall, h=topHeight+chassisX/cos(topAngle)*sin(topAngle)-10);
			translate([chassisX-nutApothem/cos(30)/2-postWall,chassisY/2-25,10])cylinder(r=nutApothem/cos(30)/2+postWall, h=topHeight+chassisX/cos(topAngle)*sin(topAngle)-10);

		
		}
		//wire pass through holes
		rotate([0,-topAngle,0])translate([12,12,topHeight-1])minkowski(){
				cylinder(r=2,h=chassisThick+2);
				cube([60,20,chassisThick+2]);
			}
		rotate([0,-topAngle,0])translate([12,68,topHeight-1])minkowski(){
				cylinder(r=2,h=chassisThick+2);
				cube([60,20,chassisThick+2]);
			}
		/*rotate([0,-topAngle,0])translate([75,40,topHeight-1])minkowski(){
				cylinder(r=2,h=chassisThick+2);
				cube([15,20,chassisThick+2]);
			}*/
		//pcb holes
		rotate([0,-topAngle,0])translate([chassisX-4,chassisY-8.5,topHeight-1])cylinder(r=2, h=topThick+2);
		rotate([0,-topAngle,0])translate([chassisX-4,8.5,topHeight-1])cylinder(r=2, h=topThick+2);
		rotate([0,-topAngle,0])translate([4,8.5,topHeight-1])cylinder(r=2, h=topThick+2);
		rotate([0,-topAngle,0])translate([4,chassisY-8.5,topHeight-1])cylinder(r=2, h=topThick+2);

		rotate([0,-topAngle,0])translate([chassisX-11,chassisY/2,topHeight-1])cylinder(r=2, h=topThick+2);
		rotate([0,-topAngle,0])translate([12,chassisY/2,topHeight-1])cylinder(r=2, h=topThick+2);
		rotate([0,-topAngle,0])translate([12,chassisY/2+8,topHeight-1])cylinder(r=2, h=topThick+2);
		rotate([0,-topAngle,0])translate([12,chassisY/2-10,topHeight-1])cylinder(r=2, h=topThick+2);
		
		//nut cavities
		translate([chassisX/2,chassisY/2,-1+10])cylinder(r=nutApothem/cos(30)/2, h=nutHeight+1,$fn=6);
		translate([chassisX-nutApothem/cos(30)/2-postWall,chassisY/2+25,-1+10])cylinder(r=nutApothem/cos(30)/2, h=nutHeight+1,$fn=6);
		translate([chassisX-nutApothem/cos(30)/2-postWall,chassisY/2-25,-1+10])cylinder(r=nutApothem/cos(30)/2, h=nutHeight+1,$fn=6);
		//screw holes
		translate([chassisX/2,chassisY/2,-1+10])cylinder(r=1.75, h=15);
		translate([chassisX-nutApothem/cos(30)/2-postWall,chassisY/2+25,-1+10])cylinder(r=1.75, h=15);
		translate([chassisX-nutApothem/cos(30)/2-postWall,chassisY/2-25,-1+10])cylinder(r=1.75, h=15);
		
		//slices
		translate([-1,chassisY/2-0.5,topHeight+topThick-layerHeight*3])rotate([0,-topAngle,0])cube([chassisX/cos(topAngle)+2,1,layerHeight*3+1]);
		translate([0,0,topHeight])rotate([0,-topAngle,0])translate([chassisX/2-0.5,-1,topThick-layerHeight*3])cube([1,chassisY+2,layerHeight*3+1]);

		//layout
		translate([0,0,-chassisHeight-chassisThick])layout(1);
	}
}

module truss(){
	
	translate([0,0,chassisHeight+chassisThick])difference(){
		union(){
			translate([0,chassisY/2-6,topHeight+topThick+pcbThick+pcbHeight])cube([chassisX-13.6,12,10]);
			translate([chassisX-13.6-8,chassisY/2-25,topHeight+topThick+pcbThick+pcbHeight])cube([8,50,10]);	
			hull(){
				translate([0,chassisY/2-6,topHeight+topThick+pcbThick+pcbHeight])cube([15,12,4]);
				translate([12,chassisY/2,topHeight+topThick+pcbThick+2])cylinder(r=5, h=topThick+2);
				translate([12,chassisY/2+8,topHeight+topThick+pcbThick+2])cylinder(r=5, h=topThick+2);
				translate([12,chassisY/2-10,topHeight+topThick+pcbThick+2])cylinder(r=5, h=topThick+2);
			}

		}
		translate([12,chassisY/2,topHeight+topThick+pcbThick-1])cylinder(r=1.2, h=10);
		translate([12,chassisY/2+8,topHeight+topThick+pcbThick-1])cylinder(r=1.2, h=10);
		translate([12,chassisY/2-10,topHeight+topThick+pcbThick-1])cylinder(r=1.2, h=10);
		//layout
		translate([0,0,-chassisHeight-chassisThick])layout(1);
	}
}


module spacer_mount(){
	
	translate([0,0,chassisHeight+chassisThick])difference(){
		union(){
			translate([chassisX-25,0,topHeight+topThick])cube([9,chassisY,8.5]);
			translate([chassisX-25,0,topHeight+topThick])cube([25-chassisRadius,8,8.5]);
			translate([chassisX-25,chassisY-8,topHeight+topThick])cube([25-chassisRadius,8,8.5]);
			translate([chassisX-25,8,topHeight+topThick])cube([9,10,17.25]);
			translate([chassisX-25,65,topHeight+topThick])cube([9,10,17.25]);

			hull(){
				translate([chassisX-chassisRadius,chassisY-chassisRadius,topHeight+topThick])cylinder(r=chassisRadius, h=8.5);
				rotate([0,-topAngle,0])translate([chassisX-4,chassisY-8.5,topHeight+topThick])cylinder(r=4, h=pcbThick);
			}
			hull(){
				translate([chassisX-chassisRadius,chassisRadius,topHeight+topThick])cylinder(r=chassisRadius, h=8.5);
				rotate([0,-topAngle,0])translate([chassisX-4,8.5,topHeight+topThick])cylinder(r=4, h=pcbThick);
			}
			
		}
		
		//layout
		translate([0,0,-chassisHeight-chassisThick])layout(1);
		translate([chassisX-26,chassisY/2-2,topHeight+topThick])cube([11,4,2]);
		rotate([0,-topAngle,0])translate([chassisX-4,chassisY-8.5,topHeight+topThick-1])cylinder(r=1.5, h=topThick+2+pcbThick);
		rotate([0,-topAngle,0])translate([chassisX-4,8.5,topHeight+topThick-1])cylinder(r=1.5, h=topThick+2+pcbThick);
	}
}

module left_spacer(){
	translate([0,0,chassisHeight+chassisThick])difference(){
		union(){
			rotate([0,-topAngle,0])translate([4,8.5,topHeight+topThick])cylinder(r=4, h=pcbThick);
		}
		rotate([0,-topAngle,0])translate([4,8.5,topHeight+topThick-1])cylinder(r=1.5, h=topThick+2+pcbThick);
	}
}

module right_spacer(){
	translate([0,0,chassisHeight+chassisThick])difference(){
		union(){
			rotate([0,-topAngle,0])translate([4,chassisY-8.5,topHeight+topThick])cylinder(r=4, h=pcbThick);
		}
		rotate([0,-topAngle,0])translate([4,chassisY-8.5,topHeight+topThick-1])cylinder(r=1.5, h=topThick+2+pcbThick);
	}
}

module maze(transparency){
	color("brown",transparency)translate([0,-mazeThick,-mazeThick])cube([maze,maze+mazeThick*2,mazeThick]);
	color("white",transparency)translate([0,-mazeThick,0])cube([maze,mazeThick,mazeWall]);
	color("white",transparency)translate([0,maze,0])cube([maze,mazeThick,mazeWall]);

}

translate([(maze-chassisX)/2,(maze-chassisY)/2,0]){
	layout(0);
	chassis();
	top_plate();
	truss();
	spacer_mount();
	left_spacer();
	right_spacer();
	} 

//maze(0.75);

//printing
//rotate([180,0,0])top_plate();
//rotate([0,180,0])truss();
