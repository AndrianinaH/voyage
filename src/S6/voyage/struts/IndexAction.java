package S6.voyage.struts;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import S6.voyage.baseModel.Client;
import S6.voyage.baseModel.Hotel;
import S6.voyage.baseModel.HotelView;
import S6.voyage.baseModel.Reservation;
import S6.voyage.service.FiltreService;
import S6.voyage.service.ReservationService;

public class IndexAction extends BaseAction
{
	private String allCommodite="";
	private FiltreService filtreService;
	private List<HotelView> hotels;
	private int page = 1;
    private int nbPage;
    private int maxResult = 2;
    private ReservationService reservationService;
    private String find="";
    private String[] list_id_commodites;
    public int[] pagination() 
    {
	    int[] ret = new int[2];
	    ret[1] = (page-1) * maxResult ;
	    ret[0] = maxResult;
	    return ret;
    }
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
		find="";
		nbPage = baseService.count(new Hotel());
		allCommodite=filtreService.getCommodite();
		setNbrReservationByClient();
		hotels=((List<HotelView>)(Object)baseService.findAll(new HotelView(),pagination()));
		return SUCCESS;
	}
	//--------- recherche hotel ou destination --------------//
	public String find() throws Exception
	{
		allCommodite=filtreService.getCommodite();
		setNbrReservationByClient();
		hotels=((List<HotelView>)(Object)filtreService.findHotelOrDestination(new HotelView(),find));
		return SUCCESS;
	}
	//--------- recherche hotel par commodite --------------//
	public String findByCommodite() throws Exception
	{
		find="Commodite";
		allCommodite=filtreService.getCommodite();
		setNbrReservationByClient();
		hotels=((List<HotelView>)(Object)filtreService.findByCommodite(new HotelView(),list_id_commodites));
		return SUCCESS;
	}
	
	//------------ les getters et les setters -------------//
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

	public List<HotelView> getHotels() {
		return hotels;
	}

	public void setHotels(List<HotelView> hotels) {
		this.hotels = hotels;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getNbPage() {
		return nbPage;
	}

	public void setNbPage(int nbPage) {
		this.nbPage = nbPage;
	}

	public int getMaxResult() {
		return maxResult;
	}

	public void setMaxResult(int maxResult) {
		this.maxResult = maxResult;
	}
	public ReservationService getReservationService() {
		return reservationService;
	}
	public void setReservationService(ReservationService reservationService) {
		this.reservationService = reservationService;
	}
	public String getFind() {
		return find;
	}
	public void setFind(String find) {
		this.find = find;
	}
	public String[] getList_id_commodites() {
		return list_id_commodites;
	}
	public void setList_id_commodites(String[] list_id_commodites) {
		this.list_id_commodites = list_id_commodites;
	}
}
