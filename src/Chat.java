import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.HierarchyBoundsAdapter;

public class Chat extends JFrame  {


    protected static JTextArea chatArea = new JTextArea();
    protected static final JTextField inputField = new JTextField(15);
    protected static final JButton send = new JButton("send");



    public Chat() throws HeadlessException {
        setTitle("Chat GeekBrains v 0.1");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(300, 500);
        setLocationRelativeTo(null);

        CardLayout cardL = new CardLayout();
        Panel panel = new Panel();
        panel.setLayout(cardL);

        chatArea.setEditable(false);
        chatArea.setBackground(new Color(255,240,240));
        panel.add(chatArea);


        add(panel, BorderLayout.NORTH);
        Panel panel2 = new Panel();
        panel2.add(inputField);
        inputField.setEditable(true);
        panel2.add(send);
        panel2.setBackground(Color.LIGHT_GRAY);
        add(panel2, BorderLayout.SOUTH);

        inputField.addActionListener(new ChatListener());
        send.addActionListener(new ChatListener());



        setVisible(true);
    }


}
