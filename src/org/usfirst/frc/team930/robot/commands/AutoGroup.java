package org.usfirst.frc.team930.robot.commands;

import org.usfirst.frc.team930.robot.OI;
import org.usfirst.frc.team930.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoGroup extends CommandGroup {
	
	private final double SPEED_1 = .4; //Speed of moving forward to totes
	private final double ANGLE_1 = 0; //Angle of moving forward to totes
	private final double TIME_1 = 1.5; //TIme in seconds for moving forward to totes
	
	private final double SPEED_2 = -.25; //Speed of moving backwards away from totes a little
	private final double ANGLE_2 = 0; //Angle of moving backwards away from totes a little
	private final double TIME_2 = .5; //TIme in seconds for moving backwards away from totes a little
	
	private final double SPEED_3 = -.5; //Speed of moving backwards away from totes a lot
	private final double ANGLE_3 = 0; //Angle of moving backwards away from totes a lot
	private final double TIME_3 = 3.0; //TIme in seconds for moving backwards away from totes a lot
    
    public  AutoGroup() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    	
    	//Open claws and Lift arm to grab height simultaneously
    	addSequential(new ArmForTime(-40,2));

    	addSequential(new CloseLeftClaw());
    	addSequential(new OpenRightClaw());
    	//addSequential(new SetHeight(OI.armPreset_0));
    	//addSequential(new ArmForTime(-30,1,false));
    	addSequential (new Delay(.5));
    	//addSequential(new)
    	
    	//Drive forward towards totes
    	addSequential(new DriveForTime(ANGLE_1,SPEED_1,TIME_1));
    	
    	addSequential (new Delay(1));
    	
    	//Close both claws simultaneously
    	addSequential(new OpenLeftClaw());
    	addSequential(new CloseRightClaw());
    	
    	addSequential (new Delay(.5));
    	
    	addSequential(new DriveForTime(0,-0.7,0.5));
    	
    	addSequential(new ArmForTime(-10,2));

   
    	
 
    	
    	//Backup slightly with totes
    	//addSequential(new DriveForTime(ANGLE_2,SPEED_2,TIME_2));
    	
    	//Raise the totes up
    	//addSequential(new SetHeight(OI.armPreset_1));
    	//addSequential(new ArmForTime(-30,1,true));
    	
    	//Finish driving backwards into auto zone
    	addSequential(new DriveForTime(ANGLE_3,SPEED_3,TIME_3));

    	


    }
}
