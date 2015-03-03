package org.usfirst.frc.team930.robot.armPID;

import org.usfirst.frc.team930.robot.OI;
import org.usfirst.frc.team930.robot.Robot;
import org.usfirst.frc.team930.robot.subsystems.Arm;

import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AngleSource implements PIDSource {
	Arm arm = Robot.arm;

	public double pidGet() {
		long time2 = System.currentTimeMillis();
		SmartDashboard.putNumber("source time", time2 - OI.time2);
		OI.time2 = time2; 
		return arm.getArmAngle();
	}

	
}