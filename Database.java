/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ajedrez;


import java.sql.Connection;
import java.sql.DriverManager;

public class Database {
    
    public static Connection connectDB(){
        
        Connection conn = null;
        
        try {
            //BD connection
            String connectionUrl = "jdbc:sqlserver://PC00I41\\SQLEXPRESS:1433;databaseName=Ajedrez";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(connectionUrl, "cifo", "java2019");
            //END BD connection
        }
        catch (Exception e) {
            //e.printStackTrace();
        }
        
        return conn;     
        
    }

    public static boolean TestConnection()
    {
        Connection cnx = connectDB();
        if(cnx!=null)
        {
            try
            {
                // si la hemos abierto, la cerramos
                cnx.close();
                return true;
            }
            catch(Exception e)
            {
                // no se puede cerrar? Entonces pasa algo...
                return false;             
            }
        }
        else
            return false;
    }
}
