package org.usfirst.frc.team930.robot;

import org.usfirst.frc.team930.robot.commands.CloseLeftClaw;
import org.usfirst.frc.team930.robot.commands.CloseRightClaw;
import org.usfirst.frc.team930.robot.commands.OpenLeftClaw;
import org.usfirst.frc.team930.robot.commands.OpenRightClaw;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OI {
	Joystick driver = new Joystick(0);
	Joystick codriver = new Joystick(1); 
	
	JoystickButton aButton = new JoystickButton(driver, 1);
	JoystickButton xButton = new JoystickButton(driver, 3);
	JoystickButton bButton = new JoystickButton(driver, 2);
	JoystickButton yButton = new JoystickButton(driver, 4);

	
	public OI (){
		aButton.whenPressed(new CloseLeftClaw());
		xButton.whenPressed(new OpenLeftClaw());
		bButton.whenPressed(new CloseRightClaw());
		yButton.whenPressed(new OpenRightClaw());


	}
}

