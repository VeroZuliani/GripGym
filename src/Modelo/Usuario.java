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
public class Usuario extends Persona
{
    private int IdUsuario;
    private static int contIdUsuario;
    private int IdPersona;
    private String contraseña;

    public Usuario(String nombre, String apellido, Date fechaNac, int edad, String sexo, int dni, String domicilio, int IdBarrio, int IdLocalidad, int codPostal, int tel, int cel, String eMail, String foto,int IdPersona, String contraseña) 
    {
        super(nombre, apellido, fechaNac, edad, sexo, dni, domicilio, IdBarrio, IdLocalidad, codPostal, tel, cel, eMail, foto);
        contIdUsuario++;
        IdUsuario=contIdUsuario;
        this.IdPersona = IdPersona;
        this.contraseña = contraseña;
    }
    
    
    public Usuario(String contraseña) 
    {   
        contIdUsuario++;
        IdUsuario=contIdUsuario;
        this.contraseña = contraseña;
    }

    public int getIdUsuario() {
        return IdUsuario;
    }

    public void setIdUsuario(int IdUsuario) {
        this.IdUsuario = IdUsuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    @Override
    public String toString() {
        return super.toString() + "Usuario{" + "IdPersona=" + IdPersona + ", contraseña=" + contraseña + '}';
    }
//    @Override
//    public String toString() {
//        return super.toString() + "Usuario{" + "contraseña=" + contraseña + '}';
//    }
   
    
}
