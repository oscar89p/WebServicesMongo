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
public final class importJson {
    
    public importJson()
    {
        
    }
    
    public static tipoPersona importJsonTipoPersona(BasicDBObject Json)
    {
        tipoPersona retorno = new tipoPersona();
        retorno.id = Json.getString("_id");
        retorno.tipo = Json.getString("tipo");
        retorno.descripcion = Json.getString("descripcion");
        
        return retorno;
    }
    
    public static tipoTelefono importJsonTipoTelefono(BasicDBObject Json)
    {
        tipoTelefono retorno = new tipoTelefono();
        retorno.id = Json.getString("_id");
        retorno.tipo = Json.getString("tipo");
        retorno.descripcion = Json.getString("descripcion");
        
        return retorno;
    }
    
    public static tipoDireccion importJsonTipoDireccion(BasicDBObject Json)
    {
        tipoDireccion retorno = new tipoDireccion();
        retorno.id = Json.getString("_id");
        retorno.tipo = Json.getString("tipo");
        retorno.descripcion = Json.getString("descripcion");
        
        return retorno;
    }
    
    public static tipoCategoriaFecha importJsonTipoCategoriaFecha(BasicDBObject Json)
    {
        tipoCategoriaFecha retorno = new tipoCategoriaFecha();
        retorno.id = Json.getString("_id");
        retorno.tipo = Json.getString("tipo");
        retorno.descripcion = Json.getString("descripcion");
        
        return retorno;
    }
    
    public static tipoCategoriaContacto importJsonTipoCategoriaContacto(BasicDBObject Json)
    {
        tipoCategoriaContacto retorno = new tipoCategoriaContacto();
        retorno.id = Json.getString("_id");
        retorno.tipo = Json.getString("tipo");
        retorno.descripcion = Json.getString("descripcion");
        
        return retorno;
    }
    
    public static tipoFecha importJsonTipoFecha(BasicDBObject Json)
    {
        tipoFecha retorno = new tipoFecha();
        retorno.id = Json.getString("_id");
        retorno.tipo = Json.getString("tipo");
        retorno.descripcion = Json.getString("descripcion");
        retorno.categoria = Json.getString("categoria");
        
        return retorno;
    }
    
    public static tipoContacto importJsonTipoContacto(BasicDBObject Json)
    {
        tipoContacto retorno = new tipoContacto();
        retorno.id = Json.getString("_id");
        retorno.tipo = Json.getString("tipo");
        retorno.descripcion = Json.getString("descripcion");
        retorno.categoria = Json.getString("categoria");
        
        return retorno;
    }
    
    public static entrada importJsonEntrada(BasicDBObject Json)
    {
        entrada retorno = new entrada();
        retorno.id = Json.getString("_id");
        retorno.identificador = Json.getString("identificador");
        retorno.nombre = Json.getString("nombre");
        retorno.tipoPersona = Json.getString("tipoPersona");
        retorno.tipoContacto = Json.getString("tipoContacto");
        retorno.fotografia = Json.getString("fotografia");
        retorno.favorito = Json.getString("favorito");
        retorno.usuario = Json.getString("usuario");
        
        return retorno;
    }
    
    public static usuario importJsonUsuario(BasicDBObject Json)
    {
        usuario retorno = new usuario();
        retorno.id = Json.getString("_id");
        retorno.nombre = Json.getString("nombre");
        retorno.password = Json.getString("password");
        retorno.usuario = Json.getString("usuario");
        
        return retorno;
    }
    
    public static direccion importJsonDireccion(BasicDBObject Json)
    {
        direccion retorno = new direccion();
        retorno.entrada = Json.getString("entrada");
        retorno.id = Json.getString("_id");
        retorno.direccion = Json.getString("direccion");
        retorno.tipo = Json.getString("tipo");

        return retorno;
    }
    
    public static telefono importJsonTelefono(BasicDBObject Json)
    {
        telefono retorno = new telefono();
        retorno.entrada = Json.getString("entrada");
        retorno.id = Json.getString("_id");
        retorno.telefono = Json.getString("telefono");
        retorno.tipo = Json.getString("tipo");

        return retorno;
    }
    
    public static fecha importJsonFecha(BasicDBObject Json)
    {
        fecha retorno = new fecha();
        retorno.id = Json.getString("_id");
        retorno.entrada = Json.getString("entrada");
        retorno.fecha = Json.getString("fecha");
        retorno.descripcion = Json.getString("descripcion");
        retorno.categoria = Json.getString("categoria");
        retorno.tipo = Json.getString("tipo");

        return retorno;
    }
    
}
