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
public class Barrio 
{
    private int IdBarrio;
    private String barrio;
    private static int contIdBarrio; 
    
     public Barrio() 
    {}
     
     public Barrio(String ba) 
    {
        contIdBarrio++;
        IdBarrio=contIdBarrio;
        barrio = ba;
    }

    public int getIdBarrio() 
    {return IdBarrio;}

    public void setIdBarrio(int IdBa) 
    {IdBarrio = IdBa;}

    public String getBarrio() 
    {return barrio;}

    public void setBarrio(String ba) 
    {barrio = ba;}

    
    public String toString() 
    {
        return barrio;
    }
}
