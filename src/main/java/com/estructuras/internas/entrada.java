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
public class entrada {
    
    private String identificador;
    private String nombre;
    private String tipoPersona;
    private String tipoContacto;
    private int noDireccion;
    private int noTelefo;
    private int noFecha;
    private int noEmail;
    private String fotografia;
    private String favorito;
    private String usuario;

    public entrada() {
        
        this.identificador = "";
        this.nombre = "";
        this.tipoPersona = "";
        this.tipoContacto = "";
        this.noDireccion = 0;
        this.noTelefo = 0;
        this.noFecha = 0;
        this.noEmail = 0;
        this.fotografia = "";
        this.favorito = "";
        this.usuario = "";
    }
    
    public entrada(BasicDBObject Json) {
        
        this.identificador = Json.getString("identificador");
        this.nombre = Json.getString("nombre");
        this.tipoPersona = Json.getString("tipoPersona");
        this.tipoContacto = Json.getString("tipoContacto");
        this.fotografia = Json.getString("fotografia");
        this.favorito = Json.getString("favorito");
        this.usuario = Json.getString("usuario");
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipoPersona() {
        return tipoPersona;
    }

    public void setTipoPersona(String tipoPersona) {
        this.tipoPersona = tipoPersona;
    }

    public String getTipoContacto() {
        return tipoContacto;
    }

    public void setTipoContacto(String tipoContacto) {
        this.tipoContacto = tipoContacto;
    }

    public int getNoDireccion() {
        return noDireccion;
    }

    public void setNoDireccion(int noDireccion) {
        this.noDireccion = noDireccion;
    }

    public int getNoTelefo() {
        return noTelefo;
    }

    public void setNoTelefo(int noTelefo) {
        this.noTelefo = noTelefo;
    }

    public int getNoFecha() {
        return noFecha;
    }

    public void setNoFecha(int noFecha) {
        this.noFecha = noFecha;
    }

    public int getNoEmail() {
        return noEmail;
    }

    public void setNoEmail(int noEmail) {
        this.noEmail = noEmail;
    }

    public String getFotografia() {
        return fotografia;
    }

    public void setFotografia(String fotografia) {
        this.fotografia = fotografia;
    }

    public String getFavorito() {
        return favorito;
    }

    public void setFavorito(String favorito) {
        this.favorito = favorito;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    
    public BasicDBObject toJson()
    {
        BasicDBObject convierte = new BasicDBObject();
        
        convierte.append("identificador",this.getIdentificador());
        convierte.append("nombre", this.getNombre());
        convierte.append("tipoPersona",this.getTipoPersona());
        convierte.append("tipoContacto",this.getTipoContacto());
        convierte.append("fotografia",this.getFotografia());
        convierte.append("favorito",this.getFavorito());
        convierte.append("usuario",this.getUsuario());
        
        return convierte;
    }
}
