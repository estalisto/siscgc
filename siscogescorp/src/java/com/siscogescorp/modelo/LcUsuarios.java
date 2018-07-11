package com.siscogescorp.modelo;
// Generated 24/05/2018 12:17:21 by Hibernate Tools 4.3.1


import java.util.Date;

/**
 * LcUsuarios generated by hbm2java
 */
public class LcUsuarios  implements java.io.Serializable {


     private int idUsuario;
     private LcEmpleados lcEmpleados;
     private LcEmpresa lcEmpresa;
     private LcRoles lcRoles;
     private String usuario;
     private String contrasenia;
     private String observacion;
     private Date fechaCreacion;
     private String estado;
     private Date expiracionTmp;

    public LcUsuarios() {
    }

	
    public LcUsuarios(int idUsuario) {
        this.idUsuario = idUsuario;
    }
    public LcUsuarios(int idUsuario, LcEmpleados lcEmpleados, LcEmpresa lcEmpresa, LcRoles lcRoles, String usuario, String contrasenia, String observacion, Date fechaCreacion, String estado, Date expiracionTmp) {
       this.idUsuario = idUsuario;
       this.lcEmpleados = lcEmpleados;
       this.lcEmpresa = lcEmpresa;
       this.lcRoles = lcRoles;
       this.usuario = usuario;
       this.contrasenia = contrasenia;
       this.observacion = observacion;
       this.fechaCreacion = fechaCreacion;
       this.estado = estado;
       this.expiracionTmp = expiracionTmp;
    }
   
    public int getIdUsuario() {
        return this.idUsuario;
    }
    
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
    public LcEmpleados getLcEmpleados() {
        return this.lcEmpleados;
    }
    
    public void setLcEmpleados(LcEmpleados lcEmpleados) {
        this.lcEmpleados = lcEmpleados;
    }
    public LcEmpresa getLcEmpresa() {
        return this.lcEmpresa;
    }
    
    public void setLcEmpresa(LcEmpresa lcEmpresa) {
        this.lcEmpresa = lcEmpresa;
    }
    public LcRoles getLcRoles() {
        return this.lcRoles;
    }
    
    public void setLcRoles(LcRoles lcRoles) {
        this.lcRoles = lcRoles;
    }
    public String getUsuario() {
        return this.usuario;
    }
    
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    public String getContrasenia() {
        return this.contrasenia;
    }
    
    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }
    public String getObservacion() {
        return this.observacion;
    }
    
    public void setObservacion(String observacion) {
        this.observacion = observacion;
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
    public Date getExpiracionTmp() {
        return this.expiracionTmp;
    }
    
    public void setExpiracionTmp(Date expiracionTmp) {
        this.expiracionTmp = expiracionTmp;
    }




}


