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
public class emailXdireccion {
    
    private String entrada;
    private String email;
    private String direccion;
    
    public emailXdireccion()
    {
        this.entrada = "";
        this.email = "";
        this.direccion = "";
    }
    
    public emailXdireccion(BasicDBObject Json)
    {
        this.entrada = Json.getString("entrada");
        this.email = Json.getString("email");
        this.direccion = Json.getString("direccion");
    }

    public String getEntrada() {
        return entrada;
    }

    public void setEntrada(String entrada) {
        this.entrada = entrada;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
        convierte.append("email", this.getEmail());
        convierte.append("direccion", this.getDireccion());
        
        return convierte;
    }
}
