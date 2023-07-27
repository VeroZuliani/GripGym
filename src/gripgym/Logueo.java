/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gripgym;

import java.sql.*;
import Controlador.*;
import javax.swing.JOptionPane;

/**
 *
 * @author Vero
 */
public class Logueo extends javax.swing.JFrame {

    Statement st;
    Connection con;
    ResultSet rs;
    
     private PresentacionSplash splashFrame;
   
    /**
     * Creates new form Logueo
     */
      //Metodo para asignar un valor y texto a nuestro splash.
    public Logueo(PresentacionSplash splashFrame)
    {
        this.splashFrame=splashFrame;
        setProgress(0, "Cargando...");
        initComponents();
        setProgress(100, "Bienvenido al Sistema");
        this.setLocationRelativeTo(null);
        
    }
     
     
     
     
    public Logueo() {
        initComponents();
        
        setLocationRelativeTo(null); //Centralizamos la ventana.
        this.setTitle("LOGUEO");
        
        ConexionJDBC.getInstance().AbrirConexion();
    }

    
     private void setProgress(int porcentaje,String informacion)
    {
        splashFrame.getLabel().setText(informacion);
        splashFrame.getProgressBar().setValue(porcentaje);
        
        try 
        {
//          Thread.sleep(1000);
            Thread.sleep(2000);
        } 
        catch (InterruptedException e) 
        {
            JOptionPane.showMessageDialog(null,"No se pudo ejecutar la presentacion");
        }
          
    }
    
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        btnIngresar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        lblContraseña = new javax.swing.JLabel();
        txtContraseña = new javax.swing.JPasswordField();
        txtUsuario = new javax.swing.JTextField();
        lblUsuario = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(966, 508));
        setResizable(false);
        getContentPane().setLayout(null);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel1.setText("ACCESO DE USUARIO");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(280, 70, 390, 42);

        btnIngresar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/ingresar-32.png"))); // NOI18N
        btnIngresar.setText("Ingresar");
        btnIngresar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnIngresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIngresarActionPerformed(evt);
            }
        });
        getContentPane().add(btnIngresar);
        btnIngresar.setBounds(340, 350, 110, 32);

        btnSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/cerrar-32.png"))); // NOI18N
        btnSalir.setText("Salir");
        btnSalir.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
        getContentPane().add(btnSalir);
        btnSalir.setBounds(500, 350, 110, 32);

        lblContraseña.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblContraseña.setText("Contraseña:");
        getContentPane().add(lblContraseña);
        lblContraseña.setBounds(310, 260, 90, 17);

        txtContraseña.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtContraseña.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        getContentPane().add(txtContraseña);
        txtContraseña.setBounds(430, 260, 182, 30);

        txtUsuario.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtUsuario.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        getContentPane().add(txtUsuario);
        txtUsuario.setBounds(430, 210, 182, 30);

        lblUsuario.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblUsuario.setText("Usuario:");
        getContentPane().add(lblUsuario);
        lblUsuario.setBounds(310, 210, 60, 17);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/CaminoMasChico.jpg"))); // NOI18N
        getContentPane().add(jLabel2);
        jLabel2.setBounds(0, 0, 960, 480);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnIngresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIngresarActionPerformed
       
        String usuario= this.txtUsuario.getText();
        String contraseña = this.txtContraseña.getText();
        
        if((usuario.isEmpty() && contraseña.isEmpty()))
        {
            JOptionPane.showMessageDialog(null, "¡Ingrese usuario y contraseña!");
        }
        else
        {
            ConexionJDBC.getInstance().AccesoUsuario(usuario, contraseña);
        }
 
        
    }//GEN-LAST:event_btnIngresarActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        
       int resp = JOptionPane.showConfirmDialog(null, "¿Seguro que desea salir?", "Salir", JOptionPane.YES_NO_OPTION);
        if (resp == JOptionPane.YES_OPTION) 
            {dispose();}
        
    }//GEN-LAST:event_btnSalirActionPerformed

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
            java.util.logging.Logger.getLogger(Logueo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Logueo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Logueo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Logueo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Logueo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnIngresar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel lblContraseña;
    private javax.swing.JLabel lblUsuario;
    public static javax.swing.JPasswordField txtContraseña;
    public static javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
