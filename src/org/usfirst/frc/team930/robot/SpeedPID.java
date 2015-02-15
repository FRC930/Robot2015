//package org.usfirst.frc.team930.robot;
//
//import edu.wpi.first.wpilibj.CANTalon;
//import edu.wpi.first.wpilibj.CANTalon.ControlMode;
//import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
//import edu.wpi.first.wpilibj.SampleRobot;
//import edu.wpi.first.wpilibj.RobotDrive;
//import edu.wpi.first.wpilibj.Joystick;
//import edu.wpi.first.wpilibj.Timer;
//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//
///**
// * This is a demo program showing the use of the RobotDrive class.
// * The SampleRobot class is the base of a robot application that will automatically call your
// * Autonomous and OperatorControl methods at the right time as controlled by the switches on
// * the driver station or the field controls.
// *
// * The VM is configured to automatically run this class, and to call the
// * functions corresponding to each mode, as described in the SampleRobot
// * documentation. If you change the name of this class or the package after
// * creating this project, you must also update the manifest file in the resource
// * directory.
// *
// * WARNING: While it may look like a good choice to use for your code if you're inexperienced,
// * don't. Unless you know what you are doing, complex code will be much more difficult under
// * this system. Use IterativeRobot or Command-Based instead if you're new.
// */
//public class SpeedPID extends SampleRobot {
//    /*RobotDrive myRobot;
//    Joystick stick;
//*/
//	CANTalon talon;
//    public SpeedPID() {
//        /*myRobot = new RobotDrive(0, 1);
//        myRobot.setExpiration(0.1);
//        stick = new Joystick(0);*/
//    	talon = new CANTalon(1);
//    	talon.changeControlMode(ControlMode.Speed);
//    	
//    }
//
//    /**
//     * Drive left & right motors for 2 seconds then stop
//     */
//    public void autonomous() {
//        /*myRobot.setSafetyEnabled(false);
//        myRobot.drive(-0.5, 0.0);	// drive forwards half speed
//        Timer.delay(2.0);		//    for 2 seconds
//        myRobot.drive(0.0, 0.0);	// stop robot*/
//    }
//
//    /**
//     * Runs the motors with arcade steering.
//     */
//    public void operatorControl() {
//        //myRobot.setSafetyEnabled(true);
//    	double p, i, d, set;
//    	SmartDashboard.putNumber("P",0);
//		SmartDashboard.putNumber("I",0);
//		SmartDashboard.putNumber("D",0);
//		SmartDashboard.putBoolean("Update",false);
//		SmartDashboard.putNumber("Setpoint", 0);
//		talon.setFeedbackDevice(FeedbackDevice.QuadEncoder);
//		
//        while (isOperatorControl() && isEnabled()) {
//            //myRobot.arcadeDrive(stick); // drive with arcade style (use right stick)
//            //Timer.delay(0.005);		// wait for a motor update time
//        	if(SmartDashboard.getBoolean("Update")){
//        		System.out.println("Updating PID");
//        		p = SmartDashboard.getNumber("P");
//        		i = SmartDashboard.getNumber("I");
//        		d = SmartDashboard.getNumber("D");
//        		System.out.println("New Values = P:" + p + " I:" + i + " D:" + d);
//        		talon.setPID(p, i, d);
//        	}
//        	set = SmartDashboard.getNumber("Setpoint");
//        	talon.set(set);
//        	SmartDashboard.putNumber("Sending to Talon", talon.getSetpoint());
//        	SmartDashboard.putNumber("Encoder Velocity",talon.getEncVelocity() * 0.60);
//        	SmartDashboard.putNumber("Encoder Speed",talon.getSpeed() * 0.60);
//        	
//        	SmartDashboard.putNumber("Set P", talon.getP());
//        	SmartDashboard.putNumber("Set I", talon.getI());
//        	SmartDashboard.putNumber("Set D", talon.getD());
//        	
//        	SmartDashboard.putNumber("Motor Voltage", talon.getOutputVoltage());
//        	
//        }
//    }
//
//    /**
//     * Runs during test mode
//     */
//    public void test() {
//    }
//}