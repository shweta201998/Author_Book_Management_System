import com.thinking.machines.library.dl.*;
class AuthorGetCountTestcase
{
public static void main(String gg[])
{
AuthorDAOInterface author=new AuthorDAO();
try
{
System.out.println("No. of Author is : "+author.getCount());
}
catch(DAOException  daoException)
{
System.out.println(daoException);
}
}
}