
package com.klef.soa.docker.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klef.soa.docker.Entity.RecyclingDrive;
import com.klef.soa.docker.Repository.RecyclingDriveRepository;

@Service
public class RecyclingDriveServiceImpl implements RecyclingDriveService {

    @Autowired
    private RecyclingDriveRepository repo;

    @Override
    public RecyclingDrive add(RecyclingDrive drive) {
        return repo.save(drive);
    }

    @Override
    public List<RecyclingDrive> displayAll() {
        return repo.findAll();
    }

    @Override
    public String deleteById(Long id) {
        repo.deleteById(id);
        return "Recycling Drive Deleted Successfully";
    }
}
