package S6.voyage.model;

import java.util.List;

public class Hotel 
{
	private int id_hotel;
	private int id_destination;
	private String nom_hotel;
	private String description;
	private String image;
	private Destination destination;
	private List<Chambre> list_chambre;
	private List<Commodite> list_commodite;
	
	public Hotel(){}
	
	public Hotel(int id_destination, String nom_hotel, String description, String image) {
		super();
		this.id_destination = id_destination;
		this.nom_hotel = nom_hotel;
		this.description = description;
		this.image = image;
	}
	
	public Hotel(int id_hotel, int id_destination, String nom_hotel, String description, String image) {
		super();
		this.id_hotel = id_hotel;
		this.id_destination = id_destination;
		this.nom_hotel = nom_hotel;
		this.description = description;
		this.image = image;
	}

	public int getId_hotel() {
		return id_hotel;
	}
	public void setId_hotel(int id_hotel) {
		this.id_hotel = id_hotel;
	}
	public int getId_destination() {
		return id_destination;
	}
	public void setId_destination(int id_destination) {
		this.id_destination = id_destination;
	}
	public String getNom_hotel() {
		return nom_hotel;
	}
	public void setNom_hotel(String nom_hotel) {
		this.nom_hotel = nom_hotel;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}

	public List<Chambre> getList_chambre() {
		return list_chambre;
	}

	public void setList_chambre(List<Chambre> list_chambre) {
		this.list_chambre = list_chambre;
	}

	public Destination getDestination() {
		return destination;
	}

	public void setDestination(Destination destination) {
		this.destination = destination;
	}

	public List<Commodite> getList_commodite() {
		return list_commodite;
	}

	public void setList_commodite(List<Commodite> list_commodite) {
		this.list_commodite = list_commodite;
	}

}
