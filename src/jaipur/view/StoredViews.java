package jaipur.view;

import jaipur.control.BaseState;

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
}
