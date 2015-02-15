package org.usfirst.frc.team930.robot.subsystems;

import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.SpeedController;

public class SCSource implements PIDSource {
	
	SpeedController s;
	
	public SCSource (SpeedController s){
		this.s = s;
	}
	
	public double pidGet(){
		return s.get();
	}
}