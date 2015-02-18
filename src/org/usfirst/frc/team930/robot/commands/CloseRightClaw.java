package org.usfirst.frc.team930.robot.commands;

import org.usfirst.frc.team930.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class CloseRightClaw extends Command {

    public CloseRightClaw() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.rightClaw);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	SmartDashboard.putString("Close right status", "Im closing the right claw ");
        SmartDashboard.putBoolean("Close Right", Robot.rightClaw.limitSwitchClosed.get());
      	Robot.rightClaw.closeClaw();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
       return Robot.rightClaw.limitSwitchClosed.get();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.rightClaw.stopClaw();
    	SmartDashboard.putString("Close right status","Leaving close right claw");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
