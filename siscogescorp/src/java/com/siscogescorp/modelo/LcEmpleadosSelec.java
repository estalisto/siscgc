package com.siscogescorp.modelo;
// Generated 24/05/2018 12:17:21 by Hibernate Tools 4.3.1



/**
 * LcEmpleadosSelec generated by hbm2java
 */
public class LcEmpleadosSelec  implements java.io.Serializable {


     private int idEmpSelec;
     private Integer secuencial;
     private Integer idEmpleado;
     private String despachado;
     private String asignado;

    public LcEmpleadosSelec() {
    }

	
    public LcEmpleadosSelec(int idEmpSelec) {
        this.idEmpSelec = idEmpSelec;
    }
    public LcEmpleadosSelec(int idEmpSelec, Integer secuencial, Integer idEmpleado, String despachado, String asignado) {
       this.idEmpSelec = idEmpSelec;
       this.secuencial = secuencial;
       this.idEmpleado = idEmpleado;
       this.despachado = despachado;
       this.asignado = asignado;
    }
   
    public int getIdEmpSelec() {
        return this.idEmpSelec;
    }
    
    public void setIdEmpSelec(int idEmpSelec) {
        this.idEmpSelec = idEmpSelec;
    }
    public Integer getSecuencial() {
        return this.secuencial;
    }
    
    public void setSecuencial(Integer secuencial) {
        this.secuencial = secuencial;
    }
    public Integer getIdEmpleado() {
        return this.idEmpleado;
    }
    
    public void setIdEmpleado(Integer idEmpleado) {
        this.idEmpleado = idEmpleado;
    }
    public String getDespachado() {
        return this.despachado;
    }
    
    public void setDespachado(String despachado) {
        this.despachado = despachado;
    }
    public String getAsignado() {
        return this.asignado;
    }
    
    public void setAsignado(String asignado) {
        this.asignado = asignado;
    }




}


