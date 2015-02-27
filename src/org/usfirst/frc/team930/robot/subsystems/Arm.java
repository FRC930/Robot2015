package org.usfirst.frc.team930.robot.subsystems;

import org.usfirst.frc.team930.robot.OI;
import org.usfirst.frc.team930.robot.RobotMap;
import org.usfirst.frc.team930.robot.armPID.ArmOutput;
import org.usfirst.frc.team930.robot.armPID.ArmSource;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Arm extends Subsystem {
	
	OI oi = OI.getInstance();
	
	final static double RAD_TO_DEG = (180.0 / Math.PI);

	CANTalon talon1;
	CANTalon talon2;

	ArmOutput armOutput;
	ArmSource armSource;
	public PIDController armPID;

	final static double SPEED_P = .005;
	final static double SPEED_I = .0007;
	final static double POS_P = .015;
	final static double POS_I = 0.0002;

	double mag = 1500; 

	public Arm() {
//		System.out.println("im a grill btw");
		talon1 = new CANTalon(RobotMap.RIGHT_ARM);
		talon2 = new CANTalon(RobotMap.LEFT_ARM);

		armOutput = new ArmOutput(talon1, talon2);
		armSource = new ArmSource();
		
	}
	
	public void startPID(){
		armPID = new PIDController(POS_P, POS_I, 0, new ArmSource(), new ArmOutput(talon1, talon2), .001);
		armPID.reset();
	}
	
	public double getArmAngle() {
//		System.out.println("2gregz");
		double zarm = oi.getArmAccelZ();
		double yarm = oi.getArmAccelY();
		double zrobot = oi.getRobotAccelZ();
		double yrobot = oi.getRobotAccelY();

		return Math.atan2(((zrobot * yarm) - (yrobot * zarm)), ((zrobot * zarm) + (yrobot * yarm)))*RAD_TO_DEG;
	}

	public void setAngle(double set) {
		armPID.setSetpoint(set);
	}

	public void initDefaultCommand() {

	}
}