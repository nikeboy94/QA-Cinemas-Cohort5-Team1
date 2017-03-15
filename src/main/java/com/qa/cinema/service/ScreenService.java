package com.qa.cinema.service;

public interface ScreenService {
	String getAllScreens();
	
	String createNewScreen(String screen);
	
	String deleteScreen(Long id);

}
