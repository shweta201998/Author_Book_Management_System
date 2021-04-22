import javax.swing.*;
import java.awt.*;
import com.thinking.machines.library.dl.*;
import java.util.*;
import javax.swing.table.*;
class MyModel6 extends AbstractTableModel
{
LinkedList data;
private String[] title={"S No.","Author Code","Author Name"};
int i;
MyModel6()
{
AuthorDAOInterface authorDAOInterface;
authorDAOInterface=new AuthorDAO();
try
{
data=new LinkedList();
LinkedList authors=authorDAOInterface.getAll();
for(authors.size())
{
authors.add(new Integer(i));
i++;
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
return i;
}
public boolean isCellEditable(int rowIndex,int columnIndex)
{
return false;
}
public Object getValueAt(int rowIndex,int columnIndex)
{
if(columnIndex==0)
{
return authors.get(rowIndex);
}
if(columnIndex==1)
{
return authors.get(rowIndex);
}
return authors.get(rowIndex);;
}

public Class getColumnClass(int columnIndex)
{
Class c=null;
try
{
if(columnIndex==0 || columnIndex==1)
{
c=Class.forName("java.lang.Integer");
}
if(columnIndex==2)
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

class AuthorModel6 extends JFrame
{
private MyModel6 m;
private Container c;
private JTable table;
private JScrollPane jsp;

AuthorModel6()
{
m=new MyModel6();

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
AuthorModel6 s=new AuthorModel6();
}
}