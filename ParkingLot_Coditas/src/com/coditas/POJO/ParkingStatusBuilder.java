package com.coditas.POJO;

public class ParkingStatusBuilder {

	private String status;
	private VehicalSpecsDTO vehicalSpecsDTO;

	public ParkingStatusBuilder setStatus(String status) {
		this.status = status;
		return this;
	}

	public ParkingStatusBuilder setVehicalSpecsDTO(VehicalSpecsDTO vehicalSpecsDTO) {
		this.vehicalSpecsDTO = vehicalSpecsDTO;
		return this;
	}

	public ParkingStatus getParkingStatus() {
		return new ParkingStatus(status, vehicalSpecsDTO);
	}

}
