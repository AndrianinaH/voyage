package S6.voyage.struts;

import java.util.List;

import S6.voyage.baseModel.Client;
import S6.voyage.baseModel.Commodite;
import S6.voyage.baseModel.Destination;
import S6.voyage.baseModel.Hotel;
import S6.voyage.baseModel.HotelView;
import S6.voyage.baseModel.Reservation;
import S6.voyage.baseModel.ChambreView;
import S6.voyage.baseModel.ReservationView;

public class ReservationAction extends BaseAction
{
	private List<ReservationView> allReservation;
	private List<Client> clients;
	private List<ChambreView> chambres;
	private Reservation reservation=new Reservation();
	private Reservation reservation_modif=new Reservation();
	private String error = "";
	private int id_res;
	private boolean editMode;
	public List<ReservationView> getAllReservation() {
		return allReservation;
	}
	//--------- get list ReservationView Action --------------//
	public String find() throws Exception
	{
		 setEditMode(false);
		 if(checkSession()) {
			setChambres((List<ChambreView>)(Object)baseService.findAll(new ChambreView()));
			setClients((List<Client>)(Object)baseService.findAll(new Client()));
			setAllReservation((List<ReservationView>)(Object)baseService.findAll(new ReservationView()));
			return SUCCESS;
		 }
		 return ERROR;
	}
	//--------------- creer reservation Action -------------------//
	public String create() throws Exception 
	{
		editMode = false;
		if(checkSession()) {
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
	//--------------- delete reservation Action -------------------//
	public String delete() throws Exception 
	{
		editMode = false;
		if(checkSession()) {
			try {
		        reservation = new Reservation(id_res);
		        baseService.findById(reservation);
		        baseService.delete(reservation);
		        reservation = new Reservation();
		        return SUCCESS;
			} catch (Exception e) {
				reservation = new Reservation();
				error =e.getMessage();
				setChambres((List<ChambreView>)(Object)baseService.findAll(new ChambreView()));
				setClients((List<Client>)(Object)baseService.findAll(new Client()));
				setAllReservation((List<ReservationView>)(Object)baseService.findAll(new ReservationView()));
	            return ERROR;
	        }
		}
		return LOGIN;
    }
	//--------------- modifier reservation Action -------------------//
	public String modifier() throws Exception 
	{
		editMode = true;
		if(checkSession()) {
	        reservation_modif = new Reservation(id_res);
	        baseService.findById(reservation_modif);
	        setChambres((List<ChambreView>)(Object)baseService.findAll(new ChambreView()));
			setClients((List<Client>)(Object)baseService.findAll(new Client()));
			setAllReservation((List<ReservationView>)(Object)baseService.findAll(new ReservationView()));
			return SUCCESS;
		}
        return LOGIN;
    }
	//--------------- edit reservation Action -------------------//
	public String edit() throws Exception 
	{
		if(checkSession()) {
			 baseService.update(reservation_modif);
			 return SUCCESS;
		}
		return LOGIN;
    }
	//----------- les getters et setters ---------//
	public void setAllReservation(List<ReservationView> allReservation) {
		this.allReservation = allReservation;
	}
	public List<Client> getClients() {
		return clients;
	}
	public void setClients(List<Client> clients) {
		this.clients = clients;
	}
	public List<ChambreView> getChambres() {
		return chambres;
	}
	public void setChambres(List<ChambreView> chambres) {
		this.chambres = chambres;
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
	public int getId_res() {
		return id_res;
	}
	public void setId_res(int id_res) {
		this.id_res = id_res;
	}
	public boolean isEditMode() {
		return editMode;
	}
	public void setEditMode(boolean editMode) {
		this.editMode = editMode;
	}
}
