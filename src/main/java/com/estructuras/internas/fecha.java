/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.estructuras.internas;

import com.mongodb.BasicDBObject;

/**
 *
 * @author oscar89p
 */
public class fecha {
 
    public String id;
    public String entrada;
    public String fecha;
    public String descripcion;
    public String categoria;
    public String tipo;
    
    public fecha()
    {
        this.id = "";
        this.entrada = "";
        this.fecha = "";
        this.descripcion = "";
        this.categoria = "";
        this.tipo = "";
    }
    
    public fecha(BasicDBObject Json)
    {
        this.entrada = Json.getString("entrada");
        this.fecha = Json.getString("fecha");
        this.descripcion = Json.getString("descripcion");
        this.categoria = Json.getString("categoria");
        this.tipo = Json.getString("tipo");
    }
    
}
