/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gripgym;


import Controlador.ConexionJDBC;
import Modelo.*;
import gripgym.*;
import java.awt.*;
import java.io.*;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Vero
 */
public class RegistrarCliente extends javax.swing.JFrame 
{
    private String urlOrigen="";
    private String mensaje="",imgDestino, extension, genero, imgBd, nombreFoto;

    
    Statement st;
    ResultSet rs;
    int fila;
    String dni,sexo;
    /**
     * Creates new form RegistrarCliente
     */
    public RegistrarCliente() 
    {
        initComponents();
        
        setLocationRelativeTo(null); //Centralizamos la ventana.
        this.setTitle("REGISTRAR CLIENTE");
       
        //Color JFrame
        this.getContentPane().setBackground(new java.awt.Color(102,102,102));
        //Color JPanel
        jPanel1.setBackground(new java.awt.Color(102,102,102));
        
        
        cargarComboBarrio();
        cargarComboLocalidad();
        
        ConexionJDBC.getInstance().AbrirConexion();
        CargarTabla();
        
      
        
       
    }

    
     public void cargarComboBarrio() {
        ArrayList r = ConexionJDBC.getInstance().getBarrio();

        if (r != null) {
            try {
                DefaultComboBoxModel modelo = new DefaultComboBoxModel();
                Iterator iter = r.iterator();

                while (iter.hasNext()) {
                    modelo.addElement(iter.next());
                }
                this.cboBarrio.setModel(modelo);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "No se pudo cargar el combo Barrio");
            }
        } else {
            JOptionPane.showMessageDialog(this, "No tiene registrado Barrio");
        }
    }
     
    public void cargarComboLocalidad() {
        ArrayList r = ConexionJDBC.getInstance().getLocalidad();

        if (r != null) {
            try {
                DefaultComboBoxModel modelo = new DefaultComboBoxModel();
                Iterator iter = r.iterator();

                while (iter.hasNext()) {
                    modelo.addElement(iter.next());
                }
                this.cboLocalidad.setModel(modelo);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "No se pudo cargar el combo Localidad");
            }
        } else {
            JOptionPane.showMessageDialog(this, "No tiene registrado Localidad");
        }
    }

    
     public void LimpiarCampos() 
     {
         this.txtFechaIngreso.setCalendar(null);
         this.txtNombre.setText("");
         this.txtApellido.setText("");
         this.txtFechaNacimiento.setCalendar(null);
         this.txtEdad.setText("");
         this.buttonGroup1.clearSelection();
         this.txtDni.setText("");
         this.txtDomicilio.setText("");
         this.cboBarrio.setSelectedIndex(-1);
         this.cboLocalidad.setSelectedIndex(-1);
         this.txtCodPostal.setText("");
         this.txtTelefono.setText("");
         this.txtCelular.setText("");
         this.txtEmail.setText("");
         this.lblFoto.setIcon(null);
     }
    
    private void DeshabilitarCampos()
    {
         this.txtFechaIngreso.setEnabled(false);
         this.txtNombre.setEnabled(false);
         this.txtApellido.setEnabled(false);
         this.txtFechaNacimiento.setEnabled(false);
         this.txtEdad.setEnabled(false);
         this.rbtFemenino.setEnabled(false);
         this.rbtMasculino.setEnabled(false);
         this.txtDni.setEnabled(false);
         this.txtDomicilio.setEnabled(false);
         this.cboBarrio.setEnabled(false);
         this.cboLocalidad.setEnabled(false);
         this.txtCodPostal.setEnabled(false);
         this.txtTelefono.setEnabled(false);
         this.txtCelular.setEnabled(false);
         this.txtEmail.setEnabled(false);
         this.lblFoto.setEnabled(false);
         this.btnBuscar.setEnabled(false);
         this.btnBuscarFoto.setEnabled(false);
    }
     
    
     private void HabilitarCampos()
    {
        this.txtFechaIngreso.setEnabled(true);
         this.txtNombre.setEnabled(true);
         this.txtApellido.setEnabled(true);
         this.txtFechaNacimiento.setEnabled(true);
         this.txtEdad.setEnabled(true);
         this.rbtFemenino.setEnabled(true);
         this.rbtMasculino.setEnabled(true);
         this.txtDni.setEnabled(true);
         this.txtDomicilio.setEnabled(true);
         this.cboBarrio.setEnabled(true);
         this.cboLocalidad.setEnabled(true);
         this.txtCodPostal.setEnabled(true);
         this.txtTelefono.setEnabled(true);
         this.txtCelular.setEnabled(true);
         this.txtEmail.setEnabled(true);
         this.lblFoto.setEnabled(true);
         this.btnBuscar.setEnabled(true);
         this.btnBuscarFoto.setEnabled(true);
         
         this.cboBarrio.setSelectedIndex(0);
         this.cboLocalidad.setSelectedIndex(0);
    }
    
     
     private void DeshabilitarBotones()
     {
         this.btnGuardar.setEnabled(false);
         this.btnEditar.setEnabled(false);
         this.btnCancelar.setEnabled(false);
         this.btnEliminar.setEnabled(false);
     }
     
      private void HabilitarBotones()
     {
         this.btnGuardar.setEnabled(true);
         this.btnEditar.setEnabled(true);
         this.btnCancelar.setEnabled(true);
         this.btnEliminar.setEnabled(true);
     }
     
      
      
     //Metodo para que muestre en la tabla todas los Clientes.
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
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        rbtFemenino = new javax.swing.JRadioButton();
        rbtMasculino = new javax.swing.JRadioButton();
        txtNombre = new javax.swing.JTextField();
        txtApellido = new javax.swing.JTextField();
        txtEdad = new javax.swing.JTextField();
        txtDni = new javax.swing.JTextField();
        txtDomicilio = new javax.swing.JTextField();
        cboBarrio = new javax.swing.JComboBox();
        cboLocalidad = new javax.swing.JComboBox();
        txtCodPostal = new javax.swing.JTextField();
        txtTelefono = new javax.swing.JTextField();
        txtCelular = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        txtFechaNacimiento = new com.toedter.calendar.JDateChooser();
        lblFoto = new javax.swing.JLabel();
        btnBuscarFoto = new javax.swing.JButton();
        lblFechaIngreso = new javax.swing.JLabel();
        txtFechaIngreso = new com.toedter.calendar.JDateChooser();
        btnBuscar = new javax.swing.JButton();
        btnNuevo = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnCerrar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaCliente = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos Personales:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(255, 255, 255))); // NOI18N

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Nombre:");

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Apellido:");

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Fecha Nacimiento:");

        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Edad:");

        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Sexo:");

        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("D.N.I:");

        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Domicilio:");

        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Barrio:");

        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Localidad:");

        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Cod Postal:");

        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Teléfono:");

        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Celular:");

        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("E-Mail:");

        buttonGroup1.add(rbtFemenino);
        rbtFemenino.setForeground(new java.awt.Color(255, 255, 255));
        rbtFemenino.setText("Femenino");

        buttonGroup1.add(rbtMasculino);
        rbtMasculino.setForeground(new java.awt.Color(255, 255, 255));
        rbtMasculino.setText("Masculino");

        lblFoto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblFoto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 3));

        btnBuscarFoto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/buscarFoto1(25x25).png"))); // NOI18N
        btnBuscarFoto.setText("Buscar Foto");
        btnBuscarFoto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarFotoActionPerformed(evt);
            }
        });

        lblFechaIngreso.setForeground(new java.awt.Color(255, 255, 255));
        lblFechaIngreso.setText("Fecha Ingreso:");

        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Buscar2.png"))); // NOI18N
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addComponent(jLabel11))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(250, 250, 250)
                                .addComponent(btnBuscar))
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel12))
                                .addGap(52, 52, 52)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(cboBarrio, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtDomicilio, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtCodPostal, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(6, 6, 6)))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel10)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(cboLocalidad, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel13)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtCelular, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtEdad, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel7)
                                    .addComponent(lblFechaIngreso))
                                .addGap(10, 10, 10)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtFechaIngreso, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtDni, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(rbtFemenino)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(rbtMasculino))
                                        .addComponent(txtApellido, javax.swing.GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)
                                        .addComponent(txtNombre))
                                    .addComponent(txtFechaNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 99, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblFoto, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(btnBuscarFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(17, 17, 17)))
                        .addGap(23, 23, 23))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblFechaIngreso)
                            .addComponent(txtFechaIngreso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtDni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel7))
                            .addComponent(btnBuscar))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel4)
                                .addComponent(jLabel5)
                                .addComponent(txtEdad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtFechaNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(rbtFemenino)
                            .addComponent(rbtMasculino))))
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtDomicilio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscarFoto))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10)
                    .addComponent(cboBarrio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboLocalidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtCodPostal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13)
                    .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCelular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/New-24.png"))); // NOI18N
        btnNuevo.setText("Nuevo");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/guardar-24.png"))); // NOI18N
        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Modificar-24.png"))); // NOI18N
        btnEditar.setText("Editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/cancelar3-24.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/eliminar-24.png"))); // NOI18N
        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnCerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/logout-24.png"))); // NOI18N
        btnCerrar.setText("Cerrar");
        btnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarActionPerformed(evt);
            }
        });

        TablaCliente.setModel(new javax.swing.table.DefaultTableModel(
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
        TablaCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TablaClienteMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TablaCliente);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnCerrar)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnNuevo)
                .addGap(16, 16, 16)
                .addComponent(btnGuardar)
                .addGap(18, 18, 18)
                .addComponent(btnEditar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCancelar)
                .addGap(18, 18, 18)
                .addComponent(btnEliminar)
                .addGap(223, 223, 223))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNuevo)
                    .addComponent(btnGuardar)
                    .addComponent(btnEditar)
                    .addComponent(btnCancelar)
                    .addComponent(btnEliminar))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(btnCerrar)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    
    private void CargarImagen()
    {
        //Método para Examinar/Buscar Fotos.
        File archivo;
        JFileChooser abrirArchivo=new JFileChooser();
        abrirArchivo.setFileFilter(new FileNameExtensionFilter("Archivos de Imagen","jpg","png","gif"));
        int respuesta = abrirArchivo.showOpenDialog(this);
        if(respuesta == JFileChooser.APPROVE_OPTION)
        {
            archivo=abrirArchivo.getSelectedFile();
            
            //Capturar la URL de la Imagen.
            urlOrigen = archivo.getAbsolutePath();
            nombreFoto=archivo.getName();
            Image foto = getToolkit().getImage(urlOrigen);
//            foto = foto.getScaledInstance(206, 173, 1);
            this.lblFoto.setIcon(new ImageIcon(foto));
            
            //Obtenemos la extensión de la Imagen cargada.
            extension = urlOrigen.substring(urlOrigen.lastIndexOf('.'));
            imgDestino = "D:\\FOTOS\\" + nombreFoto;
        }
    }
    
    
  
    
    
    private void btnBuscarFotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarFotoActionPerformed
        this.CargarImagen();
     
    }//GEN-LAST:event_btnBuscarFotoActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        
       //Validamos si los campos están vacíos.
