/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webservcicesmongo;

import com.estructuras.peticion.*;
import com.estructuras.tipo.*;
import com.estructuras.internas.*;
import com.estructuras.respuesta.*;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import java.util.ArrayList;
import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import org.bson.types.ObjectId;

/**
 *
 * @author oscar89p
 */
@WebService(serviceName = "wsMongo")
public class wsMongo {


    
    public String busqueda(@WebParam(name = "Servidor") String servidor, @WebParam(name = "Puerto") String puerto, @WebParam(name = "baseDatos") String basedatos, @WebParam(name = "tabla") String tabla, @WebParam(name = "campo") String campo, @WebParam(name = "valor") String valor)
    {
        String retorno = "";
        try
        {
            MongoClient mongo = new MongoClient(servidor , Integer.parseInt(puerto));
            DB db = mongo.getDB(basedatos);
            
            DBCollection table = db.getCollection(tabla);

            BasicDBObject searchQuery = new BasicDBObject();
            searchQuery.put(campo,valor);
            
            DBCursor cursor = table.find(searchQuery);

            while (cursor.hasNext()) {
                   retorno += cursor.getCursorId() +  " + " ;
                   retorno += cursor.toArray() + " + " ;
                   retorno += cursor.curr();
                   cursor.next();
            }
            mongo.close();
        }
        catch(Exception ex)
        {

        }
        return retorno;
        
    }
    
    @WebMethod(operationName = "tipoPersonaCRUD")
    public respuestaTipoPersona tipoPersonaCRUD(@WebParam(name = "Servidor") servidor servidor, @WebParam(name = "Accion") String accion, @WebParam(name = "TipoPersona") tipoPersona tipoPersona)
    {
        respuestaTipoPersona retorno = new respuestaTipoPersona();
        
        try
        {
            MongoClient mongo = new MongoClient(servidor.servidor , Integer.parseInt(servidor.puerto));
            DB db = mongo.getDB(servidor.basedatos);
            
            DBCollection table = db.getCollection("tipoPersona");
            BasicDBObject searchQuery = new BasicDBObject();

            boolean qry = false;
            tipoPersona item = null;

            switch(accion)
            {
                case "C":
                            if(!tipoPersona.tipo.isEmpty() && !tipoPersona.tipo.equals("?"))
                            {
                                int count1 = 0, count2 = 0;
                                count1 = (int) table.getCount();
 
                                searchQuery.put("tipo",tipoPersona.tipo);

                                DBCursor cursor = table.find(searchQuery);
                                
                                if (cursor.count()==0)
                                {
                                    
                                    cursor.close();
                                    table.insert(exportJson.exportJsonUdp(tipoPersona));
                                    count2 = (int) table.getCount();
                                    if(count2 > count1)
                                    {
                                        retorno.estado.codigo = "0000";
                                        retorno.estado.descripcion = "Creacion satisfactoria" ;
                                        retorno.estado.detalle = exportJson.exportJson(tipoPersona).toJson();
                                        retorno.item = new ArrayList<tipoPersona>();
                                        retorno.item.add(tipoPersona);
                                        retorno.estado.tipo = "OK";
                                    }
                                    else
                                    {
                                        retorno.estado.codigo = "0001";
                                        retorno.estado.descripcion = "Error al insertar, favor verifique";
                                        retorno.estado.tipo = "ER";
                                    }

                                }
                                else
                                {
                                    retorno.estado.codigo = "0002";
                                    retorno.estado.descripcion = "Registro previamente insertado, verifique";
                                    retorno.estado.tipo = "ER";
                                }
                            }
                            else
                            {
                                retorno.estado.codigo = "0003";
                                retorno.estado.descripcion = "Atributo {tipo} no valido, verifique";
                                retorno.estado.tipo = "ER";
                            }
                            break;
                case "R":

                            String filtro = "";
                            if(!tipoPersona.id.isEmpty() && !tipoPersona.id.equals("?"))
                            {
                                searchQuery.put("_id",new ObjectId(tipoPersona.id));
                                filtro = "id";
                                qry = true;
                            }
                            else
                            {
                                if(!tipoPersona.tipo.isEmpty() && !tipoPersona.tipo.equals("?"))
                                {   
                                    searchQuery.put("tipo",tipoPersona.tipo);
                                    filtro = "tipo";
                                    qry = true;
                                }
                                else
                                {
                                    if(!tipoPersona.descripcion.isEmpty() && !tipoPersona.descripcion.equals("?"))
                                    {   
                                        searchQuery.put("descripcion",tipoPersona.descripcion);
                                        filtro = "descripcion";
                                        qry = true;
                                    }
                                    else
                                    {
                                        qry = true;
                                    }
                                }
                            }
                            if(qry)
                            {
                                DBCursor cursor = null;
                                cursor = table.find(searchQuery);

                                if (cursor.count() > 0)
                                {   
                                    retorno.estado.codigo = "0000";
                                    retorno.estado.descripcion = "Consulta satisfactoria: " + filtro;
                                    retorno.estado.detalle = Integer.toString(cursor.count());
                                    retorno.estado.tipo = "OK";
                                    
                                    retorno.item = new ArrayList<tipoPersona>();
                                    
                                    while (cursor.hasNext()) 
                                    {
                                        retorno.item.add(importJson.importJsonTipoPersona((BasicDBObject) cursor.next()));
                                    }
                                }
                                else
                                {
                                    retorno.estado.codigo = "0005";
                                    retorno.estado.descripcion = "No se encontraron documentos con el filtro solicitado {\"" + filtro + "\"}, verifique";
                                    retorno.estado.detalle = exportJson.exportJson(tipoPersona).toJson();
                                    retorno.estado.tipo = "ER";
                                }
                                cursor.close();
                            }
                            else
                            {
                                retorno.estado.codigo = "0004";
                                retorno.estado.descripcion = "Atributo {tipo} no valido, verifique";
                                retorno.estado.tipo = "ER";
                            }
                            
                            break;
                case "U":
                            item = null;
                            if(!tipoPersona.id.isEmpty())
                            {
                                searchQuery.put("_id",new ObjectId(tipoPersona.id));
                                filtro = "id";
                                
                                DBCursor cursor = table.find(searchQuery);

                                if (cursor.count() ==  1)
                                {
                                    qry = true;
                                    while (cursor.hasNext()) 
                                    {
                                        item = importJson.importJsonTipoPersona((BasicDBObject) cursor.next());
                                    } 
                                }
                                cursor.close();
                            }
                            if (qry)
                            {
                                BasicDBObject updateObj = new BasicDBObject();
                                updateObj.put("$set", exportJson.exportJsonUdp(tipoPersona));
                                
                                System.out.println(searchQuery.toJson() + " - " + updateObj.toJson());
                                
                                table.update(searchQuery, updateObj);
                                
                                retorno.estado.codigo = "0000";
                                retorno.estado.descripcion = "Actualizacion satisfactoria, Id: " + tipoPersona.id ;
                                retorno.estado.detalle = searchQuery.toJson() + " - " + updateObj.toJson();
                                retorno.estado.tipo = "OK";
                                
                                retorno.item = new ArrayList<tipoPersona>();
                                retorno.item.add(item);
                                retorno.item.add(tipoPersona);
                                
                            }
                            else
                            {
                                retorno.estado.codigo = "0006";
                                retorno.estado.descripcion = "Atributo {\"_id\":\"" + tipoPersona.id + "\"} no encontrado, verifique";
                                retorno.estado.tipo = "ER";
                            }
                            break;
                case "D":
                            item = null;
                            if(!tipoPersona.id.isEmpty() && !tipoPersona.id.equals("?"))
                            {
                                searchQuery.put("_id",new ObjectId(tipoPersona.id));
                                filtro = "id";
                                
                                DBCursor cursor = table.find(searchQuery);
                                
                                if (cursor.count() == 1)
                                {
                                    qry = true;
                                    while (cursor.hasNext()) 
                                    {
                                        item = importJson.importJsonTipoPersona((BasicDBObject) cursor.next());
                                    } 
                                }
                                cursor.close();
                            }
                            if (qry && item != null)
                            {   
                                table.remove(searchQuery);
                                
                                retorno.estado.codigo = "0000";
                                retorno.estado.descripcion = "Eliminacion satisfactoria, Id: " + tipoPersona.id ;
                                retorno.estado.tipo = "OK";
                                
                                retorno.item = new ArrayList<tipoPersona>();
                                retorno.item.add(item);
                                
                            }
                            else
                            {
                                retorno.estado.codigo = "0007";
                                retorno.estado.descripcion = "Atributo {\"_id\":\"" + tipoPersona.id + "\"} no encontrado, verifique";
                                retorno.estado.tipo = "ER";
                            }
                            break;
                default:
                            retorno.estado.codigo = "DF01";
                            retorno.estado.descripcion = "Opcion no valida";
                            retorno.estado.tipo = "ER";
                            break;
            }
            mongo.close();
        }
        catch(Exception ex)
        {

        }
        return retorno;
        
    }

