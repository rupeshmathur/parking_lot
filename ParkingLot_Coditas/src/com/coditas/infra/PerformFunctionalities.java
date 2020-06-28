package com.coditas.infra;

import java.util.List;
import java.util.Map;

import com.coditas.POJO.ParkingStatus;
import com.coditas.customExceptions.InvalidCarUnparkException;

public interface PerformFunctionalities {

	
	public void createSlots(Map<Integer, ParkingStatus> availableSlots, int noOfSlots);

//	public void parkVehicle();

	public void vacantSlot(Map<Integer, ParkingStatus> availableSlots,String regNo,String status,double hours) throws InvalidCarUnparkException;

	public void printSlotStatus(Map<Integer, ParkingStatus> availableSlots);

	public List<Integer> checkSlotStatus(Map<Integer, ParkingStatus> availableSlots);

	public void updateSlotStatus(Map<Integer, ParkingStatus> availableSlots, Integer slotNo,String status,String vehicleRegNo,String carColor);
}
