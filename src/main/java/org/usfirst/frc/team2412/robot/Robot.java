package org.usfirst.frc.team2412.robot;


import org.json.JSONArray;
import org.usfirst.frc.team2412.robot.sensors.DatumSensor;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.SerialPort.Port;

public class Robot extends TimedRobot {

	DatumSensor sensor;

	@Override
	public void robotInit() {
		System.out.println(
		"Starting test"
		);
		sensor = new DatumSensor("distance", Port.kUSB1);
	}

	
	@Override
	public void teleopPeriodic() {
		

		JSONArray[] values = sensor.readSensor("distance");


		for(JSONArray value: values){
			if(value != null){
				System.out.println(value.toString());
			}
		}
	
	}

}
