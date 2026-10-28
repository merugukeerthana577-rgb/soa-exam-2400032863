
package com.klef.soa.docker.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.klef.soa.docker.Entity.RecyclingDrive;
import com.klef.soa.docker.Service.RecyclingDriveService;

import jakarta.validation.Valid;
@CrossOrigin(origins = {
    "http://localhost:3000",
    "http://localhost:5173"
})
@RestController
@RequestMapping("/RecyclingDrive")
public class RecyclingDriveController {

    @Autowired
    private RecyclingDriveService service;

  
    @GetMapping("/")
    public String test() {
        return "CRUD Service is running";
    }

 
    @PostMapping("/Add")
    public ResponseEntity<RecyclingDrive> add(
        @Valid    @RequestBody RecyclingDrive drive) {

        RecyclingDrive saved = service.add(drive);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    
    @GetMapping("/DisplayAll")
    public ResponseEntity<List<RecyclingDrive>> displayAll() {

        List<RecyclingDrive> drives = service.displayAll();
        return ResponseEntity.ok(drives);
    }

    
    @DeleteMapping("/Delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Long id) {

        String message = service.deleteById(id);

        

        return ResponseEntity.ok(message);
    }
}
