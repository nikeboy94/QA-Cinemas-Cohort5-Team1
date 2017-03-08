package com.qa.cinema.persistence;

import javax.persistence.*;

@Entity
public class Seat {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long Id;
	private String row;
	private String column;
	@ManyToOne
	@JoinColumn(name="screenId", nullable=false)
	private Screen screen;

	public Seat() {
	}

	public Seat(String row, String column) {
		super();
		this.row = row;
		this.column = column;
	}

	public Long getId() {
		return Id;
	}

	public String getRow() {
		return row;
	}

	public void setRow(String row) {
		this.row = row;
	}

	public String getColumn() {
		return column;
	}

	public void setColumn(String column) {
		this.column = column;
	}

}