//        if(this.txtFechaIngreso.getDate().equals(""))
//        {   JOptionPane.showMessageDialog(null, "¡Ingrese Fecha de Ingreso!");
//            this.txtFechaIngreso.requestFocus();}
//        else
//        {
//          if(this.txtDni.getText().equals(""))
//            {   JOptionPane.showMessageDialog(null, "¡Ingrese Dni!");
//                this.txtDni.requestFocus();}
//        else
//        {
//          if(this.txtNombre.getText().equals(""))
//            {   JOptionPane.showMessageDialog(null, "¡Ingrese Nombre!");
//                this.txtNombre.requestFocus();}
//        else
//        {
//         if(this.txtApellido.getText().equals(""))
//            {   JOptionPane.showMessageDialog(null, "¡Ingrese Apellido!");
//                this.txtApellido.requestFocus();}
//         else
//        {
//            if(this.txtFechaNacimiento.getDate().equals(""))
//            {
//                JOptionPane.showMessageDialog(null, "¡Ingrese Fecha Nacimiento!");
//                this.txtFechaNacimiento.requestFocus();
//            }
//        else
//        {
//            if(this.txtEdad.getText().equals(""))
//            {
//                JOptionPane.showMessageDialog(null, "¡Ingrese Edad!");
//                this.txtEdad.requestFocus();
//            }
//        else
//        {
//            if(this.rbtFemenino.getText().equals("") && this.rbtMasculino.getText().equals(""))
//            {
//                JOptionPane.showMessageDialog(null, "¡Ingrese Sexo!");
//                this.rbtFemenino.requestFocus();
//                this.rbtMasculino.requestFocus();
//            }
//        else
//        {
//            if(this.txtDomicilio.getText().equals(""))
//            {
//                JOptionPane.showMessageDialog(null, "¡Ingrese Domicilio!");
//                this.txtDomicilio.requestFocus();
//            }
//        else
//        {
//            if(this.cboBarrio.equals(""))
//            {
//                JOptionPane.showMessageDialog(null, "¡Ingrese Barrio!");
//                this.cboBarrio.requestFocus();
//            }
//        else
//        {
//            if(this.cboLocalidad.equals(""))
//            {
//                JOptionPane.showMessageDialog(null, "¡Ingrese Localidad!");
//                this.cboLocalidad.requestFocus();
//            }
//        else
//        {
//            if(this.txtCodPostal.getText().equals(""))
//            {
//                JOptionPane.showMessageDialog(null, "¡Ingrese Código Postal!");
//                this.txtCodPostal.requestFocus();
//            }
//        else
//        {
//            if(this.txtTelefono.getText().equals(""))
//            {
//                JOptionPane.showMessageDialog(null, "¡Ingrese Teléfono!");
//                this.txtTelefono.requestFocus();
//            }
//        else
//        {
//            if(this.txtCelular.getText().equals(""))
//            {
//                JOptionPane.showMessageDialog(null, "¡Ingrese Celular!");
//                this.txtCelular.requestFocus();
//            }
//        else
//        {
//            if(this.txtEmail.getText().equals(""))
//            {
//                JOptionPane.showMessageDialog(null, "¡Ingrese E-Mail!");
//                this.txtEmail.requestFocus();
//            }
//        }
//    }}}}}}}}}}}}  
        
        
        Date fechaIng=this.txtFechaIngreso.getDate();
        String nom=this.txtNombre.getText();
        String ape=this.txtApellido.getText();
        Date fechaNac=this.txtFechaNacimiento.getDate();
        int edad=Integer.parseInt(this.txtEdad.getText());
        
        String sexo="";
        if(this.rbtFemenino.isSelected())
        {sexo="Femenino";}
        else if(this.rbtMasculino.isSelected())
        {sexo="Masculino";}
        
        int dni=Integer.parseInt(this.txtDni.getText());
        String dom=this.txtDomicilio.getText();

        Barrio ba = (Barrio) this.cboBarrio.getSelectedItem();
        int barrio = ba.getIdBarrio();
        Localidad lo= (Localidad) this.cboLocalidad.getSelectedItem();
        int localidad = lo.getIdLocalidad();
        
        int codP=Integer.parseInt(this.txtCodPostal.getText());
        int tel=Integer.parseInt(this.txtTelefono.getText());
        int cel=Integer.parseInt(this.txtCelular.getText());
        String eMail=this.txtEmail.getText();
        String foto=imgDestino;
        
        
        Persona P=new Persona(nom,ape,fechaNac,edad,sexo,dni,dom,barrio,localidad,codP,tel,cel,eMail,foto); 
        ConexionJDBC.getInstance().InsertarCliente(P);

        Cliente C=new Cliente(fechaIng);
        ConexionJDBC.getInstance().InsertarCliente(C);
       
     
        this.CargarTabla();
        this.LimpiarCampos();
         
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        this.LimpiarCampos();
        this.HabilitarCampos();
        this.HabilitarBotones();
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
      dispose();
    }//GEN-LAST:event_btnCerrarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        int resp = JOptionPane.showConfirmDialog(null, "¿Seguro que desea cancelar?", "Cancelar", JOptionPane.YES_NO_OPTION);
        if (resp == JOptionPane.YES_OPTION) 
        {
            this.LimpiarCampos();
            this.DeshabilitarCampos();
            this.DeshabilitarBotones();
        }
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void TablaClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TablaClienteMouseClicked
 
        for (int i = 0; i < this.TablaCliente.getRowCount(); i++) 
            {
                String fi="", dni="", nom="", ape="", fn="", edad="", dom="", b="", l="", codp="", tel="", cel="", em="";
               
               
                fi = (String) this.TablaCliente.getValueAt(this.TablaCliente.getSelectedRow(), 0);
                dni = (String) this.TablaCliente.getValueAt(this.TablaCliente.getSelectedRow(), 1);
                nom = (String) this.TablaCliente.getValueAt(this.TablaCliente.getSelectedRow(), 2);
                ape = (String) this.TablaCliente.getValueAt(this.TablaCliente.getSelectedRow(), 3);
                fn = (String) this.TablaCliente.getValueAt(this.TablaCliente.getSelectedRow(), 4);
                edad = (String) this.TablaCliente.getValueAt(this.TablaCliente.getSelectedRow(), 5);
                genero = (String) this.TablaCliente.getValueAt(this.TablaCliente.getSelectedRow(), 6);
                dom = (String) this.TablaCliente.getValueAt(this.TablaCliente.getSelectedRow(), 7);
                b = (String) this.TablaCliente.getValueAt(this.TablaCliente.getSelectedRow(), 8);
                l = (String) this.TablaCliente.getValueAt(this.TablaCliente.getSelectedRow(), 9);
                codp = (String) this.TablaCliente.getValueAt(this.TablaCliente.getSelectedRow(), 10);
                tel = (String) this.TablaCliente.getValueAt(this.TablaCliente.getSelectedRow(), 11);
                cel = (String) this.TablaCliente.getValueAt(this.TablaCliente.getSelectedRow(), 12);
                em = (String) this.TablaCliente.getValueAt(this.TablaCliente.getSelectedRow(), 13);
                imgBd = (String) this.TablaCliente.getValueAt(this.TablaCliente.getSelectedRow(), 14);
                

                
//              -------------------------------------------------
                SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd"); 
                Date dato = null;
                try 
                { dato = formatoDelTexto.parse(fi);} 
                catch (ParseException ex) 
                {ex.printStackTrace();}
                this.txtFechaIngreso.setDate(dato);
//              --------------------------------------------------
                
                this.txtDni.setText(dni);
                this.txtNombre.setText(nom);
                this.txtApellido.setText(ape);
                
//              -------------------------------------------------
                SimpleDateFormat formatoDelTexto1 = new SimpleDateFormat("yyyy-MM-dd"); 
                Date dato1 = null;
                try 
                { dato1 = formatoDelTexto1.parse(fn);} 
                catch (ParseException ex) 
                {ex.printStackTrace();}
                this.txtFechaNacimiento.setDate(dato1);
//              --------------------------------------------------
                
                this.txtEdad.setText(edad);
                
//              -------------------------------------------------
                //Condición que evalúa la variable género.
                if("Femenino".equals(genero))
                {this.rbtFemenino.setSelected(true);}
                else
                {this.rbtMasculino.setSelected(true);}
//              -------------------------------------------------
                
                this.txtDomicilio.setText(dom);
                this.cboBarrio.addItem(b);
                this.cboBarrio.setSelectedItem(b);
                this.cboLocalidad.addItem(l);
                this.cboLocalidad.setSelectedItem(l);
                this.txtCodPostal.setText(codp);
                this.txtTelefono.setText(tel);
                this.txtCelular.setText(cel);
                this.txtEmail.setText(em);
                
                ImageIcon imagenSalir=new ImageIcon(imgBd);//Lee la imágen.
                this.lblFoto.setIcon(imagenSalir);//La muestra.
            }
        
        
        
    }//GEN-LAST:event_TablaClienteMouseClicked

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
    
         //Elimina la fila seleccionada.   
        DefaultTableModel model = (DefaultTableModel) this.TablaCliente.getModel();

        int fila = this.TablaCliente.getSelectedRow();

        if (fila < 0) 
        {
            JOptionPane.showMessageDialog(null, "Debe seleccionar una fila de la tabla");
        } 
        else 
        {
            int confirmar = JOptionPane.showConfirmDialog(null, "¿Esta seguro que desea Eliminar el registro? ");

            if (JOptionPane.OK_OPTION == confirmar) 
            {
                int dni=Integer.parseInt(this.txtDni.getText());
                ConexionJDBC.getInstance().EliminarClienteC(dni);
                ConexionJDBC.getInstance().EliminarClienteP(dni);
                model.removeRow(fila);
                JOptionPane.showMessageDialog(null, "¡Registro Eliminado!");
            }
        }
        this.CargarTabla();
        this.LimpiarCampos();
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed

        int dni=Integer.parseInt(this.txtDni.getText());
        
        try
        {   ConexionJDBC.getInstance().AbrirConexion();
            rs=ConexionJDBC.getInstance().getCargarCliente(dni);
            if(rs.next())
            {  
                this.txtFechaIngreso.setDate(rs.getDate(1));
                this.txtNombre.setText(rs.getString(3));
                this.txtApellido.setText(rs.getString(4));
                this.txtFechaNacimiento.setDate(rs.getDate(5));
                this.txtEdad.setText(rs.getString(6));
                
//              -------------------------------------------------
                sexo=rs.getString(7);
                
                if("Femenino".equals(sexo))
                  {this.rbtFemenino.setSelected(true);}
                else 
                  {this.rbtMasculino.setSelected(true);} 
//              -------------------------------------------------               
              
                this.txtDomicilio.setText(rs.getString(8));
                
                this.cboBarrio.addItem(rs.getString(9));
                this.cboBarrio.setSelectedItem(rs.getString(9));
                 
                this.cboLocalidad.addItem(rs.getString(10));
                this.cboLocalidad.setSelectedItem(rs.getString(10));
                
                this.txtCodPostal.setText(rs.getString(11));
                this.txtTelefono.setText(rs.getString(12));
                this.txtCelular.setText(rs.getString(13));
                this.txtEmail.setText(rs.getString(14));
                
                ImageIcon imagen= new ImageIcon(rs.getString(15));
                this.lblFoto.setIcon(imagen);
                
            }
            else
            {
                JOptionPane.showMessageDialog(this, "¡Este cliente no existe!");
                rs.close();
            }
        }
        catch(Exception e)
        { JOptionPane.showMessageDialog(this,"Error: "+e.getMessage());}
        
        
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
             
   
    }//GEN-LAST:event_btnEditarActionPerformed

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
            java.util.logging.Logger.getLogger(RegistrarCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RegistrarCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RegistrarCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RegistrarCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RegistrarCliente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TablaCliente;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnBuscarFoto;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox cboBarrio;
    private javax.swing.JComboBox cboLocalidad;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblFechaIngreso;
    private javax.swing.JLabel lblFoto;
    private javax.swing.JRadioButton rbtFemenino;
    private javax.swing.JRadioButton rbtMasculino;
    private javax.swing.JTextField txtApellido;
    private javax.swing.JTextField txtCelular;
    private javax.swing.JTextField txtCodPostal;
    private javax.swing.JTextField txtDni;
    private javax.swing.JTextField txtDomicilio;
    private javax.swing.JTextField txtEdad;
    private javax.swing.JTextField txtEmail;
    private com.toedter.calendar.JDateChooser txtFechaIngreso;
    private com.toedter.calendar.JDateChooser txtFechaNacimiento;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}
