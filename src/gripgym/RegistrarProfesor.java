/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gripgym;

import Controlador.*;
import Modelo.*;
import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Vero
 */
public class RegistrarProfesor extends javax.swing.JFrame {
    
    private String urlOrigen="";
    private String mensaje="",imgDestino, extension, genero, imgBd, nombreFoto;
    

    Statement st;
    ResultSet rs;
    int fila;
    String dni,sexo;
    /**
     * Creates new form RegistrarProfesor
     */
    
    public RegistrarProfesor() {
        initComponents();
        
        setLocationRelativeTo(null); //Centralizamos la ventana.
        this.setTitle("REGISTRAR PROFESOR");
        
         //Color JFrame
        this.getContentPane().setBackground(new java.awt.Color(102,102,102));
        //Color JPanel
        jPanel1.setBackground(new java.awt.Color(102,102,102));
        
        
        this.DeshabilitarCampos();
        this.DeshabilitarBotones();
        
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
         this.txtPuesto.setText("");
     }
    
    private void DeshabilitarCampos()
    {
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
         this.txtPuesto.setEnabled(false);
         this.btnBuscarProfesor.setEnabled(false);
         this.btnBuscarFoto.setEnabled(false);
    }
     
    
     private void HabilitarCampos()
    {
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
         this.txtPuesto.setEnabled(true);
         this.btnBuscarProfesor.setEnabled(true);
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
     
     //Metodo para que muestre en la tabla todas los Profesores.
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
        jLabel1 = new javax.swing.JLabel();
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
        txtApellido = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        txtEdad = new javax.swing.JTextField();
        rbtFemenino = new javax.swing.JRadioButton();
        rbtMasculino = new javax.swing.JRadioButton();
        txtFechaNacimiento = new com.toedter.calendar.JDateChooser();
        txtDni = new javax.swing.JTextField();
        txtDomicilio = new javax.swing.JTextField();
        cboBarrio = new javax.swing.JComboBox();
        cboLocalidad = new javax.swing.JComboBox();
        txtCodPostal = new javax.swing.JTextField();
        txtTelefono = new javax.swing.JTextField();
        txtCelular = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        txtPuesto = new javax.swing.JTextField();
        lblFoto = new javax.swing.JLabel();
        btnBuscarFoto = new javax.swing.JButton();
        btnBuscarProfesor = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnNuevo = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnCerrar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaProfesor = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos Personales:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(255, 255, 255))); // NOI18N

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Apellido:");

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Nombre:");

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Fecha Nacimiento:");

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Edad:");

        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Sexo:");

        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("D.N.I:");

        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Domicilio:");

        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Barrio:");

        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Localidad:");

        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Cod Postal:");

        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Telefono:");

        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Celular:");

        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("E-Mail:");

        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Puesto:");

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

        btnBuscarProfesor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Buscar2.png"))); // NOI18N
        btnBuscarProfesor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarProfesorActionPerformed(evt);
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
                            .addComponent(jLabel7)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(110, 110, 110)
                                .addComponent(rbtFemenino)
                                .addGap(160, 160, 160)
                                .addComponent(rbtMasculino)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnBuscarFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(50, 50, 50))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(304, 304, 304)
                                .addComponent(jLabel9)
                                .addGap(18, 18, 18)
                                .addComponent(cboLocalidad, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtFechaNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(64, 64, 64)
                                        .addComponent(jLabel4)
                                        .addGap(32, 32, 32)
                                        .addComponent(txtEdad, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 180, Short.MAX_VALUE)
                                .addComponent(lblFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(27, 27, 27))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel13)
                                    .addComponent(jLabel14))
                                .addGap(69, 69, 69)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtPuesto, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 484, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addGap(60, 60, 60)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtCodPostal, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cboBarrio, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtDomicilio, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(69, 69, 69)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(txtDni, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnBuscarProfesor))
                                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel10)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(336, 336, 336)
                                .addComponent(jLabel12)
                                .addGap(18, 18, 18)
                                .addComponent(txtCelular, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnBuscarFoto)
                        .addGap(35, 35, 35))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnBuscarProfesor)
                            .addComponent(txtDni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(9, 9, 9)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(txtFechaNacimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel4)
                                .addComponent(txtEdad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(rbtFemenino)
                            .addComponent(rbtMasculino))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtDomicilio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9)
                            .addComponent(cboLocalidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboBarrio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtCodPostal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12)
                    .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCelular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(txtPuesto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/guardar-24.png"))); // NOI18N
        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/New-24.png"))); // NOI18N
        btnNuevo.setText("Nuevo");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(192, 192, 192)
                        .addComponent(btnNuevo)
                        .addGap(18, 18, 18)
                        .addComponent(btnGuardar)
                        .addGap(18, 18, 18)
                        .addComponent(btnEditar)
                        .addGap(18, 18, 18)
                        .addComponent(btnCancelar)
                        .addGap(18, 18, 18)
                        .addComponent(btnEliminar)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1))
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar)
                    .addComponent(btnNuevo)
                    .addComponent(btnEditar)
                    .addComponent(btnCancelar)
                    .addComponent(btnEliminar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCerrar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
            imgDestino = "D:\\FOTOS\\" +nombreFoto;
        }
    }
    
    
    //Método para copiar y eliminar imagenes duplicadas.
    private void GuardarImagenes()
    {
        if(urlOrigen.equals(" "))
        {
            //Validamos si el RadioButton está seleccionado.
            if(this.rbtFemenino.isSelected())
            {urlOrigen = "D:\\FOTOS\\Femenino.jpg";}
            else
            {
                if(this.rbtMasculino.isSelected())
                {urlOrigen = "D:\\FOTOS\\Masculino.jpg";}
            }
            extension = urlOrigen.substring(urlOrigen.lastIndexOf('.'));
            imgDestino = "D:\\FOTOS\\" + extension;
        }
        
        //Método para copiar la imágen a su destino.
        try
        {
            FileInputStream fregis = new FileInputStream(urlOrigen);
            FileOutputStream fsalida = new FileOutputStream(imgDestino, true);
            int b = fregis.read();
            while (b != -1)
            {
                fsalida.write(b);
                b = fregis.read();
            }
            fsalida.flush();
            fsalida.close();
            fregis.close();
        }
        catch(IOException ioe)
        {JOptionPane.showMessageDialog(null, "Error al Generar Copia");}
     
    }
    
    
   
    
