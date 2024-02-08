package br.ufal.ic.p2.wepayu.models;

import java.time.LocalDate;

public class Cartao {
	private String id;
	private LocalDate data;
	private double horas;
	
	public Cartao(String id, LocalDate data, double horas) {
		this.id = id;
		this.data = data;
		this.horas = horas;
	}

	public String getId() {
		return id;
	}

	public LocalDate getData() {
		return data;
	}

	public double getHoras() {
		return horas;
	}
	
	
}
