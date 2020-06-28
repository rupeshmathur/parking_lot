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
	static Logger logger = null;
	static FileHandler handler = null;
	static String CURRENT_CLASS_NAME = "AllocateParkingSlot";
	public static void main(String[] args) {

		try {
			handler = new FileHandler("C:/Users/user/git/parking_lot/ParkingLot_Coditas/src/log_files/ParkingLotLogs.txt", true);
			logger = Logger.getLogger(CURRENT_CLASS_NAME.getClass().getName());
			logger.addHandler(handler);
			if (args.length > 0) {
				
				File file = new File(args[0]);
				logger.info("STARTING EXECUTION OF :: " + CURRENT_CLASS_NAME );
				readInputCommands(file);
			} else {
				logger.severe("NO INPUT FILE RECEIVED AS COMMAND LINE ARGS" + args[0] );
				System.out.println("No input file received");
			}
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			logger.severe("SecurityException " + e.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.severe("IOException " + e.getMessage());
		}

	}

	public static void readInputCommands(File file) throws IOException {

		logger.info(CURRENT_CLASS_NAME + "Executing readInputCommands method " );
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
			logger.severe("FileNotFoundException " + e.getMessage());
			throw e;
		} catch (IOException e) {
			logger.severe("IOException " + e.getMessage());
			throw e;
		}

	}
}
