package jaipur.view;

import jaipur.control.BaseState;

import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 保存界面上所有组件，便于其他类统一操作
 */
public class StoredViews {

    private GameFrame gameFrame = new GameFrame();//游戏面板类

    private static StoredViews instance = new StoredViews();

    private StoredViews(){};

    public static StoredViews getInstance() {
        if (instance == null) {
            instance = new StoredViews();
        }
        return instance;
    }

    public GameFrame getGameFrame() {
        return gameFrame;
    }

    public void setGameFrame(GameFrame gameFrame) {
        this.gameFrame = gameFrame;
    }

    /**
     * 在面板上显示命令相关消息
     */
    public void showCommandMessage(String message) {
        JTextArea jTextArea = gameFrame.getWestPanel().getjTextArea();
        jTextArea.setText("");//清空面板消息
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        String time = "【" + dateFormat.format(date) + "】 ";
        jTextArea.setText(time + message);
    }

    /**
     * 在面板上显示游戏全局信息
     */
    public void showGameInfo(String gameInfo) {
        JTextArea jTextArea = gameFrame.getEastPanel().getjTextArea();
        jTextArea.setText(gameInfo);
    }
}
