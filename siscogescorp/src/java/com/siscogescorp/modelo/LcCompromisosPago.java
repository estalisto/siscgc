package com.siscogescorp.modelo;
// Generated 24/05/2018 12:17:21 by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.Date;

/**
 * LcCompromisosPago generated by hbm2java
 */
public class LcCompromisosPago  implements java.io.Serializable {


     private int idCompromiso;
     private LcDatosDeudores lcDatosDeudores;
     private Integer idCliente;
     private Date fechaCompromiso;
     private BigDecimal valorCompromiso;
     private String revisado;
     private Date fechaRegistro;
     private Date fechaActualizado;
     private String estado;

    public LcCompromisosPago() {
    }

	
    public LcCompromisosPago(int idCompromiso) {
        this.idCompromiso = idCompromiso;
    }
    public LcCompromisosPago(int idCompromiso, LcDatosDeudores lcDatosDeudores, Integer idCliente, Date fechaCompromiso, BigDecimal valorCompromiso, String revisado, Date fechaRegistro, Date fechaActualizado, String estado) {
       this.idCompromiso = idCompromiso;
       this.lcDatosDeudores = lcDatosDeudores;
       this.idCliente = idCliente;
       this.fechaCompromiso = fechaCompromiso;
       this.valorCompromiso = valorCompromiso;
       this.revisado = revisado;
       this.fechaRegistro = fechaRegistro;
       this.fechaActualizado = fechaActualizado;
       this.estado = estado;
    }
   
    public int getIdCompromiso() {
        return this.idCompromiso;
    }
    
    public void setIdCompromiso(int idCompromiso) {
        this.idCompromiso = idCompromiso;
    }
    public LcDatosDeudores getLcDatosDeudores() {
        return this.lcDatosDeudores;
    }
    
    public void setLcDatosDeudores(LcDatosDeudores lcDatosDeudores) {
        this.lcDatosDeudores = lcDatosDeudores;
    }
    public Integer getIdCliente() {
        return this.idCliente;
    }
    
    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }
    public Date getFechaCompromiso() {
        return this.fechaCompromiso;
    }
    
    public void setFechaCompromiso(Date fechaCompromiso) {
        this.fechaCompromiso = fechaCompromiso;
    }
    public BigDecimal getValorCompromiso() {
        return this.valorCompromiso;
    }
    
    public void setValorCompromiso(BigDecimal valorCompromiso) {
        this.valorCompromiso = valorCompromiso;
    }
    public String getRevisado() {
        return this.revisado;
    }
    
    public void setRevisado(String revisado) {
        this.revisado = revisado;
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


