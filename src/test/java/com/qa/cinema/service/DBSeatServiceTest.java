package com.qa.cinema.service;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.doAnswer;
import com.qa.cinema.persistence.Movie;
import com.qa.cinema.persistence.Screen;
import com.qa.cinema.persistence.Seat;
import com.qa.cinema.persistence.Showing;
import com.qa.cinema.util.JSONUtil;
import com.qa.cinema.util.seat.MergeSeat;
import com.qa.cinema.util.seat.PersistSeat;
import com.qa.cinema.util.showing.MergeShowing;
import com.qa.cinema.util.showing.PersistShowing;
import com.qa.cinema.util.showing.RemoveShowing;
import static com.qa.cinema.util.MockJSONUtil.*;
import org.junit.Test;

@RunWith(MockitoJUnitRunner.class)
public class DBSeatServiceTest {

	@Mock
	JSONUtil util;
	
	@Mock
	EntityManager em;
	
	@InjectMocks
	DBSeatService service = new DBSeatService();
	
	Query query;
	Collection<Seat> seats;
	Seat seat;
	Screen screen;
	
	@Before
	public void setup() {
		query = mock(Query.class);
		seats = new ArrayList<Seat>();
		seat = new Seat("row", "column");
		screen = new Screen("row", "col");
		seat.setScreen(screen);
	}
	
	@Test
	public void testCreateSeat() {
		resetSeats();
		Seat newSeat = new Seat();
		newSeat.setScreen(screen);
		String json = "newSeat json";
		
		when(util.getObjectForJSON(json, Seat.class)).thenReturn(newSeat);
		doAnswer(new PersistSeat(seats)).when(em).persist(newSeat);
		
		service.createSeat(json);
		
		assertTrue(seats.contains(newSeat));
	}
	
	@Test
	public void testUpdateSeat() {
		resetSeats();
		Seat updatedSeat = new Seat();
		String id = null;
		String json = "updatedSeat json";
		
		when(util.getObjectForJSON(json, Seat.class)).thenReturn(updatedSeat);
		when(em.find(Seat.class, id)).thenReturn(findSeat(id));
		doAnswer(new MergeSeat(seats)).when(em).merge(updatedSeat);
		
		service.updateSeat(null, json);
	}
	
	public void resetSeats() {
		seats.clear();
		seats.add(seat);
	}
	
	public Seat findSeat(String id) {
		for (Seat seat : seats) {
			if (seat.getSeatId() == id) {
				return seat;
			}
		}
		return null;
	}

}
