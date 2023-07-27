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
public class Horario
{
    private int IdHorario;
    private static int contIdHorario;
    private String hora;
    private String dia;

    public Horario(){}
    
    public Horario(String hora, String dia) 
    {
        contIdHorario++;
        IdHorario=contIdHorario;
        this.hora = hora;
        this.dia = dia;
    }

    public int getIdHorario() {
        return IdHorario;
    }

    public void setIdHorario(int IdHorario) {
        this.IdHorario = IdHorario;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    @Override
    public String toString() {
        return "Horario{" + "hora=" + hora + ", dia=" + dia + '}';
    }
    
    
}
