package com.example.demo.models;
import javax.persistence.*;

@Entity
@Table(name = "division")

public class Division {
    @Id
    @Column (name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    @Column (name = "name", nullable = false)
    private String Name;

    @ManyToOne
    @JoinColumn(name = "regionId")
    private Region region;

    public Integer getId(){
        return Id;
    }

    public void setId(Integer Id){
        this.Id = Id;
    }

    public String getName(){
        return Name;
    }
    
    public void setName(String Name){
        this.Name = Name;
    }
    public Region getregion(){
        return region;
    }

    public void setregion(Region region){
        this.region = region;
    }
}