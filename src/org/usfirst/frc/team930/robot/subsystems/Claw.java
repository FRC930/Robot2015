package org.usfirst.frc.team930.robot.subsystems;

import org.usfirst.frc.team930.robot.RobotMap;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Claw extends Subsystem {
	public static Relay rightRelay = new Relay(RobotMap.RELAY_RIGHT);
	public static DigitalInput rightOpen = new DigitalInput(RobotMap.LIMIT_RIGHT_OPEN);
	public static DigitalInput rightClosed = new DigitalInput(RobotMap.LIMIT_RIGHT_CLOSED);

	public static Relay leftRelay = new Relay(RobotMap.RELAY_LEFT);
	public static DigitalInput leftOpen = new DigitalInput(RobotMap.LIMIT_LEFT_OPEN);
	public static DigitalInput leftClosed = new DigitalInput(RobotMap.LIMIT_LEFT_CLOSED);

	Relay r;
	public DigitalInput limitSwitchOpen;
	public DigitalInput limitSwitchClosed;
	int id;

	int direction;

	public Claw(Relay input, DigitalInput l1, DigitalInput l2, int ID) {
		r = input;
		limitSwitchOpen = l1;
		limitSwitchClosed = l2;
		id = ID;
		direction = 1;
	}

	public void reverseDirection() {
		direction = direction * -1;
	}

	public void openClaw() {
		r.set(direction == 1 ? Relay.Value.kForward : Relay.Value.kReverse);
	}

	public void closeClaw() {
		r.set(direction == 1 ? Relay.Value.kReverse : Relay.Value.kForward);
	}

	public void stopClaw() {
		//AnalogInput a;
		r.set(Relay.Value.kOff);
	}
	
	public boolean isOpened(){
		for (int counter = 0; counter<1000;counter++){
			//SmartDashboard.putNumber("Counter Open", counter);
			if (limitSwitchOpen.get()){
				return true;
			}
		}
		return false;
	}
	
	public boolean isClosed() {
		for (int counter2 = 0; counter2<1000;counter2++){
			//SmartDashboard.putNumber("Counter Close", counter2);
			if (limitSwitchClosed.get()){
				return true;
			}
		}
		return false;
	}
	protected void initDefaultCommand() {

	}
}