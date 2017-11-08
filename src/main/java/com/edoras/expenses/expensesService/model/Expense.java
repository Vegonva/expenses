package com.edoras.expenses.expensesService.model;

import java.util.Date;

public class Expense {

	private long id;
	private String description;
	private Double value;
	private Date date;

	private Expense() {
		id=0;
	}

	public Expense(long id, String description, Double value, Date date) {
		this.id = id;
		this.description = description;
		this.value = value;
		this.date = date;
	}

	public long getId() { return id; }
	public void setId(long id) { this.id = id; }

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) { this.description = description; }

	public Double getValue() {
		return value;
	}
	public void setValue(double value) { this.value = value; }

	public Date getDate() {
		return date;
	}
	public void setDate(Date date) { this.date = date; }

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Expense other = (Expense) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", description=" + description + ", value=" + value
				+ ", date=" + date + "]";
	}

}