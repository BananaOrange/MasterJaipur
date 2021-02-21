package jaipur.main;

import jaipur.command.Dispatcher;
import jaipur.view.GameFrame;
import jaipur.view.StoredViews;

public class Main {
    public static void main(String[] args) {
        launcher();
    }

    /**
     * 初始化方法
     */
    private static void launcher() {
        //初始化界面
        StoredViews storedViews = StoredViews.getInstance();
        //初始化命令列表等
        Dispatcher.loadCommand();
    }
}
