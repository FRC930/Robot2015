package org.usfirst.frc.team930.robot.subsystems;

public class SwerveDrive {

	/*
	 * Team 930 SwerveDrive
	 * 
	 * Heading of 0 degrees is straight forward to the robot OR in fieldcentric
	 * it is perpendicular to the driver station and aimed downfield.
	 * 
	 * Velocity inputs are measured as (+1,-1) to be the first quadrant
	 * 
	 * Interface for other objects are the INTERFACE METHODS, and private
	 * calculation methods are the CALC METHODS.
	 * 
	 * For the field centric utilization of the code, you must calibrate the
	 * robot so that it faces perpendicular to the baseline of the field.
	 * Heading zero is that way
	 * 
	 * THIS IS A MATH CLASS: do dont pass your speedcontrollers and shit
	 */

	// DECLARATIONS OF VARIABLES AND OTHER THINGS THE CODE MIGHT FIND USEFUL

	// Field Centric Specific Components
	public boolean isFieldcentric; // are we doin' field centric calculations?
	private double heading, lastHeading; // field centric headings

	// Robot Specs
	private double width, length; // length and width of the robot

	// Calculation Components
	final double RAD_TO_DEG = 180 / Math.PI;
	private double topRightSpeed, topLeftSpeed, bottomLeftSpeed,
			bottomRightSpeed;
	private double topRightAngle, topLeftAngle, bottomLeftAngle,
			bottomRightAngle;

	// Output components
	public enum Outputs {
		frontLeftSpeed, frontRightSpeed, backLeftSpeed, backRightSpeed, frontLeftAngle, frontRightAngle, backLeftAngle, backRightAngle;
	}

	// CONSTRUCTORS - eventually get them all to accept passed SpeedControllers

	public SwerveDrive(double length, double width) {
		this.width = width;
		this.length = length;

		this.isFieldcentric = false;
		this.lastHeading = 0;
	}

	public SwerveDrive(double length, double width, boolean fieldcentric) {
		this(length, width);
		this.isFieldcentric = fieldcentric;
	}

	// INTERFACE METHODS

	// update swerve for raw vals (robo or field cent)
	public void updateSwerve(double forward, double strafe, double rotIn) {
		if ((forward == strafe) == (rotIn == 0)) {
			topRightSpeed = 0;
			topLeftSpeed = 0;
			bottomLeftSpeed = 0;
			bottomRightSpeed = 0;

			topRightAngle = 0;
			topLeftAngle = 0;
			bottomLeftAngle = 0;
			bottomRightAngle = 0;
		} else {

			// rotIn is either the heading for fieldcentric code or omega in
			// robot
			// centric code, but both are measured as clockwise from North, so
			// just
			// keep that in mind as you may be reading this absurdly long
			// comment
			// about rotIn and absurdly long comments. Rather meta, no?

			// Normalize VIn (strafe and forward)
			// not necessary with the XBox controllers but just in case...
			if (Math.pow(forward, 2) + Math.pow(strafe, 2) > 1) {
				double f2 = Math.pow(forward, 2);
				double s2 = Math.pow(strafe, 2);

				forward = -Math.sqrt(f2 / (f2 + s2));
				strafe = Math.sqrt(s2 / (f2 + s2));
			} else {
				forward *= -1;
			}

			if (isFieldcentric == false) {
				rotIn *= 2 * Math.PI;
			} else {
				heading = rotIn;

				// defining the velocity vector in polar to convert to
				// robocentric
				double magnitudeV = Math.sqrt(Math.pow(strafe, 2)
						+ Math.pow(forward, 2));
				double phiV = 90 - Math.atan2(strafe, forward) * RAD_TO_DEG;

				// do the conversion so the robocentic code can handle the rest
				strafe = magnitudeV * Math.sin(phiV - lastHeading);
				forward = magnitudeV * Math.cos(phiV - lastHeading);
				rotIn = (heading - lastHeading) / .02;
				// the code should be updated to the robot every 20ms, so
				// hardcoded

				lastHeading = heading;
			}

			// Set intermediates
			double Rx = rotIn * length / 2;
			double Ry = rotIn * width / 2;

			double topX = strafe + Rx;
			double rightY = forward - Ry;
			double bottomX = strafe - Rx;
			double leftY = forward + Ry;

			// Set wheel speeds
			double tx2 = Math.pow(topX, 2);
			double bx2 = Math.pow(bottomX, 2);
			double ry2 = Math.pow(rightY, 2);
			double ly2 = Math.pow(leftY, 2);

			topRightSpeed = Math.sqrt(tx2 + ry2);
			topLeftSpeed = Math.sqrt(tx2 + ly2);
			bottomLeftSpeed = Math.sqrt(bx2 + ly2);
			bottomRightSpeed = Math.sqrt(bx2 + ry2);

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
			topRightAngle = Math.atan2(topX, rightY) * RAD_TO_DEG;
			topLeftAngle = Math.atan2(topX, leftY) * RAD_TO_DEG;
			bottomLeftAngle = Math.atan2(bottomX, leftY) * RAD_TO_DEG;
			bottomRightAngle = Math.atan2(bottomX, rightY) * RAD_TO_DEG;

			// output
			/*
			 * System.out.println(topRightSpeed + "\n" + topLeftSpeed + "\n" +
			 * bottomLeftSpeed + "\n" + bottomRightSpeed + "\n");
			 * System.out.println(topRightAngle + "\n" + topLeftAngle + "\n" +
			 * bottomLeftAngle + "\n" + bottomRightAngle + "\n");
			 */
		}

	}

	// update swerve from joysticks (either robo or field cent)

	public void switchCentricity() { // switch from robo to field and vicea
										// versa while in code
		isFieldcentric = !isFieldcentric;
	}

	public double output(Outputs val) {
		switch (val) {
		case frontRightSpeed:
			return topRightSpeed;
		case frontRightAngle:
			return topRightAngle;
		case frontLeftSpeed:
			return topLeftSpeed;
		case frontLeftAngle:
			return topLeftAngle;
		case backLeftSpeed:
			return bottomLeftSpeed;
		case backLeftAngle:
			return bottomLeftAngle;
		case backRightSpeed:
			return bottomRightSpeed;
		case backRightAngle:
			return bottomRightAngle;
		default:
			return 930; // incase of error, we throw a ridiculous value
		}
	}
}