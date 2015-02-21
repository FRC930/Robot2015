//HMS 930

package org.usfirst.frc.team930.robot;

import org.usfirst.frc.team930.robot.commands.CloseLeftClaw;
import org.usfirst.frc.team930.robot.commands.CloseRightClaw;
import org.usfirst.frc.team930.robot.commands.Drive;
import org.usfirst.frc.team930.robot.commands.MoveArm;
import org.usfirst.frc.team930.robot.commands.OpenLeftClaw;
import org.usfirst.frc.team930.robot.commands.OpenRightClaw;
// import org.usfirst.frc.team930.robot.commands.SetHeight;
import org.usfirst.frc.team930.robot.subsystems.Arm;
import org.usfirst.frc.team930.robot.subsystems.Claw;
import org.usfirst.frc.team930.robot.subsystems.Drivetrain;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

public class Robot extends IterativeRobot {
	
	public static final double DRIVETRAIN_WIDTH = 21.25;
	public static final double DRIVETRAIN_LENGTH = 33.00;
	
	public static final Arm arm = new Arm();
	public static final Claw leftClaw = new Claw(Claw.leftRelay, Claw.leftOpen, Claw.leftClosed, 1);
	public static final Claw rightClaw = new Claw(Claw.rightRelay, Claw.rightOpen, Claw.rightClosed, 2);
	public static final Drivetrain drivetrain = new Drivetrain(DRIVETRAIN_WIDTH, DRIVETRAIN_LENGTH);
	public static OI oi;

	Command closeLeftClaw;
	Command closeRightClaw;
	Command openLeftClaw;
	Command openRightClaw;
	
	// Command setHeight;
	Command moveArm;
	
	Command drive;
	
	public void robotInit() {
		oi = OI.getInstance();
		leftClaw.reverseDirection();
		closeLeftClaw = new CloseLeftClaw();
		closeRightClaw = new CloseRightClaw();
		openLeftClaw = new OpenLeftClaw();
		openRightClaw = new OpenRightClaw();
		drive = new Drive();
		// setHeight = new SetHeight();
		moveArm = new MoveArm();
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
		// if (autonomousCommand != null) autonomousCommand.cancel();
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