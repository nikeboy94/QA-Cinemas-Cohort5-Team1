package com.qa.cinema.persistence;

import javax.persistence.*;

@Entity
public class Seat {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long seatId;
	private String row;
	private String col;
	@ManyToOne
	@JoinColumn(name="screenId", nullable=false)
	private Screen screen;

	public Seat() {
	}

	public Seat(String row, String column) {
		this.row = row;
		this.col = column;
	}

	public Long getId() {
		return seatId;
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

}