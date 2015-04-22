package org.usfirst.frc.team930.robot.subsystems;

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
	 * THIS IS A MATH CLASS: dont pass your speedcontrollers and shit
	 */

	// DECLARATIONS OF VARIABLES AND OTHER THINGS THE CODE MIGHT FIND USEFUL

	// Field Centric Specific Components
	public boolean isFieldcentric; // are we doin' field centric calculations?

	// Robot Specs
			private static float width; // length and width of the robot
			private static float length;
			private static float R;

			// Calculation Components
			public static final float RAD_TO_DEG = (float) (180 / Math.PI);
			private static float topRightSpeed;
			private static float topLeftSpeed;
			private static float bottomLeftSpeed;
			private static float bottomRightSpeed;
			private static float topRightAngle, topLeftAngle, bottomLeftAngle,
					bottomRightAngle;

	// Output components
	public enum Outputs {
		frontLeftSpeed, frontRightSpeed, backLeftSpeed, backRightSpeed, frontLeftAngle, frontRightAngle, backLeftAngle, backRightAngle;
	}

	// CONSTRUCTORS - eventually get them all to accept passed SpeedControllers

	public SwerveDrive(double length2, double width2) {
		this.width = (float) width2;
		this.length = (float) length2;

		this.isFieldcentric = false;
		
		R = (float)(1.0/(Math.sqrt(Math.pow(length2, 2) + Math.pow(width2, 2))));
	}

	public SwerveDrive(double length, double width, boolean fieldcentric) {
		this(length, width);
		this.isFieldcentric = fieldcentric;
	}

	// INTERFACE METHODS

	// update swerve for raw vals (robo or field cent)
	public void updateSwerve(float forward, float strafe, float rotIn) {
		if (forward == 0 && strafe == 0 && rotIn == 0) {
			topRightSpeed = 0;
			topLeftSpeed = 0;
			bottomLeftSpeed = 0;
			bottomRightSpeed = 0;

//			topRightAngle = oldTopRightAngle;
//			topLeftAngle = oldTopLeftAngle;
//			bottomLeftAngle = oldBottomLeftAngle;
//			bottomRightAngle = oldBottomRightAngle;
		} else {

			float FWD = forward;
			SmartDashboard.putNumber("forward", forward);
			float STR = strafe;
			SmartDashboard.putNumber("strafe", strafe);
			float RCW = rotIn;

			float A = STR - RCW * (length * R); //Changed R to 2
			float B = STR + RCW * (length * R);
			float C = FWD - RCW * (width * R);
			float D = FWD + RCW * (width * R);

			float A2 = A*A;//Math.pow(A, 2);
			float B2 = B*B;//Math.pow(B, 2);
			float C2 = C*C;//Math.pow(C, 2);
			float D2 = D*D;//Math.pow(D, 2);
//			oldTopRightAngle = topRightAngle;
//			oldTopLeftAngle = topLeftAngle;
//			oldBottomRightAngle = bottomRightAngle;
//			oldBottomLeftAngle = bottomLeftAngle;

			topRightSpeed = (float) Math.sqrt(B2 + C2);
			topLeftSpeed = (float) Math.sqrt(B2 + D2);
			bottomLeftSpeed = (float) Math.sqrt(A2 + D2);
			bottomRightSpeed = (float) Math.sqrt(A2 + C2);

			topRightAngle = (int) (Math.atan2(B, C) * RAD_TO_DEG);
			topLeftAngle = (int) (Math.atan2(B, D) * RAD_TO_DEG);
			bottomLeftAngle = (int) (Math.atan2(A, D) * RAD_TO_DEG);
			bottomRightAngle = (int) (Math.atan2(A, C) * RAD_TO_DEG);

		}

		float max;
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

		float min;
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

	public void updateSwerve(float forward, float strafe, float rotIn,
			double heading) {
		float tempF = forward;
		float tempS = strafe;

		forward = (float)(tempF * Math.sin(heading) + tempS * Math.cos(heading));
		strafe = (float)(-1 * tempF * Math.cos(heading) + tempS * Math.sin(heading));

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