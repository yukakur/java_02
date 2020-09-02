import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ModeListener implements ActionListener {
    private final JButton hexButton = new JButton("HEX");
    private final JButton sqrtButton = new JButton("sqrt");
    private final List<JButton> currentButtons;
    private final JPanel currentPanel;

    public ModeListener(List<JButton> currentButtons, JPanel currentPanel) {
        this.currentButtons = currentButtons;
        this.currentPanel = currentPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ButtonMode mode = (ButtonMode) ((JComboBox<String>) e.getSource()).getModel().getSelectedItem();
        Calculator.bottom.add(hexButton);
        Calculator.bottom.add(sqrtButton);
        switch (mode) {
            case BASIC: {
                Calculator.bottom.remove(hexButton);
                Calculator.bottom.remove(sqrtButton);
                break;
            }
            case ENGINEER: {
                Calculator.bottom.remove(hexButton);
//                Calculator.bottom.add(sqrtButton);
                break;
            }
            case PROGRAMMING: {
                Calculator.bottom.remove(sqrtButton);
//                Calculator.bottom.add(hexButton);
                break;
            }
        }

        currentPanel.revalidate();
        currentPanel.repaint();
        currentPanel.setVisible(true);
    }
}
