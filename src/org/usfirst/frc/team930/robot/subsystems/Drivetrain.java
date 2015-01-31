package org.usfirst.frc.team930.robot.subsystems;

import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Drivetrain extends Subsystem {

	// By Noah and Nick

	final int CODES_PER_REV = 250;
	final double DEG_TO_REV = 1 / 360;

	SwerveDrive swerve;

	CANTalon frDrive;
	CANTalon flDrive;
	CANTalon blDrive;
	CANTalon brDrive;

	CANJaguar frRot;
	CANJaguar flRot;
	CANJaguar blRot;
	CANJaguar brRot;

	public Drivetrain(double length, double width) {
		this.swerve = new SwerveDrive(length, width);
		this.setMotors();
	}

	public Drivetrain(double length, double width, boolean fieldcent) {
		this.swerve = new SwerveDrive(length, width, fieldcent);
		this.setMotors();
	}

	public void setMotors() {

		frDrive = new CANTalon(1);
		flDrive = new CANTalon(3);
		blDrive = new CANTalon(7);
		brDrive = new CANTalon(5);

		frRot = new CANJaguar(2);
		flRot = new CANJaguar(4);
		blRot = new CANJaguar(8);
		brRot = new CANJaguar(6);

		frRot.setPositionMode(CANJaguar.kQuadEncoder, CODES_PER_REV, 1, 0, 0);
		flRot.setPositionMode(CANJaguar.kQuadEncoder, CODES_PER_REV, 1, 0, 0);
		blRot.setPositionMode(CANJaguar.kQuadEncoder, CODES_PER_REV, 1, 0, 0);
		brRot.setPositionMode(CANJaguar.kQuadEncoder, CODES_PER_REV, 1, 0, 0);

		frRot.enableControl();
		flRot.enableControl();
		blRot.enableControl();
		brRot.enableControl();
	}

	public void drive(double forward, double strafe, double rot) {
		swerve.updateSwerve(forward, strafe, rot);

		frDrive.set(swerve.output("FRSpeed"));
		flDrive.set(swerve.output("FLSpeed"));
		blDrive.set(swerve.output("BLSpeed"));
		brDrive.set(swerve.output("BRSpeed"));

		frRot.set(swerve.output("FRAngle") * DEG_TO_REV);
		flRot.set(swerve.output("FLAngle") * DEG_TO_REV);
		blRot.set(swerve.output("BLAngle") * DEG_TO_REV);
		brRot.set(swerve.output("BRAngle") * DEG_TO_REV);
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
}