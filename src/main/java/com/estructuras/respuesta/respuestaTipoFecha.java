/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.estructuras.respuesta;

import com.estructuras.tipo.*;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author oscar89p
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "respuestaTipoFecha", propOrder = {"estado","item"})

public class respuestaTipoFecha {
    
    @XmlElement( name = "estado")
    public estado estado;
    @XmlElement( name = "item")
    public List<tipoFecha> item = null;
    
    public respuestaTipoFecha(){
        estado = new estado();
    }
    
    public List<tipoFecha> getListaTipoFecha() {
        if(this.item == null) {
            this.item = new ArrayList<tipoFecha>();
        }
        return this.item;    
    }
    
}
