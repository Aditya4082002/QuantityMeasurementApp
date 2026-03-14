package com.quantitymeasurementapp.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.List;

import com.quantitymeasurementapp.entity.QuantityMeasurementEntity;
import com.quantitymeasurementapp.util.ConnectionPool;

public class QuantityMeasurementDatabaseRepository
        implements IQuantityMeasurementRepository {
	
	public QuantityMeasurementDatabaseRepository() {
	    initializeTable();
	}
	
	private void initializeTable() {

	    String sql = """
	        CREATE TABLE IF NOT EXISTS quantity_measurement (
	            id INT AUTO_INCREMENT PRIMARY KEY,
	            operation VARCHAR(50),
	            operand1 VARCHAR(50),
	            operand2 VARCHAR(50),
	            result VARCHAR(50),
	            created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
	        )
	        """;

	    try (Connection conn = ConnectionPool.getConnection();
	         Statement stmt = conn.createStatement()) {

	        stmt.execute(sql);

	    } catch (Exception e) {
	        throw new RuntimeException("Table initialization failed", e);
	    }
	}

    private static final String INSERT_SQL =
            "INSERT INTO quantity_measurement " +
            "(operation, operand1, operand2, result) " +
            "VALUES (?,?,?,?)";

    @Override
    public void save(QuantityMeasurementEntity entity){

        try(Connection conn = ConnectionPool.getConnection();
            PreparedStatement stmt = conn.prepareStatement(INSERT_SQL)){

            stmt.setString(1, entity.operation);
            stmt.setString(2, entity.operand1);
            stmt.setString(3, entity.operand2);
            stmt.setString(4, entity.result);

            stmt.executeUpdate();

        } catch(Exception e){
            throw new RuntimeException("Database save failed", e);
        }
    }

	@Override
	public List<QuantityMeasurementEntity> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void printAllMeasurements() {

	    String SELECT_SQL = "SELECT * FROM quantity_measurement";

	    try (Connection conn = ConnectionPool.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(SELECT_SQL);
	         ResultSet rs = stmt.executeQuery()) {

	        System.out.println("----- DATABASE RECORDS -----");

	        while (rs.next()) {

	            int id = rs.getInt("id");
	            String operation = rs.getString("operation");
	            String operand1 = rs.getString("operand1");
	            String operand2 = rs.getString("operand2");
	            String result = rs.getString("result");
	            Timestamp createdAt = rs.getTimestamp("created_at");

	            System.out.println(
	                    id + " | " +
	                    operation + " | " +
	                    operand1 + " | " +
	                    operand2 + " | " +
	                    result + " | " +
	                    createdAt
	            );
	        }

	    } catch (Exception e) {
	        throw new RuntimeException("Database read failed", e);
	    }
	}
}