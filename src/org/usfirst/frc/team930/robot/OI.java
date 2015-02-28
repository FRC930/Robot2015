package org.usfirst.frc.team930.robot;

import edu.wpi.first.wpilibj.ADXL345_SPI;
import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.interfaces.Accelerometer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team930.robot.commands.CloseLeftClaw;
import org.usfirst.frc.team930.robot.commands.CloseRightClaw;
import org.usfirst.frc.team930.robot.commands.OpenLeftClaw;
import org.usfirst.frc.team930.robot.commands.OpenRightClaw;

import org.usfirst.frc.team930.robot.BoxCar;

import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OI {
	private static final double DEADBAND = .1;
	public final static int DRIVER_PORT = 0;
	public final static int CODRIVER_PORT = 1;

	ADXL345_SPI armaccel= new ADXL345_SPI(SPI.Port.kOnboardCS0, Accelerometer.Range.k2G);
	public ADXL345_SPI otherAccel= new ADXL345_SPI(SPI.Port.kOnboardCS1, Accelerometer.Range.k2G);
	BuiltInAccelerometer roboaccel =  new BuiltInAccelerometer(Accelerometer.Range.k2G);
	Joystick driverXbox = new Joystick(DRIVER_PORT);
	Joystick coDriverXbox = new Joystick(CODRIVER_PORT);


	JoystickButton aButton = new JoystickButton(driverXbox, 1);
	JoystickButton xButton = new JoystickButton(driverXbox, 3);
	JoystickButton bButton = new JoystickButton(driverXbox, 2);
	JoystickButton yButton = new JoystickButton(driverXbox, 4);
	
	BoxCar boxCarArmX = new BoxCar(10);
	BoxCar boxCarArmY = new BoxCar(10);
	BoxCar boxCarArmZ = new BoxCar(10);
	BoxCar boxCarRobotX = new BoxCar(10);
	BoxCar boxCarRobotY = new BoxCar(10);
	BoxCar boxCarRobotZ = new BoxCar(10);
	

	public static OI getInstance() {
		return Holder.instance;
	}

	private OI() {
		aButton.whenPressed(new CloseLeftClaw());
		xButton.whenPressed(new OpenLeftClaw());
		bButton.whenPressed(new CloseRightClaw());
		yButton.whenPressed(new OpenRightClaw());

	}
	
	public void initAccel(){
		armaccel.free();
		otherAccel.free();
		armaccel= new ADXL345_SPI(SPI.Port.kOnboardCS0, Accelerometer.Range.k2G);
		otherAccel= new ADXL345_SPI(SPI.Port.kOnboardCS1, Accelerometer.Range.k2G);
	}

	public static class Holder {
		public static final OI instance = new OI();
	}

	// Declarations and stuff

	public double getArmHeight() {
		return coDriverXbox.getRawAxis(2);
	}
	
	
	// -Accelerations
	public double getArmAccelX() {
		return boxCarArmX.calculate(armaccel.getX());
	}

	public double getArmAccelY() {
		return boxCarArmY.calculate(armaccel.getY());
	}
	
	public double getArmAccelZ() {
		return boxCarArmZ.calculate(armaccel.getZ());
	}
	
	public double getArmAccelXRaw() {
		return armaccel.getX();
	}

	public double getArmAccelYRaw() {
		return armaccel.getY();
	}
	
	public double getArmAccelZRaw() {
		return armaccel.getZ();
	}
	
	public double getOtherAccelX() {
		return otherAccel.getX();
	}

	public double getOtherAccelY() {
		return otherAccel.getY();
	}
	
	public double getOtherAccelZ() {
		return otherAccel.getZ();
	}

	public double getRobotAccelX() {
		return boxCarRobotX.calculate(getRobotAccelXRaw());
	}
	
	public double getRobotAccelY() {
		return boxCarRobotY.calculate(getRobotAccelYRaw());
	}

	public double getRobotAccelZ() {
		return boxCarRobotZ.calculate(getRobotAccelZRaw());
	}
	
	public double getRobotAccelXRaw(){
		double name = 0;
		synchronized(roboaccel){
			name = roboaccel.getX();
		}
		return name;
	}
	
	public double getRobotAccelYRaw() {
		double name = 0;
		synchronized(roboaccel){
			name = roboaccel.getY();
		}
		return name;
	}

	public double getRobotAccelZRaw() {
		double name = 0;
		synchronized(roboaccel){
			name = roboaccel.getZ();
		}
		return name;
	}

	
	// -Joysticks
	public double getStrafe() {
		double axis = driverXbox.getRawAxis(0);
		if (Math.abs(axis) < DEADBAND) {
			return 0;
		}
		return axis;
	}

	public double getForward() {
		double axis = driverXbox.getRawAxis(1);
		if (Math.abs(axis) < DEADBAND) {
			return 0;
		}
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
		return -1 * axis;
	}
}
