package org.usfirst.frc.team930.robot.commands;

import org.usfirst.frc.team930.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class OpenLeftClaw extends Command {
	
    public OpenLeftClaw() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.leftClaw);	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        SmartDashboard.putString("Open left status", "Im opening the left claw ");
        SmartDashboard.putBoolean("Left Claw Open", Robot.leftClaw.limitSwitchOpen.get());
      	Robot.leftClaw.openClaw();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.leftClaw.limitSwitchOpen.get();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.leftClaw.stopClaw(); 
    	SmartDashboard.putString("Open left status","Leaving open left claw");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
