package com.uttara.in;

import java.time.LocalDate;

public class TaskUtil implements Constants {
	public static boolean validateName(String name) {
		if (name == null) {
			return false;
		}
		if (name.trim().equals("")) {
			return false;
		}
		if (!Character.isLetter(name.charAt(0))) {
			return false;
		}
		if (name.split(" ").length > 1) {
			return false;
		}
		for (int i = 0; i < name.length(); i++) {
			char c = name.charAt(i);
			if (!(Character.isLetter(c) || Character.isDigit(c))) {
				return false;
			}
		}
		return true;
	}

	public static boolean validatePriority(int priority) {
		if (priority != PRIORITY1 && priority != PRIORITY2 && priority != PRIORITY3) {
			return false;
		}
		return true;
	}

	public static boolean checkIfDescriptionOrTagsAreValid(String descriptionOrTag) {
		if (descriptionOrTag == null || descriptionOrTag.trim().equals("")) {
			return false;
		}
		return true;

	}

	public static boolean validateDate(String date) {
		if (date.length() != 10) {
			return false;
		}
		if (date.contains("--") || date.startsWith("-") || date.endsWith("-")) {
			return false;
		}
		String[] s = date.split("-");
		if (s.length != 3) {
			return false;
		}
		if (s[0].length() != 4 || s[1].length() != 2 || s[2].length() != 2) {
			return false;
		}
		for (String string : s) {
			for (int i = 0; i < string.length(); i++) {
				if (!Character.isDigit(string.charAt(i))) {
					return false;
				}
			}
		}
		int i1 = Integer.parseInt(s[0]);
		int i2 = Integer.parseInt(s[1]);
		int i3 = Integer.parseInt(s[2]);
		if (i2 > 12 || i3 > 31|| i2 <1 || i3 < 1) {
			System.out.println("Invalid value for MonthOfYear(valid values 1-12)month should not be greater than 31");
			return false;
		} 
		if (i2 == 2 && ((isLeapyear(i1) && i3 > 29) || (!isLeapyear(i1) && i3 > 28))) {
			System.out.println("invalid date for the month february (1-29 for leap year)");
			return false;
		}
//		if ((i2 == 1 || i2 == 3 || i2 == 5 || i2 == 7 || i2 == 8 || i2 == 10 || i2 == 12) && i3 > 31) {
//			System.out.println("Date should be less than or equal to 31 for entered month");
//			return false;
//		}
		if((i2==4||i2==6||i2==9||i2==11)&&i3>30) {
			System.out.println("Date should be less than or equal to 30 for entered month");
			return false;
		}

		LocalDate d = LocalDate.parse(date);
		if (!d.isBefore(LocalDate.now())) {
			return true;
		}
		System.out.println("Entered date should be today i.e "+LocalDate.now()+" or more than this day ");
		return false;
	}

	public static boolean isLeapyear(int year) {
		if (year % 400 == 0 || year % 4 == 0 && year % 100 != 0) {
			return true;
		}
		return false;
	}
	public static boolean validateIntInput(String input) {
		if(input ==null || input.trim().equals("")) {
			return false;
		}
		for(int i=0;i<input.length();i++) {
			if(!Character.isDigit(input.charAt(i))) {
				return false;
			}
		}
		return true;
	}

}
