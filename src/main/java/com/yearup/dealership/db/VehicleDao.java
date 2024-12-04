package com.yearup.dealership.db;

import com.yearup.dealership.models.Vehicle;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VehicleDao {
    private DataSource dataSource;

    public VehicleDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void addVehicle(Vehicle vehicle)  {
        String sql = "INSERT INTO vehicles (VIN, make, model, year, SOLD, color, vehicleType, odometer, price) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, vehicle.getVin());
            statement.setString(2, vehicle.getMake());
            statement.setString(3, vehicle.getModel());
            statement.setInt(4, vehicle.getYear());
            statement.setBoolean(5, vehicle.isSold());
            statement.setString(6, vehicle.getColor());
            statement.setString(7, vehicle.getVehicleType());
            statement.setInt(8, vehicle.getOdometer());
            statement.setDouble(9, vehicle.getPrice());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeVehicle(String VIN) {

            String sql = "DELETE FROM vehicles WHERE VIN = ?";
            try (Connection connection = dataSource.getConnection();
                 PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, VIN);
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }



    public List<Vehicle> searchByPriceRange(double minPrice, double maxPrice) {

        String sql = "SELECT * FROM vehicles WHERE price BETWEEN ? AND ?";
        List<Vehicle> vehicles = new ArrayList<>();

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setDouble(1, minPrice);
            statement.setDouble(2, maxPrice);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    vehicles.add(createVehicleFromResultSet(resultSet));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return vehicles;
        }



    public List<Vehicle> searchByMakeModel(String make, String model) {

            List<Vehicle> vehicles = new ArrayList<>();
            String sql = "SELECT * FROM vehicles WHERE make = ? AND model = ?";

            try (Connection connection = dataSource.getConnection();
                 PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, make);
                statement.setString(2, model);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        vehicles.add(createVehicleFromResultSet(resultSet));
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return vehicles;
        }



    public List<Vehicle> searchByYearRange(int minYear, int maxYear) {

            List<Vehicle> vehicles = new ArrayList<>();
            String sql = "SELECT * FROM vehicles WHERE year BETWEEN ? AND ?";

            try (Connection connection = dataSource.getConnection();
                 PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, minYear);
                statement.setInt(2, maxYear);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        vehicles.add(createVehicleFromResultSet(resultSet));
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return vehicles;
        }



    public List<Vehicle> searchByColor(String color) {
        List<Vehicle> vehicles = new ArrayList<>();
        String sql = "SELECT * FROM vehicles WHERE color = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, color);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    vehicles.add(createVehicleFromResultSet(resultSet));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return vehicles;
    }

    public List<Vehicle> searchByMileageRange(int minMileage, int maxMileage) {
        // TODO: Implement the logic to search vehicles by mileage range
        return new ArrayList<>();
    }

    public List<Vehicle> searchByType(String type) {
        // TODO: Implement the logic to search vehicles by type
        return new ArrayList<>();
    }

    private Vehicle createVehicleFromResultSet(ResultSet resultSet) throws SQLException {
        Vehicle vehicle = new Vehicle();
        vehicle.setVin(resultSet.getString("VIN"));
        vehicle.setMake(resultSet.getString("make"));
        vehicle.setModel(resultSet.getString("model"));
        vehicle.setYear(resultSet.getInt("year"));
        vehicle.setSold(resultSet.getBoolean("SOLD"));
        vehicle.setColor(resultSet.getString("color"));
        vehicle.setVehicleType(resultSet.getString("vehicleType"));
        vehicle.setOdometer(resultSet.getInt("odometer"));
        vehicle.setPrice(resultSet.getDouble("price"));
        return vehicle;
    }
}
