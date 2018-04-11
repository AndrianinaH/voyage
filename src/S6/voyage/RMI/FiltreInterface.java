package S6.voyage.RMI;

import S6.voyage.baseModel.BaseModel;
import S6.voyage.baseModel.HotelView;

import java.rmi.Remote;
import java.util.List;

/**
 * Created by Andrianina_pc on 07/02/2018.
 */
public interface FiltreInterface extends Remote {

    //--------------- recherche d'hotel ou de destination -----------------//
    public List<BaseModel> findHotelOrDestination(HotelView obj, String find)  throws Exception;
}
