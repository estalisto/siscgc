package com.siscogescorp.modelo;
// Generated 24/05/2018 12:17:21 by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * LcEmpresa generated by hbm2java
 */
public class LcEmpresa  implements java.io.Serializable {


     private int idEmpresa;
     private LcCiudad lcCiudad;
     private LcPais lcPais;
     private LcProvincia lcProvincia;
     private LcTiposIdentificacion lcTiposIdentificacion;
     private String identificacion;
     private String razonSocial;
     private String direccion;
     private String telefonos;
     private String email;
     private Date fechaCreacion;
     private Date fechaActualizacion;
     private String estado;
     private String telfonos2;
     private String celular;
     private Integer sucursal;
     private Set lcDatosDeudoreses = new HashSet(0);
     private Set lcZonases = new HashSet(0);
     private Set lcModuloRols = new HashSet(0);
     private Set lcClienteses = new HashSet(0);
     private Set lcRoleses = new HashSet(0);
     private Set lcDeudors = new HashSet(0);
     private Set lcCargoses = new HashSet(0);
     private Set lcEmpleadoses = new HashSet(0);
     private Set lcUsuarioses = new HashSet(0);
     private Set lcRecordatorioses = new HashSet(0);
     private Set lcAgencias = new HashSet(0);

    public LcEmpresa() {
    }

	
    public LcEmpresa(int idEmpresa) {
        this.idEmpresa = idEmpresa;
    }
    public LcEmpresa(int idEmpresa, LcCiudad lcCiudad, LcPais lcPais, LcProvincia lcProvincia, LcTiposIdentificacion lcTiposIdentificacion, String identificacion, String razonSocial, String direccion, String telefonos, String email, Date fechaCreacion, Date fechaActualizacion, String estado, String telfonos2, String celular, Integer sucursal, Set lcDatosDeudoreses, Set lcZonases, Set lcModuloRols, Set lcClienteses, Set lcRoleses, Set lcDeudors, Set lcCargoses, Set lcEmpleadoses, Set lcUsuarioses, Set lcRecordatorioses, Set lcAgencias) {
       this.idEmpresa = idEmpresa;
       this.lcCiudad = lcCiudad;
       this.lcPais = lcPais;
       this.lcProvincia = lcProvincia;
       this.lcTiposIdentificacion = lcTiposIdentificacion;
       this.identificacion = identificacion;
       this.razonSocial = razonSocial;
       this.direccion = direccion;
       this.telefonos = telefonos;
       this.email = email;
       this.fechaCreacion = fechaCreacion;
       this.fechaActualizacion = fechaActualizacion;
       this.estado = estado;
       this.telfonos2 = telfonos2;
       this.celular = celular;
       this.sucursal = sucursal;
       this.lcDatosDeudoreses = lcDatosDeudoreses;
       this.lcZonases = lcZonases;
       this.lcModuloRols = lcModuloRols;
       this.lcClienteses = lcClienteses;
       this.lcRoleses = lcRoleses;
       this.lcDeudors = lcDeudors;
       this.lcCargoses = lcCargoses;
       this.lcEmpleadoses = lcEmpleadoses;
       this.lcUsuarioses = lcUsuarioses;
       this.lcRecordatorioses = lcRecordatorioses;
       this.lcAgencias = lcAgencias;
    }
   
    public int getIdEmpresa() {
        return this.idEmpresa;
    }
    
    public void setIdEmpresa(int idEmpresa) {
        this.idEmpresa = idEmpresa;
    }
    public LcCiudad getLcCiudad() {
        return this.lcCiudad;
    }
    
    public void setLcCiudad(LcCiudad lcCiudad) {
        this.lcCiudad = lcCiudad;
    }
    public LcPais getLcPais() {
        return this.lcPais;
    }
    
    public void setLcPais(LcPais lcPais) {
        this.lcPais = lcPais;
    }
    public LcProvincia getLcProvincia() {
        return this.lcProvincia;
    }
    
    public void setLcProvincia(LcProvincia lcProvincia) {
        this.lcProvincia = lcProvincia;
    }
    public LcTiposIdentificacion getLcTiposIdentificacion() {
        return this.lcTiposIdentificacion;
    }
    
    public void setLcTiposIdentificacion(LcTiposIdentificacion lcTiposIdentificacion) {
        this.lcTiposIdentificacion = lcTiposIdentificacion;
    }
    public String getIdentificacion() {
        return this.identificacion;
    }
    
    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }
    public String getRazonSocial() {
        return this.razonSocial;
    }
    
    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }
    public String getDireccion() {
        return this.direccion;
    }
    
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    public String getTelefonos() {
        return this.telefonos;
    }
    
    public void setTelefonos(String telefonos) {
        this.telefonos = telefonos;
    }
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
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
    public String getTelfonos2() {
        return this.telfonos2;
    }
    
    public void setTelfonos2(String telfonos2) {
        this.telfonos2 = telfonos2;
    }
    public String getCelular() {
        return this.celular;
    }
    
    public void setCelular(String celular) {
        this.celular = celular;
    }
    public Integer getSucursal() {
        return this.sucursal;
    }
    
    public void setSucursal(Integer sucursal) {
        this.sucursal = sucursal;
    }
    public Set getLcDatosDeudoreses() {
        return this.lcDatosDeudoreses;
    }
    
    public void setLcDatosDeudoreses(Set lcDatosDeudoreses) {
        this.lcDatosDeudoreses = lcDatosDeudoreses;
    }
    public Set getLcZonases() {
        return this.lcZonases;
    }
    
    public void setLcZonases(Set lcZonases) {
        this.lcZonases = lcZonases;
    }
    public Set getLcModuloRols() {
        return this.lcModuloRols;
    }
    
    public void setLcModuloRols(Set lcModuloRols) {
        this.lcModuloRols = lcModuloRols;
    }
    public Set getLcClienteses() {
        return this.lcClienteses;
    }
    
    public void setLcClienteses(Set lcClienteses) {
        this.lcClienteses = lcClienteses;
    }
    public Set getLcRoleses() {
        return this.lcRoleses;
    }
    
    public void setLcRoleses(Set lcRoleses) {
        this.lcRoleses = lcRoleses;
    }
    public Set getLcDeudors() {
        return this.lcDeudors;
    }
    
    public void setLcDeudors(Set lcDeudors) {
        this.lcDeudors = lcDeudors;
    }
    public Set getLcCargoses() {
        return this.lcCargoses;
    }
    
    public void setLcCargoses(Set lcCargoses) {
        this.lcCargoses = lcCargoses;
    }
    public Set getLcEmpleadoses() {
        return this.lcEmpleadoses;
    }
    
    public void setLcEmpleadoses(Set lcEmpleadoses) {
        this.lcEmpleadoses = lcEmpleadoses;
    }
    public Set getLcUsuarioses() {
        return this.lcUsuarioses;
    }
    
    public void setLcUsuarioses(Set lcUsuarioses) {
        this.lcUsuarioses = lcUsuarioses;
    }
    public Set getLcRecordatorioses() {
        return this.lcRecordatorioses;
    }
    
    public void setLcRecordatorioses(Set lcRecordatorioses) {
        this.lcRecordatorioses = lcRecordatorioses;
    }
    public Set getLcAgencias() {
        return this.lcAgencias;
    }
    
    public void setLcAgencias(Set lcAgencias) {
        this.lcAgencias = lcAgencias;
    }




}


