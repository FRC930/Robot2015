//HMS 930

package org.usfirst.frc.team930.robot;

import org.usfirst.frc.team930.robot.OI.Axis;
import org.usfirst.frc.team930.robot.commands.CloseLeftClaw;
import org.usfirst.frc.team930.robot.commands.CloseRightClaw;
import org.usfirst.frc.team930.robot.commands.Drive;
import org.usfirst.frc.team930.robot.commands.OpenLeftClaw;
import org.usfirst.frc.team930.robot.commands.OpenRightClaw;
import org.usfirst.frc.team930.robot.subsystems.Arm;
import org.usfirst.frc.team930.robot.subsystems.Claw;
import org.usfirst.frc.team930.robot.subsystems.Drivetrain;
import org.usfirst.frc.team930.robot.subsystems.SwerveDrive;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {

	public static final Arm arm = new Arm();
	public static final Claw leftClaw = new Claw(Claw.leftRelay, Claw.leftOpen, Claw.leftClosed, 1);
	public static final Claw rightClaw = new Claw(Claw.rightRelay, Claw.rightOpen, Claw.rightClosed, 2);
	public static final Drivetrain drivetrain = new Drivetrain(RobotMap.DRIVETRAIN_WIDTH, RobotMap.DRIVETRAIN_LENGTH);
	public static OI oi;

	Command closeLeftClaw;
	Command closeRightClaw;
	Command openLeftClaw;
	Command openRightClaw;

	Command setHeight;

	Command drive;

	final double OSC_RATE = 10;

	public void robotInit() {
		oi = OI.getInstance();
		oi.initAccel();
		// leftClaw.reverseDirection();
		closeLeftClaw = new CloseLeftClaw();
		closeRightClaw = new CloseRightClaw();
		openLeftClaw = new OpenLeftClaw();
		openRightClaw = new OpenRightClaw();
		// drive = new Drive();
	}

	public void disabledPeriodic() {
		if(arm != null && arm.armPID != null) arm.armPID.disable();

	}

	public void autonomousInit() {
		// if (autonomousCommand != null) autonomousCommand.start();
	}

	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	public void teleopInit() {
		arm.startPID();
		arm.armPID.enable();
		
		
	}

	public void disabledInit() {
		
	}

	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		
		double clawAxisRight = oi.getCoDriverTriggerRight();
		double clawAxisLeft = oi.getCoDriverTriggerLeft();
		if (clawAxisLeft > 0.75){
			Scheduler.getInstance().add(new OpenLeftClaw());
		}
		if (clawAxisRight > 0.75){
			Scheduler.getInstance().add(new CloseRightClaw());
		}
		
		//arm.setAngle(0 /*+ OSC_RATE*Math.sin(2*Math.PI*(Timer.getMatchTime()))*/);
		
		if (arm.armPID.onTarget()){
			arm.armPID.reset();
			arm.armPID.enable();
		}
		
		SmartDashboard.putNumber("arm angle ", arm.getArmAngle());	
		SmartDashboard.putNumber("arm accel y " ,  oi.getArmAccel(Axis.Y));
		SmartDashboard.putNumber("arm accel z " ,  oi.getArmAccel(Axis.Z));
		SmartDashboard.putNumber("arm accel x " ,  oi.getArmAccel(Axis.X));
		SmartDashboard.putNumber("robot accel y " , oi.getRobotAccelY());
		SmartDashboard.putNumber("robot accel z " , oi.getRobotAccelZ());
		
		SmartDashboard.putNumber("arm accel x RAW" , oi.getArmAccelXRaw());
		SmartDashboard.putNumber("arm accel y RAW" , oi.getArmAccelYRaw());
		SmartDashboard.putNumber("arm accel z RAW" , oi.getArmAccelZRaw());
		SmartDashboard.putNumber("robot accel x RAW " , oi.getRobotAccelXRaw());
		SmartDashboard.putNumber("robot accel y RAW" , oi.getRobotAccelYRaw());
		SmartDashboard.putNumber("robot accel z RAW" , oi.getRobotAccelZRaw());
		
		SmartDashboard.putBoolean("left open", Claw.leftOpen.get());
		SmartDashboard.putBoolean("left closed", Claw.leftClosed.get());
		SmartDashboard.putBoolean("right open", Claw.rightOpen.get());
		SmartDashboard.putBoolean("right closed", Claw.rightClosed.get());
		
		SmartDashboard.putNumber("back right angle", Robot.drivetrain.getBackRightAngle());
		SmartDashboard.putNumber("back right swerve angle", Robot.drivetrain.getBackRightSwerve());
//		
//		System.out.println("other accel x " + oi.getOtherAccelX());
//		System.out.println("other accel y " + oi.getOtherAccelY());
//		System.out.println("other accel z " + oi.getOtherAccelZ());
	}

	public void testPeriodic() {
		LiveWindow.run();
	}
}