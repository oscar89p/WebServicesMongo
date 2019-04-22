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
public class email {
    
    private String entrada;
    private String email;
    private String favorito;
    
    public email()
    {
        this.entrada = "";
        this.email = "";
        this.favorito = "";
    }
    
    public email(BasicDBObject Json)
    {
        this.entrada = Json.getString("entrada");
        this.email = Json.getString("email");
        this.favorito = Json.getString("favorito");
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

    public String getFavorito() {
        return favorito;
    }

    public void setFavorito(String favorito) {
        this.favorito = favorito;
    }
        
    public BasicDBObject toJson()
    {
        BasicDBObject convierte = new BasicDBObject();
        
        convierte.append("entrada",this.getEntrada());
        convierte.append("email", this.getEmail());
        convierte.append("favorito", this.getFavorito());
        
        return convierte;
    }
}
