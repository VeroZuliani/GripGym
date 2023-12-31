/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package gripgym;

import Controlador.ConexionJDBC;
//import Reportes..*;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import java.util.Calendar;

/**
 *
 * @author Vero
 */
public class Menu extends javax.swing.JFrame {

    Connection con;
    
    /**
     * Creates new form Menu
     */
      public Menu() 
    {
        initComponents();
        
        setLocationRelativeTo(null); //Centralizamos la ventana.
        this.setTitle("MENU");
        
        
        //Insertamos FECHA y HORA del Sistema.
        Calendar cal=Calendar.getInstance();
        String fecha=cal.get(cal.DATE)+" / "+(cal.get(cal.MONTH)+1)+" / " +cal.get(cal.YEAR);
        String hora=cal.get(cal.HOUR_OF_DAY)+" : "+cal.get(cal.MINUTE)+" : "+cal.get(cal.SECOND);
        this.lblF.setText(fecha);
        this.lblH.setText(hora);
        
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
        jLabel2 = new javax.swing.JLabel();
        lblF = new javax.swing.JLabel();
        lblH = new javax.swing.JLabel();
        lblImagen = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        RegistrarUsuario = new javax.swing.JMenuItem();
        IniciarSesión = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        Cerrar = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        RegistrarCliente = new javax.swing.JMenuItem();
        ConsultarCliente = new javax.swing.JMenuItem();
        RegistrarFichaMedica = new javax.swing.JMenuItem();
        ConsultarFichaMedica = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        RegistrarPagoCuota = new javax.swing.JMenuItem();
        jMenu7 = new javax.swing.JMenu();
        RegistrarActividad = new javax.swing.JMenuItem();
        RegistrarConsultarHorario = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        RegistrarRutina = new javax.swing.JMenuItem();
        ConsultarRutina = new javax.swing.JMenuItem();
        jMenu6 = new javax.swing.JMenu();
        ReporteFichaMedica = new javax.swing.JMenuItem();
        ReporteHorario = new javax.swing.JMenuItem();
        ReporteRutina = new javax.swing.JMenuItem();
        ReportePagoCuota = new javax.swing.JMenuItem();
        ReporteAparatosMasUsados = new javax.swing.JMenuItem();
        Aparato = new javax.swing.JMenu();
        RegistrarAparato = new javax.swing.JMenuItem();
        ConsultarAparato = new javax.swing.JMenuItem();
        Salir = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        RegistrarProfesor = new javax.swing.JMenuItem();
        ConsultarProfesor = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1236, 814));
        setResizable(false);
        getContentPane().setLayout(null);

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("FECHA:");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(860, 150, 80, 22);

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("HORA:");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(860, 180, 80, 22);

        lblF.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblF.setForeground(new java.awt.Color(255, 255, 255));
        lblF.setText("00/00/00");
        getContentPane().add(lblF);
        lblF.setBounds(940, 150, 80, 20);

        lblH.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        lblH.setForeground(new java.awt.Color(255, 255, 255));
        lblH.setText("00:00:00");
        getContentPane().add(lblH);
        lblH.setBounds(940, 180, 80, 20);

        lblImagen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/mancuernas2.jpg"))); // NOI18N
        getContentPane().add(lblImagen);
        lblImagen.setBounds(0, 0, 1229, 774);

        jMenuBar1.setPreferredSize(new java.awt.Dimension(70, 40));

        jMenu1.setText("           Archivo                         ");
        jMenu1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenu1.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jMenu1.setPreferredSize(new java.awt.Dimension(153, 19));

        RegistrarUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/user1(16x16).png"))); // NOI18N
        RegistrarUsuario.setText("Registrar Usuario");
        RegistrarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RegistrarUsuarioActionPerformed(evt);
            }
        });
        jMenu1.add(RegistrarUsuario);

        IniciarSesión.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/pass1(16x16).png"))); // NOI18N
        IniciarSesión.setText("Iniciar Sesión");
        IniciarSesión.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IniciarSesiónActionPerformed(evt);
            }
        });
        jMenu1.add(IniciarSesión);
        jMenu1.add(jSeparator1);

        Cerrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/close(16x16).png"))); // NOI18N
        Cerrar.setText("Salir");
        Cerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CerrarActionPerformed(evt);
            }
        });
        jMenu1.add(Cerrar);

        jMenuBar1.add(jMenu1);

        jMenu3.setText("    Cliente/Ficha");
        jMenu3.setToolTipText("");
        jMenu3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenu3.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jMenu3.setPreferredSize(new java.awt.Dimension(153, 19));

        RegistrarCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/user(16x16).png"))); // NOI18N
        RegistrarCliente.setText("Registrar Cliente");
        RegistrarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RegistrarClienteActionPerformed(evt);
            }
        });
        jMenu3.add(RegistrarCliente);

        ConsultarCliente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/b1.png"))); // NOI18N
        ConsultarCliente.setText("Consultar Cliente");
        ConsultarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ConsultarClienteActionPerformed(evt);
            }
        });
        jMenu3.add(ConsultarCliente);

        RegistrarFichaMedica.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/reporteFM4(16x16).png"))); // NOI18N
        RegistrarFichaMedica.setText("Registrar Ficha Medica");
        RegistrarFichaMedica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RegistrarFichaMedicaActionPerformed(evt);
            }
        });
        jMenu3.add(RegistrarFichaMedica);

        ConsultarFichaMedica.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/b1.png"))); // NOI18N
        ConsultarFichaMedica.setText("Consultar Ficha Medica");
        ConsultarFichaMedica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ConsultarFichaMedicaActionPerformed(evt);
            }
        });
        jMenu3.add(ConsultarFichaMedica);

        jMenuBar1.add(jMenu3);

        jMenu5.setText("        Pago Cuota");
        jMenu5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenu5.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jMenu5.setPreferredSize(new java.awt.Dimension(154, 19));

        RegistrarPagoCuota.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/reportPC1(16x16).png"))); // NOI18N
        RegistrarPagoCuota.setText("Registrar Pago Cuota");
        RegistrarPagoCuota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RegistrarPagoCuotaActionPerformed(evt);
            }
        });
        jMenu5.add(RegistrarPagoCuota);

        jMenuBar1.add(jMenu5);

        jMenu7.setText("  Actividad/Horario");
        jMenu7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenu7.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jMenu7.setPreferredSize(new java.awt.Dimension(155, 19));

        RegistrarActividad.setText("Registrar Actividad");
        RegistrarActividad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RegistrarActividadActionPerformed(evt);
            }
        });
        jMenu7.add(RegistrarActividad);

        RegistrarConsultarHorario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/reportH(16x16).png"))); // NOI18N
        RegistrarConsultarHorario.setText("Registrar/Consultar  Horario");
        RegistrarConsultarHorario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RegistrarConsultarHorarioActionPerformed(evt);
            }
        });
        jMenu7.add(RegistrarConsultarHorario);

        jMenuBar1.add(jMenu7);

        jMenu2.setText("             Rutina");
        jMenu2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenu2.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jMenu2.setPreferredSize(new java.awt.Dimension(153, 19));

        RegistrarRutina.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/reporteFM3(16x16).png"))); // NOI18N
        RegistrarRutina.setText("Registrar Rutina");
        RegistrarRutina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RegistrarRutinaActionPerformed(evt);
            }
        });
        jMenu2.add(RegistrarRutina);

        ConsultarRutina.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/b1.png"))); // NOI18N
        ConsultarRutina.setText("Consultar Rutina");
        ConsultarRutina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ConsultarRutinaActionPerformed(evt);
            }
        });
        jMenu2.add(ConsultarRutina);

        jMenuBar1.add(jMenu2);

        jMenu6.setText("            Reporte");
        jMenu6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenu6.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jMenu6.setPreferredSize(new java.awt.Dimension(153, 19));

        ReporteFichaMedica.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/reporteFM5(20x20).png"))); // NOI18N
        ReporteFichaMedica.setText("Ficha Medica");
        ReporteFichaMedica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ReporteFichaMedicaActionPerformed(evt);
            }
        });
        jMenu6.add(ReporteFichaMedica);

        ReporteHorario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/reportH(16x16).png"))); // NOI18N
        ReporteHorario.setText("Horario");
        ReporteHorario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ReporteHorarioActionPerformed(evt);
            }
        });
        jMenu6.add(ReporteHorario);

        ReporteRutina.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/reportR(20x20).png"))); // NOI18N
        ReporteRutina.setText("Rutina");
        ReporteRutina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ReporteRutinaActionPerformed(evt);
            }
        });
        jMenu6.add(ReporteRutina);

        ReportePagoCuota.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/reportPC1(16x16).png"))); // NOI18N
        ReportePagoCuota.setText("Pago Cuota");
        ReportePagoCuota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ReportePagoCuotaActionPerformed(evt);
            }
        });
        jMenu6.add(ReportePagoCuota);

        ReporteAparatosMasUsados.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/reportAp2(20x20).png"))); // NOI18N
        ReporteAparatosMasUsados.setText("Aparatos Usados");
        ReporteAparatosMasUsados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ReporteAparatosMasUsadosActionPerformed(evt);
            }
        });
        jMenu6.add(ReporteAparatosMasUsados);

        jMenuBar1.add(jMenu6);

        Aparato.setText("         Aparato");
        Aparato.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Aparato.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        Aparato.setPreferredSize(new java.awt.Dimension(153, 19));
        Aparato.addMenuListener(new javax.swing.event.MenuListener() {
            public void menuCanceled(javax.swing.event.MenuEvent evt) {
            }
            public void menuDeselected(javax.swing.event.MenuEvent evt) {
            }
            public void menuSelected(javax.swing.event.MenuEvent evt) {
                AparatoMenuSelected(evt);
            }
        });

        RegistrarAparato.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/mancuernas.png"))); // NOI18N
        RegistrarAparato.setText("Registrar Aparato");
        RegistrarAparato.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RegistrarAparatoActionPerformed(evt);
            }
        });
        Aparato.add(RegistrarAparato);

        ConsultarAparato.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/b1.png"))); // NOI18N
        ConsultarAparato.setText("Consultar Aparato");
        ConsultarAparato.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ConsultarAparatoActionPerformed(evt);
            }
        });
        Aparato.add(ConsultarAparato);

        jMenuBar1.add(Aparato);

        Salir.setText("            Salir");
        Salir.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        Salir.setPreferredSize(new java.awt.Dimension(150, 19));
        Salir.addMenuListener(new javax.swing.event.MenuListener() {
            public void menuCanceled(javax.swing.event.MenuEvent evt) {
            }
            public void menuDeselected(javax.swing.event.MenuEvent evt) {
            }
            public void menuSelected(javax.swing.event.MenuEvent evt) {
                SalirMenuSelected(evt);
            }
        });
        jMenuBar1.add(Salir);

        jMenu4.setText("           Profesor");
        jMenu4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenu4.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jMenu4.setPreferredSize(new java.awt.Dimension(153, 19));

        RegistrarProfesor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/user(16x16).png"))); // NOI18N
        RegistrarProfesor.setText("Registrar Profesor");
        RegistrarProfesor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RegistrarProfesorActionPerformed(evt);
            }
        });
        jMenu4.add(RegistrarProfesor);

        ConsultarProfesor.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/b1.png"))); // NOI18N
        ConsultarProfesor.setText("Consultar Profesor");
        ConsultarProfesor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ConsultarProfesorActionPerformed(evt);
            }
        });
        jMenu4.add(ConsultarProfesor);

        jMenuBar1.add(jMenu4);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void AparatoMenuSelected(javax.swing.event.MenuEvent evt) {//GEN-FIRST:event_AparatoMenuSelected
    
    }//GEN-LAST:event_AparatoMenuSelected

    private void RegistrarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RegistrarUsuarioActionPerformed
        RegistrarUsuario RU=new RegistrarUsuario();
        RU.setVisible(true);
    }//GEN-LAST:event_RegistrarUsuarioActionPerformed

    private void IniciarSesiónActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IniciarSesiónActionPerformed
      Logueo L=new Logueo();
      L.setVisible(true);
    }//GEN-LAST:event_IniciarSesiónActionPerformed

    private void RegistrarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RegistrarClienteActionPerformed
        RegistrarCliente RC= new RegistrarCliente();
        RC.setVisible(true);
    }//GEN-LAST:event_RegistrarClienteActionPerformed

    private void ConsultarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ConsultarClienteActionPerformed
        ConsultarCliente CC=new ConsultarCliente();
        CC.setVisible(true);
    }//GEN-LAST:event_ConsultarClienteActionPerformed

    private void RegistrarFichaMedicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RegistrarFichaMedicaActionPerformed
        RegistrarFichaMedica RFM= new RegistrarFichaMedica();
        RFM.setVisible(true);
    }//GEN-LAST:event_RegistrarFichaMedicaActionPerformed

    private void ConsultarFichaMedicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ConsultarFichaMedicaActionPerformed
        ConsultarFichaMedica CFM= new ConsultarFichaMedica();
        CFM.setVisible(true);
    }//GEN-LAST:event_ConsultarFichaMedicaActionPerformed

    private void RegistrarProfesorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RegistrarProfesorActionPerformed
        RegistrarProfesor RP=new RegistrarProfesor();
        RP.setVisible(true);
    }//GEN-LAST:event_RegistrarProfesorActionPerformed

    private void ConsultarProfesorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ConsultarProfesorActionPerformed
        ConsultarProfesor CP=new ConsultarProfesor();
        CP.setVisible(true);
    }//GEN-LAST:event_ConsultarProfesorActionPerformed

    private void RegistrarPagoCuotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RegistrarPagoCuotaActionPerformed
       RegistrarPagoCuota RPC=new RegistrarPagoCuota();
       RPC.setVisible(true);
    }//GEN-LAST:event_RegistrarPagoCuotaActionPerformed

    private void RegistrarActividadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RegistrarActividadActionPerformed
        RegistrarActividad RA=new RegistrarActividad();
        RA.setVisible(true);
    }//GEN-LAST:event_RegistrarActividadActionPerformed

    private void RegistrarConsultarHorarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RegistrarConsultarHorarioActionPerformed
        RegistrarHorario RH=new RegistrarHorario();
        RH.setVisible(true);
    }//GEN-LAST:event_RegistrarConsultarHorarioActionPerformed

    private void RegistrarRutinaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RegistrarRutinaActionPerformed
        RegistrarRutina RR=new RegistrarRutina();
        RR.setVisible(true);
    }//GEN-LAST:event_RegistrarRutinaActionPerformed

    private void ConsultarRutinaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ConsultarRutinaActionPerformed
        ConsultarRutina CR=new ConsultarRutina();
        CR.setVisible(true);
    }//GEN-LAST:event_ConsultarRutinaActionPerformed

    private void ReporteHorarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ReporteHorarioActionPerformed
       ConexionJDBC.getInstance().AbrirConexion();
       ConexionJDBC.getInstance().GenerarReporteHorario();
    }//GEN-LAST:event_ReporteHorarioActionPerformed

    private void ReporteRutinaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ReporteRutinaActionPerformed
      ParaGenerarRutina PGR=new ParaGenerarRutina();
      PGR.setVisible(true);       
    }//GEN-LAST:event_ReporteRutinaActionPerformed

    private void ReporteAparatosMasUsadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ReporteAparatosMasUsadosActionPerformed
       ConexionJDBC.getInstance().AbrirConexion();
       ConexionJDBC.getInstance().GenerarReporteAparatosMasUsados();
    }//GEN-LAST:event_ReporteAparatosMasUsadosActionPerformed

    private void ReportePagoCuotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ReportePagoCuotaActionPerformed
        ParaGenerarPagoCuota PGPC=new ParaGenerarPagoCuota();
        PGPC.setVisible(true);
    }//GEN-LAST:event_ReportePagoCuotaActionPerformed

    private void ReporteFichaMedicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ReporteFichaMedicaActionPerformed
        ParaGenerarFichaMedica PGFM=new ParaGenerarFichaMedica();
        PGFM.setVisible(true);
    }//GEN-LAST:event_ReporteFichaMedicaActionPerformed

    private void CerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CerrarActionPerformed
       int res=JOptionPane.showConfirmDialog(this,"¿Seguro que desea salir?","Salir",JOptionPane.YES_NO_OPTION);
       if(res==JOptionPane.YES_OPTION)
       {
         System.exit(0);
       }
    }//GEN-LAST:event_CerrarActionPerformed

    private void SalirMenuSelected(javax.swing.event.MenuEvent evt) {//GEN-FIRST:event_SalirMenuSelected
         int res=JOptionPane.showConfirmDialog(this,"¿Seguro que desea salir?","Salir",JOptionPane.YES_NO_OPTION);
       if(res==JOptionPane.YES_OPTION)
       {
           System.exit(0);
       }
    }//GEN-LAST:event_SalirMenuSelected

    private void ConsultarAparatoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ConsultarAparatoActionPerformed
        ConsultarAparato CA= new ConsultarAparato();
        CA.setVisible(true);
    }//GEN-LAST:event_ConsultarAparatoActionPerformed

    private void RegistrarAparatoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RegistrarAparatoActionPerformed
        RegistrarAparato RA=new RegistrarAparato();
        RA.setVisible(true);
    }//GEN-LAST:event_RegistrarAparatoActionPerformed

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
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Menu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Menu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu Aparato;
    private javax.swing.JMenuItem Cerrar;
    private javax.swing.JMenuItem ConsultarAparato;
    private javax.swing.JMenuItem ConsultarCliente;
    private javax.swing.JMenuItem ConsultarFichaMedica;
    private javax.swing.JMenuItem ConsultarProfesor;
    private javax.swing.JMenuItem ConsultarRutina;
    private javax.swing.JMenuItem IniciarSesión;
    private javax.swing.JMenuItem RegistrarActividad;
    private javax.swing.JMenuItem RegistrarAparato;
    private javax.swing.JMenuItem RegistrarCliente;
    private javax.swing.JMenuItem RegistrarConsultarHorario;
    private javax.swing.JMenuItem RegistrarFichaMedica;
    private javax.swing.JMenuItem RegistrarPagoCuota;
    private javax.swing.JMenuItem RegistrarProfesor;
    private javax.swing.JMenuItem RegistrarRutina;
    private javax.swing.JMenuItem RegistrarUsuario;
    private javax.swing.JMenuItem ReporteAparatosMasUsados;
    private javax.swing.JMenuItem ReporteFichaMedica;
    private javax.swing.JMenuItem ReporteHorario;
    private javax.swing.JMenuItem ReportePagoCuota;
    private javax.swing.JMenuItem ReporteRutina;
    private javax.swing.JMenu Salir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JLabel lblF;
    private javax.swing.JLabel lblH;
    private javax.swing.JLabel lblImagen;
    // End of variables declaration//GEN-END:variables
}
