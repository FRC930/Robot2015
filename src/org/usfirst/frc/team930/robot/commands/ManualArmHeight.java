package org.usfirst.frc.team930.robot.commands;

import org.usfirst.frc.team930.robot.OI;
import org.usfirst.frc.team930.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class ManualArmHeight extends Command {
	
	
    public ManualArmHeight() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.arm);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	System.out.println("inside manual arm height");
    }
   
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	 OI oi = OI.getInstance();
    	 double input = Robot.arm.getArmAngle() +  oi.getArmCoDriver()*10;
    	 SmartDashboard.putNumber("Input for the arm", input);
    	 SmartDashboard.putNumber("JOYSTICK THING", oi.getArmCoDriver());
    	 //double angle = angle + input; 
    	 Robot.arm.setAngle(input);
    }

    // Make this return true when this Command no longer needs to run execute() or nah
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
