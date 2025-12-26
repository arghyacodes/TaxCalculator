package com.taxcalculator.property;

public class PropertyDTO {

	int id;
	int basevalue;
	int area;
	int age;
	String incity;
	double propertytax;
	public PropertyDTO() {
		
	}
	
	public PropertyDTO(int id, int basevalue, int area, int age, String incity, double propertytax) {
		super();
		this.id = id;
		this.basevalue = basevalue;
		this.area = area;
		this.age = age;
		this.incity = incity;
		this.propertytax = propertytax;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getBasevalue() {
		return basevalue;
	}
	public void setBasevalue(int basevalue) {
		this.basevalue = basevalue;
	}
	public int getArea() {
		return area;
	}
	public void setArea(int area) {
		this.area = area;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getIncity() {
		return incity;
	}
	public void setIncity(String incity) {
		this.incity = incity;
	}
	public double getPropertytax() {
		return propertytax;
	}
	public void setPropertytax(double propertytax) {
		this.propertytax = propertytax;
	}
	@Override
	public String toString() {
		return "PropertyDTO [id=" + id + ", basevalue=" + basevalue + ", area=" + area + ", age=" + age + ", incity="
				+ incity + ", propertytax=" + propertytax + "]";
	}
	
}
