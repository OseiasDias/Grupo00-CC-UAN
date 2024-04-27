
package org.fcuan.ws.service;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.fcuan.ws.service package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _EntregarBicicletaResponse_QNAME = new QName("http://service.ws.fcuan.org/", "entregarBicicletaResponse");
    private final static QName _LevantarBicicletaResponse_QNAME = new QName("http://service.ws.fcuan.org/", "levantarBicicletaResponse");
    private final static QName _ObterInfoEstacao_QNAME = new QName("http://service.ws.fcuan.org/", "obterInfoEstacao");
    private final static QName _ObterInfoEstacaoResponse_QNAME = new QName("http://service.ws.fcuan.org/", "obterInfoEstacaoResponse");
    private final static QName _EntregarBicicleta_QNAME = new QName("http://service.ws.fcuan.org/", "entregarBicicleta");
    private final static QName _LevantarBicicleta_QNAME = new QName("http://service.ws.fcuan.org/", "levantarBicicleta");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.fcuan.ws.service
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link LevantarBicicleta }
     * 
     */
    public LevantarBicicleta createLevantarBicicleta() {
        return new LevantarBicicleta();
    }

    /**
     * Create an instance of {@link EntregarBicicleta }
     * 
     */
    public EntregarBicicleta createEntregarBicicleta() {
        return new EntregarBicicleta();
    }

    /**
     * Create an instance of {@link EntregarBicicletaResponse }
     * 
     */
    public EntregarBicicletaResponse createEntregarBicicletaResponse() {
        return new EntregarBicicletaResponse();
    }

    /**
     * Create an instance of {@link LevantarBicicletaResponse }
     * 
     */
    public LevantarBicicletaResponse createLevantarBicicletaResponse() {
        return new LevantarBicicletaResponse();
    }

    /**
     * Create an instance of {@link ObterInfoEstacao }
     * 
     */
    public ObterInfoEstacao createObterInfoEstacao() {
        return new ObterInfoEstacao();
    }

    /**
     * Create an instance of {@link ObterInfoEstacaoResponse }
     * 
     */
    public ObterInfoEstacaoResponse createObterInfoEstacaoResponse() {
        return new ObterInfoEstacaoResponse();
    }

    /**
     * Create an instance of {@link Estacao }
     * 
     */
    public Estacao createEstacao() {
        return new Estacao();
    }

    /**
     * Create an instance of {@link Doca }
     * 
     */
    public Doca createDoca() {
        return new Doca();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EntregarBicicletaResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.ws.fcuan.org/", name = "entregarBicicletaResponse")
    public JAXBElement<EntregarBicicletaResponse> createEntregarBicicletaResponse(EntregarBicicletaResponse value) {
        return new JAXBElement<EntregarBicicletaResponse>(_EntregarBicicletaResponse_QNAME, EntregarBicicletaResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LevantarBicicletaResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.ws.fcuan.org/", name = "levantarBicicletaResponse")
    public JAXBElement<LevantarBicicletaResponse> createLevantarBicicletaResponse(LevantarBicicletaResponse value) {
        return new JAXBElement<LevantarBicicletaResponse>(_LevantarBicicletaResponse_QNAME, LevantarBicicletaResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ObterInfoEstacao }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.ws.fcuan.org/", name = "obterInfoEstacao")
    public JAXBElement<ObterInfoEstacao> createObterInfoEstacao(ObterInfoEstacao value) {
        return new JAXBElement<ObterInfoEstacao>(_ObterInfoEstacao_QNAME, ObterInfoEstacao.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ObterInfoEstacaoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.ws.fcuan.org/", name = "obterInfoEstacaoResponse")
    public JAXBElement<ObterInfoEstacaoResponse> createObterInfoEstacaoResponse(ObterInfoEstacaoResponse value) {
        return new JAXBElement<ObterInfoEstacaoResponse>(_ObterInfoEstacaoResponse_QNAME, ObterInfoEstacaoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EntregarBicicleta }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.ws.fcuan.org/", name = "entregarBicicleta")
    public JAXBElement<EntregarBicicleta> createEntregarBicicleta(EntregarBicicleta value) {
        return new JAXBElement<EntregarBicicleta>(_EntregarBicicleta_QNAME, EntregarBicicleta.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LevantarBicicleta }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.ws.fcuan.org/", name = "levantarBicicleta")
    public JAXBElement<LevantarBicicleta> createLevantarBicicleta(LevantarBicicleta value) {
        return new JAXBElement<LevantarBicicleta>(_LevantarBicicleta_QNAME, LevantarBicicleta.class, null, value);
    }

}
