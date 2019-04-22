/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.estructuras.respuesta;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author oscar89p
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "estado", propOrder = {"codigo","descripcion","tipo","detalle"})

public class estado {
    
    @XmlElement( name = "codigo") 
    public String codigo;
    @XmlElement( name = "descripcion") 
    public String descripcion;
    @XmlElement( name = "tipo") 
    public String tipo;
    @XmlElement( name = "detalle") 
    public String detalle;
    
    public estado()
    {
        this.codigo = "";
        this.descripcion = "";
        this.tipo = "";
        this.detalle = "";
    }
}
