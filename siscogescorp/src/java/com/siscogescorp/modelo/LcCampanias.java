package com.siscogescorp.modelo;
// Generated 24/05/2018 12:17:21 by Hibernate Tools 4.3.1


import java.util.Date;

/**
 * LcCampanias generated by hbm2java
 */
public class LcCampanias  implements java.io.Serializable {


     private int idCampania;
     private String descripcionCarga;
     private Date fechaTransaccion;
     private Date fechaInicio;
     private Date fechaFin;
     private String estado;
     private Integer numGestiones;
     private Integer numDeudores;
     private Integer idEmpresa;
     private Integer idAgencia;

    public LcCampanias() {
    }

	
    public LcCampanias(int idCampania) {
        this.idCampania = idCampania;
    }
    public LcCampanias(int idCampania, String descripcionCarga, Date fechaTransaccion, Date fechaInicio, Date fechaFin, String estado, Integer numGestiones, Integer numDeudores, Integer idEmpresa, Integer idAgencia) {
       this.idCampania = idCampania;
       this.descripcionCarga = descripcionCarga;
       this.fechaTransaccion = fechaTransaccion;
       this.fechaInicio = fechaInicio;
       this.fechaFin = fechaFin;
       this.estado = estado;
       this.numGestiones = numGestiones;
       this.numDeudores = numDeudores;
       this.idEmpresa = idEmpresa;
       this.idAgencia = idAgencia;
    }
   
    public int getIdCampania() {
        return this.idCampania;
    }
    
    public void setIdCampania(int idCampania) {
        this.idCampania = idCampania;
    }
    public String getDescripcionCarga() {
        return this.descripcionCarga;
    }
    
    public void setDescripcionCarga(String descripcionCarga) {
        this.descripcionCarga = descripcionCarga;
    }
    public Date getFechaTransaccion() {
        return this.fechaTransaccion;
    }
    
    public void setFechaTransaccion(Date fechaTransaccion) {
        this.fechaTransaccion = fechaTransaccion;
    }
    public Date getFechaInicio() {
        return this.fechaInicio;
    }
    
    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }
    public Date getFechaFin() {
        return this.fechaFin;
    }
    
    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }
    public String getEstado() {
        return this.estado;
    }
    
    public void setEstado(String estado) {
        this.estado = estado;
    }
    public Integer getNumGestiones() {
        return this.numGestiones;
    }
    
    public void setNumGestiones(Integer numGestiones) {
        this.numGestiones = numGestiones;
    }
    public Integer getNumDeudores() {
        return this.numDeudores;
    }
    
    public void setNumDeudores(Integer numDeudores) {
        this.numDeudores = numDeudores;
    }
    public Integer getIdEmpresa() {
        return this.idEmpresa;
    }
    
    public void setIdEmpresa(Integer idEmpresa) {
        this.idEmpresa = idEmpresa;
    }
    public Integer getIdAgencia() {
        return this.idAgencia;
    }
    
    public void setIdAgencia(Integer idAgencia) {
        this.idAgencia = idAgencia;
    }




}


