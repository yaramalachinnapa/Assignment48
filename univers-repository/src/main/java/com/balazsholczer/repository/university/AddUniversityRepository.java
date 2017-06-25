package com.balazsholczer.repository.university;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.balazsholczer.model.entity.University;

@Repository
public interface AddUniversityRepository extends JpaRepository<University, Integer>  {

	
}
