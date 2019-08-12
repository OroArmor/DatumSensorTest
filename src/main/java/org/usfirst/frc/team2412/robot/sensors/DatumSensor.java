package org.usfirst.frc.team2412.robot.sensors;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import edu.wpi.first.wpilibj.SerialPort;


public class DatumSensor {

	byte[] inBuffer = new byte[8];
	SerialPort port;
	String name;

	public DatumSensor(String name, SerialPort.Port portName) {
		this.name = name;
		port = new SerialPort(921600, portName);
	}

	public JSONArray[] readSensor(String... values) {

		String response = port.readString();

		JSONArray[] JSONValues = new JSONArray[values.length + 1];
		
		if (response != null && response.length() > 1) {

			System.out.println(response);
			try {
				JSONObject responseObject = new JSONObject(response);

				JSONObject sensorObject = responseObject.getJSONObject(name);
				JSONArray timeArray = sensorObject.getJSONArray("time");

				JSONValues[0] = timeArray;
				int i = 0;
				for (String value : values) {
					JSONValues[++i] = sensorObject.getJSONArray(value);

				}
			} catch (JSONException ex) {
				ex.printStackTrace();
			}
		}
		return JSONValues;

	}

}
