package org.usfirst.frc.team930.robot.armPID;

import edu.wpi.first.wpilibj.PIDOutput;

public class BindOutput implements PIDOutput {
	
	double PIDOut;
	
	public BindOutput(){
		
	}
	
	public double getOut(){
		return PIDOut;
	}
	
	public void pidWrite(double output) {
		PIDOut = output;
	}
	
}