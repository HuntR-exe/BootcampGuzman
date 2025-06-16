package org.example;

import org.example.Contract;
import org.example.DBConnection;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface ContractDAO {
    void addContract(Contract contract);

    default void saveContract(Contract contract, String tableName) {
        String sql = "INSERT INTO " + tableName + " (date, customer_name, customer_email, vehicle_id, " +
                "total_price, monthly_payment) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setDate(1, Date.valueOf(contract.getDate()));
            ps.setString(2, contract.getCustomerName());
            ps.setString(3, contract.getCustomerEmail());
            ps.setInt(4, contract.getVehicle().getId());
            ps.setDouble(5, contract.getTotalPrice());
            ps.setDouble(6, contract.getMonthlyPayment());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}