//Subsystem: Drivetrain

package org.usfirst.frc.team930.robot.subsystems;

import org.usfirst.frc.team930.robot.commands.Drive;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Drivetrain extends Subsystem {

    public void initDefaultCommand() {
    	setDefaultCommand(new Drive());
    }
}

