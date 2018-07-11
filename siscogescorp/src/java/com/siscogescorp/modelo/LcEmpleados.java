package com.siscogescorp.modelo;
// Generated 24/05/2018 12:17:21 by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * LcEmpleados generated by hbm2java
 */
public class LcEmpleados  implements java.io.Serializable {


     private int idEmpleado;
     private LcAgencia lcAgencia;
     private LcCargos lcCargos;
     private LcEmpresa lcEmpresa;
     private LcEstadoCivil lcEstadoCivil;
     private LcGenero lcGenero;
     private LcTiposIdentificacion lcTiposIdentificacion;
     private String identificacion;
     private String nombres;
     private String apellidos;
     private String lugarNacimiento;
     private Date fechaNacimiento;
     private String email;
     private String telefonos;
     private String celular;
     private String direccionDomicilio;
     private String profesion;
     private Integer idJefeInmediato;
     private String observacion;
     private Date fechaCreacion;
     private Date fechaActualizacion;
     private String estado;
     private Set lcTransaccioneses = new HashSet(0);
     private Set lcGestioneses = new HashSet(0);
     private Set lcUsuarioses = new HashSet(0);
     private Set lcAsignaEmpleadosTmps = new HashSet(0);
     private Set lcDetCampaniases = new HashSet(0);
     private Set lcRecordatorioses = new HashSet(0);

    public LcEmpleados() {
    }
    public LcEmpleados(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }
	
    public LcEmpleados(int idEmpleado, LcEmpresa lcEmpresa) {
        this.idEmpleado = idEmpleado;
        this.lcEmpresa = lcEmpresa;
    }
    public LcEmpleados(int idEmpleado, LcAgencia lcAgencia, LcCargos lcCargos, LcEmpresa lcEmpresa, LcEstadoCivil lcEstadoCivil, LcGenero lcGenero, LcTiposIdentificacion lcTiposIdentificacion, String identificacion, String nombres, String apellidos, String lugarNacimiento, Date fechaNacimiento, String email, String telefonos, String celular, String direccionDomicilio, String profesion, Integer idJefeInmediato, String observacion, Date fechaCreacion, Date fechaActualizacion, String estado, Set lcTransaccioneses, Set lcGestioneses, Set lcUsuarioses, Set lcAsignaEmpleadosTmps, Set lcDetCampaniases, Set lcRecordatorioses) {
       this.idEmpleado = idEmpleado;
       this.lcAgencia = lcAgencia;
       this.lcCargos = lcCargos;
       this.lcEmpresa = lcEmpresa;
       this.lcEstadoCivil = lcEstadoCivil;
       this.lcGenero = lcGenero;
       this.lcTiposIdentificacion = lcTiposIdentificacion;
       this.identificacion = identificacion;
       this.nombres = nombres;
       this.apellidos = apellidos;
       this.lugarNacimiento = lugarNacimiento;
       this.fechaNacimiento = fechaNacimiento;
       this.email = email;
       this.telefonos = telefonos;
       this.celular = celular;
       this.direccionDomicilio = direccionDomicilio;
       this.profesion = profesion;
       this.idJefeInmediato = idJefeInmediato;
       this.observacion = observacion;
       this.fechaCreacion = fechaCreacion;
       this.fechaActualizacion = fechaActualizacion;
       this.estado = estado;
       this.lcTransaccioneses = lcTransaccioneses;
       this.lcGestioneses = lcGestioneses;
       this.lcUsuarioses = lcUsuarioses;
       this.lcAsignaEmpleadosTmps = lcAsignaEmpleadosTmps;
       this.lcDetCampaniases = lcDetCampaniases;
       this.lcRecordatorioses = lcRecordatorioses;
    }
   
    public int getIdEmpleado() {
        return this.idEmpleado;
    }
    
    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }
    public LcAgencia getLcAgencia() {
        return this.lcAgencia;
    }
    
    public void setLcAgencia(LcAgencia lcAgencia) {
        this.lcAgencia = lcAgencia;
    }
    public LcCargos getLcCargos() {
        return this.lcCargos;
    }
    
    public void setLcCargos(LcCargos lcCargos) {
        this.lcCargos = lcCargos;
    }
    public LcEmpresa getLcEmpresa() {
        return this.lcEmpresa;
    }
    
    public void setLcEmpresa(LcEmpresa lcEmpresa) {
        this.lcEmpresa = lcEmpresa;
    }
    public LcEstadoCivil getLcEstadoCivil() {
        return this.lcEstadoCivil;
    }
    
    public void setLcEstadoCivil(LcEstadoCivil lcEstadoCivil) {
        this.lcEstadoCivil = lcEstadoCivil;
    }
    public LcGenero getLcGenero() {
        return this.lcGenero;
    }
    
    public void setLcGenero(LcGenero lcGenero) {
        this.lcGenero = lcGenero;
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
    public String getNombres() {
        return this.nombres;
    }
    
    public void setNombres(String nombres) {
        this.nombres = nombres;
    }
    public String getApellidos() {
        return this.apellidos;
    }
    
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
    public String getLugarNacimiento() {
        return this.lugarNacimiento;
    }
    
    public void setLugarNacimiento(String lugarNacimiento) {
        this.lugarNacimiento = lugarNacimiento;
    }
    public Date getFechaNacimiento() {
        return this.fechaNacimiento;
    }
    
    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    public String getTelefonos() {
        return this.telefonos;
    }
    
    public void setTelefonos(String telefonos) {
        this.telefonos = telefonos;
    }
    public String getCelular() {
        return this.celular;
    }
    
    public void setCelular(String celular) {
        this.celular = celular;
    }
    public String getDireccionDomicilio() {
        return this.direccionDomicilio;
    }
    
    public void setDireccionDomicilio(String direccionDomicilio) {
        this.direccionDomicilio = direccionDomicilio;
    }
    public String getProfesion() {
        return this.profesion;
    }
    
    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }
    public Integer getIdJefeInmediato() {
        return this.idJefeInmediato;
    }
    
    public void setIdJefeInmediato(Integer idJefeInmediato) {
        this.idJefeInmediato = idJefeInmediato;
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
    public Set getLcTransaccioneses() {
        return this.lcTransaccioneses;
    }
    
    public void setLcTransaccioneses(Set lcTransaccioneses) {
        this.lcTransaccioneses = lcTransaccioneses;
    }
    public Set getLcGestioneses() {
        return this.lcGestioneses;
    }
    
    public void setLcGestioneses(Set lcGestioneses) {
        this.lcGestioneses = lcGestioneses;
    }
    public Set getLcUsuarioses() {
        return this.lcUsuarioses;
    }
    
    public void setLcUsuarioses(Set lcUsuarioses) {
        this.lcUsuarioses = lcUsuarioses;
    }
    public Set getLcAsignaEmpleadosTmps() {
        return this.lcAsignaEmpleadosTmps;
    }
    
    public void setLcAsignaEmpleadosTmps(Set lcAsignaEmpleadosTmps) {
        this.lcAsignaEmpleadosTmps = lcAsignaEmpleadosTmps;
    }
    public Set getLcDetCampaniases() {
        return this.lcDetCampaniases;
    }
    
    public void setLcDetCampaniases(Set lcDetCampaniases) {
        this.lcDetCampaniases = lcDetCampaniases;
    }
    public Set getLcRecordatorioses() {
        return this.lcRecordatorioses;
    }
    
    public void setLcRecordatorioses(Set lcRecordatorioses) {
        this.lcRecordatorioses = lcRecordatorioses;
    }




}


