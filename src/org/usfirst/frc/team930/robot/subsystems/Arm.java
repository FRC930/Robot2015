package org.usfirst.frc.team930.robot.subsystems;

import org.usfirst.frc.team930.robot.BoxCar;
import org.usfirst.frc.team930.robot.OI;
import org.usfirst.frc.team930.robot.OI.Axis;
import org.usfirst.frc.team930.robot.RobotMap;
import org.usfirst.frc.team930.robot.armPID.ArmOutput;
import org.usfirst.frc.team930.robot.armPID.AngleSource;
import org.usfirst.frc.team930.robot.commands.ManualArmHeight;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.ControlMode;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Arm extends Subsystem {

	OI oi;

	final static double RAD_TO_DEG = (180.0 / Math.PI);

	CANTalon talon1;
	CANTalon talon2;

	ArmOutput armOutput;
	AngleSource armSource;
	public PIDController armPID;
	BoxCar armAngleBoxCar = new BoxCar(10);

	final static double SPEED_P = .005;
	final static double SPEED_I = .0007;
	final static double POS_P = .025;
	final static double POS_I = .000150;
	final static double POS_D = .000030;


	double mag = 1500;

	public Arm() {
		// System.out.println("im a grill btw");
		talon1 = new CANTalon(RobotMap.RIGHT_ARM);
		talon2 = new CANTalon(RobotMap.LEFT_ARM);
		talon2.changeControlMode(ControlMode.Follower);

		armOutput = new ArmOutput(talon1, talon2);
		armSource = new AngleSource();

	}

	public void startPID() {
		armPID = new PIDController(POS_P, POS_I, POS_D, new AngleSource(), new ArmOutput(talon1, talon2), .2);
		armPID.reset();
		armPID.setAbsoluteTolerance(1);
		armPID.setSetpoint(getArmAngle());

		talon2.set(RobotMap.RIGHT_ARM);
	}

	public double getArmAngle() {
		if(oi == null) oi = OI.getInstance();
		double zarm = oi.getArmAccel(Axis.Z);
		double yarm = oi.getArmAccel(Axis.Y);
		double zrobot = oi.getRobotAccel(Axis.Z);
		double yrobot = oi.getRobotAccel(Axis.Y);
		
		SmartDashboard.putNumber("ZZZZZZZZZZZ ", zarm);
		SmartDashboard.putNumber("YYYYYYYYYYY ", yarm);

		return  /*armAngleBoxCar.calculate(*/Math.atan2(((zrobot * yarm) - (yrobot * zarm)), ((zrobot * zarm) + (yrobot * yarm))) * RAD_TO_DEG/*)*/;
	}

	public void setAngle(double set) {
		armPID.setSetpoint(set);
	}

	public void initDefaultCommand() {
		setDefaultCommand(new ManualArmHeight());
	}
}
