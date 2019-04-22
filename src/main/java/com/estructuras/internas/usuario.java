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
public class usuario {
 
    private String usuario;
    private String nombre;
    private String password;

    public usuario()
    {
        this.usuario = "";
        this.nombre = "";
        this.password = "";
    }
    
    public usuario(BasicDBObject Json)
    {
        this.usuario = Json.getString("usuario");
        this.nombre = Json.getString("nombre");
        this.password = Json.getString("password");
    }
    
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public BasicDBObject toJson()
    {
        BasicDBObject convierte = new BasicDBObject();
        
        convierte.append("usuario",this.getUsuario());
        convierte.append("nombre", this.getNombre());
        convierte.append("password", this.getPassword());
        
        return convierte;
    }
    
}
