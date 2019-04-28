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
public class email {
    
    public String entrada;
    public String id;
    public String email;
    public String favorito;
    
    public email()
    {
        this.entrada = "";
        this.id = "";
        this.email = "";
        this.favorito = "";
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
        if (this.email.isEmpty() || this.email.equals("?"))
        {
            retorno.codigo = "V002";
            retorno.descripcion = "Direccion no valido, verifique";
            retorno.tipo = "ER";
            return retorno;
        }
       
        return retorno;
    }
}
