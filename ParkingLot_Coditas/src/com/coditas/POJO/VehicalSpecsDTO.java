package com.coditas.POJO;

public class VehicalSpecsDTO {

	private String vehicalColor;
	private String vehicalRegNo;

	public VehicalSpecsDTO() {
		super();

	}

	public VehicalSpecsDTO(String vehicalColor, String vehicalRegNo) {
		super();
		this.vehicalColor = vehicalColor;
		this.vehicalRegNo = vehicalRegNo;

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

	@Override
	public String toString() {

		return super.toString();
	}

}
