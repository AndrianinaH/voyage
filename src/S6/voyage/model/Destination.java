package S6.voyage.model;

import java.util.List;

public class Destination 
{
	private int id_destination;
	private String nom_destination;
	private List<Hotel> list_hotel;
	
	public Destination(){}
	
	public Destination(String nom_destination) {
		super();
		this.nom_destination = nom_destination;
	}
	public Destination(int id_destination, String nom_destination) {
		super();
		this.id_destination = id_destination;
		this.nom_destination = nom_destination;
	}

	public int getId_destination() {
		return id_destination;
	}
	public void setId_destination(int id_destination) {
		this.id_destination = id_destination;
	}
	public String getNom_destination() {
		return nom_destination;
	}
	public void setNom_destination(String nom_destination) {
		this.nom_destination = nom_destination;
	}

	public List<Hotel> getList_hotel() {
		return list_hotel;
	}

	public void setList_hotel(List<Hotel> list_hotel) {
		this.list_hotel = list_hotel;
	}
	
}
