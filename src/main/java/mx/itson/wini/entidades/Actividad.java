/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.itson.wini.entidades;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import mx.itson.wini.persistencia.Conexion;

/**En esta clase se realizan realizan todas las conexiones, obtenciones y otorgaciones de valores necesarios para que en la base de datos en  "Actividad" se pueda mostrar en nuestras tablas
 *
 * @author pedrizquierdo
 */
public class Actividad {

    /**Aqui se obtiene el dato de valor servicio
     * @return el servicio que se realizo segun en la base de datos
     */
    public Servicio getServicio() {
        return servicio;
    }

    /**Aqui se otorga el valor de tipo servicio
     * @param servicio se coloca en la base de datos el servicio que se realizo
     */
    public void setServicio(Servicio servicio) {
        this.servicio = servicio;
    }

    /**Aqui se obtiene el dato Id de tipo int
     * @return el id de la actividad que se realizo segun la base de datos
     */
    public int getId() {
        return id;
    }

    /**Aqui se Otorga el dato Id de tipo int
     * @param id identificador unico de la actividad realizada
     */
    public void setId(int id) {
        this.id = id;
    }

    /**Aqui se Obtiene el valor de Orden de tipo int
     * @return el numero de Orden que se realizo segun la base de datos
     */
    public int getOrden() {
        return orden;
    }

    /**Aqui se otorga el valor de Orden de tipo int
     * @param orden Se coloca en la base de datos la Orden del servicio que se realizo
     */
    public void setOrden(int orden) {
        this.orden = orden;
    }

