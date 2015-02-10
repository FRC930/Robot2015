//HMS 930

package org.usfirst.frc.team930.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

import org.usfirst.frc.team930.robot.commands.ArmHeight0;
import org.usfirst.frc.team930.robot.commands.ArmHeight1;
import org.usfirst.frc.team930.robot.commands.Drive;
import org.usfirst.frc.team930.robot.commands.ExampleCommand;
import org.usfirst.frc.team930.robot.subsystems.Arm;
import org.usfirst.frc.team930.robot.subsystems.Claw;
import org.usfirst.frc.team930.robot.subsystems.Drivetrain;
import org.usfirst.frc.team930.robot.subsystems.ExampleSubsystem;


public class Robot extends IterativeRobot {

	public static final Arm arm = new Arm();
	public static final Claw claw = new Claw();
	public static final Drivetrain drivetrain = new Drivetrain();
	public static OI oi;

    Command drive;
    Command armHeight0;
    Command armHeight1;
    
// XXX_N!tr0*Typ3_XXX
    public void robotInit() {
		oi = OI.getInstance();
        drive = new Drive();
        armHeight0 = new ArmHeight0();
        armHeight1 = new ArmHeight1();
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
