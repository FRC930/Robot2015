package org.usfirst.frc.team930.robot.subsystems;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Subsystem;

public class SwerveDrive extends Subsystem {

	/*
	 * Team 930 SwerveDrive
	 * 
	 * Heading of 0 degrees is straight forward to the robot. Interface for
	 * other objects are the INTERFACE METHODS, and private calculation methods
	 * are the CALC METHODS.
	 * 
	 * For the field centric utilization of the code, you must calibrate the
	 * robot so that it faces perpendicular to the baseline of the field.
	 * Heading zero is that way
	 */

	// DECLARATIONS OF VARIABLES AND OTHER THINGS THE CODE MIGHT FIND USEFUL

	// Time Components
	final long UPDATE_TIME = 5; // time between updates to the robot (ms)
	long updateStartTime; // time of this update's start
	long updateEndTime; // time of the last update's end
	long timeSinceLastUpdate; // time since the last update, in seconds

	// Field Centric Specific Components
	private boolean isFieldcentric; // are we doin' field centric calculations?

	private double heading, lastHeading; // field centric headings

	// Robot Specs
	private double width, length; // length and width of the robot

	// Calculation Components
	final double RAD_TO_DEG = 180 / Math.PI;

	// Output components
	private enum Outputs {
		TRSpeed, TRAngle, TLSpeed, TLAngle, BLSpeed, BLAngle, BRSpeed, BRAngle
	}

	// CONSTRUCTORS - eventually get them all to accept passed SpeedControllers

	public SwerveDrive(double length, double width) {
		this.width = width;
		this.length = length;

		this.isFieldcentric = false;
		this.lastHeading = 0;

		this.updateEndTime = System.currentTimeMillis();
	}

	public SwerveDrive(double length, double width, boolean fieldcentric) {
		this(length, width);
		this.isFieldcentric = fieldcentric;
	}

	// INTERFACE METHODS

	// update swerve for raw vals (robo or field cent)
	public void updateSwerve(double forward, double strafe, double rotIn) {

		// rotIn is either the heading for fieldcentric code or omega in robot
		// centric code, but both are measured as clockwise from North, so just
		// keep that in mind as you may be reading this absurdly long comment
		// about rotIn and absurdly long comments. Rather meta, no?

		this.timeControl();

		// Normalize VIn (strafe and forward)
		// not necessary with the XBox controllers but just in case...
		if (Math.pow(forward, 2) + Math.pow(strafe, 2) > 1) {
			double f2 = Math.pow(forward, 2);
			double s2 = Math.pow(strafe, 2);

			forward = Math.sqrt(f2 / (f2 + s2));
			strafe = Math.sqrt(s2 / (f2 + s2));
		}

		if (isFieldcentric == false) {
			rotIn *= 2 * Math.PI;
		} else {
			heading = rotIn;

			// defining the velocity vector in polar to convert to robocentric
			double magnitudeV = Math.sqrt(Math.pow(strafe, 2)
					+ Math.pow(forward, 2));
			double phiV = 90 - Math.atan2(strafe, forward) * RAD_TO_DEG;

			// do the conversion so the robocentic code can handle the rest
			strafe = magnitudeV * Math.sin(phiV - lastHeading);
			forward = magnitudeV * Math.cos(phiV - lastHeading);
			rotIn = (heading - lastHeading) / (timeSinceLastUpdate * 1000);

			lastHeading = heading;
		}

		// Set intermediates
		double Rx = rotIn * length / 2;
		double Ry = rotIn * width / 2;

		double topX = strafe + Rx;
		double rightY = forward - Ry;
		double bottomX = strafe - Rx;
		double leftY = strafe + Ry;

		// Set wheel speeds
		double tx2 = Math.pow(topX, 2);
		double bx2 = Math.pow(bottomX, 2);
		double ry2 = Math.pow(rightY, 2);
		double ly2 = Math.pow(leftY, 2);

		double topRightSpeed = Math.sqrt(tx2 + ry2);
		double topLeftSpeed = Math.sqrt(tx2 + ly2);
		double bottomLeftSpeed = Math.sqrt(bx2 + ly2);
		double bottomRightSpeed = Math.sqrt(bx2 + ry2);

		double max;
		max = topRightSpeed;
		if (topLeftSpeed > max) {
			max = topLeftSpeed;
		}
		if (bottomLeftSpeed > max) {
			max = bottomLeftSpeed;
		}
		if (bottomRightSpeed > max) {
			max = bottomRightSpeed;
		}
		if (max > 1) {
			topRightSpeed /= max;
			topLeftSpeed /= max;
			bottomLeftSpeed /= max;
			bottomRightSpeed /= max;
		}

		// Set wheel angles
		double topRightAngle = 90 - Math.atan2(topX, rightY) * RAD_TO_DEG;
		double topLeftAngle = 90 - Math.atan2(topX, leftY) * RAD_TO_DEG;
		double bottomLeftAngle = 90 - Math.atan2(bottomX, leftY) * RAD_TO_DEG;
		double bottomRightAngle = 90 - Math.atan2(bottomX, rightY) * RAD_TO_DEG;

		// output
		System.out.println(topRightSpeed + "\n" + topLeftSpeed + "\n"
				+ bottomLeftSpeed + "\n" + bottomRightSpeed + "\n");
		System.out.println(topRightAngle + "\n" + topLeftAngle + "\n"
				+ bottomLeftAngle + "\n" + bottomRightAngle + "\n");

		updateEndTime = System.currentTimeMillis();
	}

	// update swerve from joysticks (either robo or field cent)

	public void switchCentricity() { // switch from robo to field and vicea
										// versa while in code
		isFieldcentric = !isFieldcentric;
	}

	public double output(Outputs val){
		switch(val){
		case TRSpeed: return topRightSpeed;
		case TRAngle: return topRightAngle;
		case TLSpeed: return topLeftSpeed;
		case TLAngle: return topLeftAngle;
		case BLSpeed: return bottomLeftSpeed;
		case BLAngle: return bottomLeftAngle;
		case BRSpeed: return bottomRightSpeed;
		case BRAngle: return bottomRightAngle;
		}
	}
	
	// CONTROL METHODS

	private void timeControl() {
		updateStartTime = System.currentTimeMillis();
		timeSinceLastUpdate = updateStartTime - updateEndTime;
		while (timeSinceLastUpdate < UPDATE_TIME) {
			timeSinceLastUpdate = updateStartTime - updateEndTime;
		}
	}

	@Override
	protected void initDefaultCommand() {

	}
}