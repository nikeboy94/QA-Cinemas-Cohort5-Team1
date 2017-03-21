package com.qa.cinema.util.answer;

import java.util.Collection;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import com.qa.cinema.persistence.Showing;

public class RemoveShowing implements Answer<Void>{
	
	private Collection<Showing> showings;
	
	public RemoveShowing(Collection<Showing> showings) {
		this.showings = showings;
	}

	@Override
	public Void answer(InvocationOnMock invocation) throws Throwable {
		Showing deleteShowing = (Showing) invocation.getArguments()[0];
		showings.remove(deleteShowing);
		return null;
	}

}
