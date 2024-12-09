package com.yearup.dealership.db;

import com.yearup.dealership.models.SalesContract;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;

public class SalesDao {
    private DataSource dataSource;

    public SalesDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void addSalesContract(SalesContract salesContract) {

            String sql = "INSERT INTO sales_contracts (VIN, sale_date, price) VALUES (?, ?, ?)";

            try (Connection connection = dataSource.getConnection();
                 PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, salesContract.getVin());
                statement.setDate(2, Date.valueOf(salesContract.getSaleDate()));
                statement.setDouble(3, salesContract.getPrice());

                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

