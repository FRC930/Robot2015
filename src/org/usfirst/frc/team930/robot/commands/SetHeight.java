package org.usfirst.frc.team930.robot.commands;

import org.usfirst.frc.team930.robot.OI;
import org.usfirst.frc.team930.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class SetHeight extends Command {

	final double HEIGHT_MAX = 46;
	final double HEIGHT_MIN = 44;
	final double HEIGHT_OF_ROBOT = 45.25;
	final double LENGTH_OF_ARM = 47.0;

	OI oi = OI.getInstance();

	public SetHeight() {
		requires(Robot.arm);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		// height in inches from the floor
		double height = HEIGHT_MIN + (HEIGHT_MAX - HEIGHT_MIN) * oi.getArmHeight();

		double angle = Math.asin((height - HEIGHT_OF_ROBOT) / LENGTH_OF_ARM);

		if (angle > HEIGHT_MAX)
			angle = HEIGHT_MAX;
		else if (angle < HEIGHT_MIN)
			angle = HEIGHT_MIN;
		Robot.arm.setAngle(angle);
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
