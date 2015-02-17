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
	 * THIS IS A MATH CLASS: dont pass your speedcontrollers and shit
	 */

	// DECLARATIONS OF VARIABLES AND OTHER THINGS THE CODE MIGHT FIND USEFUL

	// Field Centric Specific Components
	public boolean isFieldcentric; // are we doin' field centric calculations?

	// Robot Specs
	private double width, length, R; // length and width of the robot

	// Calculation Components
	public static final double RAD_TO_DEG = 180 / Math.PI;
	private double topRightSpeed, topLeftSpeed, bottomLeftSpeed,
			bottomRightSpeed;
	private double topRightAngle, topLeftAngle, bottomLeftAngle,
			bottomRightAngle, oldTopRightAngle, oldTopLeftAngle,
			oldBottomLeftAngle, oldBottomRightAngle;

	// Output components
	public enum Outputs {
		frontLeftSpeed, frontRightSpeed, backLeftSpeed, backRightSpeed, frontLeftAngle, frontRightAngle, backLeftAngle, backRightAngle;
	}

	// CONSTRUCTORS - eventually get them all to accept passed SpeedControllers

	public SwerveDrive(double length, double width) {
		this.width = width;
		this.length = length;

		this.isFieldcentric = false;

		R = Math.sqrt(Math.pow(length, 2) + Math.pow(width, 2));
	}

	public SwerveDrive(double length, double width, boolean fieldcentric) {
		this(length, width);
		this.isFieldcentric = fieldcentric;
	}

	// INTERFACE METHODS

	// update swerve for raw vals (robo or field cent)
	public void updateSwerve(double forward, double strafe, double rotIn) {
		System.out.println("forward " + forward);
		System.out.println("strafe " + strafe);
		System.out.println("rotIn " + rotIn);
		if (forward == 0 && strafe == 0 && rotIn == 0) {
			topRightSpeed = 0;
			topLeftSpeed = 0;
			bottomLeftSpeed = 0;
			bottomRightSpeed = 0;

			topRightAngle = oldTopRightAngle;
			topLeftAngle = oldTopLeftAngle;
			bottomLeftAngle = oldBottomLeftAngle;
			bottomRightAngle = oldBottomRightAngle;
		} else {

			double FWD = forward;
			double STR = strafe;
			double RCW = rotIn;

			double A = STR - RCW * (length / R);
			double B = STR + RCW * (length / R);
			double C = FWD - RCW * (width / R);
			double D = FWD + RCW * (width / R);

			double A2 = Math.pow(A, 2);
			double B2 = Math.pow(B, 2);
			double C2 = Math.pow(C, 2);
			double D2 = Math.pow(D, 2);

			oldTopRightAngle = topRightAngle;
			oldTopLeftAngle = topLeftAngle;
			oldBottomRightAngle = bottomRightAngle;
			oldBottomLeftAngle = bottomLeftAngle;

			topRightSpeed = Math.sqrt(B2 + C2);
			topLeftSpeed = Math.sqrt(B2 + D2);
			bottomLeftSpeed = Math.sqrt(A2 + D2);
			bottomRightSpeed = Math.sqrt(A2 + C2);

			topRightAngle = (int) (Math.atan2(B, C) * RAD_TO_DEG);
			topLeftAngle = (int) (Math.atan2(B, D) * RAD_TO_DEG);
			bottomLeftAngle = (int) (Math.atan2(A, D) * RAD_TO_DEG);
			bottomRightAngle = (int) (Math.atan2(A, C) * RAD_TO_DEG);

		}

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

		double min;
		min = topRightSpeed;
		if (topLeftSpeed < min) {
			min = topLeftSpeed;
		}
		if (bottomLeftSpeed < min) {
			min = bottomLeftSpeed;
		}
		if (bottomRightSpeed < min) {
			min = bottomRightSpeed;
		}
		if (min < -1) {
			topRightSpeed /= min;
			topLeftSpeed /= min;
			bottomLeftSpeed /= min;
			bottomRightSpeed /= min;
		}
	}

	public void updateSwerve(double forward, double strafe, double rotIn,
			double heading) {
		double tempF = forward;
		double tempS = strafe;

		forward = tempF * Math.sin(heading) + tempS * Math.cos(heading);
		strafe = -1 * tempF * Math.cos(heading) + tempS * Math.sin(heading);

		this.updateSwerve(forward, strafe, rotIn);
	}

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