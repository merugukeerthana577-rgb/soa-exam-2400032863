package com.klef.soa.exam.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.klef.soa.exam.Entity.RecyclingDrive;

@Repository
public interface RecyclingDriveRepository extends JpaRepository<RecyclingDrive, Long> 
	{

	}


