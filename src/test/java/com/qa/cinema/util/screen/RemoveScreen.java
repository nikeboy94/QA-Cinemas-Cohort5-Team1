package com.qa.cinema.util.screen;

import java.util.Collection;

import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import com.qa.cinema.persistence.Screen;

public class RemoveScreen implements Answer<Void> {
	
	private Collection<Screen> screens;
	
	public RemoveScreen(Collection<Screen> screens) {
		this.screens = screens;
	}
	
	public Void answer(InvocationOnMock invocation) {
		Screen screenToDelete = (Screen) invocation.getArguments()[0];
		screens.remove(screenToDelete);
		return null;
	}

}
