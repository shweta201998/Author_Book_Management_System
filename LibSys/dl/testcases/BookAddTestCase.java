import com.thinking.machines.library.dl.*;
class BookAddTestcase
{
public static void main(String data[])
{
Author author=new Author();
String title=data[0];
int AuthorCode=Integer.parseInt(data[1]);
String category=data[2];
int price=Integer.parseInt(data[3]);

BookInterface book=new Book();
book.setTitle(title);
book.setAuthorCode(AuthorCode);
book.setCategory(category);
book.setPrice(price);
BookDAOInterface BookDAOInterface=new BookDAO();
try
{
BookDAOInterface.add(book);
System.out.println("Book added with code as : "+book.getCode());
}catch(DAOException daoException)
{
System.out.println(daoException);
}
}
}