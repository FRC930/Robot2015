package org.usfirst.frc.team930.robot;
/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {

	public static final int RIGHT_ARM = 5;
	public static final int LEFT_ARM = 6;

	public static final int FRONT_LEFT = 2;
	public static final int FRONT_RIGHT = 1;
	public static final int BACK_LEFT = 4;
	public static final int BACK_RIGHT = 3;
	
	public static final int RELAY_RIGHT = 0;
	public static final int RELAY_LEFT = 1;
	public static final int LIMIT_RIGHT_OPEN = 3;
	public static final int LIMIT_LEFT_OPEN = 1;
	public static final int LIMIT_RIGHT_CLOSED = 2;
	public static final int LIMIT_LEFT_CLOSED = 0;
	
	public static final int GYRO = 1;

}
