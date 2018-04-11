package S6.voyage.struts;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import S6.voyage.baseModel.BaseModel;
import S6.voyage.baseModel.Commodite;
import S6.voyage.baseModel.Destination;
import S6.voyage.service.FiltreService;

public class CommoditeAction extends BaseAction
{
	private FiltreService filtreService;
	private List<Commodite> allCommodite;
	private Commodite commodite=new Commodite();
	private Commodite commodite_modif=new Commodite();
	private String error = "";
	private int id_com;
	private boolean editMode;
	
	//--------- get list Commodite Action --------------//
	public String find() throws Exception
	{
		editMode = false;
		 if(checkSession()) {
			setAllCommodite((List<Commodite>)(Object)baseService.findAll(new Commodite()));
			return SUCCESS;
		 }
		 return ERROR;
	}
	//--------------- creer commodite Action -------------------//
	public String create() throws Exception 
	{
		editMode = false;
		if(checkSession()) {
			try {
		        if(!testCommodite(commodite)) {
		        	baseService.save(commodite);
		        	//C:\Users\Utilisateur\workspaceJEE\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\voyage\images
		    		File file=new File(ServletActionContext.getRequest().getServletContext().getRealPath("/commodite.cache"));
		        	filtreService.updateFile(file);
		            return SUCCESS;
		        }
			} catch (Exception e) {
	            error = e.getMessage();
	            setAllCommodite((List<Commodite>)(Object)baseService.findAll(new Commodite()));
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
		        commodite = new Commodite(id_com);
		        baseService.findById(commodite);
		        baseService.delete(commodite);
		        commodite = new Commodite();
		        File file=new File(ServletActionContext.getRequest().getServletContext().getRealPath("/commodite.cache"));
	        	filtreService.updateFile(file);
		        return SUCCESS;
			} catch (Exception e) {
				commodite = new Commodite();
				error ="Des hotels dependent encore de cette commodite, pour la supprimer veillez supprimer ces hotels avant";
				 setAllCommodite((List<Commodite>)(Object)baseService.findAll(new Commodite()));
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
	        commodite_modif = new Commodite(id_com);
	        baseService.findById(commodite_modif);
	        setAllCommodite((List<Commodite>)(Object)baseService.findAll(new Commodite()));
	        return SUCCESS;
		}
        return LOGIN;
    }
	//--------------- edit commodite Action -------------------//
	public String edit() throws Exception 
	{
		if(checkSession()) {
			 baseService.update(commodite_modif);
			 File file=new File(ServletActionContext.getRequest().getServletContext().getRealPath("/commodite.cache"));
        	filtreService.updateFile(file);
		     return SUCCESS;
		}
		return LOGIN;
    }

	
	//--------------- test si la commodite existe deje -------------------//
	public boolean testCommodite(Commodite commodite) throws Exception 
	{
        List<Commodite> liste = (List<Commodite>) (Object) baseService.findAll(commodite);
        if(!liste.isEmpty()){
            throw new Exception("La commodite "+commodite.getNom_commodite()+" existe deja");
        }
        return false;
    }
	//----------- les getters et setters ---------//
	public int getId_com() {
		return id_com;
	}
	public void setId_com(int id_com) {
		this.id_com = id_com;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public List<Commodite> getAllCommodite() {
		return allCommodite;
	}
	public void setAllCommodite(List<Commodite> allCommodite) {
		this.allCommodite = allCommodite;
	}
	public boolean isEditMode() {
		return editMode;
	}
	public void setEditMode(boolean editMode) {
		this.editMode = editMode;
	}
	public Commodite getCommodite() {
		return commodite;
	}
	public void setCommodite(Commodite commodite) {
		this.commodite = commodite;
	}
	public Commodite getCommodite_modif() {
		return commodite_modif;
	}
	public void setCommodite_modif(Commodite commodite_modif) {
		this.commodite_modif = commodite_modif;
	}
	public FiltreService getFiltreService() {
		return filtreService;
	}
	public void setFiltreService(FiltreService filtreService) {
		this.filtreService = filtreService;
	}
}
