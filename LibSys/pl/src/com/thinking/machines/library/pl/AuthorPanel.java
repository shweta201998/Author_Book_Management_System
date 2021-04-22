package com.thinking.machines.library.pl;
import com.thinking.machines.library.bl.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
public class AuthorPanel extends JPanel implements DocumentListener
{
private JLabel moduleTitleLabel;
private JLabel searchLabel;
private JTextField searchTextField;
private JButton searchTextFieldButton;
private JTable authorTable;
private AuthorModel authorModel;
private JScrollPane scrollPane;
private JLabel notFoundLabel;
private AuthorCrudPanel authorCrudPanel;
private enum MODE {VIEW_MODE,ADD_MODE,EDIT_MODE,DELETE_MODE,EXPORT_TO_PDF_MODE}
private MODE mode;
private ImageIcon addIcon,saveIcon,deleteIcon,EditIcon,cancelIcon,ExportToPDFIcon;
public AuthorPanel()
{
initComponent();
setAppereance();
addListener();
setViewMode();
authorCrudPanel.setViewMode();
}
public void initComponent()
{
addIcon=new ImageIcon("c:\\Libsys\\resources\\add_Icon.png");
saveIcon=new ImageIcon("c:\\libsys\\resources\\save_Icon.png");
deleteIcon=new ImageIcon("c:\\libsys\\resources\\delete_Icon.png");
EditIcon=new ImageIcon("c:\\libsys\\resources\\edit_Icon.png");
cancelIcon=new ImageIcon("c:\\libsys\\resources\\cancel_Icon.png");
ExportToPDFIcon=new ImageIcon("c:\\libsys\\resources\\PDF_Icon.jpg");
authorCrudPanel=new AuthorCrudPanel();
notFoundLabel=new JLabel("");
notFoundLabel.setForeground(Color.red);
authorModel=new AuthorModel();
authorTable=new JTable(authorModel);
scrollPane=new JScrollPane(authorTable,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

moduleTitleLabel=new JLabel("Author");
searchLabel=new JLabel("Search");
searchTextField=new JTextField();
searchTextFieldButton=new JButton(cancelIcon);
}
public void setAppereance()
{
Font titleFont=new Font("Verdana",Font.BOLD,25);
Font font=new Font("Verdana",Font.PLAIN,16);
Font boldFont=new Font("Verdana",Font.BOLD,16);
Font errorFont=new Font("Verdana",Font.BOLD,12);
notFoundLabel.setFont(errorFont);
moduleTitleLabel.setFont(titleFont);
searchLabel.setFont(font);
searchTextField.setFont(font);
authorTable.setRowHeight(30);
authorTable.getColumnModel().getColumn(0).setPreferredWidth(30);
authorTable.getTableHeader().setFont(boldFont);
authorTable.getColumnModel().getColumn(1).setPreferredWidth(420);
authorTable.setFont(font);
int lm;
int tm;
lm=10;
tm=10;
moduleTitleLabel.setBounds(lm+0,2,300,40);
searchLabel.setBounds(lm+0,tm+44,70,30);
searchTextField.setBounds(2+70+lm,tm+50,300,30);
searchTextFieldButton.setBounds(2+70+lm+304,tm+50,30,30);
scrollPane.setBounds(5,100+5,590,200);
notFoundLabel.setBounds(2+70+lm+215,43,80,20);
authorCrudPanel.setBounds(5,100+200+5+5,590,223);
authorCrudPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
add(moduleTitleLabel);
add(notFoundLabel);
add(searchLabel);
add(searchTextField);
add(searchTextFieldButton);
add(scrollPane);
add(authorCrudPanel);
setLayout(null);
}
public void addListener()
{

searchTextField.getDocument().addDocumentListener(this);
searchTextFieldButton.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ev)
{
notFoundLabel.setText(" ");
searchTextField.setText(" ");
}
});
authorTable.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
public void valueChanged(ListSelectionEvent ev)
{
try
{
AuthorInterface author;
int selectedRowIndex=authorTable.getSelectedRow();
if(selectedRowIndex<0 )
{
return;
}
author=authorModel.getAuthorByIndex(selectedRowIndex);
authorCrudPanel.setAuthor(author);
}catch(BLException e)
{
authorCrudPanel.setAuthor(null);
}
}
});
}
public void search() 
{
try
{
notFoundLabel.setText("");
String whatToSearch=searchTextField.getText();
if(whatToSearch==null || whatToSearch.trim().length()==0)
{
notFoundLabel.setText("");
return;
}
int index=authorModel.searchAuthor(whatToSearch,true,true);
authorTable.setRowSelectionInterval(index,index);
authorTable.scrollRectToVisible(authorTable.getCellRect(index,index,true));
}catch(BLException b)
{
notFoundLabel.setText("Not Found");

}
}
public void changedUpdate(DocumentEvent documentEvent)
{
search();
}
public void removeUpdate(DocumentEvent documentEvent)
{
search();
}

