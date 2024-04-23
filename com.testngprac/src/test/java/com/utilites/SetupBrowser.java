package com.utilites;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class SetupBrowser extends BaseClass{

	public String getName() {
		String userdir=System.getProperty("user.dir");
		System.out.println(userdir+"abc");
		try {
		fileReader =new FileReader(userdir+"/src/test/resources/Browsers.properties");
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		properties=new Properties();
		try {
			properties.load(fileReader);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String Browser =properties.getProperty("Browser");
		System.out.println("Current browser is ="+" "+Browser);
		return Browser;
	}
}
