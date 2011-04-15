package test;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

public class Xml
{



	public void xmlWriteDemoByDocument() 
	{ 
//	    /** 建立document对象 */ 
//	     Document document = DocumentHelper.createDocument(); 
//	    /** 建立config根节点 */ 
//	     Element configElement = document.addElement("config"); 
//	    /** 建立ftp节点 */ 
//	     configElement.addComment("东电ftp配置"); 
//	     Element ftpElement = configElement.addElement("ftp"); 
//	     ftpElement.addAttribute("name","DongDian"); 
//	    /** ftp 属性配置 */ 
//	     Element hostElement = ftpElement.addElement("ftp-host"); 
//	     hostElement.setText("127.0.0.1"); 
//	     (ftpElement.addElement("ftp-port")).setText("21"); 
//	     (ftpElement.addElement("ftp-user")).setText("cxl"); 
//	     (ftpElement.addElement("ftp-pwd")).setText("longshine"); 
//	     ftpElement.addComment("ftp最多尝试连接次数"); 
//	     (ftpElement.addElement("ftp-try")).setText("50"); 
//	     ftpElement.addComment("ftp尝试连接延迟时间"); 
//	     (ftpElement.addElement("ftp-delay")).setText("10");     
//	    /** 保存Document */ 
//	     doc2XmlFile(document,"classes/xmlWriteDemoByDocument.xml"); 
	    Document document = DocumentHelper.createDocument();
	    Element root = document.addElement( "root" );
	
	    Element author1 = root.addElement( "author" )
	        .addAttribute( "name", "James" )
	        .addAttribute( "location", "UK" )
	        .addText( "James Strachan" );
	    
	    Element author2 = root.addElement( "author" )
	        .addAttribute( "name", "Bob" )
	        .addAttribute( "location", "US" )
	        .addText( "Bob McWhirter" );
	
	    FileWriter out;
		try
		{
			out = new FileWriter( "foo.xml" );
			document.write( out );
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}
	
	
	public void generateDocument(){
		Document document = DocumentHelper.createDocument();
		     Element catalogElement = document.addElement("catalog");
		     catalogElement.addComment("An XML Catalog");
		     catalogElement.addProcessingInstruction("target","text");
		     Element journalElement =  catalogElement.addElement("journal");
		     journalElement.addAttribute("title", "XML Zone");
		     journalElement.addAttribute("publisher", "IBM developerWorks");
		     Element articleElement=journalElement.addElement("article");
		     articleElement.addAttribute("level", "Intermediate");
		     articleElement.addAttribute("date", "December-2001");
		     Element  titleElement=articleElement.addElement("title");
		     titleElement.setText("Java configuration with XML Schema");
		     Element authorElement=articleElement.addElement("author");
		     Element  firstNameElement=authorElement.addElement("firstname");
		     firstNameElement.setText("Marcello");
		     Element lastNameElement=authorElement.addElement("lastname");
		     lastNameElement.setText("Vitaletti");
		     document.addDocType("catalog",
		                           null,"file://c:/Dtds/catalog.dtd");
		    try{
		    XMLWriter output = new XMLWriter(
		            new FileWriter( new File("catalog.xml") ));
		        output.write( document );
		        output.close();
		        }
		     catch(IOException e){System.out.println(e.getMessage());}
		     
		    try
			{
				write(document);
			}
			catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
	public void write(Document document) throws IOException {

        // lets write to a file
        XMLWriter writer;
//		= new XMLWriter(
//            new FileWriter( "test.xml" )
//        );
//        writer.write( document );
//        writer.close();


        // Pretty print the document to System.out
        OutputFormat format = OutputFormat.createPrettyPrint();
        writer = new XMLWriter( new FileWriter( "test.xml" ), format );
        writer.write( document );
        writer.close();
        // Compact format to System.out
//        format = OutputFormat.createCompactFormat();
//        writer = new XMLWriter( System.out, format );
//        writer.write( document );
    }
	
	
	public static void main(String[] args) 
	{
		Xml tst = new Xml();
		tst.generateDocument();
	}
}