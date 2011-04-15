package lk;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;


public class Setting
{
    String strFILENAME = "Settings.xml";
    
	public boolean IsExist()
	{
		File f = new java.io.File("Settings.xml");
		if(!f.exists())
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	
	public void CreateFile()
	{
        /* Create file */
        Document document = DocumentHelper.createDocument();
        Element eleSetting = document.addElement("Setting");
        Element eleNickName = eleSetting.addElement("NickName");
        eleNickName.setText("Init");

        try
        {
            XMLWriter writer;
            OutputFormat format = OutputFormat.createPrettyPrint();   
            /** 指定XML编码 */  
            format.setEncoding("UTF-8");   
            //writer= new XMLWriter(new FileWriter(new File(filename)),format);   
            //换成下面的方法   
            OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(strFILENAME),"UTF-8");
            writer= new XMLWriter(osw,format);  
            
//            writer = new XMLWriter(new FileWriter(strFILENAME), format);
            writer.write(document);
            writer.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
	
	@SuppressWarnings("unchecked")
    public void SetNickName(String strNickName)
	{
	      SAXReader saxReader = new SAXReader();
	        List list;
	        
	        Document document;
	        try
	        {
	            document = saxReader.read(strFILENAME);
	            list = document.selectNodes("//Setting");
	            Iterator iter = list.iterator();
	            while (iter.hasNext())
	            {
	                Element element = (Element) iter.next();
	                Iterator iterator = element.elementIterator("NickName");
	                while (iterator.hasNext())
	                {
	                    Element titleElement = (Element) iterator.next();
	                    titleElement.setText(strNickName);
	                    try
	                    {
	                        XMLWriter writer;
	                        OutputFormat format = OutputFormat.createPrettyPrint();   
	                        /** 指定XML编码 */  
	                        format.setEncoding("UTF-8");   
	                        //writer= new XMLWriter(new FileWriter(new File(filename)),format);   
	                        //换成下面的方法   
	                        OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(strFILENAME),"UTF-8");
	                        writer= new XMLWriter(osw,format);  
	                        
//	                        writer = new XMLWriter(new FileWriter(strFILENAME), format);
	                        writer.write(document);
	                        writer.close();
	                    }
	                    catch (IOException e)
	                    {
	                        e.printStackTrace();
	                    }
	                    return;
	                }
	            }
	        }
	        catch (DocumentException e)
	        {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	}
	
	@SuppressWarnings("unchecked")
    public String GetNickName()
	{
	    SAXReader saxReader = new SAXReader();
	    List list;
	    
	    Document document;
        try
        {
            document = saxReader.read(strFILENAME);
            list = document.selectNodes("//Setting");
            Iterator iter = list.iterator();
            while (iter.hasNext())
            {
                Element element = (Element) iter.next();
                Iterator iterator = element.elementIterator("NickName");
                while (iterator.hasNext())
                {
                    Element titleElement = (Element) iterator.next();
                    return titleElement.getText();
                }
            }
        }
        catch (DocumentException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return null;

    }
	
    public static void main(String[] args)
    {
        Setting tst = new Setting();
        if (false == tst.IsExist())
        {
            System.out.println("Setting Not Exist--- Create");
            tst.CreateFile();
        }
        else
        {
            System.out.println("Setting Exist");
            System.out.println("NickName:" + tst.GetNickName());
            tst.SetNickName("Leiking");
            System.out.println("NickName:" + tst.GetNickName());
        }
        
        
    }
}
