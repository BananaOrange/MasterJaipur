package jaipur.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * 游戏面板类
 */
public class GameFrame extends JFrame {

    private static final int PANEL_WIDTH = 600;//面板宽度
    private static final int PANEL_HEIGHT = 600;//面板高度
    private static final String PANEL_TITLE = "斋普尔AI V0.1";//标题
    private NorthPanel northPanel;//顶部面板
    private WestPanel westPanel;//左侧面板
    private EastPanel eastPanel;//右侧面板

    public GameFrame() {
        JFrame jFrame = new JFrame();
        jFrame.setSize(PANEL_WIDTH, PANEL_HEIGHT);
        jFrame.setTitle(PANEL_TITLE);

        northPanel = new NorthPanel();
        westPanel = new WestPanel();
        eastPanel = new EastPanel();
        jFrame.add(northPanel, BorderLayout.NORTH);
        jFrame.add(westPanel, BorderLayout.WEST);
        jFrame.add(eastPanel, BorderLayout.EAST);

        jFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        jFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                int flag = JOptionPane.showConfirmDialog(jFrame, "是否确认退出?", "提示!",
                        JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
                if (JOptionPane.YES_OPTION == flag) {
                    System.exit(0);
                }
            }
        });

        jFrame.setResizable(false);
        jFrame.setVisible(true);
    }

    public NorthPanel getNorthPanel() {
        return northPanel;
    }

    public void setNorthPanel(NorthPanel northPanel) {
        this.northPanel = northPanel;
    }

    public WestPanel getWestPanel() {
        return westPanel;
    }

    public void setWestPanel(WestPanel westPanel) {
        this.westPanel = westPanel;
    }

    public EastPanel getEastPanel() {
        return eastPanel;
    }

    public void setEastPanel(EastPanel eastPanel) {
        this.eastPanel = eastPanel;
    }
}

/**
 * 顶部命令输入区
 */
class NorthPanel extends JPanel {

    private static final int NORTH_PANEL_WIDTH = 500;
    private static final int NORTH_PANEL_HEIGHT = 50;
    private static final int ELEMENT_HEIGHT = 30;

    private JLabel jLabel = new JLabel("命令输入");
    private JTextField jTextField = new JTextField();
    private JButton jButton = new JButton("执行");

    public NorthPanel() {
        this.setPreferredSize(new Dimension(NORTH_PANEL_WIDTH, NORTH_PANEL_HEIGHT));

        jLabel.setSize(50,ELEMENT_HEIGHT);
        jTextField.setPreferredSize(new Dimension(350, ELEMENT_HEIGHT));
        jButton.setPreferredSize(new Dimension(100, ELEMENT_HEIGHT));
        //为命令输入框和按钮添加事件
        ButtonListener buttonListener = new ButtonListener();
        jTextField.addActionListener(buttonListener);
        jButton.addActionListener(buttonListener);

        this.add(jLabel);
        this.add(jTextField);
        this.add(jButton);
    }

    public JTextField getjTextField() {
        return jTextField;
    }
}

/**
 * 左侧命令输出区
 */
class WestPanel extends JPanel {

    private static final int WEST_PANEL_WIDTH = 300;
    private static final int WEST_PANEL_HEIGHT = 500;
    private static final int TEXTAREA_ROWS = 20;
    private static final int TEXTAREA_COLUMNS = 25;
    private static final String GAME_TIP = "输入 help 获取所有命令列表\n\n" +
            "表示货物的通用命令:  [num] [item]\n\n" +
            "[1] num=数量, item=物品\n[2] 举例: 1x2p, 表示1个香料和2个皮革\n" +
            "[3] 该命令最多10个字符, 大小写不敏感\n" +
            "[4] 物品:  z:钻石  h:黄金  b:白银\n" +
            "                s:丝绸  x:香料  p:皮革\n" +
            "                l:骆驼\n";

    private JTextArea tipArea = new JTextArea(GAME_TIP,10,TEXTAREA_COLUMNS);
    private JTextArea jTextArea = new JTextArea(TEXTAREA_ROWS, TEXTAREA_COLUMNS);
    private JScrollPane jScrollPane = new JScrollPane(jTextArea);

    public WestPanel() {
        this.setPreferredSize(new Dimension(WEST_PANEL_WIDTH, WEST_PANEL_HEIGHT));

        tipArea.setLineWrap(true);
        tipArea.setEditable(false);
        jTextArea.setEditable(false);
        jTextArea.setLineWrap(true);
        this.add(tipArea);
        this.add(jTextArea);
    }

    public JTextArea getjTextArea() {
        return jTextArea;
    }
}

/**
 * 右侧游戏信息区
 */
class EastPanel extends JPanel {

    private static final int EAST_PANEL_WIDTH = 300;
    private static final int EAST_PANEL_HEIGHT = 500;
    private static final int TEXTAREA_ROWS = 30;
    private static final int TEXTAREA_COLUMNS = 25;

    private JTextArea jTextArea = new JTextArea(TEXTAREA_ROWS, TEXTAREA_COLUMNS);
    private JScrollPane jScrollPane = new JScrollPane(jTextArea);

    public EastPanel() {
        this.setPreferredSize(new Dimension(EAST_PANEL_WIDTH, EAST_PANEL_HEIGHT));

        jTextArea.setLineWrap(true);
        jTextArea.setEditable(false);
        this.add(jTextArea);
    }

    public JTextArea getjTextArea() {
        return jTextArea;
    }

    public void setjTextArea(JTextArea jTextArea) {
        this.jTextArea = jTextArea;
    }
}
