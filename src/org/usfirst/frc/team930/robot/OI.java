package org.usfirst.frc.team930.robot;

import org.usfirst.frc.team930.robot.commands.CloseClaw;
import org.usfirst.frc.team930.robot.commands.CloseLeftClaw;
import org.usfirst.frc.team930.robot.commands.CloseRightClaw;
import org.usfirst.frc.team930.robot.commands.OpenClaw;
import org.usfirst.frc.team930.robot.commands.OpenLeftClaw;
import org.usfirst.frc.team930.robot.commands.OpenRightClaw;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OI {
	Joystick driver = new Joystick(0);
	Joystick codriver = new Joystick(1); 
	
	JoystickButton closeButton = new JoystickButton(driver, 1);
	JoystickButton openButton = new JoystickButton(driver, 4);

	
	public OI (){
		closeButton.whenPressed(new CloseLeftClaw());
		openButton.whenPressed(new OpenLeftClaw());

	}
}

