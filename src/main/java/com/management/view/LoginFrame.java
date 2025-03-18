package com.management.view;

import javax.swing.*;

public class LoginFrame extends JFrame {
    public LoginFrame() {
        // 设置窗口属性
        setTitle("登录");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // 添加登录页面
        add(new LoginView(this));

        // 显示窗口
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new LoginFrame();
        });
    }
}