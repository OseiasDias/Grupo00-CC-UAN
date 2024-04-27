
import com.bikeshared.Dao.CiclistaDao;
import com.bikeshared.Dao.EstacaoDao;
import com.bikeshared.Dao.UserDao;
import com.bikeshared.models.Ciclista;
import com.bikeshared.models.Doca;
import com.bikeshared.models.Estacao;
import com.bikeshared.models.User;
import com.bikeshared.services.D12_BikeShared;
import com.bikeshared.services.UDDIManager;
import com.bikeshared.utils.Conexao;
import java.net.URL;
import java.util.Collection;
import java.util.Iterator;
import javax.xml.namespace.QName;
import javax.xml.registry.infomodel.ServiceBinding;
import javax.xml.ws.Endpoint;
import javax.xml.ws.Service;
//import javax.xml.registry.infomodel.Service;
import org.fcuan.ws.service.StationServer;
//import services.D10Station;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


public class Main {
    
    public static void main(String[] args) {
        try{
//            UserDao dao = new UserDao(Conexao.getInstance());
//            CiclistaDao cidao = new CiclistaDao(Conexao.getInstance());
//            
//            Ciclista ci = new Ciclista("joana","joaninha@gmail","1234",100);
//            int id = dao.create(ci);
//            System.out.println("User created");
//            ci.setId(id);
//            cidao.create(ci);
//            System.out.println("Ciclist created");
//            for(User user:dao.all())
//            {
//                System.out.println("ID: "+user.getId());
//                System.out.println("USERNAME: "+user.getUsername());
//                System.out.println("EMAIL: "+user.getEmail());
//                System.out.println("PASSWORD: "+user.getPassword());
//            }
//              User user = dao.find(5);
//              if(user!=null){
//                  System.out.println("ID: "+user.getId());
//                  System.out.println("USERNAME: "+user.getUsername());
//                  System.out.println("EMAIL: "+user.getEmail());
//                  System.out.println("PASSWORD: "+user.getPassword());
//                  System.out.println("Presented");
//              }
//              
              
              //dao.delete(1);
//              System.out.println("Deleted");
//                EstacaoDao dao = new EstacaoDao(Conexao.getInstance());
//                int id = dao.create(new Estacao("estacaodaUan",5,1));
                
//                Estacao est = dao.find(2);
//                  for(Estacao est: dao.all())
//                  {
//                      System.out.println("Estacao:"+est.getId()+" - "+est.getCoordenada());
//                  }
                  
//                   dao.trancarDoca(1);
//                  for(Doca dc: dao.getDocas(1))
//                  {
//                      System.out.printf("Doca: %s",((dc.getEstado()==true)?"Trancada":"Destrancada") );
//                  }
//                System.out.println("Estacao encontrada: "+est.getCoordenada());

//                    CiclistaDao cidao = new CiclistaDao(Conexao.getInstance());
//                    Ciclista ci = cidao.findByEmail("joaninha@gmail");
//                    System.out.println(ci.getUsername()+" - "+ci.getSaldo());

//                    dao = new EstacaoDao(Conexao.getInstance());
//                    dao.addDoca(2);

//////////////////////////////////////////////////////////////////////////////////
            
            /*UDDIManager.findAndUpdateServices();
            UDDIManager.printServices();
            javax.xml.registry.infomodel.Service service = UDDIManager.firstService(); 
            
            */
            /*if(service!=null){
                
            Collection sb = service.getServiceBindings();
            Iterator iterB = sb.iterator();
            if(iterB.hasNext()){
                ServiceBinding sbind = (ServiceBinding) iterB.next();
                System.out.println("URL: "+sbind.getAccessURI());
                
                URL url = new URL(sbind.getAccessURI());
                QName name = new QName("http://service.ws.fcuan.org/","StationServerImplService");
//            
                Service services = Service.create(url, name);
            
                StationServer station = services.getPort(StationServer.class);
                System.out.println(station.obterInfoEstacao(1));
            }
            
            }
            */
            
////////////////////////////////////////////////////////////////////////////////
        
        try{
            UDDIManager.findAndUpdateServices();
        }catch(Exception e)
        {
            System.err.println("NOTICE: Services from UDDI not Found:"+e.getMessage());
        }
        
        publishBikeSharedWebService();
        
        
        }catch(Exception e)
        {
            System.out.println("ERROR:" +e.getMessage());
        }
    }
    
    public static void publishBikeSharedWebService()
    {
        String url = "http://localhost:5000/D12_BikeSharedServer";
        Endpoint.publish(url, new D12_BikeShared());
        System.out.println("----------BikeSharedD12 Server published--------");
        System.out.println(url);
        
    }
}
