package lk;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;

public class SettingDialog extends JDialog
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5810758716018286513L;
	private JTextField jtfNickName;
	/**
	 * Launch the application
	 * @param args
	 */
	public static void main(String args[])
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					SettingDialog dialog = new SettingDialog();
					dialog.addWindowListener(new WindowAdapter()
					{
						public void windowClosing(WindowEvent e)
						{
							System.exit(0);
						}
					});
					dialog.setVisible(true);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the dialog
	 */
	public SettingDialog()
	{
		super();
		setUndecorated(true);
		setResizable(false);
		addWindowListener(new WindowAdapter() {
		    public void windowClosing(final WindowEvent arg0)
		    {
		        return;
		    }
	    });
		setModal(true);
		setAlwaysOnTop(true);
		setTitle("设置");
		setBounds(100, 100, 365, 48);
//		setUndecorated(true);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		final JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(BevelBorder.RAISED, Color.GRAY, Color.BLACK));
		getContentPane().add(panel, BorderLayout.CENTER);

		final JLabel label = new JLabel();
		label.setText("昵称");
		panel.add(label);

		jtfNickName = new JTextField();
		jtfNickName.setPreferredSize(new Dimension(200, 20));
		panel.add(jtfNickName);

		final JButton button = new JButton();
		button.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent arg0)
			{
			    Setting setOwnerSetting = new Setting();
			    if (jtfNickName.getText().equals(""))
			    {
			        dispose();
			        return;
			    }
			    setOwnerSetting.SetNickName(jtfNickName.getText());
			    dispose();
			}
		});
		button.setText("确认");
		panel.add(button);
		//
	}

}
