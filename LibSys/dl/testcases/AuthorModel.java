import javax.swing.*;
import java.awt.*;
import com.thinking.machines.library.dl.*;
import java.util.*;
import javax.swing.table.*;
class MyModel2 extends AbstractTableModel
{
LinkedList<AuthorInterface> data;

private String[] title={"Author Code","Author Name"};
MyModel2()
{
AuthorDAOInterface authorDAOInterface;
authorDAOInterface=new AuthorDAO();

try
{
data=new LinkedList<AuthorInterface>();
LinkedList<AuthorInterface> authors=authorDAOInterface.getAll();
for(AuthorInterface author:authors)
{
data.add(author);
}
}catch(DAOException daoException)
{
System.out.println(daoException);
}

}
public int getColumnCount()
{
return title.length;
}
public int getRowCount()
{
return data.size();
}
public boolean isCellEditable(int rowIndex,int columnIndex)
{
if(columnIndex==3)  return true;
return false;
}
public Object getValueAt(int rowIndex,int columnIndex)
{
AuthorInterface a;
a=data.get(rowIndex);
if(columnIndex==1)
{
return a.getCode();
}
return a.getName();
}
public Class getColumnClass(int columnIndex)
{
Class c=null;
try
{
if(columnIndex==0 )
{
c=Class.forName("java.lang.Integer");
}
if(columnIndex==1)
{
c=Class.forName("java.lang.String");
}
if(columnIndex==3)
{
c=Class.forName("java.lang.Boolean");
}
}catch(ClassNotFoundException cnfe)
{
}
return c;
}
/*public void setValueAt(Object d,int rowIndex,int columnIndex)
{
data.add(rowIndex*columnIndex)=d;
}*/
public String getColumnName(int columnIndex)
{
return title[columnIndex];
}
}

class AuthorModel2 extends JFrame
{
private MyModel m;
private Container c;
private JTable table;
private JScrollPane jsp;

AuthorModel2()
{
m=new MyModel();

table=new JTable(m);
Font f=new Font("Verdana",Font.PLAIN,16);
table.setFont(f);
table.setRowHeight(30);
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
AuthorModel2 s=new AuthorModel2();
}
}