    @WebMethod(operationName = "tipoTelefonoCRUD")
    public respuestaTipoTelefono tipoTelefonoCRUD(@WebParam(name = "Servidor") servidor servidor, @WebParam(name = "Accion") String accion, @WebParam(name = "TipoTelefono") tipoTelefono tipoTelefono)
    {
        respuestaTipoTelefono retorno = new respuestaTipoTelefono();
        
        try
        {
            MongoClient mongo = new MongoClient(servidor.servidor , Integer.parseInt(servidor.puerto));
            DB db = mongo.getDB(servidor.basedatos);
            
            DBCollection table = db.getCollection("tipoTelefono");
            BasicDBObject searchQuery = new BasicDBObject();

            boolean qry = false;
            tipoTelefono item = null;

            switch(accion)
            {
                case "C":
                            if(!tipoTelefono.tipo.isEmpty() && !tipoTelefono.tipo.equals("?"))
                            {
                                int count1 = 0, count2 = 0;
                                count1 = (int) table.getCount();
 
                                searchQuery.put("tipo",tipoTelefono.tipo);

                                DBCursor cursor = table.find(searchQuery);
                                
                                if (cursor.count()==0)
                                {
                                    
                                    cursor.close();
                                    table.insert(exportJson.exportJsonUdp(tipoTelefono));
                                    count2 = (int) table.getCount();
                                    if(count2 > count1)
                                    {
                                        retorno.estado.codigo = "0000";
                                        retorno.estado.descripcion = "Creacion satisfactoria" ;
                                        retorno.estado.detalle = exportJson.exportJson(tipoTelefono).toJson();
                                        retorno.item = new ArrayList<tipoTelefono>();
                                        retorno.item.add(tipoTelefono);
                                        retorno.estado.tipo = "OK";
                                    }
                                    else
                                    {
                                        retorno.estado.codigo = "0001";
                                        retorno.estado.descripcion = "Error al insertar, favor verifique";
                                        retorno.estado.tipo = "ER";
                                    }

                                }
                                else
                                {
                                    retorno.estado.codigo = "0002";
                                    retorno.estado.descripcion = "Registro previamente insertado, verifique";
                                    retorno.estado.tipo = "ER";
                                }
                            }
                            else
                            {
                                retorno.estado.codigo = "0003";
                                retorno.estado.descripcion = "Atributo {tipo} no valido, verifique";
                                retorno.estado.tipo = "ER";
                            }
                            break;
                case "R":

                            String filtro = "";
                            if(!tipoTelefono.id.isEmpty() && !tipoTelefono.id.equals("?"))
                            {
                                searchQuery.put("_id",new ObjectId(tipoTelefono.id));
                                filtro = "id";
                                qry = true;
                            }
                            else
                            {
                                if(!tipoTelefono.tipo.isEmpty() && !tipoTelefono.tipo.equals("?"))
                                {   
                                    searchQuery.put("tipo",tipoTelefono.tipo);
                                    filtro = "tipo";
                                    qry = true;
                                }
                                else
                                {
                                    if(!tipoTelefono.descripcion.isEmpty() && !tipoTelefono.descripcion.equals("?"))
                                    {   
                                        searchQuery.put("descripcion",tipoTelefono.descripcion);
                                        filtro = "descripcion";
                                        qry = true;
                                    }
                                    else
                                    {
                                        qry = true;
                                    }
                                }
                            }
                            if(qry)
                            {
                                DBCursor cursor = null;
                                cursor = table.find(searchQuery);

                                if (cursor.count() > 0)
                                {   
                                    retorno.estado.codigo = "0000";
                                    retorno.estado.descripcion = "Consulta satisfactoria: " + filtro;
                                    retorno.estado.detalle = Integer.toString(cursor.count());
                                    retorno.estado.tipo = "OK";
                                    
                                    retorno.item = new ArrayList<tipoTelefono>();
                                    
                                    while (cursor.hasNext()) 
                                    {
                                        retorno.item.add(importJson.importJsonTipoTelefono((BasicDBObject) cursor.next()));
                                    }
                                }
                                else
                                {
                                    retorno.estado.codigo = "0005";
                                    retorno.estado.descripcion = "No se encontraron documentos con el filtro solicitado {\"" + filtro + "\"}, verifique";
                                    retorno.estado.detalle = exportJson.exportJson(tipoTelefono).toJson();
                                    retorno.estado.tipo = "ER";
                                }
                                cursor.close();
                            }
                            else
                            {
                                retorno.estado.codigo = "0004";
                                retorno.estado.descripcion = "Atributo {tipo} no valido, verifique";
                                retorno.estado.tipo = "ER";
                            }
                            
                            break;
                case "U":
                            item = null;
                            if(!tipoTelefono.id.isEmpty() && !tipoTelefono.id.equals("?"))
                            {
                                searchQuery.put("_id",new ObjectId(tipoTelefono.id));
                                filtro = "id";
                                
                                DBCursor cursor = table.find(searchQuery);

                                if (cursor.count() ==  1)
                                {
                                    qry = true;
                                    while (cursor.hasNext()) 
                                    {
                                        item = importJson.importJsonTipoTelefono((BasicDBObject) cursor.next());
                                    } 
                                }
                                cursor.close();
                            }
                            if (qry)
                            {
                                BasicDBObject updateObj = new BasicDBObject();
                                updateObj.put("$set", exportJson.exportJsonUdp(tipoTelefono));
                                
                                System.out.println(searchQuery.toJson() + " - " + updateObj.toJson());
                                
                                table.update(searchQuery, updateObj);
                                
                                retorno.estado.codigo = "0000";
                                retorno.estado.descripcion = "Actualizacion satisfactoria, Id: " + tipoTelefono.id ;
                                retorno.estado.detalle = searchQuery.toJson() + " - " + updateObj.toJson();
                                retorno.estado.tipo = "OK";
                                
                                retorno.item = new ArrayList<tipoTelefono>();
                                retorno.item.add(item);
                                retorno.item.add(tipoTelefono);
                                
                            }
                            else
                            {
                                retorno.estado.codigo = "0006";
                                retorno.estado.descripcion = "Atributo {\"_id\":\"" + tipoTelefono.id + "\"} no encontrado, verifique";
                                retorno.estado.tipo = "ER";
                            }
                            break;
                case "D":
                            item = null;
                            if(!tipoTelefono.id.isEmpty())
                            {
                                searchQuery.put("_id",new ObjectId(tipoTelefono.id));
                                filtro = "id";
                                
                                DBCursor cursor = table.find(searchQuery);
                                
                                if (cursor.count() == 1)
                                {
                                    qry = true;
                                    while (cursor.hasNext()) 
                                    {
                                        item = importJson.importJsonTipoTelefono((BasicDBObject) cursor.next());
                                    } 
                                }
                                cursor.close();
                            }
                            if (qry && item != null)
                            {   
                                table.remove(searchQuery);
                                
                                retorno.estado.codigo = "0000";
                                retorno.estado.descripcion = "Eliminacion satisfactoria, Id: " + tipoTelefono.id ;
                                retorno.estado.tipo = "OK";
                                
                                retorno.item = new ArrayList<tipoTelefono>();
                                retorno.item.add(item);
                                
                            }
                            else
                            {
                                retorno.estado.codigo = "0007";
                                retorno.estado.descripcion = "Atributo {\"_id\":\"" + tipoTelefono.id + "\"} no encontrado, verifique";
                                retorno.estado.tipo = "ER";
                            }
                            break;
                default:
                            retorno.estado.codigo = "DF01";
                            retorno.estado.descripcion = "Opcion no valida";
                            retorno.estado.tipo = "ER";
                            break;
            }
            mongo.close();
        }
        catch(Exception ex)
        {

        }
        return retorno;
        
    }
    
