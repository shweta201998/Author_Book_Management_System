import javax.swing.*;
import java.awt.*;
import com.thinking.machines.library.dl.*;
import java.util.*;
class AuthorSwing extends JFrame
{
private Container c;
private JTable table;
private JScrollPane jsp;
private String[] title={"S No.","Author Code","Author Name"};
private Object[][] data;
AuthorSwing()
{
AuthorDAOInterface authorDAOInterface;
authorDAOInterface=new AuthorDAO();

try
{
LinkedList<AuthorInterface> authors=authorDAOInterface.getAll();
int p=authors.size();
data=new Object[p][3];
int i=0;
for(AuthorInterface author:authors)
{
data[i][0]=new Integer(i);
data[i][1]=author.getCode();
data[i][2]=author.getName();
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
AuthorSwing s=new AuthorSwing();
}
}