package org.usfirst.frc.team930.robot.subsystems;
import org.usfirst.frc.team930.robot.OI;
import org.usfirst.frc.team930.robot.Robot;

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
	private double width, length, R; // length and width of the robot
	

	// Calculation Components
	final double RAD_TO_DEG = 180 / Math.PI;
	private double topRightSpeed, topLeftSpeed, bottomLeftSpeed,
			bottomRightSpeed;
	private double topRightAngle, topLeftAngle, bottomLeftAngle,
			bottomRightAngle, oldTopRightAngle, oldTopLeftAngle, oldBottomLeftAngle,
			oldBottomRightAngle;
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
		
		R = Math.sqrt(Math.pow(length,2) + Math.pow(width,2));
	}

	public SwerveDrive(double length, double width, boolean fieldcentric) {
		this(length, width);
		this.isFieldcentric = fieldcentric;
		
		R = Math.sqrt(Math.pow(length,2) + Math.pow(width,2));
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
			
			double A = STR - RCW*(length/R);
			double B = STR + RCW*(length/R);
			double C = FWD - RCW*(width/R);
			double D = FWD + RCW*(width/R); 
			
			double A2 = Math.pow(A,2);
			double B2= Math.pow(B,2);
			double C2 = Math.pow(C,2);
			double D2= Math.pow(D,2);

			oldTopRightAngle = topRightAngle;
			oldTopLeftAngle = topLeftAngle;
			oldBottomRightAngle = bottomRightAngle;
			oldBottomLeftAngle = bottomLeftAngle;
			
			topRightSpeed = Math.sqrt(B2 + C2);
			topLeftSpeed = Math.sqrt(B2 + D2);
			bottomLeftSpeed = Math.sqrt(A2 + D2);
			bottomRightSpeed = Math.sqrt(A2 + C2);
			
			topRightAngle = Math.atan2(B,C) * RAD_TO_DEG;
			topLeftAngle = Math.atan2(B,D) * RAD_TO_DEG;
			bottomLeftAngle = Math.atan2(A,D) * RAD_TO_DEG;
			bottomRightAngle = Math.atan2(A,C) * RAD_TO_DEG;
			
			int modTopRight = (int)((oldTopRightAngle - topRightAngle)/360);
			int modTopLeft = (int)((oldTopLeftAngle - topLeftAngle)/360);
			int modBottomRight = (int)((oldBottomRightAngle - bottomRightAngle)/360);
			int modBottomLeft = (int)((oldBottomLeftAngle - bottomLeftAngle)/360);
			
			double diffTopRight = (oldTopRightAngle-topRightAngle)%360;
			double diffTopLeft = (oldTopLeftAngle-topLeftAngle)%360;
			double diffBottomRight = (oldBottomRightAngle-bottomRightAngle)%360;
			double diffBottomLeft = (oldBottomLeftAngle-bottomLeftAngle)%360;
			
			System.out.println(topRightAngle);
			
			if(90<diffTopRight&&diffTopRight<180){
				topRightAngle = topRightAngle + 180 + (modTopRight * 360);
				topRightSpeed = topRightSpeed * -1;
			}else if (diffTopRight == 180){
				topRightAngle = oldTopRightAngle + (modTopRight * 360);;
				topRightSpeed = topRightSpeed * -1;
			}else if (diffTopRight  > 180){
				topRightAngle = topRightAngle + 360 + (modTopRight * 360);;
			}else if (-180<diffTopRight&&diffTopRight<-90){
				topRightAngle = topRightAngle - 180 + (modTopRight * 360);;
				topRightSpeed = topRightSpeed * -1;
			}else if (diffTopRight==-180){
				topRightAngle = oldTopRightAngle + (modTopRight * 360);;
				topRightSpeed = topRightSpeed*-1;
			}else if (diffTopRight < -180){
				topRightAngle = topRightAngle - 360 + (modTopRight * 360);
			}else if (oldTopRightAngle > 270 || oldTopRightAngle < -270){
				topRightAngle = topRightAngle + (modTopRight*360);
			}
			
			if(90<diffTopLeft&&diffTopLeft<180){
				topLeftAngle = topLeftAngle + 180 + (modTopLeft * 360);
				topLeftSpeed = topLeftSpeed * -1;
			}else if (diffTopLeft == 180){
				topLeftAngle = oldTopLeftAngle + (modTopLeft * 360);
				topLeftSpeed = topLeftSpeed * -1;
			}else if (diffTopLeft  > 180){
				topLeftAngle = topLeftAngle + 360 + (modTopLeft * 360);
			}else if (-180<diffTopLeft&&diffTopLeft<-90){
				topLeftAngle = topLeftAngle -180 + (modTopLeft * 360);
				topLeftSpeed = topLeftSpeed * -1;
			}else if (diffTopLeft==-180){
				topLeftAngle = oldTopLeftAngle + (modTopLeft * 360);
				topLeftSpeed = topLeftSpeed*-1;
			}else if (diffTopLeft < -180){
				topLeftAngle = topLeftAngle - 360 + (modTopLeft * 360);
			}else if (oldTopLeftAngle > 270 || oldTopLeftAngle < -270){
				topLeftAngle = topLeftAngle + (modTopLeft*360);
			}
			
			if(90<diffBottomRight&&diffBottomRight<180){
				bottomRightAngle = bottomRightAngle + 180 + (modBottomRight * 360);
				bottomRightSpeed = bottomRightSpeed * -1;
			}else if (diffBottomRight == 180){
				bottomRightAngle = oldBottomRightAngle + (modBottomRight * 360);
				bottomRightSpeed = bottomRightSpeed * -1;
			}else if (diffBottomRight  > 180){
				bottomRightAngle = bottomRightAngle + 360 + (modBottomRight * 360);
			}else if (-180<diffBottomRight&&diffBottomRight<-90){
				bottomRightAngle = bottomRightAngle - 180 + (modBottomRight * 360);
				bottomRightSpeed = bottomRightSpeed * -1;
			}else if (diffBottomRight==-180){
				bottomRightAngle = oldBottomRightAngle + (modBottomRight * 360);
				bottomRightSpeed = bottomRightSpeed*-1;
			}else if (diffBottomRight < -180){
				bottomRightAngle = bottomRightAngle - 360 + (modBottomRight * 360);
			}else if (oldBottomRightAngle > 270 || oldBottomRightAngle < -270){
				bottomRightAngle = bottomRightAngle + (modBottomRight*360);
			}
			
			if(90<diffBottomLeft&&diffBottomLeft<180){
				bottomLeftAngle = bottomLeftAngle + 180 + (modBottomLeft * 360);
				bottomLeftSpeed = bottomLeftSpeed * -1;
			}else if (diffBottomLeft == 180){
				bottomLeftAngle = oldBottomLeftAngle + (modBottomLeft * 360);
				bottomLeftSpeed = bottomLeftSpeed * -1;
			}else if (diffBottomLeft  > 180){
				bottomLeftAngle = bottomLeftAngle + 360 + (modBottomLeft * 360);
			}else if (-180<diffBottomLeft&&diffBottomLeft<-90){
				bottomLeftAngle = bottomLeftAngle - 180 + (modBottomLeft * 360);
				bottomLeftSpeed = bottomLeftSpeed * -1;
			}else if (diffBottomLeft==-180){
				bottomLeftAngle = oldBottomLeftAngle + (modBottomLeft * 360);
				bottomLeftSpeed = bottomLeftSpeed*-1;
			}else if (diffBottomLeft < -180){
				bottomLeftAngle = bottomLeftAngle - 360 + (modBottomLeft * 360);
			}else if (oldBottomLeftAngle > 270 || oldBottomLeftAngle < -270){
				bottomLeftAngle = bottomLeftAngle + (modBottomLeft*360);
			}

			// rotIn is either the heading for fieldcentric code or omega in
			// robot
			// centric code, but both are measured as clockwise from North, so
			// just
			// keep that in mind as you may be reading this absurdly long
			// comment
			// about rotIn and absurdly long comments. Rather meta, no?

			// Normalize VIn (strafe and forward)
			// not necessary with the XBox controllers but just in case...
			/*if (Math.pow(forward, 2) + Math.pow(strafe, 2) > 1) {
				double f2 = Math.pow(forward, 2);
				double s2 = Math.pow(strafe, 2);

				forward = -Math.sqrt(f2 / (f2 + s2));
				strafe = Math.sqrt(s2 / (f2 + s2));
			}   else {
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

			*/
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
			/*

			// Set wheel angles
			topRightAngle = Math.atan2(topX, rightY) * RAD_TO_DEG;
			topLeftAngle = Math.atan2(topX, leftY) * RAD_TO_DEG;
			bottomLeftAngle = Math.atan2(bottomX, leftY) * RAD_TO_DEG;
			bottomRightAngle = Math.atan2(bottomX, rightY) * RAD_TO_DEG;
*/
			// output
			
			 /*System.out.println("top right speed: " + topRightSpeed + "\n" + "top left speed: " + topLeftSpeed + "\n" +
			 "bottom left speed: " + bottomLeftSpeed + "\n" + "bottom right speed: " + bottomRightSpeed + "\n");
			 System.out.println("top right angle: " + topRightAngle + "\n" + "top left angle: " + topLeftAngle + "\n" +
			 "bottom left angle: " + bottomLeftAngle + "\n" + "bottom right angle: " + bottomRightAngle + "\n");*/
			
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