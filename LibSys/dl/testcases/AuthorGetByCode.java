import com.thinking.machines.library.dl.*;
class AuthorGetByCodeTestcase
{
public static void main(String data[])
{
int code=Integer.parseInt(data[0]);
AuthorInterface author;
AuthorDAOInterface authorDAOInterface=new AuthorDAO();
try
{
author=authorDAOInterface.getByCode(code);
System.out.println("Name : "+author.getName());
System.out.println("Code : "+author.getCode());
}catch(DAOException daoException)
{
System.out.println(daoException);
}
}
}