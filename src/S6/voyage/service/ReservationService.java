package S6.voyage.service;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import S6.voyage.baseModel.Reservation;


public class ReservationService extends BaseService
{
	//-------------------- getNombre de reservation par client -----------------//
	 public int getNbrReservationByClient(Reservation res) throws Exception
    {
    	Session session = null;
    	String sql="SELECT COUNT(*) FROM reservation WHERE id_client=:id_client";
    	try{
    		session = hibernate.getSessionFactory().openSession();
    		Query query = session.createSQLQuery(sql);
    		query.setInteger("id_client", res.getId_client());
    		List results = query.list();
    		return Integer.parseInt(String.valueOf(results.get(0)));
    	}
    	catch(Exception ex){
    		ex.printStackTrace();
    		throw ex;
    	}
    	finally{
    		session.close();
    	}
    	
    }
}
