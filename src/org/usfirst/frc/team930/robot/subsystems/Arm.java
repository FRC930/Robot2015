package org.usfirst.frc.team930.robot.subsystems;

import org.usfirst.frc.team930.robot.OI;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.ControlMode;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Arm extends Subsystem {

	CANTalon talon1;
	CANTalon talon2; 
	
	ArmOutput armOutput;
	ArmSource armSource;
	
	final double SPEED_P = .005;
	final double SPEED_I = .001;
	final double POS_P = 0;
	final double POS_I = 0;
	
	public Arm() {
		talon1 = new CANTalon(5);
		talon2 = new CANTalon(6);
		
		armOutput = new ArmOutput(talon1, talon2);
		armSource = new ArmSource(OI.getInstance());
	}

	public double getArmAngle() {
		return armSource.getArmAngle();
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
