package jaipur.main;

import jaipur.command.Dispatcher;
import jaipur.view.GameFrame;

public class Main {
    public static void main(String[] args) {
        launcher();
    }

    /**
     * 初始化方法
     */
    private static void launcher() {
        //初始化界面
        GameFrame gameFrame = new GameFrame();
        //初始化命令列表等
        Dispatcher.loadCommand();

    }
}
