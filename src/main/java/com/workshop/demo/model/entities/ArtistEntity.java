package com.workshop.demo.model.entities;



import com.google.gson.annotations.Expose;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "artists")
public class ArtistEntity extends BaseEntity {

    @Expose
    private String name;
    @Expose
    private String careerInformation;

    public ArtistEntity() {
    }


    @Column(nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "career_information",nullable = false, columnDefinition = "TEXT")
    public String getCareerInformation() {
        return careerInformation;
    }

    public void setCareerInformation(String careerInformation) {
        this.careerInformation = careerInformation;
    }
}
