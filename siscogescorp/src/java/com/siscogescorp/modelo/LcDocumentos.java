package com.siscogescorp.modelo;
// Generated 24/05/2018 12:17:21 by Hibernate Tools 4.3.1


import java.util.Date;

/**
 * LcDocumentos generated by hbm2java
 */
public class LcDocumentos  implements java.io.Serializable {


     private int idDocumento;
     private Integer idEmpresa;
     private Integer idAgencia;
     private Integer idCliente;
     private Integer idEmpleado;
     private String nombreDocumento;
     private String saludo;
     private String cuerpo;
     private String despedida;
     private String firma;
     private Date fechaRegistro;
     private Date fechaActualizado;
     private String estado;

    public LcDocumentos() {
    }

	
    public LcDocumentos(int idDocumento) {
        this.idDocumento = idDocumento;
    }
    public LcDocumentos(int idDocumento, Integer idEmpresa, Integer idAgencia, Integer idCliente, Integer idEmpleado, String nombreDocumento, String saludo, String cuerpo, String despedida, String firma, Date fechaRegistro, Date fechaActualizado, String estado) {
       this.idDocumento = idDocumento;
       this.idEmpresa = idEmpresa;
       this.idAgencia = idAgencia;
       this.idCliente = idCliente;
       this.idEmpleado = idEmpleado;
       this.nombreDocumento = nombreDocumento;
       this.saludo = saludo;
       this.cuerpo = cuerpo;
       this.despedida = despedida;
       this.firma = firma;
       this.fechaRegistro = fechaRegistro;
       this.fechaActualizado = fechaActualizado;
       this.estado = estado;
    }
   
    public int getIdDocumento() {
        return this.idDocumento;
    }
    
    public void setIdDocumento(int idDocumento) {
        this.idDocumento = idDocumento;
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
    public Integer getIdCliente() {
        return this.idCliente;
    }
    
    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }
    public Integer getIdEmpleado() {
        return this.idEmpleado;
    }
    
    public void setIdEmpleado(Integer idEmpleado) {
        this.idEmpleado = idEmpleado;
    }
    public String getNombreDocumento() {
        return this.nombreDocumento;
    }
    
    public void setNombreDocumento(String nombreDocumento) {
        this.nombreDocumento = nombreDocumento;
    }
    public String getSaludo() {
        return this.saludo;
    }
    
    public void setSaludo(String saludo) {
        this.saludo = saludo;
    }
    public String getCuerpo() {
        return this.cuerpo;
    }
    
    public void setCuerpo(String cuerpo) {
        this.cuerpo = cuerpo;
    }
    public String getDespedida() {
        return this.despedida;
    }
    
    public void setDespedida(String despedida) {
        this.despedida = despedida;
    }
    public String getFirma() {
        return this.firma;
    }
    
    public void setFirma(String firma) {
        this.firma = firma;
    }
    public Date getFechaRegistro() {
        return this.fechaRegistro;
    }
    
    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
    public Date getFechaActualizado() {
        return this.fechaActualizado;
    }
    
    public void setFechaActualizado(Date fechaActualizado) {
        this.fechaActualizado = fechaActualizado;
    }
    public String getEstado() {
        return this.estado;
    }
    
    public void setEstado(String estado) {
        this.estado = estado;
    }




}


