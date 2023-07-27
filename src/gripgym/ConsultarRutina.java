/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gripgym;

import Controlador.ConexionJDBC;
import Modelo.*;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Vero
 */
public class ConsultarRutina extends javax.swing.JFrame {

    
    public static String nomC;
    public static String nomR;
    public static String rep;
    public static String serie;
    public static String dia;
    
    /**
     * Creates new form ConsultarRutina
     */
    public ConsultarRutina() {
        initComponents();
        
        
        setLocationRelativeTo(null); //Centralizamos la ventana.
        this.setTitle("CONSULTAR RUTINA");
        
         //Color JFrame
        this.getContentPane().setBackground(new java.awt.Color(102,102,102));
        //Color JPanel
        jPanel1.setBackground(new java.awt.Color(102,102,102));
        
        ConexionJDBC.getInstance().AbrirConexion();
        this.CargarTabla();
        
    }

    
     private void CargarTabla() {

        DefaultTableModel tabla = new DefaultTableModel();
        try {
            tabla.addColumn("Nombre");
            tabla.addColumn("Repetición");
            tabla.addColumn("Serie");
            tabla.addColumn("Día");
            tabla.addColumn("Cliente");
            tabla.addColumn("D.N.I");
           
            ResultSet rs = ConexionJDBC.getInstance().getCargarRutina();

            while (rs.next()) {
                Object dato[] = new Object[6];
                for (int i = 0; i < 6; i++) {
                    dato[i] = rs.getString(i + 1);

                }
                tabla.addRow(dato);
            }
            this.TablaRutina.setModel(tabla);

        } catch (Exception e) {
        }
    }
    
    
    
    
      public void CargarTablaRutinaPorApellidoCliente() 
     { //Paso el modelo vacio para q se resetee
        DefaultTableModel dtm=new DefaultTableModel();
        this.TablaRutina.setModel(dtm);

        //Manejo de la tabla.
        String titColumna[];
        titColumna=new String[6];
	titColumna[0]="Nombre";
	titColumna[1]="Repetición";
        titColumna[2]="Serie";
        titColumna[3]="Día";
        titColumna[4]="Cliente";  
        titColumna[5]="Dni";

        String apellidoCliente=this.txtDniApellidoCliente.getText();
        ArrayList lista =ConexionJDBC.getInstance().RutinaPorAPELLIDO(apellidoCliente);
        
        Object[][] data = new String[lista.size()][6];
        try
        {
        Iterator iter=lista.iterator();
        Rutina R=null;
   
        int indice=0;

        while(iter.hasNext())
        {    R=(Rutina)iter.next();
             data[indice][0]= R.getNombre();
             data[indice][1]= String.valueOf(R.getRepeticion());
             data[indice][2]= String.valueOf(R.getSerie());
             data[indice][3]= R.getDia();
             data[indice][4]= R.getNomCliente();
             data[indice][5]= String.valueOf(R.getDniCliente());
            
             indice++;
        }//fin del ciclo..
        dtm=new DefaultTableModel(data,titColumna);
        this.TablaRutina.setModel(dtm);
        } catch(Exception e)
          {System.out.println("Error al cargar la tabla!!!" + e.getMessage());}

    }
    
    
    