    @WebMethod(operationName = "tipoDireccionCRUD")
    public respuestaTipoDireccion tipoDireccionCRUD(@WebParam(name = "Servidor") servidor servidor, @WebParam(name = "Accion") String accion, @WebParam(name = "TipoDireccion") tipoDireccion tipoDireccion)
    {
        respuestaTipoDireccion retorno = new respuestaTipoDireccion();
        
        try
        {
            MongoClient mongo = new MongoClient(servidor.servidor , Integer.parseInt(servidor.puerto));
            DB db = mongo.getDB(servidor.basedatos);
            
            DBCollection table = db.getCollection("tipoDireccion");
            BasicDBObject searchQuery = new BasicDBObject();

            boolean qry = false;
            tipoDireccion item = null;

            switch(accion)
            {
                case "C":
                            if(!tipoDireccion.tipo.isEmpty() && !tipoDireccion.tipo.equals("?"))
                            {
                                int count1 = 0, count2 = 0;
                                count1 = (int) table.getCount();
 
                                searchQuery.put("tipo",tipoDireccion.tipo);

                                DBCursor cursor = table.find(searchQuery);
                                
                                if (cursor.count()==0)
                                {
                                    
                                    cursor.close();
                                    table.insert(exportJson.exportJsonUdp(tipoDireccion));
                                    count2 = (int) table.getCount();
                                    if(count2 > count1)
                                    {
                                        retorno.estado.codigo = "0000";
                                        retorno.estado.descripcion = "Creacion satisfactoria" ;
                                        retorno.estado.detalle = exportJson.exportJson(tipoDireccion).toJson();
                                        retorno.item = new ArrayList<tipoDireccion>();
                                        retorno.item.add(tipoDireccion);
                                        retorno.estado.tipo = "OK";
                                    }
                                    else
                                    {
                                        retorno.estado.codigo = "0001";
                                        retorno.estado.descripcion = "Error al insertar, favor verifique";
                                        retorno.estado.tipo = "ER";
                                    }

                                }
                                else
                                {
                                    retorno.estado.codigo = "0002";
                                    retorno.estado.descripcion = "Registro previamente insertado, verifique";
                                    retorno.estado.tipo = "ER";
                                }
                            }
                            else
                            {
                                retorno.estado.codigo = "0003";
                                retorno.estado.descripcion = "Atributo {tipo} no valido, verifique";
                                retorno.estado.tipo = "ER";
                            }
                            break;
                case "R":

                            String filtro = "";
                            if(!tipoDireccion.id.isEmpty() && !tipoDireccion.id.equals("?"))
                            {
                                searchQuery.put("_id",new ObjectId(tipoDireccion.id));
                                filtro = "id";
                                qry = true;
                            }
                            else
                            {
                                if(!tipoDireccion.tipo.isEmpty() && !tipoDireccion.tipo.equals("?"))
                                {   
                                    searchQuery.put("tipo",tipoDireccion.tipo);
                                    filtro = "tipo";
                                    qry = true;
                                }
                                else
                                {
                                    if(!tipoDireccion.descripcion.isEmpty() && !tipoDireccion.descripcion.equals("?"))
                                    {   
                                        searchQuery.put("descripcion",tipoDireccion.descripcion);
                                        filtro = "descripcion";
                                        qry = true;
                                    }
                                    else
                                    {
                                        qry = true;
                                    }
                                }
                            }
                            if(qry)
                            {
                                DBCursor cursor = null;
                                cursor = table.find(searchQuery);

                                if (cursor.count() > 0)
                                {   
                                    retorno.estado.codigo = "0000";
                                    retorno.estado.descripcion = "Consulta satisfactoria: " + filtro;
                                    retorno.estado.detalle = Integer.toString(cursor.count());
                                    retorno.estado.tipo = "OK";
                                    
                                    retorno.item = new ArrayList<tipoDireccion>();
                                    
                                    while (cursor.hasNext()) 
                                    {
                                        retorno.item.add(importJson.importJsonTipoDireccion((BasicDBObject) cursor.next()));
                                    }
                                }
                                else
                                {
                                    retorno.estado.codigo = "0005";
                                    retorno.estado.descripcion = "No se encontraron documentos con el filtro solicitado {\"" + filtro + "\"}, verifique";
                                    retorno.estado.detalle = exportJson.exportJson(tipoDireccion).toJson();
                                    retorno.estado.tipo = "ER";
                                }
                                cursor.close();
                            }
                            else
                            {
                                retorno.estado.codigo = "0004";
                                retorno.estado.descripcion = "Atributo {tipo} no valido, verifique";
                                retorno.estado.tipo = "ER";
                            }
                            
                            break;
                case "U":
                            item = null;
                            if(!tipoDireccion.id.isEmpty() && !tipoDireccion.id.equals("?"))
                            {
                                searchQuery.put("_id",new ObjectId(tipoDireccion.id));
                                filtro = "id";
                                
                                DBCursor cursor = table.find(searchQuery);

                                if (cursor.count() ==  1)
                                {
                                    qry = true;
                                    while (cursor.hasNext()) 
                                    {
                                        item = importJson.importJsonTipoDireccion((BasicDBObject) cursor.next());
                                    } 
                                }
                                cursor.close();
                            }
                            if (qry)
                            {
                                BasicDBObject updateObj = new BasicDBObject();
                                updateObj.put("$set", exportJson.exportJsonUdp(tipoDireccion));
                                
                                System.out.println(searchQuery.toJson() + " - " + updateObj.toJson());
                                
                                table.update(searchQuery, updateObj);
                                
                                retorno.estado.codigo = "0000";
                                retorno.estado.descripcion = "Actualizacion satisfactoria, Id: " + tipoDireccion.id ;
                                retorno.estado.detalle = searchQuery.toJson() + " - " + updateObj.toJson();
                                retorno.estado.tipo = "OK";
                                
                                retorno.item = new ArrayList<tipoDireccion>();
                                retorno.item.add(item);
                                retorno.item.add(tipoDireccion);
                                
                            }
                            else
                            {
                                retorno.estado.codigo = "0006";
                                retorno.estado.descripcion = "Atributo {\"_id\":\"" + tipoDireccion.id + "\"} no encontrado, verifique";
                                retorno.estado.tipo = "ER";
                            }
                            break;
                case "D":
                            item = null;
                            if(!tipoDireccion.id.isEmpty())
                            {
                                searchQuery.put("_id",new ObjectId(tipoDireccion.id));
                                filtro = "id";
                                
                                DBCursor cursor = table.find(searchQuery);
                                
                                if (cursor.count() == 1)
                                {
                                    qry = true;
                                    while (cursor.hasNext()) 
                                    {
                                        item = importJson.importJsonTipoDireccion((BasicDBObject) cursor.next());
                                    } 
                                }
                                cursor.close();
                            }
                            if (qry && item != null)
                            {   
                                table.remove(searchQuery);
                                
                                retorno.estado.codigo = "0000";
                                retorno.estado.descripcion = "Eliminacion satisfactoria, Id: " + tipoDireccion.id ;
                                retorno.estado.tipo = "OK";
                                
                                retorno.item = new ArrayList<tipoDireccion>();
                                retorno.item.add(item);
                                
                            }
                            else
                            {
                                retorno.estado.codigo = "0007";
                                retorno.estado.descripcion = "Atributo {\"_id\":\"" + tipoDireccion.id + "\"} no encontrado, verifique";
                                retorno.estado.tipo = "ER";
                            }
                            break;
                default:
                            retorno.estado.codigo = "DF01";
                            retorno.estado.descripcion = "Opcion no valida";
                            retorno.estado.tipo = "ER";
                            break;
            }
            mongo.close();
        }
        catch(Exception ex)
        {

        }
        return retorno;
        
    }

