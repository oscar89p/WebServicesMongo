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
public class telefonoXdireccion {
    
    public String entrada;
    public String telefono;
    public String direccion;
    
    public telefonoXdireccion()
    {
        this.entrada = "";
        this.telefono = "";
        this.direccion = "";
    }
    
    public telefonoXdireccion(BasicDBObject Json)
    {
        this.entrada = Json.getString("entrada");
        this.telefono = Json.getString("telefono");
        this.direccion = Json.getString("direccion");
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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }  
    
    public BasicDBObject toJson()
    {
        BasicDBObject convierte = new BasicDBObject();
        
        convierte.append("entrada",this.getEntrada());
        convierte.append("telefono", this.getTelefono());
        convierte.append("direccion", this.getDireccion());
        
        return convierte;
    }
}
