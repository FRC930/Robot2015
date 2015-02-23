package org.usfirst.frc.team930.robot.commands;

import org.usfirst.frc.team930.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class MoveForward extends Command {

    public MoveForward() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drivetrain);	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//SmartDashboard.putNumber("auto input", 0);
    	Robot.drivetrain.manualDrive(SmartDashboard.getNumber("auto input"));
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return 1000 == Robot.drivetrain.getWheelPosition();
    }

    // Called once after isFinished returns true
    protected void end() {
    	System.out.println("Move forward ended");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
