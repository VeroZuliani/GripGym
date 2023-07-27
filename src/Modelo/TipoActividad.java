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
public class TipoActividad 
{
    private int IdTipoActividad;
    private static int contIdTipoActividad;
    private String tipoActividad;
    
    
    public TipoActividad()
    {}

    public TipoActividad(String tipoActividad) 
    {
        contIdTipoActividad++;
        IdTipoActividad=contIdTipoActividad;
        this.tipoActividad = tipoActividad;
    }

    public int getIdTipoActividad() {
        return IdTipoActividad;
    }

    public void setIdTipoActividad(int IdTipoActividad) {
        this.IdTipoActividad = IdTipoActividad;
    }

    public String getTipoActividad() {
        return tipoActividad;
    }

    public void setTipoActividad(String tipoActividad) {
        this.tipoActividad = tipoActividad;
    }

    @Override
    public String toString() {
        return tipoActividad ;
    }
    
    
   
}
