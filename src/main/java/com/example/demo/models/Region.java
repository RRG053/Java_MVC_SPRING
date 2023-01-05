package com.example.demo.models;

// import javax.persistence.Entity;
import javax.persistence.*;

@Entity
@Table(name = "regions")

public class Region {
    @Id
    @Column (name = "regionId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @OneToMany(mappedBy = "division")    
    private Integer regionId;

    @Column (name = "regionName", nullable = false)
    private String regionName;

    public int getRegionId(){
        return regionId;
    }

    public void setRegionId(Integer regionId){
        this.regionId = regionId;
    }

    public String getRegionName(){
        return regionName;
    }
    
    public void setRegionName(String regionName){
        this.regionName = regionName;
    }
    
}