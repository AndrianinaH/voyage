package S6.voyage.baseModel;

import java.util.Date;

public class Reservation extends BaseModel
{
	//private int id_reservation;
	private Date date_debut;
	private Date date_fin;
	private int id_chambre;
	private int id_client;
//	private Chambre chambre;
//	private Client client;
	
	public Reservation(){super();}
	
	public Reservation(int id_reservation) {
		super(id_reservation);
	}
	
	public Reservation(Date date_debut, Date date_fin,int id_chambre,int id_client) {
		super();
		this.setId_chambre(id_chambre);
		this.setId_client(id_client);
		this.date_debut = date_debut;
		this.date_fin = date_fin;
	}
	
	public Reservation(int id_reservation, Date date_debut, Date date_fin,int id_chambre,int id_client) {
		super(id_reservation);
		this.setId_chambre(id_chambre);
		this.setId_client(id_client);
		this.date_debut = date_debut;
		this.date_fin = date_fin;
	}

	/*public int getId_reservation() {
		return id_reservation;
	}
	public void setId_reservation(int id_reservation) {
		this.id_reservation = id_reservation;
	}*/
 
	public Date getDate_debut() {
		return date_debut;
	}
	public void setDate_debut(Date date_debut) {
		this.date_debut = date_debut;
	}
	public Date getDate_fin() {
		return date_fin;
	}
	public void setDate_fin(Date date_fin) {
		this.date_fin = date_fin;
	}

//	public Chambre getChambre() {
//		return chambre;
//	}
//
//	public void setChambre(Chambre chambre) {
//		this.chambre = chambre;
//	}
//
//	public Client getClient() {
//		return client;
//	}
//
//	public void setClient(Client client) {
//		this.client = client;
//	}

	public int getId_chambre() {
		return id_chambre;
	}

	public void setId_chambre(int id_chambre) {
		this.id_chambre = id_chambre;
	}

	public int getId_client() {
		return id_client;
	}

	public void setId_client(int id_client) {
		this.id_client = id_client;
	}

}
