package com.siscogescorp.modelo;
// Generated 14-ene-2018 19:02:18 by Hibernate Tools 4.3.1


import java.util.Date;

/**
 * LcAsignaEmpleadosTmp generated by hbm2java
 */
public class LcAsignaEmpleadosTmp  implements java.io.Serializable {


     private int idSecCampania;
     private LcEmpleados lcEmpleados;
     private Integer idUsuarioSession;
     private Date fechaRegistro;
     private String estado;
     private Integer idCliente;

    public LcAsignaEmpleadosTmp() {
    }

	
    public LcAsignaEmpleadosTmp(int idSecCampania) {
        this.idSecCampania = idSecCampania;
    }
    public LcAsignaEmpleadosTmp(int idSecCampania, LcEmpleados lcEmpleados, Integer idUsuarioSession, Date fechaRegistro, String estado, Integer idCliente) {
       this.idSecCampania = idSecCampania;
       this.lcEmpleados = lcEmpleados;
       this.idUsuarioSession = idUsuarioSession;
       this.fechaRegistro = fechaRegistro;
       this.estado = estado;
       this.idCliente = idCliente;
    }
   
    public int getIdSecCampania() {
        return this.idSecCampania;
    }
    
    public void setIdSecCampania(int idSecCampania) {
        this.idSecCampania = idSecCampania;
    }
    public LcEmpleados getLcEmpleados() {
        return this.lcEmpleados;
    }
    
    public void setLcEmpleados(LcEmpleados lcEmpleados) {
        this.lcEmpleados = lcEmpleados;
    }
    public Integer getIdUsuarioSession() {
        return this.idUsuarioSession;
    }
    
    public void setIdUsuarioSession(Integer idUsuarioSession) {
        this.idUsuarioSession = idUsuarioSession;
    }
    public Date getFechaRegistro() {
        return this.fechaRegistro;
    }
    
    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
    public String getEstado() {
        return this.estado;
    }
    
    public void setEstado(String estado) {
        this.estado = estado;
    }
    public Integer getIdCliente() {
        return this.idCliente;
    }
    
    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }




}


