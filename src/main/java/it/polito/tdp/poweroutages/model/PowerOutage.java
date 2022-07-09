package it.polito.tdp.poweroutages.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

public class PowerOutage {

	private int id;
	private Nerc nerc;
	private int customerAffected;
	private LocalDateTime dateEventBegan;
	private LocalDateTime dateEventEnd;
	
	public PowerOutage(int id, Nerc nerc, int customerAffected, LocalDateTime dateEventBegan, LocalDateTime dateEventEnd) {
		this.id = id;
		this.nerc = nerc;
		this.customerAffected = customerAffected;
		this.dateEventBegan = dateEventBegan;
		this.dateEventEnd = dateEventEnd;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Nerc getNerc() {
		return nerc;
	}
	public void setNerc(Nerc nerc) {
		this.nerc = nerc;
	}
	public int getCustomerAffected() {
		return customerAffected;
	}
	public void setCustomerAffected(int customerAffected) {
		this.customerAffected = customerAffected;
	}
	public LocalDateTime getDateEventBegan() {
		return dateEventBegan;
	}
	public void setDateEventBegan(LocalDateTime dateEventBegan) {
		this.dateEventBegan = dateEventBegan;
	}
	public LocalDateTime getDateEventEnd() {
		return dateEventEnd;
	}
	public void setDateEventEnd(LocalDateTime dateEventEnd) {
		this.dateEventEnd = dateEventEnd;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PowerOutage other = (PowerOutage) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return this.getYear() +	" " + this.dateEventBegan.toString() + " " + this.dateEventEnd.toString() + " ore " + this.customerAffected;
	}
	
	public int getYear() {
		return dateEventBegan.getYear();
	}
	
	public long getHours() {
		LocalDateTime inizio = LocalDateTime.from(dateEventBegan);
		return inizio.until(dateEventEnd, ChronoUnit.HOURS);
	}
		
}
