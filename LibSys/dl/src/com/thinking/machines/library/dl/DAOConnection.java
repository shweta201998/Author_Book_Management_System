package com.thinking.machines.library.dl;
import java.sql.*;
public class DAOConnection
{
private DAOConnection()
{
}
public static Connection getConnection() throws DAOException
{
try
{
Class.forName("com.mysql.jdbc.Driver");
Connection connection;
connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb2017","root","kelkar");
return connection;
}catch(ClassNotFoundException classNotFoundException)
{
throw new DAOException("Hello u r on worng track."+classNotFoundException);
}catch(SQLException sqlException)
{ 
throw new DAOException("Unable to connect :"+sqlException.getMessage());
}
}
}