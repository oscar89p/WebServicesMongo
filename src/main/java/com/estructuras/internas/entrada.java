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
public class entrada {
    
    public String id;
    public String identificador;
    public String nombre;
    public String tipoPersona;
    public String tipoContacto;
    public String fotografia;
    public String favorito;
    public String usuario;

    public entrada() {
        
        this.id = "";
        this.identificador = "";
        this.nombre = "";
        this.tipoPersona = "";
        this.tipoContacto = "";
        this.fotografia = "";
        this.favorito = "";
        this.usuario = "";
    }
    
    public estado validaEntrada()
    {
        estado retorno = new estado();
        retorno.codigo = "0000";
        retorno.descripcion = "Verificacion satisfactoria";
        retorno.tipo = "OK";
        
        
        if (this.identificador.isEmpty() || this.identificador.equals("?"))
        {
            retorno.codigo = "V001";
            retorno.descripcion = "Identificacion no valida, verifique";
            retorno.tipo = "ER";
            return retorno;
        }
        if (this.nombre.isEmpty() || this.nombre.equals("?"))
        {
            retorno.codigo = "V002";
            retorno.descripcion = "Nombre no valido, verifique";
            retorno.tipo = "ER";
            return retorno;
        }
        if (this.tipoPersona.isEmpty() || this.tipoPersona.equals("?"))
        {
            retorno.codigo = "V003";
            retorno.descripcion = "Tipo de persona no valida, verifique";
            retorno.tipo = "ER";
        }
        if (this.tipoContacto.isEmpty() || this.tipoContacto.equals("?"))
        {
            retorno.codigo = "V004";
            retorno.descripcion = "Tipo de conctacto no valida, verifique";
            retorno.tipo = "ER";
        }
        if (this.usuario.isEmpty() || this.usuario.equals("?"))
        {
            retorno.codigo = "V005";
            retorno.descripcion = "Usuario no valido, verifique";
            retorno.tipo = "ER";
        }   

        return retorno;
    }
    
}
