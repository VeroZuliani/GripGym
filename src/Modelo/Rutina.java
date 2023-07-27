/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Modelo;
import java.util.Date;
/**
 *
 * @author Vero
 */
public class Rutina 
{
    private int IdRutina;
    private static int contIdRutina;
    private String nombre;
    private int repeticion;
    private int serie;
    private String dia;
    private int IdCliente;

    public Rutina(){}
    
    public Rutina(String nombre, int repeticion, int serie, String dia, int IdCliente)
    {
        contIdRutina++;
        IdRutina=contIdRutina;
        this.nombre = nombre;
        this.repeticion = repeticion;
        this.serie = serie;
        this.dia = dia;
        this.IdCliente = IdCliente;
    }

     public Rutina(String nombre, int repeticion, int serie, String dia)
    {
        contIdRutina++;
        IdRutina=contIdRutina;
        this.nombre = nombre;
        this.repeticion = repeticion;
        this.serie = serie;
        this.dia = dia;
    }
    
        public Rutina(String nombre, int repeticion, int serie, String dia, String nomC)
    {
        contIdRutina++;
        IdRutina=contIdRutina;
        this.nombre = nombre;
        this.repeticion = repeticion;
        this.serie = serie;
        this.dia = dia;
        this.nomC=nomC;
    }
     
    public int getIdRutina() {
        return IdRutina;
    }

    public void setIdRutina(int IdRutina) {
        this.IdRutina = IdRutina;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getRepeticion() {
        return repeticion;
    }

    public void setRepeticion(int repeticion) {
        this.repeticion = repeticion;
    }

    public int getSerie() {
        return serie;
    }

    public void setSerie(int serie) {
        this.serie = serie;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public int getIdCliente() {
        return IdCliente;
    }

    public void setIdCliente(int IdCliente) {
        this.IdCliente = IdCliente;
    }

    @Override
    public String toString() {
        return "Rutina{" + "nombre=" + nombre + ", repeticion=" + repeticion + ", serie=" + serie + ", dia=" + dia + ", IdCliente=" + IdCliente + '}';
    }
    
    
    
    
    private String nomC;
    
    public String getNomCliente() {return nomC;}
    public void setNomCliente(String nomC) {this.nomC=nomC;}
    
    
    private int dniC;
    
    public int getDniCliente() {return dniC;}
    public void setDniCliente(int dniC) {this.dniC=dniC;}
    
    
}
