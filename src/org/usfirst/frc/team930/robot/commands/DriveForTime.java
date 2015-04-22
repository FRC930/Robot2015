package org.usfirst.frc.team930.robot.commands;

import org.usfirst.frc.team930.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveForTime extends Command {
	
	private double angle;
	private double speed;
	private double time;
	private boolean isFinished;

    public DriveForTime(double a, double s, double t) {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.drivetrain);
        angle = a;
        speed = s;
        time = t;
        
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	isFinished = false;
    	double initTime = System.currentTimeMillis();
    	while (((System.currentTimeMillis() - initTime)/1000.0) < time){
    		Robot.drivetrain.manualSetMotors(speed, (int)angle);
    	}
    	Robot.drivetrain.manualSetMotors(0.0, (int)angle);
    	isFinished = true;
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
