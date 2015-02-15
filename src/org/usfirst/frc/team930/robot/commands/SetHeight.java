package org.usfirst.frc.team930.robot.commands;

import org.usfirst.frc.team930.robot.OI;
import org.usfirst.frc.team930.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SetHeight extends Command {

	int totes;
	final int NO_TOTES = -1;

	final double HEIGHT_MAX = 65;
	final double HEIGHT_MIN = 10;
	final double HEIGHT_OF_ROBOT = 45.25;
	final double LENGTH_OF_ARM = 47.0;

	final double TOTE_HEIGHT = 10;

	double angle;

	OI oi = OI.getInstance();

	public SetHeight() {
		requires(Robot.arm);
		totes = NO_TOTES;
	}

	public SetHeight(int totes) {
		requires(Robot.arm);
		this.totes = totes;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		SmartDashboard.putBoolean("Update", false);
		SmartDashboard.putNumber("ArmHeight (inches)", 40);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		// height in inches from the floor
		if (SmartDashboard.getBoolean("Update") && totes != NO_TOTES) {
			double height = totes * TOTE_HEIGHT;
			/* HEIGHT_MIN + (HEIGHT_MAX - HEIGHT_MIN) * oi.getArmHeight(); */

			if (height > HEIGHT_MAX)
				height = HEIGHT_MAX;
			else if (height < HEIGHT_MIN)
				height = HEIGHT_MIN;

			angle = Math.asin((height - HEIGHT_OF_ROBOT) / LENGTH_OF_ARM);

			Robot.arm.setAngle(angle);
		} else if (SmartDashboard.getBoolean("Update")) {
			double height = SmartDashboard.getNumber("ArmHeight (inches)");
			/* HEIGHT_MIN + (HEIGHT_MAX - HEIGHT_MIN) * oi.getArmHeight(); */

			if (height > HEIGHT_MAX)
				height = HEIGHT_MAX;
			else if (height < HEIGHT_MIN)
				height = HEIGHT_MIN;

			angle = Math.asin((height - HEIGHT_OF_ROBOT) / LENGTH_OF_ARM);
			SmartDashboard.putNumber("Angle", angle);
			SmartDashboard.putNumber("calc'd Height", LENGTH_OF_ARM * Math.sin(angle) + HEIGHT_OF_ROBOT);

			Robot.arm.setAngle(angle);
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		if (Robot.arm.getArmAngle() == angle)
			return true;
		else
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
