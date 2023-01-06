package com.example.demo.models;

// import javax.persistence.Entity;
import javax.persistence.*;

@Entity
@Table(name = "regions")

public class Region {
    @Id
    @Column (name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @OneToMany(mappedBy = "division")    
    private Integer Id;

    @Column (name = "name")
    private String Name;

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
    
}