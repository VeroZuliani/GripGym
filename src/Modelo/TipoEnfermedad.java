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
public class TipoEnfermedad 
{
    private int IdTipoEnfermedad;
    private static int contIdTipoEnfermedad;
    private String tipoEnfermedad;

    public TipoEnfermedad(String tipoEnfermedad)
    {
        contIdTipoEnfermedad++;
        IdTipoEnfermedad=contIdTipoEnfermedad;
        this.tipoEnfermedad = tipoEnfermedad;
    }

    public int getIdTipoEnfermedad() {
        return IdTipoEnfermedad;
    }

    public void setIdTipoEnfermedad(int IdTipoEnfermedad) {
        this.IdTipoEnfermedad = IdTipoEnfermedad;
    }

    public String getTipoEnfermedad() {
        return tipoEnfermedad;
    }

    public void setTipoEnfermedad(String tipoEnfermedad) {
        this.tipoEnfermedad = tipoEnfermedad;
    }

    @Override
    public String toString() {
        return "TipoEnfermedad{" + "tipoEnfermedad=" + tipoEnfermedad + '}';
    }
    
    
    
    
}
