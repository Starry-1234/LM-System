package com.management.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginView extends JPanel {
    private JFrame loginFrame; // 登录窗口

    public LoginView(JFrame loginFrame) {
        this.loginFrame = loginFrame;
        setLayout(new BorderLayout());

        // 标题
        JLabel titleLabel = new JLabel("登录", SwingConstants.CENTER);
        titleLabel.setFont(new Font("微软雅黑", Font.BOLD, 24));
        add(titleLabel, BorderLayout.NORTH);

        // 表单面板
        JPanel formPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel usernameLabel = new JLabel("用户名:");
        JTextField usernameField = new JTextField();
        JLabel passwordLabel = new JLabel("密码:");
        JPasswordField passwordField = new JPasswordField();
        JButton loginButton = new JButton("登录");

        formPanel.add(usernameLabel);
        formPanel.add(usernameField);
        formPanel.add(passwordLabel);
        formPanel.add(passwordField);
        formPanel.add(new JLabel()); // 占位
        formPanel.add(loginButton);

        // 登录按钮事件
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                // 简单验证
                if ("admin".equals(username) && "123456".equals(password)) {
                    JOptionPane.showMessageDialog(LoginView.this, "登录成功！");

                    // 关闭登录窗口
                    loginFrame.dispose();

                    // 打开主界面
                    MainFrame mainFrame = new MainFrame();
                    mainFrame.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(LoginView.this, "用户名或密码错误！");
                }
            }
        });

        add(formPanel, BorderLayout.CENTER);
    }
}