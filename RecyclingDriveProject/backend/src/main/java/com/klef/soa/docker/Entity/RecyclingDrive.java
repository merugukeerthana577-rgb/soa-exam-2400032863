
package com.klef.soa.docker.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

@Entity
@Table
public class RecyclingDrive {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Drive location is required")
    @Size(max = 100, message = "Location must not exceed 100 characters")
    private String driveLocation;

    @NotBlank(message = "Material type is required")
    @Size(max = 50, message = "Material type must not exceed 50 characters")
    private String materialType;

    @NotNull(message = "Collected kilograms is required")
    @PositiveOrZero(message = "Collected kilograms cannot be negative")
    private Double collectedKg;

    @NotNull(message = "Completed status is required")
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
