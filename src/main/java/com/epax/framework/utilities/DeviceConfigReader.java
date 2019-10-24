package com.epax.framework.utilities;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class DeviceConfigReader {
	public static void main(String[] args) throws Throwable {
		JSONObject jObjDevicecaps = getDeviceConfiguration("IPhone");
		for (Iterator capItr = jObjDevicecaps.keySet().iterator(); capItr.hasNext();) {
			String capValue = (String) capItr.next();
			System.out.println(capValue + "  ---- " + jObjDevicecaps.get(capValue));
		} 

	}
	/**
	 * @description : Retrieve the device configuration from json file
	 * @param device
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ParseException
	 */
	public static JSONObject getDeviceConfiguration(String device) throws FileNotFoundException, IOException, ParseException {
		JSONParser parser = new JSONParser();
		JSONObject jObjDevicecaps;
		Object devicesMap = parser.parse(new FileReader("TestDevicesConfiguration.json"));
		JSONObject jsonObject = (JSONObject) devicesMap;
		for(Iterator iterator = jsonObject.keySet().iterator(); iterator.hasNext();) {
			String deviceName = (String) iterator.next();
			if (device.toLowerCase().equals(deviceName.toLowerCase())) {
				System.out.println("Key Value is " + deviceName);
				System.out.println(jsonObject.get(deviceName));
				Object deviceCaps = jsonObject.get(deviceName);
				jObjDevicecaps = (JSONObject) deviceCaps;
				return jObjDevicecaps;
			}
		}	System.out.println("End of JSON Reading");
		return jsonObject;
	}
}

