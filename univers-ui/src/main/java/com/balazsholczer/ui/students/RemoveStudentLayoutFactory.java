package com.balazsholczer.ui.students;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.balazsholczer.model.entity.Student;
import com.balazsholczer.service.removestudent.RemoveStudentService;
import com.balazsholczer.service.showstudents.ShowStudentsService;
import com.balazsholczer.ui.commons.UniversMainUI;
import com.balazsholczer.utils.NotificationMessages;
import com.balazsholczer.utils.StringUtils;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Grid;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Grid.MultiSelectionModel;
import com.vaadin.ui.Grid.SelectionMode;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.themes.ValoTheme;
import com.vaadin.ui.VerticalLayout;

@SpringView(name = RemoveStudentLayoutFactory.NAME, ui = UniversMainUI.class)
public class RemoveStudentLayoutFactory extends VerticalLayout implements View, Button.ClickListener {

	@Autowired
	private ShowStudentsService studentService;
	
	@Autowired
	private RemoveStudentService removeStudentService;
	
	public static final String NAME = "removestudent";
	private Grid removeStudentTable;
	private Button removeStudentsButton;
	private List<Student> students;
	
	private void addLayout() {
		
		removeStudentsButton = new Button(StringUtils.REMOVE_STUDENT.getString());
		
		setMargin(true);
		BeanItemContainer<Student> container = new BeanItemContainer<Student>(Student.class, students);

		removeStudentTable = new Grid(container);
		removeStudentTable.setColumnOrder("firstName", "lastName", "age", "gender");
		removeStudentTable.removeColumn("id");
		removeStudentTable.removeColumn("university");
		removeStudentTable.setImmediate(true);
		removeStudentTable.setSelectionMode(SelectionMode.MULTI);
		
		removeStudentsButton.addClickListener(this);
		removeStudentsButton.setStyleName(ValoTheme.BUTTON_FRIENDLY);
		
		addComponent(removeStudentTable);
		addComponent(removeStudentsButton);
	}

	public void buttonClick(ClickEvent event) {
		
		MultiSelectionModel selectionModel = (MultiSelectionModel) removeStudentTable.getSelectionModel();
		
		for(Object selectedItem : selectionModel.getSelectedRows()) {
			Student student = (Student) selectedItem;
			removeStudentTable.getContainerDataSource().removeItem(student);
			removeStudentService.removeStudent(student);
		}
		
		Notification.show(NotificationMessages.STUDENT_REMOVE_SUCCESS_TITLE.getString(),
				NotificationMessages.STUDENT_REMOVE_SUCCESS_DESCRIPTION.getString(), Type.WARNING_MESSAGE);
		
		removeStudentTable.getSelectionModel().reset();
	}

	private void loadStudents() {
		students = studentService.getAllStudents();
	}
	
	public void enter(ViewChangeEvent event) {
		
		if( removeStudentTable != null ) return; 
		
		loadStudents();
		addLayout();
	}
}
