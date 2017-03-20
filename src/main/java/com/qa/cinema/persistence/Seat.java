package com.qa.cinema.persistence;

import javax.persistence.*;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
public class Seat {
	@Id
	private String seatId;
	private String row;
	private String col;
	
	@ManyToOne
	@OnDelete(action=OnDeleteAction.CASCADE)
	@JoinColumn(name="screenId", nullable=false)
	private Screen screen;

	public Seat() {
		//Empty constructor
	}

	public Seat(String row, String column) {
		this.row = row;
		this.col = column;
	}

	public String getSeatId() {
		return seatId;
	}
	
	public void setSeatId(String seatId) {
		this.seatId = seatId;
	}

	public String getRow() {
		return row;
	}

	public void setRow(String row) {
		this.row = row;
	}

	public String getColumn() {
		return col;
	}

	public void setColumn(String column) {
		this.col = column;
	}
	
	public Screen getScreen() {
		return screen;
	}
	
	public void setScreen(Screen screen) {
		this.screen = screen;
	}

}