package org.usfirst.frc.team930.robot;

import java.util.Timer;
import java.util.TimerTask;

import edu.wpi.first.wpilibj.ADXL345_SPI;
import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.interfaces.Accelerometer;

import org.usfirst.frc.team930.robot.commands.CloseLeftClaw;
import org.usfirst.frc.team930.robot.commands.CloseRightClaw;
import org.usfirst.frc.team930.robot.commands.ManualArmHeight;
import org.usfirst.frc.team930.robot.commands.OpenLeftClaw;
import org.usfirst.frc.team930.robot.commands.OpenRightClaw;
import org.usfirst.frc.team930.robot.commands.SetHeight;
import org.usfirst.frc.team930.robot.BoxCar;

import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OI {
	public static long time = System.currentTimeMillis();
	public static long time2 = System.currentTimeMillis();
	public static enum Axis {
		X, Y, Z;
	}
	
	public static int armPreset_0 = 41; 
	public static int armPreset_1 = 19;
	public static int armPreset_2 = -10;

	private static final double DEADBAND = .1;
	public final static int DRIVER_PORT = 0;
	public final static int CODRIVER_PORT = 1;
	
	ADXL345_SPI armAccel= new ADXL345_SPI(SPI.Port.kOnboardCS0, Accelerometer.Range.k2G);
	public ADXL345_SPI bindAccel= new ADXL345_SPI(SPI.Port.kOnboardCS1, Accelerometer.Range.k2G);
	BuiltInAccelerometer roboAccel =  new BuiltInAccelerometer(Accelerometer.Range.k2G);

	Joystick driverXbox = new Joystick(DRIVER_PORT);
	Joystick coDriverXbox = new Joystick(CODRIVER_PORT);

	JoystickButton aButton = new JoystickButton(driverXbox, 1);
	JoystickButton xButton = new JoystickButton(driverXbox, 3);
	JoystickButton bButton = new JoystickButton(driverXbox, 2);
	JoystickButton yButton = new JoystickButton(driverXbox, 4);
	
	JoystickButton leftbumperButtonCodriver = new JoystickButton(coDriverXbox, 5);
	JoystickButton rightbumperButtonCodriver = new JoystickButton(coDriverXbox, 6);
	JoystickButton aButtonCodriver = new JoystickButton(coDriverXbox, 1);
	JoystickButton xButtonCodriver = new JoystickButton(coDriverXbox, 3);
	JoystickButton yButtonCodriver = new JoystickButton(coDriverXbox, 4);
	JoystickButton bButtonCodriver = new JoystickButton(coDriverXbox, 2);


	
	int boxCarLength = 100;
	
	BoxCar boxCarArmX = new BoxCar(boxCarLength);
	BoxCar boxCarArmY = new BoxCar(boxCarLength);
	BoxCar boxCarArmZ = new BoxCar(boxCarLength);
	BoxCar boxCarRobotX = new BoxCar(boxCarLength);
	BoxCar boxCarRobotY = new BoxCar(boxCarLength);
	BoxCar boxCarRobotZ = new BoxCar(boxCarLength);

	double roboX = 0;
	double roboY = 0;
	double roboZ = 0;
	double armX = 0;
	double armY = 0;
	double armZ = 0;
	Timer timer; 
	TimerTask task = new TimerTask(){
		
		@Override
		public void run() {
			synchronized(roboAccel) {
				roboX = boxCarRobotX.calculate(roboAccel.getX());
				roboY = boxCarRobotY.calculate(roboAccel.getY());
				roboZ = boxCarRobotZ.calculate(roboAccel.getZ());
			}
			armX = boxCarArmX.calculate(armAccel.getX());
			armY = boxCarArmY.calculate(armAccel.getY());
			armZ = boxCarArmZ.calculate(armAccel.getZ());
		}
		
	};
	
	public static OI getInstance() {
		return Holder.instance;
	}

	private OI() {
		//Claw Button OPEN/CLOSE Mapping 
		
		leftbumperButtonCodriver.whenPressed(new CloseLeftClaw());
		rightbumperButtonCodriver.whenPressed(new OpenRightClaw());
		
		//aButtonCodriver.whenPressed(new SetHeight(0));
		//xButtonCodriver.whenPressed(new SetHeight(30));
		
		//		if (clawAxis > 0.75){
		//			new CloseLeftClaw();
		//		}
		//		if (clawAxis < -0.75){
		//			new CloseRightClaw();
		//		}
				aButtonCodriver.whenPressed(new SetHeight(armPreset_0));
				xButtonCodriver.whenPressed(new SetHeight(armPreset_1));
				yButtonCodriver.whenPressed(new SetHeight(armPreset_2));
				bButtonCodriver.whenPressed(new ManualArmHeight());

	}

	public void initAccel() {
		armAccel.free();
		bindAccel.free();
		
		armAccel = new ADXL345_SPI(SPI.Port.kOnboardCS0,
				Accelerometer.Range.k2G);
		bindAccel = new ADXL345_SPI(SPI.Port.kOnboardCS1,
				Accelerometer.Range.k2G);
		timer = new Timer();
		timer.schedule(task, 0, 1);
	}

	public static class Holder {
		public static final OI instance = new OI();
	}

	// Declarations and stuff

	public double getArmHeight() {
		return coDriverXbox.getRawAxis(2);
	}

	// -Accelerations
	public double getCentripAccel(Axis axis, double radius) {
		switch (axis) {
		case X:
			return (getArmAccel(Axis.X) - getRobotAccel(Axis.X)) * radius
					/ RobotMap.ARM_ACCEL_R;
		case Y:
			return this.getCentripAccel(Axis.X, radius)
					* RobotMap.ARM_ACCEL_TAN;
		default:
			return 930;
		}
	}

	public double getArmAccel(Axis axis) {
		switch (axis) {
		case X:
			return armX;
		case Y:
			return armY;
		case Z:
			return armZ;
		default:
			return 930;
		}
	}

	/*
	 * public double getOtherAccelX() { return otherAccel.getX(); } public
	 * double getOtherAccelY() { return otherAccel.getY(); } public double
	 * getOtherAccelZ() { return otherAccel.getZ(); }
	 */

	public double getRobotAccel(Axis axis) {
		switch (axis) {
		case X:
			return roboX;
		case Y:
			return roboY;
		case Z:
			return roboZ;
		default:
			return 930;
		}
	}
	
	public double getArmAccelXRaw() {
		return armAccel.getX();
	}

	public double getArmAccelYRaw() {
		return armAccel.getY();
	}
	
	public double getArmAccelZRaw() {
		return armAccel.getZ();
	}

	public double getRobotAccelX() {
		return roboX;
	}
	
	public double getRobotAccelY() {
		return roboY;
	}

	public double getRobotAccelZ() {
		return roboZ;
	}
	
	public double getRobotAccelXRaw(){
		double readval;
		synchronized(roboAccel){
			readval = roboAccel.getX();
		}

		return readval;
	}
	
	public double getRobotAccelYRaw() {
		double readval;
		synchronized(roboAccel){
			readval = roboAccel.getY();
		}

		return readval;
	}

	public double getRobotAccelZRaw() {
		double readval;
		synchronized(roboAccel){
			readval = roboAccel.getZ();
		}

		return readval;
	}

	// -Joysticks
	public double getCoDriverTriggerRight(){
		return coDriverXbox.getRawAxis(3);
	}
	
	public double getCoDriverTriggerLeft(){
		return coDriverXbox.getRawAxis(2);
	}
	
	public double getDriverTriggerRight(){
		return driverXbox.getRawAxis(3);
	}
	
	public double getDriverTriggerLeft(){
		return driverXbox.getRawAxis(2);
	}
	
	public double getStrafe() {
		double axis = driverXbox.getRawAxis(0);
		if (Math.abs(axis) < DEADBAND) {
			return 0;
		}
		axis = axis * axis * Math.signum(axis);
		return axis;
	}

	public double getForward() {
		double axis = driverXbox.getRawAxis(1);
		if (Math.abs(axis) < DEADBAND) {
			return 0;
		}
		axis = axis * axis * Math.signum(axis);
		return -1 * axis;
	}

	public double getRotX() {
		double axis = driverXbox.getRawAxis(4);
		if (Math.abs(axis) < DEADBAND) {
			return 0;
		}
		return axis;
	}

	public double getRotY() {
		double axis = driverXbox.getRawAxis(5);
		if (Math.abs(axis) < DEADBAND) {
			return 0;
		}
		return axis;
	}

	public double getArmCoDriver() {
		double axis = coDriverXbox.getRawAxis(1);
		if (Math.abs(axis) < DEADBAND) {
			return 0;
		}		
		System.out.println("inside oi");
		return axis;

	}
}
