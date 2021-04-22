package com.thinking.machines.library.bl;
import java.util.*;
public class BLException extends Exception
{
private Map<String,String> exception;
public BLException()
{
exception=new HashMap<String,String>();
}
public List<String> getProperties()
{
return new ArrayList<String>(exception.keySet());
}
public int getSize()
{
return exception.size();
}
public Boolean Contains(String property)
{
return exception.get(property)!=null;
}
public String getException(String property)
{
return exception.get(property);
}
public void addException(String property,String message)
{
exception.put(property,message);
}
}