//Subsystem: Claw

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
	DigitalInput limitSwitch2;
	
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
		while (limitSwitch1.get() != true){
			relay1.set(Relay.Value.kReverse);
		}
	}

	public void closeClaw() {
		while (limitSwitch2.get() != true){
			relay1.set(Relay.Value.kForward);
		}
	}
}
