package com.thinking.machines.library.pl;
import com.thinking.machines.library.bl.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
public class AuthorFrame extends JFrame
{
private AuthorPanel authorPanel;
private Container container;

public AuthorFrame()
{
setTitle("Library Manager");
initComponent();
setAppereance();
addListener();
}
public void initComponent()
{
authorPanel=new AuthorPanel();
container=getContentPane();

}
public void setAppereance()
{
authorPanel.setBounds(5,5,600,540);
setLayout(null);
container.add(authorPanel);
authorPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
setDefaultCloseOperation(EXIT_ON_CLOSE);
setSize(627,590);
Dimension d=Toolkit.getDefaultToolkit().getScreenSize();
setLocation(d.width/2-250,d.height/2-250);
setVisible(true);

}

public void addListener()
{

}
}