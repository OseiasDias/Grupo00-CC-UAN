
package org.fcuan.ws.service;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for estacao complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="estacao">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="capacidade" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="coordenada" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="disponivel" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="docas" type="{http://service.ws.fcuan.org/}doca" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="nome" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="premio" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "estacao", propOrder = {
    "capacidade",
    "coordenada",
    "disponivel",
    "docas",
    "id",
    "nome",
    "premio"
})
public class Estacao {

    protected int capacidade;
    protected String coordenada;
    protected boolean disponivel;
    @XmlElement(nillable = true)
    protected List<Doca> docas;
    protected int id;
    protected String nome;
    protected int premio;

    /**
     * Gets the value of the capacidade property.
     * 
     */
    public int getCapacidade() {
        return capacidade;
    }

    /**
     * Sets the value of the capacidade property.
     * 
     */
    public void setCapacidade(int value) {
        this.capacidade = value;
    }

    /**
     * Gets the value of the coordenada property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCoordenada() {
        return coordenada;
    }

    /**
     * Sets the value of the coordenada property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCoordenada(String value) {
        this.coordenada = value;
    }

    /**
     * Gets the value of the disponivel property.
     * 
     */
    public boolean isDisponivel() {
        return disponivel;
    }

    /**
     * Sets the value of the disponivel property.
     * 
     */
    public void setDisponivel(boolean value) {
        this.disponivel = value;
    }

    /**
     * Gets the value of the docas property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the docas property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDocas().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Doca }
     * 
     * 
     */
    public List<Doca> getDocas() {
        if (docas == null) {
            docas = new ArrayList<Doca>();
        }
        return this.docas;
    }

    /**
     * Gets the value of the id property.
     * 
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     */
    public void setId(int value) {
        this.id = value;
    }

    /**
     * Gets the value of the nome property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNome() {
        return nome;
    }

    /**
     * Sets the value of the nome property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNome(String value) {
        this.nome = value;
    }

    /**
     * Gets the value of the premio property.
     * 
     */
    public int getPremio() {
        return premio;
    }

    /**
     * Sets the value of the premio property.
     * 
     */
    public void setPremio(int value) {
        this.premio = value;
    }

}
