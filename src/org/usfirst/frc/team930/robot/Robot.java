//HMS 930

package org.usfirst.frc.team930.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

import org.usfirst.frc.team930.robot.commands.CloseLeftClaw;
import org.usfirst.frc.team930.robot.commands.CloseRightClaw;
import org.usfirst.frc.team930.robot.commands.OpenLeftClaw;
import org.usfirst.frc.team930.robot.commands.OpenRightClaw;
import org.usfirst.frc.team930.robot.subsystems.Arm;
import org.usfirst.frc.team930.robot.subsystems.Drivetrain;
import org.usfirst.frc.team930.robot.subsystems.Claw;

public class Robot extends IterativeRobot {

	
	
	public static final Arm arm = new Arm();
	public static final Claw leftClaw = new Claw(Claw.leftRelay, Claw.leftOpen, Claw.leftClosed, 1);
	public static final Claw rightClaw = new Claw(Claw.rightRelay, Claw.rightOpen, Claw.rightClosed, 2);
	public static final Drivetrain drivetrain = new Drivetrain();
	public static OI oi;

	Command closeLeftClaw;
	Command closeRightClaw;
	
	Command openLeftClaw;
	Command openRightClaw;

	
	public void robotInit() {
		oi = new OI();
		leftClaw.reverseDirection();
		closeLeftClaw = new CloseLeftClaw();
		closeRightClaw = new CloseRightClaw();
		openLeftClaw = new OpenLeftClaw();
		openRightClaw = new OpenRightClaw();
	}

	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	public void autonomousInit() {
	}

	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	public void teleopInit() {
	}

	public void disabledInit() {

	}

	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		
	}

	public void testPeriodic() {
		LiveWindow.run();
	}
}
