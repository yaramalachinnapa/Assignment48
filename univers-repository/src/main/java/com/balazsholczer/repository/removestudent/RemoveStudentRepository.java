package com.balazsholczer.repository.removestudent;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.balazsholczer.model.entity.Student;

@Repository
public interface RemoveStudentRepository extends JpaRepository<Student, Integer> {

}
