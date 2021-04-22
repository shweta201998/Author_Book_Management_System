import com.thinking.machines.library.dl.*;
class AuthorAddTestCase
{
public static void main(String data[])
{
String name=data[0];
AuthorInterface author=new Author();
author.setName(name);
AuthorDAOInterface authorDAOInterface=new AuthorDAO();
try
{
authorDAOInterface.add(author);
System.out.println("Author added with code as : "+author.getCode());
}catch(DAOException daoException)
{
System.out.println(daoException);
}
}
}