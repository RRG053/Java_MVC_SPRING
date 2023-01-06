package com.example.demo.tools;

import com.example.demo.daos.DivisionDAO;
import com.example.demo.daos.RegionDAO;
import com.example.demo.models.Division;
import com.example.demo.models.Region;

public class Test {
    public static void main(String[] args) {
        System.out.println(DBConnection.getConnection());
        RegionDAO rdao = new RegionDAO(DBConnection.getConnection());
        for (Region region : rdao.getAll()){
            System.out.print(region.getId()+" ");
            System.out.println(region.getName());
    
        }
        // Region region = new Region();
        // region.setRegionId(5);
        // region.setRegionName("Bandung");
        // System.out.println(rdao.insert(region));




        DivisionDAO ddao = new DivisionDAO(DBConnection.getConnection());
        for (Division division : ddao.getAll()){
            System.out.print(division.getregionId()+" ");
            System.out.println(division.getName());
            System.out.println(division.getregionId());
    
        }
        // Division division = new Division();
        // division.setDivisionId(0);
        // division.setDivisionName("Developer");
        // division.setregionId(1);
        // System.out.println(ddao.insert(division));
    }
}
