package org.usfirst.frc.team930.robot.armPID;

import edu.wpi.first.wpilibj.PIDOutput;

import edu.wpi.first.wpilibj.SpeedController;

public class ArmOutput implements PIDOutput {

	SpeedController sc1;
	SpeedController sc2;

	public ArmOutput(SpeedController c1, SpeedController c2) {
		
		sc1 = c1;
		sc2 = c2;

	}

	public void pidWrite(double output) {
		sc1.set(output);
		sc2.set(output);
		
//		System.out.println(output);
	}
}