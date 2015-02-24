package org.usfirst.frc.team930.robot.armPID;

import org.usfirst.frc.team930.robot.OI;
import org.usfirst.frc.team930.robot.Robot;
import org.usfirst.frc.team930.robot.subsystems.Arm;

import edu.wpi.first.wpilibj.PIDSource;

public class ArmSource implements PIDSource {
	OI oi = OI.getInstance();
	Arm arm = Robot.arm;

	public double pidGet() {
		return arm.getArmAngle();
	}

	
}