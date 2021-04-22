import javax.swing.*;
import java.awt.*;
import com.thinking.machines.library.dl.*;
import java.util.*;
import javax.swing.table.*;
class MyModel4 extends AbstractTableModel
{
class My
{
private int code;
private Integer s;
private String name;
My(int code,Integer s,String name)
{
this.code=code;
this.s=s;
this.name=name;
}
public int getCode()
{
return code;
}
public Integer getsNumber()
{
return s;
}
public String getName()
{
return name;
}

}
LinkedList<My> data;
public My my;
private String[] title={"S No.","Author Code","Author Name"};
int i;
MyModel4()
{
AuthorDAOInterface authorDAOInterface;
authorDAOInterface=new AuthorDAO();
int c;
int sn;
String n;
try
{
data=new LinkedList<My>();
LinkedList<AuthorInterface> authors=authorDAOInterface.getAll();
for(AuthorInterface author:authors)
{
c=author.getCode();
sn=new Integer(i);
n=author.getName();
my=new My(sn,c,n);
data.add(my);
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
My a=data.get(rowIndex);
if(columnIndex==1)
{
return a.getsNumber();
}
if(columnIndex==0)
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

class AuthorModel4 extends JFrame
{
private MyModel4 m;
private Container c;
private JTable table;
private JScrollPane jsp;

AuthorModel4()
{
m=new MyModel4();

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
AuthorModel4 s=new AuthorModel4();
}
}