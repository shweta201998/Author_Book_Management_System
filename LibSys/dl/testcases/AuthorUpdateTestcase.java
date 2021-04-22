import com.thinking.machines.library.dl.*;
class AuthorUpdateTestcase
{
public static void main(String data[])
{
String newName=data[2];
String oldName=data[1];
int code=Integer.parseInt(data[0]);
AuthorInterface author=new Author();
author.setName(oldName);
author.setName(newName);
author.setCode(code);
AuthorDAOInterface authorDAOInterface=new AuthorDAO();
try
{
authorDAOInterface.update(author);
System.out.println("Author updated with name as : "+author.getName());
}catch(DAOException daoException)
{
System.out.println(daoException);
}
}
}