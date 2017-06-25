package com.balazsholczer.repository.addstudent;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.balazsholczer.model.entity.Student;

@Repository
public interface AddStudentRepository extends JpaRepository<Student, Integer>  {

	
}
