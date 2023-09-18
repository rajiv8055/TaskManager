package com.uttara.in;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class Logger {
	public static Logger logger = null;

	public static Logger getInstance() {
		if (logger == null) {
			synchronized (Logger.class) {
				if (logger == null) {// double check
					logger = new Logger();
				}
			}
		}
		return logger;
	}

	private Logger() {

	}

	public void log(String data) {
		new Thread(new Runnable() {

			public void run() {
				Date date = new Date();
				BufferedWriter writer = null;
				try {
					String message = date + ":" + data;
					writer = new BufferedWriter(new FileWriter("Ledger", true));
					writer.write(message);
					writer.newLine();

				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					if (writer != null) {
						try {
							writer.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			}

		}).start();

	}
}