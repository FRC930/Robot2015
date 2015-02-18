package org.usfirst.frc.team930.robot.armPID;

import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.SpeedController;

public class SCOutput implements PIDOutput {
	
	SpeedController s;
	
	public SCOutput(SpeedController s){
		this.s = s;
	}

	public void pidWrite(double output) {
		s.set(output);		
	}
}