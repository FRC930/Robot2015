package org.usfirst.frc.team930.robot.armPID;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.SpeedController;

public class ArmOutput implements PIDOutput {

	PIDController bindPID;
	BindOutput bindOut = new BindOutput();
	SpeedController scRight;
	SpeedController scLeft;
	
	public static final double P_BIND = 1;

	public ArmOutput(SpeedController c1, SpeedController c2) {
		
		PIDController bindPID = new PIDController(P_BIND, 0, 0, new BindSource(),
				bindOut, .001);
		
		bindPID.reset();
		bindPID.setSetpoint(0);
		
		scRight = c1;
		scLeft = c2;
	}

	public void pidWrite(double output) {
		scRight.set(output + bindOut.getOut());
		scLeft.set(output - bindOut.getOut());
	}
}