package com.uttara.in;

import java.time.LocalDate;
import java.util.Objects;

public class TaskBean   {
	private String taskName;
	private String taskDescription;
	private String tags;
	private LocalDate plannedDate;
	private int priority;

	public TaskBean() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TaskBean(String taskName, String taskDescription, String tags, LocalDate plannedDate, int priority) {
		super();
		this.taskName = taskName;
		this.taskDescription = taskDescription;
		this.tags = tags;
		this.plannedDate = plannedDate;
		this.priority = priority;
	}

	@Override
	public String toString() {
		return "TakBean [taskName=" + taskName + ", taskDescription=" + taskDescription + ", tags=" + tags
				+ ", plannedDate=" + plannedDate + ", priority=" + priority + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(plannedDate, priority, tags, taskDescription, taskName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TaskBean other = (TaskBean) obj;
		return Objects.equals(plannedDate, other.plannedDate) && priority == other.priority
				&& Objects.equals(tags, other.tags) && Objects.equals(taskDescription, other.taskDescription)
				&& Objects.equals(taskName, other.taskName);
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getTaskDescription() {
		return taskDescription;
	}

	public void setTaskDescription(String taskDescription) {
		this.taskDescription = taskDescription;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public LocalDate getPlannedDate() {
		return plannedDate;
	}

	public void setPlannedDate(LocalDate plannedDate) {
		this.plannedDate = plannedDate;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

}
