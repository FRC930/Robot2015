package Helpers;

import org.usfirst.frc.team930.robot.Robot;
import org.usfirst.frc.team930.robot.subsystems.SwerveDrive;
import org.usfirst.frc.team930.robot.subsystems.SwerveDrive.Outputs;

public class RampRate {
	public SwerveDrive swerve;

	double[] oldValues = new double[4];
	
	
	
	public double[] limit(double[] newValues, double rampRate){
		double difference1 = newValues[0] - oldValues[0];
		double difference2 = newValues[1] - oldValues[1];
		double difference3 = newValues[2] - oldValues[2];
		double difference4 = newValues[3] - oldValues[3];
		if ((difference1 > rampRate) || (difference2 > rampRate) || (difference3 > rampRate) || (difference4 > rampRate)){
			double biggest;
			if ((difference1 > difference2) && (difference1 > difference3) && (difference1 > difference4)){
				biggest = newValues[0];
			}else if ((difference2 > difference1) && (difference2 > difference3) && (difference2 > difference4)){
				biggest = newValues[1];
			}else if ((difference3 > difference1) && (difference3 > difference2) && (difference3 > difference4)){
				biggest = newValues[2];
			}else biggest = newValues[3];
			
			double ratio1 = newValues[0]/biggest;
			double ratio2 = newValues[1]/biggest;
			double ratio3 = newValues[2]/biggest;
			double ratio4 = newValues[3]/biggest;
			
			newValues[0] = rampRate * ratio1;
			newValues[1] = rampRate * ratio2;
			newValues[2] = rampRate * ratio3;
			newValues[3] = rampRate * ratio4;
			
		}
		
		

		return newValues;
	}
}
