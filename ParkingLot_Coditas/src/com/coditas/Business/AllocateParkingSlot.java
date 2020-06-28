package com.coditas.Business;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.OptionalInt;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

import com.coditas.POJO.ParkingStatus;
import com.coditas.constants.ParkingLotConstants;

public class AllocateParkingSlot {

	static Map<Integer, ParkingStatus> slotsStatus = new HashMap<Integer, ParkingStatus>();
	static Logger logger;
	static FileHandler handler ;
	static String CURRENT_CLASS_NAME = "AllocateParkingSlot";
	public static void main(String[] args) {

		try {
			handler = new FileHandler("F:Workspace/ParkingLot_Coditas/src/log_files/ParkingLotLogs.txt", true);
			logger = Logger.getLogger(CURRENT_CLASS_NAME.getClass().getName());
			if (args.length > 0) {
				logger.info("");
				File file = new File(args[0]);
				readInputCommands(file);
			} else {
				System.out.println("No input file received");
			}
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void readInputCommands(File file) {

		try (BufferedReader br = new BufferedReader(new FileReader(file))) {

			String line = null;
			while ((line = br.readLine()) != null) {
				String arr[] = line.split(" ");
				if (arr.length == 1 && arr[0].equals(ParkingLotConstants.STATUS)) {

					new CarParking().printSlotStatus(slotsStatus);

				}
				if (arr.length == 2) {

					if (arr[0].equals(ParkingLotConstants.CREATE)) {

						new CarParking().createSlots(slotsStatus, Integer.parseInt(arr[1]));
					} else if (arr[0].equals(ParkingLotConstants.PARK)) {

						// checkStatusOfSlots
						// Add new Key value to the map with Slot No as key and
						// value as DTO obj
						// Mark Not Available in slotStatus map

						OptionalInt opt = new CarParking().checkSlotStatus(slotsStatus).stream().mapToInt(ip -> ip)
								.min();
						if (opt.isPresent() && slotsStatus.containsKey(opt.getAsInt())) {

							int slotNo = opt.getAsInt();
							String vehicleRegNo = arr[1];
							new CarParking().updateSlotStatus(slotsStatus, slotNo, ParkingLotConstants.NOT_AVAILABE,
									vehicleRegNo);

						} else {
							System.out.println("Sorry, parking lot is full ");
						}

					}

				}
				if (arr.length == 3 && arr[0].equals(ParkingLotConstants.LEAVE)) {

					new CarParking().vacantSlot(slotsStatus, arr[1], ParkingLotConstants.AVAILABE,
							Double.parseDouble(arr[2]));

				}

			}

		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
