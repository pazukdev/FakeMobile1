package com.pazukdev.services;

import com.pazukdev.dao.DAOBearing;
import com.pazukdev.entities.Bearing;

import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BearingsService {

	private static BearingsService instance;
	private static final Logger LOGGER = Logger.getLogger(BearingsService.class.getName());

	private final HashMap<Long, Bearing> bearings = new HashMap<>();
	private DAOBearing daoBearing = new DAOBearing();


	private BearingsService() {}


	public static BearingsService getInstance() {
		if (instance == null) {
			instance = new BearingsService();
			instance.ensureTestData();
		}
		return instance;
	}


	public synchronized List<Bearing> findAll() {
		return daoBearing.getList();
	}


	public synchronized Integer count() {
		return bearings.size();
	}


	public synchronized void delete(Bearing bearing) {
        daoBearing.delete(bearing);
	}


	public synchronized void save(Bearing bearing) {
		if (bearing == null) {
			LOGGER.log(Level.SEVERE, "Bearing is null.");
			return;
		}

		if (bearing.getId() != null) {
			daoBearing.update(bearing);
			return;
		}

		daoBearing.create(bearing);
	}


	public void ensureTestData() {

		if (findAll().isEmpty()) {
			final String[] bearingsData = new String[] {
					"207;ball;Engine;2",
					"205;ball;Engine;1",
					"364708 DM;roller;Engine;2",
					"7201234-A;plain;Engine;2",
					"7201107;plain;Engine;1",
					"778707;ball;Frame and wheels;2",
					"7204;roller;Frame and wheels;8",
					"201;ball;Generator;1",
					"200;ball;Generator;1",
					"304;ball;Gearbox;2",
					"12204;roller;Gearbox;1",
					"948066;ball;Gearbox;1"
			};

			for (String bearingSourceString : bearingsData) {
				String[] splittedString = bearingSourceString.split(";");
				Bearing bearing = new Bearing();
				bearing.setNumberOfOriginal(splittedString[0]);
				bearing.setType(splittedString[1]);
				bearing.setMajorLocation(splittedString[2]);
				bearing.setQuantity(Integer.valueOf(splittedString[3]));
				save(bearing);
			}
		}
	}

}
