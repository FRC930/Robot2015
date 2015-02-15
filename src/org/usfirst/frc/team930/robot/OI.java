package org.usfirst.frc.team930.robot;

import org.usfirst.frc.team930.robot.commands.CloseClaw;
import org.usfirst.frc.team930.robot.commands.CloseLeftClaw;
import org.usfirst.frc.team930.robot.commands.OpenClaw;
import org.usfirst.frc.team930.robot.commands.OpenLeftClaw;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OI {
	Joystick driver = new Joystick(1);
	Joystick codriver = new Joystick(2); 
	
	JoystickButton closeButton = new JoystickButton(driver, 0);
	JoystickButton openButton = new JoystickButton(driver, 3);

	
	public OI (){
		closeButton.whenPressed(new CloseClaw());
		openButton.whenPressed(new OpenClaw());

	}
}

