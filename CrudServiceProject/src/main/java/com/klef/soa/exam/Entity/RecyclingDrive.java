package com.klef.soa.exam.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class RecyclingDrive {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String driveLocation;
    private String materialType;
    private Double collectedKg;
    private Boolean completed;

    public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDriveLocation() {
		return driveLocation;
	}
	public void setDriveLocation(String driveLocation) {
		this.driveLocation = driveLocation;
	}
	public String getMaterialType() {
		return materialType;
	}
	public void setMaterialType(String materialType) {
		this.materialType = materialType;
	}
	public Double getCollectedKg() {
		return collectedKg;
	}
	public void setCollectedKg(Double collectedKg) {
		this.collectedKg = collectedKg;
	}
	public Boolean getCompleted() {
		return completed;
	}
	public void setCompleted(Boolean completed) {
		this.completed = completed;
	}
	
}
   