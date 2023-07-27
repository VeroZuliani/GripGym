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
public class PagoCuota 
{
    private int NroPagoCuota;
    private static int contNroPagoCuota;
    private Date fechaDesde;
    private Date fechaHasta;
    private int IdCliente;
    private double importe;
    private String observacion;
    private String estadoCuenta;

    public PagoCuota(Date fechaDesde, Date fechaHasta, int IdCliente, double importe, String observacion, String estadoCuenta) 
    {
        contNroPagoCuota++;
        NroPagoCuota=contNroPagoCuota;
        this.fechaDesde = fechaDesde;
        this.fechaHasta = fechaHasta;
        this.IdCliente = IdCliente;
        this.importe = importe;
        this.observacion = observacion;
        this.estadoCuenta = estadoCuenta;
    }

    
     public PagoCuota(Date fechaDesde, Date fechaHasta,double importe, String observacion, String estadoCuenta) 
    {
        contNroPagoCuota++;
        NroPagoCuota=contNroPagoCuota;
        this.fechaDesde = fechaDesde;
        this.fechaHasta = fechaHasta;
        this.importe = importe;
        this.observacion = observacion;
        this.estadoCuenta = estadoCuenta;
    }
    
    public int getNroPagoCuota() {
        return NroPagoCuota;
    }

    public void setNroPagoCuota(int NroPagoCuota) {
        this.NroPagoCuota = NroPagoCuota;
    }

    public Date getFechaDesde() {
        return fechaDesde;
    }

    public void setFechaDesde(Date fechaDesde) {
        this.fechaDesde = fechaDesde;
    }

    public Date getFechaHasta() {
        return fechaHasta;
    }

    public void setFechaHasta(Date fechaHasta) {
        this.fechaHasta = fechaHasta;
    }

    public int getIdCliente() {
        return IdCliente;
    }

    public void setIdCliente(int IdCliente) {
        this.IdCliente = IdCliente;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getEstadoCuenta() {
        return estadoCuenta;
    }

    public void setEstadoCuenta(String estadoCuenta) {
        this.estadoCuenta = estadoCuenta;
    }

    @Override
    public String toString() {
        return "PagoCuota{" + "fechaDesde=" + fechaDesde + ", fechaHasta=" + fechaHasta + ", IdCliente=" + IdCliente + ", importe=" + importe + ", observacion=" + observacion + ", estadoCuenta=" + estadoCuenta + '}';
    }

       
}
