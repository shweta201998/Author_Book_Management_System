import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import javax.swing.table.*;
import com.thinking.machines.library.bl.*;
class AddAuthorModel extends JFrame
{
private Container c;
private JTable table;
private JScrollPane jsp;
private AuthorModel model;
private JButton button1,button2,button3,button4;
private JTextField textField;
AddAuthorModel()
{
model=new AuthorModel();
textField=new JTextField(20);

table=new JTable(model);
Font f=new Font("Verdana",Font.PLAIN,16);
table.setFont(f);
table.setRowHeight(30);
jsp=new JScrollPane(table,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
c=getContentPane();
c.setLayout(new BorderLayout());
c.add(jsp,BorderLayout.CENTER);
button1=new JButton("Add");
button1.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ev)
{
try
{
AuthorInterface author=new Author();
author.setName("krishna");
model.addAuthor(author);
System.out.println("Added");
}catch(BLException b)
{
System.out.println("Not Added");
}
}
});
button2=new JButton("update");
button2.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ev)
{
try
{
AuthorInterface author=new Author();
author.setName("Sanchit");
author.setCode(3);
model.updateAuthor(author);
System.out.println("Updated");
}catch(BLException b)
{
System.out.println(b.getException("Name"));
System.out.println("Not Updated");
}
}
});
button3=new JButton("Delete");
button3.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ev)
{
try
{
model.deleteAuthor(6);
System.out.println("Deleted");
}catch(BLException b)
{
System.out.println(b.getException("generic"));
System.out.println("Not Deleted");
}
}
});
button4=new JButton("search");
button4.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ev)
{
try
{
String name=textField.getText();
int index;
index=model.searchAuthor(name,true,true);
table.setRowSelectionInterval(index,index);
table.scrollRectToVisible(table.getCellRect(index,index,true));
}catch(BLException b)
{
System.out.println(b.getException("Name"));
}
}
});

Panel p1=new Panel();
p1.setLayout(new GridLayout(1,7));
p1.add(new JLabel(" "));
p1.add(button1);
p1.add(new JLabel(" "));
p1.add(button2);
p1.add(new JLabel(" "));
p1.add(button3);
p1.add(new JLabel(" "));
p1.add(textField);
p1.add(button4);
c.add(p1,BorderLayout.SOUTH);
setDefaultCloseOperation(EXIT_ON_CLOSE);
setSize(500,400);
Dimension d=Toolkit.getDefaultToolkit().getScreenSize();
setLocation(d.width/2-250,d.height/2-200);
setVisible(true);
}
public static void main(String gg[])
{
AddAuthorModel s=new AddAuthorModel();
}
}