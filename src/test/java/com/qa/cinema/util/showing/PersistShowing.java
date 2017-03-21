package com.qa.cinema.util.answer;

import org.mockito.stubbing.Answer;
import com.qa.cinema.persistence.Showing;
import java.util.Collection;
import org.mockito.invocation.InvocationOnMock;

public class PersistShowing implements Answer<Void> {
	
	private Collection<Showing> showings;
	
	public PersistShowing(Collection<Showing> showings) {
		this.showings = showings;
	}
	
	@Override
	public Void answer(InvocationOnMock invocation) throws Throwable {
		Showing showingToPersist = (Showing) invocation.getArguments()[0];
		showings.add(showingToPersist);
		return null;
	}

}