    @WebMethod(operationName = "tipoCategoriaFechaCRUD")
    public respuestaTipoCategoriaFecha tipoCategoriaFechaCRUD(@WebParam(name = "Servidor") servidor servidor, @WebParam(name = "Accion") String accion, @WebParam(name = "TipoCategoriaFecha") tipoCategoriaFecha tipoCategoriaFecha)
    {
        respuestaTipoCategoriaFecha retorno = new respuestaTipoCategoriaFecha();
        
        try
        {
            MongoClient mongo = new MongoClient(servidor.servidor , Integer.parseInt(servidor.puerto));
            DB db = mongo.getDB(servidor.basedatos);
            
            DBCollection table = db.getCollection("tipoCategoriaFecha");
            BasicDBObject searchQuery = new BasicDBObject();

            boolean qry = false;
            tipoCategoriaFecha item = null;

            switch(accion)
            {
                case "C":
                            if(!tipoCategoriaFecha.tipo.isEmpty() && !tipoCategoriaFecha.tipo.equals("?"))
                            {
                                int count1 = 0, count2 = 0;
                                count1 = (int) table.getCount();
 
                                searchQuery.put("tipo",tipoCategoriaFecha.tipo);

                                DBCursor cursor = table.find(searchQuery);
                                
                                if (cursor.count()==0)
                                {
                                    
                                    cursor.close();
                                    table.insert(exportJson.exportJsonUdp(tipoCategoriaFecha));
                                    count2 = (int) table.getCount();
                                    if(count2 > count1)
                                    {
                                        retorno.estado.codigo = "0000";
                                        retorno.estado.descripcion = "Creacion satisfactoria" ;
                                        retorno.estado.detalle = exportJson.exportJson(tipoCategoriaFecha).toJson();
                                        retorno.item = new ArrayList<tipoCategoriaFecha>();
                                        retorno.item.add(tipoCategoriaFecha);
                                        retorno.estado.tipo = "OK";
                                    }
                                    else
                                    {
                                        retorno.estado.codigo = "0001";
                                        retorno.estado.descripcion = "Error al insertar, favor verifique";
                                        retorno.estado.tipo = "ER";
                                    }

                                }
                                else
                                {
                                    retorno.estado.codigo = "0002";
                                    retorno.estado.descripcion = "Registro previamente insertado, verifique";
                                    retorno.estado.tipo = "ER";
                                }
                            }
                            else
                            {
                                retorno.estado.codigo = "0003";
                                retorno.estado.descripcion = "Atributo {tipo} no valido, verifique";
                                retorno.estado.tipo = "ER";
                            }
                            break;
                case "R":

                            String filtro = "";
                            if(!tipoCategoriaFecha.id.isEmpty() && !tipoCategoriaFecha.id.equals("?"))
                            {
                                searchQuery.put("_id",new ObjectId(tipoCategoriaFecha.id));
                                filtro = "id";
                                qry = true;
                            }
                            else
                            {
                                if(!tipoCategoriaFecha.tipo.isEmpty() && !tipoCategoriaFecha.tipo.equals("?"))
                                {   
                                    searchQuery.put("tipo",tipoCategoriaFecha.tipo);
                                    filtro = "tipo";
                                    qry = true;
                                }
                                else
                                {
                                    if(!tipoCategoriaFecha.descripcion.isEmpty() && !tipoCategoriaFecha.descripcion.equals("?"))
                                    {   
                                        searchQuery.put("descripcion",tipoCategoriaFecha.descripcion);
                                        filtro = "descripcion";
                                        qry = true;
                                    }
                                    else
                                    {
                                        qry = true;
                                    }
                                }
                            }
                            if(qry)
                            {
                                DBCursor cursor = null;
                                cursor = table.find(searchQuery);

                                if (cursor.count() > 0)
                                {   
                                    retorno.estado.codigo = "0000";
                                    retorno.estado.descripcion = "Consulta satisfactoria: " + filtro;
                                    retorno.estado.detalle = Integer.toString(cursor.count());
                                    retorno.estado.tipo = "OK";
                                    
                                    retorno.item = new ArrayList<tipoCategoriaFecha>();
                                    
                                    while (cursor.hasNext()) 
                                    {
                                        retorno.item.add(importJson.importJsonTipoCategoriaFecha((BasicDBObject) cursor.next()));
                                    }
                                }
                                else
                                {
                                    retorno.estado.codigo = "0005";
                                    retorno.estado.descripcion = "No se encontraron documentos con el filtro solicitado {\"" + filtro + "\"}, verifique";
                                    retorno.estado.detalle = exportJson.exportJson(tipoCategoriaFecha).toJson();
                                    retorno.estado.tipo = "ER";
                                }
                                cursor.close();
                            }
                            else
                            {
                                retorno.estado.codigo = "0004";
                                retorno.estado.descripcion = "Atributo {tipo} no valido, verifique";
                                retorno.estado.tipo = "ER";
                            }
                            
                            break;
                case "U":
                            item = null;
                            if(!tipoCategoriaFecha.id.isEmpty() && !tipoCategoriaFecha.id.equals("?"))
                            {
                                searchQuery.put("_id",new ObjectId(tipoCategoriaFecha.id));
                                filtro = "id";
                                
                                DBCursor cursor = table.find(searchQuery);

                                if (cursor.count() ==  1)
                                {
                                    qry = true;
                                    while (cursor.hasNext()) 
                                    {
                                        item = importJson.importJsonTipoCategoriaFecha((BasicDBObject) cursor.next());
                                    } 
                                }
                                cursor.close();
                            }
                            if (qry)
                            {
                                BasicDBObject updateObj = new BasicDBObject();
                                updateObj.put("$set", exportJson.exportJsonUdp(tipoCategoriaFecha));
                                
                                System.out.println(searchQuery.toJson() + " - " + updateObj.toJson());
                                
                                table.update(searchQuery, updateObj);
                                
                                retorno.estado.codigo = "0000";
                                retorno.estado.descripcion = "Actualizacion satisfactoria, Id: " + tipoCategoriaFecha.id ;
                                retorno.estado.detalle = searchQuery.toJson() + " - " + updateObj.toJson();
                                retorno.estado.tipo = "OK";
                                
                                retorno.item = new ArrayList<tipoCategoriaFecha>();
                                retorno.item.add(item);
                                retorno.item.add(tipoCategoriaFecha);
                                
                            }
                            else
                            {
                                retorno.estado.codigo = "0006";
                                retorno.estado.descripcion = "Atributo {\"_id\":\"" + tipoCategoriaFecha.id + "\"} no encontrado, verifique";
                                retorno.estado.tipo = "ER";
                            }
                            break;
                case "D":
                            item = null;
                            if(!tipoCategoriaFecha.id.isEmpty())
                            {
                                searchQuery.put("_id",new ObjectId(tipoCategoriaFecha.id));
                                filtro = "id";
                                
                                DBCursor cursor = table.find(searchQuery);
                                
                                if (cursor.count() == 1)
                                {
                                    qry = true;
                                    while (cursor.hasNext()) 
                                    {
                                        item = importJson.importJsonTipoCategoriaFecha((BasicDBObject) cursor.next());
                                    } 
                                }
                                cursor.close();
                            }
                            if (qry && item != null)
                            {   
                                table.remove(searchQuery);
                                
                                retorno.estado.codigo = "0000";
                                retorno.estado.descripcion = "Eliminacion satisfactoria, Id: " + tipoCategoriaFecha.id ;
                                retorno.estado.tipo = "OK";
                                
                                retorno.item = new ArrayList<tipoCategoriaFecha>();
                                retorno.item.add(item);
                                
                            }
                            else
                            {
                                retorno.estado.codigo = "0007";
                                retorno.estado.descripcion = "Atributo {\"_id\":\"" + tipoCategoriaFecha.id + "\"} no encontrado, verifique";
                                retorno.estado.tipo = "ER";
                            }
                            break;
                default:
                            retorno.estado.codigo = "DF01";
                            retorno.estado.descripcion = "Opcion no valida";
                            retorno.estado.tipo = "ER";
                            break;
            }
            mongo.close();
        }
        catch(Exception ex)
        {

        }
        return retorno;
        
    }
    
