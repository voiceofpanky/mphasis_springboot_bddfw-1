package com.mphasis.qe.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Generic {

	public String getChromeVersion() {

		String chromeVersion = null;
		String[] secondLevelsplitChromeVersion = null;

		try {

			Process process = Runtime.getRuntime().exec("Test.bat");
			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String line;

			while ((line = reader.readLine()) != null) {
				if (line.startsWith("Version"))

				{
					String[] splitChromeVer = line.split("=");
					chromeVersion = splitChromeVer[1];
					secondLevelsplitChromeVersion = chromeVersion.split("[.]");

				}
			}
			reader.close();
		}

		catch (Exception e) {
			e.printStackTrace();
		}

		return secondLevelsplitChromeVersion[0];

	}

	public String getDriverVersion(String splitChromeVersion) {

		String proxyDetailsWFG = "";

		String driverVersion = null;
		ArrayList<String> alDriverVersions = new ArrayList<String>();
		alDriverVersions = (ArrayList<String>) WebDriverManager.chromedriver().proxy(proxyDetailsWFG).getVersions();
		for (String version : alDriverVersions) {

			if (version.startsWith(splitChromeVersion)) {
				driverVersion = version;
				break;

			}

		}

		return driverVersion;

	}

}
