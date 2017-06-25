package com.balazsholczer.ui.universities;

import org.springframework.beans.factory.annotation.Autowired;

import com.balazsholczer.ui.commons.UniversMainUI;
import com.balazsholczer.utils.StringUtils;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.TabSheet.SelectedTabChangeEvent;

@SpringView(name = UniversityLayoutFactory.NAME, ui=UniversMainUI.class)
public class UniversityLayoutFactory extends VerticalLayout implements View, UniversitySavedListener {

	public static final String NAME = "operations";
	private TabSheet tabSheet;

	@Autowired
	private AddUniversityLayoutFactory addUniversityFactory;

	@Autowired
	private ShowUniversityLayoutFactory showUniversitiesFactory;
	
	@Autowired
	private StatisticsUniversityLayoutFactory statisticsUniversityFactory;

	private void addLayout() {

		setMargin(true);

		tabSheet = new TabSheet();
		tabSheet.setWidth("100%");
		
		Component addUniversityTab = addUniversityFactory.createComponent(this);				
		Component showUniversityTab = showUniversitiesFactory.createComponent();	
		Component chartUniversityTab = statisticsUniversityFactory.createComponent();	
		
		tabSheet.addTab(addUniversityTab, StringUtils.MENU_ADD_UNIVERSITY.getString());
		tabSheet.addTab(showUniversityTab, StringUtils.MENU_SHOW_UNIVERSITY.getString());
		tabSheet.addTab(chartUniversityTab, StringUtils.MENU_CHART_UNIVERSITY.getString());
		
		addComponent(tabSheet);
	}
	
	public void universitySaved() {
		showUniversitiesFactory.refreshTables();
	}
	
	public void enter(ViewChangeEvent event) {
		removeAllComponents();
		addLayout();
	}
}
