package org.usfirst.frc.team930.robot.subsystems;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Subsystem;

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
	
	boolean isFinished;

	public Claw(Relay input, DigitalInput l1, DigitalInput l2) {
		r = input;
		limitSwitchOpen = l1;
		limitSwitchClosed = l2;
		isFinished = true;

	}

	public void openClaw() {
		isFinished = false;
		while (limitSwitchOpen.get() == true) {
			r.set(Relay.Value.kReverse);
		}
		r.set(Relay.Value.kOff);
		isFinished = true;
	}

	public void closeClaw() {
		isFinished = false;
		while (limitSwitchClosed.get() == true) {
			r.set(Relay.Value.kForward);
		}
		r.set(Relay.Value.kOff);
		isFinished = true;
	}
	
	public boolean isFinished(){
		return isFinished;
	}

	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
}