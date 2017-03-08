package com.qa.cinema.persistence;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Screen {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String screenType;
	private String screenDesc;

	public Screen(){
		//Empty constructor
	}

	public Screen(String screenType, String screenDesc) {
		super();
		this.screenType = screenType;
		this.screenDesc = screenDesc;
	}

	public String getScreenType() {
		return screenType;
	}

	public void setScreenType(String screenType) {
		this.screenType = screenType;
	}

	public String getScreenDesc() {
		return screenDesc;
	}

	public void setScreenDesc(String screenDesc) {
		this.screenDesc = screenDesc;
	}

	public Long getId() {
		return id;
	}
	
}