    @WebMethod(operationName = "tipoCategoriaContactoCRUD")
    public respuestaTipoCategoriaContacto tipoCategoriaContactoCRUD(@WebParam(name = "Servidor") servidor servidor, @WebParam(name = "Accion") String accion, @WebParam(name = "TipoCategoriaContacto") tipoCategoriaContacto tipoCategoriaContacto)
    {
        respuestaTipoCategoriaContacto retorno = new respuestaTipoCategoriaContacto();
        
        try
        {
            MongoClient mongo = new MongoClient(servidor.servidor , Integer.parseInt(servidor.puerto));
            DB db = mongo.getDB(servidor.basedatos);
            
            DBCollection table = db.getCollection("tipoCategoriaContacto");
            BasicDBObject searchQuery = new BasicDBObject();

            boolean qry = false;
            tipoCategoriaContacto item = null;

            switch(accion)
            {
                case "C":
                            if(!tipoCategoriaContacto.tipo.isEmpty() && !tipoCategoriaContacto.tipo.equals("?"))
                            {
                                int count1 = 0, count2 = 0;
                                count1 = (int) table.getCount();
 
                                searchQuery.put("tipo",tipoCategoriaContacto.tipo);

                                DBCursor cursor = table.find(searchQuery);
                                
                                if (cursor.count()==0)
                                {
                                    
                                    cursor.close();
                                    table.insert(exportJson.exportJsonUdp(tipoCategoriaContacto));
                                    count2 = (int) table.getCount();
                                    if(count2 > count1)
                                    {
                                        retorno.estado.codigo = "0000";
                                        retorno.estado.descripcion = "Creacion satisfactoria" ;
                                        retorno.estado.detalle = exportJson.exportJson(tipoCategoriaContacto).toJson();
                                        retorno.item = new ArrayList<tipoCategoriaContacto>();
                                        retorno.item.add(tipoCategoriaContacto);
                                        retorno.estado.tipo = "OK";
                                    }
                                    else
                                    {
                                        retorno.estado.codigo = "0001";
                                        retorno.estado.descripcion = "Error al insertar, favor verifique";
                                        retorno.estado.tipo = "ER";
                                    }

                                }
                                else
                                {
                                    retorno.estado.codigo = "0002";
                                    retorno.estado.descripcion = "Registro previamente insertado, verifique";
                                    retorno.estado.tipo = "ER";
                                }
                            }
                            else
                            {
                                retorno.estado.codigo = "0003";
                                retorno.estado.descripcion = "Atributo {tipo} no valido, verifique";
                                retorno.estado.tipo = "ER";
                            }
                            break;
                case "R":

                            String filtro = "";
                            if(!tipoCategoriaContacto.id.isEmpty() && !tipoCategoriaContacto.id.equals("?"))
                            {
                                searchQuery.put("_id",new ObjectId(tipoCategoriaContacto.id));
                                filtro = "id";
                                qry = true;
                            }
                            else
                            {
                                if(!tipoCategoriaContacto.tipo.isEmpty() && !tipoCategoriaContacto.tipo.equals("?"))
                                {   
                                    searchQuery.put("tipo",tipoCategoriaContacto.tipo);
                                    filtro = "tipo";
                                    qry = true;
                                }
                                else
                                {
                                    if(!tipoCategoriaContacto.descripcion.isEmpty() && !tipoCategoriaContacto.descripcion.equals("?"))
                                    {   
                                        searchQuery.put("descripcion",tipoCategoriaContacto.descripcion);
                                        filtro = "descripcion";
                                        qry = true;
                                    }
                                    else
                                    {
                                        qry = true;
                                    }
                                }
                            }
                            if(qry)
                            {
                                DBCursor cursor = null;
                                cursor = table.find(searchQuery);

                                if (cursor.count() > 0)
                                {   
                                    retorno.estado.codigo = "0000";
                                    retorno.estado.descripcion = "Consulta satisfactoria: " + filtro;
                                    retorno.estado.detalle = Integer.toString(cursor.count());
                                    retorno.estado.tipo = "OK";
                                    
                                    retorno.item = new ArrayList<tipoCategoriaContacto>();
                                    
                                    while (cursor.hasNext()) 
                                    {
                                        retorno.item.add(importJson.importJsonTipoCategoriaContacto((BasicDBObject) cursor.next()));
                                    }
                                }
                                else
                                {
                                    retorno.estado.codigo = "0005";
                                    retorno.estado.descripcion = "No se encontraron documentos con el filtro solicitado {\"" + filtro + "\"}, verifique";
                                    retorno.estado.detalle = exportJson.exportJson(tipoCategoriaContacto).toJson();
                                    retorno.estado.tipo = "ER";
                                }
                                cursor.close();
                            }
                            else
                            {
                                retorno.estado.codigo = "0004";
                                retorno.estado.descripcion = "Atributo {tipo} no valido, verifique";
                                retorno.estado.tipo = "ER";
                            }
                            
                            break;
                case "U":
                            item = null;
                            if(!tipoCategoriaContacto.id.isEmpty() && !tipoCategoriaContacto.id.equals("?"))
                            {
                                searchQuery.put("_id",new ObjectId(tipoCategoriaContacto.id));
                                filtro = "id";
                                
                                DBCursor cursor = table.find(searchQuery);

                                if (cursor.count() ==  1)
                                {
                                    qry = true;
                                    while (cursor.hasNext()) 
                                    {
                                        item = importJson.importJsonTipoCategoriaContacto((BasicDBObject) cursor.next());
                                    } 
                                }
                                cursor.close();
                            }
                            if (qry)
                            {
                                BasicDBObject updateObj = new BasicDBObject();
                                updateObj.put("$set", exportJson.exportJsonUdp(tipoCategoriaContacto));
                                
                                System.out.println(searchQuery.toJson() + " - " + updateObj.toJson());
                                
                                table.update(searchQuery, updateObj);
                                
                                retorno.estado.codigo = "0000";
                                retorno.estado.descripcion = "Actualizacion satisfactoria, Id: " + tipoCategoriaContacto.id ;
                                retorno.estado.detalle = searchQuery.toJson() + " - " + updateObj.toJson();
                                retorno.estado.tipo = "OK";
                                
                                retorno.item = new ArrayList<tipoCategoriaContacto>();
                                retorno.item.add(item);
                                retorno.item.add(tipoCategoriaContacto);
                                
                            }
                            else
                            {
                                retorno.estado.codigo = "0006";
                                retorno.estado.descripcion = "Atributo {\"_id\":\"" + tipoCategoriaContacto.id + "\"} no encontrado, verifique";
                                retorno.estado.tipo = "ER";
                            }
                            break;
                case "D":
                            item = null;
                            if(!tipoCategoriaContacto.id.isEmpty())
                            {
                                searchQuery.put("_id",new ObjectId(tipoCategoriaContacto.id));
                                filtro = "id";
                                
                                DBCursor cursor = table.find(searchQuery);
                                
                                if (cursor.count() == 1)
                                {
                                    qry = true;
                                    while (cursor.hasNext()) 
                                    {
                                        item = importJson.importJsonTipoCategoriaContacto((BasicDBObject) cursor.next());
                                    } 
                                }
                                cursor.close();
                            }
                            if (qry && item != null)
                            {   
                                table.remove(searchQuery);
                                
                                retorno.estado.codigo = "0000";
                                retorno.estado.descripcion = "Eliminacion satisfactoria, Id: " + tipoCategoriaContacto.id ;
                                retorno.estado.tipo = "OK";
                                
                                retorno.item = new ArrayList<tipoCategoriaContacto>();
                                retorno.item.add(item);
                                
                            }
                            else
                            {
                                retorno.estado.codigo = "0007";
                                retorno.estado.descripcion = "Atributo {\"_id\":\"" + tipoCategoriaContacto.id + "\"} no encontrado, verifique";
                                retorno.estado.tipo = "ER";
                            }
                            break;
                default:
                            retorno.estado.codigo = "DF01";
                            retorno.estado.descripcion = "Opcion no valida";
                            retorno.estado.tipo = "ER";
                            break;
            }
            mongo.close();
        }
        catch(Exception ex)
        {

        }
        return retorno;
        
    }
    
