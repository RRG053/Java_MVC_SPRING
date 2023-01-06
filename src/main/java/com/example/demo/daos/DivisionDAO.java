package com.example.demo.daos;
import java.sql.*;
import java.util.*;

import com.example.demo.models.Division;
import com.example.demo.models.Region;

public class DivisionDAO {

    private Connection connections;

    public DivisionDAO(Connection connections){
        this.connections = connections;
    }

    public List<Division> getAll(){
        List<Division> divisions = new ArrayList<>();
        String query = "select * from division d join regions r on d.regionid=r.id";
        try {
            ResultSet resultSet = connections
                    .prepareStatement(query)
                    .executeQuery();
            while (resultSet.next()) {
                Division division = new Division();
                Region region = new Region();
                
                division.setId(resultSet.getInt(1));
                division.setName(resultSet.getString(2));
                region.setId(resultSet.getInt(4));
                region.setName(resultSet.getString(5));
                division.setregion(region);
                divisions.add(division);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return divisions;
    }
    
    public boolean insert(Division division){
        try{
            PreparedStatement prepareStatement = connections.prepareStatement("INSERT INTO DIVISION(name, regionId) VALUES(?,1)");
//            prepareStatement.setInt(1, division.getId());
            prepareStatement.setString(1, division.getName());  
//            prepareStatement.setInt(2, division.getregion().getId());

            prepareStatement.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean update(Division division){
        try {
            String query = "UPDATE DIVISION SET name = ? WHERE id = ?";
            PreparedStatement prepareStatement = connections.prepareStatement(query);
 //           prepareStatement.setInt(1, division.getId());
            prepareStatement.setString(1, division.getName());
            prepareStatement.setInt(2, division.getId());
            prepareStatement.execute();
            return true;
        } catch (SQLException e) {
            e.getMessage();
        }
        return false;
    }

    public boolean delete(Integer id){
        try {
            String query = "DELETE FROM DIVISION WHERE id = ?";
            PreparedStatement prepareStatement = connections.prepareStatement(query);
            prepareStatement.setInt(1, id);
            prepareStatement.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Division getById(Integer id) {
        Division division = new Division();
        try {
            String query = "SELECT * FROM DIVISION WHERE id=?";
            PreparedStatement prepareStatement = connections.prepareStatement(query);
            prepareStatement.setInt(1, id);
            ResultSet resultSet = prepareStatement.executeQuery();
            while (resultSet.next()) {
                division.setId(resultSet.getInt(1));
                division.setName(resultSet.getString(2));           
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return division;
    }
}