/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gripgym;

/**
 *
 * @author Vero
 */
public class GenerarNumero 
{
    private int dato;
    private int cont=1;
    private String num="";

    public void generar(int dato) {
        this.dato = dato;
           if((this.dato>=10000000) || (this.dato<100000000)) 
           {
               int can=this.dato;
               num = "" + can; 
           }
           if((this.dato>=1000000) || (this.dato<10000000)) 
           {
               int can=this.dato;
               num = "0" + can; 
           }
           if((this.dato>=100000) || (this.dato<1000000)) 
           {
               int can=this.dato;
               num = "00" + can; 
           }
           if((this.dato>=10000) || (this.dato<100000)) 
           {
               int can=this.dato;
               num = "000" + can; 
           }
           if((this.dato>=1000) || (this.dato<10000)) 
           {
               int can=this.dato;
               num = "0000" + can; 
           }
           if((this.dato>=100) || (this.dato<1000))
           {
               int can=this.dato;
               num = "00000" + can; 
           }
           if((this.dato>=9) || (this.dato<100)) 
           {
                int can=this.dato;
               num = "000000" + can; 
           }
           if (this.dato<9)
           {
               int can=this.dato;
               num = "0000000" + can; 
           }
          
    }

    public String serie()
    {
        return this.num;
    }
    
    
    
}
