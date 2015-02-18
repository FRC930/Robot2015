package org.usfirst.frc.team930.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Claw extends Subsystem{
	public static Relay rightRelay = new Relay(0);
	public static DigitalInput rightOpen = new DigitalInput(3);
	public static DigitalInput rightClosed = new DigitalInput(2);
		
	public static Relay leftRelay = new Relay(1);
	public static DigitalInput leftOpen = new DigitalInput(1);
	public static DigitalInput leftClosed = new DigitalInput(0);
	
	Relay r;
	public DigitalInput limitSwitchOpen;
	public DigitalInput limitSwitchClosed;
	int id;
	
	boolean isFinished;
	int direction;

	public Claw(Relay input, DigitalInput l1, DigitalInput l2, int ID) {
		r = input;
		limitSwitchOpen = l1;
		limitSwitchClosed = l2;
		isFinished = true;
		id = ID;
		direction = 1;
	}
	
	public void reverseDirection(){
		direction = direction * -1;
	}

	public void openClaw() {
//		isFinished = false;
//		System.out.println("open claw " + limitSwitchOpen.get());
//		while (limitSwitchOpen.get() == true){
			r.set(direction == 1 ? Relay.Value.kForward : Relay.Value.kReverse);
//			System.out.println("open claw. im stuck " + id);
//			
//		}
//		r.set(Relay.Value.kOff);
//		isFinished = true;
	}

	public void closeClaw() {
//		isFinished = false;
//		
//		System.out.println("close claw " + limitSwitchClosed.get());
//		while (limitSwitchClosed.get() == true) {
		r.set(direction == 1 ? Relay.Value.kReverse : Relay.Value.kForward);
//			System.out.println("close claw. im stuck " + id);
//			Timer.delay(.1);
//		}
//		r.set(Relay.Value.kOff);
//		if (limitSwitchClosed.get() == false){
//			r.set(Relay.Value.kForward);
//			System.out.println("if");
//		}else{
//			r.set(Relay.Value.kOff);
//			System.out.println("else");
//		}
//		isFinished = true;
	}
	
//	public boolean isFinished(){
//		return isFinished;
//	}
	
	public void stopClaw(){
		r.set(Relay.Value.kOff);
	}

	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
}