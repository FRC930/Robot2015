package org.usfirst.frc.team930.robot.subsystems;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Arm extends Subsystem {
	CANTalon talon1;
	CANTalon talon2;
	final double LENGTH_OF_ARM = 1;
	double a = 0; // height of pivot point
	double b = 0; // lateral distance from pivot to motor

	public Arm() {
		talon1 = new CANTalon(9);
		talon2 = new CANTalon(10);

	}

	public double setHeight(double height) {

		double val = 2 * LENGTH_OF_ARM * (height - a);
		double output = Math
				.sqrt((Math.sqrt(Math.pow(b, 4) + Math.pow(val, 2)) - Math.pow(
						b, 2)) / 2);
		return output; //gives height of the motor for the arm
	}

	public void initDefaultCommand() {

		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
}
