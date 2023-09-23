package com.stelaino.automation.rewards;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author stelaino
 */
public class MsRewardsAutomationApp extends JFrame {

    private static final ExecutorService TASK_EXECUTOR = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    private final JLabel driverLabel;
    private final JTextPane cookieTextPane;
    private final JTextField executionTextField;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MsRewardsAutomationApp app = new MsRewardsAutomationApp();
            app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            app.setVisible(true);
        });
    }

    public MsRewardsAutomationApp() {
        // 设置窗口标题
        setTitle("MS Rewards Automation App");

        // 创建"驱动"标签和文件选择组件
        JPanel driverPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel driverTextLabel = new JLabel("驱动:");
        // 下载对应版本的chrome浏览器驱动
        // https://github.com/GoogleChromeLabs/chrome-for-testing
        // https://googlechromelabs.github.io/chrome-for-testing/known-good-versions-with-downloads.json
        driverLabel = new JLabel("C:\\IDE\\lib\\chromedriver.exe");
        JButton driverButton = new JButton("选择文件");
        driverButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int returnValue = fileChooser.showOpenDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                driverLabel.setText(selectedFile.getAbsolutePath());
            }
        });
        driverPanel.add(driverTextLabel);
        driverPanel.add(driverLabel);
        driverPanel.add(driverButton);

        // 创建"Cookie"标签和富文本框
        JPanel cookiePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel cookieTextLabel = new JLabel("Cookie:");
        cookieTextPane = new JTextPane();
        cookieTextPane.setPreferredSize(new Dimension(300, 100));
        JScrollPane cookieScrollPane = new JScrollPane(cookieTextPane);
        cookiePanel.add(cookieTextLabel);
        cookiePanel.add(cookieScrollPane);

        // 创建"执行次数"标签和数字输入框
        JPanel executionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel executionTextLabel = new JLabel("执行次数:");
        executionTextField = new JTextField(5);
        executionTextField.setText("30");
        executionPanel.add(executionTextLabel);
        executionPanel.add(executionTextField);

        // 创建确认按钮并添加事件处理
        JButton confirmButton = new JButton("确认");
        confirmButton.addActionListener(e -> {
            String driverPath = driverLabel.getText();
            String cookieValue = cookieTextPane.getText();
            int executionCount = Integer.parseInt(executionTextField.getText());

            System.setProperty("webdriver.chrome.driver", driverPath);
            TASK_EXECUTOR.execute(() -> ChromeSearchAutomation.doTask(cookieValue, executionCount, false));
            TASK_EXECUTOR.execute(() -> ChromeSearchAutomation.doTask(cookieValue, executionCount, true));

        });

        // 设置窗口布局
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // 添加组件到窗口
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(driverPanel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(cookiePanel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(executionPanel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        add(confirmButton, gbc);

        // 调整窗口尺寸
        Dimension dimension = new Dimension(600, 400);
        setPreferredSize(dimension);
        setMinimumSize(dimension);

        setLocationRelativeTo(null); // 将窗口居中于屏幕
        pack();
    }
}
