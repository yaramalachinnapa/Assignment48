package com.balazsholczer.ui.universities;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.balazsholczer.model.entity.University;
import com.balazsholczer.service.showuniversities.ShowAllUniversitiesService;
import com.balazsholczer.ui.views.UIComponentBuilder;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.Component;
import com.vaadin.ui.Grid;
import com.vaadin.ui.VerticalLayout;


@org.springframework.stereotype.Component
public class ShowUniversityLayoutFactory implements UIComponentBuilder {

	private List<University> universities;
	private BeanItemContainer<University> container;
	
	private class ShowUniversityLayout extends VerticalLayout {

		private Grid universityTable;

		public ShowUniversityLayout init() {

			setMargin(true);
			container = new BeanItemContainer<University>(University.class, universities);

			universityTable = new Grid(container);
			universityTable.setColumnOrder("universityName", "universityCountry", "universityCity");
			universityTable.removeColumn("id");
			universityTable.setImmediate(true);
			
			return this;
		}

		public ShowUniversityLayout layout() {
			addComponent(universityTable);
			return this;
		}

		public ShowUniversityLayout load() {
			universities = showUniversitiesService.getAllUniversities();
			return this;
		}
	}

	public void refreshTables() {
		universities = showUniversitiesService.getAllUniversities();
		container.removeAllItems();
		container.addAll(universities);
	}

	@Autowired
	private ShowAllUniversitiesService showUniversitiesService;

	public Component createComponent() {
		return new ShowUniversityLayout().load().init().layout();
	}
}