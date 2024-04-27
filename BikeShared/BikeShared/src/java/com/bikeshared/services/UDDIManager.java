/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bikeshared.services;

import java.net.MalformedURLException;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import javax.xml.namespace.QName;
import javax.xml.registry.BulkResponse;
import javax.xml.registry.Connection;
import javax.xml.registry.ConnectionFactory;
import javax.xml.registry.RegistryService;
import javax.xml.registry.BusinessLifeCycleManager;
import javax.xml.registry.BusinessQueryManager;
import javax.xml.registry.FindQualifier;
import javax.xml.registry.JAXRException;
import javax.xml.registry.infomodel.Key;
import javax.xml.registry.infomodel.Organization;
import javax.xml.registry.infomodel.Service;
import javax.xml.registry.infomodel.ServiceBinding;
import org.fcuan.ws.service.StationServer;


public class UDDIManager {
    
    static BusinessQueryManager businessQueryManager;
    static BusinessLifeCycleManager businessLifeCycleManager;
    static String organizationName;
    static Organization organization=null;
    static Collection<Service>services ;
    private static void connectToJuddi()
    {
        try{
            ConnectionFactory connFactory = org.apache.ws.scout.registry.ConnectionFactoryImpl.newInstance();
            
            Properties props = new Properties();
            // Localização do ficheiro de configuração da ligação,
            // que deve estar na directoria WEB-INF/classes do .war
            props.setProperty("scout.juddi.client.config.file", "uddi.xml");
            // URL para pesquisas ao UDDI registry
            props.setProperty("javax.xml.registry.queryManagerURL", "http://localhost:8080/juddiv3/services/inquiry");
            // URL para publicar dados no UDDI registry
            props.setProperty("javax.xml.registry.lifeCycleManagerURL", "http://localhost:8080/juddiv3/services/publish");
            // URL do gestor de segurança do UDDI registry
            props.setProperty("javax.xml.registry.securityManagerURL", "http://localhost:8080/juddiv3/services/security");
            // Versão UDDI que o registry usa
            props.setProperty("scout.proxy.uddiVersion", "3.0");
            // Protocolo de transporte usado para invocações ao UDDI registry
            props.setProperty("scout.proxy.transportClass", "org.apache.juddi.v3.client.transport.JAXWSTransport");
            connFactory.setProperties(props);
            
            
            // Finalmente, estabelece a ligação ao UDDI registry
            Connection connection = connFactory.createConnection();

            // Define credenciais de autenticação a usar para interação com o UDDI registry
            // Nota: o jUDDI fornecido para utilização no projecto está configurado
            // para aceitar qualquer par username/password; no mundo real, não assim
            PasswordAuthentication passwdAuth = new PasswordAuthentication("username", "password".toCharArray());
            Set<PasswordAuthentication> creds = new HashSet<PasswordAuthentication>();
            creds.add(passwdAuth);
            connection.setCredentials(creds);

            // Obter objecto RegistryService
            RegistryService rs = connection.getRegistryService();

            // Obter objecto QueryManager da JAXR Business API
            // (caso se pretenda fazer pesquisas)
            businessQueryManager = rs.getBusinessQueryManager();
            // Obter objecto BusinessLifeCycleManager da JAXR Business API
            // (caso se pretenda registar/alterar informação no UDDI registry)
            businessLifeCycleManager = rs.getBusinessLifeCycleManager();
            System.out.println("Conexão ao Juddi bem sucedida...");
            
        }catch(Exception e){
            System.out.println("ERRO: "+e.getMessage());
        }
        
    }
    
