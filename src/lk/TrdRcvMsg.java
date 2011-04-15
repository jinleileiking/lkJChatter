package lk;

/**
 * 
 */
import java.awt.Color;
import java.awt.Frame;
import java.io.IOException;
import java.net.InetAddress;


/**
 * @author Administrator
 *
 */
public class TrdRcvMsg extends Thread
{
    
	
    public TrdRcvMsg()
    { 
    }
    @Override
    public void run()
    {    	
        while (true)
        {
            // open udp port
//            System.out.println("trdRcvHello running");

            try
            {
                UdpRcv.rcv(Constant.u32MsgPort);
                
                if (!InetAddress.getLocalHost().getHostAddress().equals(UdpRcv.strRcvIP))
                {
                    TextPaneLK tpTp =  MainFrame.GetInstance().GetMsgPane();
                    
                    /* 若最小化则需要闪烁 */
                    if (Frame.ICONIFIED == MainFrame.GetInstance().getExtendedState() ||
                            !MainFrame.GetInstance().isFocused())
                    {
                        SysTray.GetInstance().setFlash(true);
                    }
                    
                    if (NickNameTbl.GetInstance().GetName(UdpRcv.strRcvIP) != null)
                    {


                        
                        tpTp.AddText(Time.GetTime() + NickNameTbl.GetInstance().GetName(UdpRcv.strRcvIP) + 
                                ": ", Color.blue);
                        tpTp.AddText(UdpRcv.strRcvContent + "\r\n", Color.black);
                        
                        
                    }
                    else
                    {
                        tpTp.AddText(Time.GetTime() +
                                UdpRcv.strRcvIP + 
                               ": ",  Color.blue);
                       tpTp.AddText(UdpRcv.strRcvContent + "\r\n", Color.black);
                       
                    }
//					toolTip.setToolTip(new ImageIcon("trayicon.jpg"),
//					        NickNameTbl.GetInstance().GetName(UdpRcv.strRcvIP) + ": " + UdpRcv.strRcvContent);
//                    toolTip.setToolTip(new ImageIcon("trayicon.jpg"),"你有新消息!");
                    
                    tpTp.setCaretPosition();
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

