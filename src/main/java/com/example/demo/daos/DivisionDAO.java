package com.example.demo.daos;
import java.sql.*;
import java.util.*;

import com.example.demo.models.Division;

public class DivisionDAO {

    private Connection connections;

    public DivisionDAO(Connection connections){
        this.connections = connections;
    }

    public List<Division> getAll(){
        List<Division> divisions = new ArrayList<>();
        String query = "SELECT * FROM DIVISION";
        try {
            ResultSet resultSet = connections
                    .prepareStatement(query)
                    .executeQuery();
            while (resultSet.next()) {
                Division division = new Division();
                division.setDivisionId(resultSet.getInt(1));
                division.setDivisionName(resultSet.getString(2));
                division.setregionId(resultSet.getInt(3));

                divisions.add(division);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return divisions;
    }
    
    public boolean insert(Division division){
        try{
            PreparedStatement prepareStatement = connections.prepareStatement("INSERT INTO DIVISION(division_id, division_name, region_id) VALUES(?,?,?)");
            prepareStatement.setInt(1, division.getDivisionId());
            prepareStatement.setString(2, division.getDivisionName());
            prepareStatement.setInt(3, division.getregionId());

            prepareStatement.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean update(Division division){
        try {
            String query = "UPDATE DIVISION SET division_id = ?, division_name = ?, region_id = ? WHERE division_id = ?";
            PreparedStatement prepareStatement = connections.prepareStatement(query);
            prepareStatement.setInt(1, division.getDivisionId());
            prepareStatement.setString(2, division.getDivisionName());
            prepareStatement.setInt(3, division.getregionId());
            prepareStatement.setInt(4, division.getDivisionId());

            prepareStatement.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(Division division){
        try {
            String query = "DELETE FROM DIVISION WHERE division_id = ?";
            PreparedStatement prepareStatement = connections.prepareStatement(query);
            prepareStatement.setInt(1, division.getDivisionId());
            prepareStatement.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}