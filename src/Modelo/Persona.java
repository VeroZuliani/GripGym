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
public class Persona 
{
    private int IdPersona;
    private static int contIdPersona; 
    private String nombre;
    private String apellido;
    private Date fechaNac;
    private int edad;
    private String sexo;
    private int dni;
    private String domicilio;
    private int IdBarrio;
    private int IdLocalidad;
    private int codPostal;
    private int tel;
    private int cel;
    private String eMail;
    private String foto;
    

    
    public Persona()
    {}

    public Persona(String nombre, String apellido, Date fechaNac, int edad, String sexo, int dni, String domicilio, int IdBarrio, int IdLocalidad, int codPostal, int tel, int cel, String eMail, String foto) 
    {
        contIdPersona++;
        IdPersona=contIdPersona;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNac = fechaNac;
        this.edad = edad;
        this.sexo = sexo;
        this.dni = dni;
        this.domicilio = domicilio;
        this.IdBarrio = IdBarrio;
        this.IdLocalidad = IdLocalidad;
        this.codPostal = codPostal;
        this.tel = tel;
        this.cel = cel;
        this.eMail = eMail;
        this.foto = foto;
    }
    
    

    public int getIdPersona() {
        return IdPersona;
    }

    public void setIdPersona(int IdPersona) {
        this.IdPersona = IdPersona;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Date getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(Date fechaNac) {
        this.fechaNac = fechaNac;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public int getIdBarrio() {
        return IdBarrio;
    }

    public void setIdBarrio(int IdBarrio) {
        this.IdBarrio = IdBarrio;
    }

    public int getIdLocalidad() {
        return IdLocalidad;
    }

    public void setIdLocalidad(int IdLocalidad) {
        this.IdLocalidad = IdLocalidad;
    }

    public int getCodPostal() {
        return codPostal;
    }

    public void setCodPostal(int codPostal) {
        this.codPostal = codPostal;
    }

    public int getTel() {
        return tel;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }

    public int getCel() {
        return cel;
    }

    public void setCel(int cel) {
        this.cel = cel;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

//    public Image getFoto() {
//        return foto;
//    }
//
//    public void setFoto(Image foto) {
//        this.foto = foto;
//    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
    
    @Override
    public String toString() {
        return "Persona{" + "IdPersona=" + IdPersona + ", nombre=" + nombre + ", apellido=" + apellido + ", fechaNac=" + fechaNac + ", edad=" + edad + ", sexo=" + sexo + ", dni=" + dni + ", domicilio=" + domicilio + ", IdBarrio=" + IdBarrio + ", IdLocalidad=" + IdLocalidad + ", codPostal=" + codPostal + ", tel=" + tel + ", cel=" + cel + ", eMail=" + eMail + ", foto=" + foto + '}';
    }

    
    
    
    
    
    
    //Metodos que devuelve el String cuando busco un cliente/profesor.
    private String barrio;
    private String localidad;
    
    public String getBarrio() {return barrio;}

    public void setBarrio(String barrio) {this.barrio=barrio;}

    public String getLocalidad() {return localidad;}

    public void setLocalidad(String localidad) {this.localidad=localidad;}
    
    
    
    
    
    
    
}
