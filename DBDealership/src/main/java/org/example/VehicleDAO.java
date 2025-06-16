package org.example;

import org.example.Vehicle;
import org.example.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VehicleDAO {
    public List<Vehicle> getAllVehicles() {
        List<Vehicle> vehicles = new ArrayList<>();
        String sql = "SELECT * FROM vehicles";

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                vehicles.add(mapVehicle(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vehicles;
    }

    public List<Vehicle> getByPriceRange(double min, double max) {
        return getVehiclesByCriteria("price BETWEEN ? AND ?", min, max);
    }

    public List<Vehicle> getByMakeModel(String make, String model) {
        return getVehiclesByCriteria("make = ? AND model = ?", make, model);
    }

    public List<Vehicle> getByYearRange(int start, int end) {
        return getVehiclesByCriteria("year BETWEEN ? AND ?", start, end);
    }

    public List<Vehicle> getByColor(String color) {
        return getVehiclesByCriteria("color = ?", color);
    }

    public List<Vehicle> getByMileageRange(int min, int max) {
        return getVehiclesByCriteria("odometer BETWEEN ? AND ?", min, max);
    }

    public List<Vehicle> getByType(String type) {
        return getVehiclesByCriteria("type = ?", type);
    }

    private List<Vehicle> getVehiclesByCriteria(String whereClause, Object... params) {
        List<Vehicle> vehicles = new ArrayList<>();
        String sql = "SELECT * FROM vehicles WHERE " + whereClause;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            for (int i = 0; i < params.length; i++) {
                ps.setObject(i + 1, params[i]);
            }

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    vehicles.add(mapVehicle(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vehicles;
    }

    public void addVehicle(Vehicle vehicle) {
        String sql = "INSERT INTO vehicles (vin, year, make, model, type, color, odometer, price) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            setVehicleParameters(ps, vehicle);
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    vehicle.setId(rs.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeVehicle(int vehicleId) {
        String sql = "DELETE FROM vehicles WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, vehicleId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Vehicle mapVehicle(ResultSet rs) throws SQLException {
        Vehicle v = new Vehicle();
        v.setId(rs.getInt("id"));
        v.setVin(rs.getInt("vin"));
        v.setYear(rs.getInt("year"));
        v.setMake(rs.getString("make"));
        v.setModel(rs.getString("model"));
        v.setType(rs.getString("type"));
        v.setColor(rs.getString("color"));
        v.setOdometer(rs.getInt("odometer"));
        v.setPrice(rs.getDouble("price"));
        return v;
    }

    private void setVehicleParameters(PreparedStatement ps, Vehicle vehicle) throws SQLException {
        ps.setInt(1, vehicle.getVin());
        ps.setInt(2, vehicle.getYear());
        ps.setString(3, vehicle.getMake());
        ps.setString(4, vehicle.getModel());
        ps.setString(5, vehicle.getType());
        ps.setString(6, vehicle.getColor());
        ps.setInt(7, vehicle.getOdometer());
        ps.setDouble(8, vehicle.getPrice());
    }
}