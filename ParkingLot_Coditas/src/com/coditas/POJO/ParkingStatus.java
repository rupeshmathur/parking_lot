package com.coditas.POJO;

public class ParkingStatus {

	private String status;
	private VehicalSpecsDTO vehicalSpecsDTO;
	private double parkedHours;

	public ParkingStatus() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ParkingStatus(String status, VehicalSpecsDTO vehicalSpecsDTO) {
		super();
		this.status = status;
		this.vehicalSpecsDTO = vehicalSpecsDTO;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public VehicalSpecsDTO getVehicalSpecsDTO() {
		return vehicalSpecsDTO;
	}

	public void setVehicalSpecsDTO(VehicalSpecsDTO vehicalSpecsDTO) {
		this.vehicalSpecsDTO = vehicalSpecsDTO;
	}

	public double getParkedHours() {
		return parkedHours;
	}

	public void setParkedHours(double parkedHours) {
		this.parkedHours = parkedHours;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

}
