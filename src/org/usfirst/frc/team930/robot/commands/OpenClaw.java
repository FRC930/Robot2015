package org.usfirst.frc.team930.robot.commands;

import org.usfirst.frc.team930.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class OpenClaw extends Command {

    public OpenClaw() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
<<<<<<< HEAD
<<<<<<< HEAD
    	requires(Robot.claw);	
=======
    	requires(Robot.claw);
>>>>>>> 241bffde9df7f41f24123ea9da86fe0b6bb169f3
=======
    	requires(Robot.claw);
>>>>>>> 43910c700cc0bae58df19f610d690026b38427ee
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
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
