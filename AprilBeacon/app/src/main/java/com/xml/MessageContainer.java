package com.xml;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.util.Log;

public class MessageContainer 
{

	private ArrayList<MessageStruct> jlist_items;

	//item
	public ArrayList<MessageStruct> getListItems() 
	{
	  return jlist_items;
	}
	
	public MessageStruct getoneJL(int index)
	{
		return jlist_items.get(index);
	}
	
	public MessageContainer() 
	{
		jlist_items = new ArrayList<MessageStruct>();
	}

	public void addRXMLItem(MessageStruct item) 
	{
		jlist_items.add(item);
	}
}
