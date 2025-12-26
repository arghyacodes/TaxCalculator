package com.taxcalculator.vehicle;

public class VehicleDTO {
	String regno;
	String brand;
	int topspeed;
	int seatingcapacity;
	String fueltype;
	double purchasecost;
	double vehicletax;
	
	public VehicleDTO() {
		
	}

	public String getRegno() {
		return regno;
	}

	public void setRegno(String regno) {
		this.regno = regno;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public int getTopspeed() {
		return topspeed;
	}

	public void setTopspeed(int topspeed) {
		this.topspeed = topspeed;
	}

	public int getSeatingcapacity() {
		return seatingcapacity;
	}

	public void setSeatingcapacity(int seatingcapacity) {
		this.seatingcapacity = seatingcapacity;
	}

	public String getFueltype() {
		return fueltype;
	}

	public void setFueltype(String fueltype) {
		this.fueltype = fueltype;
	}

	public double getPurchasecost() {
		return purchasecost;
	}

	public void setPurchasecost(double purchasecost) {
		this.purchasecost = purchasecost;
	}

	public double getVehicletax() {
		return vehicletax;
	}

	public void setVehicletax(double vehicletax) {
		this.vehicletax = vehicletax;
	}

	public VehicleDTO(String regno, String brand, int topspeed, int seatingcapacity, String fueltype, double purchasecost,
			double vehicletax) {
		super();
		this.regno = regno;
		this.brand = brand;
		this.topspeed = topspeed;
		this.seatingcapacity = seatingcapacity;
		this.fueltype = fueltype;
		this.purchasecost = purchasecost;
		this.vehicletax = vehicletax;
	}

	@Override
	public String toString() {
		return "VehicleDTO [regno=" + regno + ", brand=" + brand + ", topspeed=" + topspeed + ", seatingcapacity="
				+ seatingcapacity + ", fueltype=" + fueltype + ", purchasecost=" + purchasecost + ", vehicletax="
				+ vehicletax + "]";
	}
	
}
