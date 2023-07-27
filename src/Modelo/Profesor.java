/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo;

import java.awt.Image;
import java.util.Date;

/**
 *
 * @author Vero
 */
public class Profesor extends Persona
{
    private int IdProfesor;
    private static int contIdProfesor;
    private int IdPersona;
    private String puesto;

    public Profesor()
    {}
    
    public Profesor(int IdPersona, String puesto, String nombre, String apellido, Date fechaNac, int edad, String sexo, int dni, String domicilio, int IdBarrio, int IdLocalidad, int codPostal, int tel, int cel, String eMail, String foto) 
    {
        super(nombre, apellido, fechaNac, edad, sexo, dni, domicilio, IdBarrio, IdLocalidad, codPostal, tel, cel, eMail, foto);
        contIdProfesor++;
        IdProfesor=contIdProfesor;
        this.IdPersona = IdPersona;
        this.puesto = puesto;
    }

    public Profesor(String puesto) 
    {
        contIdProfesor++;
        IdProfesor=contIdProfesor;
        this.puesto = puesto;
    }
    
      public Profesor(String puesto, String nombre, String apellido, Date fechaNac, int edad, String sexo, int dni, String domicilio, int IdBarrio, int IdLocalidad, int codPostal, int tel, int cel, String eMail, String foto) 
    {
        super(nombre, apellido, fechaNac, edad, sexo, dni, domicilio, IdBarrio, IdLocalidad, codPostal, tel, cel, eMail, foto);
        contIdProfesor++;
        IdProfesor=contIdProfesor;
        this.puesto = puesto;
    }

    public int getIdProfesor() {
        return IdProfesor;
    }

    public void setIdProfesor(int IdProfesor) {
        this.IdProfesor = IdProfesor;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    @Override
    public String toString() {
        return super.toString() + "Profesor{" + "IdPersona=" + IdPersona + ", puesto=" + puesto + '}';
    }

 
    
}
