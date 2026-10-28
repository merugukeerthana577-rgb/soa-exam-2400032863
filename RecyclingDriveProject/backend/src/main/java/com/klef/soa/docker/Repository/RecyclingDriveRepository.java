package com.klef.soa.docker.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.klef.soa.docker.Entity.RecyclingDrive;

@Repository
public interface RecyclingDriveRepository extends JpaRepository<RecyclingDrive, Long> 
	{

	}