    /**Aqui se obtiene el valor de Descripcion de tipo String
     * @return La descripcion del problema del servicio que se realizo segun la base de datos
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**Aqui se otorga el valor de Descripcion de tipo String
     * @param descripcion se coloca la descripcion del problema del servicio que se realizo
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    /**aqui se le coloca una lista de array de tipo actividad y consulta en la base de datos
     * @param idServicio es un int el cual es el id del servicio especifico
     * @return el int de id y orden y el string de descripcion del problema 
     */
    public static List<Actividad> getList(int idServicio) {
        List<Actividad> actividades = new ArrayList<>();
        try {
            Connection conexion = Conexion.obtener();
            String consulta = "SELECT id, orden, descripcion FROM actividad WHERE id_servicio = ?";
            PreparedStatement statement = conexion.prepareStatement(consulta);
            statement.setInt(1, idServicio);
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                Actividad a = new Actividad();
                a.setId(rs.getInt(1));
                a.setOrden(rs.getInt(2));
                a.setDescripcion(rs.getString(3));
                actividades.add(a);
                
            }
        } catch(Exception ex) {
            System.err.println("Ocurrió un error: " + ex.getMessage());
        } return actividades;
      
    }
    
    private int id;
    private int orden;
    private String descripcion;
    private Servicio servicio;
    
    /**
     * Aqui se coloca una seccion de codigo para poder desde el programa eliminar un id en la base de datos
     * @param id es un int el cual es el id especifico en la tabla actividad
     * @return La eliminacion de un id especifico seleccionado
     */
    public static boolean delete(int id) {
        boolean resultado = false;
        try{
                Connection conexion = Conexion.obtener();
                String consulta = "DELETE FROM actividad WHERE id = ?";
                PreparedStatement statement = conexion.prepareStatement(consulta);
                
                statement.setInt(1, id);
                
                statement.execute();
                resultado = statement.getUpdateCount() == 1;
                conexion.close();
        }catch(Exception ex) {
            System.err.println("Ocurrió un error: " + ex.getMessage());
        }return resultado;
    } 
    
    /**
     * Aqui esta seccion de codigo sirve para poder editar desde nuestro programa, la base de datos y poder 
     * modificar toda la tabla de actividad
     * @param id es un int el cual es el identificador unico de la actividad especifica 
     * @param orden es un int el cual es el numero de orden de la actividad especifica
     * @param descripcion es un string el cual da la descripcion del problema en el servicio especifico realizado
     * @param idServicio es un int el cual es el id del servicio especifico 
     * @return la edicion completada de id, orden, descripcion, idservicio
     */
    public static boolean edit(int id,int orden, String descripcion, int idServicio) {
        boolean resultado = false;
        try{
                Connection conexion = Conexion.obtener();
                String consulta = "UPDATE actividad SET orden = ?, descripcion = ?, id_servicio = ? WHERE id = ?";
                PreparedStatement statement = conexion.prepareStatement(consulta);
                statement.setInt(1, orden);
                statement.setString(2, descripcion);
                statement.setInt(3, idServicio);
                statement.setInt(4,id);
                
                statement.execute();
                resultado = statement.getUpdateCount() == 1;
                conexion.close();
        }catch(Exception ex) {
            System.err.println("Ocurrió un error: " + ex.getMessage());
        }return resultado;
    } 
    
    /**
     * Esta seccion de codigo sirve para poder poder guardar nuestros cambios al realizarlos
     * @param orden es n int el cual es la orden del servicio especifico selecconado
     * @param descripcion es un string de la descripcion del problema especifico seleccionado
     * @param idServicio es un int del id del servicio especifico seleccionado
     * @return El guardado completo de nuestros cambios en la base de datos
     */
    public static boolean save(int orden, String descripcion, int idServicio) {
        boolean resultado = false;
        try{
                Connection conexion = Conexion.obtener();
                String consulta = "INSERT INTO actividad (orden, descripcion, id_servicio) VALUES (?, ?, ?)";
                PreparedStatement statement = conexion.prepareStatement(consulta);
                statement.setInt(1, orden);
                statement.setString(2, descripcion);
                statement.setInt(3, idServicio);
                
                statement.execute();
                resultado = statement.getUpdateCount() == 1;
                conexion.close();
        }catch(Exception ex) {
            System.err.println("Ocurrió un error: " + ex.getMessage());
        }return resultado;
        
        
    } 
    /**
     * En esta seccion de codigo se utiliza para poder obtener el id de la persona en especifico
     * el id de la orden en especifico y el id de la descripcion seleccionada especifica
     * @param id es un int el cual es el id de la actividad, id de orden y id de la descripcion
     * @return Un objeto de tipo Actividad que contiene los datos del registro correspondiente.
     * Si no se encuentra el registro, se devuelve un objeto vacío con valores predeterminados.
     */
    public static Actividad getById(int id){
            Actividad a = new Actividad ();
        try {
            Connection conexion = Conexion.obtener();
            
            String query = "SELECT id, orden, descripcion FROM actividad WHERE id = ?";
            PreparedStatement statement = conexion.prepareStatement(query);
            statement.setInt(1, id);
            
            ResultSet rs = rs = statement.executeQuery();
            while(rs.next()) {
 
                a.setId(rs.getInt(1));
                a.setOrden(rs.getInt(2));
                a.setDescripcion(rs.getString(3));

            }
        } catch (Exception ex) {
            System.err.println("Ocurrió un error " + ex.getMessage());
        }
        return a;
    }
    /**
     * Esta seccion de codigo se usa para poder seleccionar todo lo que este dentro de "Actividad"
     * En la base de datos
     * @return todos los datos seleccionados de la base de datos
     */
    public static List<Actividad> getAll (){
        List<Actividad> actividades = new ArrayList<>();
        try {
            Connection conexion = Conexion.obtener();
            Statement statement = conexion.createStatement();
            ResultSet rs = statement.executeQuery("SELECT id, orden, descripcion, id_servicio FROM Actividad");
            while(rs.next()) {
                Actividad a = new Actividad ();
                a.setId(rs.getInt(1));
                a.setOrden(rs.getInt(2));               
                a.setDescripcion(rs.getString(3));
                
                Servicio s = Servicio.getById(rs.getInt(4));
                a.setServicio(s);
                
                actividades.add(a);
                
            }
            
        } catch(Exception ex){
            System.err.println("Ocurrió un error: " + ex.getMessage());
        } return actividades; 
    } 
    
}
