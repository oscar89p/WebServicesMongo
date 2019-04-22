/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.estructuras.tipo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author oscar89p
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tipoPersona", propOrder = {"id","tipo","descripcion"})

public class tipoPersona {
    
    @XmlElement( name = "id")
    public String id;
    @XmlElement( name = "tipo") 
    public String tipo;
    @XmlElement( name = "descripcion") 
    public String descripcion;
    
    public tipoPersona()
    {
        this.id = "";
        this.tipo = "";
        this.descripcion = "";
    }
    
}
