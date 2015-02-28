package org.usfirst.frc.team930.robot;

import java.util.ArrayList;

public class BoxCar {
	public ArrayList<Double> boxCar;
	int boxAmount;

	public BoxCar() {
		boxCar = new ArrayList<Double>();
		boxAmount = 5;

		for (int x = 0; x < boxAmount; x++) {
			boxCar.add(0.0);
		}
	}

	public double calc(double speed) {
		boxCar.add(0, speed);
		boxCar.remove(boxCar.size() - 1);
		double sum = 0;
		for (int i = 0; i < boxCar.size(); i++) {
			sum += boxCar.get(i);
		}
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
