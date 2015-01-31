package org.usfirst.frc.team930.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team930.robot.OI;
import org.usfirst.frc.team930.robot.Robot;

/**
 *
 */
public class Drive extends Command {

	OI oi = OI.getInstance();

	public Drive() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.drivetrain);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if (Robot.drivetrain.swerve.isFieldcentric == false)
			Robot.drivetrain.drive(oi.getForward(), oi.getStrafe(),
					oi.getRotX());
		else
			Robot.drivetrain.drive(oi.getForward(), oi.getStrafe(),
					90 - Math.atan2(oi.getRotX(), oi.getRotY()));
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
