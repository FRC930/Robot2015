package org.usfirst.frc.team930.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OI {
	Joystick driver = new Joystick(1);
	Joystick codriver = new Joystick(2); 
	
	JoystickButton aButton = new JoystickButton(driver, 4);
	
	public OI (){
		aButton.whenPressed(closeClaw);
	}
}

