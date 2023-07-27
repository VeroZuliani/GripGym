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
public class Cliente extends Persona
{
    private int IdCliente;
    private static int contIdCliente;
    private int IdPersona;
    private Date fechaIngreso;
    
    public Cliente()
    {}

    
    public Cliente(int IdPersona, Date fechaIngreso, String nombre, String apellido, Date fechaNac, int edad, String sexo, int dni, String domicilio, int IdBarrio, int IdLocalidad, int codPostal, int tel, int cel, String eMail, String foto) 
    {
        super(nombre, apellido, fechaNac, edad, sexo, dni, domicilio, IdBarrio, IdLocalidad, codPostal, tel, cel, eMail, foto);
        contIdCliente++;
        IdCliente=contIdCliente;
        this.IdPersona = IdPersona;
        this.fechaIngreso = fechaIngreso;
    }

    public Cliente(Date fechaIngreso) 
    {
        contIdCliente++;
        IdCliente=contIdCliente;
        this.fechaIngreso = fechaIngreso;
    }

   
    public int getIdCliente() {
        return IdCliente;
    }

    public void setIdCliente(int IdCliente) {
        this.IdCliente = IdCliente;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    @Override
    public String toString() {
        return super.toString() + "Cliente{" + "IdPersona=" + IdPersona + ", fechaIngreso=" + fechaIngreso + '}';
    }

    
    
}
