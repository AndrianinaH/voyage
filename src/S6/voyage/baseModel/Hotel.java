package S6.voyage.baseModel;

import java.util.List;

public class Hotel extends BaseModel
{
	//private int id_hotel;
	private String nom_hotel;
	private String description;
	private String image;
	private int id_destination;
	private int id_commodite;
	//private Destination destination;
	//private Commodite commodite;
	//private List<Chambre> list_chambre;
	
	public Hotel(){super();}
	
	public Hotel(int id_hotel) {
		super(id_hotel);
	}
	public Hotel(String nom_hotel, String description, String image,int id_destination,int id_commodite) {
		super();
		this.nom_hotel = nom_hotel;
		this.description = description;
		this.image = image;
		this.id_destination = id_destination;
		this.id_commodite = id_commodite;
	}
	
	public Hotel(int id_hotel, String nom_hotel, String description, String image,int id_destination,int id_commodite) {
		super(id_hotel);
		this.nom_hotel = nom_hotel;
		this.description = description;
		this.image = image;
		this.id_destination = id_destination;
		this.id_commodite = id_commodite;
	}

	/*public int getId_hotel() {
		return id_hotel;
	}
	public void setId_hotel(int id_hotel) {
		this.id_hotel = id_hotel;
	}*/
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

	public int getId_commodite() {
		return id_commodite;
	}

	public void setId_commodite(int id_commodite) {
		this.id_commodite = id_commodite;
	}

	/*public Destination getDestination() {
		return destination;
	}

	public void setDestination(Destination destination) {
		this.destination = destination;
	}*/

	/*public List<Chambre> getList_chambre() {
		return list_chambre;
	}

	public void setList_chambre(List<Chambre> list_chambre) {
		this.list_chambre = list_chambre;
	}*/

	/*public Commodite getCommodite() {
		return commodite;
	}

	public void setCommodite(Commodite commodite) {
		this.commodite = commodite;
	}*/
}