public void insertUpdate(DocumentEvent documentEvent)
{
search();

}
public void setViewMode()
{
if(authorModel.getRowCount()>0)
{
searchTextField.setEnabled(true);
searchTextFieldButton.setEnabled(true);
searchLabel.setEnabled(true);
scrollPane.setEnabled(true);
authorTable.setEnabled(true);

}
else
{
searchTextField.setEnabled(false);
searchTextFieldButton.setEnabled(false);
scrollPane.setEnabled(false);
authorTable.setEnabled(true);
}
}
public void setAddMode()
{
searchTextField.setEnabled(false);
searchTextFieldButton.setEnabled(false);
scrollPane.setEnabled(false);
authorTable.setEnabled(false);
}

public void setEditMode()
{
searchTextField.setEnabled(false);
searchTextFieldButton.setEnabled(false);
scrollPane.setEnabled(false);
authorTable.setEnabled(false);

}
public void setDeleteMode()
{
searchTextField.setEnabled(false);
searchTextFieldButton.setEnabled(false);
scrollPane.setEnabled(false);

}
public void setExportToPDFMode()
{
searchTextField.setEnabled(false);
searchTextFieldButton.setEnabled(false);
searchLabel.setEnabled(false);

}

//Inner Class
class AuthorCrudPanel extends JPanel implements ActionListener
{
private AuthorInterface author;
private JLabel AuthorCaptionLabel;
private JLabel nameLabel;
private JTextField nameTextField;
private JButton clearTextFieldButton;
private JButton addButton;
private JButton editButton;
private JButton deleteButton;
private JButton cancelButton;
private JButton exportToPDF;
private JPanel p;
private JLabel requiredLabel;

AuthorCrudPanel()
{
initComponent();
setAppearance();
addListeners();
}
public void initComponent()
{
requiredLabel=new JLabel("");
p=new JPanel(); 
AuthorCaptionLabel=new JLabel("Author : ");
nameLabel=new JLabel("");
nameTextField=new JTextField();
clearTextFieldButton=new JButton(cancelIcon);
addButton=new JButton(addIcon);
editButton=new JButton(EditIcon);
deleteButton=new JButton(deleteIcon);
cancelButton=new JButton(cancelIcon);
exportToPDF=new JButton(ExportToPDFIcon);

}
public void setAppearance()
{
Font font=new Font("Verdana",Font.PLAIN,20);
Font requiredFont=new Font("Verdana",Font.BOLD,10);
requiredLabel.setFont(requiredFont);
requiredLabel.setForeground(Color.red);
AuthorCaptionLabel.setFont(font);
nameLabel.setFont(font);
nameTextField.setFont(font);
int lm;
int tm;
lm=10;
tm=10;
AuthorCaptionLabel.setBounds(lm+0,tm+0,95,30);
nameLabel.setBounds(lm+95+5,tm+0,350,30);
requiredLabel.setBounds(lm+95+5,0,200,10);
nameTextField.setBounds(lm+95+5,tm+0,350,30);
clearTextFieldButton.setBounds(lm+95+5+350+3,tm+0,30,30);
setLayout(null);
add(AuthorCaptionLabel);
add(nameLabel);
add(requiredLabel);
add(nameTextField);
add(clearTextFieldButton);
requiredLabel.setVisible(false);
addButton.setBounds(70,40,70,70);//40+10+10+10
editButton.setBounds(160,40,70,70);//70+10+70+10
deleteButton.setBounds(250,40,70,70);//160+70+10+10
cancelButton.setBounds(340,40,70,70);//250+10+10+70
exportToPDF.setBounds(360+70,40,70,70);//340+10+10+70
p.setLayout(null);
p.add(addButton);
p.add(editButton);
p.add(deleteButton);
p.add(cancelButton);
p.add(exportToPDF);
p.setBorder(BorderFactory.createLineBorder(Color.BLACK));
p.setBounds(10,60,570,150);
add(p);

}
public void setViewMode()
{
mode=MODE.VIEW_MODE;
nameTextField.setText("");
nameTextField.setVisible(false);
nameTextField.setEnabled(true);
clearTextFieldButton.setEnabled(false);
addButton.setEnabled(true);

clearTextFieldButton.setVisible(false);
editButton.setEnabled(true);
deleteButton.setEnabled(true);
cancelButton.setEnabled(false);
exportToPDF.setEnabled(true);
addButton.setIcon(addIcon);
editButton.setIcon(EditIcon);

}
public void addMode()
{
mode=MODE.ADD_MODE;
nameLabel.setText("");
nameTextField.setText("");
nameTextField.setEnabled(true);
nameTextField.setVisible(true);
clearTextFieldButton.setVisible(true);

clearTextFieldButton.setEnabled(true);
editButton.setEnabled(false);
deleteButton.setEnabled(false);
cancelButton.setEnabled(true);
exportToPDF.setEnabled(false);
addButton.setIcon(saveIcon);
}

public void editMode()
{
mode=MODE.EDIT_MODE;

nameLabel.setText("");
nameTextField.setEnabled(true);
clearTextFieldButton.setVisible(true);

clearTextFieldButton.setEnabled(true);
addButton.setEnabled(false);
editButton.setEnabled(true);
deleteButton.setEnabled(false);
cancelButton.setEnabled(true);
exportToPDF.setEnabled(false);
editButton.setIcon(EditIcon);
}
public void deleteMode()
{
mode=MODE.DELETE_MODE;
nameLabel.setText("");

nameTextField.setEnabled(true);
clearTextFieldButton.setEnabled(true);
addButton.setEnabled(false);
editButton.setEnabled(false);
deleteButton.setEnabled(false);
cancelButton.setEnabled(true);
exportToPDF.setEnabled(false);

}
public void exportToPDFMode()
{
mode=MODE.EXPORT_TO_PDF_MODE;

nameTextField.setEnabled(true);
clearTextFieldButton.setEnabled(true);
addButton.setEnabled(false);
editButton.setEnabled(false);
deleteButton.setEnabled(false);
cancelButton.setEnabled(true);
exportToPDF.setEnabled(false);

}
public void setAuthor(AuthorInterface author)
{
this.author=author;
if(author!=null)
{
nameLabel.setText(author.getName());
nameTextField.setVisible(false);
nameLabel.setVisible(true);
clearTextFieldButton.setVisible(false);
}

}

public void addListeners()
{
clearTextFieldButton.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ev)
{
nameTextField.setText("");
}
});
addButton.addActionListener(this);
editButton.addActionListener(this);
deleteButton.addActionListener(this);
cancelButton.addActionListener(this);
exportToPDF.addActionListener(this);
}
public void actionPerformed(ActionEvent ev)
{
if(ev.getSource()==addButton)
{
if(mode==mode.VIEW_MODE)
{
this.addMode();
AuthorPanel.this.setAddMode();
}
else
{
try
{
AuthorInterface author=new Author();
author.setName(nameTextField.getText());
int index=authorModel.addAuthor(author);
authorTable.setRowSelectionInterval(index,index);
authorTable.scrollRectToVisible(authorTable.getCellRect(index,index,true));
this.setViewMode();
AuthorPanel.this.setViewMode();

}catch(BLException blException)
{
java.util.List<String> list=blException.getProperties();
for(String property:list)
{
JOptionPane.showMessageDialog(AuthorPanel.this,blException.getException(property));
}
}

}
return;
}
if(ev.getSource()==editButton)
{

if(mode==mode.VIEW_MODE)
{

int selectedRowIndex=authorTable.getSelectedRow();
if(selectedRowIndex<0 || selectedRowIndex>=authorTable.getRowCount())
{
JOptionPane.showMessageDialog(AuthorPanel.this,"Select a Row for Edit");
return;
}
this.editMode();
AuthorPanel.this.setEditMode();
nameTextField.setVisible(true);
}
else
{
try
{
AuthorInterface author=new Author();
author.setName(nameTextField.getText());
author.setCode(this.author.getCode());
int selectedOption=JOptionPane.showConfirmDialog(AuthorPanel.this,"Save Author ?","Author",JOptionPane.YES_NO_OPTION);
if(selectedOption==JOptionPane.YES_OPTION)
{
int index=authorModel.updateAuthor(author);
authorTable.setRowSelectionInterval(index,index);
authorTable.scrollRectToVisible(authorTable.getCellRect(index,index,true));
searchTextField.setText("");
notFoundLabel.setText("");
}
else
{
this.setViewMode();
AuthorPanel.this.setViewMode();
return;
}

}catch(BLException blException)
{
java.util.List<String> list=blException.getProperties();
for(String property:list)
{
JOptionPane.showMessageDialog(AuthorPanel.this,blException.getException(property));
}

}
this.setViewMode();
AuthorPanel.this.setViewMode();
}
return;
}
if(ev.getSource()==deleteButton)
{

int selectedRowIndex=authorTable.getSelectedRow();
if(selectedRowIndex<0 || selectedRowIndex>=authorTable.getRowCount())
{
JOptionPane.showMessageDialog(AuthorPanel.this,"Select a Row for Delete");
return;
}
this.deleteMode();
AuthorPanel.this.setDeleteMode();
nameTextField.setVisible(true);
int selectedOption=JOptionPane.showConfirmDialog(AuthorPanel.this,"Delete Author ?","Author",JOptionPane.YES_NO_OPTION);
if(selectedOption==JOptionPane.YES_OPTION)
{
try
{
authorModel.deleteAuthor(author.getCode());
JOptionPane.showMessageDialog(AuthorPanel.this,"Author Deleted");

searchTextField.setText("");
notFoundLabel.setText("");
}catch(BLException blException)
{
java.util.List<String> list=blException.getProperties();
for(String property:list)
{
JOptionPane.showMessageDialog(AuthorPanel.this,blException.getException(property));
}

}

}
else
{
this.setViewMode();
AuthorPanel.this.setViewMode();
return;
}
this.setViewMode();
AuthorPanel.this.setViewMode();


}
if(ev.getSource()==cancelButton)
{
this.setViewMode();
AuthorPanel.this.setViewMode();
}
if(ev.getSource()==exportToPDF)
{

}

}

}
}