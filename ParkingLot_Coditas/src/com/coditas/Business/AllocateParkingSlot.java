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
import com.coditas.Utilities.ParkingLotUtils;
import com.coditas.constants.ParkingLotConstants;

public class AllocateParkingSlot {

	static Map<Integer, ParkingStatus> slotsStatus = new HashMap<>();
	public static Logger logger = null;
	static FileHandler handler = null;
	static String CURRENT_CLASS_NAME = "AllocateParkingSlot";

	public static void main(String[] args) {

		try {

			handler = new FileHandler("C:/Users/user/git/parking_lot/ParkingLot_Coditas/src/log_files/ParkingLotLogs_"
					+ System.currentTimeMillis() + ".txt", true);
			logger = Logger.getLogger(CURRENT_CLASS_NAME.getClass().getName());
			logger.addHandler(handler);
			if (args.length > 0) {

				File file = new File(args[0]);
				logger.info("STARTING EXECUTION OF :: " + CURRENT_CLASS_NAME);

				readInputCommands(file);
			} else {
				logger.severe("NO INPUT FILE RECEIVED AS COMMAND LINE ARGS" + args[0]);
				System.out.println("No input file received");
			}
		} catch (SecurityException e) {
			logger.severe("SecurityException " + e.getMessage());
		} catch (IOException e) {
			logger.severe("IOException " + e.getMessage());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.severe("IOException " + e.getMessage());
		}

	}

	/*
	 * Reads the input file and processes as per commands given
	 */
	public static void readInputCommands(File file) throws Exception {

		logger.info(CURRENT_CLASS_NAME + "Executing readInputCommands method ");
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {

			String line = null;
			Map<String, String> commandProperties = new ParkingLotUtils().fetchInstance();
			while ((line = br.readLine()) != null) {
				String commandArgs[] = line.split(" ");

				if (commandArgs[0].equals(commandProperties.get(ParkingLotConstants.STATUS))) {

					new CarParking().printSlotStatus(slotsStatus);

				}

				if (commandArgs[0].equals(commandProperties.get(ParkingLotConstants.CREATE))) {
					new CarParking().createSlots(slotsStatus, Integer.parseInt(commandArgs[1]));
				}

				if (commandArgs[0].equals(commandProperties.get(ParkingLotConstants.PARK))) {
					OptionalInt opt = new CarParking().checkSlotStatus(slotsStatus).stream().mapToInt(ip -> ip).min();
					if (opt.isPresent() && slotsStatus.containsKey(opt.getAsInt())) {

						int slotNo = opt.getAsInt();
						String vehicleRegNo = commandArgs[1];
						new CarParking().updateSlotStatus(slotsStatus, slotNo, ParkingLotConstants.NOT_AVAILABE,
								vehicleRegNo, commandArgs[2]);

					} else {
						logger.severe("Sorry, parking lot is full ");
						System.out.println("Sorry, parking lot is full ");
					}

				}

				if (commandArgs[0].equals(commandProperties.get(ParkingLotConstants.LEAVE))) {

					new CarParking().vacantSlot(slotsStatus, commandArgs[1], ParkingLotConstants.AVAILABE,
							Double.parseDouble(commandArgs[2]));

				}

			}

		} catch (FileNotFoundException e) {

			throw e;
		} catch (IOException e) {

			throw e;
		} catch (NumberFormatException e) {

			throw e;
		} catch (Exception e) {

			throw e;
		}

	}
}
