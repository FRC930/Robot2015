package org.usfirst.frc.team930.robot.subsystems;

import org.usfirst.frc.team930.robot.commands.Drive;
import org.usfirst.frc.team930.robot.subsystems.SwerveDrive;
import org.usfirst.frc.team930.robot.subsystems.SwerveDrive.Outputs;

import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.CANTalon.ControlMode;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Drivetrain extends Subsystem {

	// By Team 930

	final int CODES_PER_REV = 414;
	public final double DEG_TO_GEAR_TO_REV = 1 / 360.0;
	final double SLOWDOWN = .3;
	final int SPEED_TO_CODES = 8000;

	final double DEFAULT_JAG_P = -5900;
	final double DEFAULT_JAG_I = -80;
	final double DEFAULT_TAL_P = .05;
	final double DEFAULT_TAL_I = .0007;

	final int FRONT_LEFT = 2;
	final int FRONT_RIGHT = 1;
	final int BACK_LEFT = 4;
	final int BACK_RIGHT = 3;

	public SwerveDrive swerve;

	public CANTalon frDrive;
	public CANTalon flDrive;
	public CANTalon blDrive;
	public CANTalon brDrive;

	public CANJaguar frRot;
	public CANJaguar flRot;
	public CANJaguar blRot;
	public CANJaguar brRot;

	Gyro gyro;

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

		gyro = new Gyro(1);

		frRot.setPositionMode(CANJaguar.kQuadEncoder, CODES_PER_REV,
				DEFAULT_JAG_P, DEFAULT_JAG_I, 0);
		flRot.setPositionMode(CANJaguar.kQuadEncoder, CODES_PER_REV,
				DEFAULT_JAG_P, DEFAULT_JAG_I, 0);
		blRot.setPositionMode(CANJaguar.kQuadEncoder, CODES_PER_REV,
				DEFAULT_JAG_P, DEFAULT_JAG_I, 0);
		brRot.setPositionMode(CANJaguar.kQuadEncoder, CODES_PER_REV,
				DEFAULT_JAG_P, DEFAULT_JAG_I, 0);

		frDrive.changeControlMode(ControlMode.Speed);
		frDrive.setPID(DEFAULT_TAL_P, DEFAULT_TAL_I, 0);
		flDrive.changeControlMode(ControlMode.Speed);
		flDrive.setPID(DEFAULT_TAL_P, DEFAULT_TAL_I, 0);
		blDrive.changeControlMode(ControlMode.Speed);
		blDrive.setPID(DEFAULT_TAL_P, DEFAULT_TAL_I, 0);
		brDrive.changeControlMode(ControlMode.Speed);
		brDrive.setPID(DEFAULT_TAL_P, DEFAULT_TAL_I, 0);

		frRot.enableControl();
		flRot.enableControl();
		blRot.enableControl();
		brRot.enableControl();

		gyro.initGyro();
	}

	public void drive(double forward, double strafe, double rot) {

		//
		// if (forward == 0 && strafe == 0) {
		// frRot.setI(0);
		// flRot.setI(0);
		// blRot.setI(0);
		// brRot.setI(0);
		// } else {
		// frRot.setI(DEFAULT_I);
		// flRot.setI(DEFAULT_I);
		// blRot.setI(DEFAULT_I);
		// brRot.setI(DEFAULT_I);
		// }

		// double heading = Math.PI / 2 - gyro.getAngle() * (Math.PI / 180);
		swerve.updateSwerve(forward, strafe, rot);

		System.out.println("fr encoder " + frRot.getPosition());

		this.quickAngle(swerve.output(Outputs.frontRightAngle),
				swerve.output(Outputs.frontRightSpeed), frRot, frDrive);
		this.quickAngle(swerve.output(Outputs.frontLeftAngle),
				swerve.output(Outputs.frontLeftSpeed), flRot, flDrive);
		this.quickAngle(swerve.output(Outputs.backLeftAngle),
				swerve.output(Outputs.backLeftSpeed), blRot, blDrive);
		this.quickAngle(swerve.output(Outputs.backRightAngle),
				swerve.output(Outputs.backRightSpeed), brRot, brDrive);

		// SmartDashboard.putNumber("front right", frRot.getFaults());
		// SmartDashboard.putNumber("front left", flRot.getFaults());
		// SmartDashboard.putNumber("back right", brRot.getFaults());
		// SmartDashboard.putNumber("back left", blRot.getFaults());
		//

		/*
		 * SmartDashboard.putNumber("Front right encoder", frRot.getPosition());
		 * SmartDashboard.putNumber("Front left encoder", flRot.getPosition());
		 * SmartDashboard.putNumber("Back right encoder", brRot.getPosition());
		 * SmartDashboard.putNumber("Back left encoder", blRot.getPosition());
		 */

		SmartDashboard.putNumber("FR angle output",
				swerve.output(Outputs.frontRightAngle) * DEG_TO_GEAR_TO_REV);
		SmartDashboard.putNumber("FL angle output",
				swerve.output(Outputs.frontLeftAngle) * DEG_TO_GEAR_TO_REV);
		SmartDashboard.putNumber("BR angle output",
				swerve.output(Outputs.backRightAngle) * DEG_TO_GEAR_TO_REV);
		SmartDashboard.putNumber("BL angle output",
				swerve.output(Outputs.backLeftAngle) * DEG_TO_GEAR_TO_REV);

		SmartDashboard.putNumber("FR speed output",
				swerve.output(Outputs.frontRightSpeed));
		SmartDashboard.putNumber("FL speed output",
				swerve.output(Outputs.frontLeftSpeed));
		SmartDashboard.putNumber("BR speed output",
				swerve.output(Outputs.backRightSpeed));
		SmartDashboard.putNumber("BL speed output",
				swerve.output(Outputs.backLeftSpeed));

		SmartDashboard.putNumber("Gyro", gyro.getAngle() % 360);

		// outputings
		/*
		 * SmartDashboard.putNumber("FRS",
		 * swerve.output(Outputs.frontRightSpeed));
		 * SmartDashboard.putNumber("FLS",
		 * swerve.output(Outputs.frontLeftSpeed));
		 * SmartDashboard.putNumber("BLS",
		 * swerve.output(Outputs.backLeftSpeed));
		 * SmartDashboard.putNumber("BRS",
		 * swerve.output(Outputs.backRightSpeed));
		 * 
		 * SmartDashboard.putNumber("FRA",
		 * swerve.output(Outputs.frontRightAngle));
		 * SmartDashboard.putNumber("FLA",
		 * swerve.output(Outputs.frontLeftAngle));
		 * SmartDashboard.putNumber("BLA",
		 * swerve.output(Outputs.backLeftAngle));
		 * SmartDashboard.putNumber("BRA",
		 * swerve.output(Outputs.backRightAngle));
		 */

		// thigns
		// System.out.println("FRS " + swerve.output(Outputs.frontRightSpeed));
		// System.out.println("FLS " + swerve.output(Outputs.frontLeftSpeed));
		// System.out.println("BLS " + swerve.output(Outputs.backLeftSpeed));
		// System.out.println("BRS " + swerve.output(Outputs.backRightSpeed));
		//
		// System.out.println("FRA " + swerve.output(Outputs.frontRightAngle));
		// System.out.println("FLA " + swerve.output(Outputs.frontLeftAngle));
		// System.out.println("BLA " + swerve.output(Outputs.backLeftAngle));
		// System.out.println("BRA " + swerve.output(Outputs.backRightAngle));
	}

	public void quickAngle(double angle, double speed, CANJaguar jag,
			CANTalon talon) {
		// tr
		double oldAngle = jag.getPosition() * 360;
		double dir = 0;
		angle += 360 * (int) ((oldAngle - angle) / 360);

		if (oldAngle > angle) {
			dir = 1;
		} else {
			dir = -1;
		}
		while (Math.abs(oldAngle - angle) > 90) {
			angle += dir * 180;
			speed *= -1;

		}
		jag.set(angle * DEG_TO_GEAR_TO_REV);
		talon.set(SPEED_TO_CODES * SLOWDOWN * speed);

	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		setDefaultCommand(new Drive());

	}

}