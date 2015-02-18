package org.usfirst.frc.team930.robot.subsystems;

import org.usfirst.frc.team930.robot.OI;
import org.usfirst.frc.team930.robot.RobotMap;
import org.usfirst.frc.team930.robot.armPID.ArmOutput;
import org.usfirst.frc.team930.robot.armPID.ArmSource;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Arm extends Subsystem {

	CANTalon talon1;
	CANTalon talon2; 
	
	ArmOutput armOutput;
	ArmSource armSource;
	public PIDController armPID;
	
	final double SPEED_P = .005;
	final double SPEED_I = .0007;
	final double POS_P = 0;
	final double POS_I = 0;
	
	public Arm() {
		talon1 = new CANTalon(RobotMap.RIGHT_ARM);
		talon2 = new CANTalon(RobotMap.LEFT_ARM);
		
		armOutput = new ArmOutput(talon1, talon2);
		armSource = new ArmSource(OI.getInstance());
		armPID = new PIDController(SPEED_P, SPEED_I, 0, armSource, armOutput);
	}

	public double getArmAngle() {
		return armSource.getArmAngle();
	}
	
	public void setAngle(double set) {
		armPID.setSetpoint(set);
	}
		
	public void initDefaultCommand() {

	}
}
