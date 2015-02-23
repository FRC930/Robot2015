//HMS 930

package org.usfirst.frc.team930.robot;

import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

import org.usfirst.frc.team930.robot.commands.Auto;
import org.usfirst.frc.team930.robot.commands.CloseLeftClaw;
import org.usfirst.frc.team930.robot.commands.CloseRightClaw;
import org.usfirst.frc.team930.robot.commands.Drive;
import org.usfirst.frc.team930.robot.commands.MoveForward;
import org.usfirst.frc.team930.robot.commands.OpenLeftClaw;
import org.usfirst.frc.team930.robot.commands.OpenRightClaw;
import org.usfirst.frc.team930.robot.subsystems.Drivetrain;
import org.usfirst.frc.team930.robot.subsystems.Claw;


public class Robot extends IterativeRobot {
	
	public static final double DRIVETRAIN_WIDTH = 21.25;
	public static final double DRIVETRAIN_LENGTH = 33.00;
	
	public static final Claw leftClaw = new Claw(Claw.leftRelay, Claw.leftOpen, Claw.leftClosed, 1);
	public static final Claw rightClaw = new Claw(Claw.rightRelay, Claw.rightOpen, Claw.rightClosed, 2);
	public static final Drivetrain drivetrain = new Drivetrain(DRIVETRAIN_WIDTH, DRIVETRAIN_LENGTH);
	public static OI oi;

	Command closeLeftClaw;
	Command closeRightClaw;
	
	Command openLeftClaw;
	Command openRightClaw;
	Command drive;

	Command moveForward;
	Command auto;
	
	public void robotInit() {
		oi = OI.getInstance();
		leftClaw.reverseDirection();
		closeLeftClaw = new CloseLeftClaw();
		closeRightClaw = new CloseRightClaw();
		openLeftClaw = new OpenLeftClaw();
		openRightClaw = new OpenRightClaw();
		drive = new Drive();
		auto = new Auto();
		moveForward = new MoveForward();
	}

	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

    public void autonomousInit() {
    	Robot.drivetrain.changeTalonToPosition();
    	Robot.drivetrain.resetEncoder();
    	auto.start();
    }
 
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
    	Robot.drivetrain.changeTalonToPosition();
    	Robot.drivetrain.resetEncoder();
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