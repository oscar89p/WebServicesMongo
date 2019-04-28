/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.estructuras.internas;

import com.estructuras.respuesta.estado;
import com.mongodb.BasicDBObject;

/**
 *
 * @author oscar89p
 */
public class fecha {
 
    public String id;
    public String entrada;
    public String fecha;
    public String descripcion;
    public String categoria;
    public String tipo;
    
    public fecha()
    {
        this.id = "";
        this.entrada = "";
        this.fecha = "";
        this.descripcion = "";
        this.categoria = "";
        this.tipo = "";
    }
    
    public estado validaFecha()
    {        
        estado retorno = new estado();
        retorno.codigo = "0000";
        retorno.descripcion = "Verificacion satisfactoria";
        retorno.tipo = "OK";
        
        
        if (this.entrada.isEmpty())
        {
            retorno.codigo = "V001";
            retorno.descripcion = "Entrada no valida, verifique";
            retorno.tipo = "ER";
            return retorno;
        }
        if (this.fecha.isEmpty())
        {
            retorno.codigo = "V002";
            retorno.descripcion = "Fecha no valido, verifique";
            retorno.tipo = "ER";
            return retorno;
        }
        if (this.descripcion.isEmpty())
        {
            retorno.codigo = "V003";
            retorno.descripcion = "Descripcion no valida, verifique";
            retorno.tipo = "ER";
            return retorno;
        }
        if (this.categoria.isEmpty())
        {
            retorno.codigo = "V004";
            retorno.descripcion = "Categoria no valida, verifique";
            retorno.tipo = "ER";
            return retorno;
        }
        if (this.tipo.isEmpty())
        {
            retorno.codigo = "V005";
            retorno.descripcion = "Tipo no valida, verifique";
            retorno.tipo = "ER";
            return retorno;
        }
        return retorno;
    }
    
}
