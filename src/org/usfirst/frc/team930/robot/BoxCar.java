package org.usfirst.frc.team930.robot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class BoxCar {
	private List<Double> boxCar;
	int boxAmount;

	public BoxCar(int length) {
		boxCar = Collections.synchronizedList(new ArrayList<Double>());
		boxAmount = length;

		for (int x = 0; x < boxAmount; x++) {
			boxCar.add(0.0);
		}
	}

	public double calculate(double speed) {
		double sum = 0;
		synchronized(boxCar) {
			boxCar.add(0, speed);
			boxCar.remove(boxAmount);
			for (int i = 0; i < boxCar.size(); i++) {
//				System.out.println(boxCar.get(i));
				sum += boxCar.get(i);
			}
		}
		System.out.println("boxcar length " + boxCar.size());
		SmartDashboard.putNumber("boxcar length ", boxCar.size());
		return sum/boxAmount;
	}

	public double getAvg() {
		double sum = 0;
		for (int i =0; i < boxCar.size(); i++) {
			sum += boxCar.get(i);
		}
		return sum / boxAmount;
}

	public void reset() {

	}

}
