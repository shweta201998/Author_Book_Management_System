import com.thinking.machines.library.dl.*;
import java.util.*;
class ContainsBookWithAuthorCodeTestcase
{
public static void main(String data[])
{
int authorCode=Integer.parseInt(data[0]);
BookDAOInterface bookDAOInterface=new BookDAO();
try
{
boolean contain=bookDAOInterface.containsBookWithAuthorCode(authorCode);
if(contain)
{
System.out.println("Author contains Books");
}
else
{
System.out.println("Author Not Contains Books");
}
}catch(DAOException daoException)
{
System.out.println(daoException);
}
}
}