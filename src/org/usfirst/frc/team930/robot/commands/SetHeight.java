package org.usfirst.frc.team930.robot.commands;

import org.usfirst.frc.team930.robot.OI;
import org.usfirst.frc.team930.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SetHeight extends Command {

	/*final double HEIGHT_MAX = 65;
	final double HEIGHT_MIN = 10;
	final double HEIGHT_OF_ROBOT = 45.25;
	final double LENGTH_OF_ARM = 47.0;*/

	double angle;

	public SetHeight(double angle) {
		requires(Robot.arm);
		this.angle = angle;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		SmartDashboard.putNumber("Execute Angle", angle);
		Robot.arm.setAngle(angle);
	}	

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return Robot.arm.armPID.onTarget();
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}