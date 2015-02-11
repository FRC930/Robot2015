package org.usfirst.frc.team930.robot;


import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

import org.usfirst.frc.team930.robot.commands.ArmHeight0;
import org.usfirst.frc.team930.robot.commands.ArmHeight1;
import org.usfirst.frc.team930.robot.commands.MoveArmDown;
import org.usfirst.frc.team930.robot.commands.MoveArmUp;

//XXX_666taintedGangaGod_XXX
/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */

public class OI {
	
	public static OI getInstance(){
		return Holder.instance;
	}
	public OI(){
//		incUp.whenPressed(new ArmHeight1());
//		incDown.whenPressed(new ArmHeight0());
//		goUp.whileHeld(new MoveArmUp());
//		goDown.whileHeld(new MoveArmDown()); 

// XXX_Druk3nRainbowPig_XXX
	}
	
	public static class Holder {
		public static final OI instance = new OI();
	}
	Joystick stick2 = new Joystick(0);
//	Button incUp = new JoystickButton(stick2, 3);
//	Button incDown = new JoystickButton(stick2, 0);
//	Button goUp = new JoystickButton(stick2, 1);
//	Button goDown = new JoystickButton(stick2, 2);
	
	
	// XXX_P0tat0*.C0mM_XXX
	public double getAxisY(){
	return 0.5*stick2.getRawAxis(1);
	
	}

}
	

    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);
    
    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.
    
    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:
    
    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());
    
    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());
    
    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());
}

