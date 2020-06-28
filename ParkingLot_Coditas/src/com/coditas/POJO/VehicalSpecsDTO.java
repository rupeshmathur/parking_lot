package com.coditas.POJO;

public class VehicalSpecsDTO {

	
	private String vehicalColor;
	private String vehicalRegNo;
	private String fareCharged;

	public VehicalSpecsDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public VehicalSpecsDTO(String vehicalColor, String vehicalRegNo, String fareCharged) {
		super();
		this.vehicalColor = vehicalColor;
		this.vehicalRegNo = vehicalRegNo;
		this.fareCharged = fareCharged;
	}

	public String getVehicalColor() {
		return vehicalColor;
	}

	public void setVehicalColor(String vehicalColor) {
		this.vehicalColor = vehicalColor;
	}

	public String getVehicalRegNo() {
		return vehicalRegNo;
	}

	public void setVehicalRegNo(String vehicalRegNo) {
		this.vehicalRegNo = vehicalRegNo;
	}

	public String getFareCharged() {
		return fareCharged;
	}

	public void setFareCharged(String fareCharged) {
		this.fareCharged = fareCharged;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

}
