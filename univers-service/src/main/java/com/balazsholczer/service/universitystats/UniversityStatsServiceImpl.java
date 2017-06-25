package com.balazsholczer.service.universitystats;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.balazsholczer.repository.university.UniversityStatisticsRepository;

@Service
public class UniversityStatsServiceImpl implements UniversityStatsService {

	@Autowired
	private UniversityStatisticsRepository getUniversityStatisticsRepository;
	
	public Integer getStatistics(Integer universityId) {
		return getUniversityStatisticsRepository.getNumOfStudentsForUniversity(universityId);
	}

}
