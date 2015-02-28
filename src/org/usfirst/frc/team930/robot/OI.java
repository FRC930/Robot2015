package org.usfirst.frc.team930.robot;

import edu.wpi.first.wpilibj.ADXL345_SPI;
import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.interfaces.Accelerometer;

import org.usfirst.frc.team930.robot.commands.CloseLeftClaw;
import org.usfirst.frc.team930.robot.commands.CloseRightClaw;
import org.usfirst.frc.team930.robot.commands.OpenLeftClaw;
import org.usfirst.frc.team930.robot.commands.OpenRightClaw;
import org.usfirst.frc.team930.robot.BoxCar;

import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OI {

	public static enum Axis {
		X, Y, Z;
	}

	private static final double DEADBAND = .1;
	public final static int DRIVER_PORT = 0;
	public final static int CODRIVER_PORT = 1;

	ADXL345_SPI armAccel = new ADXL345_SPI(SPI.Port.kOnboardCS0,
			Accelerometer.Range.k2G);
	ADXL345_SPI bindAccel = new ADXL345_SPI(SPI.Port.kOnboardCS1,
			Accelerometer.Range.k2G);
	BuiltInAccelerometer roboAccel = new BuiltInAccelerometer(
			Accelerometer.Range.k8G);

	Joystick driverXbox = new Joystick(DRIVER_PORT);
	Joystick coDriverXbox = new Joystick(CODRIVER_PORT);

	JoystickButton aButton = new JoystickButton(driverXbox, 1);
	JoystickButton xButton = new JoystickButton(driverXbox, 3);
	JoystickButton bButton = new JoystickButton(driverXbox, 2);
	JoystickButton yButton = new JoystickButton(driverXbox, 4);

	BoxCar boxCarArmX = new BoxCar(5);
	BoxCar boxCarArmY = new BoxCar(5);
	BoxCar boxCarArmZ = new BoxCar(5);

	public static OI getInstance() {
		return Holder.instance;
	}

	private OI() {
		aButton.whenPressed(new CloseLeftClaw());
		xButton.whenPressed(new OpenLeftClaw());
		bButton.whenPressed(new CloseRightClaw());
		yButton.whenPressed(new OpenRightClaw());

	}

	public void initAccel() {
		armAccel.free();
		bindAccel.free();
		
		armAccel = new ADXL345_SPI(SPI.Port.kOnboardCS0,
				Accelerometer.Range.k2G);
		bindAccel = new ADXL345_SPI(SPI.Port.kOnboardCS1,
				Accelerometer.Range.k2G);
	}

	public static class Holder {
		public static final OI instance = new OI();
	}

	// Declarations and stuff

	public double getArmHeight() {
		return coDriverXbox.getRawAxis(2);
	}

	// -Accelerations
	public double getCentripAccel(Axis axis, double radius) {
		switch (axis) {
		case X:
			return (getArmAccel(Axis.X) - getRobotAccel(Axis.X)) * radius
					/ RobotMap.ARM_ACCEL_R;
		case Y:
			return this.getCentripAccel(Axis.X, radius)
					* RobotMap.ARM_ACCEL_TAN;
		default:
			return 930;
		}
	}

	public double getArmAccel(Axis axis) {
		switch (axis) {
		case X:
			return boxCarArmX.calc(-armAccel.getX());
		case Y:
			return boxCarArmY.calc(-armAccel.getY());
		case Z:
			return boxCarArmZ.calc(armAccel.getZ());
		default:
			return 930;
		}
	}

	/*
	 * public double getOtherAccelX() { return otherAccel.getX(); } public
	 * double getOtherAccelY() { return otherAccel.getY(); } public double
	 * getOtherAccelZ() { return otherAccel.getZ(); }
	 */

	public double getRobotAccel(Axis axis) {
		switch (axis) {
		case X:
			return -roboAccel.getX();
		case Y:
			return -roboAccel.getY();
		case Z:
			return roboAccel.getZ();
		default:
			return 930;
		}
	}

	// -Joysticks
	public double getStrafe() {
		double axis = driverXbox.getRawAxis(0);
		if (Math.abs(axis) < DEADBAND) {
			return 0;
		}
		return axis;
	}

	public double getForward() {
		double axis = driverXbox.getRawAxis(1);
		if (Math.abs(axis) < DEADBAND) {
			return 0;
		}
		return -1 * axis;
	}

	public double getRotX() {
		double axis = driverXbox.getRawAxis(4);
		if (Math.abs(axis) < DEADBAND) {
			return 0;
		}
		return axis;
	}

	public double getRotY() {
		double axis = driverXbox.getRawAxis(5);
		if (Math.abs(axis) < DEADBAND) {
			return 0;
		}
		return axis;
	}

	public double getArmCoDriver() {
		double axis = coDriverXbox.getRawAxis(1);
		if (Math.abs(axis) < DEADBAND) {
			return 0;
		}
		return -1 * axis;
	}
}
