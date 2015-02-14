package org.usfirst.frc.team930.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Relay;

public class Grip {
	Relay r;
	DigitalInput limitSwitch1;
	DigitalInput limitSwitch2;


public Grip(){
	
	
	
}

public Grip(Relay input, DigitalInput l1, DigitalInput l2){
	r = input;
	limitSwitch1 = l1;
	limitSwitch2 = l2;
	
	
}
public void openClaw() {
	while (limitSwitch1.get() != true){
		r.set(Relay.Value.kReverse);
	}
}

public void closeClaw() {
	while (limitSwitch2.get() != true){
		r.set(Relay.Value.kForward);
	}
}




}