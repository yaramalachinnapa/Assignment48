package com.balazsholczer.service.adduniversity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.balazsholczer.model.entity.University;
import com.balazsholczer.repository.university.AddUniversityRepository;

@Service
public class AddUniversityServiceImpl implements AddUniversityService {

	@Autowired
	private AddUniversityRepository addUniversityRepository;
	
	public void addUniversity(University universityDAO) {
		
		University university = new University();
		university.setUniversityName(universityDAO.getUniversityName());
		university.setUniversityCountry(universityDAO.getUniversityCountry());
		university.setUniversityCity(universityDAO.getUniversityCity());
		
		addUniversityRepository.save(university);
	}
}
