/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gripgym;

import Controlador.*;
import Modelo.*;
import static gripgym.ConsultarProfesor.apellido;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Vero
 */
public class ConsultarCliente extends javax.swing.JFrame {

    public static String apellido;
    
    /**
     * Creates new form ConsultarCliente
     */
    
    public ConsultarCliente() {
        initComponents();
        
        setLocationRelativeTo(null); //Centralizamos la ventana.
        this.setTitle("CONSULTAR CLIENTE");
        
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
            tabla.addColumn("Ingreso");
            tabla.addColumn("D.N.I");
            tabla.addColumn("Nombre");
            tabla.addColumn("Apellido");
            tabla.addColumn("Nacimiento");
            tabla.addColumn("Edad");
            tabla.addColumn("Sexo");
            tabla.addColumn("Domicilio");
            tabla.addColumn("Barrio");
            tabla.addColumn("Localidad");
            tabla.addColumn("Cod Postal");
            tabla.addColumn("Telefono");
            tabla.addColumn("Celular");
            tabla.addColumn("E-Mail");
            tabla.addColumn("Foto");
           
            ResultSet rs = ConexionJDBC.getInstance().getCargarCliente();

            while (rs.next()) {
                Object dato[] = new Object[15];
                for (int i = 0; i < 15; i++) {
                    dato[i] = rs.getString(i + 1);

                }
                tabla.addRow(dato);
            }
            this.TablaCliente.setModel(tabla);

        } catch (Exception e) {
        }
    }
    

     
          
      public void CargarTablaClientePorAPELLIDO()
     { //Paso el modelo vacio para q se resetee
        DefaultTableModel dtm=new DefaultTableModel();
        this.TablaCliente.setModel(dtm);

        //Manejo de la tabla.
        String titColumna[];
        titColumna=new String[15];
	titColumna[0]="Ingreso";
	titColumna[1]="Nombre";
        titColumna[2]="Apellido";
        titColumna[3]="Nacimiento";
        titColumna[4]="Edad";
        titColumna[5]="Sexo";
        titColumna[6]="D.N.I";
        titColumna[7]="Domicilio";
        titColumna[8]="Barrio";
        titColumna[9]="Localidad";
        titColumna[10]="Cod Postal";
        titColumna[11]="Telefono";
        titColumna[12]="Celular";
        titColumna[13]="E-Mail";
        titColumna[14]="Foto";
       
        String apellido=this.txtDniApellido.getText();
        ArrayList lista =ConexionJDBC.getInstance().ClientePorAPELLIDO(apellido);
        
        Object[][] data = new String[lista.size()][15];
        try
        {
        Iterator iter=lista.iterator();
        Cliente C=null;
        int indice=0;

        while(iter.hasNext())
        {    C=(Cliente)iter.next();
             data[indice][0]= String.valueOf(C.getFechaIngreso());
             data[indice][1]= C.getNombre();
             data[indice][2]= C.getApellido();
             data[indice][3]= String.valueOf(C.getFechaNac());
             data[indice][4]= String.valueOf(C.getEdad());
             data[indice][5]= C.getSexo();
             data[indice][6]= String.valueOf(C.getDni());
             data[indice][7]= C.getDomicilio();
             data[indice][8]= C.getBarrio();
             data[indice][9]= C.getLocalidad();
             data[indice][10]= String.valueOf(C.getCodPostal());
             data[indice][11]= String.valueOf(C.getTel());
             data[indice][12]= String.valueOf(C.getCel());
             data[indice][13]= C.geteMail();
             data[indice][14]= C.getFoto();
            
             indice++;
        }//fin del ciclo..
        dtm=new DefaultTableModel(data,titColumna);
        this.TablaCliente.setModel(dtm);
        } catch(Exception e)
          {System.out.println("Error al cargar la tabla!!!" + e.getMessage());}
    }
     
      
        public void CargarTablaClientePorDNI()
     { //Paso el modelo vacio para q se resetee
        DefaultTableModel dtm=new DefaultTableModel();
        this.TablaCliente.setModel(dtm);

        //Manejo de la tabla.
        String titColumna[];
        titColumna=new String[15];
	titColumna[0]="Ingreso";
	titColumna[1]="Nombre";
        titColumna[2]="Apellido";
        titColumna[3]="Nacimiento";
        titColumna[4]="Edad";
        titColumna[5]="Sexo";
        titColumna[6]="D.N.I";
        titColumna[7]="Domicilio";
        titColumna[8]="Barrio";
        titColumna[9]="Localidad";
        titColumna[10]="Cod Postal";
        titColumna[11]="Telefono";
        titColumna[12]="Celular";
        titColumna[13]="E-Mail";
        titColumna[14]="Foto";
       
        int dni=Integer.parseInt(this.txtDniApellido.getText());
        ArrayList lista =ConexionJDBC.getInstance().ClientePorDNI(dni);

        Object[][] data = new String[lista.size()][15];
        try
        {
        Iterator iter=lista.iterator();
        Cliente C=null;
        int indice=0;

        while(iter.hasNext())
        {    C=(Cliente)iter.next();
             data[indice][0]= String.valueOf(C.getFechaIngreso());
             data[indice][1]= C.getNombre();
             data[indice][2]= C.getApellido();
             data[indice][3]= String.valueOf(C.getFechaNac());
             data[indice][4]= String.valueOf(C.getEdad());
             data[indice][5]= C.getSexo();
             data[indice][6]= String.valueOf(C.getDni());
             data[indice][7]= C.getDomicilio();
             data[indice][8]= C.getBarrio();
             data[indice][9]= C.getLocalidad();
             data[indice][10]= String.valueOf(C.getCodPostal());
             data[indice][11]= String.valueOf(C.getTel());
             data[indice][12]= String.valueOf(C.getCel());
             data[indice][13]= C.geteMail();
             data[indice][14]= C.getFoto();
            
             indice++;
        }//fin del ciclo..
        dtm=new DefaultTableModel(data,titColumna);
        this.TablaCliente.setModel(dtm);
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
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaCliente = new javax.swing.JTable();
        btnCerrar = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        btnBuscar = new javax.swing.JButton();
        txtDniApellido = new javax.swing.JTextField();
        rbtDni = new javax.swing.JRadioButton();
        rbtApellido = new javax.swing.JRadioButton();
        btnAceptar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        TablaCliente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        TablaCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TablaClienteMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TablaCliente);

        btnCerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/logout-24.png"))); // NOI18N
        btnCerrar.setText("Cerrar");
        btnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarActionPerformed(evt);
            }
        });

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
                .addGap(236, 236, 236)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(rbtDni)
                        .addGap(42, 42, 42)
                        .addComponent(txtDniApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(rbtApellido))
                .addGap(28, 28, 28)
                .addComponent(btnBuscar)
                .addContainerGap(386, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnBuscar)
                            .addComponent(txtDniApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(rbtDni)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                        .addComponent(rbtApellido)))
                .addContainerGap())
        );

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
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnAceptar)
                        .addGap(41, 41, 41)
                        .addComponent(btnCerrar))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
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
       
        String dniapellido = this.txtDniApellido.getText();
       
        if(this.rbtDni.isSelected()==true)
        {
            ConexionJDBC.getInstance().buscarClienteDni(dniapellido);
            this.CargarTablaClientePorDNI();
            this.txtDniApellido.setText("");
        }
        else if(this.rbtApellido.isSelected()==true)
        {
            ConexionJDBC.getInstance().buscarClienteApe(dniapellido);
            this.CargarTablaClientePorAPELLIDO();
            this.txtDniApellido.setText("");
        }
 
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        
//        apellido=this.txtApellido.getText();
//        
//        RegistrarActividad.txtApellidoCliente.setText(apellido);
        
        
        
            for (int i = 0; i < this.TablaCliente.getRowCount(); i++) 
            {
                String ape = "",nom = "";
               
                ape = (String) this.TablaCliente.getValueAt(this.TablaCliente.getSelectedRow(), 3);
                nom = (String) this.TablaCliente.getValueAt(this.TablaCliente.getSelectedRow(), 2);
                
                RegistrarActividad.txtApellidoCliente.setText(ape+" "+nom);
            }
        
        dispose(); //Cerrar ventana.
        
        
        
