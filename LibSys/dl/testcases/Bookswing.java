import javax.swing.*;
import java.awt.*;
import com.thinking.machines.library.dl.*;
import java.util.*;
class BookSwing extends JFrame
{
private Container c;
private JTable table;
private JScrollPane jsp;
private String[] title={"S No.","Author Name","Book Code","Book Name","Category","Price"};
private Object[][] data;
BookSwing()
{
BookDAOInterface bookDAOInterface;
bookDAOInterface=new BookDAO();

try
{
LinkedList<BookInterface> books=bookDAOInterface.getAll();
int p=books.size();
data=new Object[p][7];
int i=0;
AuthorDAOInterface authorDAOInterface=new AuthorDAO();
for(BookInterface book:books)
{
int authorCode=book.getAuthorCode();
AuthorInterface author=authorDAOInterface.getByCode(authorCode);

data[i][0]=new Integer(i);
data[i][1]=author.getName();
data[i][2]=book.getCode();
data[i][3]=book.getTitle();
data[i][4]=book.getCategory();
data[i][5]=book.getPrice();

i++;
}
}catch(DAOException daoException)
{
System.out.println(daoException);
}

table=new JTable(data,title);
jsp=new JScrollPane(table,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
c=getContentPane();
c.setLayout(new BorderLayout());
c.add(jsp,BorderLayout.CENTER);
setDefaultCloseOperation(EXIT_ON_CLOSE);
setSize(500,400);
Dimension d=Toolkit.getDefaultToolkit().getScreenSize();
setLocation(d.width/2-250,d.height/2-200);
setVisible(true);
}
public static void main(String gg[])
{
BookSwing s=new BookSwing();
}
}