package org.usfirst.frc.team930.robot;

import edu.wpi.first.wpilibj.Joystick;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	
	private static final double DEADBAND = .1;
	public final static int DRIVER_PORT = 0;
	public final static int CODRIVER_PORT = 1;
	
	public static OI getInstance() {
		return Holder.instance;
	}

	private OI() {

	}

	public static class Holder {
		public static final OI instance = new OI();
	}

	Joystick driverXbox = new Joystick(DRIVER_PORT);
	Joystick coDriverXbox = new Joystick(CODRIVER_PORT);

	public double getStrafe() {
		double axis = driverXbox.getRawAxis(0);
		if(Math.abs(axis) < DEADBAND) {
			return 0;
		}
		return axis;
	}
	
	public double getForward() {
		double axis = driverXbox.getRawAxis(1);
		if(Math.abs(axis) < DEADBAND) {
			return 0;
		}
		return -1 * axis;
	}
	
	public double getRotX() {
		double axis = driverXbox.getRawAxis(4);
		if(Math.abs(axis) < DEADBAND) {
			return 0;
		}
		return axis;
	}
	
	public double getRotY() {
		double axis = driverXbox.getRawAxis(5);
		if(Math.abs(axis) < DEADBAND) {
			return 0;
		}
		return axis;
	}
}