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
	
	public void armH0(){
		talon1.changeControlMode();
		talon2.changeControlMode();
		
		talon1.enableControl();
		talon2.enableControl();
		
		talon1.setPID(0, 0, 0);
		talon2.setPID(0, 0, 0);
		
		talon1.setPosition(1);
		talon2.setPosition(1);
	}
	
	public void armH1(){
		talon1.changeControlMode();
		talon2.changeControlMode();
		
		talon1.enableControl();
		talon2.enableControl();
		
		talon1.setPID(0, 0, 0);
		talon2.setPID(0, 0, 0);
		
		talon1.setPosition(1);
		talon2.setPosition(1);
		
	}
	
	public void armUp(){
		talon1.changeControlMode();
		talon2.changeControlMode();
		
		talon1.enableControl();
		talon2.enableControl();
		
		talon1.setPID(0, 0, 0);
		talon2.setPID(0, 0, 0);
		
		talon1.set(1);
		talon2.set(1);
		
	}
	
	public void armDown(){
		talon1.changeControlMode();
		talon2.changeControlMode();
		
		talon1.enableControl();
		talon2.enableControl();
		
		talon1.setPID(0, 0, 0);
		talon2.setPID(0, 0, 0);
		
		talon1.set(1);
		talon2.set(1);
		
	}

	public void initDefaultCommand() {

		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
}
