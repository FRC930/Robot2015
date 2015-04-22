package org.usfirst.frc.team930.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Delay extends Command {
	double time;
	double startTime;
	boolean isFinished;

    public Delay(double timeInput) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	time = timeInput * 1000.0;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	isFinished = false;
    	startTime = System.currentTimeMillis();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if ((System.currentTimeMillis()-startTime) < time){
    		isFinished = false;
    	}else isFinished = true;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isFinished;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
