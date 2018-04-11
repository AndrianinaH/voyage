package S6.voyage.dao;

import java.sql.*;
public class ConnectBase 
{
	public ConnectBase(){}
	public static Connection getCon() throws Exception
	{
  		Class.forName("org.postgresql.Driver");
  		Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/voyage","postgres","itu");
	    return conn;
	}
	
}
