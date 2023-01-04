package com.example.demo.daos;
import java.sql.*;
import java.util.*;

import com.example.demo.models.Region;

public class RegionDAO {

    private Connection connection;

    public RegionDAO(Connection connection){
        this.connection = connection;
    }

    public List<Region> getAll(){
        List<Region> regions = new ArrayList<>();
        String query = "SELECT * FROM REGIONS";
        try {
            ResultSet resultSet = connection
                    .prepareStatement(query)
                    .executeQuery();
            while (resultSet.next()) {
                Region region = new Region();
                region.setRegionId(resultSet.getInt(1));
                region.setRegionName(resultSet.getString(2));
                regions.add(region);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return regions;
    }
    
    public boolean insert(Region region){
        try{
            PreparedStatement prepareStatement = connection.prepareStatement("INSERT INTO REGIONS(region_id, region_name) VALUES(?,?)");
            prepareStatement.setInt(1, region.getRegionId());
            prepareStatement.setString(2, region.getRegionName());
            prepareStatement.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean update(Region region){
        try {
            String query = "UPDATE REGIONS SET region_id = ?, region_name = ? WHERE region_id = ?";
            PreparedStatement prepareStatement = connection.prepareStatement(query);
            prepareStatement.setInt(1, region.getRegionId());
            prepareStatement.setString(2, region.getRegionName());
            prepareStatement.setInt(3, region.getRegionId());
            prepareStatement.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(Region region){
        try {
            String query = "DELETE FROM REGIONS WHERE region_id = ?";
            PreparedStatement prepareStatement = connection.prepareStatement(query);
            prepareStatement.setInt(1, region.getRegionId());
            prepareStatement.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}