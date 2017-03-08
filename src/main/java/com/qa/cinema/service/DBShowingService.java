package com.qa.cinema.service;

import java.util.Collection;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.qa.cinema.persistence.Showing;
import com.qa.cinema.util.JSONUtil;

@Stateless
@Default
public class DBShowingService implements ShowingService {

	@PersistenceContext(unitName = "primary")
	private EntityManager manager;
	
	@Inject
	private JSONUtil util;
	
	@Override
	public String getAllShowings() {
		Query query = manager.createQuery("SELECT s FROM Showing s");
		Collection<Showing> showings = (Collection<Showing>) query.getResultList();
		return util.getJSONForObject(showings);
	}

	@Override
	public String createShowing(String showing) {
		Showing aShowing = util.getObjectForJSON(showing, Showing.class);
		manager.persist(aShowing);
		return "{\"message\": \"Showing sucessfully added\"}";
	}

	@Override
	public String updateShowing(Long showingId, String showing) {
		Showing updatedShowing = util.getObjectForJSON(showing, Showing.class);
		Showing showingInDB = findShowing(showingId);
		
		if (showingInDB != null) {
			showingInDB.setScreen(updatedShowing.getScreen());
			showingInDB.setMovie(updatedShowing.getMovie());
			showingInDB.setDateTime(updatedShowing.getDateTime());
			manager.merge(showingInDB);
			return "{\"message\": \"Showing sucessfully updated\"}";
		} else {
			return "{\"message\": \"Showing failed to update\"}";
		}
	}

	@Override
	public String deleteShowing(Long showingId) {
		Showing showingInDB = findShowing(showingId);
		if (showingInDB != null) {
			manager.remove(showingInDB);
			return "{\"message\": \"Showing sucessfull deleted\"}";
		} else {
			return "{\"message\": \"Showing failed to delete\"}";
		}
	}
	
	private Showing findShowing(Long id) {
		return manager.find(Showing.class, id);
	}

}
