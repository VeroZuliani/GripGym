/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gripgym;

import Controlador.*;
import Modelo.*;
import java.sql.ResultSet;
import java.util.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Vero
 */
public class ConsultarProfesor extends javax.swing.JFrame {

    public static String apellido;
     
    /**
     * Creates new form ConsultarProfesor
     */
    
    public ConsultarProfesor() {
        initComponents();
        
        setLocationRelativeTo(null); //Centralizamos la ventana.
        this.setTitle("CONSULTAR PROFESOR");
        
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
            tabla.addColumn("Puesto");
           
            ResultSet rs = ConexionJDBC.getInstance().getCargarProfesor();

            while (rs.next()) {
                Object dato[] = new Object[15];
                for (int i = 0; i < 15; i++) {
                    dato[i] = rs.getString(i + 1);

                }
                tabla.addRow(dato);
            }
            this.TablaProfesor.setModel(tabla);

        } catch (Exception e) {
        }
    }
    
     
     
      public void CargarTablaProfesorPorApellido() 
     { //Paso el modelo vacio para q se resetee
        DefaultTableModel dtm=new DefaultTableModel();
        this.TablaProfesor.setModel(dtm);

        //Manejo de la tabla.
        String titColumna[];
        titColumna=new String[15];
        titColumna[0]="D.N.I";
	titColumna[1]="Nombre";
	titColumna[2]="Apellido";
        titColumna[3]="Nacimiento";
        titColumna[4]="Edad";
        titColumna[5]="Sexo";
        titColumna[6]="Domicilio";
        titColumna[7]="Barrio";
        titColumna[8]="Localidad";
        titColumna[9]="Cod Postal";
        titColumna[10]="Telefono";
        titColumna[11]="Celular";
        titColumna[12]="EMail";
        titColumna[13]="Foto"; 
        titColumna[14]="Puesto";

        String apellido=this.txtDniApellido.getText();
        ArrayList lista =ConexionJDBC.getInstance().ProfesorPorAPELLIDO(apellido);
        
        Object[][] data = new String[lista.size()][15];
        try
        {
        Iterator iter=lista.iterator();
        Profesor P=null;
   
        int indice=0;

        while(iter.hasNext())
        {    P=(Profesor)iter.next();
             data[indice][0]= String.valueOf(P.getDni());
             data[indice][1]= P.getNombre();
             data[indice][2]= P.getApellido();
             data[indice][3]= String.valueOf(P.getFechaNac());
             data[indice][4]= String.valueOf(P.getEdad());
             data[indice][5]= P.getSexo();
             data[indice][6]= P.getDomicilio();
             data[indice][7]=P.getBarrio();
             data[indice][8]=P.getLocalidad();
             data[indice][9]=String.valueOf(P.getCodPostal());
             data[indice][10]=String.valueOf(P.getTel());
             data[indice][11]=String.valueOf(P.getCel());
             data[indice][12]=P.geteMail();
             data[indice][13]=P.getFoto();
             data[indice][14]= P.getPuesto();
            
             indice++;
        }//fin del ciclo..
        dtm=new DefaultTableModel(data,titColumna);
        this.TablaProfesor.setModel(dtm);
        } catch(Exception e)
          {System.out.println("Error al cargar la tabla!!!" + e.getMessage());}

    }
 
     
       public void CargarTablaProfesorPorDNI() 
     { //Paso el modelo vacio para q se resetee
        DefaultTableModel dtm=new DefaultTableModel();
        this.TablaProfesor.setModel(dtm);

        //Manejo de la tabla.
        String titColumna[];
        titColumna=new String[15];
	titColumna[0]="D.N.I";
        titColumna[1]="Nombre";
	titColumna[2]="Apellido";
        titColumna[3]="Nacimiento";
        titColumna[4]="Edad";
        titColumna[5]="Sexo";
        titColumna[6]="Domicilio";
        titColumna[7]="Barrio";
        titColumna[8]="Localidad";
        titColumna[9]="Cod Postal";
        titColumna[10]="Telefono";
        titColumna[11]="Celular";
        titColumna[12]="EMail";
        titColumna[13]="Foto";      
        titColumna[14]="Puesto";
        
        int dni=Integer.parseInt(this.txtDniApellido.getText());
        ArrayList lista =ConexionJDBC.getInstance().ProfesorPorDNI(dni);
        
        Object[][] data = new String[lista.size()][15];
        try
        {
        Iterator iter=lista.iterator();
        Profesor P=null;
   
        int indice=0;

        while(iter.hasNext())
        {    P=(Profesor)iter.next();
             data[indice][0]= String.valueOf(P.getDni());
             data[indice][1]= P.getNombre();
             data[indice][2]= P.getApellido();
             data[indice][3]= String.valueOf(P.getFechaNac());
             data[indice][4]= String.valueOf(P.getEdad());
             data[indice][5]= P.getSexo();
             data[indice][6]= P.getDomicilio();
             data[indice][7]=P.getBarrio();
             data[indice][8]=P.getLocalidad();
             data[indice][9]=String.valueOf(P.getCodPostal());
             data[indice][10]=String.valueOf(P.getTel());
             data[indice][11]=String.valueOf(P.getCel());
             data[indice][12]=P.geteMail();
             data[indice][13]=P.getFoto();
             data[indice][14]= P.getPuesto();
            
             indice++;
        }//fin del ciclo..
        dtm=new DefaultTableModel(data,titColumna);
        this.TablaProfesor.setModel(dtm);
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
        TablaProfesor = new javax.swing.JTable();
        btnCerrar = new javax.swing.JButton();
        btnAceptar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

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
        rbtApellido.setText("Apellido");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(181, 181, 181)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rbtApellido)
                    .addComponent(rbtDni))
                .addGap(18, 18, 18)
                .addComponent(txtDniApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnBuscar)
                .addContainerGap(288, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtDniApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnBuscar)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(rbtDni)
                        .addGap(18, 18, 18)
                        .addComponent(rbtApellido)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        TablaProfesor.setModel(new javax.swing.table.DefaultTableModel(
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
        TablaProfesor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TablaProfesorMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TablaProfesor);

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
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnAceptar)
                        .addGap(65, 65, 65)
                        .addComponent(btnCerrar)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 137, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCerrar)
                    .addComponent(btnAceptar))
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
            ConexionJDBC.getInstance().buscarProfesorDni(dniapellido);
            this.CargarTablaProfesorPorDNI();
            this.txtDniApellido.setText("");
        }
        else if(this.rbtApellido.isSelected()==true)
        {
            ConexionJDBC.getInstance().buscarProfesorApe(dniapellido);
            this.CargarTablaProfesorPorApellido();
            this.txtDniApellido.setText("");
        }
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
//        apellido=this.txtApellido.getText();
        
