
package org.fcuan.ws.service;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "StationServerImplService", targetNamespace = "http://service.ws.fcuan.org/", wsdlLocation = "http://localhost:9000/Station?wsdl")
public class StationServerImplService
    extends Service
{

    private final static URL STATIONSERVERIMPLSERVICE_WSDL_LOCATION;
    private final static WebServiceException STATIONSERVERIMPLSERVICE_EXCEPTION;
    private final static QName STATIONSERVERIMPLSERVICE_QNAME = new QName("http://service.ws.fcuan.org/", "StationServerImplService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost:9000/Station?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        STATIONSERVERIMPLSERVICE_WSDL_LOCATION = url;
        STATIONSERVERIMPLSERVICE_EXCEPTION = e;
    }

    public StationServerImplService() {
        super(__getWsdlLocation(), STATIONSERVERIMPLSERVICE_QNAME);
    }

    public StationServerImplService(WebServiceFeature... features) {
        super(__getWsdlLocation(), STATIONSERVERIMPLSERVICE_QNAME, features);
    }

    public StationServerImplService(URL wsdlLocation) {
        super(wsdlLocation, STATIONSERVERIMPLSERVICE_QNAME);
    }

    public StationServerImplService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, STATIONSERVERIMPLSERVICE_QNAME, features);
    }

    public StationServerImplService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public StationServerImplService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns StationServer
     */
    @WebEndpoint(name = "StationServerImplPort")
    public StationServer getStationServerImplPort() {
        return super.getPort(new QName("http://service.ws.fcuan.org/", "StationServerImplPort"), StationServer.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns StationServer
     */
    @WebEndpoint(name = "StationServerImplPort")
    public StationServer getStationServerImplPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://service.ws.fcuan.org/", "StationServerImplPort"), StationServer.class, features);
    }

    private static URL __getWsdlLocation() {
        if (STATIONSERVERIMPLSERVICE_EXCEPTION!= null) {
            throw STATIONSERVERIMPLSERVICE_EXCEPTION;
        }
        return STATIONSERVERIMPLSERVICE_WSDL_LOCATION;
    }

}
