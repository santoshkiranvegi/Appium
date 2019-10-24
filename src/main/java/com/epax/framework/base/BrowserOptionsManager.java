/**
 * 
 */
package com.epax.framework.base;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.epax.framework.utilities.DeviceConfigReader;
/**
 * @author qa
 *
 */
public class BrowserOptionsManager {

	 //Get Chrome Options
    public static ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--ignore-certificate-errors");
        options.addArguments("--disable-popup-blocking");
        //options.addArguments("--incognito");
        return options;
    }
 
    //Get Firefox Options
    public static FirefoxOptions getFirefoxOptions () {
        FirefoxOptions options = new FirefoxOptions();
        FirefoxProfile profile = new FirefoxProfile();
        //Accept Untrusted Certificates
        profile.setAcceptUntrustedCertificates(true);
        profile.setAssumeUntrustedCertificateIssuer(false);
        //Use No Proxy Settings
        profile.setPreference("network.proxy.type", 0);
        profile.setPreference("capability.policy.default.Window.frameElement.get","allAccess");
        //Set Firefox profile to capabilities
        options.setCapability(FirefoxDriver.PROFILE, profile);
        return options;
    }

    //Get Edge Options
    public static EdgeOptions getEdgeOptions () {
        EdgeOptions options = new EdgeOptions();
        return options;
    }

    public static DesiredCapabilities getDeviceCapabilities(String device) {
    	DesiredCapabilities capabilities=new DesiredCapabilities();
		try {
			JSONObject jObjDevicecaps = DeviceConfigReader.getDeviceConfiguration(device);
			for (Iterator capItr = jObjDevicecaps.keySet().iterator(); capItr.hasNext();) {
				String capValue = (String) capItr.next();
				System.out.println(capValue + "  ---- " + jObjDevicecaps.get(capValue));
				capabilities.setCapability(capValue,jObjDevicecaps.get(capValue).toString());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return capabilities;
    }
}
