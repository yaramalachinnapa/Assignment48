package com.balazsholczer.ui.universities;

import org.springframework.beans.factory.annotation.Autowired;
import com.balazsholczer.model.entity.University;
import com.balazsholczer.service.adduniversity.AddUniversityService;
import com.balazsholczer.ui.views.UIComponentBuilder;
import com.balazsholczer.utils.NotificationMessages;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.themes.ValoTheme;
import com.vaadin.ui.Component;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;

@org.springframework.stereotype.Component
public class AddUniversityLayoutFactory {

	private class AddUniversityLayout extends VerticalLayout implements Button.ClickListener {

		private TextField universityName;
		private TextField universityCountry;
		private TextField universityCity;
		private Button saveButton;
		private BeanFieldGroup<University> fieldGroup;
		private University university;
		
		private UniversitySavedListener universitySavedListener;
		
		public AddUniversityLayout(UniversitySavedListener universitySavedListener) {
			this.universitySavedListener = universitySavedListener;
			this.university = new University();
		}

		public AddUniversityLayout init() {

			universityName = new TextField("Name");
			universityCountry = new TextField("Country");
			universityCity = new TextField("City");
			
			saveButton = new Button("Save", this);
			saveButton.setStyleName(ValoTheme.BUTTON_FRIENDLY);
			
			universityName.setNullRepresentation("");
			universityCountry.setNullRepresentation("");
			universityCity.setNullRepresentation("");

			return this;
		}
		
		public AddUniversityLayout bind() {

			fieldGroup = new BeanFieldGroup<University>(University.class);
			fieldGroup.bindMemberFields(this);
			fieldGroup.setItemDataSource(university);

			return this;
		}

		public Component layout() {
			
			setWidth("100%");

			GridLayout grid = new GridLayout(2,4);
			grid.setHeightUndefined();
			grid.setSpacing(true);
			
			grid.addComponent(universityName,0,0,1,0);
			grid.addComponent(universityCountry,0,1,1,1);
			grid.addComponent(universityCity,0,2,1,2);
			grid.addComponent(new HorizontalLayout(saveButton),0,3,0,3);

			return grid;
		}

		public void buttonClick(ClickEvent event) {
			
			try {
				fieldGroup.commit();
			} catch (CommitException e) {
				Notification.show(NotificationMessages.STUDENT_SAVE_VALIDATION_ERROR_TITLE.getString(),
						NotificationMessages.STUDENT_SAVE_VALIDATION_ERROR_DESCRIPTION.getString(), Type.ERROR_MESSAGE);
				return;
			}
			
			clearFields();
			addUniversityService.addUniversity(university);
			universitySavedListener.universitySaved();
			Notification.show("SAVE", "University successfully saved!", Type.WARNING_MESSAGE);
		}

		private void clearFields() {
			universityName.setValue(null);
			universityCountry.setValue(null);
			universityCity.setValue(null);
		}
	}
	
	@Autowired
	private AddUniversityService addUniversityService;

	public Component createComponent(UniversitySavedListener universitySavedListener) {
		return new AddUniversityLayout(universitySavedListener).init().bind().layout();
	}
}
