package com.coditas.Business;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.coditas.POJO.ParkingStatus;
import com.coditas.POJO.ParkingStatusBuilder;
import com.coditas.POJO.VehicalSpecsDTO;
import com.coditas.constants.ParkingLotConstants;
import com.coditas.infra.PerformFunctionalities;

public class CarParking implements PerformFunctionalities {

	static String CURRENT_CLASS_NAME = "CarParking";
	/*@Override
	public void parkVehicle() {
		// TODO Auto-generated method stub

	}*/

	@Override
	public void vacantSlot(Map<Integer, ParkingStatus> availableSlots, String regNo, String status,double hours) {
		Iterator<Map.Entry<Integer, ParkingStatus>> iterator = availableSlots.entrySet().iterator();
		double temp = 0.0;
		while (iterator.hasNext()) {
			Map.Entry<Integer, ParkingStatus> entry = iterator.next();
			if (regNo.equalsIgnoreCase(entry.getValue().getVehicalSpecsDTO().getVehicalRegNo())) {
				if(hours>2)
				{
					temp = (hours-2.0)*ParkingLotConstants.BASE_FARE + ParkingLotConstants.BASE_FARE;
				}
				else
				{
					temp = ParkingLotConstants.BASE_FARE;
				}
				entry.getValue().setParkedHours(temp);
				entry.getValue().setStatus(status);
				System.out.println("Registration number " + regNo + " with Slot Number " + entry.getKey()
				+ " is free with Charge " + temp);
			}

		}
	}

	@Override
	public void printSlotStatus(Map<Integer, ParkingStatus> availableSlots) {
		Iterator<Map.Entry<Integer, ParkingStatus>> iterator = availableSlots.entrySet().iterator();
		System.out.println("Slot No. Registration No.");
		while (iterator.hasNext()) {
			Map.Entry<Integer, ParkingStatus> entry = iterator.next();

			System.out.println(entry.getKey() + "        " + entry.getValue().getVehicalSpecsDTO().getVehicalRegNo());

		}
	}

	@Override
	public void createSlots(Map<Integer, ParkingStatus> availableSlots, int noOfSlots) {

		System.out.println("Created parking lot with " + noOfSlots + " slots ");
		for (int i = 1; i <= noOfSlots; i++) {
			ParkingStatusBuilder psb = new ParkingStatusBuilder().setStatus(ParkingLotConstants.AVAILABE);
			availableSlots.put(i, psb.getParkingStatus());

		}

	}

	@Override
	public List<Integer> checkSlotStatus(Map<Integer, ParkingStatus> availableSlots) {

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

	@Override
	public void updateSlotStatus(Map<Integer, ParkingStatus> availableSlots, Integer slotNo, String status,
			String vehicleRegNo,String carColor) {
		if (availableSlots.containsKey(slotNo)) {
			ParkingStatus pb = availableSlots.get(slotNo);
			pb.setStatus(status);
			pb.setVehicalSpecsDTO(new VehicalSpecsDTO(carColor, vehicleRegNo));
			System.out.println("Allocated slot number: " + slotNo);
			availableSlots.put(slotNo, pb);

		}

	}

}
