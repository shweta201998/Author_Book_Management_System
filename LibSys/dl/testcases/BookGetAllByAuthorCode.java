import com.thinking.machines.library.dl.*;
import java.util.*;
class BookGetAllByAuthorCodeTestcase
{
public static void main(String data[])
{
int authorCode=Integer.parseInt(data[0]);
BookDAOInterface bookDAOInterface=new BookDAO();
try
{
LinkedList<BookInterface> books=bookDAOInterface.getAllByAuthorCode(authorCode);
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