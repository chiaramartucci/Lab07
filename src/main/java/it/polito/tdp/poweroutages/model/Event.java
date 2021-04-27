package it.polito.tdp.poweroutages.model;


import java.time.LocalDateTime;


public class Event {
	
	private LocalDateTime data_inizio;
	private LocalDateTime data_fine;
	private double numeroOre;
	private int numeroPersoneCoinvolte;
	
	
	public Event(LocalDateTime data_inizio, LocalDateTime data_fine, double numeroOre, int numeroPersoneCoinvolte) {
		this.data_inizio = data_inizio;
		this.data_fine = data_fine;
		this.numeroOre = numeroOre;
		this.numeroPersoneCoinvolte = numeroPersoneCoinvolte;
	}


	public LocalDateTime getData_inizio() {
		return data_inizio;
	}


	public void setData_inizio(LocalDateTime data_inizio) {
		this.data_inizio = data_inizio;
	}


	public LocalDateTime getData_fine() {
		return data_fine;
	}


	public void setData_fine(LocalDateTime data_fine) {
		this.data_fine = data_fine;
	}


	public double getNumeroOre() {
		return numeroOre;
	}


	public void setNumeroOre(long numeroOre) {
		this.numeroOre = numeroOre;
	}


	@Override
	public String toString() {
		return "Event [data_inizio=" + data_inizio + ", data_fine=" + data_fine + ", numeroOre=" + numeroOre
				+ ", numeroPersoneCoinvolte=" + numeroPersoneCoinvolte + "]";
	}


	public int getNumeroPersoneCoinvolte() {
		return numeroPersoneCoinvolte;
	}


	public void setNumeroPersoneCoinvolte(int numeroPersoneCoinvolte) {
		this.numeroPersoneCoinvolte = numeroPersoneCoinvolte;
	}
	
	
	
	
	

}
