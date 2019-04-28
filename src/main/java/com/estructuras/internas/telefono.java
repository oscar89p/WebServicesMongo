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
public class telefono {
    
    public String id;
    public String entrada;
    public String telefono;
    public String tipo;
    
    public telefono()
    {
        this.id = "";
        this.entrada = "";
        this.telefono = "";
        this.tipo = "";
    }
    
    public estado validaEmail()
    {        
        estado retorno = new estado();
        retorno.codigo = "0000";
        retorno.descripcion = "Verificacion satisfactoria";
        retorno.tipo = "OK";
        
        
        if (this.entrada.isEmpty() || this.entrada.equals("?"))
        {
            retorno.codigo = "V001";
            retorno.descripcion = "Entrada no valida, verifique";
            retorno.tipo = "ER";
            return retorno;
        }
        
        if (this.telefono.isEmpty() || this.telefono.equals("?"))
        {
            retorno.codigo = "V002";
            retorno.descripcion = "Telefono no valido, verifique";
            retorno.tipo = "ER";
            return retorno;
        }
        
        if (this.tipo.isEmpty() || this.tipo.equals("?"))
        {
            retorno.codigo = "V003";
            retorno.descripcion = "Tipo no valido, verifique";
            retorno.tipo = "ER";
            return retorno;
        }
        
        return retorno;
    }
    
}
