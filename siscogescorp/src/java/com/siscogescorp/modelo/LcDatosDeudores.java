package com.siscogescorp.modelo;
// Generated 14-ene-2018 19:02:18 by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * LcDatosDeudores generated by hbm2java
 */
public class LcDatosDeudores  implements java.io.Serializable {


     private int idDatosDeudor;
     private LcAgencia lcAgencia;
     private LcCiudad lcCiudad;
     private LcEmpresa lcEmpresa;
     private LcEstatus lcEstatus;
     private LcTipoCredito lcTipoCredito;
     private Integer tipoIdentificacion;
     private String identificacion;
     private String nombres;
     private String apellidos;
     private String nombresCompleto;
     private String email;
     private Date fechaCreacion;
     private String estado;
     private Integer idParroquia;
     private Set lcDetCampaniases = new HashSet(0);
     private Set lcGestioneses = new HashSet(0);
     private Set lcArticulos = new HashSet(0);
     private Set lcRecaudacioneses = new HashSet(0);
     private Set lcRecordatorioses = new HashSet(0);
     private Set lcTransaccioneses = new HashSet(0);
     private Set lcCuotases = new HashSet(0);
     private Set lcDeudoresDocumentoses = new HashSet(0);
     private Set lcCompromisosPagos = new HashSet(0);

    public LcDatosDeudores() {
    }

	
    public LcDatosDeudores(int idDatosDeudor) {
        this.idDatosDeudor = idDatosDeudor;
    }
    public LcDatosDeudores(int idDatosDeudor, LcAgencia lcAgencia, LcCiudad lcCiudad, LcEmpresa lcEmpresa, LcEstatus lcEstatus, LcTipoCredito lcTipoCredito, Integer tipoIdentificacion, String identificacion, String nombres, String apellidos, String nombresCompleto, String email, Date fechaCreacion, String estado, Integer idParroquia, Set lcDetCampaniases, Set lcGestioneses, Set lcArticulos, Set lcRecaudacioneses, Set lcRecordatorioses, Set lcTransaccioneses, Set lcCuotases, Set lcDeudoresDocumentoses, Set lcCompromisosPagos) {
       this.idDatosDeudor = idDatosDeudor;
       this.lcAgencia = lcAgencia;
       this.lcCiudad = lcCiudad;
       this.lcEmpresa = lcEmpresa;
       this.lcEstatus = lcEstatus;
       this.lcTipoCredito = lcTipoCredito;
       this.tipoIdentificacion = tipoIdentificacion;
       this.identificacion = identificacion;
       this.nombres = nombres;
       this.apellidos = apellidos;
       this.nombresCompleto = nombresCompleto;
       this.email = email;
       this.fechaCreacion = fechaCreacion;
       this.estado = estado;
       this.idParroquia = idParroquia;
       this.lcDetCampaniases = lcDetCampaniases;
       this.lcGestioneses = lcGestioneses;
       this.lcArticulos = lcArticulos;
       this.lcRecaudacioneses = lcRecaudacioneses;
       this.lcRecordatorioses = lcRecordatorioses;
       this.lcTransaccioneses = lcTransaccioneses;
       this.lcCuotases = lcCuotases;
       this.lcDeudoresDocumentoses = lcDeudoresDocumentoses;
       this.lcCompromisosPagos = lcCompromisosPagos;
    }
   
    public int getIdDatosDeudor() {
        return this.idDatosDeudor;
    }
    
    public void setIdDatosDeudor(int idDatosDeudor) {
        this.idDatosDeudor = idDatosDeudor;
    }
    public LcAgencia getLcAgencia() {
        return this.lcAgencia;
    }
    
    public void setLcAgencia(LcAgencia lcAgencia) {
        this.lcAgencia = lcAgencia;
    }
    public LcCiudad getLcCiudad() {
        return this.lcCiudad;
    }
    
    public void setLcCiudad(LcCiudad lcCiudad) {
        this.lcCiudad = lcCiudad;
    }
    public LcEmpresa getLcEmpresa() {
        return this.lcEmpresa;
    }
    
    public void setLcEmpresa(LcEmpresa lcEmpresa) {
        this.lcEmpresa = lcEmpresa;
    }
    public LcEstatus getLcEstatus() {
        return this.lcEstatus;
    }
    
    public void setLcEstatus(LcEstatus lcEstatus) {
        this.lcEstatus = lcEstatus;
    }
    public LcTipoCredito getLcTipoCredito() {
        return this.lcTipoCredito;
    }
    
    public void setLcTipoCredito(LcTipoCredito lcTipoCredito) {
        this.lcTipoCredito = lcTipoCredito;
    }
    public Integer getTipoIdentificacion() {
        return this.tipoIdentificacion;
    }
    
    public void setTipoIdentificacion(Integer tipoIdentificacion) {
        this.tipoIdentificacion = tipoIdentificacion;
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
    public String getNombresCompleto() {
        return this.nombresCompleto;
    }
    
    public void setNombresCompleto(String nombresCompleto) {
        this.nombresCompleto = nombresCompleto;
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
    public String getEstado() {
        return this.estado;
    }
    
    public void setEstado(String estado) {
        this.estado = estado;
    }
    public Integer getIdParroquia() {
        return this.idParroquia;
    }
    
    public void setIdParroquia(Integer idParroquia) {
        this.idParroquia = idParroquia;
    }
    public Set getLcDetCampaniases() {
        return this.lcDetCampaniases;
    }
    
    public void setLcDetCampaniases(Set lcDetCampaniases) {
        this.lcDetCampaniases = lcDetCampaniases;
    }
    public Set getLcGestioneses() {
        return this.lcGestioneses;
    }
    
    public void setLcGestioneses(Set lcGestioneses) {
        this.lcGestioneses = lcGestioneses;
    }
    public Set getLcArticulos() {
        return this.lcArticulos;
    }
    
    public void setLcArticulos(Set lcArticulos) {
        this.lcArticulos = lcArticulos;
    }
    public Set getLcRecaudacioneses() {
        return this.lcRecaudacioneses;
    }
    
    public void setLcRecaudacioneses(Set lcRecaudacioneses) {
        this.lcRecaudacioneses = lcRecaudacioneses;
    }
    public Set getLcRecordatorioses() {
        return this.lcRecordatorioses;
    }
    
    public void setLcRecordatorioses(Set lcRecordatorioses) {
        this.lcRecordatorioses = lcRecordatorioses;
    }
    public Set getLcTransaccioneses() {
        return this.lcTransaccioneses;
    }
    
    public void setLcTransaccioneses(Set lcTransaccioneses) {
        this.lcTransaccioneses = lcTransaccioneses;
    }
    public Set getLcCuotases() {
        return this.lcCuotases;
    }
    
    public void setLcCuotases(Set lcCuotases) {
        this.lcCuotases = lcCuotases;
    }
    public Set getLcDeudoresDocumentoses() {
        return this.lcDeudoresDocumentoses;
    }
    
    public void setLcDeudoresDocumentoses(Set lcDeudoresDocumentoses) {
        this.lcDeudoresDocumentoses = lcDeudoresDocumentoses;
    }
    public Set getLcCompromisosPagos() {
        return this.lcCompromisosPagos;
    }
    
    public void setLcCompromisosPagos(Set lcCompromisosPagos) {
        this.lcCompromisosPagos = lcCompromisosPagos;
    }




}

