package org.usfirst.frc.team930.robot.subsystems;

import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.SpeedController;

public class ArmOutput implements PIDOutput {
	
	SpeedController controller1;
	SpeedController controller2;
	
	public ArmOutput(){
	}
	
	public ArmOutput(SpeedController c1, SpeedController c2){
		controller1 = c1;
		controller2 = c2;
	}
	
	public void pidWrite(double output) {
		controller1.set(output);
		controller2.set(output);
	}
}