
package dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;



public class C3p0Utils {
	// src����c3p0-config.xml���ã������Զ���ȡ����
private static DataSource dataSource=new ComboPooledDataSource();
private static Connection con=null;

public static DataSource getDataSource(){
 return dataSource;
 }

 public static Connection getConnection() throws SQLException{
 con=dataSource.getConnection();
 return con;
 }
 
 public static void closeConnection(){
 try {
	if(con!=null)
	con.close();
} catch (SQLException e) {
	e.printStackTrace();
}
}
	 
	  
  
}
