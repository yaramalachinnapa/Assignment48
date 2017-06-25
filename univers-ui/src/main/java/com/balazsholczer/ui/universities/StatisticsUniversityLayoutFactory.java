package com.balazsholczer.ui.universities;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.balazsholczer.model.entity.University;
import com.balazsholczer.service.showuniversities.ShowAllUniversitiesService;
import com.balazsholczer.service.universitystats.UniversityStatsService;
import com.balazsholczer.ui.views.UIComponentBuilder;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

@org.springframework.stereotype.Component
public class StatisticsUniversityLayoutFactory implements UIComponentBuilder {
	
	private List<University> universities;
	
	private class StatisticsUniversityLayout extends VerticalLayout {
		
		public Component layout() {
			
			setMargin(true);
			
			for(University university : universities) {
				int numOfStudent = universityStatsService.getStatistics(university.getId());
				Label label = new Label("<p><b>"+university.getUniversityName()+"</b>"+"  -  "+numOfStudent+" students"+"</p>", ContentMode.HTML);
				addComponent(label);
			}
			
			return this;
		}
		
		public StatisticsUniversityLayout load() {
			universities = universitiesService.getAllUniversities();
			return this;
		}
	}
	
	@Autowired
	private UniversityStatsService universityStatsService;
	
	@Autowired
	private ShowAllUniversitiesService universitiesService;
	
	public Component createComponent() {
		return new StatisticsUniversityLayout().load().layout();
	}
}
