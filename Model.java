package com.uttara.in;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class Model implements Constants {
	private static List<Categories> categoryList = new ArrayList<Categories>();

	public static void loadCategories() {
		
		File[] categories = PATH.listFiles();
		BufferedReader reader = null;
		for (File file : categories) {
			try {
				reader = new BufferedReader(new FileReader(file));
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			String fileName = file.getName().substring(0, file.getName().lastIndexOf("."));
			Categories category = new Categories(fileName);
			categoryList.add(category);
			String line;
			try {
				while ((line = reader.readLine()) != null) {
					String[] s = line.split(":");
					if (s.length < 5) {
						break;
					}
					LocalDate d = LocalDate.parse(s[3]);
					int i = Integer.parseInt(s[4]);
					TaskBean t = new TaskBean(s[0], s[1], s[2], d, i);
					category.getTasks().add(t);

				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				if (reader != null) {
					try {
						reader.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	}

	public static List<Categories> getCategoryList() {
		return categoryList;
	}

	public static void setCategoryList(List<Categories> categoryList) {
		Model.categoryList = categoryList;
	}

	public static boolean isTheCategoryExist(String catName) {
		for (Categories categories : categoryList) {
			if (categories.getName().equalsIgnoreCase(catName)) {
				return true;
			}

		}
		return false;
	}

	public static boolean checkIfTaskExists(Categories category, String taskName) {
		if (category.getTasks().size() != 0) {
			for (TaskBean task : category.getTasks()) {
				if (task.getTaskName().equalsIgnoreCase(taskName)) {
					return true;
				}
			}
			return false;
		}
		return false;
	}

	public static void write() {

		for (Categories categories : categoryList) {
			createCategory(categories.getName());
			File f = new File(String.valueOf(PATH) + "\\" + categories.getName() + ".txt");
			if (f.exists()) {
				BufferedWriter writer = null;
				try {
					writer = new BufferedWriter(new FileWriter(f));
					for (TaskBean t : categories.getTasks()) {
						String s = t.getTaskName() + ":" + t.getTaskDescription() + ":" + t.getTags() + ":"
								+ t.getPlannedDate() + ":" + t.getPriority();
						try {
							writer.write(s);
							writer.newLine();
							writer.flush();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} finally {
					if (writer != null) {
						try {
							writer.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}

			}
		}
	}

	public static void createCategory(String categoryName) {
		File f = new File(String.valueOf(PATH) + "\\" + categoryName + ".txt");
		if (!f.exists()) {
			try {
				f.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void deleteCategory(String categoryName) {
		for (Categories categories : categoryList) {
			if (categories.getName().equalsIgnoreCase(categoryName)) {
				categoryList.remove(categories);
				break;
			}
		}
		File f = new File(Constants.PATH + "\\" + categoryName + ".txt");
		if (f.exists()) {

			f.delete();
			f.deleteOnExit();
		}
	}

	public static void deleteTask(Categories category, String taskName) {

		for (TaskBean task : category.getTasks()) {
			if (task.getTaskName().equals(taskName)) {
				category.getTasks().remove(task);
				break;
			}
		}
	}

	public static TaskBean getTask(Categories category, String taskName) {
		for (TaskBean task : category.getTasks()) {
			if (task.getTaskName().equals(taskName)) {
				return task;
			}
		}
		return null;
	}

	public static Categories getCategory(String categoryName) {
		for (Categories categories : categoryList) {
			if (categories.getName().equals(categoryName)) {
				return categories;
			}
		}
		return null;
	}

	public static void searchString(String toBeSearched) {
		int totalOccurance = 0;
		int occuranceInName = 0;
		int occuranceInDescription = 0;
		int occuranceInTags = 0;
		for (Categories categories : categoryList) {
			for (TaskBean task : categories.getTasks()) {
				if (task.getTaskName().contains(toBeSearched)) {
					totalOccurance++;
					occuranceInName++;
				}
				for (String string : task.getTaskDescription().split(" ")) {
					if (string.contains(toBeSearched)) {
						totalOccurance++;
						occuranceInDescription++;
					}
				}
				for (String string : task.getTags().split(",")) {
					if (string.contains(toBeSearched)) {
						totalOccurance++;
						occuranceInDescription++;
					}
				}
			}
		}
		System.out.println("Total no of occurance of " + toBeSearched + " is " + totalOccurance);
		System.out.println("Number of occurance of " + toBeSearched + " in name is " + occuranceInName);
		System.out
				.println("Number no of occurance of " + toBeSearched + " in description is " + occuranceInDescription);
		System.out.println("Number no of occurance of " + toBeSearched + " in tags is " + occuranceInTags);

	}

	public static void listTasksByNameInAlphabeticalOrder(Categories category) {
		System.out.println("Task list according to name");
		TaskNameComparator taskNameCompator = new TaskNameComparator();
		TreeSet<TaskBean> tasks = new TreeSet<TaskBean>(taskNameCompator);
		tasks.addAll(category.getTasks());
		for (TaskBean taskBean : tasks) {
			System.out.println(taskBean);
		}
	}

	public static void listTasksByDueDate(Categories category) {
		System.out.println("Task list according to due date");
		TaskDateComparator taskDateComparator = new TaskDateComparator();
		TreeSet<TaskBean> tasks = new TreeSet<TaskBean>(taskDateComparator);
		tasks.addAll(category.getTasks());
		for (TaskBean taskBean : tasks) {
			System.out.println(taskBean);
		}
	}

	public static void listTasksByPriority(Categories category) {
		System.out.println("Task list according to priority");
		TaskPriorityComparator TaskPriorityComparator = new TaskPriorityComparator();
		TreeSet<TaskBean> tasks = new TreeSet<TaskBean>(TaskPriorityComparator);
		tasks.addAll(category.getTasks());
		for (TaskBean taskBean : tasks) {
			System.out.println(taskBean);
		}
	}
}
