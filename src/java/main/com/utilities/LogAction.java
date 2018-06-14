package com.utilities;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class LogAction {

	Logger logger;

	Calendar cal = Calendar.getInstance();
	Date currentTime = cal.getTime();
	File logFile;
	String fileName;

	public LogAction(Class ClassName) {

		logger = Logger.getLogger(ClassName);

		//create log file
		fileName = "" + cal.getTime();
		String[] currDate = fileName.split(" ");
		fileName = currDate[2] + " " + currDate[1] + " " + currDate[5] + " " + currDate[3];
		
		fileName  = "Test Run - " + fileName.replaceAll(":",".")+  ".txt";

		logFile= new File(fileName);


	}

	public void writeLog(String type, String message){

		Calendar cal = Calendar.getInstance();
		Date currentTime = cal.getTime();

		switch(type)
		{

		case "ERROR" : 
			Logger.getRootLogger().setLevel(Level.ERROR);
			BasicConfigurator.configure();
			logger.error(type + " " + currentTime + ": " + message);
			writeToLogFile(type + " " + currentTime + ": " + message);
			break;

		case "INFO" : 
			Logger.getRootLogger().setLevel(Level.INFO);
			BasicConfigurator.configure();
			logger.info(type + " " + currentTime + ": " + message);
			writeToLogFile(type + " " + currentTime + ": " + message);
			break;

		case "PASS" : 
			Logger.getRootLogger().setLevel(Level.INFO);
			BasicConfigurator.configure();
			logger.info(type + " " + currentTime + ": " + message);
			writeToLogFile(type + " " + currentTime + ": " + message);
			break;

		case "FAIL" : 
			Logger.getRootLogger().setLevel(Level.ERROR);
			BasicConfigurator.configure();
			logger.error(type + " " + currentTime + ": " + message);
			writeToLogFile(type + " " + currentTime + ": " + message);
			writeToLogFile("Test step failed..");
			throw new TestFailedException("Test step failed..");

		default:
			Logger.getRootLogger().setLevel(Level.INFO);
			BasicConfigurator.configure();
			logger.info(type + " " + currentTime + ": " + message);
			writeToLogFile(type + " " + currentTime + ": " + message);
			break;
		}


	}


	public void writeToLogFile(String content){

		FileWriter fw = null;
		BufferedWriter bw = null;

		try {
			fw = new FileWriter("./logs/" + fileName, true);
			bw = new BufferedWriter(fw);
			bw.newLine();
			bw.write(content);
			bw.flush();
			fw.close();
			bw.close();
		}
		catch(IOException e) {

			System.err.println("Can't write to Log File, IOException occurred!!!!!!!!!!!!!!!!! \n");
			e.printStackTrace();


		}

	}



}
