/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bikeshared.services;

import com.bikeshared.Dao.UserDao;
import com.bikeshared.Dao.CiclistaDao;
import com.bikeshared.Dao.EstacaoDao;
import com.bikeshared.models.Ciclista;
import com.bikeshared.models.Doca;
import com.bikeshared.models.Estacao;
import com.bikeshared.models.User;
import com.bikeshared.utils.Conexao;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.xml.namespace.QName;
import javax.xml.registry.JAXRException;
import javax.xml.registry.infomodel.ServiceBinding;
import javax.xml.ws.Service;
import org.fcuan.ws.service.StationServer;
//import org.fcuan.ws.service.Doca;
//import services.D10Station;

@WebService(serviceName = "D12_BikeShared")
public class D12_BikeShared {

    @WebMethod(operationName = "ativarUtilizador")
    public int ativarUtilizador(@WebParam(name = "email") String email) {
        try {
            System.out.println("EMAIL: " + email);
            UserDao dao = new UserDao(Conexao.getInstance());
            CiclistaDao cidao = new CiclistaDao(Conexao.getInstance());

            Ciclista ciclista = new Ciclista(email, email, "1234", 10);
            int id = dao.create(ciclista);
            ciclista.setId(id);
            cidao.create(ciclista);
            System.out.println("ciclista email: " + ciclista.getEmail());
            System.out.println("Ciclista Created...");
            return 1;
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
        return 0;
    }

    @WebMethod(operationName = "login")
    public Ciclista login(@WebParam(name = "email") String email, @WebParam(name = "password") String password) {
        try {
            CiclistaDao dao = new CiclistaDao(Conexao.getInstance());

            Ciclista ciclista = dao.findByEmail(email);
            if (ciclista != null) {
                if (ciclista.getPassword().equals(password)) {
                    return ciclista;
                } else {
                    return null;
                }
            }
            return ciclista;
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
            return null;
        }
    }

    @WebMethod(operationName = "findCiclista")
    public Ciclista findCiclista(@WebParam(name = "email") String email) {
        try {
            CiclistaDao dao = new CiclistaDao(Conexao.getInstance());

            Ciclista ciclista = dao.findByEmail(email);
            if (ciclista != null) {
                return ciclista;
            }
            return null;
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
            return null;
        }
    }

    @WebMethod(operationName = "loginManager")
    public User loginManager(@WebParam(name = "email") String email, @WebParam(name = "password") String password) {
        try {
            UserDao dao = new UserDao(Conexao.getInstance());

            User user = dao.findUserByEmail(email);
            if (user != null) {
                if (user.getPassword().equals(password)) {
                    return user;
                } else {
                    return null;
                }
            }
            return user;
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
            return null;
        }
    }

    @WebMethod(operationName = "obterInfoEstacao")
    public Estacao obterInfoEstacao(@WebParam(name = "name_estacao") String namestacao) {

        Estacao estacaoModel = new Estacao();
        ArrayList<Doca> d = new ArrayList<>();

        try {
            javax.xml.registry.infomodel.Service service = UDDIManager.findStationService(namestacao);

            if (service != null) {
                System.out.println("Estacao Encontrada");
                Collection sb = service.getServiceBindings();
                Iterator iterB = sb.iterator();
                if (iterB.hasNext()) {
                    ServiceBinding sbind = (ServiceBinding) iterB.next();
                    System.out.println("URL: " + sbind.getAccessURI());

                    URL url = new URL(sbind.getAccessURI());
                    QName name = new QName("http://service.ws.fcuan.org/", "StationServerImplService");
//            
                    Service services = Service.create(url, name);

                    StationServer station = services.getPort(StationServer.class);
//                    System.out.println();
                    System.out.println("PORT ACCESSED");
                    org.fcuan.ws.service.Estacao e = station.obterInfoEstacao(namestacao);
                    System.out.println("ESTACAO OBTIDA");
                    System.out.println("NOME: " + e.getNome());
                    System.out.println("COORDENADA: " + e.getCoordenada());
                    System.out.println("PREMIO: " + e.getPremio());
                    estacaoModel = buildStationModel(e);

                    List list = e.getDocas();
                    d = (ArrayList) buildDockaList(list);
                    estacaoModel.setDocas((d));

                    return estacaoModel;
//                    return d;
                }

            }
            System.out.println("NAO ENCONTRADA A Estacao");
//            return d;
            UDDIManager.findAndUpdateServices();
            return null;
        } catch (JAXRException e) {
            System.out.println("ERROR Tryng to get Station: " + e.getMessage());
            return null;
        } catch (MalformedURLException e) {
            System.out.println("ERROR: url mal formada " + e.getMessage());
            return null;
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
            return null;
        }
    }

    @WebMethod(operationName = "listarEstacoes")
    public ArrayList<Estacao> listarEstacoes(@WebParam(name = "premio") int premio) {
        try {
            EstacaoDao dao = new EstacaoDao(Conexao.getInstance());

            ArrayList<Estacao> estacoes = dao.all();
            return estacoes;
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
        return null;
    }

    @WebMethod(operationName = "levantarBicicleta")
    public int levantarBicicleta(@WebParam(name = "station_id") String station, @WebParam(name = "email") String email) {
        try {
            System.out.println("USER: " + email);
            // finding user
            CiclistaDao ciclistaDao = new CiclistaDao(Conexao.getInstance());
            Ciclista ciclista = ciclistaDao.findByEmail(email);

            if (!ciclistaPodeLevantar(ciclista)) {
                return 0;
            }
            System.out.println("USER " + email + "Pode levantar");

            // finding Station Server to call operation
            StationServer stationServer = UDDIManager.findStationServerPort(station);

            if (stationServer == null) {
                return 0;
            }

            if (stationServer.levantarBicicleta(station, email) == 1) {

                System.out.println("Bicicleta Levantada");
                ciclista.setTem_bike(true);
                ciclista.setSaldo((ciclista.getSaldo() - 1));
                ciclistaDao.update(ciclista);
                return 1;
            } else {
                return 0;
            }
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
        return 0;
    }

    @WebMethod(operationName = "consultarSaldo")
    public Double consultarSaldo(@WebParam(name = "email") String email) {
        try {
            CiclistaDao dao = new CiclistaDao(Conexao.getInstance());

            Ciclista ci = dao.findByEmail(email);
            if (ci == null) {
                System.out.println("Ciclista Not Found:" + email);
                return 0.0;
            }
            System.out.println("Ciclista Found... " + ci.getEmail());
            return ci.getSaldo();
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
        return 0.0;
    }

    @WebMethod(operationName = "teste")
    public String teste(@WebParam(name = "id_estacao") int idEstacao) {
        try {

        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
        return null;
    }

    @WebMethod(operationName = "listarCiclistas")
    public ArrayList<Ciclista> listarCiclistas() {
        try {
            CiclistaDao dao = new CiclistaDao(Conexao.getInstance());

            ArrayList<Ciclista> ciclistas = dao.all();

            return ciclistas;
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
        return null;
    }

//    Helper Function to build Docka ArrayList
    private ArrayList<Doca> buildDockaList(List<org.fcuan.ws.service.Doca> l) {
        ArrayList<Doca> arr = new ArrayList<>();
        for (org.fcuan.ws.service.Doca doca : l) {
            Doca d = new Doca();
            d.setId(doca.getId());
            d.setEstado(doca.isEstado());
            arr.add(d);
        }
        return arr;
    }

    // Helper function to build Station Model
    private Estacao buildStationModel(org.fcuan.ws.service.Estacao e) {
        Estacao estacaoModel = new Estacao();
        estacaoModel.nome = e.getNome();
        estacaoModel.setCapacidade(e.getCapacidade());
        estacaoModel.setCoordenada(e.getCoordenada());
        estacaoModel.setId(e.getId());
        estacaoModel.setPremio(e.getPremio());
        estacaoModel.setDisponivel(e.isDisponivel());
        return estacaoModel;
    }

    // Helper function to verify if ciclista can rent
    private boolean ciclistaPodeLevantar(Ciclista ciclista) {
        if (ciclista == null) {
            System.err.println("Ciclista não encontrado");
            return false;
        }
        if (ciclista.getSaldo() <= 0) {
            System.err.println("Ciclista não tem saldo suficiente");
            return false;
        }
        if (ciclista.isTem_bike() == true) {
            System.err.println("Ciclista já tem bicicleta levantada");
            return false;
        }
        return true;
    }

    /**
     * Operação de Web service
     */
    @WebMethod(operationName = "CadastrarUsuario")
    public String CadastrarUsuario(@WebParam(name = "nome") String nome, @WebParam(name = "email") String email, @WebParam(name = "senha") String senha) {
        UserDao opusuario = new UserDao();
        if (opusuario.createUsuario(nome, email, senha)) {
            System.out.println("Usuário Cadastrado com Sucesso");
            return "AAAAAAAA";
        }else{
            System.out.println("Falha ao Cadastrar");   
        }
        return "nnnv";
     
    }
    
 /**    
   @WebMethod(operationName = "RecuperarUsuarios");;
    public List<String> RecuperarUsuarios() {
    UserDao opusuario = new UserDao();
    List<String> usuarios = opusuario.getUsuarios();
    return usuarios;
}
 */
    /**
     * Operação de Web service
     */
    @WebMethod(operationName = "p")
    public String p() {
        
        return null;
    }

    /**
     * Operação de Web service
     */
     /**
    @WebMethod(operationName = "ListarUsuario")
    public ArrayList<User>  ListarUsuario(@WebParam(name = "username") String username, @WebParam(name = "email") String email, @WebParam(name = "password") int password) {
            UserDao opusuario = new UserDao();
            List<String> User = opusuario.getUser();

        return User;
    }
   */
    /**
     * Operação de Web service
     */
@WebMethod(operationName = "ListarUsuarios")
   public ArrayList<User> ListarUsuarios() {  
       
        try {
            UserDao dao = new UserDao(Conexao.getInstance());

            ArrayList<User> users = dao.all();

            return users;
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
        return null;
      
    }

    /**
     * Operação de Web service
     */
    @WebMethod(operationName = "ExcluirUsuario")
    public int ExcluirUsuario(@WebParam(name = "id") final int id) {
        try {
        UserDao opusuario = new UserDao(Conexao.getInstance());
        return opusuario.delete(id);
    } catch (Exception e) {
        System.out.println("ERROR: " + e.getMessage());
    }
    return id;
    }

    /**
     * Operação de Web service
     */
    @WebMethod(operationName = "ExcluirUsuarios")
    public int ExcluirUsuarios(@WebParam(name = "id") int id) {
      
       try {
        // Create a UserDao instance with a database connection from Conexao
        UserDao opusuario = new UserDao(Conexao.getInstance());

        // Call the delete method to delete the user and return the result
        int result = opusuario.delete(id);

        // Return the result
        return result;
    } catch (Exception e) {
        // Handle exceptions by printing an error message and logging the exception
        System.err.println("ERROR: " + e.getMessage());
        e.printStackTrace(); // Log the exception stack trace

        // Return an error code or throw a custom exception if necessary
        return -1; // You can choose an appropriate error code
    }
    }

    /**
     * Operação de Web service
     */
    @WebMethod(operationName = "ExcluirEstacao")
    public int ExcluirEstacao(@WebParam(name = "id")  int id) {
             try {
        // Create a UserDao instance with a database connection from Conexao
            EstacaoDao dao = new EstacaoDao(Conexao.getInstance());

        // Call the delete method to delete the user and return the result
        int result = dao.delete(id);

        // Return the result
        return result;
    } catch (Exception e) {
        // Handle exceptions by printing an error message and logging the exception
        System.err.println("ERROR: " + e.getMessage());
        e.printStackTrace(); // Log the exception stack trace

        // Return an error code or throw a custom exception if necessary
        return -1; // You can choose an appropriate error code
    }
    }

    /**
     * Operação de Web service
     */
    @WebMethod(operationName = "ExcluirCiclista")
    public String ExcluirCiclista(@WebParam(name = "id") final int id) {
        //TODO write your implementation code here:
        return null;
    }

    
    
    
    

    /**
     * Operação de Web service
     */
    /**
    @WebMethod(operationName = "ListarDocas")
    public ArrayList<Doca>  ListarDocas() {
                   try {
        UserDao opusuario = new UserDao(Conexao.getInstance());

            ArrayList<Doca> docas = opusuario.all();

            return docas;
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
        return null;
    }
      */
}