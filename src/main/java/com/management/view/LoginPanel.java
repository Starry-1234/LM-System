package com.management.view;

import com.management.view.LoginFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

public class LoginPanel extends JPanel {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private LoginFrame loginFrame;
    private Properties properties;

    public LoginPanel(LoginFrame loginFrame) {
        this.loginFrame = loginFrame;
        setLayout(new BorderLayout());

        // 读取 application.properties 文件
        properties = new Properties();
        try (FileInputStream fis = new FileInputStream("src/main/resources/application.properties")) {
            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }

        JLabel titleLabel = new JLabel("图书管理系统", JLabel.CENTER);
        titleLabel.setFont(new Font("微软雅黑", Font.BOLD, 20));
        titleLabel.setForeground(Color.BLUE);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0)); // 上、左、下、右边距

        JPanel loginPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;

        JLabel usernameLabel = new JLabel("用户名：");
        usernameLabel.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        gbc.gridx = 0;
        gbc.gridy = 0;
        loginPanel.add(usernameLabel, gbc);

        usernameField = new JTextField(15);
        usernameField.setPreferredSize(new Dimension(usernameField.getPreferredSize().width, usernameField.getFontMetrics(usernameField.getFont()).getHeight() + 5));
        gbc.gridx = 1;
        gbc.gridy = 0;
        loginPanel.add(usernameField, gbc);

        JLabel passwordLabel = new JLabel("密码：");
        passwordLabel.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        gbc.gridx = 0;
        gbc.gridy = 1;
        loginPanel.add(passwordLabel, gbc);

        passwordField = new JPasswordField(15);
        passwordField.setPreferredSize(new Dimension(passwordField.getPreferredSize().width, passwordField.getFontMetrics(passwordField.getFont()).getHeight() + 5));
        gbc.gridx = 1;
        gbc.gridy = 1;
        loginPanel.add(passwordField, gbc);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton loginButton = new JButton("登录");
        loginButton.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        JButton registerButton = new JButton("注册");
        registerButton.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        JButton resetButton = new JButton("找回密码");
        resetButton.setFont(new Font("微软雅黑", Font.PLAIN, 15));
        buttonPanel.add(loginButton);
        buttonPanel.add(registerButton);
        buttonPanel.add(resetButton);

        add(titleLabel, BorderLayout.NORTH);
        add(loginPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                if (checkLogin(username, password)) {
                    JOptionPane.showMessageDialog(LoginPanel.this, "登录成功", "提示", JOptionPane.INFORMATION_MESSAGE);

                    // 关闭登录窗口
                    loginFrame.dispose();

                    // 打开主界面
                    MainFrame mainFrame = new MainFrame();
                    mainFrame.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(LoginPanel.this, "用户名或密码错误", "错误", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginFrame.showPanel("register");
            }
        });

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginFrame.showPanel("resetPassword");
            }
        });
    }

    private boolean checkLogin(String username, String password) {
        try {
            String url = properties.getProperty("db.url");
            String user = properties.getProperty("db.username");
            String pass = properties.getProperty("db.password");
            Connection conn = DriverManager.getConnection(url, user, pass);
            String sql = "SELECT * FROM login WHERE 用户名 = ? AND 密码 = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
