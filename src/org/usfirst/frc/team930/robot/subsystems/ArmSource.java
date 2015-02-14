package org.usfirst.frc.team930.robot.subsystems;

import org.usfirst.frc.team930.robot.OI;

import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.ADXL345_SPI;

public class ArmSource implements PIDSource {
	OI oi;
	ADXL345_SPI accel;
	double lastAngle;

	public ArmSource(OI oi) {
		this.oi = oi;
		lastAngle = Math.atan2(oi.getArmAccelX(), oi.getArmAccelY());
	}

	public double pidGet() {
		return this.getArmAngle();
	}

	public double getArmAngle() {
		double xarm = oi.getArmAccelX();
		double yarm = oi.getArmAccelY();
		double xrobot = oi.getRobotAccelY();
		double yrobot = oi.getRobotAccelZ();

		return Math.atan2((xrobot * yarm - yrobot * xarm), (xrobot * xarm + yrobot
				* yarm));
	}
}