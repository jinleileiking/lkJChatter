/**
 * 
 */
package lk;
import java.net.SocketException;
import java.net.UnknownHostException;


/**
 * @author Administrator
 *
 */
public class TrdSndHello extends Thread
{
    private String strOwnerName;
    
    public TrdSndHello(String strOwnerName)
    {
        this.strOwnerName = strOwnerName;
    }
    
    public void SetOwnerName(String strOwnerName)
    {
        this.strOwnerName = strOwnerName;
    }
    
    @Override
    public void run()
    {
		while (true)
		{
			// open udp port

			try
			{
				UdpBroadcastSnd.UniSnd(Constant.strIps, Constant.strHello + strOwnerName,
						Constant.u32HelloPort);
			}
			catch (SocketException e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			catch (UnknownHostException e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			System.out.println("UniCasting Finished");
			try
			{
				sleep(50000);
			}
			catch (InterruptedException e)
			{

			}

		}
	}
    
    public static void main(String[] args)
    {
//        TrdSndHello trdTst = new TrdSndHello();
//        {
//            trdTst.run();
//        }
    }
    
    
}



