package it.prova.gestioneappartamento.model;

import java.util.Date;

public class Appartamento {
	private Long id;
	private String quartiere;
	private int metriQuadri;
	private int prezzo;
	private Date dataCostruzione;

	public Appartamento() {
	}

	public Appartamento(String quartiere, int metriQuadri, int prezzo, Date dataCostruzione) {
		super();
		this.quartiere = quartiere;
		this.metriQuadri = metriQuadri;
		this.prezzo = prezzo;
		this.dataCostruzione = dataCostruzione;
	}

	public Appartamento(Long id, String quartiere, int metriQuadri, int prezzo, Date dataCostruzione) {
		super();
		this.id = id;
		this.quartiere = quartiere;
		this.metriQuadri = metriQuadri;
		this.prezzo = prezzo;
		this.dataCostruzione = dataCostruzione;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getQuartiere() {
		return quartiere;
	}

	public void setQuartiere(String quartiere) {
		this.quartiere = quartiere;
	}

	public int getMetriQuadri() {
		return metriQuadri;
	}

	public void setMetriQuadri(int metriQuadri) {
		this.metriQuadri = metriQuadri;
	}

	public int getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(int prezzo) {
		this.prezzo = prezzo;
	}

	public Date getDataCostruzione() {
		return dataCostruzione;
	}

	public void setDataCostruzione(Date dataCostruzione) {
		this.dataCostruzione = dataCostruzione;
	}

}
