package com.qa.cinema.util.screen;

import java.util.Collection;

import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import com.qa.cinema.persistence.Screen;

public class PersistScreen implements Answer<Void> {
	
	private Collection<Screen> screens;
	
	public PersistScreen(Collection<Screen> screens) {
		this.screens = screens;
	}

	@Override
	public Void answer(InvocationOnMock invocation) throws Throwable {
		Screen screen = (Screen) invocation.getArguments()[0];
		screens.add(screen);
		return null;
	}

}
