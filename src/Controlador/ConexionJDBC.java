/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Controlador;

import Modelo.*;
import gripgym.*;
import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.*;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Vero
 */
public class ConexionJDBC 
{
    private Connection con;
    private Statement st;
    private ResultSet rs;
    private static ConexionJDBC instancia;
    private ArrayList array;
  

    
    private ConexionJDBC()
    { }

 public static ConexionJDBC getInstance() 
 {
     if (instancia == null) 
     {instancia = new ConexionJDBC();}
     return instancia;
 }
 
  public void AbrirConexion()
    { 
        try 
        {
            String userName = "sa";
            String password = "DateBaseSQL";

            String url = "jdbc:sqlserver://localhost:1433;database=GripGym";
       
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
            con = DriverManager.getConnection(url, userName, password);
            
            System.out.println("Conexión a la BD");
	} 
        catch (Exception e) 
            {System.out.println("ERROR EN ABRIR CONEXION!! ");}

    }

    public void Cerrar()
    {
        try
         {	
             con.close();
	     System.out.println("Conexión cerrada");
          } 
        catch (SQLException e) 
          {System.out.println("Error al cerrar conexión");}
    }
    
    
    
    
    
////////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
    
  
    
       //Metodo Combo Barrio.
    public ArrayList getBarrio()
    {
        ArrayList lista=new ArrayList();
        int IdBarrio;
        String barrio;
        Barrio B=null;
        
        try
        {
            AbrirConexion();
            st=con.createStatement();
            String sql="Select IdBarrio, barrio from Barrio";
            rs=st.executeQuery(sql);
            
            while(rs.next())
            {
                IdBarrio=rs.getInt("IdBarrio");
                barrio=rs.getString("barrio");
                B= new  Barrio();
                B.setIdBarrio(IdBarrio);
                B.setBarrio(barrio);
                
                lista.add(B);
            }
        }
        catch(Exception e)
        {System.out.println("Error en la consulta:"+e.getMessage());}
        finally
        {
            try
            {Cerrar();}
            
            catch(Exception e)
            {System.out.println("Error en el cierre de la consulta getBarrio");}
        }
         return lista;  
    }
    
    
   //Metodo Combo Localidad.
    public ArrayList getLocalidad()
    {
        ArrayList lista=new ArrayList();
        int IdLocalid;
        String localid;
        Localidad L=null;
        
        try
        {
            AbrirConexion();
            st=con.createStatement();
            String sql="Select IdLocalidad, localidad from Localidad";
            rs=st.executeQuery(sql);
            
            while(rs.next())
            {
                IdLocalid=rs.getInt("IdLocalidad");
                localid=rs.getString("localidad");
                L= new  Localidad();
                L.setIdLocalidad(IdLocalid);
                L.setLocalidad(localid);
                
                lista.add(L);
            }
        }
        catch(Exception e)
        {System.out.println("Error en la consulta:"+e.getMessage());}
        finally
        {
            try
            {Cerrar();}
            
            catch(Exception e)
            {System.out.println("Error en el cierre de la consulta getLocalidad");}
        }
         return lista;  
    }
    
    
    
    
    
    ///////////// CLIENTE //////////////////////////////////////////////////////
    
