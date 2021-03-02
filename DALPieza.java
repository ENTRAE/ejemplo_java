/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ajedrez;

import static ajedrez.Database.connectDB;
import java.io.*;
import java.sql.*;


/**
 *
 * @author Jose
 */
public class DALPieza {
    
    public static String LeerImagen(String nombrePieza, boolean color)
    {
        // en esta 2a versión, usamos la carpeta temporal del sistema para guardar el archivo de la imagen leída de la BD
        // Ya no tenemos que preocuparnos del path exacto y real (variable según el sistema y entorno de ejecución)
        
        char colorChar = (color ? 'W' : 'B');
        //String fileName = "C:\\Users\\Docent\\Documents\\NetBeansProjects\\Ajedrez\\piezas\\" + nombrePieza + colorChar + ".png";
        String fileName = System.getProperty("java.io.tmpdir") + "\\" + nombrePieza + colorChar + ".png";
        byte[] fileBytes = null;
	String query;
	try 
	{
            Connection cnx = connectDB();
            query = "SELECT Imagen FROM ImagenPieza WHERE NombrePieza = '" + nombrePieza + "' AND Color='" + colorChar+"'";
            Statement state = cnx.createStatement();
            ResultSet rs = state.executeQuery(query);

            if (rs.next()) 
            {
                // leemos los byates de la BD
                fileBytes = rs.getBytes(1);
                
                // creamos el archivo temporal con la imagen
                OutputStream targetFile = new FileOutputStream(fileName);
                targetFile.write(fileBytes);
                targetFile.close();
            }
	} 
	catch (Exception e) 
	{
            e.printStackTrace();
	}
        
        return fileName;
    }
    
    public static void GuardarImagen(String nombrePieza, boolean color)
    {
	String query;
        PreparedStatement pstmt;
        String colorChar = (color ? "W" : "B");
        
        // leemos la imagen de los resources del proyecto (en el JAR)
        InputStream image = DALPieza.class.getResourceAsStream("/imatges/" + nombrePieza + colorChar + ".png");
        
	try 
	{
            Connection cnx = connectDB();
            
            // borramos la imagen anterior, si la hubiera
            query = "DELETE FROM ImagenPieza WHERE NombrePieza=? AND Color=?";
            pstmt = cnx.prepareStatement(query);
            pstmt.setString(1, nombrePieza);
            pstmt.setString(2, colorChar);
            pstmt.executeUpdate();

            // insertamos la nueva imagenen la BD
            query = "INSERT INTO [dbo].[ImagenPieza] ([NombrePieza] ,[Color] ,[Imagen]) VALUES (?, ?, ?);";
            pstmt = cnx.prepareStatement(query);
            pstmt.setString(1, nombrePieza);
            pstmt.setString(2, colorChar);
            pstmt.setBinaryStream(3, image);          
            pstmt.executeUpdate();
	} 
	catch (Exception e) 
	{
            e.printStackTrace();
	}    
    }  
    
}
