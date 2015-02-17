package org.usfirst.frc.team930.robot.commands;

import org.usfirst.frc.team930.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class CloseLeftClaw extends Command {

    public CloseLeftClaw() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.leftClaw);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	SmartDashboard.putString("Close left status", "Im closing the left claw ");
        SmartDashboard.putBoolean("Close Left", Robot.leftClaw.limitSwitchClosed.get());
      	Robot.leftClaw.closeClaw();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
       return Robot.leftClaw.limitSwitchClosed.get();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.leftClaw.stopClaw();
    	SmartDashboard.putString("Close left status","Leaving close left claw");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
