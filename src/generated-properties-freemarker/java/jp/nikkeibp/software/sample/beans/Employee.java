package jp.nikkeibp.software.sample.beans;

public class Employee {
	private java.lang.Boolean manager;
	public void setManager(java.lang.Boolean manager){
		this.manager = manager;
	}
 	public java.lang.Boolean isManager(){
		return manager;
	}
	private jp.nikkeibp.software.sample.beans.Department department;
	public void setDepartment(jp.nikkeibp.software.sample.beans.Department department){
		this.department = department;
	}
 	public jp.nikkeibp.software.sample.beans.Department getDepartment(){
		return department;
	}
	private java.lang.String name;
	public void setName(java.lang.String name){
		this.name = name;
	}
 	public java.lang.String getName(){
		return name;
	}
}