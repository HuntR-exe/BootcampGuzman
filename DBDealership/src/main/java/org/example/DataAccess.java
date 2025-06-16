package org.example;

import org.example.VehicleDAO;
import org.example.Dealership;
import org.example.Vehicle;

public class DataAccess {
    private static final Dealership dealership = new Dealership(
            "GearHeads ~ Japan",
            "123 Cherry BLVD",
            "555-1234"
    );

    public static Dealership getDealership() {
        dealership.setInventory(new VehicleDAO().getAllVehicles());
        return dealership;
    }
}