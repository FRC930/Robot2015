package org.usfirst.frc.team930.robot.subsystems;

import org.usfirst.frc.team930.robot.OI;
import org.usfirst.frc.team930.robot.Robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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
<<<<<<< HEAD
	 * THIS IS A MATH CLASS: do dont pass your speedcontrollers and shit
=======
	 * THIS IS A MATH CLASS: dont pass your speedcontrollers and shit
>>>>>>> fa75819e2d4e07df98d36a421a4614893eab8f70
	 */

	// DECLARATIONS OF VARIABLES AND OTHER THINGS THE CODE MIGHT FIND USEFUL

	// Field Centric Specific Components
	public boolean isFieldcentric; // are we doin' field centric calculations?
	private double heading, lastHeading; // field centric headings

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
		this.lastHeading = 0;

		R = Math.sqrt(Math.pow(length, 2) + Math.pow(width, 2));
	}

	public SwerveDrive(double length, double width, boolean fieldcentric) {
		this(length, width);
		this.isFieldcentric = fieldcentric;

		R = Math.sqrt(Math.pow(length, 2) + Math.pow(width, 2));
	}

	// INTERFACE METHODS

	// update swerve for raw vals (robo or field cent)
	public void updateSwerve(double forward, double strafe, double rotIn) {
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

		// if (Math.abs(topRightAngle) > Math.abs(oldTopRightAngle) + 360){
		// System.out.println("Top Right Angle: " + topRightAngle);
		// topRightAngle = oldTopRightAngle;
		// System.out.println("hi I'm in the if statement");
		// System.out.println("OLD Top Right Angle: " + topRightAngle);
		// }

		/*
		 * 
		 * // Set wheel angles topRightAngle = Math.atan2(topX, rightY) *
		 * RAD_TO_DEG; topLeftAngle = Math.atan2(topX, leftY) * RAD_TO_DEG;
		 * bottomLeftAngle = Math.atan2(bottomX, leftY) * RAD_TO_DEG;
		 * bottomRightAngle = Math.atan2(bottomX, rightY) * RAD_TO_DEG;
		 */
		// output

		/*
		 * System.out.println("top right speed: " + topRightSpeed + "\n" +
		 * "top left speed: " + topLeftSpeed + "\n" + "bottom left speed: " +
		 * bottomLeftSpeed + "\n" + "bottom right speed: " + bottomRightSpeed +
		 * "\n"); System.out.println("top right angle: " + topRightAngle + "\n"
		 * + "top left angle: " + topLeftAngle + "\n" + "bottom left angle: " +
		 * bottomLeftAngle + "\n" + "bottom right angle: " + bottomRightAngle +
		 * "\n");
		 */
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