//        RegistrarActividad.txtApellidoProfesor.setText(apellido);
        

//           if(this.rbtDni.isSelected()==true)
//        {
//          
//        }
//        else if(this.rbtApellido.isSelected()==true)
//        {
              for (int i = 0; i < this.TablaProfesor.getRowCount(); i++) 
            {
                String ape = "",nom = "";
               
                ape = (String) this.TablaProfesor.getValueAt(this.TablaProfesor.getSelectedRow(), 2);
                nom = (String) this.TablaProfesor.getValueAt(this.TablaProfesor.getSelectedRow(), 1);
                
                RegistrarActividad.txtApellidoProfesor.setText(ape +" "+nom);
            }
//        }
        
        
        dispose(); //Cerrar ventana.
    }//GEN-LAST:event_btnAceptarActionPerformed

    private void TablaProfesorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TablaProfesorMouseClicked

         if(this.rbtDni.isSelected()==true)
        {
           for (int i = 0; i < this.TablaProfesor.getRowCount(); i++) 
            {
                String dni = "";
               
                dni = (String) this.TablaProfesor.getValueAt(this.TablaProfesor.getSelectedRow(), 0);

                this.txtDniApellido.setText(dni);
            }
        }
        else if(this.rbtApellido.isSelected()==true)
        {
            for (int i = 0; i < this.TablaProfesor.getRowCount(); i++) 
            {
                String ape = "";
               
                ape = (String) this.TablaProfesor.getValueAt(this.TablaProfesor.getSelectedRow(), 2);

                this.txtDniApellido.setText(ape);
            }
        }
        
        
        
        
        
    }//GEN-LAST:event_TablaProfesorMouseClicked

    
  
    
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
            java.util.logging.Logger.getLogger(ConsultarProfesor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ConsultarProfesor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ConsultarProfesor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ConsultarProfesor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ConsultarProfesor().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TablaProfesor;
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
