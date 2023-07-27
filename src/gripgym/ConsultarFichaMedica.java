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
public class ConsultarFichaMedica extends javax.swing.JFrame {

    /**
     * Creates new form ConsultarFichaMedica
     */
    public ConsultarFichaMedica() {
        initComponents();
        
        setLocationRelativeTo(null); //Centralizamos la ventana.
        this.setTitle("CONSULTAR FICHA MEDICA");
     
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
            tabla.addColumn("Nro Ficha Médica");
            tabla.addColumn("D.N.I");
            tabla.addColumn("Cliente");
            tabla.addColumn("Grupo Sanguíneo");
            tabla.addColumn("Tel Emergencia");
            tabla.addColumn("Mutual");
            tabla.addColumn("Enfermedad");
            tabla.addColumn("Observación");
           
            ResultSet rs = ConexionJDBC.getInstance().getCargarFichaMedica();

            while (rs.next()) {
                Object dato[] = new Object[8];
                for (int i = 0; i < 8; i++) {
                    dato[i] = rs.getString(i + 1);

                }
                tabla.addRow(dato);
            }
            this.TablaFichaMedica.setModel(tabla);

        } catch (Exception e) {
        }
    }
    
    
        
        
        
    public void CargarTablaFichaMedicaPorAPELLIDO()
     { //Paso el modelo vacio para q se resetee
        DefaultTableModel dtm=new DefaultTableModel();
        this.TablaFichaMedica.setModel(dtm);

        //Manejo de la tabla.
        String titColumna[];
        titColumna=new String[8];
	titColumna[0]="Nro Ficha Médica";
	titColumna[1]="D.N.I";
        titColumna[2]="Cliente";
        titColumna[3]="GrupoSanguíneo";
        titColumna[4]="Tel Emergencia";
        titColumna[5]="Mutual";
        titColumna[6]="Enfermedad";
        titColumna[7]="Observación";
       
        String apellido=this.txtDniApellido.getText();
        ArrayList lista =ConexionJDBC.getInstance().FichaMedicaPorAPELLIDO(apellido);
        
        Object[][] data = new String[lista.size()][8];
        try
        {
        Iterator iter=lista.iterator();
        FichaMedica FM=null;
        Persona P=null;
        int indice=0;

        while(iter.hasNext())
        {    FM=(FichaMedica)iter.next();
             P=(Persona)iter.next();
             data[indice][0]= String.valueOf(FM.getIdFichaMedica());
             data[indice][1]= String.valueOf(P.getDni());
             data[indice][2]= P.getApellido();
             data[indice][3]= FM.getGrupoSanguineo();
             data[indice][4]= String.valueOf(FM.getTelefonoEmergencia());
             data[indice][5]= FM.getMutual();
             data[indice][6]= FM.getEnfermedad();
             data[indice][7]= FM.getObservacion();
             indice++;
        }//fin del ciclo..
        dtm=new DefaultTableModel(data,titColumna);
        this.TablaFichaMedica.setModel(dtm);
        } catch(Exception e)
          {System.out.println("Error al cargar la tabla!!!" + e.getMessage());}
    }
     
        
        
        
         
    public void CargarTablaFichaMedicaPorDNI()
     { //Paso el modelo vacio para q se resetee
        DefaultTableModel dtm=new DefaultTableModel();
        this.TablaFichaMedica.setModel(dtm);

        //Manejo de la tabla.
        String titColumna[];
        titColumna=new String[8];
	titColumna[0]="Nro Ficha Médica";
	titColumna[1]="D.N.I";
        titColumna[2]="Cliente";
        titColumna[3]="GrupoSanguíneo";
        titColumna[4]="Tel Emergencia";
        titColumna[5]="Mutual";
        titColumna[6]="Enfermedad";
        titColumna[7]="Observación";
       
        int dni=Integer.parseInt(this.txtDniApellido.getText());
        ArrayList lista =ConexionJDBC.getInstance().FichaMedicaPorDNI(dni);
        
        Object[][] data = new String[lista.size()][8];
        try
        {
        Iterator iter=lista.iterator();
        FichaMedica FM=null;
        Persona P=null;
        int indice=0;

        while(iter.hasNext())
        {    FM=(FichaMedica)iter.next();
             P=(Persona)iter.next();
             data[indice][0]= String.valueOf(FM.getIdFichaMedica());
             data[indice][1]= String.valueOf(P.getDni());
             data[indice][2]= P.getApellido();
             data[indice][3]= FM.getGrupoSanguineo();
             data[indice][4]= String.valueOf(FM.getTelefonoEmergencia());
             data[indice][5]= FM.getMutual();
             data[indice][6]= FM.getEnfermedad();
             data[indice][7]= FM.getObservacion();
             indice++;
        }//fin del ciclo..
        dtm=new DefaultTableModel(data,titColumna);
        this.TablaFichaMedica.setModel(dtm);
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
        txtDniApellido = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        rbtDni = new javax.swing.JRadioButton();
        rbtApellido = new javax.swing.JRadioButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaFichaMedica = new javax.swing.JTable();
        btnCerrar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

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

        buttonGroup1.add(rbtApellido);
        rbtApellido.setForeground(new java.awt.Color(255, 255, 255));
        rbtApellido.setText("Apellido:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(155, 155, 155)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rbtApellido)
                    .addComponent(rbtDni))
                .addGap(18, 18, 18)
                .addComponent(txtDniApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnBuscar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnBuscar)
                    .addComponent(txtDniApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(rbtDni)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(rbtApellido)
                .addContainerGap())
        );

        TablaFichaMedica.setModel(new javax.swing.table.DefaultTableModel(
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
        TablaFichaMedica.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TablaFichaMedicaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TablaFichaMedica);

        btnCerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/logout-24.png"))); // NOI18N
        btnCerrar.setText("Cerrar");
        btnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 804, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnCerrar)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addComponent(btnCerrar)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        dispose();
    }//GEN-LAST:event_btnCerrarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
       
        String dniapellido = this.txtDniApellido.getText();
       
        if(this.rbtDni.isSelected()==true)
        {
            ConexionJDBC.getInstance().buscarClienteDni(dniapellido);
            this.CargarTablaFichaMedicaPorDNI();
            this.txtDniApellido.setText("");
        }
        else if(this.rbtApellido.isSelected()==true)
        {
            ConexionJDBC.getInstance().buscarFichaMedicaApe(dniapellido);
            this.CargarTablaFichaMedicaPorAPELLIDO();
            this.txtDniApellido.setText("");
        }
        
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void TablaFichaMedicaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TablaFichaMedicaMouseClicked
        
        if(this.rbtDni.isSelected()==true)
        {
           for (int i = 0; i < this.TablaFichaMedica.getRowCount(); i++) 
            {
                String dni = "";
               
                dni = (String) this.TablaFichaMedica.getValueAt(this.TablaFichaMedica.getSelectedRow(), 1);

                this.txtDniApellido.setText(dni);
            }
        }
        else if(this.rbtApellido.isSelected()==true)
        {
            for (int i = 0; i < this.TablaFichaMedica.getRowCount(); i++) 
            {
                String ape = "";
               
                ape = (String) this.TablaFichaMedica.getValueAt(this.TablaFichaMedica.getSelectedRow(), 2);

                this.txtDniApellido.setText(ape);
            }
        }
        
    }//GEN-LAST:event_TablaFichaMedicaMouseClicked

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
            java.util.logging.Logger.getLogger(ConsultarFichaMedica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ConsultarFichaMedica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ConsultarFichaMedica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ConsultarFichaMedica.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ConsultarFichaMedica().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TablaFichaMedica;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCerrar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton rbtApellido;
    private javax.swing.JRadioButton rbtDni;
    private javax.swing.JTextField txtDniApellido;
    // End of variables declaration//GEN-END:variables
}
