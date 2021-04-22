import com.thinking.machines.library.dl.*;
class AuthorGetByNameTestcase
{
public static void main(String data[])
{
String name=data[0];
AuthorInterface author;
AuthorDAOInterface authorDAOInterface=new AuthorDAO();
try
{
author=authorDAOInterface.getByName(name);
System.out.println("Name : "+author.getName());
System.out.println("Code : "+author.getCode());
}catch(DAOException daoException)
{
System.out.println(daoException);
}
}
}