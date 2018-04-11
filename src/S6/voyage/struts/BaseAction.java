package S6.voyage.struts;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import S6.voyage.baseModel.Client;
import S6.voyage.baseModel.Reservation;
import S6.voyage.baseModel.User;
import S6.voyage.service.BaseService;
import S6.voyage.service.ReservationService;

public class BaseAction extends ActionSupport implements SessionAware
{
	private User user_login=new User();
	private Client client_login=new Client();
	protected BaseService baseService;
	private SessionMap<String,Object> sessionMap;
	private String loginError = "";
	private int nbr_reservation=0;
	//----------- test session et getUser ----------//
	public boolean checkSession()
	{
        boolean b = true;
        HttpSession session = ServletActionContext.getRequest().getSession(false);
        if(session==null || session.getAttribute("admin_voyage")==null){
            b = false;
        }
        return b;
    }
	public User testLogin(User u) throws Exception 
	{
        List<User> liste = (List<User>) (Object) baseService.findAll(u);
        if(!liste.isEmpty()){
            return (User)liste.get(0);
        }
        return null;
    }
	//----------- test session et getClient ----------//
	public boolean checkSessionClient() throws Exception 
	{
        boolean b = true;
        HttpSession session = ServletActionContext.getRequest().getSession(false);
        if(session==null || session.getAttribute("client_voyage")==null){
            b = false;
        }
        return b;
    }
	public Client testLoginClient(Client c) throws Exception 
	{
        List<Client> liste = (List<Client>) (Object) baseService.findAll(c);
        if(!liste.isEmpty()){
            return (Client)liste.get(0);
        }
        return null;
    }

	//--------------- deconnexion Action --------------//
	public String logout() {
        if(sessionMap!=null){
            sessionMap.invalidate();
        }
        return SUCCESS;
    }
	//--------------- deconnexion Action --------------//
	public String logoutClient() {
        if(sessionMap!=null){
            sessionMap.invalidate();
        }
        return SUCCESS;
    }
	//--------------- login admin Action -------------------//
	public String login() throws Exception 
	{
        User user = testLogin(user_login);
        if(user != null) {
            sessionMap.put("admin_voyage",user);
            return LOGIN;
        }
        else {
            loginError = "Autentification echouer";
            return ERROR;
        }
    }
	//--------------- login Client Action -------------------//
	public String loginClient() throws Exception 
	{
        Client client = testLoginClient(client_login);
        if(client != null) {
            sessionMap.put("client_voyage",client);
            return LOGIN;
        }
        else {
            loginError = "Autentification echouer";
            return ERROR;
        }
    }
	//--------------- Inscription client Action -------------------//
	public String create() throws Exception 
	{
		try {
			Client c=new Client();
			c.setEmail(client_login.getEmail());
			if(!testClient(c)) {
				baseService.save(client_login);
        	}
		} catch (Exception e) {
			loginError = e.getMessage();
            return ERROR;
        }
		return SUCCESS;
    }
	//--------------- test si le client existe deja -------------------//
	public boolean testClient(Client client) throws Exception 
	{
        List<Client> liste = (List<Client>) (Object) baseService.findAll(client);
        if(!liste.isEmpty()){
            throw new Exception("L'email "+client.getEmail()+" existe deja");
        }
        return false;
    }
	//--------------- afficher login admin Action -----------------------//
	public String execute() throws Exception {
        return SUCCESS;
    }
	//--------------- afficher login client Action -----------------------//
	public String signIn() throws Exception {
        return SUCCESS;
    }
	//--------------- afficher inscription client Action -----------------------//
	public String signUp() throws Exception {
        return SUCCESS;
    }
	//-------------- les getters et setters --------------------------//
	public Client getClient_login() {
		return client_login;
	}

	public void setClient_login(Client client_login) {
		this.client_login = client_login;
	}
	@Override
	public void setSession(Map<String, Object> map) {
		sessionMap= (SessionMap)map;
	}
	
	public BaseService getBaseService() {
		return baseService;
	}

	public void setBaseService(BaseService baseService) {
		this.baseService = baseService;
	}

	public SessionMap<String,Object> getSessionMap() {
		return sessionMap;
	}

	public void setSessionMap(SessionMap<String,Object> sessionMap) {
		this.sessionMap = sessionMap;
	}

	public String getLoginError() {
		return loginError;
	}

	public void setLoginError(String loginError) {
		this.loginError = loginError;
	}
	
	public User getUser_login() {
		return user_login;
	}

	public void setUser_login(User user_login) {
		this.user_login = user_login;
	}
	public int getNbr_reservation() {
		return nbr_reservation;
	}
	public void setNbr_reservation(int nbr_reservation) {
		this.nbr_reservation = nbr_reservation;
	}
}
