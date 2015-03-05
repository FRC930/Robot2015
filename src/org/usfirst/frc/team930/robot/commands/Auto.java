package org.usfirst.frc.team930.robot.commands;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc.team930.robot.commands.*;

/* We'll make a bunch of lists for each individual command
 * and then load them into the program, run them, and load the next.  
 */

public class Auto extends CommandGroup {

	private static FileInputStream fis = null;
	private static DataInputStream dis = null;

	private static ArrayList<Double> command = new ArrayList<Double>();

	// STATIC BLOCK - copy paste this for your commands
	static { // command loader
		try { // try making file and data streams for a file
			fis = new FileInputStream("file.csv");
			dis = new DataInputStream(fis);

			try { // try reading and adding in all the doubles
				while (dis.available() > 0) {
					command.add(dis.readDouble());
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	// END STATIC BLOCK

	public Auto() {
		
	}
	
	private void drive(ArrayList<Double> command){
		// read through the thing here and send to drive command
		
	}
	
	private void rotate(ArrayList<Double> command){
		// d = delta theta * sqrt(L^2+W^2) / 2		
	}
}