//         for (int i = 0; i < this.TablaCliente.getRowCount(); i++) 
//            {
//                String dni="",ape = "",nom = "";
//               
//                dni = (String) this.TablaCliente.getValueAt(this.TablaCliente.getSelectedRow(), 1);
//                ape = (String) this.TablaCliente.getValueAt(this.TablaCliente.getSelectedRow(), 3);
//                nom = (String) this.TablaCliente.getValueAt(this.TablaCliente.getSelectedRow(), 2);
//               
//                RegistrarPagoCuota.txtDni.setText(dni);
//                RegistrarPagoCuota.txtApeNom.setText(ape+" "+nom);
//            }
//        
//        dispose(); //Cerrar ventana.
//        
        
        
        
        
    }//GEN-LAST:event_btnAceptarActionPerformed

    private void TablaClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TablaClienteMouseClicked

        if(this.rbtDni.isSelected()==true)
        {
           for (int i = 0; i < this.TablaCliente.getRowCount(); i++) 
            {
                String dni = "";
               
                dni = (String) this.TablaCliente.getValueAt(this.TablaCliente.getSelectedRow(), 1);

                this.txtDniApellido.setText(dni);
            }
        }
        else if(this.rbtApellido.isSelected()==true)
        {
            for (int i = 0; i < this.TablaCliente.getRowCount(); i++) 
            {
                String ape = "";
               
                ape = (String) this.TablaCliente.getValueAt(this.TablaCliente.getSelectedRow(), 3);

                this.txtDniApellido.setText(ape);
            }
        }
        
    }//GEN-LAST:event_TablaClienteMouseClicked

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
            java.util.logging.Logger.getLogger(ConsultarCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ConsultarCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ConsultarCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ConsultarCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ConsultarCliente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TablaCliente;
    private javax.swing.JButton btnAceptar;
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
