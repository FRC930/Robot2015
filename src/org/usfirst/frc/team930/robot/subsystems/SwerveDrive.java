package org.usfirst.frc.team930.robot.subsystems;

//import edu.wpi.first.wpilibj.SpeedController;
//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
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

	// SpeedController Components - if possible to implement
	// SpeedController TR, TL, BR, BL;

	// Field Centric Specific Components
	private boolean isFieldcentric; // are we doin' field centric calculations?

	private double heading, lastHeading; // field centric headings

	// Joysticks
	Joystick translationStick, headingStick;

	// Robot Specs
	private double width, length; // length and width of the robot

	// Calculation Components
	final double ANGLE_CONVERSION = 180 / Math.PI;

	// CONSTRUCTORS - eventually get them all to accept passed SpeedControllers

	public SwerveDrive(double length, double width) {
		this.width = width;
		this.length = length;

		this.isFieldcentric = false;
		this.lastHeading = 0;

		this.updateEndTime = System.currentTimeMillis();
	}

	public SwerveDrive(double length, double width, boolean fieldcentric) {
		this.width = width;
		this.length = length;

		this.isFieldcentric = fieldcentric;
		this.lastHeading = 0;

		this.updateEndTime = System.currentTimeMillis();
	}

	public SwerveDrive(double length, double width, Joystick translationStick,
			Joystick headingStick) {
		this.width = width;
		this.length = length;
		this.translationStick = translationStick;
		this.headingStick = headingStick;

		this.isFieldcentric = false;
		this.lastHeading = 0;

		this.updateEndTime = System.currentTimeMillis();
	}

	public SwerveDrive(double length, double width, boolean fieldcentric,
			Joystick translationStick, Joystick headingStick) {
		this.width = width;
		this.length = length;
		this.translationStick = translationStick;
		this.headingStick = headingStick;

		this.isFieldcentric = fieldcentric;
		this.lastHeading = 0;

		this.updateEndTime = System.currentTimeMillis();
	}

	// INTERFACE METHODS

	// update swerve for raw robocentic values
	public void updateSwerve(double forward, double strafe, double rotationCW) {

		this.timeControl();

		rotationCW *= 2 * Math.PI;

		// Convert robo to field cent
		// calculate the current heading
		heading = 90 - Math.atan2(headingStick.getX(), headingStick.getY())
				* ANGLE_CONVERSION;

		// defining the velocity vector in polar to convert to robocentric
		double magnitudeV = Math.sqrt(Math.pow(translationStick.getX(), 2)
				+ Math.pow(translationStick.getY(), 2));
		double phiV = 90
				- Math.atan2(translationStick.getX(), translationStick.getY())
				* ANGLE_CONVERSION;

		// do the conversion so the robocentic code can handle the rest
		strafe = magnitudeV * Math.sin(phiV - lastHeading);
		forward = magnitudeV * Math.cos(phiV - lastHeading);
		rotationCW = (heading - lastHeading) / (timeSinceLastUpdate * 1000);

		lastHeading = heading;

		// Normalize Translation
		// not necessary with the XBox controllers but just in case...
		if (Math.pow(forward, 2) + Math.pow(strafe, 2) > 1) {
			double f2 = Math.pow(forward, 2);
			double s2 = Math.pow(strafe, 2);

			forward = Math.sqrt(f2 / (f2 + s2));
			strafe = Math.sqrt(s2 / (f2 + s2));
		}

		// Set intermediates
		double Rx = rotationCW * length / 2;
		double Ry = rotationCW * width / 2;

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
		double topRightAngle = 90 - Math.atan2(topX, rightY) * ANGLE_CONVERSION;
		double topLeftAngle = 90 - Math.atan2(topX, leftY) * ANGLE_CONVERSION;
		double bottomLeftAngle = 90 - Math.atan2(bottomX, leftY)
				* ANGLE_CONVERSION;
		double bottomRightAngle = 90 - Math.atan2(bottomX, rightY)
				* ANGLE_CONVERSION;

		// output
		System.out.println(topRightSpeed + "\n" + topLeftSpeed + "\n"
				+ bottomLeftSpeed + "\n" + bottomRightSpeed + "\n");
		System.out.println(topRightAngle + "\n" + topLeftAngle + "\n"
				+ bottomLeftAngle + "\n" + bottomRightAngle + "\n");

		updateEndTime = System.currentTimeMillis();
	}

	// update swerve from joysticks (either robo or field cent)
	public void updateSwerve(Joystick translationStick, Joystick headingStick) {

		// basic input values
		double strafe, forward, rotationCW;

		this.timeControl();

		// if the code is field cent set the sticks for future use, otherwise
		// use to get values for the robo cent
		if (isFieldcentric == true) {
			this.translationStick = translationStick;
			this.headingStick = headingStick; // field cent heading
		} else {
			strafe = translationStick.getX();
			forward = translationStick.getY();
			rotationCW = headingStick.getX() * 2 * Math.PI; // rotational
															// velocity omega,
															// converted to rad
		}

		// Convert robo to field cent
		// calculate the current heading
		heading = 90 - Math.atan2(headingStick.getX(), headingStick.getY())
				* ANGLE_CONVERSION;

		// defining the velocity vector in polar to convert to robocentric
		double magnitudeV = Math.sqrt(Math.pow(translationStick.getX(), 2)
				+ Math.pow(translationStick.getY(), 2));
		double phiV = 90
				- Math.atan2(translationStick.getX(), translationStick.getY())
				* ANGLE_CONVERSION;

		// do the conversion so the robocentic code can handle the rest
		strafe = magnitudeV * Math.sin(phiV - lastHeading);
		forward = magnitudeV * Math.cos(phiV - lastHeading);
		rotationCW = (heading - lastHeading) / (timeSinceLastUpdate * 1000);

		lastHeading = heading;

		// Normalize Translation
		// not necessary with the XBox controllers but just in case...
		if (Math.pow(forward, 2) + Math.pow(strafe, 2) > 1) {
			double f2 = Math.pow(forward, 2);
			double s2 = Math.pow(strafe, 2);

			forward = Math.sqrt(f2 / (f2 + s2));
			strafe = Math.sqrt(s2 / (f2 + s2));
		}

		// Set intermediates
		double Rx = rotationCW * length / 2;
		double Ry = rotationCW * width / 2;

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
		double topRightAngle = 90 - Math.atan2(topX, rightY) * ANGLE_CONVERSION;
		double topLeftAngle = 90 - Math.atan2(topX, leftY) * ANGLE_CONVERSION;
		double bottomLeftAngle = 90 - Math.atan2(bottomX, leftY)
				* ANGLE_CONVERSION;
		double bottomRightAngle = 90 - Math.atan2(bottomX, rightY)
				* ANGLE_CONVERSION;

		// output
		System.out.println(topRightSpeed + "\n" + topLeftSpeed + "\n"
				+ bottomLeftSpeed + "\n" + bottomRightSpeed + "\n");
		System.out.println(topRightAngle + "\n" + topLeftAngle + "\n"
				+ bottomLeftAngle + "\n" + bottomRightAngle + "\n");

		updateEndTime = System.currentTimeMillis();
	}

	// update swerve from your joysticks are already in constructor
	public void updateSwerve() {

		// basic input values
		double strafe, forward, rotationCW;

		this.timeControl();

		// if the code is field cent set the sticks for future use, otherwise
		// use to get values for the robo cent
		if (isFieldcentric == false) {
			strafe = translationStick.getX();
			forward = translationStick.getY();
			rotationCW = headingStick.getX() * 2 * Math.PI;
		} else { // if you are using fieldcent...
			heading = 90 - Math.atan2(headingStick.getX(), headingStick.getY())
					* ANGLE_CONVERSION;

			// defining the velocity vector in polar to convert to robocentric
			double magnitudeV = Math.sqrt(Math.pow(translationStick.getX(), 2)
					+ Math.pow(translationStick.getY(), 2));
			double phiV = 90
					- Math.atan2(translationStick.getX(),
							translationStick.getY()) * ANGLE_CONVERSION;

			// do the conversion so the robocentic code can handle the rest
			strafe = magnitudeV * Math.sin(phiV - lastHeading);
			forward = magnitudeV * Math.cos(phiV - lastHeading);
			rotationCW = (heading - lastHeading) / (timeSinceLastUpdate * 1000);

			lastHeading = heading;
		}

		// Normalize Translation
		// not necessary with the XBox controllers but just in case...
		if (Math.pow(forward, 2) + Math.pow(strafe, 2) > 1) {
			double f2 = Math.pow(forward, 2);
			double s2 = Math.pow(strafe, 2);

			forward = Math.sqrt(f2 / (f2 + s2));
			strafe = Math.sqrt(s2 / (f2 + s2));
		}

		// Set intermediates
		double Rx = rotationCW * length / 2;
		double Ry = rotationCW * width / 2;

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
		double topRightAngle = 90 - Math.atan2(topX, rightY) * ANGLE_CONVERSION;
		double topLeftAngle = 90 - Math.atan2(topX, leftY) * ANGLE_CONVERSION;
		double bottomLeftAngle = 90 - Math.atan2(bottomX, leftY)
				* ANGLE_CONVERSION;
		double bottomRightAngle = 90 - Math.atan2(bottomX, rightY)
				* ANGLE_CONVERSION;

		// output
		System.out.println(topRightSpeed + "\n" + topLeftSpeed + "\n"
				+ bottomLeftSpeed + "\n" + bottomRightSpeed + "\n");
		System.out.println(topRightAngle + "\n" + topLeftAngle + "\n"
				+ bottomLeftAngle + "\n" + bottomRightAngle + "\n");

		updateEndTime = System.currentTimeMillis();
	}

	public void switchCentricity() { // switch from robo to field and vicea
										// versa while in code
		isFieldcentric = !isFieldcentric;
	}

	// extra output code

	// SmartDashboard output
	// SmartDashboard.putNumber("Top Right Speed", topRightSpeed);
	// SmartDashboard.putNumber("Top Left Speed", topRightSpeed);
	// SmartDashboard.putNumber("Bottom Left Speed", topRightSpeed);
	// SmartDashboard.putNumber("Bottom Right Speed", topRightSpeed);
	//
	// SmartDashboard.putNumber("Top Right Angle", topRightAngle);
	// SmartDashboard.putNumber("Top Left Angle", topRightAngle);
	// SmartDashboard.putNumber("Bottom Left Angle", topRightAngle);
	// SmartDashboard.putNumber("Bottom Right Angle", topRightAngle);

	// Wheel Output
	// TR.set(topRightSpeed);
	// TL.set(topLeftSpeed);
	// BL.set(bottomLeftSpeed);
	// BR.set(bottomRightSpeed);
	//
	// 

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