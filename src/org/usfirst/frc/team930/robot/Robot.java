//HMS 930

package org.usfirst.frc.team930.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

import org.usfirst.frc.team930.robot.commands.Drive;
import org.usfirst.frc.team930.robot.subsystems.Arm;
import org.usfirst.frc.team930.robot.subsystems.Claw;
import org.usfirst.frc.team930.robot.subsystems.Drivetrain;


public class Robot extends IterativeRobot {

	public static final Arm arm = new Arm();
	public static final Claw claw = new Claw();
	public static final Drivetrain drivetrain = new Drivetrain(33.00 , 21.25);
	public static OI oi;

    Command drive;

 
    public void robotInit() {
		oi = new OI();
        drive = new Drive();
    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

    public void autonomousInit() {
        if (autonomousCommand != null) autonomousCommand.start();
    }

    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
        if (autonomousCommand != null) autonomousCommand.cancel();
    }

    public void disabledInit(){

    }

    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    }
    
    public void testPeriodic() {
        LiveWindow.run();
    }
}