     public void CargarTablaRutinaPorDniCliente() 
     { //Paso el modelo vacio para q se resetee
        DefaultTableModel dtm=new DefaultTableModel();
        this.TablaRutina.setModel(dtm);

        //Manejo de la tabla.
        String titColumna[];
        titColumna=new String[6];
	titColumna[0]="Nombre";
	titColumna[1]="Repetición";
        titColumna[2]="Serie";
        titColumna[3]="Día";
        titColumna[4]="Cliente";  
        titColumna[5]="Dni";

        int dni=Integer.parseInt(this.txtDniApellidoCliente.getText());
        ArrayList lista =ConexionJDBC.getInstance().RutinaPorDNI(dni);
        
        Object[][] data = new String[lista.size()][6];
        try
        {
        Iterator iter=lista.iterator();
        Rutina R=null;
   
        int indice=0;

        while(iter.hasNext())
        {    R=(Rutina)iter.next();
             data[indice][0]= R.getNombre();
             data[indice][1]= String.valueOf(R.getRepeticion());
             data[indice][2]= String.valueOf(R.getSerie());
             data[indice][3]= R.getDia();
             data[indice][4]= R.getNomCliente();
             data[indice][5]= String.valueOf(R.getDniCliente());
            
             indice++;
        }//fin del ciclo..
        dtm=new DefaultTableModel(data,titColumna);
        this.TablaRutina.setModel(dtm);
        } catch(Exception e)
          {System.out.println("Error al cargar la tabla!!!" + e.getMessage());}

    }
    
    
    
    
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        btnBuscar = new javax.swing.JButton();
        txtDniApellidoCliente = new javax.swing.JTextField();
        rbtDni = new javax.swing.JRadioButton();
        rbtApellidoC = new javax.swing.JRadioButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaRutina = new javax.swing.JTable();
        btnCerrar = new javax.swing.JButton();
        btnAceptar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Buscar por:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(255, 255, 255))); // NOI18N

        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Buscar2.png"))); // NOI18N
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        buttonGroup1.add(rbtDni);
        rbtDni.setForeground(new java.awt.Color(255, 255, 255));
        rbtDni.setText("D.N.I:");

        buttonGroup1.add(rbtApellidoC);
        rbtApellidoC.setForeground(new java.awt.Color(255, 255, 255));
        rbtApellidoC.setText("Apellido Cliente:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(171, 171, 171)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(rbtDni)
                        .addGap(295, 295, 295))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(rbtApellidoC)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtDniApellidoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)))
                .addComponent(btnBuscar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(rbtDni)
                        .addGap(18, 18, 18)
                        .addComponent(rbtApellidoC))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnBuscar)
                            .addComponent(txtDniApellidoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        TablaRutina.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        TablaRutina.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TablaRutinaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TablaRutina);

        btnCerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/logout-24.png"))); // NOI18N
        btnCerrar.setText("Cerrar");
        btnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarActionPerformed(evt);
            }
        });

        btnAceptar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/ok-24-24.png"))); // NOI18N
        btnAceptar.setText("Aceptar");
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnAceptar)
                        .addGap(46, 46, 46)
                        .addComponent(btnCerrar))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 862, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCerrar)
                    .addComponent(btnAceptar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
       dispose();
    }//GEN-LAST:event_btnCerrarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
       String dniapellidoC = this.txtDniApellidoCliente.getText();
       
       if(this.rbtDni.isSelected()==true)
        {
            ConexionJDBC.getInstance().buscarRutinaDni(dniapellidoC);
            this.CargarTablaRutinaPorDniCliente();
            this.txtDniApellidoCliente.setText("");
        }
        else if(this.rbtApellidoC.isSelected()==true)
        {
            ConexionJDBC.getInstance().buscarRutinaApe(dniapellidoC);
            this.CargarTablaRutinaPorApellidoCliente();
            this.txtDniApellidoCliente.setText("");
        }
       
       
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void TablaRutinaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TablaRutinaMouseClicked
       
         if(this.rbtDni.isSelected()==true)
        {
           for (int i = 0; i < this.TablaRutina.getRowCount(); i++) 
            {
                String dni = "";
               
                dni = (String) this.TablaRutina.getValueAt(this.TablaRutina.getSelectedRow(), 5);

                this.txtDniApellidoCliente.setText(dni);
            }
        }
        else if(this.rbtApellidoC.isSelected()==true)
        {
            for (int i = 0; i < this.TablaRutina.getRowCount(); i++) 
            {
                String ape = "";
               
                ape = (String) this.TablaRutina.getValueAt(this.TablaRutina.getSelectedRow(), 4);

                this.txtDniApellidoCliente.setText(ape);
            }
        }
        
         
    }//GEN-LAST:event_TablaRutinaMouseClicked

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
       
//        hora=this.txtHora.getText();
//        dia=this.txtDia.getText();
//        
//        RegistrarActividad.txtHora.setText(hora);
//        RegistrarActividad.txtDia.setText(dia);
        
        
      
//        if(this.rbtApellidoC.isSelected()==true)
//        {
            for (int i = 0; i < this.TablaRutina.getRowCount(); i++) 
            {
                String nomC = "",nomR = "",rep = "",serie = "",dia = "";
               
                nomC = (String) this.TablaRutina.getValueAt(this.TablaRutina.getSelectedRow(), 4);
                nomR = (String) this.TablaRutina.getValueAt(this.TablaRutina.getSelectedRow(), 0);
                rep = (String) this.TablaRutina.getValueAt(this.TablaRutina.getSelectedRow(), 1);
                serie = (String) this.TablaRutina.getValueAt(this.TablaRutina.getSelectedRow(), 2);
                dia = (String) this.TablaRutina.getValueAt(this.TablaRutina.getSelectedRow(), 3);
                
               RegistrarRutina.txtCliente.setText(nomC);
               RegistrarRutina.txtNombre.setText(nomR);
               RegistrarRutina.txtRepeticion.setText(rep);
               RegistrarRutina.txtSerie.setText(serie);
               RegistrarRutina.txtDia.setText(dia);
               
            }
//        }
        
        
        dispose(); //Cerrar ventana.
        
        
        
    }//GEN-LAST:event_btnAceptarActionPerformed

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
            java.util.logging.Logger.getLogger(ConsultarRutina.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ConsultarRutina.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ConsultarRutina.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ConsultarRutina.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ConsultarRutina().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TablaRutina;
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCerrar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton rbtApellidoC;
    private javax.swing.JRadioButton rbtDni;
    private javax.swing.JTextField txtDniApellidoCliente;
    // End of variables declaration//GEN-END:variables
}
