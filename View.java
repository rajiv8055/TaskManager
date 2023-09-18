package com.uttara.in;

import java.time.LocalDate;
import java.util.Scanner;

public class View {

	public static void main(String[] args) {
		Scanner sc1 = new Scanner(System.in);
		Scanner sc2 = new Scanner(System.in);
		try {
			//Model.loadCategories();
			Logger logger = Logger.getInstance();
			logger.log("***** Starting Task Manager *****");
			int choice1 = 0, choice2 = 0, choice3 = 0, choice4=0, priority, newPriority;
			String categoryName, taskName, taskDescription, taskTags, date;
			String newTaskName, newTaskDescription, newTaskTags;
			String input1 = null, input2 = null, input3 = null, input4 = null, input5 = null;
			System.out.println("--*--*=> Welcome to Task Manager <=*--*--");
			boolean b = true;
			while (choice1 != 6) {
				outer: while (b) {
					System.out.println("PRESS 1 TO CREATE CATEGORY");
					System.out.println("PRESS 2 TO LOAD   CATEGORY");
					System.out.println("PRESS 3 TO LIST   CATEGORY");
					System.out.println("PRESS 4 TO SEARCH CATEGORY");
					System.out.println("PRESS 5 TO REMOVE CATEGORY");
					System.out.println("PRESS 6 TO EXIT");
					input1 = sc1.nextLine();
					while (!TaskUtil.validateIntInput(input1)) {
						System.out.println("Invalid input please enter valid input");
						continue outer;
					}
					b = false;
				}
				b = true;
				choice1 = Integer.parseInt(input1);
				switch (choice1) {

				case 1:
					logger.log("----Creating category----");
					System.out.println("Enter category name\n[Name should be one word, starts with letter and without any special character]");
					categoryName = sc2.nextLine();
					while (!TaskUtil.validateName(categoryName)) {
						System.out.println("Category must be single word,starts with letter");
						System.out.println("alphanumeric with no special character");
						System.out.println("Please enter another category name");
						categoryName = sc2.nextLine();
					}
					if (Model.isTheCategoryExist(categoryName)) {
						logger.log("----Category creation failed----");
						System.out.println("Category already exists...please try again another category name");
						System.out.println("Check list category once to know what are the categories already exists");
					} else {
						Categories category = new Categories(categoryName);
						Model.getCategoryList().add(category);
						Model.createCategory(categoryName);
						logger.log("----Category Created Successfully----");
						System.out.println("Category created successfully");
						choice2=0;
						while (choice2 != 6) {
							b = true;
							outer: while (b) {
								System.out.println("PRESS 1 TO ADD TASK");
								System.out.println("PRESS 2 TO UPDATE TASK");
								System.out.println("PRESS 3 TO REMOVE TASK");
								System.out.println("PRESS 4 TO LIST TASK");
								System.out.println("PRESS 5 TO SEARCH");
								System.out.println("PRESS 6 TO GO BACK");
								input2 = sc1.nextLine();
								while (!TaskUtil.validateIntInput(input2)) {
									System.out.println("invalid input please enter valid input");
									continue outer;
								}
								b = false;
							}
							b = true;

							choice2 = Integer.parseInt(input2);
							switch (choice2) {
							case 1:
								logger.log("----Creating task----");
								b = true;
								System.out.println("Enter task name");
								taskName = sc2.nextLine();
								outer: while (b) {
									if (!Model.checkIfTaskExists(category, taskName)) {
										while (!TaskUtil.validateName(taskName)) {
											System.out.println("Task must be single word,starts with letter");
											System.out.println("Alphanumeric with no special character");
											System.out.println("Enter another task name");
											taskName = sc2.nextLine();
											continue outer;
										}
										b = false;
									} else {
										System.out.println("Aask already exists Enter another task name");
										taskName = sc2.nextLine();
									}

								}
								System.out.println("Enter task description");
								taskDescription = sc2.nextLine();
								while (!TaskUtil.checkIfDescriptionOrTagsAreValid(taskDescription)) {
									System.out.println("Description should not be empty give proper description");
									taskDescription = sc2.nextLine();
								}
								System.out.println(
										"Enter the tags of the task...comma seperated[ex:daily,morning,etc....]");
								taskTags = sc2.nextLine();
								while (!TaskUtil.checkIfDescriptionOrTagsAreValid(taskTags)) {
									System.out.println("Tags should not be empty give proper description");
									taskTags = sc2.nextLine();
								}

								System.out.println("Enter the planned date the task to be completed");
								System.out.println("Enter the date like this [yyyy-MM-dd]");
								date = sc2.nextLine();
								while (!TaskUtil.validateDate(date)) {
									System.out.println("Invalid Date");
									System.out.println("Enter the date like this [yyyy-MM-dd]");
									date = sc2.nextLine();
								}
								LocalDate d = LocalDate.parse(date);

								b = true;

								outer: while (b) {
									System.out.println("Enter Priority of the task");
									System.out.println("ENTER 10 FOR HIGH PRIORITY");
									System.out.println("ENTER 5 FOR MEDIUM PRIORITY");
									System.out.println("ENTER 1 FOR LOW PRIORITY");
									input3 = sc1.nextLine();
									if (TaskUtil.validateIntInput(input3)) {
										priority = Integer.parseInt(input3);
										while (!TaskUtil.validatePriority(priority)) {
											System.out.println("Please enter valid priority like 1 or 5 or 10");
											continue outer;
										}
										b = false;
									} else {
										System.out.println("Please enter valid priority like 1 or 5 or 10");
										continue outer;
									}
								}
								priority = Integer.parseInt(input3);
								b = true;
								TaskBean task = new TaskBean(taskName, taskDescription, taskTags, d, priority);
								category.getTasks().add(task);
								logger.log("----Task Added Successfully----");
								System.out.println("Task added successfully");

								break;
							case 2:
								if (category.getTasks().size() != 0) {
									System.out.print("[ ");
									for(TaskBean tasks: category.getTasks()){
										System.out.print(tasks.getTaskName()+", ");
									}
									System.out.println("]");
									System.out.println("Enter the task to updated which are shown in above list");
									taskName = sc2.nextLine();
									if (Model.checkIfTaskExists(category, taskName)) {
										TaskBean taskToBeUpdated = Model.getTask(category, taskName);
										choice3=0;
										while (choice3 != 6) {
											logger.log("----Updating task----");
											System.out.println("PRESS 1 TO UPDATE NAME");
											System.out.println("PRESS 2 TO UPDATE DESCRIPTION");
											System.out.println("PRESS 3 TO UPDATE TAGS");
											System.out.println("PRESS 4 TO UPDATE DATE");
											System.out.println("PRESS 5 to UPDATE PRIORITY");
											System.out.println("PRESS 6 GO BACK");
											choice3 = sc1.nextInt();
											switch (choice3) {
											case 1:
												logger.log("----Updating task name----");
												b = true;
												System.out.println("Enter new name for the task");
												newTaskName = sc2.nextLine();
												outer: while (b) {
													if (!Model.checkIfTaskExists(category, newTaskName)) {
														while (!TaskUtil.validateName(newTaskName)) {
															System.out.println(
																	"Task must be single word,starts with letter");
															System.out
																	.println("alphanumeric with no special character");
															System.out.println("Enter another task name");
															newTaskName = sc2.nextLine();
															continue outer;
														}
														taskToBeUpdated.setTaskName(newTaskName);
														logger.log("----Task name updated successfully----");
														System.out.println("Task Name updated successfully");
														b = false;
													} else {
														logger.log("----Updating task name failed----");
														System.out
																.println("Task already exists Enter another task name");
														taskName = sc2.nextLine();
													}

												}

												break;
											case 2:
												
												logger.log("----Updating task description----");
												System.out.println("Enter new description of the task");
												newTaskDescription = sc2.nextLine();
												while (!TaskUtil.checkIfDescriptionOrTagsAreValid(newTaskDescription)) {
													logger.log("----Updating task Description failed----");
													System.out.println(
															"Description should not be empty give proper description");
													newTaskDescription = sc2.nextLine();
												}
												taskToBeUpdated.setTaskDescription(newTaskDescription);
												logger.log("----Task Description updated successfully----");
												System.out.println("Task Description updated successfully");
												break;
											case 3:
												logger.log("----Updating task tags----");
												System.out.println(
														"Enter the new tags of the task...comma seperated[ex:daily,morning,etc....]");
												newTaskTags = sc2.nextLine();
												while (!TaskUtil.checkIfDescriptionOrTagsAreValid(newTaskTags)) {
													logger.log("----Updating task tags failed----");
													System.out.println(
															"Tags should not be empty give proper description");
													newTaskTags = sc2.nextLine();
												}
												taskToBeUpdated.setTags(newTaskTags);
												logger.log("----Task tags updated successfully----");
												System.out.println("Task tags updated successfully");
												break;
											case 4:
												logger.log("----Updating task planned planned----");
												System.out
														.println("Enter the new planned date the task to be completed");
												System.out.println("enter the date like this [yyyy-MM-dd]");
												date = sc2.nextLine();
												while (!TaskUtil.validateDate(date)) {
													logger.log("----Updating task new planned date failed----");
													System.out.println("Invalid Date");
													System.out.println("enter the date like this [yyyy-MM-dd]");
													date = sc2.nextLine();
												}
												LocalDate newDate = LocalDate.parse(date);
												taskToBeUpdated.setPlannedDate(newDate);
												logger.log("----Task new planned date updated successfully----");
												System.out.println("Tasks new planned date updated successfully");
												break;
											case 5:
												b = true;
												logger.log("----Updating task Priority----");
												outer: while (b) {
													System.out.println("Enter new Priority of the task");
													System.out.println("ENTER 10 FOR HIGH PRIORITY");
													System.out.println("ENTER 5 FOR MEDIUM PRIORITY");
													System.out.println("ENTER 1 FOR LOW PRIORITY");
													input4 = sc1.nextLine();
													if (TaskUtil.validateIntInput(input4)) {
														newPriority = Integer.parseInt(input4);
														while (!TaskUtil.validatePriority(newPriority)) {
															logger.log("----Updating task Priority failed----");
															System.out.println(
																	"Please enter valid priority like 1 or 5 or 10");
															continue outer;
														}
														b = false;
													} else {
														System.out.println(
																"Please enter valid priority like 1 or 5 or 10");
														continue outer;
													}
												}
												newPriority = Integer.parseInt(input4);
												taskToBeUpdated.setPriority(newPriority);
												logger.log("----Task Priority updated successfully----");
												System.out.println("Task Priority updated successfully");
												b = true;
												break;
											case 6:
												System.out.println("<--<--Going Back");
												break;
											default:
												System.out.println("Option not supported yet");
												break;
											}
										}

									} else {
										logger.log("----Category loading failed----");
										System.out.println("There is no such task present in the category");
									}

								} else {
									logger.log("----Category loading failed----");
									System.out.println("There is no task present in the category to update");
								}

								break;
							case 3:
								if (category.getTasks().size() != 0) {
									System.out.print("[ ");
									for(TaskBean tasks: category.getTasks()){
										System.out.print(tasks.getTaskName()+", ");
									}
									System.out.println("]");
									logger.log("----Deleting task----");
									System.out.println("Enter task to be removed shown in above task list");
									taskName = sc2.nextLine();
									if (Model.checkIfTaskExists(category, taskName)) {
										Model.deleteTask(category, taskName);
										logger.log("----Task deleted successfully----");
										System.out.println("Task " + taskName + " deleted successfully");
									} else {
										logger.log("----Task Deletion failed----");
										System.out.println("Task not exists");
									}

								} else {
									logger.log("----Task Deletion failed----");
									System.out.println("No tasks created yet add new task");
								}

								break;
							case 4:
								if (category.getTasks().size() != 0) {
									b = true;
									outer: while (b) {
										System.out.println("PRESS 1 to list tasks by alphabetical order of task name");
										System.out.println("PRESS 2 to list tasks by due date");
										System.out.println("PRESS 3 to list tasks by priority");
										input5 = sc1.nextLine();
										while (!TaskUtil.validateIntInput(input5)) {
											System.out.println("Please enter valid input");
											continue outer;

										}
										choice4 = Integer.parseInt(input5);
										switch (choice4) {
										case 1:
											Model.listTasksByNameInAlphabeticalOrder(category);
											break;
										case 2:
											Model.listTasksByDueDate(category);
											break;
										case 3:
											Model.listTasksByPriority(category);
											break;
										default:
											System.out.println("Option not supported yet");
											break;
										}
										b = false;

									}
								} else {
									System.out.println("No tasks exists in the category");
								}
								b = true;


								break;
							case 5:

								System.out.println("Enter a String to search");
								String search = sc2.nextLine();
								while (search == null || search.trim().equals("")) {
									System.out.println(
											"Element to be searched should not be empty enter something to search");
									search = sc2.nextLine();
								}
								Model.searchString(search);
								break;

							case 6:
								System.out.println("<---<---going back");
								break;
							default:
								System.out.println("Option not supported yet");
								break;
							}
						}
					}
					break;
				case 2:
					System.out.println(Model.getCategoryList());
					System.out.println("Enter category to update shown in above list");
					logger.log("----Updating Category----");
					categoryName = sc2.nextLine();
					if (Model.isTheCategoryExist(categoryName)) {
						Categories categoryToBeLoaded = Model.getCategory(categoryName);
						choice2=0;
						while (choice2 != 6) {
							b = true;

							outer: while (b) {
								System.out.println("PRESS 1 TO ADD TASK");
								System.out.println("press 2 TO UPDATE TASK");
								System.out.println("press 3 TO REMOVE TASK");
								System.out.println("press 4 TO LIST TASK");
								System.out.println("press 5 TO SEARCH");
								System.out.println("press 6 TO GO BACK");
								input2 = sc1.nextLine();
								while (!TaskUtil.validateIntInput(input2)) {
									System.out.println("invalid input please enter valid input");
									continue outer;
								}
								b = false;
							}
							b = true;

							choice2 = Integer.parseInt(input2);
							switch (choice2) {
							case 1:
								b = true;
								logger.log("----Adding task----");
								System.out.println("Enter task name");
								taskName = sc2.nextLine();
								outer: while (b) {
									if (!Model.checkIfTaskExists(categoryToBeLoaded, taskName)) {
										while (!TaskUtil.validateName(taskName)) {
											System.out.println("Task must be single word,starts with letter");
											System.out.println("alphanumeric with no special character");
											System.out.println("Enter another task name");
											taskName = sc2.nextLine();
											continue outer;
										}
										b = false;
									} else {
										System.out.println("Task already exists Enter another task name");
										taskName = sc2.nextLine();
									}

								}
								System.out.println("Enter task description");
								taskDescription = sc2.nextLine();
								while (!TaskUtil.checkIfDescriptionOrTagsAreValid(taskDescription)) {
									System.out.println("Description should not be empty give proper description");
									taskDescription = sc2.nextLine();
								}
								System.out.println(
										"Enter the tags of the task...comma seperated[ex:daily,morning,etc....]");
								taskTags = sc2.nextLine();
								while (!TaskUtil.checkIfDescriptionOrTagsAreValid(taskTags)) {
									System.out.println("Tags should not be empty give proper description");
									taskTags = sc2.nextLine();
								}

								System.out.println("Enter the planned date the task to be completed");
								System.out.println("Enter the date like this [yyyy-MM-dd]");
								date = sc2.nextLine();
								while (!TaskUtil.validateDate(date)) {
									System.out.println("Invalid Date");
									System.out.println("Enter the date like this [yyyy-MM-dd]");
									date = sc2.nextLine();
								}
								LocalDate d = LocalDate.parse(date);

								b = true;

								outer: while (b) {
									System.out.println("Enter Priority of the task");
									System.out.println("ENTER 10 FOR HIGH PRIORITY");
									System.out.println("ENTER 5 FOR MEDIUM PRIORITY");
									System.out.println("ENTER 1 FOR LOW PRIORITY");
									input3 = sc1.nextLine();
									if (TaskUtil.validateIntInput(input3)) {
										priority = Integer.parseInt(input3);
										while (!TaskUtil.validatePriority(priority)) {
											System.out.println("Please enter valid priority like 1 or 5 or 10");
											continue outer;
										}
										b = false;
									} else {
										System.out.println("Please enter valid priority like 1 or 5 or 10");
										continue outer;
									}
								}
								priority = Integer.parseInt(input3);
								b = true;
								TaskBean task = new TaskBean(taskName, taskDescription, taskTags, d, priority);
								categoryToBeLoaded.getTasks().add(task);
								logger.log("----Task Added----");
								System.out.println("Task added successfully");

								break;
							case 2:
								if (categoryToBeLoaded.getTasks().size() != 0) {
									System.out.print("[ ");
									for(TaskBean tasks: categoryToBeLoaded.getTasks()){
										System.out.print(tasks.getTaskName()+", ");
									}
									System.out.println("]");

									System.out.println("Enter the task to updated which are shown in above list");
									taskName = sc2.nextLine();
									if (Model.checkIfTaskExists(categoryToBeLoaded, taskName)) {
										TaskBean taskToBeUpdated = Model.getTask(categoryToBeLoaded, taskName);
										choice3=0;
										while (choice3 != 6) {
											logger.log("----Updating task----");
											System.out.println("PRESS 1 TO UPDATE NAME");
											System.out.println("PRESS 2 TO UPDATE DESCRIPTION");
											System.out.println("PRESS 3 TO UPDATE TAGS");
											System.out.println("PRESS 4 TO UPDATE DATE");
											System.out.println("PRESS 5 TO UPDATE PRIORITY");
											System.out.println("PRESS 6 GO BACK");
											choice3 = sc1.nextInt();
											switch (choice3) {
											case 1:
												b = true;
												logger.log("----updating task name----");
												System.out.println("Enter new name for the task");
												newTaskName = sc2.nextLine();
												outer: while (b) {
													if (!Model.checkIfTaskExists(categoryToBeLoaded, newTaskName)) {
														while (!TaskUtil.validateName(newTaskName)) {
															logger.log("----Updating task name failed----");
															System.out.println(
																	"Task must be single word,starts with letter");
															System.out
																	.println("alphanumeric with no special character");
															System.out.println("Enter another task name");
															newTaskName = sc2.nextLine();
															continue outer;
														}
														taskToBeUpdated.setTaskName(newTaskName);
														logger.log("----task name updated----");
														System.out.println("Task Name updated successfully");
														b = false;
													} else {
														logger.log("----Updating task name failed----");
														System.out
																.println("Task already exists Enter another task name");
														taskName = sc2.nextLine();
													}

												}

												break;
											case 2:
												logger.log("----Updating task description----");
												System.out.println("Enter new description of the task");
												newTaskDescription = sc2.nextLine();
												while (!TaskUtil.checkIfDescriptionOrTagsAreValid(newTaskDescription)) {
													logger.log("----Updating task description failed----");
													System.out.println(
															"Description should not be empty give proper description");
													newTaskDescription = sc2.nextLine();
												}
												taskToBeUpdated.setTaskDescription(newTaskDescription);
												logger.log("----Task Description updated----");
												System.out.println("Task Description updated successfully");
												break;
											case 3:
												logger.log("----Updating task tags----");
												System.out.println(
														"Enter the new tags of the task...comma seperated[ex:daily,morning,etc....]");
												newTaskTags = sc2.nextLine();
												while (!TaskUtil.checkIfDescriptionOrTagsAreValid(newTaskTags)) {
													logger.log("----Updating task tags failed----");
													System.out.println(
															"Tags should not be empty give proper description");
													newTaskTags = sc2.nextLine();
												}
												taskToBeUpdated.setTags(newTaskTags);
												logger.log("----Task tags updated----");
												System.out.println("Task tags updated successfully");
												break;
											case 4:
												logger.log("----Updating task completion date----");
												System.out
														.println("Enter the new planned date the task to be completed");
												System.out.println("Enter the date like this [yyyy-MM-dd]");
												date = sc2.nextLine();
												while (!TaskUtil.validateDate(date)) {
													logger.log("----Task date update failed----");
													System.out.println("Invalid Date");
													System.out.println("Enter the date like this [yyyy-MM-dd]");
													date = sc2.nextLine();
												}
												LocalDate newDate = LocalDate.parse(date);
												taskToBeUpdated.setPlannedDate(newDate);
												logger.log("----Task new planned date updated----");
												System.out.println("Task new planned date updated successfully");
												break;
											case 5:
												b = true;
												logger.log("----Updating task priority----");
												outer: while (b) {
													System.out.println("Enter new Priority of the task");
													System.out.println("ENTER 10 FOR HIGH PRIORITY");
													System.out.println("ENTER 5 FOR MEDIUM PRIORITY");
													System.out.println("ENTER 1 FOR LOW PRIORITY");
													input4 = sc1.nextLine();
													if (TaskUtil.validateIntInput(input4)) {
														newPriority = Integer.parseInt(input4);
														while (!TaskUtil.validatePriority(newPriority)) {
															logger.log("----Updating task priority failed----");
															System.out.println(
																	"Please enter valid priority like 1 or 5 or 10");
															continue outer;
														}
														b = false;
													} else {
														logger.log("----Updating task priority failed----");
														System.out.println(
																"Please enter valid priority like 1 or 5 or 10");
														continue outer;
													}
												}
												newPriority = Integer.parseInt(input4);
												logger.log("----Task Priority Updated----");
												taskToBeUpdated.setPriority(newPriority);
												b = true;
												break;
											case 6:
												System.out.println("<--<--Going Back");
												break;
											default:
												System.out.println("Option not supported yet");
												break;
											}
										}

									} else {
										logger.log("----Categoty loading failed----");
										System.out.println("There is no such task present in the category");
									}

								} else {
									logger.log("----Categoty loading failed----");
									System.out.println("There is no task present in the category to update");
								}

								break;
							case 3:
								if (categoryToBeLoaded.getTasks().size() != 0) {
									System.out.print("[ ");
									for(TaskBean tasks: categoryToBeLoaded.getTasks()){
										System.out.print(tasks.getTaskName()+", ");
									}
									System.out.println("]");
									logger.log("--#--Deleting task--#--");
									System.out.println("Enter task to be removed shown in above task list");
									taskName = sc2.nextLine();
									if (Model.checkIfTaskExists(categoryToBeLoaded, taskName)) {
										Model.deleteTask(categoryToBeLoaded, taskName);
										logger.log("----Task Deleted successfully----");
										System.out.println("Task " + taskName + " deleted successfully");
									} else {
										logger.log("----Task Deletion failed----");
										System.out.println("Task not exists");
									}

								} else {
									logger.log("----Task Deletion failed----");
									System.out.println("No tasks created yet add new task");
								}

								break;
							case 4:
								if (categoryToBeLoaded.getTasks().size() != 0) {
									b = true;
									outer: while (b) {
										System.out.println("PRESS 1 to list tasks by alphabetical order of task name");
										System.out.println("PRESS 2 to list tasks by due date");
										System.out.println("PRESS 3 to list tasks by priority");
										input5 = sc1.nextLine();
										while (!TaskUtil.validateIntInput(input5)) {
											System.out.println("Please enter valid input");
											continue outer;

										}
										choice4 = Integer.parseInt(input5);
										switch (choice4) {
										case 1:
											Model.listTasksByNameInAlphabeticalOrder(categoryToBeLoaded);
											break;
										case 2:
											Model.listTasksByDueDate(categoryToBeLoaded);
											break;
										case 3:
											Model.listTasksByPriority(categoryToBeLoaded);
											break;
										default:
											System.out.println("Option not supported yet");
											break;
										}
										b = false;

									}
								} else {
									System.out.println("No tasks exists in the category");
								}
								b = true;

								break;
							case 5:
								System.out.println("Enter a String to search");
								String search = sc2.nextLine();
								while (search == null || search.trim().equals("")) {
									System.out.println(
											"Element to be searched should not be empty enter something to search");
									search = sc2.nextLine();
								}
								Model.searchString(search);
								break;

							case 6:
								System.out.println("<---<---going back");
								break;
							}
						}
					} else {
						System.out.println("@ Category not exists");
					}
					break;
				case 3:
					if (Model.getCategoryList().size() != 0) {
						System.out.println("These are the categories exists");
						System.out.println(Model.getCategoryList());
					} else {
						System.out.println("No Category exists yet...add new categories");
					}
					break;
				case 4:
					if (Model.getCategoryList().size() != 0) {
						System.out.println("Enter category name");
						categoryName = sc2.nextLine();
						if (Model.isTheCategoryExist(categoryName)) {
							System.out.println("Category " + categoryName + " exists");
						} else {
							System.out.println("Category " + categoryName + " not exists");
						}
					} else {
						System.out.println("No Category exists yet...add new categories");
					}
					break;
				case 5:
					if (Model.getCategoryList().size() != 0) {
						logger.log("--#--Deleting Category--#--");
						System.out.println(Model.getCategoryList());
						System.out.println("Enter category name");
						categoryName = sc2.nextLine();
						if (Model.isTheCategoryExist(categoryName)) {
							Model.deleteCategory(categoryName);
							logger.log("----Category Deleted Successfully----");
							System.out.println("Category Deleted Successfully");
						} else {
							logger.log("----Category Deletion failed----");
							System.out.println("Category not exists");
						}
					} else {
						logger.log("----Category Deletion failed----");
						System.out.println("No Category exists yet...add new categories");
					}

					break;
				case 6:
					
					Model.write();
					logger.log("--*--*--*--Exiting Task Manager--*--*--*--");
					System.out.println("@ See you SOON"
							+ "--***--");
					System.exit(0);
					break;

				default:
					System.out.println("Option not supported yet");
					break;

				}
			}
		} catch (Throwable e) {
			e.printStackTrace();
		} finally {
			Model.write();
			sc1.close();
			sc2.close();
		}

	}
}