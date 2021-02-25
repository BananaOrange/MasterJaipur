package jaipur.view;

import jaipur.command.Dispatcher;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 实现顶部"执行"按钮的事件监听
 */
public class ButtonListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        NorthPanel northPanel = StoredViews.getInstance().getGameFrame().getNorthPanel();
        String command = northPanel.getjTextField().getText();
        Dispatcher.postCommand(command.toLowerCase());
    }
}
