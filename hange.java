package com.uttara.in;

//import java.text.SimpleDateFormat;
import java.time.LocalDate;
//import java.util.Date;

public class hange {

	public static void main(String[] args) {
		LocalDate d=LocalDate.parse("0001-00-29");
		System.out.println(d);
//		SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
		System.out.println(Integer.parseInt("01")==01);
		
	}
	public static void updateTaskName(TaskBean task, String newTaskName) {
		task.setTaskName(newTaskName);
	}

	public static void updateTaskDescription(TaskBean task, String newTaskDescription) {
		task.setTaskDescription(newTaskDescription);
	}

	public static void updateTaskTags(TaskBean task, String newTaskTags) {
		task.setTags(newTaskTags);
	}

	public static void updateTaskCompletionDate(TaskBean task, LocalDate newDate) {
		task.setPlannedDate(newDate);
	}

	public static void updateTaskPriority(TaskBean task, int newPriority) {
		task.setPriority(newPriority);
	}

}
