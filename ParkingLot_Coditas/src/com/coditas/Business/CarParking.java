package com.coditas.Business;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.coditas.POJO.ParkingStatus;
import com.coditas.POJO.ParkingStatusBuilder;
import com.coditas.POJO.VehicalSpecsDTO;
import com.coditas.constants.ParkingLotConstants;
import com.coditas.customExceptions.InvalidCarUnparkException;
import com.coditas.infra.PerformFunctionalities;

public class CarParking implements PerformFunctionalities {

	static String CURRENT_CLASS_NAME = "CarParking";
	/*
	 * If a car is unparked this method vacant the slot , calculate the parking fee
	 * (non-Javadoc)
	 * @see com.coditas.infra.PerformFunctionalities#vacantSlot(java.util.Map, java.lang.String, java.lang.String, double)
	 */
	@Override
	public void vacantSlot(Map<Integer, ParkingStatus> availableSlots, String regNo, String status,double hours)  {
		AllocateParkingSlot.logger.info(CURRENT_CLASS_NAME + "Executing vacantSlot method" );
		Iterator<Map.Entry<Integer, ParkingStatus>> iterator = availableSlots.entrySet().iterator();
		double temp = 0.0;
		boolean invalidRegNo = true;
		try {
			while (iterator.hasNext()) {
				Map.Entry<Integer, ParkingStatus> entry = iterator.next();
				if (regNo.equalsIgnoreCase(entry.getValue().getVehicalSpecsDTO().getVehicalRegNo())) {
					if(hours>ParkingLotConstants.MIN_HOURS_FOR_BASE_FARE)
					{
						temp = (hours-ParkingLotConstants.MIN_HOURS_FOR_BASE_FARE)*ParkingLotConstants.BASE_FARE + ParkingLotConstants.BASE_FARE;
					}
					else
					{
						temp = ParkingLotConstants.BASE_FARE;
					}
					entry.getValue().setParkedHours(temp);
					entry.getValue().setStatus(status);
					System.out.println("Registration number " + regNo + " with Slot Number " + entry.getKey()
					+ " is free with Charge " + temp);
					invalidRegNo = false;;
				}
			}
				if(invalidRegNo)
				{
					throw new InvalidCarUnparkException("Invalid Car Registration number provided !!! " + regNo);
				}
		}
		catch (InvalidCarUnparkException e) {
			AllocateParkingSlot.logger.warning(CURRENT_CLASS_NAME + "Executing vacantSlot method" + e.getMessage());
			System.out.println("Registration number " + regNo  +" not found");
		}
	}

	/*
	 * Prints the current status of the parking lot
	 * (non-Javadoc)
	 * @see com.coditas.infra.PerformFunctionalities#printSlotStatus(java.util.Map)
	 */
	@Override
	public void printSlotStatus(Map<Integer, ParkingStatus> availableSlots) {
		AllocateParkingSlot.logger.info(CURRENT_CLASS_NAME + "Executing printSlotStatus method" );
		Iterator<Map.Entry<Integer, ParkingStatus>> iterator = availableSlots.entrySet().iterator();
		System.out.println("Slot No. Registration No.");
		while (iterator.hasNext()) {
			Map.Entry<Integer, ParkingStatus> entry = iterator.next();
			if(entry.getValue().getStatus().equals(ParkingLotConstants.NOT_AVAILABE))
			{
				System.out.println(entry.getKey() + "        " + entry.getValue().getVehicalSpecsDTO().getVehicalRegNo());
			}
		

		}
	}

	/*
	 * Create the parking slots with a specified number of slots
	 * (non-Javadoc)
	 * @see com.coditas.infra.PerformFunctionalities#createSlots(java.util.Map, int)
	 */
	@Override
	public void createSlots(Map<Integer, ParkingStatus> availableSlots, int noOfSlots) {
		AllocateParkingSlot.logger.info(CURRENT_CLASS_NAME + "Executing createSlots method" );
		System.out.println("Created parking lot with " + noOfSlots + " slots ");
		for (int i = 1; i <= noOfSlots; i++) {
			ParkingStatusBuilder psb = new ParkingStatusBuilder().setStatus(ParkingLotConstants.AVAILABE);
			availableSlots.put(i, psb.getParkingStatus());

		}

	}
/*
 * Check all the vacant slots , return a list of available slots out of which minimum value slot is allocated further 
 * (non-Javadoc)
 * @see com.coditas.infra.PerformFunctionalities#checkSlotStatus(java.util.Map)
 */
	@Override
	public List<Integer> checkSlotStatus(Map<Integer, ParkingStatus> availableSlots) {
		AllocateParkingSlot.logger.info(CURRENT_CLASS_NAME + "Executing checkSlotStatus method");
		List<Integer> vacantSlotNos = new ArrayList<>();
		Iterator<Map.Entry<Integer, ParkingStatus>> iterator = availableSlots.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<Integer, ParkingStatus> entry = iterator.next();
			if (entry.getValue().getStatus().equals(ParkingLotConstants.AVAILABE)) {
				vacantSlotNos.add(entry.getKey());
			}

		}

		return vacantSlotNos;
	}

	/*
	 * Update slots status Available or Not Available as applicable
	 * (non-Javadoc)
	 * @see com.coditas.infra.PerformFunctionalities#updateSlotStatus(java.util.Map, java.lang.Integer, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public void updateSlotStatus(Map<Integer, ParkingStatus> availableSlots, Integer slotNo, String status,
			String vehicleRegNo,String carColor) {
		AllocateParkingSlot.logger.info(CURRENT_CLASS_NAME + "Executing updateSlotStatus method");
		if (availableSlots.containsKey(slotNo)) {
			ParkingStatus pb = availableSlots.get(slotNo);
			pb.setStatus(status);
			pb.setVehicalSpecsDTO(new VehicalSpecsDTO(carColor, vehicleRegNo));
			System.out.println("Allocated slot number: " + slotNo);
			availableSlots.put(slotNo, pb);

		}

	}

}
