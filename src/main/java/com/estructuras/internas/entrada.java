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
    
    public String id;
    public String identificador;
    public String nombre;
    public String tipoPersona;
    public String tipoContacto;
    public String fotografia;
    public String favorito;
    public String usuarioId;

    public entrada() {
        
        this.id = "";
        this.identificador = "";
        this.nombre = "";
        this.tipoPersona = "";
        this.tipoContacto = "";
        this.fotografia = "";
        this.favorito = "";
        this.usuarioId = "";
    }
    
}
