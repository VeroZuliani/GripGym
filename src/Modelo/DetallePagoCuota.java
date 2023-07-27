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
public class DetallePagoCuota 
{
    private int IdDetallePagoCuota;
    private static int contIdDetallePagoCuota;
    private int NroPagoCuota;
    private String descripcion;
    private double importe;

    public DetallePagoCuota(int NroPagoCuota, String descripcion, double importe) 
    {
        contIdDetallePagoCuota++;
        IdDetallePagoCuota=contIdDetallePagoCuota;
        this.NroPagoCuota = NroPagoCuota;
        this.descripcion = descripcion;
        this.importe = importe;
    }

    public int getIdDetallePagoCuota() {
        return IdDetallePagoCuota;
    }

    public void setIdDetallePagoCuota(int IdDetallePagoCuota) {
        this.IdDetallePagoCuota = IdDetallePagoCuota;
    }

    public int getNroPagoCuota() {
        return NroPagoCuota;
    }

    public void setNroPagoCuota(int NroPagoCuota) {
        this.NroPagoCuota = NroPagoCuota;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    @Override
    public String toString() {
        return "DetallePagoCuota{" + "NroPagoCuota=" + NroPagoCuota + ", descripcion=" + descripcion + ", importe=" + importe + '}';
    }
    
    
    
    
}
