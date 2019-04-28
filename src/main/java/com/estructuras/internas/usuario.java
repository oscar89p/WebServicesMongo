/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.estructuras.internas;

import com.estructuras.respuesta.estado;
import com.webservcicesmongo.cifrar;

/**
 *
 * @author oscar89p
 */
public class usuario {
 
    public String id;
    public String usuario;
    public String nombre;
    public String password;

    public usuario()
    {
        this.id = "";
        this.usuario = "";
        this.nombre = "";
        this.password = "";
    }
    
    public estado validaUsuario()
    {        
        estado retorno = new estado();
        retorno.codigo = "0000";
        retorno.descripcion = "Verificacion satisfactoria";
        retorno.tipo = "OK";
        
        
        if (this.nombre.isEmpty() || this.nombre.equals("?"))
        {
            retorno.codigo = "V001";
            retorno.descripcion = "Nombre no valido, verifique";
            retorno.tipo = "ER";
            return retorno;
        }
        
        if (this.usuario.isEmpty() || this.usuario.equals("?"))
        {
            retorno.codigo = "V002";
            retorno.descripcion = "Usuario no valido, verifique";
            retorno.tipo = "ER";
            return retorno;
        }
        
        return retorno;
    } 
}