//      File fichero;
    private void btnBuscarFotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarFotoActionPerformed
//               int resultado;
//        
//        BuscarFoto buscarfoto= new BuscarFoto();
//        FileNameExtensionFilter filtro = new FileNameExtensionFilter("JPG y PNG","jpg","png");
//        buscarfoto.fchBuscarFoto.setFileFilter(filtro);
//        resultado = buscarfoto.fchBuscarFoto.showOpenDialog(null);
//        
//        if(JFileChooser.APPROVE_OPTION == resultado)
//        {
//            fichero = buscarfoto.fchBuscarFoto.getSelectedFile();
//            
//            try
//            {
//              ImageIcon icon = new ImageIcon(fichero.toString());
//              Icon icono = new ImageIcon(icon.getImage().getScaledInstance(this.lblFoto.getWidth(), this.lblFoto.getHeight(), Image.SCALE_DEFAULT));
//              this.lblFoto.setText(null);
//              this.lblFoto.setIcon(icono);
//            }
//            catch(Exception ex)
//            {JOptionPane.showMessageDialog(null, "Error al abrir la imagen!!" + ex);}
//        }
        

        
       /////////////////////////////////////////////////////////////////////////
        
        
        this.CargarImagen();
        
    }//GEN-LAST:event_btnBuscarFotoActionPerformed

    private void btnBuscarProfesorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarProfesorActionPerformed

        int dni=Integer.parseInt(this.txtDni.getText());
        
        try
        {   ConexionJDBC.getInstance().AbrirConexion();
            rs=ConexionJDBC.getInstance().getCargarProfesor(dni);
            if(rs.next())
            {  
                this.txtNombre.setText(rs.getString(2));
                this.txtApellido.setText(rs.getString(3));
                this.txtFechaNacimiento.setDate(rs.getDate(4));
                this.txtEdad.setText(rs.getString(5));
                
//              -------------------------------------------------
                sexo=rs.getString(6);
                
                if("Femenino".equals(sexo))
                  {this.rbtFemenino.setSelected(true);}
                else 
                  {this.rbtMasculino.setSelected(true);} 
//              -------------------------------------------------               
              
                this.txtDomicilio.setText(rs.getString(7));
                
                this.cboBarrio.addItem(rs.getString(8));
                this.cboBarrio.setSelectedItem(rs.getString(8));
                 
                this.cboLocalidad.addItem(rs.getString(9));
                this.cboLocalidad.setSelectedItem(rs.getString(9));
                
                this.txtCodPostal.setText(rs.getString(10));
                this.txtTelefono.setText(rs.getString(11));
                this.txtCelular.setText(rs.getString(12));
                this.txtEmail.setText(rs.getString(13));
                
                ImageIcon imagen= new ImageIcon(rs.getString(14));
                this.lblFoto.setIcon(imagen);
                
                this.txtPuesto.setText(rs.getString(15));
                
            }
            else
            {
                JOptionPane.showMessageDialog(this, "¡Este profesor no existe!");
                rs.close();
            }
        }
        catch(Exception e)
        { JOptionPane.showMessageDialog(this,"Error: "+e.getMessage());}
       
        
        
    }//GEN-LAST:event_btnBuscarProfesorActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
             //Validamos si los campos están vacíos.
