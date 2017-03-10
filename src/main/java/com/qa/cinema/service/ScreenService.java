package com.qa.cinema.service;

public interface ScreenService {
	String listAllScreens();
	
	String createNewScreen(String screen);
	
	String deleteScreen(Long id);

}
