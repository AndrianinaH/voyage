package S6.voyage.model;

import java.util.List;

public class Commodite 
{
	private int id_commodite;
	private String nom_commodite;
	private List<Hotel> list_hotel;
	
	public Commodite(){}
	
	public Commodite(String nom_commodite) {
		super();
		this.nom_commodite = nom_commodite;
	}
	
	public Commodite(int id_commodite, String nom_commodite) {
		super();
		this.id_commodite = id_commodite;
		this.nom_commodite = nom_commodite;
	}

	public int getId_commodite() {
		return id_commodite;
	}

	public void setId_commodite(int id_commodite) {
		this.id_commodite = id_commodite;
	}

	public String getNom_commodite() {
		return nom_commodite;
	}

	public void setNom_commodite(String nom_commodite) {
		this.nom_commodite = nom_commodite;
	}

	public List<Hotel> getList_hotel() {
		return list_hotel;
	}

	public void setList_hotel(List<Hotel> list_hotel) {
		this.list_hotel = list_hotel;
	}
}
