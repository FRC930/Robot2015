package org.usfirst.frc.team930.robot.commands;

import org.usfirst.frc.team930.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Auto extends Command {
    int counter; //Is it bad to put this here?

    public Auto() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.drivetrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	counter = 0;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        double[]moveBack = {0.0000, -6.1146, -18.3438, -36.6876, -61.1461, -91.7191, -128.4067, -171.2090, -220.1258, -275.1573, -336.3033, -403.5640, -476.9392, -556.4291, -642.0336, -733.7527, -831.5864, -935.5347, -1045.5976, -1161.7751, -1284.0672, -1406.3593, -1528.6514, -1650.9435, -1773.2356, -1895.5277, -2011.7052, -2121.7681, -2225.7164, -2323.5501, -2415.2692, -2500.8737, -2580.3636, -2653.7388, -2720.9995, -2782.1455, -2837.1770, -2886.0938, -2928.8961, -2965.5837, -2996.1567, -3014.5006, -3020.6152, -3014.5006, -2996.1567, -2965.5837, -2928.8961, -2886.0938, -2837.1770, -2782.1455, -2720.9995, -2653.7388, -2580.3636, -2500.8737, -2415.2692, -2323.5501, -2225.7164, -2121.7681, -2011.7052, -1895.5277, -1773.2356, -1650.9435, -1528.6514, -1406.3593, -1284.0672, -1161.7751, -1045.5976, -935.5347, -831.5864, -733.7527, -642.0336, -556.4291, -476.9392, -403.5640, -336.3033, -275.1573, -220.1258, -171.2090, -128.4067, -91.7191, -61.1461, -36.6876, -18.3438, -6.1146,0.0000,0.0000,0.0000};
        int counter = 0;
        if (counter < moveBack.length){

        	Robot.drivetrain.manualSetMotors(moveBack[counter], 0);  
           
            counter = counter + 1;

        	} 
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
