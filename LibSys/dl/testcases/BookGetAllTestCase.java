import com.thinking.machines.library.dl.*;
import java.util.*;
class BookGetAllTestcase
{
public static void main(String data[])
{
BookDAOInterface bookDAOInterface=new BookDAO();
try
{
LinkedList<BookInterface> books=bookDAOInterface.getAll();
for(BookInterface book:books)
{
System.out.printf("Code %d, Name %s\n price %d\n Category %s\n",book.getCode(),book.getTitle(),book.getPrice(),book.getCategory());
}
}catch(DAOException daoException)
{
System.out.println(daoException);
}
}
}