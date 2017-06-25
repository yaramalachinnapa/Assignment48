package com.balazsholczer.ui.students;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.balazsholczer.model.entity.Student;
import com.balazsholczer.model.entity.University;
import com.balazsholczer.service.addstudent.AddStudentService;
import com.balazsholczer.service.showuniversities.ShowAllUniversitiesService;
import com.balazsholczer.utils.Gender;
import com.balazsholczer.utils.NotificationMessages;
import com.balazsholczer.utils.StringUtils;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@org.springframework.stereotype.Component
public class AddStudentMainLayoutFactory {

	private class AddStudentMainLayout extends VerticalLayout implements ClickListener {

		private static final long serialVersionUID = 1L;
		private TextField firstName;
		private TextField lastName;
		private TextField age;
		private ComboBox gender;
		private Button saveButton;
		private Button clearButton;
		private ComboBox university;

		private StudentSavedListener studentSavedListener;

		private BeanFieldGroup<Student> fieldGroup;
		private Student student;

		public AddStudentMainLayout(StudentSavedListener studentSavedListener) {
			this.studentSavedListener = studentSavedListener;
			this.student = new Student();
		}

		public AddStudentMainLayout init() {

			fieldGroup = new BeanFieldGroup<Student>(Student.class);

			firstName = new TextField(StringUtils.FIRST_NAME.getString());
			lastName = new TextField(StringUtils.LAST_NAME.getString());
			age = new TextField(StringUtils.AGE.getString());
			gender = new ComboBox(StringUtils.GENDER.getString());
			university = new ComboBox(StringUtils.UNIVERSITY.getString());
			university.setWidth("100%");

			firstName.setNullRepresentation("");
			lastName.setNullRepresentation("");
			age.setNullRepresentation("");

			saveButton = new Button(StringUtils.SAVE.getString());
			clearButton = new Button(StringUtils.CANCEL.getString());
			saveButton.addClickListener(this);
			clearButton.addClickListener(this);

			saveButton.setStyleName(ValoTheme.BUTTON_FRIENDLY);
			clearButton.setStyleName(ValoTheme.BUTTON_PRIMARY);

			gender.addItem(Gender.MALE.getString());
			gender.addItem(Gender.FEMALE.getString());

			return this;
		}

		public AddStudentMainLayout bind() {
			fieldGroup.bindMemberFields(this);
			fieldGroup.setItemDataSource(student);
			return this;
		}

		public Component layout() {

			setMargin(true);

			GridLayout layout = new GridLayout(2, 4);
			layout.setSizeUndefined();
			layout.setSpacing(true);

			layout.addComponent(firstName, 0, 0);
			layout.addComponent(lastName, 1, 0);

			layout.addComponent(age, 0, 1);
			layout.addComponent(gender, 1, 1);

			layout.addComponent(university, 0, 2, 1, 2);

			layout.addComponent(new HorizontalLayout(saveButton, clearButton), 0, 3);

			age.clear();

			return layout;
		}

		public void buttonClick(ClickEvent event) {
			if (event.getSource() == this.saveButton) {
				save();
			} else {
				clearFields();
			}
		}

		private void clearFields() {
			firstName.setValue(null);
			lastName.setValue(null);
			age.setValue(null);
			gender.setValue(null);
			university.setValue(null);
		}

		private void save() {
			if (!isOperationValid()) {
				Notification.show(NotificationMessages.STUDENT_SAVE_ERROR_TITLE.getString(),
						NotificationMessages.STUDENT_SAVE_ERROR_DESCRIPTION.getString(), Type.ERROR_MESSAGE);
			} else {
				saveStudent();
			}
		}

		private void saveStudent() {
			try {
				fieldGroup.commit();
			} catch (CommitException e) {
				Notification.show(NotificationMessages.STUDENT_SAVE_VALIDATION_ERROR_TITLE.getString(),
						NotificationMessages.STUDENT_SAVE_VALIDATION_ERROR_DESCRIPTION.getString(), Type.ERROR_MESSAGE);
				return;
			}

			addStudentService.saveStudent(student);
			studentSavedListener.studentSaved();

			Notification.show(NotificationMessages.STUDENT_SAVE_SUCCESS_TITLE.getString(),
					NotificationMessages.STUDENT_SAVE_SUCCESS_DESCRIPTION.getString(), Type.WARNING_MESSAGE);

			clearFields();
		}

		public AddStudentMainLayout load() {

			List<University> universities = showAllUniversitiesService.getAllUniversities();
			university.addItems(universities);

			return this;
		}
	}

	private boolean isOperationValid() {
		return showAllUniversitiesService.getAllUniversities().size() != 0;
	}

	@Autowired
	private AddStudentService addStudentService;

	@Autowired
	private ShowAllUniversitiesService showAllUniversitiesService;

	public Component createComponent(StudentSavedListener studentSavedListener) {
		return new AddStudentMainLayout(studentSavedListener).init().load().bind().layout();
	}
}
