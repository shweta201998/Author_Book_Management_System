package com.thinking.machines.library.bl;
import java.awt.*;
import javax.swing.*;
import java.util.*;
import javax.swing.table.*;
import com.thinking.machines.library.dl.*;
public class AuthorModel extends AbstractTableModel 
{

String title[]={"S No.","Author"};
public LinkedList<AuthorInterface> blauthors;
public AuthorModel()
{
populateDataStructure();
}
public void populateDataStructure()  
{
try
{
blauthors=new LinkedList<AuthorInterface>();
LinkedList<com.thinking.machines.library.dl.AuthorInterface> authors;
com.thinking.machines.library.dl.AuthorDAOInterface dlauthordao;
dlauthordao=new com.thinking.machines.library.dl.AuthorDAO();
authors=dlauthordao.getAll();
for(com.thinking.machines.library.dl.AuthorInterface dlauthor:authors)
{
AuthorInterface blauthor=new Author();
blauthor.setName(dlauthor.getName());
blauthor.setCode(dlauthor.getCode());
blauthors.add(blauthor);
}
}catch(com.thinking.machines.library.dl.DAOException daoException)
{
System.out.println(daoException.getMessage());
}
}
public int getColumnCount()
{
return title.length;
}
public int getRowCount()
{
return blauthors.size();
}
public String getColumnName(int columnIndex)
{
return title[columnIndex];
}
public boolean isCellEditable(int rowIndex,int columnIndex)
{
return false;
}
public Object getValueAt(int rowIndex,int columnIndex)
{
if(columnIndex==0)
{
return rowIndex+1;
}
return blauthors.get(rowIndex).getName();
}
public Class getColumnClass(int columnIndex)
{
Class c=null;
if(columnIndex==0)
{
c=Integer.class;
return c;
}
c=String.class;
return c;
}
public AuthorInterface getAuthorByIndex(int index) throws BLException
{
if(index<0 || index>blauthors.size())
{
BLException blException=new BLException();
blException.addException("Generic","Invalid Index");
}
return blauthors.get(index);
}
public int addAuthor(AuthorInterface authorInterface) throws BLException
{
String name=authorInterface.getName();
if(name==null || name.trim().length()==0)
{
BLException blException=new BLException();
blException.addException("Name","Required");
throw blException;
}
for(int x=0;x<blauthors.size();x++)
{
if(blauthors.get(x).getName().equals(name))
{
BLException blException=new BLException();
blException.addException("Name","Author Exists");
throw blException;

}
x++;
}
try
{
com.thinking.machines.library.dl.AuthorDAOInterface authors;
authors=new com.thinking.machines.library.dl.AuthorDAO();

com.thinking.machines.library.dl.AuthorInterface author=new com.thinking.machines.library.dl.Author();
author.setName(name);
authors.add(author);
authorInterface.setCode(author.getCode());
}catch(com.thinking.machines.library.dl.DAOException daoException)
{
BLException blException=new BLException();
blException.addException("Generic", daoException.getMessage());
throw blException;

}

int i;
for(i=0;i<blauthors.size();i++)
{
if(blauthors.get(i).getName().compareTo(name)>0) break;
}
blauthors.add(i,authorInterface);
fireTableDataChanged();
return i;
}
public int updateAuthor(AuthorInterface authorInterface) throws BLException
{
BLException blException=null;
String name=authorInterface.getName();
int code=authorInterface.getCode();
if(code<=0)
{
blException=new BLException();
blException.addException("Code","Code Required");
}
if(name==null || name.trim().length()==0)
{
blException=new BLException();
blException.addException("Name","Name Required");
}
if(blException!=null)
{
throw blException;
}
for(AuthorInterface author:blauthors)
{
if(author.getName().equals(name) && author.getCode()!=code)
{
blException=new BLException();
blException.addException("Name","Name Exists");
throw blException;
}
}
try
{
int i;
for(i=0;i<blauthors.size();i++)
{
if(blauthors.get(i).getCode()==code)
{
break;
}

}
if(i==blauthors.size())
{
blException=new BLException();
blException.addException("code",code+"Invalid.");
throw blException;

}
com.thinking.machines.library.dl.AuthorDAOInterface authors;
authors=new com.thinking.machines.library.dl.AuthorDAO();

com.thinking.machines.library.dl.AuthorInterface author=new com.thinking.machines.library.dl.Author();
author.setName(name);
author.setCode(code);
authors.update(author);
blauthors.remove(i);

}catch(com.thinking.machines.library.dl.DAOException daoException)
{
blException=new BLException();
blException.addException("Generic","doaException.getMessage()");
throw blException;

}
int t;
for(t=0;t<blauthors.size();t++)
{
if(blauthors.get(t).getName().compareTo(name)>0) break;
}
blauthors.add(t,authorInterface);
fireTableDataChanged();
return t;

}
public void deleteAuthor(int code) throws BLException
{
BLException blException=null;
if(code<=0)
{
blException=new BLException();
blException.addException("Code","Code Required");
throw blException;
}
int i;
try
{
for(i=0;i<blauthors.size();i++)
{
if(blauthors.get(i).getCode()==code)
{
break;
}

}
if(i==blauthors.size())
{
blException=new BLException();
blException.addException("code",code+"Invalid.");
throw blException;

}
com.thinking.machines.library.dl.AuthorDAOInterface authors;
authors=new com.thinking.machines.library.dl.AuthorDAO();

authors.remove(code);
blauthors.remove(i);
fireTableDataChanged();

}catch(com.thinking.machines.library.dl.DAOException daoException)
{
blException=new BLException();
blException.addException("Generic", daoException.getMessage());
throw blException;

}

}
public int searchAuthor(String namePart,boolean searchPartial,boolean IncaseSensitive) throws BLException
{
BLException blException;
if(namePart==null || namePart.trim().length()==0)
{
blException=new BLException();
blException.addException("Name","Name Required");
throw blException;
}
String whatToSearch;
whatToSearch=namePart;
if(IncaseSensitive) whatToSearch=whatToSearch.toUpperCase();
int f=0;
for(AuthorInterface author:blauthors)
{
if(searchPartial)
{
if(IncaseSensitive)
{
if(author.getName().toUpperCase().startsWith(whatToSearch)) return f;
}
else
{
if(author.getName().startsWith(whatToSearch)) return f;

}

}
else
{
if(IncaseSensitive)
{
if(author.getName().toUpperCase().equalsIgnoreCase(whatToSearch)) return f;

}
else
{
if(author.getName().equals(whatToSearch)) return f;
}
}
f++;
}
blException=new BLException();
blException.addException("Name","Not Found");
throw blException;
}
public void exportToPDF()
{
String s="c:\\libsys\\Author.pdf"
Document document=new Document();
document.getInstance
}
}
