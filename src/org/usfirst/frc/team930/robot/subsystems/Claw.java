//Sybsystem: Claw

package org.usfirst.frc.team930.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Claw extends Subsystem {
	CANTalon clawTalon1 = new CANTalon(1);
	CANTalon clawTalon2 = new CANTalon(2);
	DigitalInput limitSwitch1 = new DigitalInput(1);
	DigitalInput limitSwitch2 = new DigitalInput(2);

    public void initDefaultCommand() {

    }
    
    public void closeClaw(){
    	if (limitSwitch1.get() == false){
    		clawTalon1.set(1);
    		clawTalon2.set(1);
    		
    	}else{
    		clawTalon1.set(-1);
    		clawTalon2.set(-1);
    	}
    }
    
    public void openCLaw(){
    	if (limitSwitch2.get() == false){
    		clawTalon1.set(-1);
    		clawTalon2.set(1);
    		
    	}else{
    		clawTalon1.set(-1);
    		clawTalon2.set(-1);
    	}
    	
    }
}

