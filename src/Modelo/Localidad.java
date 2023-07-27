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
public class Localidad 
{
    private int IdLocalidad;
    private static int contIdLocalidad;
    private String localidad;
  
    
    public Localidad()
    {}

    public Localidad(String local) 
    {
        contIdLocalidad++;
        IdLocalidad=contIdLocalidad;
        localidad = local;
    }

    public int getIdLocalidad() 
    {return IdLocalidad;}

    public void setIdLocalidad(int IdLocal)
    {IdLocalidad = IdLocal;}

    public String getLocalidad() 
    {return localidad;}

    public void setLocalidad(String local) 
    {localidad = local;}

   
    public String toString() 
    {
        return localidad;
    }
}