    @WebMethod(operationName = "tipoFechaCRUD")
    public respuestaTipoFecha tipoFechaCRUD(@WebParam(name = "Servidor") servidor servidor, @WebParam(name = "Accion") String accion, @WebParam(name = "TipoFecha") tipoFecha tipoFecha)
    {
        respuestaTipoFecha retorno = new respuestaTipoFecha();
        
        try
        {
            MongoClient mongo = new MongoClient(servidor.servidor , Integer.parseInt(servidor.puerto));
            DB db = mongo.getDB(servidor.basedatos);
            
            DBCollection table = db.getCollection("tipoFecha");
            BasicDBObject searchQuery = new BasicDBObject();

            boolean qry = false;
            tipoFecha item = null;

            switch(accion)
            {
                case "C":
                            if(!tipoFecha.tipo.isEmpty() && !tipoFecha.tipo.equals("?"))
                            {
                                int count1 = 0, count2 = 0;
                                count1 = (int) table.getCount();
 
                                searchQuery.put("tipo",tipoFecha.tipo);

                                DBCursor cursor = table.find(searchQuery);
                                
                                if (cursor.count()==0)
                                {
                                    
                                    cursor.close();
                                    table.insert(exportJson.exportJsonUdp(tipoFecha));
                                    count2 = (int) table.getCount();
                                    if(count2 > count1)
                                    {
                                        retorno.estado.codigo = "0000";
                                        retorno.estado.descripcion = "Creacion satisfactoria" ;
                                        retorno.estado.detalle = exportJson.exportJson(tipoFecha).toJson();
                                        retorno.item = new ArrayList<tipoFecha>();
                                        retorno.item.add(tipoFecha);
                                        retorno.estado.tipo = "OK";
                                    }
                                    else
                                    {
                                        retorno.estado.codigo = "0001";
                                        retorno.estado.descripcion = "Error al insertar, favor verifique";
                                        retorno.estado.tipo = "ER";
                                    }

                                }
                                else
                                {
                                    retorno.estado.codigo = "0002";
                                    retorno.estado.descripcion = "Registro previamente insertado, verifique";
                                    retorno.estado.tipo = "ER";
                                }
                            }
                            else
                            {
                                retorno.estado.codigo = "0003";
                                retorno.estado.descripcion = "Atributo {tipo} no valido, verifique";
                                retorno.estado.tipo = "ER";
                            }
                            break;
                case "R":

                            String filtro = "";
                            if(!tipoFecha.id.isEmpty() && !tipoFecha.id.equals("?"))
                            {
                                searchQuery.put("_id",new ObjectId(tipoFecha.id));
                                filtro = "id";
                                qry = true;
                            }
                            else
                            {
                                if(!tipoFecha.tipo.isEmpty() && !tipoFecha.tipo.equals("?"))
                                {   
                                    searchQuery.put("tipo",tipoFecha.tipo);
                                    filtro = "tipo";
                                    qry = true;
                                }
                                else
                                {
                                    if(!tipoFecha.descripcion.isEmpty() && !tipoFecha.descripcion.equals("?"))
                                    {   
                                        searchQuery.put("descripcion",tipoFecha.descripcion);
                                        filtro = "descripcion";
                                        qry = true;
                                    }
                                    else
                                    {
                                        if(!tipoFecha.categoria.isEmpty() && !tipoFecha.categoria.equals("?"))
                                        {   
                                            searchQuery.put("categoria",tipoFecha.categoria);
                                            filtro = "categoria";
                                            qry = true;
                                        }
                                        else
                                        {
                                            qry = true;
                                        }  
                                    }
                                }
                            }
                            if(qry)
                            {
                                DBCursor cursor = null;
                                cursor = table.find(searchQuery);

                                if (cursor.count() > 0)
                                {   
                                    retorno.estado.codigo = "0000";
                                    retorno.estado.descripcion = "Consulta satisfactoria: " + filtro;
                                    retorno.estado.detalle = Integer.toString(cursor.count());
                                    retorno.estado.tipo = "OK";
                                    
                                    retorno.item = new ArrayList<tipoFecha>();
                                    
                                    while (cursor.hasNext()) 
                                    {
                                        retorno.item.add(importJson.importJsonTipoFecha((BasicDBObject) cursor.next()));
                                    }
                                }
                                else
                                {
                                    retorno.estado.codigo = "0005";
                                    retorno.estado.descripcion = "No se encontraron documentos con el filtro solicitado {\"" + filtro + "\"}, verifique";
                                    retorno.estado.detalle = exportJson.exportJson(tipoFecha).toJson();
                                    retorno.estado.tipo = "ER";
                                }
                                cursor.close();
                            }
                            else
                            {
                                retorno.estado.codigo = "0004";
                                retorno.estado.descripcion = "Atributo {tipo} no valido, verifique";
                                retorno.estado.tipo = "ER";
                            }
                            
                            break;
                case "U":
                            item = null;
                            if(!tipoFecha.id.isEmpty() && !tipoFecha.id.equals("?"))
                            {
                                searchQuery.put("_id",new ObjectId(tipoFecha.id));
                                filtro = "id";
                                
                                DBCursor cursor = table.find(searchQuery);

                                if (cursor.count() ==  1)
                                {
                                    qry = true;
                                    while (cursor.hasNext()) 
                                    {
                                        item = importJson.importJsonTipoFecha((BasicDBObject) cursor.next());
                                    } 
                                }
                                cursor.close();
                            }
                            if (qry)
                            {
                                BasicDBObject updateObj = new BasicDBObject();
                                updateObj.put("$set", exportJson.exportJsonUdp(tipoFecha));
                                
                                System.out.println(searchQuery.toJson() + " - " + updateObj.toJson());
                                
                                table.update(searchQuery, updateObj);
                                
                                retorno.estado.codigo = "0000";
                                retorno.estado.descripcion = "Actualizacion satisfactoria, Id: " + tipoFecha.id ;
                                retorno.estado.detalle = searchQuery.toJson() + " - " + updateObj.toJson();
                                retorno.estado.tipo = "OK";
                                
                                retorno.item = new ArrayList<tipoFecha>();
                                retorno.item.add(item);
                                retorno.item.add(tipoFecha);
                                
                            }
                            else
                            {
                                retorno.estado.codigo = "0006";
                                retorno.estado.descripcion = "Atributo {\"_id\":\"" + tipoFecha.id + "\"} no encontrado, verifique";
                                retorno.estado.tipo = "ER";
                            }
                            break;
                case "D":
                            item = null;
                            if(!tipoFecha.id.isEmpty())
                            {
                                searchQuery.put("_id",new ObjectId(tipoFecha.id));
                                filtro = "id";
                                
                                DBCursor cursor = table.find(searchQuery);
                                
                                if (cursor.count() == 1)
                                {
                                    qry = true;
                                    while (cursor.hasNext()) 
                                    {
                                        item = importJson.importJsonTipoFecha((BasicDBObject) cursor.next());
                                    } 
                                }
                                cursor.close();
                            }
                            if (qry && item != null)
                            {   
                                table.remove(searchQuery);
                                
                                retorno.estado.codigo = "0000";
                                retorno.estado.descripcion = "Eliminacion satisfactoria, Id: " + tipoFecha.id ;
                                retorno.estado.tipo = "OK";
                                
                                retorno.item = new ArrayList<tipoFecha>();
                                retorno.item.add(item);
                                
                            }
                            else
                            {
                                retorno.estado.codigo = "0007";
                                retorno.estado.descripcion = "Atributo {\"_id\":\"" + tipoFecha.id + "\"} no encontrado, verifique";
                                retorno.estado.tipo = "ER";
                            }
                            break;
                default:
                            retorno.estado.codigo = "DF01";
                            retorno.estado.descripcion = "Opcion no valida";
                            retorno.estado.tipo = "ER";
                            break;
            }
            mongo.close();
        }
        catch(Exception ex)
        {

        }
        return retorno;
        
    }
    
