/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.estructuras.internas;

import com.estructuras.respuesta.estado;

/**
 *
 * @author oscar89p
 */
public class direccion {
    
    public String entrada;
    public String id;
    public String direccion;
    public String tipo;
    
    public direccion()
    {
        this.entrada = "";
        this.id = "";
        this.direccion = "";
        this.tipo = "";
    }
    
    public estado validaDireccion()
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
        if (this.direccion.isEmpty() || this.direccion.equals("?"))
        {
            retorno.codigo = "V002";
            retorno.descripcion = "Direccion no valido, verifique";
            retorno.tipo = "ER";
            return retorno;
        }
        if (this.tipo.isEmpty() || this.tipo.equals("?")) 
        {
            retorno.codigo = "V003";
            retorno.descripcion = "Tipo de direccion no valida, verifique";
            retorno.tipo = "ER";
        }
       
        return retorno;
    }
}
