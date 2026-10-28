
package com.klef.soa.docker.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

public class RecyclingDriveRequest {

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
