import com.thinking.machines.library.dl.*;
class BookUpdateTestcase
{
public static void main(String data[])
{
Author author=new Author();
String title=data[1];
int AuthorCode=Integer.parseInt(data[2]);
int code=Integer.parseInt(data[0]);
String newtitle=data[3];
int newAuthorCode=Integer.parseInt(data[4]);
String category=data[5];
int price=Integer.parseInt(data[6]);

BookInterface book=new Book();
book.setTitle(title);
book.setAuthorCode(AuthorCode);
book.setCategory(category);
book.setPrice(price);
book.setTitle(newtitle);
book.setAuthorCode(newAuthorCode);
book.setCode(code);
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