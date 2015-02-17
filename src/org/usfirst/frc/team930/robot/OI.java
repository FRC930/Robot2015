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
	
	JoystickButton closeButtonLeft = new JoystickButton(driver, 1);
	JoystickButton openButtonLeft = new JoystickButton(driver, 3);
	JoystickButton closeButtonRight = new JoystickButton(driver, 2);
	JoystickButton openButtonRight = new JoystickButton(driver, 4);

	
	public OI (){
		closeButtonLeft.whenPressed(new CloseLeftClaw());
		openButtonLeft.whenPressed(new OpenLeftClaw());
		closeButtonRight.whenPressed(new CloseRightClaw());
		openButtonRight.whenPressed(new OpenRightClaw());


	}
}

