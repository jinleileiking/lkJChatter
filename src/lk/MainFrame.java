package lk;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

import javax.swing.JButton;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;

import org.jvnet.substance.skin.SubstanceModerateLookAndFeel;

public class MainFrame extends JFrame
{

    /**
	 * 
	 */
    private static final long serialVersionUID = -8091824449829470873L;
    private JList jlFriends;
    private JTextField jtfSnd;
    static private MainFrame frame;
    static private Setting setOwnerSetting;
    static private String strOwnerNickName;
    TrdSndHello trdSnd;
    private boolean bFocused;
    private TextPaneLK jtpMsg;
    
    /**
     * Launch the application
     * 
     * @param args
     */
    

    
    
    public static void main(String args[])
    {
         JFrame.setDefaultLookAndFeelDecorated(true);
         try
         {
         UIManager.setLookAndFeel(new SubstanceModerateLookAndFeel());
         }
         catch (Exception e)
         {
         System.out.println("Substance Raven Graphite failed to initialize");
         }

        EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                try
                {

                    setOwnerSetting = new Setting();

                    if (!setOwnerSetting.IsExist())
                    {
                        System.out.println("NoNickName");
                        setOwnerSetting.CreateFile();
                        MsgBox.show("没有设置昵称，请在设置里设置昵称", "警告");
                        SettingDialog SettingDiag = new SettingDialog();
                        SettingDiag.setVisible(true);
                    }
                    else
                    {
                        // Get NickName
                    }

                    strOwnerNickName = setOwnerSetting.GetNickName();
                    frame = new MainFrame();
                    frame.setVisible(true);
                    SysTray.CreateInstance(frame);
                    SysTray.GetInstance().start();
                    SysTray.GetInstance().setFlash(false);

                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }

        });
    }

    private void SndText()
    {
        if (jtfSnd.getText().trim().equals(""))
        {
            // System.out.println("null");
            return;
        }

        // try {
        //        	
        /* Get Ips */
        for (int i = 0; i < jlFriends.getModel().getSize(); i++)
        {
            // if
            // (!InetAddress.getLocalHost().getHostAddress().equals(UdpRcv.strRcvIP))
            // {

            Udp.UniSnd(NickNameTbl.GetInstance().GetIp(
                    jlFriends.getModel().getElementAt(i).toString()), jtfSnd
                    .getText(), Constant.u32MsgPort);
            // }
        }
        
        jtpMsg.AddText(Time.GetTime() + "我" + ": ", Color.gray);
        jtpMsg.AddText(jtfSnd.getText() + "\n", Color.BLACK);
        jtpMsg.setCaretPosition();
        
        jtfSnd.setText("");

    }

    

    /**
     * Create the frame
     */
    public MainFrame()
    {
        super();
        addWindowFocusListener(new WindowFocusListener() {
            public void windowGainedFocus(final WindowEvent arg0)
            {
                setFocused(true);
                SysTray.GetInstance().setFlash(false);
            }
            public void windowLostFocus(final WindowEvent arg0)
            {
                setFocused(false);
            }
        });
        setTitle("lkChatter---群聊工具--by 伟大的Leiking");

        setBounds(100, 100, 654, 536);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        final JPanel panel_1 = new JPanel();
        panel_1.setLayout(new BorderLayout());
        getContentPane().add(panel_1);

        final JScrollPane scrollPane = new JScrollPane();
        scrollPane.setMinimumSize(new Dimension(0, 0));
        scrollPane.setMaximumSize(new Dimension(0, 0));
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        panel_1.add(scrollPane);

        jtpMsg = new TextPaneLK();
        scrollPane.setViewportView(jtpMsg);
        jtpMsg.setBorder(new LineBorder(Color.LIGHT_GRAY, 2, true));
        jtpMsg.setEditable(false);

        final JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setPreferredSize(new Dimension(100, 0));
        panel_1.add(scrollPane_1, BorderLayout.EAST);

        jlFriends = new JList();
        jlFriends.setBorder(new BevelBorder(BevelBorder.RAISED));
        jlFriends.setPreferredSize(new Dimension(0, 0));
        scrollPane_1.setViewportView(jlFriends);

        final JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.SOUTH);

        final JLabel jlbOwner = new JLabel();
        panel.add(jlbOwner);

        final JScrollPane scrollPane_2 = new JScrollPane();
        panel.add(scrollPane_2);

        jtfSnd = new JTextField();
        jtfSnd.addKeyListener(new KeyAdapter()
        {
            @Override
            public void keyTyped(final KeyEvent arg0)
            {
                // if (arg0.getKeyCode() == KeyEvent.VK_ENTER)
                if (arg0.getKeyChar() == '\n')
                {
                    SndText();
                }
            }
        });
        scrollPane_2.setViewportView(jtfSnd);
        jtfSnd.setPreferredSize(new Dimension(400, 25));

        final JButton jbnSnd = new JButton();

        jbnSnd.setText("发送");
        panel.add(jbnSnd);

        addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowOpened(final WindowEvent arg0)
            {

                DefaultListModel dlm = new DefaultListModel();
                jlFriends.setModel(dlm);

                TrdRcvHello trdRcv = new TrdRcvHello(dlm);
                {
                    trdRcv.start();
                }

                trdSnd = new TrdSndHello(strOwnerNickName);
                {
                    trdSnd.start();
                }

                TrdRcvMsg trdRcvMsg = new TrdRcvMsg();
                {
                    trdRcvMsg.start();
                }

                jlbOwner.setText(strOwnerNickName);
            }

            @Override
            public void windowIconified(final WindowEvent arg0)
            {
                setVisible(false); // 使窗口不可视
            }
        });

        jbnSnd.addActionListener(new ActionListener()
        {
            public void actionPerformed(final ActionEvent arg0)
            {
                SndText();
            }
        });

        final JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        final JMenu menu_1 = new JMenu();
        menu_1.setText("设置");
        menuBar.add(menu_1);

        final JMenuItem newItemMenuItem_1 = new JMenuItem();
        newItemMenuItem_1.addActionListener(new ActionListener()
        {
            public void actionPerformed(final ActionEvent arg0)
            {
                SettingDialog SettingDiag = new SettingDialog();
                SettingDiag.setVisible(true);
                trdSnd.SetOwnerName(setOwnerSetting.GetNickName());
                jlbOwner.setText(setOwnerSetting.GetNickName());
            }
        });
        newItemMenuItem_1.setText("个人配置");
        menu_1.add(newItemMenuItem_1);

        final JMenu menu = new JMenu();
        menu.setText("帮助");
        menuBar.add(menu);

        final JMenuItem newItemMenuItem = new JMenuItem();
        newItemMenuItem.addActionListener(new ActionListener()
        {
            public void actionPerformed(final ActionEvent arg0)
            {
                MsgBox.show("有任何建议请发送至jin.lei@zte.com.cn", "关于");
            }
        });
        newItemMenuItem.setText("关于");
        menu.add(newItemMenuItem);


    }

    public TextPaneLK GetMsgPane()
    {
        return jtpMsg;
    }
    
    public static MainFrame GetInstance()
    {
        return frame;
    }
    

    public void setFocused(boolean bFocused)
    {
        this.bFocused = bFocused;
    }


    @Override
    public boolean isFocused()
    {
        return bFocused;
    }

}
