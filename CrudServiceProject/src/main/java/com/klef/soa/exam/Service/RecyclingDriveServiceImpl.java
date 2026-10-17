
package com.klef.soa.exam.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klef.soa.exam.Entity.RecyclingDrive;
import com.klef.soa.exam.Repository.RecyclingDriveRepository;

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
        if (repo.existsById(id)) {
            repo.deleteById(id);
            return "Recycling Drive Deleted Successfully";
        } else {
            return "Recycling Drive ID Not Found";
        }
    }
}
