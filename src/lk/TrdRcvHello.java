package lk;

/**
 * 
 */
import java.io.IOException;

import javax.swing.DefaultListModel;


/**
 * @author Administrator
 *
 */
public class TrdRcvHello extends Thread
{
    DefaultListModel dlmToAdd;
    public TrdRcvHello(DefaultListModel dlmToAdd)
    { 
        this.dlmToAdd = dlmToAdd;
    }
    @Override
    public void run()
    {
        Boolean bFind = false;

        while (true)
        {
            // open udp port
            // System.out.println("trdRcvHello running");

            try
            {
                UdpRcv.rcv(Constant.u32HelloPort);

                if (-1 != UdpRcv.strRcvContent.indexOf(Constant.strHello))
                {
                    // get ip, refresh the list
                    // UdpRcv.strRcvIP;
                    // Event Hell rcved

                    if (UdpRcv.strRcvIP.equals(LocalHost.getIP()))
                    {
                        continue;
                    }

                    String strRcvNickname = UdpRcv.strRcvContent.substring(UdpRcv.strRcvContent.indexOf(Constant.strHello) + Constant.strHello.length());
                    System.out.println("strRcvNickname: " + strRcvNickname);

                    String[] kmName = new String[dlmToAdd.getSize()];
                    for (int i = 0; i < dlmToAdd.getSize(); i++)
                    {
                        kmName[i] = (String) dlmToAdd.getElementAt(i);
                        if (UdpRcv.strRcvIP.equals(NickNameTbl.GetInstance().GetIp(kmName[i])))
                        {
                            bFind = true;
                            break;
                        }
                        else
                        {
                            bFind = false;
                        }
                    }

                    if (bFind == false)
                    {
                        dlmToAdd.addElement(strRcvNickname);
                        NickNameTbl.GetInstance().Add(UdpRcv.strRcvIP, strRcvNickname);
                        System.out.println(UdpRcv.strRcvIP);
                    }

                }
            }
            catch (IOException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            try
            {
                sleep(100);
            }
            catch (InterruptedException e)
            {

            }

        }
    }
    
    public static void main(String[] args)
    {
//        trdRcvHello trdTst = new trdRcvHello();
//        {
//            trdTst.run();
//        }
    }
    
    
}

