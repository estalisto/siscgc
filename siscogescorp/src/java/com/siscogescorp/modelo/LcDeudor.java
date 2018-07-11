package com.siscogescorp.modelo;
// Generated 24/05/2018 12:17:21 by Hibernate Tools 4.3.1


import java.util.Date;

/**
 * LcDeudor generated by hbm2java
 */
public class LcDeudor  implements java.io.Serializable {


     private int idDeudor;
     private LcClientes lcClientes;
     private LcEmpresa lcEmpresa;
     private String nombreCartera;
     private String observacion;
     private String datosAdicional;
     private Date fechaCreacion;
     private Date fechaActualizacion;
     private String estado;

    public LcDeudor() {
    }

	
    public LcDeudor(int idDeudor) {
        this.idDeudor = idDeudor;
    }
    public LcDeudor(int idDeudor, LcClientes lcClientes, LcEmpresa lcEmpresa, String nombreCartera, String observacion, String datosAdicional, Date fechaCreacion, Date fechaActualizacion, String estado) {
       this.idDeudor = idDeudor;
       this.lcClientes = lcClientes;
       this.lcEmpresa = lcEmpresa;
       this.nombreCartera = nombreCartera;
       this.observacion = observacion;
       this.datosAdicional = datosAdicional;
       this.fechaCreacion = fechaCreacion;
       this.fechaActualizacion = fechaActualizacion;
       this.estado = estado;
    }
   
    public int getIdDeudor() {
        return this.idDeudor;
    }
    
    public void setIdDeudor(int idDeudor) {
        this.idDeudor = idDeudor;
    }
    public LcClientes getLcClientes() {
        return this.lcClientes;
    }
    
    public void setLcClientes(LcClientes lcClientes) {
        this.lcClientes = lcClientes;
    }
    public LcEmpresa getLcEmpresa() {
        return this.lcEmpresa;
    }
    
    public void setLcEmpresa(LcEmpresa lcEmpresa) {
        this.lcEmpresa = lcEmpresa;
    }
    public String getNombreCartera() {
        return this.nombreCartera;
    }
    
    public void setNombreCartera(String nombreCartera) {
        this.nombreCartera = nombreCartera;
    }
    public String getObservacion() {
        return this.observacion;
    }
    
    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }
    public String getDatosAdicional() {
        return this.datosAdicional;
    }
    
    public void setDatosAdicional(String datosAdicional) {
        this.datosAdicional = datosAdicional;
    }
    public Date getFechaCreacion() {
        return this.fechaCreacion;
    }
    
    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
    public Date getFechaActualizacion() {
        return this.fechaActualizacion;
    }
    
    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }
    public String getEstado() {
        return this.estado;
    }
    
    public void setEstado(String estado) {
        this.estado = estado;
    }




}


