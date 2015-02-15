//HMS 930

package org.usfirst.frc.team930.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

import org.usfirst.frc.team930.robot.commands.CloseClaw;
import org.usfirst.frc.team930.robot.commands.OpenClaw;
import org.usfirst.frc.team930.robot.subsystems.Arm;
import org.usfirst.frc.team930.robot.subsystems.Drivetrain;
import org.usfirst.frc.team930.robot.subsystems.Claw;

public class Robot extends IterativeRobot {

	public static final Arm arm = new Arm();
	public static final Claw claw = new Claw();
	public static final Drivetrain drivetrain = new Drivetrain();
	public static OI oi;

	Command closeClaw;
	Command openClaw;

	public void robotInit() {
		oi = new OI();
		closeClaw = new CloseClaw();
		openClaw = new OpenClaw();
	}

	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	public void autonomousInit() {
	//	if (autonomousCommand != null)
			//autonomousCommand.start();
	}

	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	public void teleopInit() {
		//if (autonomousCommand != null)
			//autonomousCommand.cancel();
	}

	public void disabledInit() {

	}

	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}

	public void testPeriodic() {
		LiveWindow.run();
	}
}
