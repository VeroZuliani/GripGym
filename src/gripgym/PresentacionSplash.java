/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gripgym;

import com.sun.awt.AWTUtilities;
import javax.swing.*;

/**
 *
 * @author Vero
 */
public class PresentacionSplash extends javax.swing.JFrame {

    private PresentacionSplash splashFrame=this;
    
    /**
     * Creates new form PresentacionSplash
     */
    public PresentacionSplash() {
        initComponents();
        
        AWTUtilities.setWindowOpacity(splashFrame, new Float(1));
        
        setLocationRelativeTo(null); //Centralizamos la ventana.
        
        //Creamos metodo Hilo.
        startThread();
    
    }
    
    
     public void startThread()
    {
        Thread thread=new Thread(new Runnable() 
        {

            @Override
            public void run() 
            {
                  //Declaro objeto de menu. 
                  //Asignamos la variable splashFrame, que contiene este Frame;
                  //para que despues lo mande a logueo.
                  Logueo aplicationFrame=new Logueo(splashFrame);
                  
                  //Centramos el Frame.
                  aplicationFrame.setLocationRelativeTo(null);
                  
                  //Hago que se vea visible.
                  aplicationFrame.setVisible(true);
                  
                  //Ocultamos la animacion.
                  dispose();
            }
        });
                 //Iniciamos o le damos comienzo al hilo.
                 thread.start();
      }
    
   
    
    //Creamos metodos para el progreso y el label que cambie.
    
    public JProgressBar getProgressBar()
    {
        return this.BarraProgreso;
    }
    
     public JLabel getLabel()
    {
        return this.lblCargando;
    }
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblCargando = new javax.swing.JLabel();
        BarraProgreso = new javax.swing.JProgressBar();
        lblImagenPresentacion = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(883, 476));
        setResizable(false);
        getContentPane().setLayout(null);

        lblCargando.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        lblCargando.setForeground(new java.awt.Color(255, 0, 102));
        lblCargando.setText("  Cargando ... ");
        getContentPane().add(lblCargando);
        lblCargando.setBounds(0, 440, 350, 20);

        BarraProgreso.setPreferredSize(new java.awt.Dimension(146, 18));
        getContentPane().add(BarraProgreso);
        BarraProgreso.setBounds(-10, 460, 900, 18);

        lblImagenPresentacion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/GG.jpg"))); // NOI18N
        getContentPane().add(lblImagenPresentacion);
        lblImagenPresentacion.setBounds(0, 0, 883, 474);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PresentacionSplash.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PresentacionSplash.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PresentacionSplash.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PresentacionSplash.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PresentacionSplash().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JProgressBar BarraProgreso;
    private javax.swing.JLabel lblCargando;
    private javax.swing.JLabel lblImagenPresentacion;
    // End of variables declaration//GEN-END:variables
}
