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

	// Robot Specs
	private double width, length, R; // length and width of the robot

	// Calculation Components
	final double RAD_TO_DEG = 180 / Math.PI;
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

		R = Math.sqrt(Math.pow(length, 2) + Math.pow(width, 2));
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

			// System.out.println("A " + A);
			// System.out.println("B " + B);
			// System.out.println("C " + C);
			// System.out.println("D " + D);

			double A2 = Math.pow(A, 2);
			double B2 = Math.pow(B, 2);
			double C2 = Math.pow(C, 2);
			double D2 = Math.pow(D, 2);

			// System.out.println("A2 " + A2);
			// System.out.println("B2 " + B2);
			// System.out.println("C2 " + C2);
			// System.out.println("D2 " + D2);

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

		// int modTopRight = (int) ((oldTopRightAngle - topRightAngle) / 360);
		// int modTopLeft = (int) ((oldTopLeftAngle - topLeftAngle) / 360);
		// int modBottomRight = (int) ((oldBottomRightAngle - bottomRightAngle)
		// / 360);
		// int modBottomLeft = (int) ((oldBottomLeftAngle - bottomLeftAngle) /
		// 360);
		//
		// double diffTopRight = (oldTopRightAngle - topRightAngle) % 360;
		// double diffTopLeft = (oldTopLeftAngle - topLeftAngle) % 360;
		// double diffBottomRight = (oldBottomRightAngle - bottomRightAngle) %
		// 360;
		// double diffBottomLeft = (oldBottomLeftAngle - bottomLeftAngle) % 360;
		//
		// /*
		// * So this does run in O(1) since the while loop will only run once
		// * or twice due to the += 360 * (int) lines of code to bring it within
		// * 360 degrees. For all intents and purposes it won't run that long
		// *
		// * WARNING: CURRENTLY SLIGHTLY BROKEN. WORKING ON FIX
		// */
		// int dir;
		// // tr
		// System.out.println("topRightAngle b4 it is set " + topRightAngle);
		// topRightAngle += 360 * (int) ((oldTopRightAngle - topRightAngle) /
		// 360);
		// System.out.println("old tr angle " + oldTopRightAngle);
		// System.out.println("old tr speed " + topRightSpeed);
		//
		//
		// if (oldTopRightAngle > topRightAngle) {
		// dir = 1;
		// } else {
		// dir = -1;
		// }
		// while (Math.abs(oldTopRightAngle - topRightAngle) > 90) {
		// topRightAngle += dir * 180;
		// topRightSpeed *= -1;
		//
		// System.out.println("tr angle " + topRightAngle);
		// System.out.println("tr speed " + topRightSpeed);
		//
		// }
		// // tl
		// topLeftAngle += 360 * (int) (oldTopLeftAngle - topLeftAngle);
		// if (oldTopLeftAngle > topLeftAngle)
		// dir = 1;
		// else
		// dir = -1;
		// while (Math.abs(oldTopLeftAngle - topLeftAngle) > 90) {
		// topLeftAngle += dir * 180;
		// topLeftSpeed *= -1;
		// }
		// // bl
		// bottomLeftAngle += 360 * (int) (oldBottomLeftAngle -
		// bottomLeftAngle);
		// if (oldBottomLeftAngle > bottomLeftAngle)
		// dir = 1;
		// else
		// dir = -1;
		// while (Math.abs(oldBottomLeftAngle - bottomLeftAngle) > 90) {
		// bottomLeftAngle += dir * 180;
		// bottomLeftSpeed *= -1;
		// }
		// // br
		// bottomRightAngle += 360* (int) (oldBottomRightAngle -
		// bottomRightAngle);
		// if (oldBottomRightAngle > bottomRightAngle)
		// dir = 1;
		// else
		// dir = -1;
		// while (Math.abs(oldBottomRightAngle - bottomRightAngle) > 90) {
		// bottomRightAngle += dir * 180;
		// bottomRightSpeed *= -1;
		// }
		//
		// // if (90 < diffTopRight && diffTopRight <= 270) {
		// // topRightAngle = topRightAngle + 180 + (modTopRight * 360);
		// // topRightSpeed = topRightSpeed * -1;
		// // System.out.println("1 " + topRightAngle);
		// // System.out.println("1 " + topRightSpeed);
		// // } else if (diffTopRight == 180) {
		// // topRightAngle = oldTopRightAngle;
		// // topRightSpeed = topRightSpeed * -1;
		// // System.out.println("2 " + topRightAngle);
		// // System.out.println("2 " + topRightSpeed);
		// // } else if (diffTopRight > 270) {
		// // topRightAngle = topRightAngle + 360 + (modTopRight * 360);
		// // System.out.println("3 " + topRightAngle);
		// // System.out.println("3 " + topRightSpeed);
		// // } else if (-270 <= diffTopRight && diffTopRight < -90) {
		// // topRightAngle = topRightAngle - 180 + (modTopRight * 360);
		// // topRightSpeed = topRightSpeed * -1;
		// // System.out.println("4 " + topRightAngle);
		// // System.out.println("4 " + topRightSpeed);
		// // } else if (diffTopRight == -180) {
		// // topRightAngle = oldTopRightAngle;
		// // topRightSpeed = topRightSpeed * -1;
		// // System.out.println("5 " + topRightAngle);
		// // System.out.println("5 " + topRightSpeed);
		// // } else if (diffTopRight < -270) {
		// // topRightAngle = topRightAngle - 360 + (modTopRight * 360);
		// // System.out.println("6 " + topRightAngle);
		// // System.out.println("6 " + topRightSpeed);
		// // } else if (oldTopRightAngle % 360 > 270
		// // || oldTopRightAngle % 360 < -270) {
		// // topRightAngle = topRightAngle + (modTopRight * 360);
		// // System.out.println("7 " + topRightAngle);
		// // System.out.println("7 " + topRightSpeed);
		// // } else {
		// // topRightAngle = topRightAngle + (modTopRight * 360);
		// // System.out.println("8 " + topRightAngle);
		// // System.out.println("8 " + topRightSpeed);
		// // }
		// //
		// // if (90 < diffTopLeft && diffTopLeft <= 270) {
		// // topLeftAngle = topLeftAngle + 180 + (modTopLeft * 360);
		// // topLeftSpeed = topLeftSpeed * -1;
		// // } else if (diffTopLeft == 180) {
		// // topLeftAngle = oldTopLeftAngle;
		// // topLeftSpeed = topLeftSpeed * -1;
		// // } else if (diffTopLeft > 270) {
		// // topLeftAngle = topLeftAngle + 360 + (modTopLeft * 360);
		// // } else if (-270 <= diffTopLeft && diffTopLeft < -90) {
		// // topLeftAngle = topLeftAngle - 180 + (modTopLeft * 360);
		// // topLeftSpeed = topLeftSpeed * -1;
		// // } else if (diffTopLeft == -180) {
		// // topLeftAngle = oldTopLeftAngle;
		// // topLeftSpeed = topLeftSpeed * -1;
		// // } else if (diffTopLeft < -270) {
		// // topLeftAngle = topLeftAngle - 360 + (modTopLeft * 360);
		// // } else if (oldTopLeftAngle % 360 > 270 || oldTopLeftAngle % 360 <
		// -270) {
		// // topLeftAngle = topLeftAngle + (modTopLeft * 360);
		// // } else {
		// // topLeftAngle = topLeftAngle + (modTopLeft * 360);
		// // }
		// //
		// // if (90 < diffBottomRight && diffBottomRight <= 270) {
		// // bottomRightAngle = bottomRightAngle + 180 + (modBottomRight *
		// 360);
		// // bottomRightSpeed = bottomRightSpeed * -1;
		// // } else if (diffBottomRight == 180) {
		// // bottomRightAngle = oldBottomRightAngle;
		// // bottomRightSpeed = bottomRightSpeed * -1;
		// // } else if (diffBottomRight > 270) {
		// // bottomRightAngle = bottomRightAngle + 360 + (modBottomRight *
		// 360);
		// // } else if (-270 <= diffBottomRight && diffBottomRight < -90) {
		// // bottomRightAngle = bottomRightAngle - 180 + (modBottomRight *
		// 360);
		// // bottomRightSpeed = bottomRightSpeed * -1;
		// // } else if (diffBottomRight == -180) {
		// // bottomRightAngle = oldBottomRightAngle;
		// // bottomRightSpeed = bottomRightSpeed * -1;
		// // } else if (diffBottomRight < -270) {
		// // bottomRightAngle = bottomRightAngle - 360 + (modBottomRight *
		// 360);
		// // } else if (oldBottomRightAngle % 360 > 270
		// // || oldBottomRightAngle % 360 < -270) {
		// // bottomRightAngle = bottomRightAngle + (modBottomRight * 360);
		// // } else {
		// // bottomRightAngle = bottomRightAngle + (modBottomRight * 360);
		// // }
		// //
		// // if (90 < diffBottomLeft && diffBottomLeft <= 270) {
		// // bottomLeftAngle = bottomLeftAngle + 180 + (modBottomLeft * 360);
		// // bottomLeftSpeed = bottomLeftSpeed * -1;
		// // } else if (diffBottomLeft == 180) {
		// // bottomLeftAngle = oldBottomLeftAngle;
		// // bottomLeftSpeed = bottomLeftSpeed * -1;
		// // } else if (diffBottomLeft > 270) {
		// // bottomLeftAngle = bottomLeftAngle + 360 + (modBottomLeft * 360);
		// // } else if (-270 <= diffBottomLeft && diffBottomLeft < -90) {
		// // bottomLeftAngle = bottomLeftAngle - 180 + (modBottomLeft * 360);
		// // bottomLeftSpeed = bottomLeftSpeed * -1;
		// // } else if (diffBottomLeft == -180) {
		// // bottomLeftAngle = oldBottomLeftAngle;
		// // bottomLeftSpeed = bottomLeftSpeed * -1;
		// // } else if (diffBottomLeft < -270) {
		// // bottomLeftAngle = bottomLeftAngle - 360 + (modBottomLeft * 360);
		// // } else if (oldBottomLeftAngle % 360 > 270
		// // || oldBottomLeftAngle % 360 < -270) {
		// // bottomLeftAngle = bottomLeftAngle + (modBottomLeft * 360);
		// // } else {
		// // bottomLeftAngle = bottomLeftAngle + (modBottomLeft * 360);
		// // }
		//
		// // rotIn is either the heading for fieldcentric code or omega in
		// // robot
		// // centric code, but both are measured as clockwise from North, so
		// // just
		// // keep that in mind as you may be reading this absurdly long
		// // comment
		// // about rotIn and absurdly long comments. Rather meta, no?
		//
		// // Normalize VIn (strafe and forward)
		// // not necessary with the XBox controllers but just in case...
		// /*
		// * if (Math.pow(forward, 2) + Math.pow(strafe, 2) > 1) { double f2 =
		// * Math.pow(forward, 2); double s2 = Math.pow(strafe, 2);
		// *
		// * forward = -Math.sqrt(f2 / (f2 + s2)); strafe = Math.sqrt(s2 / (f2 +
		// * s2)); } else { forward *= -1; }
		// *
		// * if (isFieldcentric == false) { rotIn *= 2 * Math.PI; } else {
		// heading
		// * = rotIn;
		// *
		// * // defining the velocity vector in polar to convert to //
		// robocentric
		// * double magnitudeV = Math.sqrt(Math.pow(strafe, 2) +
		// Math.pow(forward,
		// * 2)); double phiV = 90 - Math.atan2(strafe, forward) * RAD_TO_DEG;
		// *
		// * // do the conversion so the robocentic code can handle the rest
		// * strafe = magnitudeV * Math.sin(phiV - lastHeading); forward =
		// * magnitudeV * Math.cos(phiV - lastHeading); rotIn = (heading -
		// * lastHeading) / .02; // the code should be updated to the robot
		// every
		// * 20ms, so // hardcoded
		// *
		// * lastHeading = heading; }
		// *
		// * // Set intermediates double Rx = rotIn * length / 2; double Ry =
		// * rotIn * width / 2;
		// *
		// * double topX = strafe + Rx; double rightY = forward - Ry; double
		// * bottomX = strafe - Rx; double leftY = forward + Ry;
		// *
		// * // Set wheel speeds double tx2 = Math.pow(topX, 2); double bx2 =
		// * Math.pow(bottomX, 2); double ry2 = Math.pow(rightY, 2); double ly2
		// =
		// * Math.pow(leftY, 2);
		// *
		// * topRightSpeed = Math.sqrt(tx2 + ry2); topLeftSpeed = Math.sqrt(tx2
		// +
		// * ly2); bottomLeftSpeed = Math.sqrt(bx2 + ly2); bottomRightSpeed =
		// * Math.sqrt(bx2 + ry2);
		// */
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