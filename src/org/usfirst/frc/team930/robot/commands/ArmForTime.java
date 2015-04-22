package org.usfirst.frc.team930.robot.commands;

import org.usfirst.frc.team930.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ArmForTime extends Command {
	
	private double angle;
	private double time;
	private boolean increase;
	boolean isFinished;

    public ArmForTime(double a, double t) {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.arm);
        angle = a;
        time = t;
        
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double initTime = System.currentTimeMillis();
    	isFinished = false;
    	while (((System.currentTimeMillis() - initTime)/1000.0) < time){
    		
//    		if (increase){
//    			angle = angle + 1;
//    		}else
//    			angle = angle - 1;
    		
       	 	Robot.arm.setAngle(angle);

    	}
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
