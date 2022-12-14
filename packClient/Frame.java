package frames;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.awt.*;


import uses.Functions;
public class Frame extends JFrame{
    JTextField jTextField;
    JLabel jLabel;
    public Frame(){
        JPanel jPanel = new JPanel();
        Functions f = new Functions();
        JTextArea text = new JTextArea();
        text.setText(f.instructionsSt());
        text.setFont(text.getFont().deriveFont(15f));
        text.setEditable(false);
        jLabel = new JLabel("1767SQL>>");
        jTextField = new JTextField();
        jTextField.setPreferredSize(new Dimension(200,20));
        jPanel.add(jLabel);
        jPanel.add(jTextField);
        jPanel.add(text);
        this.add(jPanel);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setPreferredSize(new Dimension(500,800));
        this.setResizable(false);
        this.pack();
        this.setBackground(Color.GRAY);
        setVisible(true);
    }
    public JTextField getjTextField() {
        return jTextField;
    }
    public void setjTextField(JTextField jTextField) {
        this.jTextField = jTextField;
    }
}
