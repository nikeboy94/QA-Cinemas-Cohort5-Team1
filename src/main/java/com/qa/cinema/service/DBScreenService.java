package com.qa.cinema.service;

import java.util.Collection;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.qa.cinema.persistence.Screen;
import com.qa.cinema.util.JSONUtil;

@Stateless
@Default
public class DBScreenService implements ScreenService{
	
	@PersistenceContext(unitName = "primary")
	private EntityManager em;
	
	@Inject
	private JSONUtil util;

	@Override
	public String getAllScreens() {
		Query query = em.createQuery("SELECT s FROM Screen s");
		Collection<Screen> screens = (Collection<Screen>) query.getResultList();
		return util.getJSONForObject(screens);
	}

	@Override
	public String createNewScreen(String screen) {
		Screen newScreen = util.getObjectForJSON(screen, Screen.class);
		em.persist(newScreen);
		return "{\"message\": \"Screen successfully added\"}";
	}

	@Override
	public String deleteScreen(Long id) {
		Screen screenInDB = findScreen(id);
		if(screenInDB != null){
			em.remove(screenInDB);
		}
		return "(\"message\": \"Screen successfully deleted\")";
	}

	private Screen findScreen(Long id) {
		return em.find(Screen.class, id);
	}

}
