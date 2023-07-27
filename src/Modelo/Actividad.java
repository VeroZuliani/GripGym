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
public class Actividad 
{
    private int IdActividad;
    private static int contIdActividad;
    private String nombre;
    private int IdHorario;
    private int IdTipoActividad;
    private int IdProfesor;
    private int IdCliente;
   

    public Actividad(String nombre, int IdHorario, int IdTipoActividad, int IdProfesor, int IdCliente) 
    {
        contIdActividad++;
        IdActividad=contIdActividad;
        this.nombre = nombre;
        this.IdHorario = IdHorario;
        this.IdTipoActividad = IdTipoActividad;
        this.IdProfesor = IdProfesor;
        this.IdCliente = IdCliente;
    }

    
    
    public Actividad(String nombre) 
    {
        contIdActividad++;
        IdActividad=contIdActividad;
        this.nombre = nombre;
    }
    
   
    
    public int getIdActividad() {
        return IdActividad;
    }

    public void setIdActividad(int IdActividad) {
        this.IdActividad = IdActividad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getIdHorario() {
        return IdHorario;
    }

    public void setIdHorario(int IdHorario) {
        this.IdHorario = IdHorario;
    }

    public int getIdTipoActividad() {
        return IdTipoActividad;
    }

    public void setIdTipoActividad(int IdTipoActividad) {
        this.IdTipoActividad = IdTipoActividad;
    }

    public int getIdProfesor() {
        return IdProfesor;
    }

    public void setIdProfesor(int IdProfesor) {
        this.IdProfesor = IdProfesor;
    }

    public int getIdCliente() {
        return IdCliente;
    }

    public void setIdCliente(int IdCliente) {
        this.IdCliente = IdCliente;
    }

    @Override
    public String toString() {
        return "Actividad{" + "nombre=" + nombre + ", IdHorario=" + IdHorario + ", IdTipoActividad=" + IdTipoActividad + ", IdProfesor=" + IdProfesor + ", IdCliente=" + IdCliente + '}';
    }
    
    
    
    
}
