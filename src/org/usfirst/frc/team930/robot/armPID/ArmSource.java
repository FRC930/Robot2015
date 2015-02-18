package org.usfirst.frc.team930.robot.armPID;

import org.usfirst.frc.team930.robot.OI;

import edu.wpi.first.wpilibj.PIDSource;

public class ArmSource implements PIDSource {
	OI oi = OI.getInstance();

	public ArmSource() {
		
	}

	public double pidGet() {
		return this.getArmAngle();
	}

	public double getArmAngle() {
		double xarm = oi.getArmAccelX();
		double yarm = oi.getArmAccelY();
		double xrobot = oi.getRobotAccelY();
		double yrobot = oi.getRobotAccelZ();

		return Math.atan2((xrobot * yarm - yrobot * xarm),
				(xrobot * xarm + yrobot * yarm));
	}
}