//HMS 930

package org.usfirst.frc.team930.robot;

import org.usfirst.frc.team930.robot.commands.CloseLeftClaw;
import org.usfirst.frc.team930.robot.commands.CloseRightClaw;
import org.usfirst.frc.team930.robot.commands.Drive;
import org.usfirst.frc.team930.robot.commands.OpenLeftClaw;
import org.usfirst.frc.team930.robot.commands.OpenRightClaw;
import org.usfirst.frc.team930.robot.subsystems.Arm;
import org.usfirst.frc.team930.robot.subsystems.Claw;
import org.usfirst.frc.team930.robot.subsystems.Drivetrain;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {
	
	public static final double DRIVETRAIN_WIDTH = 21.25;
	public static final double DRIVETRAIN_LENGTH = 33.00;
	
	public static final Claw leftClaw = new Claw(Claw.leftRelay, Claw.leftOpen, Claw.leftClosed, 1);
	public static final Claw rightClaw = new Claw(Claw.rightRelay, Claw.rightOpen, Claw.rightClosed, 2);
	public static final Drivetrain drivetrain = new Drivetrain(DRIVETRAIN_WIDTH, DRIVETRAIN_LENGTH);
	public static final Arm arm = new Arm();
	public static OI oi;

	Command closeLeftClaw;
	Command closeRightClaw;
	Command openLeftClaw;
	Command openRightClaw;
	
	Command setHeight;
	
	Command drive;
	
	public void robotInit() {
		oi = OI.getInstance();
		oi.initAccel();
		//leftClaw.reverseDirection();
		closeLeftClaw = new CloseLeftClaw();
		closeRightClaw = new CloseRightClaw();
		openLeftClaw = new OpenLeftClaw();
		openRightClaw = new OpenRightClaw();
		//drive = new Drive();
	}

	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	public void autonomousInit() {
		// if (autonomousCommand != null) autonomousCommand.start();
	}

	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	public void teleopInit() {
		arm.startPID();
		arm.armPID.enable();
	}

	public void disabledInit() {

	}

	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		
		arm.setAngle(-5);
		
		SmartDashboard.putNumber("arm angle ", arm.getArmAngle());
		SmartDashboard.putNumber("arm accel x " , oi.getArmAccelX());
		SmartDashboard.putNumber("arm accel y " , oi.getArmAccelY());
		SmartDashboard.putNumber("arm accel z " , oi.getArmAccelZ());
		SmartDashboard.putNumber("robot accel x " , oi.getRobotAccelX());
		SmartDashboard.putNumber("robot accel y " , oi.getRobotAccelY());
		SmartDashboard.putNumber("robot accel z " , oi.getRobotAccelZ());
//		
//		System.out.println("other accel x " + oi.getOtherAccelX());
//		System.out.println("other accel y " + oi.getOtherAccelY());
//		System.out.println("other accel z " + oi.getOtherAccelZ());
	}

	public void testPeriodic() {
		LiveWindow.run();
	}
}