    @WebMethod(operationName = "tipoContactoCRUD")
    public respuestaTipoContacto tipoContactoCRUD(@WebParam(name = "Servidor") servidor servidor, @WebParam(name = "Accion") String accion, @WebParam(name = "TipoContacto") tipoContacto tipoContacto)
    {
        respuestaTipoContacto retorno = new respuestaTipoContacto();
        
        try
        {
            MongoClient mongo = new MongoClient(servidor.servidor , Integer.parseInt(servidor.puerto));
            DB db = mongo.getDB(servidor.basedatos);
            
            DBCollection table = db.getCollection("tipoContacto");
            BasicDBObject searchQuery = new BasicDBObject();

            boolean qry = false;
            tipoContacto item = null;

            switch(accion)
            {
                case "C":
                            if(!tipoContacto.tipo.isEmpty() && !tipoContacto.tipo.equals("?"))
                            {
                                int count1 = 0, count2 = 0;
                                count1 = (int) table.getCount();
 
                                searchQuery.put("tipo",tipoContacto.tipo);

                                DBCursor cursor = table.find(searchQuery);
                                
                                if (cursor.count()==0)
                                {
                                    
                                    cursor.close();
                                    table.insert(exportJson.exportJsonUdp(tipoContacto));
                                    count2 = (int) table.getCount();
                                    if(count2 > count1)
                                    {
                                        retorno.estado.codigo = "0000";
                                        retorno.estado.descripcion = "Creacion satisfactoria" ;
                                        retorno.estado.detalle = exportJson.exportJson(tipoContacto).toJson();
                                        retorno.item = new ArrayList<tipoContacto>();
                                        retorno.item.add(tipoContacto);
                                        retorno.estado.tipo = "OK";
                                    }
                                    else
                                    {
                                        retorno.estado.codigo = "0001";
                                        retorno.estado.descripcion = "Error al insertar, favor verifique";
                                        retorno.estado.tipo = "ER";
                                    }

                                }
                                else
                                {
                                    retorno.estado.codigo = "0002";
                                    retorno.estado.descripcion = "Registro previamente insertado, verifique";
                                    retorno.estado.tipo = "ER";
                                }
                            }
                            else
                            {
                                retorno.estado.codigo = "0003";
                                retorno.estado.descripcion = "Atributo {tipo} no valido, verifique";
                                retorno.estado.tipo = "ER";
                            }
                            break;
                case "R":

                            String filtro = "";
                            if(!tipoContacto.id.isEmpty() && !tipoContacto.id.equals("?"))
                            {
                                searchQuery.put("_id",new ObjectId(tipoContacto.id));
                                filtro = "id";
                                qry = true;
                            }
                            else
                            {
                                if(!tipoContacto.tipo.isEmpty() && !tipoContacto.tipo.equals("?"))
                                {   
                                    searchQuery.put("tipo",tipoContacto.tipo);
                                    filtro = "tipo";
                                    qry = true;
                                }
                                else
                                {
                                    if(!tipoContacto.descripcion.isEmpty() && !tipoContacto.descripcion.equals("?"))
                                    {   
                                        searchQuery.put("descripcion",tipoContacto.descripcion);
                                        filtro = "descripcion";
                                        qry = true;
                                    }
                                    else
                                    {
                                        if(!tipoContacto.categoria.isEmpty() && !tipoContacto.categoria.equals("?"))
                                        {   
                                            searchQuery.put("categoria",tipoContacto.categoria);
                                            filtro = "categoria";
                                            qry = true;
                                        }
                                        else
                                        {
                                            qry = true;
                                        }  
                                    }
                                }
                            }
                            if(qry)
                            {
                                DBCursor cursor = null;
                                cursor = table.find(searchQuery);

                                if (cursor.count() > 0)
                                {   
                                    retorno.estado.codigo = "0000";
                                    retorno.estado.descripcion = "Consulta satisfactoria: " + filtro;
                                    retorno.estado.detalle = Integer.toString(cursor.count());
                                    retorno.estado.tipo = "OK";
                                    
                                    retorno.item = new ArrayList<tipoContacto>();
                                    
                                    while (cursor.hasNext()) 
                                    {
                                        retorno.item.add(importJson.importJsonTipoContacto((BasicDBObject) cursor.next()));
                                    }
                                }
                                else
                                {
                                    retorno.estado.codigo = "0005";
                                    retorno.estado.descripcion = "No se encontraron documentos con el filtro solicitado {\"" + filtro + "\"}, verifique";
                                    retorno.estado.detalle = exportJson.exportJson(tipoContacto).toJson();
                                    retorno.estado.tipo = "ER";
                                }
                                cursor.close();
                            }
                            else
                            {
                                retorno.estado.codigo = "0004";
                                retorno.estado.descripcion = "Atributo {tipo} no valido, verifique";
                                retorno.estado.tipo = "ER";
                            }
                            
                            break;
                case "U":
                            item = null;
                            if(!tipoContacto.id.isEmpty() && !tipoContacto.id.equals("?"))
                            {
                                searchQuery.put("_id",new ObjectId(tipoContacto.id));
                                filtro = "id";
                                
                                DBCursor cursor = table.find(searchQuery);

                                if (cursor.count() ==  1)
                                {
                                    qry = true;
                                    while (cursor.hasNext()) 
                                    {
                                        item = importJson.importJsonTipoContacto((BasicDBObject) cursor.next());
                                    } 
                                }
                                cursor.close();
                            }
                            if (qry)
                            {
                                BasicDBObject updateObj = new BasicDBObject();
                                updateObj.put("$set", exportJson.exportJsonUdp(tipoContacto));
                                
                                System.out.println(searchQuery.toJson() + " - " + updateObj.toJson());
                                
                                table.update(searchQuery, updateObj);
                                
                                retorno.estado.codigo = "0000";
                                retorno.estado.descripcion = "Actualizacion satisfactoria, Id: " + tipoContacto.id ;
                                retorno.estado.detalle = searchQuery.toJson() + " - " + updateObj.toJson();
                                retorno.estado.tipo = "OK";
                                
                                retorno.item = new ArrayList<tipoContacto>();
                                retorno.item.add(item);
                                retorno.item.add(tipoContacto);
                                
                            }
                            else
                            {
                                retorno.estado.codigo = "0006";
                                retorno.estado.descripcion = "Atributo {\"_id\":\"" + tipoContacto.id + "\"} no encontrado, verifique";
                                retorno.estado.tipo = "ER";
                            }
                            break;
                case "D":
                            item = null;
                            if(!tipoContacto.id.isEmpty())
                            {
                                searchQuery.put("_id",new ObjectId(tipoContacto.id));
                                filtro = "id";
                                
                                DBCursor cursor = table.find(searchQuery);
                                
                                if (cursor.count() == 1)
                                {
                                    qry = true;
                                    while (cursor.hasNext()) 
                                    {
                                        item = importJson.importJsonTipoContacto((BasicDBObject) cursor.next());
                                    } 
                                }
                                cursor.close();
                            }
                            if (qry && item != null)
                            {   
                                table.remove(searchQuery);
                                
                                retorno.estado.codigo = "0000";
                                retorno.estado.descripcion = "Eliminacion satisfactoria, Id: " + tipoContacto.id ;
                                retorno.estado.tipo = "OK";
                                
                                retorno.item = new ArrayList<tipoContacto>();
                                retorno.item.add(item);
                                
                            }
                            else
                            {
                                retorno.estado.codigo = "0007";
                                retorno.estado.descripcion = "Atributo {\"_id\":\"" + tipoContacto.id + "\"} no encontrado, verifique";
                                retorno.estado.tipo = "ER";
                            }
                            break;
                default:
                            retorno.estado.codigo = "DF01";
                            retorno.estado.descripcion = "Opcion no valida";
                            retorno.estado.tipo = "ER";
                            break;
            }
            mongo.close();
        }
        catch(Exception ex)
        {

        }
        return retorno;
        
    }
    
