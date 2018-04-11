package S6.voyage.baseModel;

import java.util.List;

public class Commodite extends BaseModel
{
	//private int id_com;
	private String nom_commodite;
	//private List<Hotel> list_hotel;
	
	public Commodite(){super();}
	public Commodite(int id_commodite) {
		super(id_commodite);
	}
	public Commodite(String nom_commodite) {
		super();
		this.nom_commodite = nom_commodite;
	}
	
	public Commodite(int id_commodite, String nom_commodite) {
		super(id_commodite);
		this.setNom_commodite(nom_commodite);
	}

	public String getNom_commodite() {
		return nom_commodite;
	}

	public void setNom_commodite(String nom_commodite) {
		this.nom_commodite = nom_commodite;
	}

	/*public List<Hotel> getList_hotel() {
		return list_hotel;
	}

	public void setList_hotel(List<Hotel> list_hotel) {
		this.list_hotel = list_hotel;
	}*/
}
