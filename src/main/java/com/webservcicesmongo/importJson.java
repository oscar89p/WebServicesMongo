/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webservcicesmongo;

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
    

    
}
