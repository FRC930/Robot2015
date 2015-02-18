//HMS 930

package org.usfirst.frc.team930.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

import org.usfirst.frc.team930.robot.commands.SetHeight;
import org.usfirst.frc.team930.robot.subsystems.Arm;

public class Robot extends IterativeRobot {

	public static OI oi = OI.getInstance();
	public static final Arm arm = new Arm();

	Command drive;
	Command setHeight;

	public void robotInit() {
		setHeight = new SetHeight();
	}

	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	public void autonomousInit() {
		// if (autonomousCommand != null) autonomousCommand.start();
	}

	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	public void teleopInit() {
		// if (autonomousCommand != null) autonomousCommand.cancel();
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