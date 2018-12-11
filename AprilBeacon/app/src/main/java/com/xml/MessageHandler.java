package com.xml;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import android.util.Log;

public class MessageHandler extends DefaultHandler
{
	//tag
	private String TAG = "MessageHandler";
	
	private final static int ID = 1;
	private final static int TITLE = 2;
	private final static int CONTENT = 3;
	private final static int PIC = 4;
	private final static int ADDRESS = 5;
	private final static int PNUM = 6;
	private final static int TYPE = 7;
	private final static int USER = 8;
	private final static int AREA = 9;
	private final static int RDATE = 10;

	private MessageStruct jls;
	private MessageContainer jlcs;
	
	private int type;

	public MessageContainer getContainer() 
	{
		return jlcs;
	}

	public MessageStruct getJListStruct() 
	{
		return jlcs.getoneJL(0);
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		
		String s = new String(ch, start, length);
		
		switch (type) 
		{
		case ID:
			jls.id = s;
			type = 0;
			break;
		case TITLE:
			jls.title = s;
			type = 0;
			break;
		case CONTENT:
			jls.content = s;
			type = 0;
			break;
		case PIC:
			jls.pic = s;
			type = 0;
			break;
		case ADDRESS:
			jls.address = s;
			type = 0;
			break;
		case PNUM:
			jls.pnum = s;
			type = 0;
			break;
		case TYPE:
			jls.type = s;
			type = 0;
			break;
		case USER:
			jls.user = s;
			type = 0;
			break;
		case AREA:
			jls.area = s;
			type = 0;
			break;
		case RDATE:
			jls.rdate = s;
			type = 0;
			break;
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {

		if (localName.toLowerCase().equals("item")) 
		{
			jlcs.addRXMLItem(jls);	
		}
	}

	@Override
	public void startDocument() throws SAXException 
	{
		jlcs = new MessageContainer();
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException 
	{
		if (localName.toLowerCase().equals("item")) 
		{
			jls = new MessageStruct();
			return;
		}
		else if (localName.toLowerCase().equals("id")) 
		{
			type = ID;
			return;
		}
		else if (localName.toLowerCase().equals("title")) 
		{
			type = TITLE;
			return;
		}
		else if (localName.toLowerCase().equals("content")) 
		{
			type = CONTENT;
			return;
		}
		else if (localName.toLowerCase().equals("pic")) 
		{
			type = PIC;
			return;
		}
		else if (localName.toLowerCase().equals("address")) 
		{
			type = ADDRESS;
			return;
		}
		else if (localName.toLowerCase().equals("pnum")) 
		{
			type = PNUM;
			return;
		}
		else if (localName.toLowerCase().equals("type")) 
		{
			type = TYPE;
			return;
		}
		else if (localName.toLowerCase().equals("user")) 
		{
			type = USER;
			return;
		}
		else if (localName.toLowerCase().equals("area")) 
		{
			type = AREA;
			return;
		}		
		else if (localName.toLowerCase().equals("rdate")) 
		{
			type = RDATE;
			return;
		}		
		type = 0;
	}

}