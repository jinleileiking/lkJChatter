package lk;

import java.util.HashMap;
import java.util.Iterator;

public class NickNameTbl
{
    static private NickNameTbl nntblSgl;
    private HashMap<String, String> hmNickName;
    
    public NickNameTbl()
    {
        hmNickName = new HashMap<String, String>();
        return;
    }
    
    
    public static NickNameTbl GetInstance()
    {
        if (nntblSgl == null)
        {
            nntblSgl = new NickNameTbl();
        }
        return nntblSgl;
    }
    
    public void Add(String strIp, String strNickName)
    {
        hmNickName.put(strNickName, strIp);
    }
    
    public String GetIp(String strNickName)
    {
        Iterator<String> iterIter = hmNickName.keySet().iterator();
        while (iterIter.hasNext())
        {
            String strTmp = iterIter.next();
            if (strTmp.equals(strNickName))
            {
                return hmNickName.get(strTmp);
            }
        }  
        return null;
    }
    
    public String GetName(String strIp)
    {
        Iterator<String> iterIter = hmNickName.keySet().iterator();
        while (iterIter.hasNext())
        {
            String strTmp = iterIter.next();
            if (hmNickName.get(strTmp).equals(strIp))
            {
                return strTmp;
            }
        }  
        return null;
    }
    
    public static void main(String[] args)
    {
        NickNameTbl.GetInstance().Add("10.86.10.48", "leiking");
        System.out.println(NickNameTbl.GetInstance().GetIp("leiking"));
        System.out.println(NickNameTbl.GetInstance().GetName("10.86.10.48"));
    }
}
