/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webservcicesmongo;

import com.estructuras.internas.*;
import com.estructuras.tipo.*;
import com.mongodb.BasicDBObject;

/**
 *
 * @author oscar89p
 */
public final class exportJson {
    
    public exportJson()
    {
        
    }
    
    public static BasicDBObject exportJson(tipoPersona Object)
    {
        BasicDBObject convierte = new BasicDBObject();
        convierte.append("_id", Object.id);
        convierte.append("tipo",Object.tipo);
        convierte.append("descripcion", Object.descripcion);
        
        return convierte;
    }
    
    public static BasicDBObject exportJsonUdp(tipoPersona Object)
    {
        BasicDBObject convierte = new BasicDBObject();
        convierte.append("tipo",Object.tipo);
        convierte.append("descripcion", Object.descripcion);
        
        return convierte;
    }
    
    public static BasicDBObject exportJson(tipoTelefono Object)
    {
        BasicDBObject convierte = new BasicDBObject();
        convierte.append("_id", Object.id);
        convierte.append("tipo",Object.tipo);
        convierte.append("descripcion", Object.descripcion);
        
        return convierte;
    }
    
    public static BasicDBObject exportJsonUdp(tipoTelefono Object)
    {
        BasicDBObject convierte = new BasicDBObject();
        convierte.append("tipo",Object.tipo);
        convierte.append("descripcion", Object.descripcion);
        
        return convierte;
    }
    
    public static BasicDBObject exportJson(tipoDireccion Object)
    {
        BasicDBObject convierte = new BasicDBObject();
        convierte.append("_id", Object.id);
        convierte.append("tipo",Object.tipo);
        convierte.append("descripcion", Object.descripcion);
        
        return convierte;
    }
    
    public static BasicDBObject exportJsonUdp(tipoDireccion Object)
    {
        BasicDBObject convierte = new BasicDBObject();
        convierte.append("tipo",Object.tipo);
        convierte.append("descripcion", Object.descripcion);
        
        return convierte;
    }
    
    public static BasicDBObject exportJson(tipoCategoriaFecha Object)
    {
        BasicDBObject convierte = new BasicDBObject();
        convierte.append("_id", Object.id);
        convierte.append("tipo",Object.tipo);
        convierte.append("descripcion", Object.descripcion);
        
        return convierte;
    }
    
    public static BasicDBObject exportJsonUdp(tipoCategoriaFecha Object)
    {
        BasicDBObject convierte = new BasicDBObject();
        convierte.append("tipo",Object.tipo);
        convierte.append("descripcion", Object.descripcion);
        
        return convierte;
    }
    
    public static BasicDBObject exportJson(tipoCategoriaContacto Object)
    {
        BasicDBObject convierte = new BasicDBObject();
        convierte.append("_id", Object.id);
        convierte.append("tipo",Object.tipo);
        convierte.append("descripcion", Object.descripcion);
        
        return convierte;
    }
    
    public static BasicDBObject exportJsonUdp(tipoCategoriaContacto Object)
    {
        BasicDBObject convierte = new BasicDBObject();
        convierte.append("tipo",Object.tipo);
        convierte.append("descripcion", Object.descripcion);
        
        return convierte;
    }
    
    public static BasicDBObject exportJson(tipoFecha Object)
    {
        BasicDBObject convierte = new BasicDBObject();
        convierte.append("_id", Object.id);
        convierte.append("tipo",Object.tipo);
        convierte.append("descripcion", Object.descripcion);
        convierte.append("categoria", Object.categoria);
        
        return convierte;
    }
    
    public static BasicDBObject exportJsonUdp(tipoFecha Object)
    {
        BasicDBObject convierte = new BasicDBObject();
        convierte.append("tipo",Object.tipo);
        convierte.append("descripcion", Object.descripcion);
        convierte.append("categoria", Object.categoria);
        
        return convierte;
    }
    
    public static BasicDBObject exportJson(tipoContacto Object)
    {
        BasicDBObject convierte = new BasicDBObject();
        convierte.append("_id", Object.id);
        convierte.append("tipo",Object.tipo);
        convierte.append("descripcion", Object.descripcion);
        convierte.append("categoria", Object.categoria);
        
        return convierte;
    }
    
    public static BasicDBObject exportJsonUdp(tipoContacto Object)
    {
        BasicDBObject convierte = new BasicDBObject();
        convierte.append("tipo",Object.tipo);
        convierte.append("descripcion", Object.descripcion);
        convierte.append("categoria", Object.categoria);
        
        return convierte;
    }
    
    public static BasicDBObject exportJson(entrada Object)
    {
        BasicDBObject convierte = new BasicDBObject();
        convierte.append("_id",Object.id);
        convierte.append("identificador",Object.identificador);
        convierte.append("nombre", Object.nombre);
        convierte.append("tipoPersona",Object.tipoPersona);
        convierte.append("tipoContacto",Object.tipoContacto);
        convierte.append("fotografia",Object.fotografia);
        convierte.append("favorito",Object.favorito);
        convierte.append("usuario",Object.usuario);
        
        return convierte;
    }
    
    public static BasicDBObject exportJsonUpd(entrada Object)
    {
        BasicDBObject convierte = new BasicDBObject();
        convierte.append("identificador",Object.identificador);
        convierte.append("nombre", Object.nombre);
        convierte.append("tipoPersona",Object.tipoPersona);
        convierte.append("tipoContacto",Object.tipoContacto);
        convierte.append("fotografia",Object.fotografia);
        convierte.append("favorito",Object.favorito);
        convierte.append("usuario",Object.usuario);
        
        return convierte;
    }
    
    public static BasicDBObject exportJson(usuario Object)
    {
        BasicDBObject convierte = new BasicDBObject();
        convierte.append("_id",Object.id);
        convierte.append("usuario",Object.usuario);
        convierte.append("nombre", Object.nombre);
        convierte.append("password", Object.password);
        
        return convierte;
    }
    
    public static BasicDBObject exportJsonUpd(usuario Object)
    {
        BasicDBObject convierte = new BasicDBObject();
        convierte.append("usuario",Object.usuario);
        convierte.append("nombre", Object.nombre);
        convierte.append("password", Object.password);
        
        return convierte;
    }
    
}
