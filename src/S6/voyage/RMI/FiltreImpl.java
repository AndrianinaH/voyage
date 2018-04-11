package S6.voyage.RMI;

import S6.voyage.baseModel.BaseModel;
import S6.voyage.baseModel.HotelView;
import S6.voyage.service.FiltreService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

/**
 * Created by Andrianina_pc on 07/02/2018.
 */
public class FiltreImpl implements FiltreInterface {

    public FiltreImpl() throws RemoteException {
        super();
    }

    @Override
    public List<BaseModel> findHotelOrDestination(HotelView obj, String find) throws Exception {
        ApplicationContext appContext = new ClassPathXmlApplicationContext("Beans.xml");
        FiltreService filtreService = (FiltreService) appContext.getBean("filtreService");
        return filtreService.findHotelOrDestination(obj,find);
    }
}
