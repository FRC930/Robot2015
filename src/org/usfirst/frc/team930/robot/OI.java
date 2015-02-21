package org.usfirst.frc.team930.robot;

import edu.wpi.first.wpilibj.ADXL345_SPI;
import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.interfaces.Accelerometer;

public class OI {

	public static OI getInstance() {
		return Holder.instance;
	}

	public OI() {

	}

	public static class Holder {
		public static final OI instance = new OI();
	}

	// Declarations and stuff
	Joystick stick2 = new Joystick(0);
	ADXL345_SPI armaccel = new ADXL345_SPI(SPI.Port.kOnboardCS0,
			Accelerometer.Range.k2G);
	BuiltInAccelerometer roboaccel = new BuiltInAccelerometer(
			Accelerometer.Range.k8G);
	
	// buttons
	
	
	// funcs and stuff
	public double getArmHeight() {
		return stick2.getRawAxis(2);
	}

	public double getArmAccelX() {
		return armaccel.getX();
	}

	public double getArmAccelY() {
		return armaccel.getY();
	}

	public double getRobotAccelY() {
		return -1 * roboaccel.getY();
	}

	public double getRobotAccelZ() {
		return roboaccel.getZ();
	}
}