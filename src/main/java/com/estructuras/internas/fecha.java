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
 
    private String entrada;
    private String fecha;
    private String descripcion;
    private String categoria;
    private String tipo;
    
    public fecha()
    {
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

    public String getEntrada() {
        return entrada;
    }

    public void setEntrada(String entrada) {
        this.entrada = entrada;
    }
    
    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
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
        convierte.append("fecha", this.getFecha());
        convierte.append("descripcion", this.getDescripcion());
        convierte.append("categoria", this.getCategoria());
        convierte.append("tipo", this.getTipo());
        
        return convierte;
    }
}
