package com.balazsholczer.service.showstudents;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.balazsholczer.model.entity.Student;
import com.balazsholczer.repository.addstudent.ShowAllStudentsRepository;

@Service
public class ShowStudentsServiceImpl implements ShowStudentsService {

	@Autowired
	private ShowAllStudentsRepository showAllStudentsRepository;
	
	public List<Student> getAllStudents() {
		return showAllStudentsRepository.getAllStudents();
	}

}
