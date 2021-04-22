import com.thinking.machines.library.dl.*;
class BookRemoveTestcase
{
public static void main(String data[])
{
int code=Integer.parseInt(data[0]);
BookInterface book=new Book();
BookDAOInterface BookDAOInterface=new BookDAO();
try
{
BookDAOInterface.remove(code);
System.out.println("Book deleted ");
}catch(DAOException daoException)
{
System.out.println(daoException);
}
}
}