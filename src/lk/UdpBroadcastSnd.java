package lk;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
public class UdpBroadcastSnd {
    public static void snd(String cStrToSnd, int u32Port)throws SocketException, UnknownHostException
    {
    	String strBroadcast = "10.86.12.255";
        DatagramSocket ds = new DatagramSocket();// 创建用来发送数据报包的套接字
        DatagramPacket dp = new DatagramPacket(cStrToSnd.getBytes(),
                                               cStrToSnd.getBytes().length,
                                               InetAddress.getByName(strBroadcast), u32Port);
        // 构造数据报包，用来将长度为 length 的包发送到指定主机上的指定端口号
        try 
        {
            ds.send(dp);
            System.out.println("--> UDP " + strBroadcast + ":"+ u32Port + " " + cStrToSnd);
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
        ds.close();
        
    }

    public static void UniSnd(String[] strDstIpadd, String cStrToSnd, int u32Port)throws SocketException, UnknownHostException
    {
    	for (int i = 0; i < strDstIpadd.length; i++) 
    	{
    		System.out.println("UdpUniBroadcasting:" + strDstIpadd[i]);
	    	String[] strSplitted = strDstIpadd[i].split("\\.");
			
	    	for (int s32Tmp = 1; s32Tmp <255; s32Tmp++)
	    	{
	 			String strUdpSndFinal;
	 			strUdpSndFinal = strSplitted[0] + "." + 
					 			strSplitted[1] + "." + 
					 			strSplitted[2] + "." + 
					 			s32Tmp;   	
	 	    	DatagramSocket ds = new DatagramSocket();// 创建用来发送数据报包的套接字
	 	        DatagramPacket dp;
				try {
					dp = new DatagramPacket(cStrToSnd.getBytes("utf8"),
															cStrToSnd.getBytes("utf8").length,
															InetAddress.getByName(strUdpSndFinal), u32Port);
				} catch (UnsupportedEncodingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					return;
				}
	 	        // 构造数据报包，用来将长度为 length 的包发送到指定主机上的指定端口号
	 	        try 
	 	        {
	 	            ds.send(dp);
//	 	            System.out.println("--> UDP " + strUdpSndFinal + ":"+ u32Port + " " + cStrToSnd);
	 	        } 
	 	        catch (IOException e) 
	 	        {
	 	            e.printStackTrace();
	 	        }
	 	        ds.close(); 
	 	        
	 	        try
				{
					Thread.sleep(1);
				}
				catch (InterruptedException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}    
	    	

		}

    }
    
      public static void main(String[] args) {
          
//          while(true)
//          {
//            try
//            {
//            	UniSnd("10.86.12.24", "hello", Constant.u32HelloPort);
//            }
//            catch (SocketException e)
//            {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//            catch (UnknownHostException e)
//            {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//            
//            try
//            {
//                Thread.sleep(10000);
//            }
//            catch (InterruptedException e)
//            {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//          }
    }
//    
    
//    public static void main(String[] args) {
//        Timer timer = new Timer();
//        timer.schedule(new MyTask(), 1000, 1000);
//    }
//    static class MyTask extends java.util.TimerTask{ 
//        @Override
//        public void run() { 
//            UdpBroadcastSnd tt = new UdpBroadcastSnd();
//            Date d = new Date();
//            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
//            String strdate = sdf.format(d);
//            String[] classTime = {"17:18:00","17:19:00","17:20:00"};
//            for(int i = 0;i<classTime.length;i++){
//                 if(classTime[i].equals(strdate)){
//                     try {
//                        tt.sendData();
//                    } catch (SocketException e) {
//                        // TODO Auto-generated catch block
//                        e.printStackTrace();
//                    } catch (UnknownHostException e) {
//                        // TODO Auto-generated catch block
//                        e.printStackTrace();
//                    }
//                 }
//            }   
//        }
//    }
}