//Sybsystem: Claw

package org.usfirst.frc.team930.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Claw extends Subsystem {

	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	Relay relay2;
	Relay relay1;
	DigitalInput limitSwitch1;
	DigitalInput limitSwitch2 ;
	
	public Claw(){ // Constructor
		relay1 = new Relay(1);					
		relay2 = new Relay(2);
		limitSwitch1 = new DigitalInput(1);
		limitSwitch2 = new DigitalInput(2);
	}
	
	public void initDefaultCommand() {
		
		//Sometimes the world is a better place with a little more onions 
		// setDirection the default command for a subsystem here.
		// setDirectionDefaultCommand(new MySpecialCommand());
	}

	public void openClaw() {
		if (limitSwitch2.get() == false) {		//Control for open claw
			relay1.set(Relay.Value.kReverse);
			relay2.set(Relay.Value.kReverse);

		} else {
			relay1.set(Relay.Value.kOff);						//Control for close claw, opposite
			relay2.set(Relay.Value.kOff);		
		}

	}

	public void closeClaw() {
		if (limitSwitch1.get() == false) {	//Control for close claw
			relay1.set(Relay.Value.kForward);
			relay2.set(Relay.Value.kForward);

		} else {
			relay1.set(Relay.Value.kOff);					//Control for open claw, opposite
			relay2.set(Relay.Value.kOff);	

		}
	}
}
