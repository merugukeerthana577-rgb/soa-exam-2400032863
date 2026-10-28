package com.klef.soa.docker.Service;

import java.util.List;

import com.klef.soa.docker.Entity.RecyclingDrive;

public interface RecyclingDriveService {

    RecyclingDrive add(RecyclingDrive drive);

    List<RecyclingDrive> displayAll();
    
    String deleteById(Long id);
}