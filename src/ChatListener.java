import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ChatListener implements ActionListener {

    private static List<String> list = new ArrayList<>();

    @Override
    public void actionPerformed(ActionEvent e) {

        list.add(Chat.inputField.getText());
        if(list.size() > 25) list.remove(0);
        Chat.inputField.setText(null);
        Chat.chatArea.setText(null);
        for(String str: list) {
            Chat.chatArea.append(" - " + str + "\n");
        }
    }

}
