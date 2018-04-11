package S6.voyage.struts;

import java.io.File;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import S6.voyage.baseModel.Commodite;
import S6.voyage.baseModel.Destination;
import S6.voyage.baseModel.Hotel;
import S6.voyage.baseModel.HotelView;

public class HotelAction extends BaseAction
{
	private List<HotelView> allHotel;
	private List<Destination> destinations;
	private List<Commodite> commodites;
	private Hotel hotel=new Hotel();
	private Hotel hotel_modif=new Hotel();
	private String error = "";
	private int id_hotel;
	private boolean editMode;
	//---- upload image -----//
	private File imageHotel;
    private String destinationPath;
    private String imageHotelFileName;
 
	//--------- get list HotelView Action --------------//
	public String find() throws Exception
	{
		 setEditMode(false);
		 if(checkSession()) {
			setCommodites((List<Commodite>)(Object)baseService.findAll(new Commodite()));
			setDestinations((List<Destination>)(Object)baseService.findAll(new Destination()));
			setAllHotel((List<HotelView>)(Object)baseService.findAll(new HotelView()));
			return SUCCESS;
		 }
		 return ERROR;
	}
	//--------------- creer hotel Action -------------------//
	public String create() throws Exception 
	{
		editMode = false;
		if(checkSession()) {
			try {
				 destinationPath=ServletActionContext.getRequest().getServletContext().getRealPath("/images");
				 File destFile = new File(destinationPath,imageHotelFileName);
				 FileUtils.copyFile(imageHotel, destFile);
				 hotel.setImage(imageHotelFileName);
				 baseService.save(hotel);
	             return SUCCESS;
			} catch (Exception e) {
	            e.printStackTrace();
	            return ERROR;
	        }
		}
		return LOGIN;
    }
	//--------------- delete hotel Action -------------------//
	public String delete() throws Exception 
	{
		editMode = false;
		if(checkSession()) {
			try {
		        hotel = new Hotel(id_hotel);
		        baseService.findById(hotel);
		        baseService.delete(hotel);
		        hotel = new Hotel();
		        return SUCCESS;
			} catch (Exception e) {
				hotel = new Hotel();
				error ="Des chambres sont encore reference dans cette hotel, pour la supprimer veillez supprimer ces chambres avant";
				setCommodites((List<Commodite>)(Object)baseService.findAll(new Commodite()));
				setDestinations((List<Destination>)(Object)baseService.findAll(new Destination()));
				setAllHotel((List<HotelView>)(Object)baseService.findAll(new HotelView()));
	            return ERROR;
	        }
		}
		return LOGIN;
    }
	//--------------- modifier hotel Action -------------------//
	public String modifier() throws Exception 
	{
		editMode = true;
		if(checkSession()) {
	        hotel_modif = new Hotel(id_hotel);
	        baseService.findById(hotel_modif);
	        setCommodites((List<Commodite>)(Object)baseService.findAll(new Commodite()));
			setDestinations((List<Destination>)(Object)baseService.findAll(new Destination()));
			setAllHotel((List<HotelView>)(Object)baseService.findAll(new HotelView()));
			return SUCCESS;
		}
        return LOGIN;
    }
	//--------------- edit hotel Action -------------------//
	public String edit() throws Exception 
	{
		if(checkSession()) {
			
			if(imageHotelFileName!=null){
				destinationPath=ServletActionContext.getRequest().getServletContext().getRealPath("/images");
				File destFile = new File(destinationPath,imageHotelFileName);
				FileUtils.copyFile(imageHotel, destFile);
				hotel_modif.setImage(imageHotelFileName);
			}
			baseService.update(hotel_modif);
			return SUCCESS;
		}
		return LOGIN;
    }
	//----------- les getters et setters ---------//
	public List<HotelView> getAllHotel() {
		return allHotel;
	}
	public void setAllHotel(List<HotelView> allHotel) {
		this.allHotel = allHotel;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public boolean getEditMode() {
		return editMode;
	}
	public void setEditMode(boolean editMode) {
		this.editMode = editMode;
	}

	public Hotel getHotel() {
		return hotel;
	}
	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}
	public List<Destination> getDestinations() {
		return destinations;
	}
	public void setDestinations(List<Destination> destinations) {
		this.destinations = destinations;
	}
	public List<Commodite> getCommodites() {
		return commodites;
	}
	public void setCommodites(List<Commodite> commodites) {
		this.commodites = commodites;
	}
	public int getId_hotel() {
		return id_hotel;
	}
	public void setId_hotel(int id_hotel) {
		this.id_hotel = id_hotel;
	}
	public Hotel getHotel_modif() {
		return hotel_modif;
	}
	public void setHotel_modif(Hotel hotel_modif) {
		this.hotel_modif = hotel_modif;
	}
	public String getDestinationPath() {
		return destinationPath;
	}
	public void setDestinationPath(String destinationPath) {
		this.destinationPath = destinationPath;
	}
	public String getImageHotelFileName() {
		return imageHotelFileName;
	}
	public void setImageHotelFileName(String imageHotelFileName) {
		this.imageHotelFileName = imageHotelFileName;
	}
	public File getImageHotel() {
		return imageHotel;
	}
	public void setImageHotel(File imageHotel) {
		this.imageHotel = imageHotel;
	}
}