    @WebMethod(operationName = "entradaCRUD")
    public respuestaEntrada entradaCRUD(@WebParam(name = "Servidor") servidor servidor, @WebParam(name = "Accion") String accion, @WebParam(name = "Entrada") entrada entrada)
    {
        respuestaEntrada retorno = new respuestaEntrada();
        
        try
        {
            MongoClient mongo = new MongoClient(servidor.servidor , Integer.parseInt(servidor.puerto));
            DB db = mongo.getDB(servidor.basedatos);
            
            DBCollection table = db.getCollection("entrada");
            DBCollection tabletemp = null;
            BasicDBObject searchQuery = new BasicDBObject();

            boolean qry = false;
            entrada item = null;

            switch(accion)
            {
                case "C":
                            retorno.estado = entrada.validaEntrada();
                            if(retorno.estado.tipo.equals("OK"))
                            {
                                int count1 = 0, count2 = 0;
                                count1 = (int) table.getCount();
 
                                List<BasicDBObject> obj = new ArrayList<BasicDBObject>();
                                obj.add(new BasicDBObject("identificador", entrada.identificador));
                                obj.add(new BasicDBObject("nombre", entrada.nombre));
                                searchQuery.put("$or", obj); 
                                searchQuery.put("usuario",entrada.usuario);

                                DBCursor cursor = table.find(searchQuery);
                                
                                System.out.println(cursor.count());
                                
                                if (cursor.count()==0)
                                {
                                    cursor.close();
                                    
                                    searchQuery = new BasicDBObject();
                                    searchQuery.put("tipo", entrada.tipoPersona);

                                    tabletemp = db.getCollection("tipoPersona");
                                    cursor = tabletemp.find(searchQuery);
                                    
                                    System.out.println(cursor.count());
                                    
                                    if (cursor.count() > 0)
                                    {
                                        cursor.close();
                                    
                                        searchQuery = new BasicDBObject();
                                        searchQuery.put("tipo", entrada.tipoContacto);
                                    
                                        tabletemp = db.getCollection("tipoContacto");
                                        cursor = tabletemp.find(searchQuery);
                                    
                                        if (cursor.count() > 0)
                                        {
                                            table.insert(exportJson.exportJsonUpd(entrada));
                                            count2 = (int) table.getCount();
                                            if(count2 > count1)
                                            {
                                                retorno.estado.codigo = "0000";
                                                retorno.estado.descripcion = "Creacion satisfactoria" ;
                                                retorno.estado.detalle = exportJson.exportJsonUpd(entrada).toJson();
                                                retorno.item = new ArrayList<entrada>();
                                                retorno.item.add(entrada);
                                                retorno.estado.tipo = "OK";
                                            }
                                            else
                                            {
                                                retorno.estado.codigo = "0001";
                                                retorno.estado.descripcion = "Error al insertar, favor verifique";
                                                retorno.estado.tipo = "ER";
                                            }
                                            cursor.close();
                                        }
                                        else
                                        {
                                            retorno.estado.codigo = "0009";
                                            retorno.estado.descripcion = "Tipo contacto no valido, verifique";
                                            retorno.estado.tipo = "ER";
                                        }
                                    }
                                    else
                                    {
                                        retorno.estado.codigo = "0008";
                                        retorno.estado.descripcion = "Tipo persona no valido, verifique";
                                        retorno.estado.tipo = "ER";
                                    }
                                }
                                else
                                {
                                    retorno.estado.codigo = "0002";
                                    retorno.estado.descripcion = "Registro previamente insertado, verifique";
                                    retorno.estado.tipo = "ER";
                                }
                            }
                            else
                            {
                                // El metodo de la verificacion de la clase ya asigna el mensaje segun criterio de validacion
                            }
                            break;
                case "R":

                            String filtro = "";
                            if(!entrada.id.isEmpty() && !entrada.id.equals("?"))
                            {
                                searchQuery.put("_id",new ObjectId(entrada.id));
                                filtro = "id";
                                qry = true;
                            }
                            else
                            {
                                if(!entrada.identificador.isEmpty() && !entrada.identificador.equals("?"))
                                {
                                    searchQuery.put("identificador",entrada.identificador);
                                    filtro = "identificador";
                                    qry = true;
                                }
                                else
                                {
                                    if(!entrada.nombre.isEmpty() && !entrada.nombre.equals("?"))
                                    {   
                                        searchQuery.put("nombre",entrada.nombre);
                                        filtro = "nombre";
                                        qry = true;
                                    }
                                    else
                                    {
                                        if(!entrada.tipoPersona.isEmpty() && !entrada.tipoPersona.equals("?"))
                                        {   
                                            searchQuery.put("tipoPersona",entrada.tipoPersona);
                                            filtro = "tipopersona";
                                            qry = true;
                                        }
                                        else
                                        {
                                            if(!entrada.tipoContacto.isEmpty() && !entrada.tipoContacto.equals("?"))
                                            {   
                                                searchQuery.put("tipoContacto",entrada.tipoContacto);
                                                filtro = "tipocontacto";
                                                qry = true;
                                            }
                                            else
                                            {
                                                qry = true;
                                            }  
                                        }
                                    }
                                }
                            }
                            if(qry)
                            {
                                DBCursor cursor = null;
                                searchQuery.put("usuario",entrada.usuario);
                                
                                System.out.println(searchQuery.toJson());
                                
                                cursor = table.find(searchQuery);

                                if (cursor.count() > 0)
                                {   
                                    retorno.estado.codigo = "0000";
                                    retorno.estado.descripcion = "Consulta satisfactoria: " + filtro;
                                    retorno.estado.detalle = Integer.toString(cursor.count());
                                    retorno.estado.tipo = "OK";
                                    
                                    retorno.item = new ArrayList<entrada>();
                                    
                                    while (cursor.hasNext()) 
                                    {
                                        retorno.item.add(importJson.importJsonEntrada((BasicDBObject) cursor.next()));
                                    }
                                }
                                else
                                {
                                    retorno.estado.codigo = "0005";
                                    retorno.estado.descripcion = "No se encontraron documentos con el filtro solicitado {\"" + filtro + "\"}, verifique";
                                    retorno.estado.detalle = exportJson.exportJson(entrada).toJson();
                                    retorno.estado.tipo = "ER";
                                }
                                cursor.close();
                            }
                            else
                            {
                                retorno.estado.codigo = "0004";
                                retorno.estado.descripcion = "Atributo {tipo} no valido, verifique";
                                retorno.estado.tipo = "ER";
                            }
                            
                            break;
                case "U":
 
                            item = null;
                            if(!entrada.id.isEmpty() && !entrada.id.equals("?"))
                            {
                                searchQuery.put("_id",new ObjectId(entrada.id));
                                filtro = "id";
                                                                
                                DBCursor cursor = table.find(searchQuery);

                                if (cursor.count() ==  1)
                                {
                                    qry = true;
                                    while (cursor.hasNext()) 
                                    {
                                        item = importJson.importJsonEntrada((BasicDBObject) cursor.next());
                                    } 
                                }
                                cursor.close();
                            }
                            if (qry)
                            {
                                BasicDBObject updateObj = new BasicDBObject();
                                updateObj.put("$set", exportJson.exportJsonUpd(entrada));
                                
                                System.out.println(searchQuery.toJson() + " - " + updateObj.toJson());
                                
                                table.update(searchQuery, updateObj);
                                
                                retorno.estado.codigo = "0000";
                                retorno.estado.descripcion = "Actualizacion satisfactoria, Id: " + entrada.id ;
                                retorno.estado.detalle = searchQuery.toJson() + " - " + updateObj.toJson();
                                retorno.estado.tipo = "OK";
                                
                                retorno.item = new ArrayList<entrada>();
                                retorno.item.add(item);
                                retorno.item.add(entrada);
                                
                            }
                            else
                            {
                                retorno.estado.codigo = "0006";
                                retorno.estado.descripcion = "Atributo {\"_id\":\"" + entrada.id + "\"} no encontrado, verifique";
                                retorno.estado.tipo = "ER";
                            }
                            break;
                case "D":
                            
                            item = null;
                            if(!entrada.id.isEmpty() && !entrada.id.equals("?"))
                            {
                                searchQuery.put("_id",new ObjectId(entrada.id));
                                filtro = "id";
                                
                                DBCursor cursor = table.find(searchQuery);
                                
                                if (cursor.count() == 1)
                                {
                                    qry = true;
                                    while (cursor.hasNext()) 
                                    {
                                        item = importJson.importJsonEntrada((BasicDBObject) cursor.next());
                                    } 
                                }
                                cursor.close();
                            }
                            if (qry && item != null)
                            {   
                                table.remove(searchQuery);
                                
                                retorno.estado.codigo = "0000";
                                retorno.estado.descripcion = "Eliminacion satisfactoria, Id: " + entrada.id ;
                                retorno.estado.tipo = "OK";
                                
                                retorno.item = new ArrayList<entrada>();
                                retorno.item.add(item);
                                
                            }
                            else
                            {
                                retorno.estado.codigo = "0007";
                                retorno.estado.descripcion = "Atributo {\"_id\":\"" + entrada.id + "\"} no encontrado, verifique";
                                retorno.estado.tipo = "ER";
                            }
                            break;
                default:
                            retorno.estado.codigo = "DF01";
                            retorno.estado.descripcion = "Opcion no valida";
                            retorno.estado.tipo = "ER";
                            break;
            }
            mongo.close();
        }
        catch(Exception ex)
        {

        }
        return retorno;
        
    }
}


/*
private static void in_Example(DBCollection collection)
{
    BasicDBObject inQuery = new BasicDBObject();
 
    List<Integer> list = new ArrayList<Integer>();
    list.add(2);
    list.add(4);
    list.add(5);
 
    inQuery.put("employeeId", new BasicDBObject("$in", list));
 
    DBCursor cursor = collection.find(inQuery);
    while(cursor.hasNext()) {
        System.out.println(cursor.next());
}

private static void andLogicalComparison_Example(DBCollection collection)
{
    BasicDBObject andQuery = new BasicDBObject();
    List<BasicDBObject> obj = new ArrayList<BasicDBObject>();
    obj.add(new BasicDBObject("employeeId", 2));
    obj.add(new BasicDBObject("employeeName", "TestEmployee_2"));
    andQuery.put("$and", obj);
  
    System.out.println(andQuery.toString());
  
    DBCursor cursor = collection.find(andQuery);
    while (cursor.hasNext()) {
        System.out.println(cursor.next());
    }
}


*/