package org.usfirst.frc.team930.robot.armPID;

import org.usfirst.frc.team930.robot.OI;
import org.usfirst.frc.team930.robot.Robot;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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
		long time = System.currentTimeMillis();
		SmartDashboard.putNumber("time", time - OI.time);
		OI.time = time; 
		scRight.set(bindOut.getOut());
		SmartDashboard.putNumber("bindOut", bindOut.getOut());
		scLeft.set(-1 * bindOut.getOut());
	}
}