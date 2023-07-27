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
public class Aparato 
{
   private int IdAparato;
   private static int contIdAparato;
   private String nombre;
   private String tipo;
   private String descripcion;
   private int IdRutina;

   public Aparato()
   {}
   
    public Aparato(String nombre, String tipo, String descripcion, int IdRutina) 
    {
        contIdAparato++;
        IdAparato=contIdAparato;
        this.nombre = nombre;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.IdRutina = IdRutina;
    }

       public Aparato(String nombre, String tipo, String descripcion) 
    {
        contIdAparato++;
        IdAparato=contIdAparato;
        this.nombre = nombre;
        this.tipo = tipo;
        this.descripcion = descripcion;
    }
    
    public int getIdAparato() {
        return IdAparato;
    }

    public void setIdAparato(int IdAparato) {
        this.IdAparato = IdAparato;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getIdRutina() {
        return IdRutina;
    }

    public void setIdRutina(int IdRutina) {
        this.IdRutina = IdRutina;
    }

    @Override
    public String toString() {
        return "Aparato{" + "nombre=" + nombre + ", tipo=" + tipo + ", descripcion=" + descripcion + ", IdRutina=" + IdRutina + '}';
    }
   
   
    private String nomR;
    
    public String getNomRutina() {return nomR;}

    public void setNomRutina(String nomR) {this.nomR=nomR;}
   
}
