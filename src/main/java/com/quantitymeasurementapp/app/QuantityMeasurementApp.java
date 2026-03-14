package com.quantitymeasurementapp.app;

import com.quantitymeasurementapp.controller.QuantityMeasurementController;
import com.quantitymeasurementapp.repository.*;
import com.quantitymeasurementapp.service.*;
import com.quantitymeasurementapp.util.ApplicationConfig;

public class QuantityMeasurementApp {

	public static void main(String[] args) {

		IQuantityMeasurementRepository repo;

		if (ApplicationConfig.get("repository.type").equals("database")) {

			repo = new QuantityMeasurementDatabaseRepository();

		} else {

			repo = QuantityMeasurementCacheRepository.getInstance();
		}

		IQuantityMeasurementService service = new QuantityMeasurementServiceImpl(repo);

		QuantityMeasurementController controller = new QuantityMeasurementController(service);

		controller.performOperations();
		repo.printAllMeasurements();
	}
}