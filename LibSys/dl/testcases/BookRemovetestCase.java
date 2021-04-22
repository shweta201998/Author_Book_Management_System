import com.thinking.machines.library.dl.*;
class BookGetByCodeTestcase
{
public static void main(String data[])
{
int code=Integer.parseInt(data[0]);
BookInterface book;
BookDAOInterface BookDAOInterface=new BookDAO();
try
{
book=BookDAOInterface.getByCode(code);
System.out.println("Title :"+book.getTitle() );
System.out.println("Category :"+book.getCategory() );
System.out.println("Price :"+book.getPrice() );
System.out.println("Author Code :"+book.getAuthorCode());
}catch(DAOException daoException)
{
System.out.println(daoException);
}
}
}