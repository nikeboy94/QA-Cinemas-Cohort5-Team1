package com.qa.cinema.util.showing;

import java.util.Collection;
import java.util.List;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import com.qa.cinema.persistence.Showing;

public class MergeShowing implements Answer<Void>{
	
	private Collection<Showing> showings;
	
	public MergeShowing(Collection<Showing> showings) {
		this.showings = showings;
	}

	@Override
	public Void answer(InvocationOnMock invocation) throws Throwable {
		Showing updatedShowing = (Showing) invocation.getArguments()[0];
		for (Showing showing : showings) {
			if (showing.getShowingId() == updatedShowing.getShowingId()) {
				int index = ((List<Showing>) showings).indexOf(showing);
				((List<Showing>) showings).set(index, updatedShowing);
			}
		}
		return null;
	}

}
