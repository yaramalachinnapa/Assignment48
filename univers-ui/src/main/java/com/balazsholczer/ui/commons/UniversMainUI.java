package com.balazsholczer.ui.commons;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import com.balazsholczer.navigator.UniversNavigator;
import com.balazsholczer.ui.students.StudentLayoutFactory;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.navigator.SpringViewProvider;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;


@SpringUI(path = UniversMainUI.NAME)
@Title("U n i v e r s")
@Theme("valo")
public class UniversMainUI extends UI {

	public static final String NAME = "/ui";
	
	@Autowired
	private ApplicationContext appContext;
	
	@Autowired
	private SpringViewProvider viewProvider;
	
	@Autowired
	private UniversMenuFactory menuFactory;
	
	@Autowired
	private UniversLogoLayoutFactory logoFactory;
	
	private Panel changeTab = new Panel();
	
	@Override
	protected void init(VaadinRequest request) {

		changeTab.setHeight("100%");
		
		VerticalLayout root = new VerticalLayout();
		root.setSizeFull();
		root.setMargin(true);
		
		Panel content = new Panel();
		content.setWidth("75%");
		content.setHeight("100%");
		
		Panel logoContent = new Panel();
		logoContent.setWidth("75%");
		logoContent.setHeightUndefined();
		
		HorizontalLayout uiLayout = new HorizontalLayout();
		uiLayout.setSizeFull();
		uiLayout.setMargin(true);
		
		Component logo = logoFactory.createComponent();
		Component menu = menuFactory.createComponent();
		
		uiLayout.addComponent(menu);
		uiLayout.addComponent(changeTab);
		
		uiLayout.setComponentAlignment(changeTab, Alignment.TOP_CENTER);
		uiLayout.setComponentAlignment(menu, Alignment.TOP_CENTER);
		
		uiLayout.setExpandRatio(menu, 1);
		uiLayout.setExpandRatio(changeTab, 2);
		
		logoContent.setContent(logo);
		content.setContent(uiLayout);
		
		root.addComponent(logoContent);
		root.addComponent(content);
		root.setComponentAlignment(content, Alignment.MIDDLE_CENTER);
		root.setComponentAlignment(logoContent, Alignment.TOP_CENTER);
		root.setExpandRatio(content, 1);
		
		initializeNavigator();
		
		setContent(root);
	}

	private void initializeNavigator() {
		UniversNavigator navigator = new UniversNavigator(this, changeTab);
		appContext.getAutowireCapableBeanFactory().autowireBean(navigator);
		navigator.addProvider(viewProvider);
		navigator.navigateTo(StudentLayoutFactory.NAME);
	}
}
