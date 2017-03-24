package com.qa.cinema.util.user;

import java.util.Collection;
import java.util.List;

import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import com.qa.cinema.persistence.User;

public class MergeUser implements Answer<Void> {
	
private Collection<User> users;
	
	public MergeUser(Collection<User> users) {
		this.users = users;
	}

	@Override
	public Void answer(InvocationOnMock invocation) throws Throwable {
		User updatedUser = (User) invocation.getArguments()[0];
		for (User user : users) {
			if (user.getEmail().equals(updatedUser.getEmail())) {
				int index = ((List<User>) users).indexOf(user);
				((List<User>) users).set(index, updatedUser);
			}
		}
		return null;
	}

}