//        if(this.txtDni.getText().equals(""))
//            {   JOptionPane.showMessageDialog(null, "¡Ingrese Dni!");
//                this.txtDni.requestFocus(); }
//        else
//        {
//          if(this.txtNombre.getText().equals(""))
//            {   JOptionPane.showMessageDialog(null, "¡Ingrese Nombre!");
//                this.txtNombre.requestFocus();  }
//        else
//        {
//         if(this.txtApellido.getText().equals(""))
//            {   JOptionPane.showMessageDialog(null, "¡Ingrese Apellido!");
//                this.txtApellido.requestFocus();    }
//         else
//        {
//         if(this.txtFechaNacimiento.getDate().equals(""))
//            {   JOptionPane.showMessageDialog(null, "¡Ingrese Fecha Nacimiento!");
//                this.txtFechaNacimiento.requestFocus(); }
//        else
//        {
//         if(this.txtEdad.getText().equals(""))
//            {   JOptionPane.showMessageDialog(null, "¡Ingrese Edad!");
//                this.txtEdad.requestFocus();    }
//        else
//        {
//          if(this.rbtFemenino.isSelected()==false && this.rbtMasculino.isSelected()==false)
//            {   JOptionPane.showMessageDialog(null, "¡Ingrese Sexo!");
//                this.rbtFemenino.requestFocus();
//                this.rbtMasculino.requestFocus();   }
//        else
//        {
//         if(this.txtDomicilio.getText().equals(""))
//            {   JOptionPane.showMessageDialog(null, "¡Ingrese Domicilio!");
//                this.txtDomicilio.requestFocus();   }
//        else
//        {
//         if(this.cboBarrio.getSelectedIndex() != 1)
//            {   //JOptionPane.showMessageDialog(null, "¡Ingrese Barrio!");
//                this.cboBarrio.requestFocus();  }
//        else
//        {
//         if(this.cboLocalidad.getSelectedIndex() != 1)
//            {   JOptionPane.showMessageDialog(null, "¡Ingrese Localidad!");
//                this.cboLocalidad.requestFocus();   }
//        else
//        {
//         if(this.txtCodPostal.getText().equals(""))
//            {   JOptionPane.showMessageDialog(null, "¡Ingrese Código Postal!");
//                this.txtCodPostal.requestFocus();   }
//        else
//        {
//         if(this.txtTelefono.getText().equals(""))
//            {   JOptionPane.showMessageDialog(null, "¡Ingrese Teléfono!");
//                this.txtTelefono.requestFocus();    }
//        else
//        {
//         if(this.txtCelular.getText().equals(""))
//            {   JOptionPane.showMessageDialog(null, "¡Ingrese Celular!");
//                this.txtCelular.requestFocus(); }
//        else
//        {
//         if(this.txtEmail.getText().equals(""))
//            {   JOptionPane.showMessageDialog(null, "¡Ingrese E-Mail!");
//                this.txtEmail.requestFocus();   }

      
       
        
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
//        String foto=this.lblFoto.getText();
        String foto=imgDestino;
        String puesto=this.txtPuesto.getText();
        
        
        Persona P=new Persona(nom,ape,fechaNac,edad,sexo,dni,dom,barrio,localidad,codP,tel,cel,eMail,foto); 
        ConexionJDBC.getInstance().InsertarProfesor(P);
        
        Profesor Pr=new Profesor(puesto);
        ConexionJDBC.getInstance().InsertarProfesor(Pr);
       
     
        
