package lk;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Udp {
    public static void UniSnd(String strDstIp, String cStrToSnd, int u32Port)
    {
        DatagramSocket ds;
		try {
			ds = new DatagramSocket();
		} catch (SocketException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return;
		}// 创建用来发送数据报包的套接字
		
        DatagramPacket dp;
		try {
			try {
				dp = new DatagramPacket(cStrToSnd.getBytes("utf8"),
				                                       cStrToSnd.getBytes("utf8").length,
				                                       InetAddress.getByName(strDstIp), u32Port);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return;
			}
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return;
		}
		
        // 构造数据报包，用来将长度为 length 的包发送到指定主机上的指定端口号
        try 
        {
            ds.send(dp);
            System.out.println("--> UDP " + strDstIp + ":"+ u32Port + " " + cStrToSnd);
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
        ds.close();
        
    }
}
