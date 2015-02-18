package org.usfirst.frc.team930.robot.armPID;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.SpeedController;

public class ArmOutput implements PIDOutput {

	final double SPEED_P = .005;
	final double SPEED_I = .0007;

	PIDController controller1;
	PIDController controller2;

	SCSource source1;
	SCSource source2;

	SCOutput out1;
	SCOutput out2;

	public ArmOutput() {
	}

	public ArmOutput(SpeedController c1, SpeedController c2) {
		source1 = new SCSource(c1);
		source2 = new SCSource(c2);
		out1 = new SCOutput(c1);
		out2 = new SCOutput(c2);

		controller1 = new PIDController(SPEED_P, SPEED_I, 0, source1, out1);
		controller2 = new PIDController(SPEED_P, SPEED_I, 0, source2, out2);
	}

	public void pidWrite(double output) {
		controller1.setSetpoint(output);
		controller2.setSetpoint(output);
	}
}