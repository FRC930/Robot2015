package org.usfirst.frc.team930.robot.subsystems;
import org.usfirst.frc.team930.robot.Robot;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Claw extends Subsystem{
	public static Relay leftRelay = new Relay(0);
	public static Relay rightRelay = new Relay(1);
	public static DigitalInput rightOpen = new DigitalInput(0);
	public static DigitalInput leftOpen = new DigitalInput(1);
	public static DigitalInput rightClosed = new DigitalInput(2);
	public static DigitalInput leftClosed = new DigitalInput(3);
	
	Relay r;
	DigitalInput limitSwitchOpen;
	DigitalInput limitSwitchClosed;
	int id;
	
	boolean isFinished;

	public Claw(Relay input, DigitalInput l1, DigitalInput l2, int ID) {
		r = input;
		limitSwitchOpen = l1;
		limitSwitchClosed = l2;
		isFinished = true;
		id = ID;

	}

	public void openClaw() {
		isFinished = false;
		System.out.println("open claw " + limitSwitchOpen.get());
		while ((limitSwitchOpen.get() == true) & Robot.isEnabled() {
			r.set(Relay.Value.kReverse);
			System.out.println("open claw. im stuck " + id);
			
		}
		r.set(Relay.Value.kOff);
		isFinished = true;
	}

	public void closeClaw() {
		isFinished = false;
		
		System.out.println("close claw " + limitSwitchClosed.get());
		while (limitSwitchClosed.get() == true) {
			r.set(Relay.Value.kForward);
			System.out.println("close claw. im stuck " + id);
			Timer.delay(.1);
		}
		r.set(Relay.Value.kOff);
//		if (limitSwitchClosed.get() == false){
//			r.set(Relay.Value.kForward);
//			System.out.println("if");
//		}else{
//			r.set(Relay.Value.kOff);
//			System.out.println("else");
//		}
		isFinished = true;
	}
	
	public boolean isFinished(){
		return isFinished;
	}

	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
}