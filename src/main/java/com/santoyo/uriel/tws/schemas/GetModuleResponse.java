//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.7 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2018.05.06 a las 05:18:15 PM CDT 
//


package com.santoyo.uriel.tws.schemas;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para anonymous complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="module" type="{http://uriel.santoyo.com/tws/schemas}Module"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "module"
})
@XmlRootElement(name = "getModuleResponse")
public class GetModuleResponse {

    @XmlElement(required = true)
    protected Module module;

    /**
     * Obtiene el valor de la propiedad module.
     * 
     * @return
     *     possible object is
     *     {@link Module }
     *     
     */
    public Module getModule() {
        return module;
    }

    /**
     * Define el valor de la propiedad module.
     * 
     * @param value
     *     allowed object is
     *     {@link Module }
     *     
     */
    public void setModule(Module value) {
        this.module = value;
    }

}
