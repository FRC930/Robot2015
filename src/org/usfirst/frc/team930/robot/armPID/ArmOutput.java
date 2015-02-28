package org.usfirst.frc.team930.robot.armPID;

import org.usfirst.frc.team930.robot.Robot;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.SpeedController;

public class ArmOutput implements PIDOutput {

	PIDController bindPID;
	BindOutput bindOut;
	SpeedController scRight;
	SpeedController scLeft;
	
	public ArmOutput(SpeedController c1, SpeedController c2) {
		
		bindOut = Robot.bindOut;
		scRight = c1;
		scLeft = c2;
	}

	public void pidWrite(double output) {
		scRight.set(output + bindOut.getOut());
		scLeft.set(output - bindOut.getOut());
	}
}