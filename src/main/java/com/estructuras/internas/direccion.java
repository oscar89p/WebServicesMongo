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
public class direccion {
    
    private String entrada;
    private String direccion;
    private String tipo;
    
    public direccion()
    {
        this.entrada = "";
        this.direccion = "";
        this.tipo = "";
    }
        
    public direccion(BasicDBObject Json)
    {
        this.entrada = Json.getString("entrada");
        this.direccion = Json.getString("direccion");
        this.tipo = Json.getString("tipo");
    }

    public String getEntrada() {
        return entrada;
    }

    public void setEntrada(String entrada) {
        this.entrada = entrada;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    } 
    
    public BasicDBObject toJson()
    {
        BasicDBObject convierte = new BasicDBObject();
        
        convierte.append("entrada",this.getEntrada());
        convierte.append("direccion", this.getDireccion());
        convierte.append("tipo", this.getTipo());
        
        return convierte;
    }
}
