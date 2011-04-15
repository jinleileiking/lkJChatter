package lk;

import java.awt.AWTException;
import java.awt.Frame;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;

import com.swtdesigner.SwingResourceManager;

public class SysTray extends Thread
{
    static SysTray stSgl;
    private boolean bFlash;
    private TrayIcon ti;
    private Image imgTray;
    private Image imgNull;
    static private JFrame jfMasterThis;
    
    public static void CreateInstance(JFrame jfMaster)
    {
        setJFrame(jfMaster);
        stSgl = new SysTray();
        stSgl.Create();
    }
    
    public static SysTray GetInstance()
    {
        return stSgl;
    }
    
    public void setFlash(boolean bFlash)
    {
        this.bFlash = bFlash;
    }
    
    public static void setJFrame(JFrame jfMaster)
    {
        jfMasterThis = jfMaster;
    }
    
    public void Create()
    {
        imgTray = SwingResourceManager.getIcon(MainFrame.class,
                "trayicon.jpg").getImage();
        imgNull = SwingResourceManager.getIcon(MainFrame.class,
                "null.jpg").getImage();
        
        if (SystemTray.isSupported())
        {// 判断当前平台是否支持系统托盘
            SystemTray st = SystemTray.getSystemTray();

            // Image image = Toolkit.getDefaultToolkit().getImage(
            // getClass().getClassLoader().getResource("lk\\trayicon.gif"));//
            // 定义托盘图标的图片
            ti = new TrayIcon(imgTray);
            ti.setImageAutoSize(true);
            ti.setToolTip("群聊工具");

            try
            {
                st.add(ti);
            }
            catch (AWTException e1)
            {
                // 
                e1.printStackTrace();
            }
            
            ti.addMouseListener(new MouseAdapter()
            {
                @Override
                public void mouseClicked(MouseEvent e)
                {
                    if (e.getButton() == MouseEvent.BUTTON1)// 鼠标左键单击，打开窗体
                    {
                        if (jfMasterThis.isVisible() == true)
                        {

                            jfMasterThis.setVisible(false);

                            /* 若有消息则需要闪 */
                        }
                        else
                        {
                            jfMasterThis.setVisible(true);
                            jfMasterThis.setAlwaysOnTop(true);
                            jfMasterThis.setAlwaysOnTop(false);
                            jfMasterThis.requestFocus();
                            /* 停止闪烁 */
                            bFlash = false;
                        }
                    }
                    jfMasterThis.setExtendedState(Frame.NORMAL);
                }
            });
        }
    }
    
    @Override
    public void run()
    {       
        while(true)
        {
            if (bFlash)
            {
                ti.setImage(imgNull);
                
                try
                {
                    sleep(500);
                }
                catch (InterruptedException e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                
                ti.setImage(imgTray);
                
                try
                {
                    sleep(500);
                }
                catch (InterruptedException e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            else
            {
                ti.setImage(imgTray);
                
                try
                {
                    sleep(500);
                }
                catch (InterruptedException e)
                {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
                
        }
    }
}