    public static void setOrganization(String name)
    {
        try{
            UDDIManager.connectToJuddi();
            Organization o ;
//            UDDIManager.organization = ;
            if((o = findOrganizationByName(name)) != null){
                UDDIManager.organization = o;
                System.out.println("Organization set");
            } else{
                System.err.println("Organization not set");
            }
        }catch(Exception e)
        {
            System.err.println("NOTICE: Failed to set Organization "+e.getMessage());
        }
    }
    public static Organization findOrganizationByName(String name) throws JAXRException
    {
        Collection findQualifiers = new ArrayList();
        findQualifiers.add(FindQualifier.SORT_BY_NAME_DESC);
        
        Collection namePatterns = new ArrayList();
        namePatterns.add("%"+name+"%");
        
        System.out.println("Buscando Organizacao");
        BulkResponse response = businessQueryManager.findOrganizations(findQualifiers,namePatterns,null,null, null, null);
        
        System.out.println("Consultando resposta");
        Collection orgs = response.getCollection();
        Iterator iterOrg = orgs.iterator();
        System.out.println("Iterando pela resposta");
        if(iterOrg.hasNext())
        {
            Organization org = (Organization) iterOrg.next();
            return org;
        }
        return null;
    }
     public static void findAndUpdateServices() throws JAXRException
    {
        
        UDDIManager.setOrganization("SDws");
        Collection<Service> foundServices= new ArrayList();
//        Collection findQualifiers = new ArrayList();
//        findQualifiers.add(FindQualifier.SORT_BY_DATE_DESC);
//        
//        Collection namePatterns = new ArrayList();
//        namePatterns.add("%"+serviceName+"%");
//        Key key = businessLifeCycleManager.createKey(orgKey);
//        BulkResponse response =businessQueryManager.findServices(key, findQualifiers, namePatterns, null, null);
        
        Collection services = organization.getServices();
        Iterator iterKeys = services.iterator();
        
        while(iterKeys.hasNext())
        {
            Service service = (Service) iterKeys.next();
            
//            System.out.println("----------------------------");
//            System.out.println("Station Server: "+service.getName().getValue());
//            System.out.println("Description: "+ service.getDescription().getValue());
            Collection sb = service.getServiceBindings();
            Iterator iterB = sb.iterator();
            if(iterB.hasNext()){
                ServiceBinding sbind = (ServiceBinding) iterB.next();
//                System.out.println("URL: "+sbind.getAccessURI());;
                foundServices.add(service);
            }
//            System.out.println("----------------------------");
//            return service;
        }
        UDDIManager.services = foundServices;
//        return null;
    }
     
     public static void printServices()throws JAXRException
     {
        Iterator iterKeys = services.iterator();
        
        while(iterKeys.hasNext())
        {
            Service service = (Service) iterKeys.next();
            
            System.out.println("----------------------------");
            System.out.println("Station Server: "+service.getName().getValue());
            System.out.println("Description: "+ service.getDescription().getValue());
            Collection sb = service.getServiceBindings();
            Iterator iterB = sb.iterator();
            if(iterB.hasNext()){
                ServiceBinding sbind = (ServiceBinding) iterB.next();
                System.out.println("URL: "+sbind.getAccessURI());
            }
            System.out.println("----------------------------");
//           
        }
     }
     public static Service firstService()
     {
         Iterator i = services.iterator();
         if(i.hasNext())
         {
            Service se = (Service) i.next();
            return se;
         }
          return null;    
     }
     
     public static Service findStationService(String estacao) throws JAXRException
     {
         System.out.println("FINDING STATION");
         Iterator i = services.iterator();
         while(i.hasNext())
         {
            
            Service se = (Service) i.next();
            System.out.println("Comparing to: "+se.getName().getValue());
            if(estacao.equals(se.getName().getValue()))
            {
                
                System.out.println("STATION FOUND ");
                return se;
            }
         }
         System.out.println("STATION NOT FOUND ");
          return null;    
     }
     
     public static StationServer findStationServerPort(String StationName)
     {
       
         try{
        
            javax.xml.registry.infomodel.Service service = UDDIManager.findStationService(StationName);
            
            if(service!=null){
                System.out.println("Estacao Encontrada");
                Collection sb = service.getServiceBindings();
                Iterator iterB = sb.iterator();
                if(iterB.hasNext()){
                    ServiceBinding sbind = (ServiceBinding) iterB.next();
                    System.out.println("URL: "+sbind.getAccessURI());
                
                    URL url = new URL(sbind.getAccessURI());
                    QName name = new QName("http://service.ws.fcuan.org/","StationServerImplService");
//            
                    javax.xml.ws.Service services = javax.xml.ws.Service.create(url, name);
            
                    StationServer station = services.getPort(StationServer.class);
//                  
                    return station;
                }
            
            }
            System.out.println("NAO ENCONTRADA A Estacao");
            UDDIManager.findAndUpdateServices();
            return null;
        }catch(JAXRException e)
        {
            System.out.println("ERROR Tryng to get Station: "+e.getMessage());
            return null;
        }catch(MalformedURLException e)
        {
            System.out.println("ERROR: url mal formada "+e.getMessage());
            return null;
        }
        catch(Exception e)
        {
            System.out.println("ERROR: "+e.getMessage());
            return null;
        }
     }
}
