package org.usfirst.frc.team930.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team930.robot.OI;
import org.usfirst.frc.team930.robot.Robot;

public class Drive extends Command {

	OI oi = OI.getInstance();
	boolean isManual;
	double fwd;
	double str;
	double rot;

	public Drive() {
		requires(Robot.drivetrain);
		isManual = false;
	}

	public Drive(double fwd, double str, double rot) {
		this();
		this.fwd = fwd;
		this.str = str;
		this.rot = rot;
		isManual = true;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if (isManual = false) {
			if (Robot.drivetrain.swerve.isFieldcentric == false) {
				Robot.drivetrain.drive(oi.getForward(), oi.getStrafe(),
						oi.getRotX());

			} else {
				Robot.drivetrain.drive(oi.getForward(), oi.getStrafe(),
						Math.atan2(oi.getRotX(), oi.getRotY()));
			}
		} else if (isManual = true) {
			Robot.drivetrain.drive(fwd, str, rot);
		} else {
			System.out
					.println("somehow you set a boolean value to a non boolean value. *slow clap*");
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