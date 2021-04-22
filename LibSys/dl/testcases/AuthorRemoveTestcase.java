import com.thinking.machines.library.dl.*;
class AuthorRemoveTestcase
{
public static void main(String data[])
{
int code=Integer.parseInt(data[0]);
AuthorDAOInterface authorDAOInterface=new AuthorDAO();
try
{
authorDAOInterface.remove(code);
System.out.println("Author Deleted ");
}catch(DAOException daoException)
{
System.out.println(daoException);
}
}
}