package S6.voyage.struts;

import java.util.List;

import S6.voyage.baseModel.Chambre;
import S6.voyage.baseModel.ChambreView;
import S6.voyage.baseModel.HotelView;

public class ChambreAction extends BaseAction
{
	private List<ChambreView> allChambre;
	private List<HotelView> hotels;
	private Chambre chambre=new Chambre();
	private Chambre chambre_modif=new Chambre();
	private String error = "";
	private int id_chambre;
	private boolean editMode;
	
	//--------- get list ChambreView Action --------------//
	public String find() throws Exception
	{
		 setEditMode(false);
		 if(checkSession()) {
			setHotels((List<HotelView>)(Object)baseService.findAll(new HotelView()));
			setAllChambre((List<ChambreView>)(Object)baseService.findAll(new ChambreView()));
			return SUCCESS;
		 }
		 return ERROR;
	}
	//--------------- creer chambre Action -------------------//
	public String create() throws Exception 
	{
		editMode = false;
		if(checkSession()) {
			try {
	        	baseService.save(chambre);
	            return SUCCESS;
			} catch (Exception e) {
	            e.printStackTrace();
	            return ERROR;
	        }
		}
		return LOGIN;
    }
	//--------------- delete chambre Action -------------------//
	public String delete() throws Exception 
	{
		editMode = false;
		if(checkSession()) {
			try {
		        chambre = new Chambre(id_chambre);
		        baseService.findById(chambre);
		        baseService.delete(chambre);
		        chambre = new Chambre();
		        return SUCCESS;
			} catch (Exception e) {
				chambre = new Chambre();
				error ="Des Reservations de cette chambre existes, pour la supprimer veillez supprimer ces reservations avant";
				setHotels((List<HotelView>)(Object)baseService.findAll(new HotelView()));
				setAllChambre((List<ChambreView>)(Object)baseService.findAll(new ChambreView()));
	            return ERROR;
	        }
		}
		return LOGIN;
    }
	//--------------- modifier chambre Action -------------------//
	public String modifier() throws Exception 
	{
		editMode = true;
		if(checkSession()) {
			chambre_modif = new Chambre(id_chambre);
	        baseService.findById(chambre_modif);
	        setHotels((List<HotelView>)(Object)baseService.findAll(new HotelView()));
			setAllChambre((List<ChambreView>)(Object)baseService.findAll(new ChambreView()));
			return SUCCESS;
		}
        return LOGIN;
    }
	//--------------- edit chambre Action -------------------//
	public String edit() throws Exception 
	{
		if(checkSession()) {
			 baseService.update(chambre_modif);
			 return SUCCESS;
		}
		return LOGIN;
    }
	//----------- les getters et setters ---------//
	public List<ChambreView> getAllChambre() {
		return allChambre;
	}
	public void setAllChambre(List<ChambreView> allChambre) {
		this.allChambre = allChambre;
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
	public Chambre getChambre_modif() {
		return chambre_modif;
	}
	public void setChambre_modif(Chambre chambre_modif) {
		this.chambre_modif = chambre_modif;
	}
	public List<HotelView> getHotels() {
		return hotels;
	}
	public void setHotels(List<HotelView> hotels) {
		this.hotels = hotels;
	}

}