//        }}}}}}}}}}}} 
 
        this.CargarTabla();
        this.LimpiarCampos();
  
        
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarActionPerformed
        dispose();
    }//GEN-LAST:event_btnCerrarActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        this.LimpiarCampos();
        this.HabilitarCampos();
        this.HabilitarBotones();
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        int resp = JOptionPane.showConfirmDialog(null, "¿Seguro que desea cancelar?", "Cancelar", JOptionPane.YES_NO_OPTION);
        if (resp == JOptionPane.YES_OPTION) 
        {
            this.LimpiarCampos();
            this.DeshabilitarCampos();
            this.DeshabilitarBotones();
        }
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        
        //Elimina la fila seleccionada.   
        DefaultTableModel model = (DefaultTableModel) this.TablaProfesor.getModel();

        int fila = this.TablaProfesor.getSelectedRow();

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
                ConexionJDBC.getInstance().EliminarProfesorPr(dni);
                ConexionJDBC.getInstance().EliminarProfesorP(dni);
                model.removeRow(fila);
                JOptionPane.showMessageDialog(null, "¡Registro Eliminado!");
            }
        }
        this.CargarTabla();
        this.LimpiarCampos();
        
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void TablaProfesorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TablaProfesorMouseClicked

        for (int i = 0; i < this.TablaProfesor.getRowCount(); i++) 
            {
                String dni="", nom="", ape="", fn="", edad="", dom="", b="", l="", codp="", tel="", cel="", em="", puesto="";
               
               
                dni = (String) this.TablaProfesor.getValueAt(this.TablaProfesor.getSelectedRow(), 0);
                nom = (String) this.TablaProfesor.getValueAt(this.TablaProfesor.getSelectedRow(), 1);
                ape = (String) this.TablaProfesor.getValueAt(this.TablaProfesor.getSelectedRow(), 2);
                fn = (String) this.TablaProfesor.getValueAt(this.TablaProfesor.getSelectedRow(), 3);
                edad = (String) this.TablaProfesor.getValueAt(this.TablaProfesor.getSelectedRow(), 4);
                genero = (String) this.TablaProfesor.getValueAt(this.TablaProfesor.getSelectedRow(), 5);
                dom = (String) this.TablaProfesor.getValueAt(this.TablaProfesor.getSelectedRow(), 6);
                b = (String) this.TablaProfesor.getValueAt(this.TablaProfesor.getSelectedRow(), 7);
                l = (String) this.TablaProfesor.getValueAt(this.TablaProfesor.getSelectedRow(), 8);
                codp = (String) this.TablaProfesor.getValueAt(this.TablaProfesor.getSelectedRow(), 9);
                tel = (String) this.TablaProfesor.getValueAt(this.TablaProfesor.getSelectedRow(), 10);
                cel = (String) this.TablaProfesor.getValueAt(this.TablaProfesor.getSelectedRow(), 11);
                em = (String) this.TablaProfesor.getValueAt(this.TablaProfesor.getSelectedRow(), 12);
                imgBd = (String) this.TablaProfesor.getValueAt(this.TablaProfesor.getSelectedRow(), 13);
                puesto = (String) this.TablaProfesor.getValueAt(this.TablaProfesor.getSelectedRow(), 14);
                
                
                this.txtDni.setText(dni);
                this.txtNombre.setText(nom);
                this.txtApellido.setText(ape);
                
//              -------------------------------------------------
                SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd"); 
                Date dato = null;
                try 
                { dato = formatoDelTexto.parse(fn);} 
                catch (ParseException ex) 
                {ex.printStackTrace();}
                this.txtFechaNacimiento.setDate(dato);
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

                this.txtPuesto.setText(puesto);

            }
        
        
    }//GEN-LAST:event_TablaProfesorMouseClicked

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        
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

        int barrio=this.cboBarrio.getSelectedIndex();
        int localidad=this.cboLocalidad.getSelectedIndex();
//        Barrio ba = (Barrio) this.cboBarrio.getSelectedItem();
//        int barrio = ba.getIdBarrio();
//        Localidad lo= (Localidad) this.cboLocalidad.getSelectedItem();
//        int localidad = lo.getIdLocalidad();
        
//        int barrio = this.cboBarrio.getSelectedIndex();
//        int localidad=this.cboLocalidad.getSelectedIndex();
        
        int codP=Integer.parseInt(this.txtCodPostal.getText());
        int tel=Integer.parseInt(this.txtTelefono.getText());
        int cel=Integer.parseInt(this.txtCelular.getText());
        String eMail=this.txtEmail.getText();
//        String foto=this.lblFoto.getText();
            String foto=imgDestino;
        String puesto=this.txtPuesto.getText();
        
//        Profesor profesor=new Profesor(puesto,nom,ape,fechaNac,edad,sexo,dni,dom,barrio,localidad,codP,tel,cel,eMail,foto); 
//        ConexionJDBC.getInstance().EditarProfesorP(profesor);
        
        Profesor profesor1=new Profesor(puesto,nom,ape,fechaNac,edad,sexo,dni,dom,barrio,localidad,codP,tel,cel,eMail,foto); 
        ConexionJDBC.getInstance().EditarProfesor(profesor1);
        
        this.CargarTabla();
        
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
            java.util.logging.Logger.getLogger(RegistrarProfesor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RegistrarProfesor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RegistrarProfesor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RegistrarProfesor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RegistrarProfesor().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TablaProfesor;
    private javax.swing.JButton btnBuscarFoto;
    private javax.swing.JButton btnBuscarProfesor;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox cboBarrio;
    private javax.swing.JComboBox cboLocalidad;
    private javax.swing.JLabel jLabel1;
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
    private com.toedter.calendar.JDateChooser txtFechaNacimiento;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtPuesto;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}
