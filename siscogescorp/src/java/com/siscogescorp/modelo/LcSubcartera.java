package com.siscogescorp.modelo;
// Generated 14-ene-2018 19:02:18 by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * LcSubcartera generated by hbm2java
 */
public class LcSubcartera  implements java.io.Serializable {


     private int idSubcartera;
     private LcCartera lcCartera;
     private String nombreSubcartera;
     private Date fechaCreacion;
     private String estado;
     private Set lcSegmentos = new HashSet(0);

    public LcSubcartera() {
    }

	
    public LcSubcartera(int idSubcartera) {
        this.idSubcartera = idSubcartera;
    }
    public LcSubcartera(int idSubcartera, LcCartera lcCartera, String nombreSubcartera, Date fechaCreacion, String estado, Set lcSegmentos) {
       this.idSubcartera = idSubcartera;
       this.lcCartera = lcCartera;
       this.nombreSubcartera = nombreSubcartera;
       this.fechaCreacion = fechaCreacion;
       this.estado = estado;
       this.lcSegmentos = lcSegmentos;
    }
   
    public int getIdSubcartera() {
        return this.idSubcartera;
    }
    
    public void setIdSubcartera(int idSubcartera) {
        this.idSubcartera = idSubcartera;
    }
    public LcCartera getLcCartera() {
        return this.lcCartera;
    }
    
    public void setLcCartera(LcCartera lcCartera) {
        this.lcCartera = lcCartera;
    }
    public String getNombreSubcartera() {
        return this.nombreSubcartera;
    }
    
    public void setNombreSubcartera(String nombreSubcartera) {
        this.nombreSubcartera = nombreSubcartera;
    }
    public Date getFechaCreacion() {
        return this.fechaCreacion;
    }
    
    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
    public String getEstado() {
        return this.estado;
    }
    
    public void setEstado(String estado) {
        this.estado = estado;
    }
    public Set getLcSegmentos() {
        return this.lcSegmentos;
    }
    
    public void setLcSegmentos(Set lcSegmentos) {
        this.lcSegmentos = lcSegmentos;
    }




}


