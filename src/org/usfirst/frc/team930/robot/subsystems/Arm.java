package org.usfirst.frc.team930.robot.subsystems;

import org.usfirst.frc.team930.robot.OI;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.ControlMode;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Arm extends Subsystem {

	CANTalon talon1;
	CANTalon talon2; 
	double lastAngle;
	
	public Arm() {
		OI oi = OI.getInstance();
		talon1 = new CANTalon(5);
		talon2 = new CANTalon(6);
		lastAngle = Math.atan2(oi.getArmAccelX(), oi.getArmAccelY());
	}

	public double getArmAngle() {
		OI oi = OI.getInstance();
		double x = oi.getArmAccelX() + oi.getRobotAccelY() * Math.sin(lastAngle);
		double y = oi.getArmAccelX() - oi.getRobotAccelY() * Math.cos(lastAngle);
		return Math.atan2(x, y);
	}

	public void moveArm(double speed) {

		talon1.changeControlMode(ControlMode.Speed);
		talon2.changeControlMode(ControlMode.Speed);

		talon1.enableControl();
		talon2.enableControl();

		talon1.setPID(0, 0, 0);
		talon2.setPID(0, 0, 0);

		talon1.set(speed);
		talon2.set(speed);

	}

	public void armUp() {
		talon1.changeControlMode(ControlMode.Speed);
		talon2.changeControlMode(ControlMode.Speed);

		talon1.enableControl();
		talon2.enableControl();

		talon1.setPID(0, 0, 0);
		talon2.setPID(0, 0, 0);

		talon1.set(1);
		talon2.set(1);

	}

	public void armDown() {
		talon1.changeControlMode(ControlMode.Speed);
		talon2.changeControlMode(ControlMode.Speed);

		talon1.enableControl();
		talon2.enableControl();

		talon1.setPID(0, 0, 0);
		talon2.setPID(0, 0, 0);

		talon1.set(1);
		talon2.set(1);

	}
	
	public void initDefaultCommand() {

	}
}
