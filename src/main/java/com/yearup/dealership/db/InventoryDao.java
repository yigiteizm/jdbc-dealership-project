package com.yearup.dealership.db;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InventoryDao {
    private DataSource dataSource;

    public InventoryDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void addVehicleToInventory(String vin, int dealershipId) {

            String sql = "INSERT INTO inventory (dealership_id, VIN) VALUES (?, ?)";

            try (Connection connection = dataSource.getConnection();
                 PreparedStatement statement = connection.prepareStatement(sql)) {
                // Dealership ID ve VIN değerlerini ayarla
                statement.setInt(1, dealershipId);
                statement.setString(2, vin);

                // SQL sorgusunu çalıştır
                statement.executeUpdate();
                System.out.println("Vehicle with VIN " + vin + " added to dealership ID " + dealershipId);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


    public void removeVehicleFromInventory(String vin) {
        // TODO: Implement the logic to remove a vehicle from the inventory
    }
}
