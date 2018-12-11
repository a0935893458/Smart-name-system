package com.xml;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class MYLHandler extends DefaultHandler
{
	  private boolean h_scuess = false;
	  
	  private MYStruct myParsedExampleDataSet;
	  
	  public MYStruct getParsedData() 
	  {
	       return this.myParsedExampleDataSet;
	  }
	  
	  public void startDocument() throws SAXException 
	  {
	       this.myParsedExampleDataSet = new MYStruct();
	  }

	  @Override
	  public void endDocument() throws SAXException {
	       // Nothing to do
	  }

	  @Override
	  public void startElement(String namespaceURI, String localName,
	            String qName, Attributes atts) throws SAXException 
	  {
	       if (localName.toLowerCase().equals("result")) {
	           this.h_scuess = true;
	       }
	  }
	  @Override
	  public void endElement(String namespaceURI, String localName, String qName)
	           throws SAXException {
	    if (localName.toLowerCase().equals("result")) {
		      this.h_scuess = false;
	    }
	  }
	  

	  @Override
	 public void characters(char ch[], int start, int length) {
	    if(this.h_scuess){
	       myParsedExampleDataSet.h_chilid = new String(ch,start,length);
	    }
	 }

}
