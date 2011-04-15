package lk;

import java.awt.Color;

import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class TextPaneLK extends JTextPane
{

    /**
     * 
     */
    private static final long serialVersionUID = -6786806566473277485L;

    public void setCaretPosition()
    {
        this.setCaretPosition(this.getStyledDocument().getLength());
        return;
    }

    public void AddText(String strStr, Color clrColor)
    {
        SimpleAttributeSet attrSet = new SimpleAttributeSet();
        StyleConstants.setForeground(attrSet, clrColor);
        StyledDocument doc = this.getStyledDocument();
        try
        {
            doc.insertString(doc.getLength(), strStr, attrSet);
        }
        catch (BadLocationException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
