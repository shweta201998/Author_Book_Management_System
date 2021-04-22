import com.thinking.machines.library.dl.*;
import java.util.*;
class AuthorGetAllTestCase
{
public static void main(String data[])
{
AuthorDAOInterface authorDAOInterface=new AuthorDAO();
try
{
LinkedList<AuthorInterface> authors=authorDAOInterface.getAll();
for(AuthorInterface author:authors)
{
System.out.printf("Code %d, Name %s\n",author.getCode(),author.getName());
}
}catch(DAOException daoException)
{
System.out.println(daoException);
}
}
}