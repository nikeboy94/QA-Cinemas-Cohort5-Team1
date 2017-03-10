package com.qa.cinema.service;

import java.util.*;

import javax.inject.Inject;
import javax.persistence.*;
import javax.persistence.PersistenceContext;

import com.qa.cinema.persistence.Seat;
import com.qa.cinema.util.JSONUtil;

public class DBSeatService implements SeatService {
	@PersistenceContext(unitName = "primary")
	private EntityManager em;

	@Inject
	private JSONUtil util;

	@Override
	public String createSeat(String seat) {
		Seat newSeat = util.getObjectForJSON(seat, Seat.class);
		em.persist(newSeat);
		return "{\"message\": \"Seat successfully added\"}";
	}

	@Override
	public String updateSeat(Long seatId, String seat) {
		Seat updateSeat = util.getObjectForJSON(seat, Seat.class);
		Seat seatInDB = findSeat(seatId);
		if (seatInDB != null) {
			seatInDB = updateSeat;
			em.merge(seatInDB);
			return "{\"message\": \"Seat successfully updated\"}";
		} else {
			return "{\"message\": \"Updating seat failed\"}";
		}

	}

	@Override
	public String deleteSeat(Long seatId) {
		Seat seatInDB = findSeat(seatId);
		if (seatInDB != null) {
			em.remove(seatInDB);
			return "{\"message\": \"Seat successfully deleted\"}";
		} else {
			return "{\"message\": \"Deleting seat failed\"}";
		}

	}

	@Override
	public String findByScreenId(Long screenId) {
		Query query = em.createQuery("SELECT s FROM Seat s where s.screenId=:screenId").setParameter("screenId", screenId);
		Collection<Seat> seats = (Collection<Seat>) query.getResultList();
		return util.getJSONForObject(seats);
	}

	private Seat findSeat(Long id) {
		return em.find(Seat.class, id);
	}

}
