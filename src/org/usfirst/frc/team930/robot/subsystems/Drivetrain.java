package org.usfirst.frc.team930.robot.subsystems;

import org.usfirst.frc.team930.robot.commands.Drive;
import org.usfirst.frc.team930.robot.subsystems.SwerveDrive;
import org.usfirst.frc.team930.robot.subsystems.SwerveDrive.Outputs;

import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Drivetrain extends Subsystem {

	// By Noah and Nick

	final int CODES_PER_REV = 497;
	final double DEG_TO_GEAR_TO_TICK = 48 / 40 * 497.0 / 360;
	final int FRONT_LEFT = 2;
	final int FRONT_RIGHT = 1;
	final int BACK_LEFT = 4;
	final int BACK_RIGHT = 3;

	public SwerveDrive swerve;

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

		frDrive = new CANTalon(FRONT_RIGHT);
		flDrive = new CANTalon(FRONT_LEFT);
		blDrive = new CANTalon(BACK_LEFT);
		brDrive = new CANTalon(BACK_RIGHT);

		frRot = new CANJaguar(FRONT_RIGHT);
		flRot = new CANJaguar(FRONT_LEFT);
		blRot = new CANJaguar(BACK_LEFT);
		brRot = new CANJaguar(BACK_RIGHT);

		frRot.setPositionMode(CANJaguar.kQuadEncoder, CODES_PER_REV, -5900, -80, 0);
		flRot.setPositionMode(CANJaguar.kQuadEncoder, CODES_PER_REV, -5900, -80, 0);
		blRot.setPositionMode(CANJaguar.kQuadEncoder, CODES_PER_REV, -5900, -80, 0);
		brRot.setPositionMode(CANJaguar.kQuadEncoder, CODES_PER_REV, -5900, -80, 0);

		frRot.enableControl();
		flRot.enableControl();
		blRot.enableControl();
		brRot.enableControl();
	}

	public void drive(double forward, double strafe, double rot) {
		swerve.updateSwerve(forward, strafe, rot);
		
		frDrive.set(swerve.output(Outputs.frontRightSpeed));
		flDrive.set(swerve.output(Outputs.frontLeftSpeed));
		blDrive.set(swerve.output(Outputs.backLeftSpeed));
		brDrive.set(swerve.output(Outputs.backRightSpeed));
		
		System.out.println(swerve.output(Outputs.frontRightAngle));

		frRot.set(swerve.output(Outputs.frontRightAngle) * DEG_TO_GEAR_TO_TICK);
		flRot.set(swerve.output(Outputs.frontLeftAngle) * DEG_TO_GEAR_TO_TICK);
		blRot.set(swerve.output(Outputs.backLeftAngle) * DEG_TO_GEAR_TO_TICK);
		brRot.set(swerve.output(Outputs.backRightAngle) * DEG_TO_GEAR_TO_TICK);
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		setDefaultCommand(new Drive());
		
	}
}