package com.rebel.alliance.qusarfireoperation.utilities;

import java.io.PrintWriter;
import java.io.StringWriter;

public class Utility {
	public static String getStackError(Exception e) {
		String response = e.getMessage() + " - ";
		StringWriter sw = new StringWriter();
		e.printStackTrace(new PrintWriter(sw));
		response += sw.toString();
		return response;
	}
}
