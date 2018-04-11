package S6.voyage.RMI;

import S6.voyage.baseModel.BaseModel;
import S6.voyage.baseModel.HotelView;

import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

/**
 * Created by Andrianina_pc on 07/02/2018.
 */
public class RMIServer {

    public static void main(String[] args){
        try {

            if(System.getSecurityManager() == null) System.setSecurityManager(null);

            FiltreImpl filtreImpl = new FiltreImpl();
            //List<HotelView> allHotelFind =  ((List<HotelView>)(Object)filtreImpl.findHotelOrDestination(new HotelView(),"Toky"));
            FiltreInterface filter = (FiltreInterface) UnicastRemoteObject.exportObject(filtreImpl,0);
            Registry registry = LocateRegistry.createRegistry(8081);
            registry.bind("filtreMandeTsara",filter);
            System.out.println("Server ready...");


        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
