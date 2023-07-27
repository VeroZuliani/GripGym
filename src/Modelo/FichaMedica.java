/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo;

/**
 *
 * @author Vero
 */
public class FichaMedica 
{
    private int IdFichaMedica;
    private int IdCliente;
    private static int contIdCliente;
    private String grupoSanguineo;
    private int telefonoEmergencia;
    private String mutual;
    private String enfermedad;
    private String observacion;

    public FichaMedica()
    {}
    
    public FichaMedica(int IdFichaMedica, String grupoSanguineo, int telefonoEmergencia, String mutual, String enfermedad, String observacion) 
    {
        contIdCliente++;
        IdCliente=contIdCliente;
        this.IdFichaMedica = IdFichaMedica;
        this.grupoSanguineo = grupoSanguineo;
        this.telefonoEmergencia = telefonoEmergencia;
        this.mutual = mutual;
        this.enfermedad = enfermedad;
        this.observacion = observacion;
    }

    public int getIdFichaMedica() {
        return IdFichaMedica;
    }

    public void setIdFichaMedica(int IdFichaMedica) {
        this.IdFichaMedica = IdFichaMedica;
    }

    public int getIdCliente() {
        return IdCliente;
    }

    public void setIdCliente(int IdCliente) {
        this.IdCliente = IdCliente;
    }

    public String getGrupoSanguineo() {
        return grupoSanguineo;
    }

    public void setGrupoSanguineo(String grupoSanguineo) {
        this.grupoSanguineo = grupoSanguineo;
    }

    public int getTelefonoEmergencia() {
        return telefonoEmergencia;
    }

    public void setTelefonoEmergencia(int telefonoEmergencia) {
        this.telefonoEmergencia = telefonoEmergencia;
    }

    public String getMutual() {
        return mutual;
    }

    public void setMutual(String mutual) {
        this.mutual = mutual;
    }

    public String getEnfermedad() {
        return enfermedad;
    }

    public void setEnfermedad(String enfermedad) {
        this.enfermedad = enfermedad;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    @Override
    public String toString() {
        return "FichaMedica{" + "IdFichaMedica=" + IdFichaMedica + ", grupoSanguineo=" + grupoSanguineo + ", telefonoEmergencia=" + telefonoEmergencia + ", mutual=" + mutual + ", enfermedad=" + enfermedad + ", observacion=" + observacion + '}';
    }
    
  
    
}
