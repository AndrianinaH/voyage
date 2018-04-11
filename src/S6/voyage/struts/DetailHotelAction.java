package S6.voyage.struts;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import S6.voyage.baseModel.Chambre;
import S6.voyage.baseModel.ChambreView;
import S6.voyage.baseModel.Client;
import S6.voyage.baseModel.Hotel;
import S6.voyage.baseModel.Reservation;
import S6.voyage.baseModel.ReservationView;
import S6.voyage.service.FiltreService;
import S6.voyage.service.ReservationService;

public class DetailHotelAction extends BaseAction
{
	private String allCommodite="";
	private FiltreService filtreService;
	private List<ChambreView> chambres;
	private List<ReservationView> reservations;
	private int id_hotel=0;
	private int page=1;
	private Reservation reservation=new Reservation();
	private Reservation reservation_modif=new Reservation();
	private ReservationService reservationService;
	private Chambre chambre=new Chambre();
	private String error = "";
	private int id_chambre;
	private boolean editMode;
	private int id_reservation;
	//----------- getNombre Reservation by client ----------//
  	public void setNbrReservationByClient() throws Exception 
  	{
  		if(checkSessionClient()){
  			HttpSession session= ServletActionContext.getRequest().getSession(false);
  			Reservation res=new Reservation();
  			Client client=(Client)session.getAttribute("client_voyage");
  			res.setId_client(client.getId());
  	        setNbr_reservation(reservationService.getNbrReservationByClient(res));
  		}else setNbr_reservation(0);
  		
     }
	//--------- methode par defaut --------------//
	public String execute() throws Exception
	{
		if(id_hotel!=0){
			allCommodite=filtreService.getCommodite();
			Hotel hotel=new Hotel(id_hotel);
			baseService.findById(hotel);
			ChambreView chambre=new ChambreView();
			chambre.setNom_hotel(hotel.getNom_hotel());
			setNbrReservationByClient();
			setChambres(((List<ChambreView>)(Object)baseService.findAll(chambre)));
			return SUCCESS;	
		}
		return ERROR;
	}
	//--------------- reserver Modal Action -------------------//
	public String reserverModal() throws Exception 
	{
		editMode = true;
		if(checkSessionClient()) {
	        if(id_hotel!=0 && id_chambre!=0){
	        	chambre = new Chambre(id_chambre);
		        baseService.findById(chambre);
		        //-------- rediriger vers detailHotel------------//
				return execute();
			}else return ERROR;
	       
		}
        return LOGIN;
    }
	//--------- voir reservation client --------------//
	public String reservationClient() throws Exception
	{
		editMode = false;
		if(checkSessionClient()){
			allCommodite=filtreService.getCommodite();
  			HttpSession session= ServletActionContext.getRequest().getSession(false);
  			ReservationView res=new ReservationView();
  			Client client=(Client)session.getAttribute("client_voyage");
  			res.setNom_client(client.getNom_client());
  			setNbrReservationByClient();
  			setReservations(((List<ReservationView>)(Object)baseService.findAll(res)));
			return SUCCESS;	
		}
		return LOGIN;
	}
	//--------------- anuler reservation client Action -------------------//
	public String delete() throws Exception 
	{
		editMode = false;
		if(checkSessionClient()) {
			try {
		        reservation = new Reservation(id_reservation);
		        baseService.findById(reservation);
		        baseService.delete(reservation);
		        reservation = new Reservation();
		        return SUCCESS;
			} catch (Exception e) {
				reservation = new Reservation();
				error =e.getMessage();
	            return reservationClient();
	        }
		}
		return LOGIN;
    }
	//--------------- reserver Modal Action -------------------//
	public String modifReserverClient() throws Exception 
	{
		editMode = true;
		if(checkSessionClient()) {
			allCommodite=filtreService.getCommodite();
		 	reservation_modif = new Reservation(id_reservation);
	        baseService.findById(reservation_modif);
	        //-------- rediriger vers reservationClient------------//
	        HttpSession session= ServletActionContext.getRequest().getSession(false);
  			ReservationView res=new ReservationView();
  			Client client=(Client)session.getAttribute("client_voyage");
  			res.setNom_client(client.getNom_client());
  			setNbrReservationByClient();
  			setReservations(((List<ReservationView>)(Object)baseService.findAll(res)));
			return SUCCESS;
		}
        return LOGIN;
    }
	//--------------- update reservation Client Action -------------------//
	public String updateReservationClient() throws Exception 
	{
		if(checkSessionClient()) {
			 baseService.update(reservation_modif);
			 return SUCCESS;
		}
		return LOGIN;
    }
	//--------------- creer reservation Client Action -------------------//
	public String create() throws Exception 
	{
		editMode = false;
		if(checkSessionClient()) {
			try {
	        	baseService.save(reservation);
	            return SUCCESS;
			} catch (Exception e) {
	            e.printStackTrace();
	            return ERROR;
	        }
		}
		return LOGIN;
    }
	//------------ les getters et setters -------------//
	public String getAllCommodite() {
		return allCommodite;
	}
	public void setAllCommodite(String allCommodite) {
		this.allCommodite = allCommodite;
	}
	public FiltreService getFiltreService() {
		return filtreService;
	}
	public void setFiltreService(FiltreService filtreService) {
		this.filtreService = filtreService;
	}

	public int getId_hotel() {
		return id_hotel;
	}
	public void setId_hotel(int id_hotel) {
		this.id_hotel = id_hotel;
	}
	public List<ChambreView> getChambres() {
		return chambres;
	}
	public void setChambres(List<ChambreView> chambres) {
		this.chambres = chambres;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public Reservation getReservation() {
		return reservation;
	}
	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}
	public Reservation getReservation_modif() {
		return reservation_modif;
	}
	public void setReservation_modif(Reservation reservation_modif) {
		this.reservation_modif = reservation_modif;
	}
	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
	public boolean isEditMode() {
		return editMode;
	}
	public void setEditMode(boolean editMode) {
		this.editMode = editMode;
	}
	public int getId_chambre() {
		return id_chambre;
	}
	public void setId_chambre(int id_chambre) {
		this.id_chambre = id_chambre;
	}
	public Chambre getChambre() {
		return chambre;
	}
	public void setChambre(Chambre chambre) {
		this.chambre = chambre;
	}
	public ReservationService getReservationService() {
		return reservationService;
	}
	public void setReservationService(ReservationService reservationService) {
		this.reservationService = reservationService;
	}
	public List<ReservationView> getReservations() {
		return reservations;
	}
	public void setReservations(List<ReservationView> reservations) {
		this.reservations = reservations;
	}
	public int getId_reservation() {
		return id_reservation;
	}
	public void setId_reservation(int id_reservation) {
		this.id_reservation = id_reservation;
	}

}
