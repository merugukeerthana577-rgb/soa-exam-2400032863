package com.klef.soa.exam.Service;

import java.util.List;
import com.klef.soa.exam.Entity.RecyclingDrive;

public interface RecyclingDriveService {

    RecyclingDrive add(RecyclingDrive drive);

    List<RecyclingDrive> displayAll();
    
    String deleteById(Long id);
}