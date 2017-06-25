package com.balazsholczer.service.removestudent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.balazsholczer.model.entity.Student;
import com.balazsholczer.repository.removestudent.RemoveStudentRepository;

@Service
public class RemoveStudentServiceImpl implements RemoveStudentService {

	@Autowired
	private RemoveStudentRepository removeStudentRepository;
	
	public void removeStudent(Student student) {
		removeStudentRepository.delete(student);
	}
}
