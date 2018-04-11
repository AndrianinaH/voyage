package S6.voyage.struts;

import java.util.List;

import S6.voyage.baseModel.Client;
import S6.voyage.baseModel.Commodite;

public class ClientAction extends BaseAction
{
	private List<Client> allClient;
	private Client client=new Client();
	private Client client_modif=new Client();
	private String error = "";
	private int id_client;
	private boolean editMode;
	
	//--------- get list Client Action --------------//
	public String find() throws Exception
	{
		editMode = false;
		 if(checkSession()) {
			setAllClient((List<Client>)(Object)baseService.findAll(new Client()));
			return SUCCESS;
		 }
		 return ERROR;
	}
	//--------------- creer client Action -------------------//
	public String create() throws Exception 
	{
		editMode = false;
		if(checkSession()) {
			try {
	        	baseService.save(client);
	            return SUCCESS;
			} catch (Exception e) {
	            error = e.getMessage();
	            setAllClient((List<Client>)(Object)baseService.findAll(new Client()));
	            return ERROR;
	        }
		}
		return LOGIN;
    }
	//--------------- delete client Action -------------------//
	public String delete() throws Exception 
	{
		editMode = false;
		if(checkSession()) {
			try {
		        client = new Client(id_client);
		        baseService.findById(client);
		        baseService.delete(client);
		        client = new Client();
		        return SUCCESS;
			} catch (Exception e) {
				client = new Client();
				error ="Des reservation sont au nom de ce client, pour la supprimer veillez supprimer ces reservations avant";
				setAllClient((List<Client>)(Object)baseService.findAll(new Client()));
	            return ERROR;
	        }
		}
		return LOGIN;
    }
	//--------------- modifier client Action -------------------//
	public String modifier() throws Exception 
	{
		editMode = true;
		if(checkSession()) {
			client_modif = new Client(id_client);
	        baseService.findById(client_modif);
	        setAllClient((List<Client>)(Object)baseService.findAll(new Client()));
	        return SUCCESS;
		}
        return LOGIN;
    }
	//--------------- edit client Action -------------------//
	public String edit() throws Exception 
	{
		if(checkSession()) {
			 baseService.update(client_modif);
		     return SUCCESS;
		}
		return LOGIN;
    }

	//----------- les getters et setters ---------//
	public List<Client> getAllClient() {
		return allClient;
	}
	public void setAllClient(List<Client> allClient) {
		this.allClient = allClient;
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public int getId_client() {
		return id_client;
	}
	public void setId_client(int id_client) {
		this.id_client = id_client;
	}
	public Client getClient_modif() {
		return client_modif;
	}
	public void setClient_modif(Client client_modif) {
		this.client_modif = client_modif;
	}
	public boolean isEditMode() {
		return editMode;
	}
	public void setEditMode(boolean editMode) {
		this.editMode = editMode;
	}
}
