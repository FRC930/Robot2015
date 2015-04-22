package org.usfirst.frc.team930.robot.armPID;

import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ArmOutput implements PIDOutput {

	SpeedController scRight;
	SpeedController scLeft;
	
	public ArmOutput(SpeedController c1, SpeedController c2) {
		
		scRight = c1;
		scLeft = c2;
		
	}

	public void pidWrite(double output) { 
		SmartDashboard.putNumber("OUTPUT ", output);
		scRight.set(output*-1);
	}
}