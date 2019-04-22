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
public class telefono {
    
    private String entrada;
    private String telefono;
    private String tipo;
    
    public telefono()
    {
        this.entrada = "";
        this.telefono = "";
        this.tipo = "";
    }
    
    public telefono(BasicDBObject Json)
    {
        this.entrada = Json.getString("entrada");
        this.telefono = Json.getString("telefono");
        this.tipo = Json.getString("tipo");
    }

    public String getEntrada() {
        return entrada;
    }

    public void setEntrada(String entrada) {
        this.entrada = entrada;
    }
    
    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
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
        convierte.append("telefono", this.getTelefono());
        convierte.append("tipo", this.getTipo());
        
        return convierte;
    }
}