       public ResultSet getCargarCliente()
    {  rs=null;
       try
       { st=con.createStatement();
       String sql="Select c.fechaIngreso,p.dni,p.nombre,p.apellido,p.fechaNacimiento,p.edad,p.sexo,p.domicilio,b.barrio,l.localidad,p.codPostal,p.telefono,p.celular,p.[e-Mail],p.foto "
                + "from Persona p, Cliente c, Barrio b, Localidad l "
                + "where p.IdPersona=c.IdPersona and p.IdBarrio=b.IdBarrio and p.IdLocalidad=l.IdLocalidad "; 
         rs=st.executeQuery(sql);
        }
       catch(Exception e){}
       
       return rs;
    }
    
      
    public ResultSet getCargarCliente(int dni)
    {  rs=null;
       try
       { st=con.createStatement();
         String sql="Select c.fechaIngreso,p.dni,p.nombre,p.apellido,p.fechaNacimiento,p.edad,p.sexo,p.domicilio,b.barrio,l.localidad,p.codPostal,p.telefono,p.celular,p.[e-Mail],p.foto from Persona p inner join Cliente c on c.IdPersona=p.IdPersona inner join Barrio b on b.IdBarrio=p.IdBarrio inner join Localidad l on l.IdLocalidad=p.IdLocalidad where p.dni='"+dni+"' "; 
         rs=st.executeQuery(sql);
       }
       catch(Exception e){}
       
       return rs;
    }
       
       
    public  boolean InsertarCliente(Persona persona){
       try {
            
            PreparedStatement stmt = con.prepareStatement("INSERT INTO Persona(nombre,apellido,fechaNacimiento,edad,sexo,dni,domicilio,IdBarrio,IdLocalidad,codPostal,telefono,celular,[e-Mail],foto) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            stmt.setString(1,persona.getNombre());
            stmt.setString(2,persona.getApellido());
            stmt.setDate(3,new java.sql.Date(persona.getFechaNac().getTime()));
            stmt.setInt(4,persona.getEdad());
            stmt.setString(5,persona.getSexo());
            stmt.setInt(6,persona.getDni());
            stmt.setString(7,persona.getDomicilio());
            stmt.setInt(8,persona.getIdBarrio());
            stmt.setInt(9,persona.getIdLocalidad());
            stmt.setInt(10,persona.getCodPostal());
            stmt.setInt(11,persona.getTel());
            stmt.setInt(12,persona.getCel());
            stmt.setString(13,persona.geteMail());
            stmt.setString(14,persona.getFoto());
            stmt.executeUpdate();
           return true;
        } catch (SQLException ex) {
            Logger.getLogger(ConexionJDBC.class.getName()).log(Level.SEVERE, null, ex);
           return false;
        }
   }

    public  boolean InsertarCliente(Cliente cliente)
    {
     try {
           PreparedStatement stmt = con.prepareStatement("INSERT INTO Cliente(IdPersona,fechaIngreso) VALUES ((select max(IdPersona) from Persona),?)");
            stmt.setDate(1,new java.sql.Date(cliente.getFechaIngreso().getTime()));
            stmt.executeUpdate();
           return true;
        } catch (SQLException ex) {
            Logger.getLogger(ConexionJDBC.class.getName()).log(Level.SEVERE, null, ex);
           return false;
        }
    }
     
    
     public boolean EditarCliente(Cliente cliente){
       try {
            PreparedStatement stmt = con.prepareStatement("UPDATE Cliente SET fechaIngreso = ? FROM Cliente c INNER JOIN Persona p on p.IdPersona=c.IdPersona WHERE p.dni = ?");
           stmt.setDate(1,new java.sql.Date(cliente.getFechaIngreso().getTime()));
            stmt.setInt(2,cliente.getDni());
           stmt.executeUpdate();
            return true;
       } catch (SQLException ex) {
           Logger.getLogger(ConexionJDBC.class.getName()).log(Level.SEVERE, null, ex);
           return false;
       }
   }    
    
    
    
     
       public  Cliente buscarClienteApe(String apellido){
        try {AbrirConexion();
            ResultSet rs;
            PreparedStatement stmt = con.prepareStatement("select c.IdPersona,c.fechaIngreso,p.nombre,p.apellido,p.fechaNacimiento,p.edad,p.sexo,p.dni,p.domicilio,b.IdBarrio,l.IdLocalidad,p.codPostal,p.telefono,p.celular,p.[e-Mail],p.foto from Cliente c inner join Persona p on c.IdPersona=p.IdPersona inner join Barrio b on b.IdBarrio=p.IdBarrio inner join Localidad l on l.IdLocalidad=p.IdLocalidad where apellido = ?");
                    
            stmt.setString(1, apellido);
            rs = stmt.executeQuery();
            while (rs.next()) {
              Cliente cliente = new Cliente(rs.getInt(1),
                                            rs.getDate(2),
                                            rs.getString(3),
                                            rs.getString(4),
                                            rs.getDate(5),
                                            rs.getInt(6),
                                            rs.getString(7),
                                            rs.getInt(8),
                                            rs.getString(9),
                                            rs.getInt(10),
                                            rs.getInt(11),
                                            rs.getInt(12),
                                            rs.getInt(13),
                                            rs.getInt(14),
                                            rs.getString(15),
                                            rs.getString(16)
                                            );
            return cliente;
            }
           return null;
        } catch (SQLException ex) {
            Logger.getLogger(ConexionJDBC.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
     
    public  Cliente buscarClienteDni(String dni){
        try {AbrirConexion();
            ResultSet rs;
            PreparedStatement stmt = con.prepareStatement("select c.IdPersona,c.fechaIngreso,p.nombre,p.apellido,p.fechaNacimiento,p.edad,p.sexo,p.dni,p.domicilio,b.IdBarrio,l.IdLocalidad,p.codPostal,p.telefono,p.celular,p.[e-Mail],p.foto from Cliente c inner join Persona p on c.IdPersona=p.IdPersona inner join Barrio b on b.IdBarrio=p.IdBarrio inner join Localidad l on l.IdLocalidad=p.IdLocalidad where dni = ?");
                    
            stmt.setString(1, dni);
            rs = stmt.executeQuery();
            while (rs.next()) {
              Cliente cliente = new Cliente(rs.getInt(1),
                                            rs.getDate(2),
                                            rs.getString(3),
                                            rs.getString(4),
                                            rs.getDate(5),
                                            rs.getInt(6),
                                            rs.getString(7),
                                            rs.getInt(8),
                                            rs.getString(9),
                                            rs.getInt(10),
                                            rs.getInt(11),
                                            rs.getInt(12),
                                            rs.getInt(13),
                                            rs.getInt(14),
                                            rs.getString(15),
                                            rs.getString(16)
                                            );
            return cliente;
            }
           return null;
        } catch (SQLException ex) {
            Logger.getLogger(ConexionJDBC.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    } 
       
       
    public ArrayList ClientePorAPELLIDO(String Apellido)
    { 
        ArrayList lista=new ArrayList();
       int idP,edad,dni,codP,tel,cel;
       String nom,ape,sexo,dom,eMail,foto,barrio,localidad;
       Date fechaIng,fechaNac;
       Cliente C=null;
       try
       { AbrirConexion();
         st=con.createStatement();
         String sql="select c.IdPersona,c.fechaIngreso,p.nombre,p.apellido,p.fechaNacimiento,p.edad,p.sexo,p.dni,p.domicilio,b.barrio,l.localidad,p.codPostal,p.telefono,p.celular,p.[e-Mail],p.foto from Cliente c inner join Persona p on c.IdPersona=p.IdPersona inner join Barrio b on b.IdBarrio=p.IdBarrio inner join Localidad l on l.IdLocalidad=p.IdLocalidad where apellido = '"+Apellido+"'";
         rs=st.executeQuery(sql);
         while(rs.next())
         { idP=rs.getInt(1);
           fechaIng=rs.getDate(2);
           nom=rs.getString(3);
           ape=rs.getString(4);
           fechaNac=rs.getDate(5);
           edad=rs.getInt(6);
           sexo=rs.getString(7);
           dni=rs.getInt(8);
           dom=rs.getString(9);
           barrio=rs.getString(10);
           localidad=rs.getString(11);
           codP=rs.getInt(12);
           tel=rs.getInt(13);
           cel=rs.getInt(14);
           eMail=rs.getString(15);
           foto=rs.getString(16);

           C=new Cliente();
           C.setIdPersona(idP);
           C.setFechaIngreso(fechaIng);
           C.setNombre(nom);
           C.setApellido(ape);
           C.setFechaNac(fechaNac);
           C.setEdad(edad);
           C.setSexo(sexo);
           C.setDni(dni);
           C.setDomicilio(dom);
           C.setBarrio(barrio);
           C.setLocalidad(localidad);
           C.setCodPostal(codP);
           C.setTel(tel);
           C.setCel(cel);
           C.seteMail(eMail);
           C.setFoto(foto);
                   
           lista.add(C);
         }

       }
       catch(Exception e){ System.out.println("Error en la consulta:" + e.getMessage());}
       finally
       { try{ rs.close(); Cerrar();}
         catch(Exception e){ System.out.println("Error en liberacion de recursos:" + e.getMessage());}
       }
       return lista;
    }
        
        
      public ArrayList ClientePorDNI(int Dni)
    { 
        ArrayList lista=new ArrayList();
       int idP,edad,dni,codP,tel,cel;
       String nom,ape,sexo,dom,eMail,foto,barrio,localidad;
       Date fechaIng,fechaNac;
       Cliente C=null;
       try
       { AbrirConexion();
         st=con.createStatement();
         String sql="select c.IdPersona,c.fechaIngreso,p.nombre,p.apellido,p.fechaNacimiento,p.edad,p.sexo,p.dni,p.domicilio,b.barrio,l.localidad,p.codPostal,p.telefono,p.celular,p.[e-Mail],p.foto from Cliente c inner join Persona p on c.IdPersona=p.IdPersona inner join Barrio b on b.IdBarrio=p.IdBarrio inner join Localidad l on l.IdLocalidad=p.IdLocalidad where dni = "+Dni+"";
         rs=st.executeQuery(sql);
         while(rs.next())
         { idP=rs.getInt(1);
           fechaIng=rs.getDate(2);
           nom=rs.getString(3);
           ape=rs.getString(4);
           fechaNac=rs.getDate(5);
           edad=rs.getInt(6);
           sexo=rs.getString(7);
           dni=rs.getInt(8);
           dom=rs.getString(9);
           barrio=rs.getString(10);
           localidad=rs.getString(11);
           codP=rs.getInt(12);
           tel=rs.getInt(13);
           cel=rs.getInt(14);
           eMail=rs.getString(15);
           foto=rs.getString(16);

           C=new Cliente();
           C.setIdPersona(idP);
           C.setFechaIngreso(fechaIng);
           C.setNombre(nom);
           C.setApellido(ape);
           C.setFechaNac(fechaNac);
           C.setEdad(edad);
           C.setSexo(sexo);
           C.setDni(dni);
           C.setDomicilio(dom);
           C.setBarrio(barrio);
           C.setLocalidad(localidad);
           C.setCodPostal(codP);
           C.setTel(tel);
           C.setCel(cel);
           C.seteMail(eMail);
           C.setFoto(foto);
                   
           lista.add(C);
         }

       }
       catch(Exception e){ System.out.println("Error en la consulta:" + e.getMessage());}
       finally
       { try{ rs.close(); Cerrar();}
         catch(Exception e){ System.out.println("Error en liberacion de recursos:" + e.getMessage());}
       }
       return lista;
    }   
        
        
       public String EliminarClienteC (int Dni)
    {  String aux="";
       try
       { st=con.createStatement();
         String sql="DELETE FROM Cliente FROM Cliente c INNER JOIN Persona p ON p.IdPersona=c.IdPersona WHERE dni=" + Dni +" ";
         st.executeUpdate(sql);
         aux="Borrado exitoso";
       }
       catch(Exception e){ aux="No se pudo borrar..";}
       return aux;
    }        
            
    public String EliminarClienteP (int Dni)
    {  String aux="";
       try
       { st=con.createStatement();
         String sql="DELETE FROM Persona WHERE dni=" + Dni +" ";
         st.executeUpdate(sql);
         aux="Borrado exitoso";
       }
       catch(Exception e){ aux="No se pudo borrar..";}
       return aux;
    }    
        
        
        
        
        
        
        
        
        
        
        
    ///////////// HORARIO //////////////////////////////////////////////////////    
    
     public  Horario buscarHora(String hora){
        try {AbrirConexion();
            ResultSet rs;
           PreparedStatement stmt = con.prepareStatement("select hora,dia from Horario where hora = ?");
            stmt.setString(1, hora);
            rs = stmt.executeQuery();
            while (rs.next()) {
              Horario horario = new Horario(rs.getString(1),
                                            rs.getString(2));
            return horario;
            }
           return null;
        } catch (SQLException ex) {
            Logger.getLogger(ConexionJDBC.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
     
     
      public  Horario buscarDia(String dia){
        try {AbrirConexion();
            ResultSet rs;
           PreparedStatement stmt = con.prepareStatement("select hora, dia from Horario where dia = ?");
            stmt.setString(1, dia);
            rs = stmt.executeQuery();
            while (rs.next()) {
              Horario horario = new Horario(rs.getString(1),
                                            rs.getString(2));
            return horario;
            }
           return null;
        } catch (SQLException ex) {
            Logger.getLogger(ConexionJDBC.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
      
      
    public ResultSet getCargarHoraDia()
    {  rs=null;
       try
       { st=con.createStatement();
       String sql="Select *"
                + "from Horario"; 
         rs=st.executeQuery(sql);
        }
       catch(Exception e){}
       
       return rs;
    }
    

    public ArrayList HorarioPorHora(String Hora)
    { 
        ArrayList lista=new ArrayList();
       int IdHorario;
       String hora,dia;
       Horario H=null;
       try
       { AbrirConexion();
         st=con.createStatement();
         String sql="select * from Horario where hora='"+Hora+"'";
         rs=st.executeQuery(sql);
         while(rs.next())
         { IdHorario=rs.getInt(1);
           hora=rs.getString(2);
           dia=rs.getString(3);

           H=new Horario();
           H.setIdHorario(IdHorario);
           H.setHora(hora);
           H.setDia(dia);
                   
           lista.add(H);
         }

       }
       catch(Exception e){ System.out.println("Error en la consulta:" + e.getMessage());}
       finally
       { try{ rs.close(); Cerrar();}
         catch(Exception e){ System.out.println("Error en liberacion de recursos:" + e.getMessage());}
       }
       return lista;
    }
      
    
    public ArrayList HorarioPorDia(String Dia)
    { 
        ArrayList lista=new ArrayList();
       int IdHorario;
       String hora,dia;
       Horario H=null;
       try
       { AbrirConexion();
         st=con.createStatement();               
         String sql="select * from Horario where dia='"+Dia+"'";
         rs=st.executeQuery(sql);
         while(rs.next())
         { IdHorario=rs.getInt(1);
           hora=rs.getString(2);
           dia=rs.getString(3);

           H=new Horario();
           H.setIdHorario(IdHorario);
           H.setHora(hora);
           H.setDia(dia);
                   
           lista.add(H);
         }

       }
       catch(Exception e){ System.out.println("Error en la consulta:" + e.getMessage());}
       finally
       { try{ rs.close(); Cerrar();}
         catch(Exception e){ System.out.println("Error en liberacion de recursos:" + e.getMessage());}
       }
       return lista;
    }
   
   
   
   public  boolean InsertarHorario(Horario Horario){
       try {
            
            PreparedStatement stmt = con.prepareStatement("INSERT INTO Horario(hora,dia) VALUES (?, ?)");
            stmt.setString(1,Horario.getHora());
            stmt.setString(2,Horario.getDia());
            stmt.executeUpdate();
           return true;
        } catch (SQLException ex) {
            Logger.getLogger(ConexionJDBC.class.getName()).log(Level.SEVERE, null, ex);
           return false;
        }
   }
   
    public String EditarHorario(String hora,String dia)
    { String aux="";
      try          
      { st=con.createStatement();
        String sql="update Horario set hora='" + hora + "', dia='" + dia+ "' where hora='" + hora +"' and dia='" + dia +"' ";
        st.executeUpdate(sql);
        aux="Modificación exitosa";
      }
      catch(Exception e)
      { aux="No se pudo realizar la modificacion";}
      return aux;
    }
     
   public boolean EditarH(Horario horario){
       try {
           PreparedStatement stmt = con.prepareStatement("UPDATE Horario SET hora =?, dia=? WHERE hora=? and dia=? ");
//           PreparedStatement stmt = con.prepareStatement("UPDATE Horario SET hora =?, dia=? WHERE IdHorario=(select IdHorario from Horario where hora=? and dia=?) ");
           stmt.setString(1,horario.getHora());
           stmt.setString(2,horario.getDia());
           stmt.setString(3,horario.getHora());
           stmt.setString(4,horario.getDia());
           stmt.executeUpdate();
            return true;
       } catch (SQLException ex) {
           Logger.getLogger(ConexionJDBC.class.getName()).log(Level.SEVERE, null, ex);
           return false;
       }
   }      


    public String EditarHorarioHorario(String hora,String dia)
    { String aux="";
      try          
      { st=con.createStatement();
        String sql="update Horario set hora='" + hora + "',dia='" + dia+ "' where IdHorario=(select IdHorario from Horario where hora='" + hora +"' and dia='" + dia +"')";
      st.executeUpdate(sql);
        aux="Modificación exitosa";
      }
      catch(Exception e)
      { aux="No se pudo realizar la modificacion";}
      return aux;
    }
     

      public String EliminarHorario (String Hora, String Dia)
    {  String aux="";
       try
       { st=con.createStatement();
         String sql="DELETE FROM Horario WHERE hora='" + Hora +"' and dia='" + Dia +"' ";
         st.executeUpdate(sql);
         aux="Borrado exitoso";
       }
       catch(Exception e){ aux="No se pudo borrar..";}
       return aux;
    } 
     

      
      
      
      
      
      
      
    ///////////// USUARIO //////////////////////////////////////////////////////
      
       
    public void AccesoUsuario(String usuario,String contraseña)
    {
        try
        {
            AbrirConexion();
            
            st=con.createStatement();
            String sql="Select p.nombre,u.contraseña from Usuario u inner join Persona p on p.IdPersona=u.IdPersona where p.nombre='"+usuario+"' and u.contraseña='"+contraseña+"' ";
            rs = st.executeQuery(sql);

            if(rs.next())
            {
//                JOptionPane.showMessageDialog(null,"¡Logueo Exitoso!");
                Menu M = new  Menu();
                M.setVisible(true);
            }
            else
            {
                JOptionPane.showMessageDialog(null,"¡Usuario o contraseña incorrecta!");
            }
            
        }
        catch(Exception ex)
        {System.out.println("Error en AccesoUsuario:" + ex.getMessage());}
        
    }
    
  
        public  boolean InsertarUsuarioP(Persona persona){
        try 
        {
           AbrirConexion();
          PreparedStatement stmt = con.prepareStatement("INSERT INTO Persona(nombre,apellido,fechaNacimiento,edad,sexo,dni,domicilio,IdBarrio,IdLocalidad,codPostal,telefono,celular,[e-Mail],foto) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            stmt.setString(1,persona.getNombre());
            stmt.setString(2,persona.getApellido());
            stmt.setDate(3, new java.sql.Date(persona.getFechaNac().getTime()));    
            stmt.setInt(4,persona.getEdad());
            stmt.setString(5,persona.getSexo());
            stmt.setInt(6,persona.getDni());
            stmt.setString(7,persona.getDomicilio());
            stmt.setInt(8,persona.getIdBarrio());
            stmt.setInt(9,persona.getIdLocalidad());
            stmt.setInt(10,persona.getCodPostal());
            stmt.setInt(11,persona.getTel());
            stmt.setInt(12,persona.getCel());
            stmt.setString(13,persona.geteMail());
            stmt.setString(14, persona.getFoto());
            stmt.executeUpdate();
            
            return true;
        }
        catch (SQLException ex) {
            Logger.getLogger(ConexionJDBC.class.getName()).log(Level.SEVERE, null, ex);
           return false;
        }

   }
        
    
        public  boolean InsertarUsuarioU(Usuario usuario){
        try 
        {
           AbrirConexion();
           
            PreparedStatement stmtU = con.prepareStatement("INSERT INTO Usuario(IdPersona,contraseña) VALUES ((select max(IdPersona) from Persona),?)");
            stmtU.setString(1, usuario.getContraseña());
            
            stmtU.executeUpdate();
            
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ConexionJDBC.class.getName()).log(Level.SEVERE, null, ex);
           return false;
        }
   } 
    
     public boolean EditarUsuarioP(Persona persona){
       try {
            PreparedStatement stmt = con.prepareStatement("UPDATE Persona SET nombre = ?, apellido = ?, fechaNacimiento = ?, edad = ?, sexo = ?, dni = ?, domicilio = ?, IdBarrio = ?, IdLocalidad = ?, codPostal= ?, telefono = ?, celular = ?, [e-Mail] = ?, foto = ? WHERE dni = ?");
           stmt.setString(1,persona.getNombre());
           stmt.setString(2,persona.getApellido());
           stmt.setDate(3,new java.sql.Date(persona.getFechaNac().getTime()));
           stmt.setInt(4,persona.getEdad());
           stmt.setString(5,persona.getSexo());
           stmt.setInt(6,persona.getDni());
           stmt.setString(7, persona.getDomicilio());
           stmt.setInt(8, persona.getIdBarrio());
           stmt.setInt(9, persona.getIdLocalidad());
           stmt.setInt(10, persona.getCodPostal());
           stmt.setInt(11, persona.getTel());
           stmt.setInt(12, persona.getCel());
           stmt.setString(13, persona.geteMail());
           stmt.setString(14, persona.getFoto());
           stmt.setInt(15, persona.getDni());
           stmt.executeUpdate();
            return true;
       } catch (SQLException ex) {
           Logger.getLogger(ConexionJDBC.class.getName()).log(Level.SEVERE, null, ex);
           return false;
       }
   }         
            
       public boolean EditarUsuarioU(Usuario usuario){
       try {
            PreparedStatement stmt = con.prepareStatement("UPDATE Usuario SET contraseña = ? FROM Usuario u INNER JOIN Persona p ON p.IdPersona=u.IdPersona WHERE p.dni = ?");
            stmt.setString(1,usuario.getContraseña());
            stmt.setInt(2,usuario.getDni());
            stmt.executeUpdate();
            return true;
       } catch (SQLException ex) {
           Logger.getLogger(ConexionJDBC.class.getName()).log(Level.SEVERE, null, ex);
           return false;
       }
   }        
            
             
    public String EliminarUsuarioU (int Dni)
    {  String aux="";
       try
       { st=con.createStatement();
         String sql="DELETE Usuario FROM Usuario u INNER JOIN Persona p ON p.IdPersona=u.IdPersona WHERE p.dni=" + Dni +" ";
         st.executeUpdate(sql);
         aux="Borrado exitoso";
       }
       catch(Exception e){ aux="No se pudo borrar..";}
       return aux;
    }        
            
    public String EliminarUsuarioP (int Dni)
    {  String aux="";
       try
       { st=con.createStatement();
         String sql="DELETE Persona WHERE dni=" + Dni +" ";
         st.executeUpdate(sql);
         aux="Borrado exitoso";
       }
       catch(Exception e){ aux="No se pudo borrar..";}
       return aux;
    } 
    
    
    
    
    public ResultSet getCargarUsuario(int dni)
    {  rs=null;
       try
       { st=con.createStatement();
         String sql="Select p.dni,p.nombre,p.apellido,p.fechaNacimiento,p.edad,p.sexo,p.domicilio,b.barrio,l.localidad,p.codPostal,p.telefono,p.celular,p.[e-Mail],u.contraseña,p.foto from Persona p inner join Usuario u on u.IdPersona=p.IdPersona inner join Barrio b on b.IdBarrio=p.IdBarrio inner join Localidad l on l.IdLocalidad=p.IdLocalidad where p.dni='"+dni+"' "; 
         rs=st.executeQuery(sql);
       }
       catch(Exception e){}
       
       return rs;
    }
    
    
   
    
    
    
    
    
    
        
      ///////////// ACTIVIDAD //////////////////////////////////////////////////   
         
    //Metodo Combo Tipo Actividad.
    public ArrayList getTipoActividad()
    {
        ArrayList lista=new ArrayList();
        int IdTipoActividad;
        String tipoActividad;
        TipoActividad TipoActividad=null;
        
        try
        {
            AbrirConexion();
            st=con.createStatement();
            String sql="Select IdTipoActividad, tipoActividad from TipoActividad";
            rs=st.executeQuery(sql);
            
            while(rs.next())
            {
                IdTipoActividad=rs.getInt("IdTipoActividad");
                tipoActividad=rs.getString("tipoActividad");
                TipoActividad= new  TipoActividad();
                TipoActividad.setIdTipoActividad(IdTipoActividad);
                TipoActividad.setTipoActividad(tipoActividad);
                
                lista.add(TipoActividad);
            }
        }
        catch(Exception e)
        {System.out.println("Error en la consulta:"+e.getMessage());}
        finally
        {
            try
            {Cerrar();}
            
            catch(Exception e)
            {System.out.println("Error en el cierre de la consulta getTipoActividad");}
        }
         return lista;  
    }
    
    
    public  boolean InsertarActividad(Actividad actividad, String H, String D, String TA, String ApeNomProf, String ApeNomCli){
        try 
        {
           AbrirConexion();
           PreparedStatement stmtU = con.prepareStatement(" INSERT INTO Actividad(nombre,IdHorario,IdTipoActividad,IdProfesor,IdCliente)values(?,(select IdHorario from Horario where hora='"+H+"' and dia='"+D+"'),(select IdTipoActividad from TipoActividad where tipoActividad='"+TA+"'),(select distinct pr.IdProfesor from Profesor pr left join Persona p on p.IdPersona=pr.IdPersona inner join Actividad a on a.IdProfesor=pr.IdProfesor where p.apellido+' '+p.nombre='"+ApeNomProf+"'),(select distinct c.IdCliente from Cliente c left join Persona p on p.IdPersona=c.IdPersona inner join Actividad a on a.IdCliente=c.IdCliente where p.apellido+' '+p.nombre='"+ApeNomCli+"')) ");
           stmtU.setString(1, actividad.getNombre());
            
            stmtU.executeUpdate();
            
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ConexionJDBC.class.getName()).log(Level.SEVERE, null, ex);
           return false;
        }
   } 
      
      
      
    public ResultSet getCargarActividad()
        {  
            rs=null;
            try
            { st=con.createStatement();
                String sql="Select a.nombre,h.hora,h.dia,ta.tipoActividad,p.apellido+' '+p.nombre 'Profesor', pe.apellido+' '+pe.nombre 'Cliente' from Actividad a inner join Horario h on h.IdHorario=a.IdHorario inner join TipoActividad ta on ta.IdTipoActividad=a.IdTipoActividad inner join Profesor pr on pr.IdProfesor=a.IdProfesor inner join Cliente c on c.IdCliente=a.idCliente left join Persona p on p.IdPersona=pr.IdPersona left join Persona pe on pe.IdPersona=c.IdPersona";   
                rs=st.executeQuery(sql);
            }
            catch(Exception e){}
       
            return rs;
        }
      
    
    
    
     public boolean EditarActividad(Actividad actividad){
       try {
            PreparedStatement stmt = con.prepareStatement(" UPDATE Actividad SET nombre=?,IdHorario=(select IdHorario from Horario where hora=? and dia=?),IdTipoActividad=(select IdTipoActividad from TipoActividad where tipoActividad=?)," 
                    +"IdProfesor=(select distinct pr.IdProfesor from Profesor pr left join Persona p on p.IdPersona=pr.IdPersona inner join Actividad a on a.IdProfesor=pr.IdProfesor where p.apellido+' '+p.nombre=?)," 
                    +"IdCliente=(select distinct c.IdCliente from Cliente c left join Persona p on p.IdPersona=c.IdPersona inner join Actividad a on a.IdCliente=c.IdCliente where p.apellido+' '+p.nombre=?) " 
                    +"FROM Actividad a " 
                    +"INNER JOIN Horario h on h.IdHorario=a.IdHorario" 
                    +"INNER JOIN TipoActividad ta on ta.IdTipoActividad=a.IdTipoActividad" 
                    +"INNER JOIN Profesor pr on pr.IdProfesor=a.IdProfesor" 
                    +"INNER JOIN Cliente c on c.IdCliente=a.IdCliente" 
                    +"LEFT JOIN Persona p on p.IdPersona=pr.IdPersona and p.IdPersona=c.IdCliente" 
                    +"WHERE a.nombre=? and h.IdHorario=(select IdHorario from Horario where hora=? and dia=?) ");
           
            stmt.setString(1,actividad.getNombre());
            stmt.setInt(2,actividad.getIdHorario());
            stmt.setInt(3,actividad.getIdTipoActividad());
            stmt.setInt(4,actividad.getIdProfesor());
            stmt.setInt(5,actividad.getIdCliente());
            stmt.setString(6,actividad.getNombre());
            stmt.setInt(7,actividad.getIdHorario());
        
            stmt.executeUpdate();
            return true;
       } catch (SQLException ex) {
           Logger.getLogger(ConexionJDBC.class.getName()).log(Level.SEVERE, null, ex);
           return false;
       }
   }
    
    
    
    public String EliminarActividad(String Nombre, String H, String D, String TA, String ApeNomP, String ApeNomC)
    {  String aux="";
       try
       { st=con.createStatement();
         String sql="DELETE FROM Actividad WHERE nombre='" + Nombre +"' and IdHorario=(select IdHorario from Horario where hora='" + H +"' and dia='" + D +"') and IdTipoActividad=(select IdTipoActividad from TipoActividad where tipoActividad='"+TA+"') and IdProfesor=(select distinct pr.IdProfesor from Profesor pr left join Persona p on p.IdPersona=pr.IdPersona inner join Actividad a on a.IdProfesor=pr.IdProfesor where p.apellido+' '+p.nombre='"+ApeNomP+"') and IdCliente=(select distinct c.IdCliente from Cliente c left join Persona p on p.IdPersona=c.IdPersona inner join Actividad a on a.IdCliente=c.IdCliente where p.apellido+' '+p.nombre='" + ApeNomC +"') ";
         st.executeUpdate(sql);
         aux="Borrado exitoso";
       }
       catch(Exception e){ aux="No se pudo borrar..";}
       return aux;
    } 
    
    
    
       
       
       
       
       
       
       
       
       
       
       
       
       
    /////////////// PROFESOR ///////////////////////////////////////////////////
    
    public ResultSet getCargarProfesor()
    {  rs=null;
       try
       { st=con.createStatement();
       String sql="Select p.dni,p.nombre,p.apellido,p.fechaNacimiento,p.edad,p.sexo,p.domicilio,b.barrio,l.localidad,p.codPostal,p.telefono,p.celular,p.[e-Mail],p.foto,pr.puesto "
                + "from Persona p, Profesor pr, Barrio b, Localidad l "
                + "where p.IdPersona=pr.IdPersona and p.IdBarrio=b.IdBarrio and p.IdLocalidad=l.IdLocalidad "; 
         rs=st.executeQuery(sql);
        }
       catch(Exception e){}
       
       return rs;
    }

    
     public ResultSet getCargarProfesor(int dni)
    {  rs=null;
       try
       { st=con.createStatement();
         String sql="Select p.dni,p.nombre,p.apellido,p.fechaNacimiento,p.edad,p.sexo,p.domicilio,b.barrio,l.localidad,p.codPostal,p.telefono,p.celular,p.[e-Mail],p.foto,pr.puesto from Persona p inner join Profesor pr on pr.IdPersona=p.IdPersona inner join Barrio b on b.IdBarrio=p.IdBarrio inner join Localidad l on l.IdLocalidad=p.IdLocalidad where p.dni='"+dni+"' "; 
         rs=st.executeQuery(sql);
       }
       catch(Exception e){}
       
       return rs;
    }
    
    
    public ResultSet getActualizarProfesor()
    {  rs=null;
       try
       { st=con.createStatement();
       String sql="Select p.IdPersona,p.dni,p.nombre,p.apellido,p.fechaNacimiento,p.edad,p.sexo,p.domicilio,b.barrio,l.localidad,p.codPostal,p.telefono,p.celular,p.[e-Mail],p.foto,pr.puesto "
                + "from Persona p, Profesor pr, Barrio b, Localidad l "
                + "where p.IdPersona=pr.IdPersona and p.IdBarrio=b.IdBarrio and p.IdLocalidad=l.IdLocalidad "; 
         rs=st.executeQuery(sql);
        }
       catch(Exception e){}
       
       return rs;
    }

 
    public  Profesor buscarProfesorApe(String apellido){
        try {AbrirConexion();
            ResultSet rs;
           PreparedStatement stmt = con.prepareStatement("select pr.IdPersona,p.nombre,p.apellido,pr.puesto,p.fechaNacimiento,p.edad,p.sexo,p.dni,p.domicilio,b.IdBarrio,l.IdLocalidad,p.codPostal,p.telefono,p.celular,p.[e-Mail],p.foto from Profesor pr inner join Persona p on pr.IdPersona=p.IdPersona inner join Barrio b on b.IdBarrio=p.IdBarrio inner join Localidad l on l.IdLocalidad=p.IdLocalidad where apellido = ?");
            stmt.setString(1, apellido);
            rs = stmt.executeQuery();
            while (rs.next()) {
              Profesor profesor = new Profesor( rs.getInt(1),
                                                rs.getString(2),
                                                rs.getString(3),
                                                rs.getString(4),
                                                rs.getDate(5),
                                                rs.getInt(6),
                                                rs.getString(7),
                                                rs.getInt(8),
                                                rs.getString(9),
                                                rs.getInt(10),
                                                rs.getInt(11),
                                                rs.getInt(12),
                                                rs.getInt(13),
                                                rs.getInt(14),
                                                rs.getString(15),
                                                rs.getString(16)
                                            );
            return profesor;
            }
           return null;
        } catch (SQLException ex) {
            Logger.getLogger(ConexionJDBC.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
        
     public  Profesor buscarProfesorDni(String dni){
        try {AbrirConexion();
            ResultSet rs;
           PreparedStatement stmt = con.prepareStatement("select pr.IdPersona,p.nombre,p.apellido,pr.puesto,p.fechaNacimiento,p.edad,p.sexo,p.dni,p.domicilio,b.IdBarrio,l.IdLocalidad,p.codPostal,p.telefono,p.celular,p.[e-Mail],p.foto from Profesor pr inner join Persona p on pr.IdPersona=p.IdPersona inner join Barrio b on b.IdBarrio=p.IdBarrio inner join Localidad l on l.IdLocalidad=p.IdLocalidad where dni = ?");
            stmt.setString(1, dni);
            rs = stmt.executeQuery();
            while (rs.next()) {
              Profesor profesor = new Profesor( rs.getInt(1),
                                                rs.getString(2),
                                                rs.getString(3),
                                                rs.getString(4),
                                                rs.getDate(5),
                                                rs.getInt(6),
                                                rs.getString(7),
                                                rs.getInt(8),
                                                rs.getString(9),
                                                rs.getInt(10),
                                                rs.getInt(11),
                                                rs.getInt(12),
                                                rs.getInt(13),
                                                rs.getInt(14),
                                                rs.getString(15),
                                                rs.getString(16)
                                            );
            return profesor;
            }
           return null;
        } catch (SQLException ex) {
            Logger.getLogger(ConexionJDBC.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    
    public ArrayList ProfesorPorAPELLIDO(String Apellido)
    { 
        ArrayList lista=new ArrayList();
       int IdPers,edad,dni,codP,tel,cel;
       String puesto,nom,ape,sexo,dom,eMail,foto,barrio,localidad;
       Date fechaNac;
       Profesor P=null;
       try
       { AbrirConexion();
         st=con.createStatement();               
         String sql="select pr.IdPersona,p.dni,p.nombre,p.apellido,p.fechaNacimiento,p.edad,p.sexo,p.domicilio,b.barrio,l.localidad,p.codPostal,p.telefono,p.celular,p.[e-Mail],p.foto,pr.puesto from Profesor pr inner join Persona p on pr.IdPersona=p.IdPersona inner join Barrio b on b.IdBarrio=p.IdBarrio inner join Localidad l on l.IdLocalidad=p.IdLocalidad where apellido = '"+Apellido+"'";
         rs=st.executeQuery(sql);
         while(rs.next())
         { IdPers=rs.getInt(1);
           dni=rs.getInt(2);
           nom=rs.getString(3);
           ape=rs.getString(4);
           fechaNac=rs.getDate(5);
           edad=rs.getInt(6);
           sexo=rs.getString(7);
           dom=rs.getString(8);
           barrio=rs.getString(9);
           localidad=rs.getString(10);
           codP=rs.getInt(11);
           tel=rs.getInt(12);
           cel=rs.getInt(13);
           eMail=rs.getString(14);
           foto=rs.getString(15);
           puesto=rs.getString(16);
           P=new Profesor();
           P.setIdPersona(IdPers);
           P.setNombre(nom);
           P.setApellido(ape);
           P.setPuesto(puesto);
           P.setFechaNac(fechaNac);
           P.setEdad(edad);
           P.setSexo(sexo);
           P.setDni(dni);
           P.setDomicilio(dom);
           P.setBarrio(barrio);
           P.setLocalidad(localidad);
           P.setCodPostal(codP);
           P.setTel(tel);
           P.setCel(cel);
           P.seteMail(eMail);
           P.setFoto(foto);
                   
           lista.add(P);
         }

       }
       catch(Exception e){ System.out.println("Error en la consulta ProfesorPorAPELLIDO:" + e.getMessage());}
       finally
       { try{ rs.close(); Cerrar();}
         catch(Exception e){ System.out.println("Error en liberacion de recursos:" + e.getMessage());}
       }
       return lista;
    }
   
    
     public ArrayList ProfesorPorDNI(int Dni)
    { 
        ArrayList lista=new ArrayList();
       int IdPers,edad,dni,codP,tel,cel;
       String puesto,nom,ape,sexo,dom,eMail,foto,barrio,localidad;
       Date fechaNac;
       Profesor P=null;
       try
       { AbrirConexion();
         st=con.createStatement();               
         String sql="select pr.IdPersona,p.dni,p.nombre,p.apellido,p.fechaNacimiento,p.edad,p.sexo,p.domicilio,b.barrio,l.localidad,p.codPostal,p.telefono,p.celular,p.[e-Mail],p.foto,pr.puesto from Profesor pr inner join Persona p on pr.IdPersona=p.IdPersona inner join Barrio b on b.IdBarrio=p.IdBarrio inner join Localidad l on l.IdLocalidad=p.IdLocalidad where dni = "+Dni+"";
         rs=st.executeQuery(sql);
         while(rs.next())
         { IdPers=rs.getInt(1);
           dni=rs.getInt(2);
           nom=rs.getString(3);
           ape=rs.getString(4);
           fechaNac=rs.getDate(5);
           edad=rs.getInt(6);
           sexo=rs.getString(7);
           dom=rs.getString(8);
           barrio=rs.getString(9);
           localidad=rs.getString(10);
           codP=rs.getInt(11);
           tel=rs.getInt(12);
           cel=rs.getInt(13);
           eMail=rs.getString(14);
           foto=rs.getString(15);
           puesto=rs.getString(16);
           
           P=new Profesor();
           P.setIdPersona(IdPers);
           P.setNombre(nom);
           P.setApellido(ape);
           P.setPuesto(puesto);
           P.setFechaNac(fechaNac);
           P.setEdad(edad);
           P.setSexo(sexo);
           P.setDni(dni);
           P.setDomicilio(dom);
           P.setBarrio(barrio);
           P.setLocalidad(localidad);
           P.setCodPostal(codP);
           P.setTel(tel);
           P.setCel(cel);
           P.seteMail(eMail);
           P.setFoto(foto);
                   
           lista.add(P);
         }

       }
       catch(Exception e){ System.out.println("Error en la consulta ProfesorPorDNI:" + e.getMessage());}
       finally
       { try{ rs.close(); Cerrar();}
         catch(Exception e){ System.out.println("Error en liberacion de recursos:" + e.getMessage());}
       }
       return lista;
    }
    
    
    
  
      public  boolean InsertarProfesor(Persona persona)
      {
     try {
            
            PreparedStatement stmt = con.prepareStatement("INSERT INTO Persona(nombre,apellido,fechaNacimiento,edad,sexo,dni,domicilio,IdBarrio,IdLocalidad,codPostal,telefono,celular,[e-Mail],foto) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            stmt.setString(1,persona.getNombre());
            stmt.setString(2,persona.getApellido());
            stmt.setDate(3,new java.sql.Date(persona.getFechaNac().getTime()));
            stmt.setInt(4,persona.getEdad());
            stmt.setString(5,persona.getSexo());
            stmt.setInt(6,persona.getDni());
            stmt.setString(7,persona.getDomicilio());
            stmt.setInt(8,persona.getIdBarrio());
            stmt.setInt(9,persona.getIdLocalidad());
            stmt.setInt(10,persona.getCodPostal());
            stmt.setInt(11,persona.getTel());
            stmt.setInt(12,persona.getCel());
            stmt.setString(13,persona.geteMail());
            stmt.setString(14,persona.getFoto());
            
            stmt.executeUpdate();
           return true;
        } catch (SQLException ex) {
            Logger.getLogger(ConexionJDBC.class.getName()).log(Level.SEVERE, null, ex);
           return false;
        }
   }
    
    
    public  boolean InsertarProfesor(Profesor profesor)
    {
     try {
           PreparedStatement stmt = con.prepareStatement("INSERT INTO Profesor(IdPersona,puesto) VALUES ((select max(IdPersona) from Persona),?)");
            stmt.setString(1,profesor.getPuesto());
            stmt.executeUpdate();
           return true;
        } catch (SQLException ex) {
            Logger.getLogger(ConexionJDBC.class.getName()).log(Level.SEVERE, null, ex);
           return false;
        }
    }
    
    
     public boolean EditarProfesorP(Profesor profesor){
       try {
            PreparedStatement stmt = con.prepareStatement("UPDATE Persona SET nombre = ?, apellido = ?, fechaNacimiento = ?, edad = ?, sexo = ?, dni = ?, domicilio = ?, IdBarrio = ?, IdLocalidad = ?, codPostal= ?, telefono = ?, celular = ?, [e-Mail] = ?, foto = ? WHERE dni = ?");
           stmt.setString(1,profesor.getNombre());
           stmt.setString(2,profesor.getApellido());
           stmt.setDate(3,new java.sql.Date(profesor.getFechaNac().getTime()));
           stmt.setInt(4,profesor.getEdad());
           stmt.setString(5,profesor.getSexo());
           stmt.setInt(6,profesor.getDni());
           stmt.setString(7, profesor.getDomicilio());
           stmt.setInt(8, profesor.getIdBarrio());
           stmt.setInt(9, profesor.getIdLocalidad());
           stmt.setInt(10, profesor.getCodPostal());
           stmt.setInt(11, profesor.getTel());
           stmt.setInt(12, profesor.getCel());
           stmt.setString(13, profesor.geteMail());
           stmt.setString(14, profesor.getFoto());
           stmt.setInt(15, profesor.getDni());
           stmt.executeUpdate();
            return true;
       } catch (SQLException ex) {
           Logger.getLogger(ConexionJDBC.class.getName()).log(Level.SEVERE, null, ex);
           return false;
       }
   }         
            
       public boolean EditarProfesor(Profesor profesor){
       try {
            PreparedStatement stmt = con.prepareStatement("UPDATE Profesor SET puesto = ? FROM Profesor pr INNER JOIN Persona p on p.IdPersona=pr.IdPersona WHERE p.dni = ?");
           stmt.setString(1,profesor.getPuesto());
            stmt.setInt(2,profesor.getDni());
           stmt.executeUpdate();
            return true;
       } catch (SQLException ex) {
           Logger.getLogger(ConexionJDBC.class.getName()).log(Level.SEVERE, null, ex);
           return false;
       }
   }        
            
    
    public String EliminarProfesorPr (int Dni)
    {  String aux="";
       try
       { st=con.createStatement();
         String sql="DELETE FROM Profesor FROM Profesor pr INNER JOIN Persona p ON p.IdPersona=pr.IdPersona WHERE dni=" + Dni +" ";
         st.executeUpdate(sql);
         aux="Borrado exitoso";
       }
       catch(Exception e){ aux="No se pudo borrar..";}
       return aux;
    }        
            
    public String EliminarProfesorP (int Dni)
    {  String aux="";
       try
       { st=con.createStatement();
         String sql="DELETE FROM Persona WHERE dni=" + Dni +" ";
         st.executeUpdate(sql);
         aux="Borrado exitoso";
       }
       catch(Exception e){ aux="No se pudo borrar..";}
       return aux;
    } 
    
    
    
    
    
    
    
    
    
    //////////////////////////// RUTINA ////////////////////////////////////////
    public ResultSet getCargarRutina()
        {  
            rs=null;
            try
            { st=con.createStatement();
            String sql="Select r.nombre,r.repeticion,r.serie,r.dia,p.apellido+' '+p.nombre 'Cliente',p.dni from Rutina r inner join Cliente c on c.IdCliente=r.IdCliente left join Persona p on p.IdPersona=c.IdPersona";   
                rs=st.executeQuery(sql);
            }
            catch(Exception e){}
       
            return rs;
        }
    
    
    public  Rutina buscarRutinaApe(String apellidoCliente){
        try {AbrirConexion();
            ResultSet rs;
           PreparedStatement stmt = con.prepareStatement("select r.nombre,r.repeticion,r.serie,r.dia,c.IdCliente from Rutina r inner join Cliente c on c.IdCliente=r.IdCliente left join Persona p on p.IdPersona=c.IdPersona where p.apellido = ?");
            stmt.setString(1, apellidoCliente);
            rs = stmt.executeQuery();
            while (rs.next()) {
              Rutina rutina = new Rutina( rs.getString(1),
                                                rs.getInt(2),
                                                rs.getInt(3),
                                                rs.getString(4),
                                                rs.getInt(5)
                                            );
            return rutina;
            }
           return null;
        } catch (SQLException ex) {
            Logger.getLogger(ConexionJDBC.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
        
    
    public ArrayList RutinaPorAPELLIDO(String ApellidoCliente)
    { 
        ArrayList lista=new ArrayList();
       int repet,serie,dniC;
       String nom,nomC,dia;
       Rutina R=null;
       try
       { AbrirConexion();
         st=con.createStatement();               
         String sql="select r.nombre,r.repeticion,r.serie,r.dia,p.apellido+' '+p.nombre 'Cliente',p.dni from Rutina r inner join Cliente c on c.IdCliente=r.IdCliente left join Persona p on p.IdPersona=c.IdPersona where p.apellido = '"+ApellidoCliente+"' or p.apellido+' '+p.nombre= '"+ApellidoCliente+"' ";
         rs=st.executeQuery(sql);
         while(rs.next())
         { nom=rs.getString(1);
           repet=rs.getInt(2);
           serie=rs.getInt(3);
           dia=rs.getString(4);
           nomC=rs.getString(5);
           dniC=rs.getInt(6);
           
           R=new Rutina();
           R.setNombre(nom);
           R.setRepeticion(repet);
           R.setSerie(serie);
           R.setDia(dia);
           R.setNomCliente(nomC);
           R.setDniCliente(dniC);
           
           lista.add(R);
         }

       }
       catch(Exception e){ System.out.println("Error en la consulta RutinaPorAPELLIDO:" + e.getMessage());}
       finally
       { try{ rs.close(); Cerrar();}
         catch(Exception e){ System.out.println("Error en liberacion de recursos:" + e.getMessage());}
       }
       return lista;
    }
    
    
    
     public  Rutina buscarRutinaDni(String dniCliente){
        try {AbrirConexion();
            ResultSet rs;
           PreparedStatement stmt = con.prepareStatement("select r.nombre,r.repeticion,r.serie,r.dia,c.IdCliente from Rutina r inner join Cliente c on c.IdCliente=r.IdCliente left join Persona p on p.IdPersona=c.IdPersona where p.dni = ?");
            stmt.setString(1, dniCliente);
            rs = stmt.executeQuery();
            while (rs.next()) {
              Rutina rutina = new Rutina( rs.getString(1),
                                                rs.getInt(2),
                                                rs.getInt(3),
                                                rs.getString(4),
                                                rs.getInt(5)
                                            );
            return rutina;
            }
           return null;
        } catch (SQLException ex) {
            Logger.getLogger(ConexionJDBC.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
        
    
    public ArrayList RutinaPorDNI(int DniCliente)
    { 
        ArrayList lista=new ArrayList();
       int repet,serie,dniC;
       String nom,nomC,dia;
       Rutina R=null;
       try
       { AbrirConexion();
         st=con.createStatement();               
         String sql="select r.nombre,r.repeticion,r.serie,r.dia,p.apellido+' '+p.nombre 'Cliente',p.dni from Rutina r inner join Cliente c on c.IdCliente=r.IdCliente left join Persona p on p.IdPersona=c.IdPersona where p.dni = '"+DniCliente+"' ";
         rs=st.executeQuery(sql);
         while(rs.next())
         { nom=rs.getString(1);
           repet=rs.getInt(2);
           serie=rs.getInt(3);
           dia=rs.getString(4);
           nomC=rs.getString(5);
           dniC=rs.getInt(6);
           
           R=new Rutina();
           R.setNombre(nom);
           R.setRepeticion(repet);
           R.setSerie(serie);
           R.setDia(dia);
           R.setNomCliente(nomC);
           R.setDniCliente(dniC);
           
           lista.add(R);
         }

       }
       catch(Exception e){ System.out.println("Error en la consulta RutinaPorDNI:" + e.getMessage());}
       finally
       { try{ rs.close(); Cerrar();}
         catch(Exception e){ System.out.println("Error en liberacion de recursos:" + e.getMessage());}
       }
       return lista;
    }
    
    
    
    
    public  boolean InsertarRutina(Rutina rutina, String ApeNom){
        try 
        {
           AbrirConexion();
           PreparedStatement stmtU = con.prepareStatement("INSERT INTO Rutina(nombre,repeticion,serie,dia,IdCliente)values(?,?,?,?,(select distinct c.IdCliente from Cliente c left join Persona p on p.IdPersona=c.IdPersona inner join Rutina r on r.IdCliente=c.IdCliente where p.apellido+' '+p.nombre= '"+ApeNom+"'))");
           stmtU.setString(1, rutina.getNombre());
           stmtU.setInt(2, rutina.getRepeticion());
           stmtU.setInt(3, rutina.getSerie());
           stmtU.setString(4, rutina.getDia());
            
            stmtU.executeUpdate();
            
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ConexionJDBC.class.getName()).log(Level.SEVERE, null, ex);
           return false;
        }
   } 
    
      public String EliminarRutina (String Nombre,int Repeticion,int Serie, String Dia, String ApeNom)
    {  String aux="";
       try
       { st=con.createStatement();
         String sql="DELETE FROM Rutina WHERE nombre='" + Nombre +"' and repeticion=" + Repeticion +" and serie=" + Serie +" and dia='" + Dia +"' and IdCliente=(select distinct c.IdCliente from Cliente c left join Persona p on p.IdPersona=c.IdPersona inner join Rutina r on r.IdCliente=c.IdCliente where p.apellido+' '+p.nombre='" + ApeNom +"') ";
         st.executeUpdate(sql);
         aux="Borrado exitoso";
       }
       catch(Exception e){ aux="No se pudo borrar..";}
       return aux;
    } 
    
    
    public boolean EditarRutina(Rutina rutina){
       try {
            PreparedStatement stmt = con.prepareStatement("UPDATE Rutina SET nombre =?, repeticion=?, serie=?, dia=? FROM Rutina r INNER JOIN Cliente c on c.IdCliente=r.IdCliente LEFT JOIN Persona p on p.IdPersona=c.IdPersona WHERE p.apellido+' '+p.nombre=? and r.dia=?");
            stmt.setString(1,rutina.getNombre());
            stmt.setInt(2,rutina.getRepeticion());
            stmt.setInt(3,rutina.getSerie());
            stmt.setString(4,rutina.getDia());
            stmt.setString(5,rutina.getNomCliente());
            stmt.setString(6,rutina.getDia());
            stmt.executeUpdate();
            return true;
       } catch (SQLException ex) {
           Logger.getLogger(ConexionJDBC.class.getName()).log(Level.SEVERE, null, ex);
           return false;
       }
   }
    
     
    public ResultSet getRutina()
    {  rs=null;
       try
       { st=con.createStatement();
         String sql="Select p.apellido+'; '+p.nombre 'Cliente',r.nombre,r.repeticion,r.serie,r.dia from Rutina r inner join Cliente c on c.IdCliente=r.IdCliente left join Persona p on p.IdPersona=c.IdPersona";
         rs=st.executeQuery(sql);
        }
       catch(Exception e){}
       
       return rs;
    }  
      
      
    
    
    
    
    
    
    
    //////////////////////////// REPORTES //////////////////////////////////////
     public void GenerarReporteHorario()
     {
        try
         { 
            JasperReport reporte = (JasperReport) JRLoader.loadObject("D:\\TESIS GRIP GYM\\GripGym\\src\\Reportes\\ReporteHorarios.jasper");
            JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, null, con);

            JasperViewer jviewer = new JasperViewer(jasperPrint, false);
            jviewer.setVisible(true);
        }
         catch(JRException ex)
         {Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);}
     }
    
     
    public void GenerarReporteRutina()
    {
       try
       {
           JasperReport reporte = (JasperReport) JRLoader.loadObject("D:\\TESIS GRIP GYM\\GripGym\\src\\Reportes\\ReporteRutina.jasper");
           Map parametro=new HashMap();
           parametro.put("ApeNomCliente", RegistrarRutina.txtCliente.getText());
           
           JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametro, con);
           JasperViewer jviewer = new JasperViewer(jasperPrint, false);
           jviewer.setVisible(true);
       }
       catch(JRException ex)
       {Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);}
    }
     
         public void GenerarReporteRutinaMenu(int dni)
    {
       try
       {
           JasperReport reporte = (JasperReport) JRLoader.loadObject("D:\\TESIS GRIP GYM\\GripGym\\src\\Reportes\\ReporteRutina.jasper");
           Map parametro=new HashMap();
//           parametro.put("Dni", ParaGenerarRutina.txtDniCliente.getText());
            parametro.put("Dni",dni);
           JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametro, con);
           JasperViewer jviewer = new JasperViewer(jasperPrint, false);
           jviewer.setVisible(true);
       }
       catch(JRException ex)
       {Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);}
    }
     
         
    
    public void GenerarReporteAparatosMasUsados()
     {
        try
         { 
            JasperReport reporte = (JasperReport) JRLoader.loadObject("D:\\TESIS GRIP GYM\\GripGym\\src\\Reportes\\ReporteAparatos.jasper");
            JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, null, con);

            JasperViewer jviewer = new JasperViewer(jasperPrint, false);
            jviewer.setVisible(true);
        }
         catch(JRException ex)
         {Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);}
     }
         
         
    public void GenerarReportePagoCuotaMenu(int dni)
    {
       try
       {
           JasperReport reporte = (JasperReport) JRLoader.loadObject("D:\\TESIS GRIP GYM\\GripGym\\src\\Reportes\\ReportePagoCuota.jasper");
           Map parametro=new HashMap();
//           parametro.put("ApeNomCliente", ParaGenerarPagoCuota.txtDniCliente.getText());
           parametro.put("Dni",dni);
           
           JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametro, con);
           JasperViewer jviewer = new JasperViewer(jasperPrint, false);
           jviewer.setVisible(true);
       }
       catch(JRException ex)
       {Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);}
    }  
     
    
//       public void GenerarReportePagoCuota()
//    {
//       try
//       {
//           JasperReport reporte = (JasperReport) JRLoader.loadObject("D:\\TESIS GRIP GYM\\GripGym\\src\\Reportes\\ReportePagoCuota.jasper");
//           Map parametro=new HashMap();
//           parametro.put("ApeNomCliente", RegistrarPagoCuota.txtApeNom.getText());
//           
//           JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametro, con);
//           JasperViewer jviewer = new JasperViewer(jasperPrint, false);
//           jviewer.setVisible(true);
//       }
//       catch(JRException ex)
//       {Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);}
//    }  
    
       
       
    public void GenerarReporteFichaMedicaMenu(int dni)
    {
       try
       {
           JasperReport reporte = (JasperReport) JRLoader.loadObject("D:\\TESIS GRIP GYM\\GripGym\\src\\Reportes\\ReporteFichaMedica.jasper");
           Map parametro=new HashMap();
//           parametro.put("ApeNomCliente", ParaGenerarFichaMedica.txtCliente.getText());
            parametro.put("Dni",dni); 
           
           JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametro, con);
           JasperViewer jviewer = new JasperViewer(jasperPrint, false);
           jviewer.setVisible(true);
       }
       catch(JRException ex)
       {Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);}
    } 
       

     
    
    
    
    
    
    
    
    
    
    
    
    
    
   
    //////////////////////////// FICHA MÉDICA //////////////////////////////////
    
         public ResultSet getCargarFichaMedica()
        {  
            rs=null;
            try
            { st=con.createStatement();
            String sql="select fm.IdFichaMedica,p.dni,p.apellido+' '+p.nombre 'Cliente',fm.grupoSanguineo,fm.telefonoEmergencia,fm.mutual,fm.enfermedad,fm.observacion from FichaMedica fm inner join Cliente c on c.IdCliente=fm.IdCliente left join Persona p on p.IdPersona=c.IdPersona";   
                rs=st.executeQuery(sql);
            }
            catch(Exception e){}
       
            return rs;
        }
         
       public ResultSet getCargarTablaFichaMedica()
        {  
            rs=null;
            try
            { st=con.createStatement();
            String sql="select fm.IdFichaMedica,p.dni,p.apellido+' '+p.nombre'Cliente',p.edad,p.sexo,p.telefono,p.celular,fm.telefonoEmergencia,fm.grupoSanguineo,fm.mutual,fm.enfermedad,fm.observacion from FichaMedica fm inner join Cliente c on c.IdCliente=fm.IdCliente left join Persona p on p.IdPersona=c.IdPersona";   
                rs=st.executeQuery(sql);
            }
            catch(Exception e){}
       
            return rs;
        }    
         
        
    public  FichaMedica buscarFichaMedicaApe(String apellido){
        try {AbrirConexion();
            ResultSet rs;
            PreparedStatement stmt = con.prepareStatement("select fm.IdFichaMedica,fm.grupoSanguineo,fm.telefonoEmergencia,fm.mutual,fm.enfermedad,fm.observacion from FichaMedica fm inner join Cliente c on c.IdCliente=fm.IdCliente left join Persona p on p.IdPersona=c.IdPersona where p.apellido = ?");
            stmt.setString(1, apellido);
            rs = stmt.executeQuery();
            while (rs.next()) {
            FichaMedica fichaMedica = new FichaMedica( rs.getInt(1),
                                                rs.getString(2),
                                                rs.getInt(3),
                                                rs.getString(4),
                                                rs.getString(5),
                                                rs.getString(6)
                                            );
            return fichaMedica;
            }
           return null;
        } catch (SQLException ex) {
            Logger.getLogger(ConexionJDBC.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
        
     public  FichaMedica buscarFichaMedicaDni(String dni){
        try {AbrirConexion();
            ResultSet rs;
           PreparedStatement stmt = con.prepareStatement("select fm.IdFichaMedica,fm.grupoSanguineo,fm.telefonoEmergencia,fm.mutual,fm.enfermedad,fm.observacion from FichaMedica fm inner join Cliente c on c.IdCliente=fm.IdCliente left join Persona p on p.IdPersona=c.IdPersona where dni = ?");
            stmt.setString(1, dni);
            rs = stmt.executeQuery();
            while (rs.next()) {
            FichaMedica fichaMedica = new FichaMedica( rs.getInt(1),
                                                rs.getString(2),
                                                rs.getInt(3),
                                                rs.getString(4),
                                                rs.getString(5),
                                                rs.getString(6)
                                            );
            return fichaMedica;
            }
           return null;
        } catch (SQLException ex) {
            Logger.getLogger(ConexionJDBC.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    
    public ArrayList FichaMedicaPorAPELLIDO(String Apellido)
    { 
        ArrayList lista=new ArrayList();
       int IdFM,telE,dni;
       String grupoS,mutual,enf,obs,apenom;
       Persona P=null;
       FichaMedica FM=null;
       try
       { AbrirConexion();
         st=con.createStatement();               
         String sql="select fm.IdFichaMedica,p.dni,p.apellido+' '+p.nombre 'Cliente',fm.grupoSanguineo,fm.telefonoEmergencia,fm.mutual,fm.enfermedad,fm.observacion from FichaMedica fm inner join Cliente c on c.IdCliente=fm.IdCliente left join Persona p on p.IdPersona=c.IdPersona where p.apellido = '"+Apellido+"'";
         rs=st.executeQuery(sql);
         while(rs.next())
         { IdFM=rs.getInt(1);
           dni=rs.getInt(2);
           apenom=rs.getString(3);
           grupoS=rs.getString(4);
           telE=rs.getInt(5);
           mutual=rs.getString(6);
           enf=rs.getString(7);
           obs=rs.getString(8);
           
           FM=new FichaMedica();
           P=new Persona();
           FM.setIdFichaMedica(IdFM);
           P.setDni(dni);
           P.setApellido(apenom);
           FM.setGrupoSanguineo(grupoS);
           FM.setTelefonoEmergencia(telE);
           FM.setMutual(mutual);
           FM.setEnfermedad(enf);
           FM.setObservacion(obs);
                   
           lista.add(FM);
           lista.add(P);
         }

       }
       catch(Exception e){ System.out.println("Error en la consulta FichaMedicaPorAPELLIDO:" + e.getMessage());}
       finally
       { try{ rs.close(); Cerrar();}
         catch(Exception e){ System.out.println("Error en liberacion de recursos:" + e.getMessage());}
       }
       return lista;
    }

         
    public ArrayList FichaMedicaPorDNI(int DniCliente)
    { 
        ArrayList lista=new ArrayList();
       int IdFM,telE,dni;
       String grupoS,mutual,enf,obs,apenom;
       Persona P=null;
       FichaMedica FM=null;
       try
       { AbrirConexion();
         st=con.createStatement();               
         String sql="select fm.IdFichaMedica,p.dni,p.apellido+' '+p.nombre 'Cliente',fm.grupoSanguineo,fm.telefonoEmergencia,fm.mutual,fm.enfermedad,fm.observacion from FichaMedica fm inner join Cliente c on c.IdCliente=fm.IdCliente left join Persona p on p.IdPersona=c.IdPersona where p.dni= '"+DniCliente+"'";
         rs=st.executeQuery(sql);
         while(rs.next())
         { IdFM=rs.getInt(1);
           dni=rs.getInt(2);
           apenom=rs.getString(3);
           grupoS=rs.getString(4);
           telE=rs.getInt(5);
           mutual=rs.getString(6);
           enf=rs.getString(7);
           obs=rs.getString(8);
           
           FM=new FichaMedica();
           P=new Persona();
           FM.setIdFichaMedica(IdFM);
           P.setDni(dni);
           P.setApellido(apenom);
           FM.setGrupoSanguineo(grupoS);
           FM.setTelefonoEmergencia(telE);
           FM.setMutual(mutual);
           FM.setEnfermedad(enf);
           FM.setObservacion(obs);
                   
           lista.add(FM);
           lista.add(P);
         }

       }
       catch(Exception e){ System.out.println("Error en la consulta FichaMedicaPorDNI:" + e.getMessage());}
       finally
       { try{ rs.close(); Cerrar();}
         catch(Exception e){ System.out.println("Error en liberacion de recursos:" + e.getMessage());}
       }
       return lista;
    }    
         
         
    public ResultSet getCargarClienteFichaMedica(int dni)
    {  rs=null;
       try
       { st=con.createStatement();
         String sql="select p.dni,p.apellido+' '+p.nombre'Cliente',p.edad,p.sexo,p.telefono,p.celular from Cliente c left join Persona p on p.IdPersona=c.IdPersona where p.dni='"+dni+"' "; 
         rs=st.executeQuery(sql);
       }
       catch(Exception e){}
       
       return rs;
    }    
    
    
        public  boolean InsertarFichaMedica(FichaMedica fichamedica){
        try 
        {
           AbrirConexion();
           PreparedStatement stmt= con.prepareStatement("INSERT INTO FichaMedica(IdFichaMedica,grupoSanguineo,telefonoEmergencia,mutual,enfermedad,observacion)values(?,?,?,?,?,?)");
           stmt.setInt(1, fichamedica.getIdFichaMedica());
           stmt.setString(2, fichamedica.getGrupoSanguineo());
           stmt.setInt(3, fichamedica.getTelefonoEmergencia());
           stmt.setString(4, fichamedica.getMutual());
           stmt.setString(5, fichamedica.getEnfermedad());
           stmt.setString(6, fichamedica.getObservacion());
            
            stmt.executeUpdate();
            
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ConexionJDBC.class.getName()).log(Level.SEVERE, null, ex);
           return false;
        }
   } 
    
    
    public boolean EditarFichaMedica(FichaMedica fichamedica){
       try {
            PreparedStatement stmt = con.prepareStatement("UPDATE FichaMedica SET IdFichaMedica=? ,telefonoEmergencia=? ,grupoSanguineo=? , mutual=? ,enfermedad=? ,observacion=? WHERE IdFichaMedica=? ");
            stmt.setInt(1, fichamedica.getIdFichaMedica());
            stmt.setInt(2, fichamedica.getTelefonoEmergencia());
            stmt.setString(3, fichamedica.getGrupoSanguineo());
            stmt.setString(4, fichamedica.getMutual());
            stmt.setString(5, fichamedica.getEnfermedad());
            stmt.setString(6, fichamedica.getObservacion());
            stmt.setInt(7, fichamedica.getIdFichaMedica());

            stmt.executeUpdate();
            return true;
       } catch (SQLException ex) {
           Logger.getLogger(ConexionJDBC.class.getName()).log(Level.SEVERE, null, ex);
           return false;
       }
   }
        
        
    public String EliminarFichaMedica (int NroFichaM)
    {  String aux="";
       try
       { st=con.createStatement();
         String sql="DELETE FROM FichaMedica WHERE IdFichaMedica=" + NroFichaM +" ";
         st.executeUpdate(sql);
         aux="Borrado exitoso";
       }
       catch(Exception e){ aux="No se pudo borrar..";}
       return aux;
    }    
    
    
         
         
         
         
         
         
         
         
         
    //////////////////////////// APARATO //////////////////////////////////////// 
         
            public ResultSet getCargarAparato()
        {  
            rs=null;
            try
            { st=con.createStatement();
//            String sql="select a.nombre,tipo,descripcion,r.nombre from Aparato a inner join Rutina r on r.IdRutina=a.IdRutina";   
              String sql="select nombre,tipo,descripcion from Aparato";
                rs=st.executeQuery(sql);
            }
            catch(Exception e){}
       
            return rs;
        }
            
            
            
    public  Aparato buscarAparato(String nomAparato){
        try {AbrirConexion();
            ResultSet rs;
           PreparedStatement stmt = con.prepareStatement("select a.nombre,a.tipo,a.descripcion, r.IdRutina from Aparato a inner join Rutina r on r.IdRutina=a.IdRutina where a.nombre= ?");
            stmt.setString(1, nomAparato);
            rs = stmt.executeQuery();
            while (rs.next()) {
              Aparato aparato = new Aparato( rs.getString(1),
                                                rs.getString(2),
                                                rs.getString(3),
                                                rs.getInt(4)
                                            );
            return aparato;
            }
           return null;
        } catch (SQLException ex) {
            Logger.getLogger(ConexionJDBC.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
        
    
    
     public ArrayList AparatoPorNOMBRE(String NombreAparato)
    { 
        ArrayList lista=new ArrayList();
       String nomA,tipo,des,nomR;
       Aparato A=null;
       try
       { AbrirConexion();
         st=con.createStatement();               
         String sql="select a.nombre,a.tipo,a.descripcion, r.nombre from Aparato a inner join Rutina r on r.IdRutina=a.IdRutina where a.nombre= '"+NombreAparato+"'  ";
         rs=st.executeQuery(sql);
         while(rs.next())
         { nomA=rs.getString(1);
           tipo=rs.getString(2);
           des=rs.getString(3);
           nomR=rs.getString(4);
           
           A=new Aparato();
           A.setNombre(nomA);
           A.setTipo(tipo);
           A.setDescripcion(des);
           A.setNomRutina(nomR);
                   
           lista.add(A);
         }

       }
       catch(Exception e){ System.out.println("Error en la consulta AparatoPorNOMBRE:" + e.getMessage());}
       finally
       { try{ rs.close(); Cerrar();}
         catch(Exception e){ System.out.println("Error en liberacion de recursos:" + e.getMessage());}
       }
       return lista;
    }
    
    
    public  boolean InsertarAparato(Aparato aparato){
        try 
        {
           AbrirConexion();
           PreparedStatement stmtU = con.prepareStatement("INSERT INTO Aparato(nombre,tipo,descripcion)values(?,?,?)");
           stmtU.setString(1, aparato.getNombre());
           stmtU.setString(2, aparato.getTipo());
           stmtU.setString(3, aparato.getDescripcion());
            
            stmtU.executeUpdate();
            
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ConexionJDBC.class.getName()).log(Level.SEVERE, null, ex);
           return false;
        }
   } 
    

    
    
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
//////////////////////////// DETALLE PAGO CUOTA ////////////////////////////////
     
        
    public ResultSet getCargarNroPagoCuota()
    {  rs=null;
       try
       { st=con.createStatement();
         String sql="Select max(NroPagoCuota) + 1 from PagoCuota"; 
         rs=st.executeQuery(sql);
       }
       catch(Exception e){}
       
       return rs;
    }
    
      
      public ResultSet getCargarDetallePagoCuota(int nroPC)
        {  
            rs=null;
            try
            { st=con.createStatement();
            String sql="SELECT NroPagoCuota,descripcion,importe FROM DetallePagoCuota WHERE NroPagoCuota = "+nroPC+" ";
//            String sql="SELECT NroPagoCuota,descripcion,importe FROM DetallePagoCuota WHERE NroPagoCuota = (SELECT MAX(NroPagoCuota) FROM DetallePagoCuota)";   
                rs=st.executeQuery(sql);
            }
            catch(Exception e){}
       
            return rs;
        }
     
     
    
    public  boolean InsertarPagoCuota(PagoCuota pagocuota, int dni){
        try 
        {
           AbrirConexion();
           PreparedStatement stmt= con.prepareStatement("INSERT INTO PagoCuota(fechaDesde,fechaHasta,importe,observacion,estadoCuenta,IdCliente)values(?,?,?,?,?,(select distinct c.IdCliente from Cliente c left join Persona p on p.IdPersona=c.IdPersona where p.dni="+dni+"))");
           stmt.setDate(1, new java.sql.Date(pagocuota.getFechaDesde().getTime()));
           stmt.setDate(2, new java.sql.Date(pagocuota.getFechaHasta().getTime()));
           stmt.setDouble(3, pagocuota.getImporte());
           stmt.setString(4, pagocuota.getObservacion());
           stmt.setString(5, pagocuota.getEstadoCuenta());
            
            stmt.executeUpdate();
            
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ConexionJDBC.class.getName()).log(Level.SEVERE, null, ex);
           return false;
        }
   } 
      

    
    public  boolean InsertarDetallePagoCuota(DetallePagoCuota detallepagocuota){
        try 
        {
           AbrirConexion();
           PreparedStatement stmtU = con.prepareStatement("INSERT INTO DetallePagoCuota(NroPagoCuota,descripcion,importe)values(?,?,?)");
           stmtU.setInt(1, detallepagocuota.getNroPagoCuota());
           stmtU.setString(2, detallepagocuota.getDescripcion());
           stmtU.setDouble(3, detallepagocuota.getImporte());
            
            stmtU.executeUpdate();
            
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ConexionJDBC.class.getName()).log(Level.SEVERE, null, ex);
           return false;
        }
   } 
  
    
    public ResultSet getCargarClientePagoCuota(int dni)
    {  rs=null;
       try
       { st=con.createStatement();
         
//        String sql="select p.dni,p.apellido+' '+p.nombre'Cliente',pc.fechaDesde,pc.fechaHasta from Cliente c left join Persona p on p.IdPersona=c.IdPersona inner join PagoCuota pc on pc.IdCliente=c.IdCliente where p.dni='"+dni+"' ";
         String sql="select p.dni,p.apellido+' '+p.nombre'Cliente'  from Cliente c left join Persona p on p.IdPersona=c.IdPersona where p.dni='"+dni+"' "; 
         rs=st.executeQuery(sql);
       }
       catch(Exception e){}
       
       return rs;
    }
    
  
     public String EliminarDetallePagoCuota (int NroPC,String des,double imp)
//     public String EliminarDetallePagoCuota (int NroPC)
    {  String aux="";
       try
       { st=con.createStatement();
        String sql="DELETE FROM DetallePagoCuota WHERE NroPagoCuota=" + NroPC +" and descripcion='"+ des +"' and importe="+ imp +" ";
//         String sql="DELETE FROM DetallePagoCuota WHERE NroPagoCuota=" + NroPC +" ";
         st.executeUpdate(sql);
         aux="Borrado exitoso";
       }
       catch(Exception e){ aux="No se pudo borrar..";}
       return aux;
    } 
  

     public String EliminarDetallePagoCuota (int NroPC)
    {  String aux="";
       try
       { st=con.createStatement();
         String sql="DELETE FROM DetallePagoCuota WHERE NroPagoCuota=" + NroPC +" ";
         st.executeUpdate(sql);
         aux="Borrado exitoso";
       }
       catch(Exception e){ aux="No se pudo borrar..";}
       return aux;
    } 
     
     
     
    public ResultSet getAlertaDeudor(int dni,String fechaV,String fechaA)
    {  rs=null;
       try
       { st=con.createStatement();
         String sql="Select p.dni,p.apellido+' '+p.nombre 'Cliente',convert(varchar, pc.fechaDesde, 103)'Fecha Desde',convert(varchar, pc.fechaHasta, 103)'Fecha Hasta' from PagoCuota pc inner join Cliente c on c.IdCliente=pc.IdCliente left join Persona p on p.IdPersona=c.IdPersona where p.dni='"+dni+"' and pc.fechaHasta='"+fechaV+"' and convert(varchar, GETDATE(), 103)>='"+fechaA+"' "; 
         rs=st.executeQuery(sql);
       }
       catch(Exception e){}
       
       return rs;
    }
    

     public ResultSet getSinAlerta(int dni,Date fechaI,Date fechaV)
    {  rs=null;
       try
       { st=con.createStatement();
         String sql="Select p.dni,p.apellido+' '+p.nombre 'Cliente',convert(varchar, pc.fechaDesde, 103)'Fecha Desde',convert(varchar, pc.fechaHasta, 103)'Fecha Hasta' from PagoCuota pc inner join Cliente c on c.IdCliente=pc.IdCliente left join Persona p on p.IdPersona=c.IdPersona where p.dni='"+dni+"' and fechaHasta between '"+fechaI+"' and '"+fechaV+"' ";
         rs=st.executeQuery(sql);
       }
       catch(Exception e){}
       
       return rs;
    }
  
    
    
    
    
    
    
    
    
    
} 

