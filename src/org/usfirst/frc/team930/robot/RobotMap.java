package org.usfirst.frc.team930.robot;

/**
 * For storage of physical constants like port numbers or dimensions to reduce
 * magic numbers
 */
public class RobotMap {

	// PORTS
	public static final int RIGHT_ARM = 5;
	public static final int LEFT_ARM = 6;

	public static final int FRONT_LEFT = 2;
	public static final int FRONT_RIGHT = 1;
	public static final int BACK_LEFT = 4;
	public static final int BACK_RIGHT = 3;

	public static final int RELAY_RIGHT = 0;
	public static final int RELAY_LEFT = 1;
	public static final int LIMIT_RIGHT_OPEN = 3;
	public static final int LIMIT_LEFT_OPEN = 0;
	public static final int LIMIT_RIGHT_CLOSED = 1;
	public static final int LIMIT_LEFT_CLOSED = 2;

	public static final int GYRO = 1;

	// DIMENSIONS
	public static final double DRIVETRAIN_WIDTH = 21.25;
	public static final double DRIVETRAIN_LENGTH = 33.00;

	public static final double ROBOT_ACCEL_R = 10.0;

	public static final double ARM_ACCEL_R = 10.0;
	public static final double ARM_ACCEL_TAN = 10.0 / 10.0; // Y/X

	public static final double BIND_ACCEL_R = 10.0;
}
