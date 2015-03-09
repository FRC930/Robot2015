package org.usfirst.frc.team930.robot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class BoxCar {
	double[] boxCar;
	int boxAmount;
	double inverseBoxAmount;
	private static final double[] array = {0.0155,0.0371,0.0925,0.1564,0.1985,0.1985,0.1564,0.0925,0.0371,0.0155};

	public BoxCar(int length) {
		boxAmount = length;		
		boxCar = new double[length];		
		inverseBoxAmount = 1.0/boxAmount;

	}

	public double calculate(double speed) {
		double sum = 0.0;
		synchronized(boxCar) {
			for (int i = boxAmount-1; i > 0; i--){
				boxCar[i]=boxCar[i-1];
			}
			boxCar[0] = speed;
			
			for(int i = 0; i  <= (boxAmount-1); i++){
				sum+=/*array[i]**/boxCar[i];
			}

		}
		SmartDashboard.putNumber("boxcar length ", boxAmount);
		SmartDashboard.putNumber("boxcar sum ", speed);
		return sum*inverseBoxAmount;
	}


	public void reset() {

	}

}
