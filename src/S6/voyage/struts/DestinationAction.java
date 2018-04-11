package S6.voyage.struts;
import java.util.List;

import S6.voyage.baseModel.Destination;
import S6.voyage.baseModel.User;

public class DestinationAction extends BaseAction
{
	private List<Destination> allDestination;
	private Destination destination=new Destination();
	private Destination destination_modif=new Destination();
	private String error = "";
	private int id_dest;
	private boolean editMode;
	//--------- get list Destination Action --------------//
	public String find() throws Exception
	{
		editMode = false;
		 if(checkSession()) {
			setAllDestination((List<Destination>)(Object)baseService.findAll(new Destination()));
			return SUCCESS;
		 }
		 return ERROR;
	}
	//--------------- creer destination Action -------------------//
	public String create() throws Exception 
	{
		editMode = false;
		if(checkSession()) {
			try {
		        if(!testDestination(destination)) {
		        	baseService.save(destination);
		            return SUCCESS;
		        }
			} catch (Exception e) {
	            error = e.getMessage();
	            setAllDestination((List<Destination>)(Object)baseService.findAll(new Destination()));
	            return ERROR;
	        }
		}
		return LOGIN;
    }
	//--------------- delete destination Action -------------------//
	public String delete() throws Exception 
	{
		editMode = false;
		if(checkSession()) {
			try {
		        destination = new Destination(id_dest);
		        baseService.findById(destination);
		        baseService.delete(destination);
		        destination = new Destination();
		        return SUCCESS;
			} catch (Exception e) {
				destination = new Destination();
				error ="Des hotels sont encore reference dans cette destination, pour la supprimer veillez supprimer ces hotels avant";
				setAllDestination((List<Destination>)(Object)baseService.findAll(new Destination()));
	            return ERROR;
	        }
		}
		return LOGIN;
    }
	//--------------- modifier destination Action -------------------//
	public String modifier() throws Exception 
	{
		editMode = true;
		if(checkSession()) {
	        destination_modif = new Destination(id_dest);
	        baseService.findById(destination_modif);
	        setAllDestination((List<Destination>)(Object)baseService.findAll(new Destination()));
	        return SUCCESS;
		}
        return LOGIN;
    }
	//--------------- edit destination Action -------------------//
	public String edit() throws Exception 
	{
		if(checkSession()) {
			 baseService.update(destination_modif);
		     return SUCCESS;
		}
		return LOGIN;
    }
	//--------------- test si la designation existe deje -------------------//
	public boolean testDestination(Destination destination) throws Exception 
	{
        List<Destination> liste = (List<Destination>) (Object) baseService.findAll(destination);
        if(!liste.isEmpty()){
            throw new Exception("La destination "+destination.getNom_destination()+" existe deja");
        }
        return false;
    }
	
	//----------- les getters et setters ---------//
	public List<Destination> getAllDestination() {
		return allDestination;
	}

	public void setAllDestination(List<Destination> allDestination) {
		this.allDestination = allDestination;
	}

	public Destination getDestination() {
		return destination;
	}

	public void setDestination(Destination destination) {
		this.destination = destination;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public int getId_dest() {
		return id_dest;
	}
	public void setId_dest(int id_dest) {
		this.id_dest = id_dest;
	}
	public boolean getEditMode() {
		return editMode;
	}
	public void setEditMode(boolean editMode) {
		this.editMode = editMode;
	}
	public Destination getDestination_modif() {
		return destination_modif;
	}
	public void setDestination_modif(Destination destination_modif) {
		this.destination_modif = destination_modif;
	}
	
}
