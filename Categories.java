package com.uttara.in;

import java.util.ArrayList;
import java.util.Objects;

public class Categories {
	private String categoryName;
	private ArrayList<TaskBean> tasks=new ArrayList<TaskBean>();
	public String getName() {
		return categoryName;
	}
	public void setName(String categoryName) {
		this.categoryName = categoryName;
	}
	public ArrayList<TaskBean> getTasks() {
		return tasks;
	}
	public void setTasks(ArrayList<TaskBean> tasks) {
		this.tasks = tasks;
	}
	public Categories(String categoryName) {
		super();
		this.categoryName = categoryName;
	}
	@Override
	public int hashCode() {
		return Objects.hash(categoryName, tasks);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Categories other = (Categories) obj;
		return Objects.equals(categoryName, other.categoryName) && Objects.equals(tasks, other.tasks);
	}
	@Override
	public String toString() {
		return  categoryName;
	}
	public Categories() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
