package lk;


import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UdpRcv {
    
    static String strRcvContent;
    static String strRcvIP;
    
    public static void rcv(int u32PortLocal) throws IOException
    {
        DatagramSocket ds = new DatagramSocket(u32PortLocal);
        //创建接收数据报套接字并将其绑定到本地主机上的指定端口
        byte[] buf = new byte[1024];
        DatagramPacket dp = new DatagramPacket(buf, 1024);
        ds.receive(dp);
//        String strRecv = new String(dp.getData(), 0, dp.getLength()) + " from "
//                + dp.getAddress().getHostAddress() + ":" + dp.getPort();
//        System.out.println(strRecv);
//        
//        strRcvContent = new String(dp.getData(), 0, dp.getLength());
        strRcvContent = new String(dp.getData(), "utf8").trim();
        
        strRcvIP = dp.getAddress().getHostAddress();
        
        System.out.println("<-- UDP " + strRcvIP + ":" + u32PortLocal + " " + strRcvContent);
        
        ds.close();        
    }
    
    public static void main(String[] args)
    {
        while(true)
        {
            try
            {
//                String strRcvContent = new String();
//                String strRcvIP = new String();
                rcv(Constant.u32HelloPort);
//                strRcvContent = UdpRcv.strRcvContent;
//                strRcvIP = UdpRcv.strRcvIP;
//                
//                System.out.println(strRcvContent + strRcvIP);
            }
            catch (IOException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
