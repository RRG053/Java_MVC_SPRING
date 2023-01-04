package com.example.demo.models;
import javax.persistence.*;

@Entity
@Table(name = "division")

public class Division {
    @Id
    @Column (name = "divisionId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int divisionId;

    @Column (name = "divisionName", nullable = false)
    private String divisionName;

    @ManyToOne
    private int regionid;

    public int getDivisionId(){
        return divisionId;
    }

    public void setDivisionId(Integer divisionId){
        this.divisionId = divisionId;
    }

    public String getDivisionName(){
        return divisionName;
    }
    
    public void setDivisionName(String divisionName){
        this.divisionName = divisionName;
    }
    public int getregionId(){
        return regionid;
    }

    public void setregionId(Integer regionid){
        this.regionid = regionid;
    }
}