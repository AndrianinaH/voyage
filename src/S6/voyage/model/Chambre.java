package S6.voyage.model;

public class Chambre 
{
	private int id_chambre;
	private int id_hotel;
	private String nom_chambre;
	private int nbr_petit_lit;
	private int nbr_grand_lit;
	private double prix;
	private Hotel hotel;
	
	public Chambre(){}
	
	public Chambre(int id_hotel, String nom_chambre, int nbr_petit_lit, int nbr_grand_lit,double prix) {
		super();
		this.id_hotel = id_hotel;
		this.nom_chambre = nom_chambre;
		this.nbr_petit_lit = nbr_petit_lit;
		this.nbr_grand_lit = nbr_grand_lit;
		this.prix = prix;
	}
	
	public Chambre(int id_chambre, int id_hotel, String nom_chambre, int nbr_petit_lit, int nbr_grand_lit,double prix) {
		super();
		this.id_chambre = id_chambre;
		this.id_hotel = id_hotel;
		this.nom_chambre = nom_chambre;
		this.nbr_petit_lit = nbr_petit_lit;
		this.nbr_grand_lit = nbr_grand_lit;
		this.prix = prix;
	}

	public int getId_chambre() {
		return id_chambre;
	}
	public void setId_chambre(int id_chambre) {
		this.id_chambre = id_chambre;
	}
	public int getId_hotel() {
		return id_hotel;
	}
	public void setId_hotel(int id_hotel) {
		this.id_hotel = id_hotel;
	}
	public String getNom_chambre() {
		return nom_chambre;
	}
	public void setNom_chambre(String nom_chambre) {
		this.nom_chambre = nom_chambre;
	}
	public int getNbr_petit_lit() {
		return nbr_petit_lit;
	}
	public void setNbr_petit_lit(int nbr_petit_lit) {
		this.nbr_petit_lit = nbr_petit_lit;
	}
	public int getNbr_grand_lit() {
		return nbr_grand_lit;
	}
	public void setNbr_grand_lit(int nbr_grand_lit) {
		this.nbr_grand_lit = nbr_grand_lit;
	}
	public double getPrix() {
		return prix;
	}
	public void setPrix(double prix) {
		this.prix = prix;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}
	

}
