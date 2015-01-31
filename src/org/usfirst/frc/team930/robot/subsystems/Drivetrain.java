package org.usfirst.frc.team930.robot.subsystems;

import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Drivetrain extends Subsystem {

	// By Noah and Nick
	
	final int CODES_PER_REV = 250;
	
	SwerveDrive swerve;
	CANTalon frDrive = new CANTalon(1);
	CANTalon flDrive = new CANTalon(3);
	CANTalon blDrive = new CANTalon(7);
	CANTalon brDrive = new CANTalon(5);

	CANJaguar frRot = new CANJaguar(2);
	CANJaguar flRot = new CANJaguar(4);
	CANJaguar blRot = new CANJaguar(8);
	CANJaguar brRot = new CANJaguar(6);

	public Drivetrain(double length, double width) {
		this.swerve = new SwerveDrive(length, width);
		
		frRot.setPositionMode(CANJaguar.kQuadEncoder, CODES_PER_REV, 1, 0, 0);
		flRot.setPositionMode(CANJaguar.kQuadEncoder, CODES_PER_REV, 1, 0, 0);
		blRot.setPositionMode(CANJaguar.kQuadEncoder, CODES_PER_REV, 1, 0, 0);
		brRot.setPositionMode(CANJaguar.kQuadEncoder, CODES_PER_REV, 1, 0, 0);
		
		frRot.enableControl();
	}

	public Drivetrain(double length, double width, boolean fieldcent) {
		this.swerve = new SwerveDrive(length, width, fieldcent);
	}

	public void drive(double forward, double strafe, double rot) {
		swerve.updateSwerve(forward, strafe, rot);
		frDrive.set(swerve.output("FRSpeed"));
		flDrive.set(swerve.output("FLSpeed"));
		blDrive.set(swerve.output("BLSpeed"));
		brDrive.set(swerve.output("BRSpeed"));
		
		

	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
}